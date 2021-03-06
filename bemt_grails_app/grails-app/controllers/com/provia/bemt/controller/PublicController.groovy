package com.provia.bemt.controller

import grails.transaction.Transactional
import com.provia.bemt.domain.Organization
import com.provia.bemt.domain.PasswordHistory
import com.provia.bemt.domain.Bemtuser
import com.provia.bemt.domain.PasswordResetRequest
import cr.co.arquetipos.password.*
import com.provia.bemt.service.OrganizationService
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH


class PublicController extends BemtController {
    def organizationService
    def userService
  
    static allowedMethods = [pwreset: "POST", validate: "POST"]


    def index(Object params) { 
//      println "GroovySystem.getVersion() " + GroovySystem.getVersion()
      /* this is a comment */
  	if(session.user) {
            redirect controller: 'home', view: "index"
  	}    
  	render(view: 'index', model:[obj: new Bemtuser(params)])
    }

    def validate() {
        def user = Bemtuser.findByEmailIlike(params.email)
        if (user && PasswordTools.checkDigestBase64(params.password,user.password)) {
            if (user.expirationDate > new Date())
            {
//                session.invalidate()
                def ss = request.getSession()
                ss.user = user
                def orgs = organizationService.getUserOrganizations(user)
                ss.org = null
                if(orgs.size() > 0) {
                    ss.org = orgs.first()
                }
                println "Date: " + new Date() + " Login for user: " + params.email + ": " + session.org
                user.lastLogin = new Date()
                if (user.loginCount) {
                    user.loginCount ++
                } else {
                    user.loginCount = 1
                }
                println "user.lastLogin: " + user.lastLogin
                println "user.loginCount: " + user.loginCount
                
                user.save()
                redirect controller: 'home', view: 'index'
            }
            else
            {
                flash.error = "Password has expired. Please reset password."
                render(view:'expiredPasswordLogin',   model: [obj:user])
                //redirect controller: 'public', action: 'expiredPasswordLogin', params: [user:user]
            }
        }
        else {
            flash.error = "Login failed.  Please check your email/password."
            redirect controller: 'public', action: 'login'
        }
    }

    @Transactional
    def resetpass() {
        //get token from the URL
        def token = params.token
        //check to see if the token is valid and request is still valid
        if(token) {
            def prr = PasswordResetRequest.findByToken(token)
            //check date
            def now = System.currentTimeMillis()
            def msInDay = 24*60*60*1000 //24h*60m*60s*1000ms = 1 day
            def then = prr.dateCreated.time
            def diff = now-then
            if(diff < msInDay) {
                redirect controller: 'public',  action: "passwordreset", params: [token: token]
            } else {
                //we know it exists but throw off the user just in case
                flash.error = "This password reset request either does not exist or has expired. If you wish to reset your password, please submit a new password reset request."
                redirect controller: 'public', action: "index"        
            }
        } else {
            redirect controller: 'public', action: "index"
        }
    }

    def pwreset() {

        if (!super.checkPassword(params.password)) {
            flash.error = "Your password must be at least 8 characters with no spaces and have any three of the following: uppercase character, lowercase character, numeric character and special character." 
            render view: 'passwordreset', model: [token:params.token]
            return
        }

        if (params.password != params.password_check) {
            flash.error = "Your passwords must match." 
            render view: 'passwordreset', model: [token:params.token]
            return
        }
        //get prr by token
        def prr = PasswordResetRequest.findByToken(params.token)
        if(prr) {
            def owner = prr.owner
            def user = Bemtuser.get(owner.id)
            
            if(userService.differentFromPast(user,params.password))
            {
                def ePassword = PasswordTools.saltPasswordBase64(params.password)
                user.password = ePassword
                def currentDate = new Date()
                user.dateModified = currentDate
                user.expirationDate = currentDate + changePasswordFrequency()
                user.notificationSent = "N"
            
                if(!user.save()) {
                    flash.error = parseErrors(user.errors);
                    redirect controller: 'public', view: 'index'
                    return
                }
                def pwH = new PasswordHistory(bemtuser: user, password:ePassword, changeDate: currentDate).save() 
                flash.message = "Your password has been reset, please use your email and new password to log in."
                redirect controller: 'public', action: "login"
                return
            }
            else{
                def number = userService.uniqueFromPastPasswords()
                flash.error = "Please enter a password different from past "+number+" passwords." 
                render view: 'passwordreset', model: [token:params.token]
                return
            }
        }
        redirect controller: 'public', action: "index"
    }


    def passwordreset() {
        if(params.token != '') {
            render(view: 'passwordreset', model:[token: params.token])
            return
        }
        redirect controller: 'public', action: "index"
    }
 
    
    def forgotpass() {
        
        def user = Bemtuser.findByEmailIlike(params.email)
        if(user) {
            //generate token (two random UUIDs cat'd together)
            def token = UUID.randomUUID().toString()+UUID.randomUUID().toString()
            //save request
            def prr = new PasswordResetRequest(owner:user, token:token).save()
            def url = CH.config.grails.serverURL
            //send email with token in the link
            sendMail {
                async true
                to params.email
                subject "BEMT Password Reset Request"
                html g.render(template:"resetemail", model: [url: url, token: token])
            }
        } else {
            println "user with email: "+params.email+" attempted to reset password, user does not exist."
        }
      //create a long hash.
        //create an email to the user with a link that includes the hash as a qs param
        //do some stuff
        request.withFormat {
            form {
                flash.message = "An Email with instructions for resetting your password has been sent to '"+params.email+"'!"
                redirect controller: 'public', action: 'index'
            }
        }
    }

    @Transactional
    def changeExpiredPw(Bemtuser obj)
    {
        def userEmail = obj.email
        if (!super.checkPassword(params.password)) {
            flash.error = "Your password must be at least 8 characters with no spaces and have any three of the following: uppercase character, lowercase character, numeric character and special character." 
            render( view:'expiredPasswordLogin' , model:[obj: obj])
            return
        }
        
        if (params.password != params.password_check) {
            flash.error = "Your passwords must match." 
            render(view: 'expiredPasswordLogin',  model: [obj:obj])
            return
        }
        def currentDate = new Date()
        def user = Bemtuser.findByEmailIlike(userEmail)
        if(userService.differentFromPast(user,params.password))
        {   
            def ePassword = PasswordTools.saltPasswordBase64(params.password) 
            user.password = ePassword
            user.dateModified = currentDate
            user.expirationDate = currentDate + changePasswordFrequency()
            user.notificationSent = "N"
            if(!user.save()) {
                flash.error = parseErrors(user.errors);
                render(view: 'expiredPasswordLogin',   model: [obj:obj])
                return
            } else {
                def passwordHis = new PasswordHistory(bemtuser:user,password:ePassword,changeDate:currentDate).save()
                session.user = user
                flash.message = "User Password is updated Successfully." 
                redirect controller: 'home', view: 'index'
            }
        }
        else{
            def number = userService.uniqueFromPastPasswords()
            flash.error = "Please enter a password different from past "+number+" passwords." 
            render(view: 'expiredPasswordLogin',   model: [obj:obj])
            return
        }
    }
    
    
    @Transactional
    def createuser() {
        //println(" In create user")
        if (params.nameFirst.size() == 0 || params.nameLast.size() == 0 || params.email.size() == 0) {
            flash.error = "You must fill out all the fields." 
            redirect controller: 'public', view: 'index', params: [nameFirst:params.nameFirst,nameLast:params.nameLast,email:params.email]
            return
        }
        
        def users = Bemtuser.findAllByEmailIlike(params.email)
        if ((users!=null)&& (users.size > 0)) {
            flash.error = "A BEMT user with email '"+params.email+"' already exists, please use a different email." 
            redirect controller: 'public', view: 'index', params: [nameFirst:params.nameFirst,nameLast:params.nameLast,email:params.email]
            return
        }
        
        if (!super.checkPassword(params.password)) {
            flash.error = "Your password must be at least 8 characters with no spaces and have any three of the following: uppercase character, lowercase character, numeric character and special character." 
            redirect controller: 'public', view: 'index', params: [nameFirst:params.nameFirst,nameLast:params.nameLast,email:params.email]
            return
        }
        
        if (params.password != params.password_check) {
            flash.error = "Your passwords must match." 
            redirect controller: 'public', view: 'index', params: [nameFirst:params.nameFirst,nameLast:params.nameLast,email:params.email]
            return
        }
        
        def ePassword = PasswordTools.saltPasswordBase64(params.password)
        def currentDate = new Date()
        def user = new Bemtuser(nameFirst:params.nameFirst, nameLast: params.nameLast, 
            email:params.email, password:ePassword, dateCreated: currentDate, 
            dateModified: currentDate, expirationDate: currentDate + changePasswordFrequency(), notificationSent:"N" )
        
        
        if(!user.save()) {
            if (user.errors.hasFieldErrors("email")) {
                //println user.errors.getFieldError("email").rejectedValue
                flash.error = "Please enter a valid email address"
            }
            else flash.error = parseErrors(user.errors);
            redirect controller: 'public', view: 'index', params: [nameFirst:params.nameFirst,nameLast:params.nameLast,email:params.email]
            return
        } else {
            def passwordHis = new PasswordHistory(bemtuser:user,password:ePassword,changeDate:currentDate).save()
            session.user = user
            flash.message = "New User Created Successfully." 
            redirect controller: 'home', view: 'index'
        }  
    }
    
    def login() { }
    
    def privacy() { }
    
    def terms() { }
    
    def about() { }
    
    def contact() { }
    
    def passwordresetrequest() { }
    
    def expiredPasswordLogin(Object obj) {  
        
        render(view: 'expiredPasswordLogin', model:[obj: obj])}
    
}

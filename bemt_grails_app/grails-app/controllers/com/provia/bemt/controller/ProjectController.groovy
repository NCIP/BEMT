package com.provia.bemt.controller

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.*

import com.provia.bemt.domain.Project
import com.provia.bemt.domain.Product
import com.provia.bemt.domain.ProjectProduct
import com.provia.bemt.domain.ProductType

import com.provia.bemt.service.ProjectService
import com.provia.bemt.service.ProformaProjectService


class ProjectController extends BemtController {
	def projectService
	def proformaProjectService

	def index() {
		render(view: 'index', model:[objList: Project.findAllByOrganization(session.org)])
	}

	def price(Project obj) {
		super.isValid(obj) //check to see if user can act on this obj
		def products = ProjectProduct.findAllByProject(obj)
		render(view: 'price', model:[obj: obj, productList: products])
	}

	def show(Project obj) {
		super.isValid(obj) //check to see if user can act on this obj
		super.notSupported()
	}

	def edit(Project obj) {
		super.isValid(obj) //check to see if user can act on this obj
		def serviceType = ProductType.findByName('Service')
		def specimenType = ProductType.findByName('Specimen/Product')
		def services = Product.findAllByOrganizationAndType(session.org, serviceType)
		def specimens = Product.findAllByOrganizationAndType(session.org, specimenType)
		render(view: 'edit', model:[obj: obj, serviceList: services, specimenList: specimens])
	}

	def create() {
		def serviceType = ProductType.findByName('Service')
		def specimenType = ProductType.findByName('Specimen/Product')
		def services = Product.findAllByOrganizationAndType(session.org, serviceType)
		def specimens = Product.findAllByOrganizationAndType(session.org, specimenType)

		render(view: 'create', model:[obj: new Project(params), serviceList: services, specimenList: specimens])
	}

  @Transactional
	def save(Project obj) {
		super.isValid(obj) //check to see if user can act on this obj

		if(!obj.validate()) {

			def serviceType = ProductType.findByName('Service')
			def specimenType = ProductType.findByName('Specimen/Product')
			def services = Product.findAllByOrganizationAndType(session.org, serviceType)
			def specimens = Product.findAllByOrganizationAndType(session.org, specimenType)			
      respond obj.errors, view:'create', model:[obj: obj, serviceList: services, specimenList: specimens]
			return
		} else {
			def prods = Product.getAll(params.list('includedproducts')) 

	   	//make sure there is a fee schedule attached to this project			
			projectService.createProjectFeeSchedule(obj)
			obj.save(flush:true)
     	prods.each {
     		println "Product: " + it
     		projectService.addProductToProject(obj,it)
     	}

			request.withFormat {
				form {
					flash.message = "Project '${obj.name}' was created successfully!"
			    redirect action: 'index'
				}
				'*' {respond obj, [status: CREATED]}
			}
		}
	}

  @Transactional
	def update(Project obj) {
 		super.isValid(obj) //check to see if user can act on this obj

		if(!obj.save()) {
      respond obj.errors, view:'edit', model:[obj: obj]
			return
		} else {

			//this is tricky, be careful... make sure you compare product ids to product ids.. note, Product and ProjectProducts!

			def p1 = Product.getAll(params.list('includedproducts'))
			def newprods = p1.collectEntries{[it.id, it]}

			def p2 = ProjectProduct.findAllByProject(obj) 
			def currentprods = p2.collectEntries{[it.product.id, it]} //since we have ProjectProduct, need it.product.id

     	//handle the ones to delete
			for ( e in currentprods ) {
    		if( ! newprods.containsKey(e.key)) {
    			projectService.removeProductFromProject(obj,e.value)
    		}	
			}

     //handle the ones to add
			for ( e in newprods ) {
    		if( ! currentprods.containsKey(e.key)) {
    			projectService.addProductToProject(obj,e.value)
    		}	
			}
     	obj.save()

			request.withFormat {
				form {
					flash.message = "Project '${obj.name}' was updated successfully!"
			    redirect action: 'index'
				}
				'*' {respond obj, [status: UDPATED]}
			}
		}
	}	

	@Transactional
	def updatebaseprice() {
		def pp = ProjectProduct.get(params.projectProduct)
		try {
			def prod = pp.product
			super.isValid(prod) //check to see if user can act on this obj
			def unitCost = prod.unitCost
			def basePrice = params.basePrice.toBigDecimal()

			pp.basePrice = basePrice
			
			pp.recoveryPercent = projectService.determineRecoveryPercentage(basePrice,unitCost)

		} catch (Exception e) {
			super.returnJsonException(e)
			return
		}
		if(!pp.save()) {
      render pp.errors as JSON
			return
		} else {
			proformaProjectService.updateBasePrice(pp)
			render pp as JSON
		}
	}


  @Transactional
	def delete(Project obj) {
		super.isValid(obj) //check to see if user can act on this obj
    //should check to make sure that doesn't exist in Proforma.
    def doit = proformaProjectService.canDeleteProject(obj);

    if (!doit) {
    		flash.error = "Project '${obj.name}' is included in your current Forecast and can not be deleted until it is removed from your Forecast."
    	} else {
		    obj.delete flush:true
		    flash.message = "Project '${obj.name}' was deleted!"
    	}
    redirect action: "index"
	}
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bemtDailyJob
import com.provia.bemt.domain.PropertiesLookUp
/**
 *
 * @author mareedus
 */
class PasswordExpiryJob {
 static triggers = {
         cron name: 'passwordExpiry', cronExpression: "0 40 14 * * ?" 
        
    } 
    def group = "MyGroup"  
    def userService
    
    public def execute(){
        def lookup = PropertiesLookUp.findByParamName('SEND_PASSWORD_EXPIRY_EMAILS')
        println("Job started.................")
        println("Lookup.paramValue: " + lookup.paramValue)
        if (lookup?.paramValue.equals("Yes")) {
            // println " ${(new Date()).toString()},  Job run!"
            println((new Date()).toString() + " started password expiry job")
            userService.dailyPasswordExpiry()
            println((new Date()).toString() + " ended password expiry job")
        } else {
            println((new Date()).toString() + " Daily password expiry job is NOT RUNNING.")
        }
       
  }	
}


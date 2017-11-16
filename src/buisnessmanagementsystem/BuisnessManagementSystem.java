/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buisnessmanagementsystem;

import itp.project.x.Interfaces.login;
import classes.workoutthestatements;

/**
 *
 * @author Rashan
 */
public class BuisnessManagementSystem {

    /**
     * @param args the command line arguments
     */
    
        workoutthestatements start = new workoutthestatements();
    
    BuisnessManagementSystem(){
         
          //start.initiate();
    }
     
    public static void main(String[] args) {
        // TODO code application logic here
         new login().setVisible(true);//works
    }
    
}

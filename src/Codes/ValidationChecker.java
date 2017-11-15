/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codes;

/**
 *
 * @author Sandun
 */
public class ValidationChecker {
    
    public static boolean isInteger(String text){
        return text.matches("(?<=\\s|^)\\d+(?=\\s|$)");
    
    }
    
    
     public static boolean isDecimal(String text){
        return text.matches("^(?:[1-9]\\d*|0)?(?:\\.\\d+)?$");
    
    }
     
      public static boolean isYear(String text){
        return text.matches( "([1-9][0-9]{0,3})");
    
    }
     
    
    
}

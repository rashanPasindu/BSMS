/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Rashan
 */
public class holdtheUsername {
    
     String user;
    
  public void tranferUSername(String us){
     
     user=us;
      
  }
          
   public String getUsername(){
     
     if (user !=null){
     return user;
     }
     else
     return "null";
   }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;



//Class picture that takes all the parameters of a picture and makes getters and setters.
public class Recp {

  
    private String recp;
    private String uname;
    private java.util.UUID UUID=null;
    
    public void Recp() {

    }
    
     
    public String getUsername(){
        return uname;
    }
   
    public void setUsername(String uname){
        this.uname = uname;
    }
    
    public String getRecp(){
        return recp;
    }
   
    public void setRecp(String recp){
        this.recp = recp;
    }
    
    public void setUUID(java.util.UUID UUID){
        this.UUID =UUID;
    }
    public String getSUUID(){
        return UUID.toString();
    }
    
    
}
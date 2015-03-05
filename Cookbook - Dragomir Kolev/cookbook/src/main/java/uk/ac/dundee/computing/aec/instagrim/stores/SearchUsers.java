/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;



//Class picture that takes all the parameters of a picture and makes getters and setters.
public class SearchUsers {

  
    private String name;
    private String uname;
    
    public void SearchUsers() {

    }
    
     public String getName(){
        return name;   
    }
    public String getUsername(){
        return uname;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setUsername(String uname){
        this.uname = uname;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasrehmanualemail;

/**
 *
 * @author internet
 */
//Model class for Email messages.
public class Message {

    private int id, isSent;

    private String email, natID, phoneNumber;

    public Message(String email, String natID, String num) {
        this.isSent = 0;
        this.email = email;
        this.phoneNumber = num;
        this.natID = natID;
  
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getId() {
        return id;
    }

    public int getIsSent() {
        return isSent;
    }

    public String getEmail() {
        return email;
    }

    public String getNatID() {
        return natID;
    }

    public void setPhoneNumber(String num) {
        this.phoneNumber = num;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setIsSent(int sent) {
        this.isSent = sent;
    }

}

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
public class ChatMessage {
  int id;
 
   int requestID;
 
   String customMessage;
 
   int region;
 
   int isSent;
   
   public ChatMessage(int request, String custom, int reg, int isSent){
       this.requestID=request;
       this.customMessage=custom;
       this.region=reg;
       this.isSent=isSent;
   }
   
   public ChatMessage(){
       
   }
 
   public int getId() {
      return this.id;
   }
 
   public void setId(int value) {
      this.id = value;
   }
 
   public int getRequestID() {
      return this.requestID;
   }
 
   public void setRequestID(int value) {
      this.requestID = value;
   }
 
   public String getCustomMessage() {
      return this.customMessage;
   }
 
   public void setCustomMessage(String value) {
      this.customMessage = value;
   }
 
   public int getRegion() {
      return this.region;
   }
 
   public void setRegion(int value) {
      this.region = value;
   }
 
   public int getIsSent() {
      return this.isSent;
   }
 
   public void setIsSent(int value) {
      this.isSent = value;
   }
   
   
}


public class Customer {
  private int CustomerID;

  private double serviceTime;	
	
  public Customer(int CustomerID, double serviceTime) {
		
    this.CustomerID = CustomerID;
    this.serviceTime = serviceTime;

    }


  public int getCustomerID() {

    return this.CustomerID;
	
    }

  public double getserviceTime() {
		
    return this.serviceTime;
        
    }	 
  
  public void setCustomerID(int i) {
	  this.CustomerID = i;
   } 
  
  public void setserviceTime(double serviceTime) {
    
    this.serviceTime = serviceTime;
    }

  public Counter gotoCounter(Shop shop) {
	  return shop.getavailableCounter();
    }
  
  public Counter gotoshortQueue(Shop shop){
	 return shop.getshortQueue();
    }
 

  @Override 
  public String toString() {
	 return "C" + CustomerID;
   } 
}
	

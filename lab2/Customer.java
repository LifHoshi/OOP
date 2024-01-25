class Customer {
  private int CustomerID;

  private double serviceTime;	
	
  public Customer(int CustomerID) {
		
    this.CustomerID = CustomerID;

    }

  public Customer(double serviceTime) {
	
    this.serviceTime = serviceTime;
    
    }

  public int getCustomerID() {

    return this.CustomerID;
	
    }

  public double getserviceTime() {
		
    return this.serviceTime;
        
    }	 
    
  public void setserviceTime(double serviceTime) {
    
    this.serviceTime = serviceTime;
    }

}
	

class ShopDepart extends Event {
	
  private Customer CustomerID;
       
        
  public ShopDepart(double time, Customer CustomerID) {
    super(time);

    this.CustomerID = CustomerID;

    }

       	
  @Override 
  public String toString() {
    String str = "";

    str = String.format(": C%d departed", this.CustomerID.getCustomerID());
         
    return super.toString() + str;
      }

  @Override 
    public Event[] simulate() { 
      return new Event[] {};
      }

    
}



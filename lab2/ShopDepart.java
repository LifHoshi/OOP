class ShopDepart extends Event {
	
  private Customer CustomerID;
       
  private Counter CounterID;
        
  private Counter available;
        
  private Customer serviceTime;

  private Queue q;
       	
  public ShopDepart(double time, Customer CustomerID,
    Customer serviceTime , Counter CounterID, Counter available ) {
      
      this(time , CustomerID);
		
      this.serviceTime = serviceTime;
		
      this.available = available;
		
      this.CounterID = CounterID;
     
      }

  public ShopDepart(double time, Customer CustomerID, Customer serviceTime,Counter available) {
             
      this(time , CustomerID); 
             
      this.serviceTime = serviceTime;
	     
      this.available = available;
      
      }
	  
  public ShopDepart(double time, Customer CustomerID){
		 
      super(time);
		 
      this.CustomerID = CustomerID;
 
      }

  @Override 
  public String toString() {
    String str = "";

    str = String.format(": Customer %d departed", this.CustomerID.getCustomerID());
         
    return super.toString() + str;
      }

  @Override 
    public Event[] simulate() {
	    if (q.isEmpty()) {
		    
      return new Event[] { new ShopBegin(this.getTime(), this.CustomerID, 
              this.serviceTime,CounterID, this.available)
       };
      } else { 


      return new Event[] {};
      }
}
}

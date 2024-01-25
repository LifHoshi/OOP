class ShopEnd extends Event {

  private Customer CustomerID;

  private Counter CounterID;

  private Counter available;

  private Customer serviceTime;
       	
  public ShopEnd(double time, Customer CustomerID,
    Customer serviceTime , Counter CounterID, Counter available ) {
		
    this(time , CustomerID);
		
    this.serviceTime = serviceTime;
		
    this.available = available;
		
    this.CounterID = CounterID;

    }

  public ShopEnd(double time, Customer CustomerID, Customer serviceTime,Counter available) {
             
    this(time , CustomerID); 

    this.serviceTime = serviceTime;
	     
    this.available = available;
    
    }
	  
  public ShopEnd(double time, Customer CustomerID){
		  
    super(time);

    this.CustomerID = CustomerID;
   
    }
              
@Override 
  public String toString() {
      String str = "";

      str = String.format(": Customer %d service done (by Counter %d)", 
          this.CustomerID.getCustomerID(), this.CounterID.getCounterID());

      return super.toString() + str;
    }

   
@Override 
  public Event[] simulate() {

      this.available.getavailable()[this.CounterID.getCounterID()] = true;

      return new Event[] { 
        new ShopDepart(this.getTime(), this.CustomerID),
      };
    }  



} 

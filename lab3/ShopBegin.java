class ShopBegin extends Event {

  private Customer customer;

  private Counter c;

  private Shop shop;
  
  
       	
    public ShopBegin(double time, Customer customer, Shop shop, Counter counter) {
	  this(time,customer);
	  this.shop = shop;
	  this.c = counter;
    }

    public ShopBegin(double time,Customer customer, Shop shop) {
	  this(time,customer);
	  this.shop =shop;
    }
    
    public ShopBegin(double time, Customer customer) {
    super(time);

    this.customer = customer;

    }



@Override
   public String toString() {

     String str = "";
           
     str = String.format(": C%d service begin (by S%d) ",this.customer.getCustomerID(), this.c.getCounterID());
	   
     return super.toString() + str;
	 
     }
	  
                   
@Override 
    public Event[] simulate() {
    
    this.c.setunavailable();

    double endTime = this.getTime() + this.customer.getserviceTime();

     return new Event[] { 
		   new ShopEnd(endTime, this.customer,
            this.shop, this.c)
      };
    }
}    

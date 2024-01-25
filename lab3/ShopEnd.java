class ShopEnd extends Event {

  private Customer customer;

  private Counter c;

  private Shop shop;
  
  public ShopEnd(double time, Customer customer) {
	 super(time);

	 this.customer = customer;

          }

  public ShopEnd(double time, Customer customer, Shop shop) {
	 this(time,customer);

	 this.shop = shop;
         }

  public ShopEnd(double time,Customer customer, Shop shop, Counter c) {
	 this(time,customer);
			                
	 this.shop =shop;
         }

       	
              
@Override 
  public String toString() {
      String str = "";

      str = String.format(": C%d service done (by S%d)", 
          this.customer.getCustomerID(), this.c.getCounterID());

      return super.toString() + str;
    }

   
@Override 
  public Event[] simulate() {
    if (this.c.isQempty()){
      if(this.c.notqueue()){
	if(this.shop.isQempty()){
	       c.setavailable();
	       return new Event[] {
		 new ShopDepart(this.getTime(),this.customer)
	       };
	       } else {
	         return new Event[] {
		 new ShopDepart(this.getTime(),this.customer),
		 new ShopBegin(this.getTime(),this.shop.deq(),this.shop,this.c)
	       };
	       }
         } else {
	      c.setavailable();
	      return new Event[]{
		new ShopDepart(this.getTime(),this.customer)
	        };
	 }
         } else {
      if(this.shop.isQempty()){
	      return new Event[] {
		     new ShopDepart(this.getTime(),this.customer),
	             new ShopBegin(this.getTime(), this.c.deq(),this.shop,this.c)
		    };
	 } else {
	      return new Event[] {
	            new ShopDepart(this.getTime(),this.customer),
		      new ShopBegin(this.getTime(),this.c.deq(),this.shop,this.c),
		      new ShopjoinCounterQueue(this.getTime(),this.shop.deq(),this.c)
		};
	   }
  }
  }




} 

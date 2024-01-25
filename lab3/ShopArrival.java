class ShopArrival extends Event {
 
  private Customer customer;
  
  private Shop shop;
       	
  //private Queue q

  public ShopArrival(double time, Customer customer, Shop shop) {
    this(time,customer);

    this.shop = shop;
    }

  public ShopArrival(double time, Customer customer) {
	  super(time);
	  this.customer = customer;
    }
       
@Override 
  public String toString() {
    String str = "";

    str = String.format(": C%d arrived", this.customer.getCustomerID());
    return super.toString() + str;

    }


@Override
  public Event[] simulate(){
    Counter counter = this.customer.gotoCounter(this.shop);

    if (counter == null) {
      counter = this.customer.gotoshortQueue(this.shop);
     if(counter == null) {
       if(this.shop.isQfull()){
          return new Event[]{
	  new ShopDepart(this.getTime(),this.customer)
	  };
       } else {
	  return new Event[]{
	  new ShopjoinQueue(this.getTime(),this.customer,this.shop)
          };
	  }
       } else {
	  return new Event[] {
	  new ShopjoinCounterQueue(this.getTime(),this.customer,counter)
	  };
       
      }
    } else {
	  return new Event[] {
	  new ShopBegin(this.getTime(), this.customer, this.shop , counter)
	 };
      }
   }



}

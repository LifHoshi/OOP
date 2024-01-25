class ShopjoinQueue extends Event {
	private Customer CustomerID;

	private Shop shop;


	public ShopjoinQueue(double time,Customer CustomerID, Shop shop) {
		super(time);

		this.shop = shop;

		this.CustomerID = CustomerID;
	}

       @Override 
       public String toString() {
	    String str = "";

	    str = String.format(": %s joined shop queue %s",
				               this.CustomerID, this.shop);

            return super.toString() + str + shop.toString();
	}
 



	@Override
        public Event[] simulate() {
	  this.shop.enq(this.CustomerID);
                 return new Event[] {};
        }
}

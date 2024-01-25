public class ShopjoinCounterQueue extends Event{
	private Customer CustomerID;
	private Counter c;

	public ShopjoinCounterQueue(double time, Customer CustomerID,Counter c){
		super(time);
		this.CustomerID = CustomerID;
		this.c = c;
	}

	@Override
	public String toString() {
	    String str = "";

	    str = String.format(": %s joined counter queue(at %s)",
				               this.CustomerID, this.c);

            return super.toString() + str;
	}


	@Override 
	public Event[] simulate() {
		this.c.enq(CustomerID);
		return new Event[] {};
	}
}
 


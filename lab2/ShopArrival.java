class ShopArrival extends Event {
 
  private Customer CustomerID;

  private Counter CounterID;

  private Counter available;

  private Customer serviceTime;

  private Queue q;
       	

  public ShopArrival(double time, Customer CustomerID,
    Customer serviceTime , Counter CounterID, Counter available ) {

    this(time , CustomerID);

    this.serviceTime = serviceTime;

    this.available = available;

    this.CounterID = CounterID;
    }

  public ShopArrival(double time, Customer CustomerID, Customer serviceTime,Counter available) {

    this(time , CustomerID); 

    this.serviceTime = serviceTime;

    this.available = available;
    }
	  
  public ShopArrival(double time, Customer CustomerID){

    super(time);

    this.CustomerID = CustomerID;
  }


       
@Override 
  public String toString() {
    String str = "";

    str = String.format(": Customer %d arrives", this.CustomerID.getCustomerID());
      return super.toString() + str;

    }


@Override
  public Event[] simulate(){

      // The current event is an arrival event.  
      // Find the first available counter.
    int counter = this.available.FindCounter();     
    if (counter == -1) {
        // If no such counter can be found, the customer
        // should depart.
      return new Event[] { 
        new ShopDepart(this.getTime(), this.CustomerID)
      };
      } else {
        // Else, the customer should go the the first 
        // available counter and get served.
	Counter CounterID = new Counter(counter);
        return new Event[] { 
          new ShopBegin(this.getTime(), this.CustomerID, 
              this.serviceTime,CounterID, this.available)
        };
      }
    } 
       
}

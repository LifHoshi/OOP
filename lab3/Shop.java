class Shop {
  private Array<Counter> cs;

  private Queue<Customer> q;


  public Shop(int totalcounters, int lengthqueue, int m ) {
	  this.cs = new Array<Counter>(totalcounters);
          for(int i = 0; i < totalcounters; ++i) {
		  this.cs.set(i, new Counter(i,m));
	  }

	  this.q = new Queue<Customer>(lengthqueue);
	  
  }


  public boolean isQfull() {
	 return q.isFull();
  }

  public boolean isQempty() {
	 return q.isEmpty();
  }

  public void enq(Customer c) {
	 this.q.enq(c);
  }

  public Customer deq() {
	 return this.q.deq();
  }

  public Array<Counter> getCounters() {
	  return cs;
  }

  public Counter getavailableCounter() {
	 Counter counter = null;

     for(int i = 0 ;i < this.cs.length(); ++i) {
       if(this.cs.get(i).isavailable()){
	       counter = this.cs.get(i);
	       break;
       }
     }
     
     return counter;
  }
  
  public Queue<Customer> getQueue() {
	  return q;
  }

  public Counter getshortQueue() {
	  Counter counter = cs.min();
  if( counter.isQfull()){
	  return null;
  }

   return counter;
  }

   @Override 
   public String toString() {
	 return q.toString() ;
    } 


}

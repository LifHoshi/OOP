public class Counter implements Comparable<Counter>{ 
	
  private int ID;
       
  private boolean available;

  private int maxSize;

  private Queue<Customer> q;

  public Counter(int ID, int m) {
    this.ID = ID;
    this.available = true;
    this.q = new Queue<Customer>(m);
    this.maxSize = m;

  } 


  public int getCounterID() {
   
    return this.ID;
  }   
  
  public boolean isavailable() {

    return this.available;
  }

  public void setCounterID(int i) {
    
    this.ID = i;
  }

  public void setavailable() {
   
    this.available = true;
  }
  
  public void setunavailable() {
    this.available = false;
  }

  public void enq(Customer ID) {
	 this.q.enq(ID);
  }

  public Customer deq() {
	  return q.deq();
  }

  public boolean notqueue(){
	  return maxSize ==0;
  }

  public boolean isQfull(){
	 return q.isFull();
  }

  public boolean isQempty(){
	 return q.isEmpty();
  } 




  @Override 
  public int compareTo(Counter counter){
   if(this.q.isEmpty()){
    if(counter.q.isEmpty()) {
     if(this.ID < counter.ID) {
	     return -1;
     } else {
	     return 1;
     } 
    } else {
	    return -1;
    }
   }

   
   if(counter.q.isEmpty()) {
	   return 1;
   }

   int QueueLength = this.q.length();
   int CounterLength = counter.q.length();

   if(QueueLength == CounterLength) {
    if(this.ID < counter.ID) {
	    return -1;
     } else {
	     return 1;
     }
   }
   return QueueLength  - CounterLength;
 }

 @Override
 public String toString() {
	 return "S" + ID + " " +q.toString();
 } 

}
		 

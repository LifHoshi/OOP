class Counter { 
	
  private int CounterID;
       
  private boolean[] available;

  public Counter(int CounterID) {
    this.CounterID = CounterID;

  } 

  public Counter(boolean[] available) {
    this.available = available;
  }


  public int getCounterID() {
   
    return this.CounterID = CounterID;
  } 

  public void setCounterID(int CounterID) {
    
    this.CounterID = CounterID;
  }

  public void setavailable(boolean[] available) {
   
    this.available = available;
  }

  public boolean[] getavailable() {
    
    return this.available = available;
  }

  public int FindCounter() {
		// The current event is an arrival event.  
      // Find the first available counter.
    int counter = -1;
   
    for (int i = 0; i < this.available.length; i += 1) {
   
    if (this.available[i]) {
      counter = i;
          break;
        }
      }   return counter;
      }
}
		 

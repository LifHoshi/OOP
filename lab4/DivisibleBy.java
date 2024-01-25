public class DivisibleBy implements BooleanCondition<Integer> {
  private final int n;

  DivisibleBy(int n) {
    this.n = n;
  }


  @Override 
  public boolean test(Integer m) {
    if (m == null) {
      return false;
      
    } 
    
    return m % n == 0;
    
  }
}


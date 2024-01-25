/**
 * A transformer with a parameter k that takes in an object x 
 * and outputs the last k digits of the hash value of x.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Put Your Name (Lab Group)
 */
public class LastDigitsOfHashCode implements Transformer<Object, Integer> {
  private final Integer k;

  LastDigitsOfHashCode(Integer k) {
    this.k = k;
  }
  
  @Override
  public Integer transform(Object obj) {
    return Math.abs((int) (obj.hashCode() % (Math.pow(10, k))));
  }

}

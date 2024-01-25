/**
 * CS2030S Lab 5
 * AY21/22 Semester 2
 *
 * @author Wang Lifu (16F)
 *
 */
package cs2030s.fp;

public interface Transformer<T, U> {
  public abstract U transform(T t);
}

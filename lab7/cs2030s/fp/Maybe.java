/** 
 * CS2030S Lab 5.
 * AY21/22 Semester 2
 *
 * @author Wang Lifu (16F)
 */

package cs2030s.fp;

import java.lang.NullPointerException;
import java.util.NoSuchElementException;

public abstract class Maybe<T> {

  public static <T> Maybe<T> none() {
    @SuppressWarnings("unchecked") 
    Maybe<T> non = (Maybe<T>) None.NONE;
    return non;
  }

  public static <T> Maybe<T> of(T t) {
    if (t == null) {
      return none();
    } else {

      return some(t);
    }
  }

  public static <T> Maybe<T> some(T t) {
    return new Some<>(t);
  }

  protected abstract T get() throws NoSuchElementException;

  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> trans) 
      throws NullPointerException;

  public abstract Maybe<T> filter(BooleanCondition<? super T> cond);

  public abstract <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> trans);

  public abstract T orElse(T s);

  public abstract T orElseGet(Producer<? extends T> producer);

  static class None extends Maybe<Object> {
  
    private static final Maybe<Object> NONE = new None();

    @Override
    public String toString() {
      return "[]";
    }

    @Override 
    protected Object get() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == NONE) {
        return true;
      }

      return false;
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super Object, ? extends U> trans) {
      return super.<U>none();
    } 

    @Override
    public None filter(BooleanCondition<? super Object> cond) {
      return this;
    }  

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super Object, ? extends Maybe<? extends U>> trans) {
      return super.<U>none();
    }    

    @Override 
    public Object orElseGet(Producer<? extends Object> producer) {
      return producer.produce();
    }

    @Override
    public Object orElse(Object t) {
      return t;
    }

  }

  static final class Some<T> extends Maybe<T> {
    private final T data;

    private Some(T t) {
      this.data = t;
    }  

    protected T get() {
      return this.data;
    }

    @Override 
    public String toString() {
      return "[" + this.data + "]";
    }

    @Override
    public boolean equals(Object t) {
      if (this == t) {
        return true;
      }
    
      if (t instanceof Some<?>) {
        Some<?> item = (Some<?>) t;

        if (this.data == item.data) {
          return true;
        }

        if (this.data == null || item.data == null) {
          return false;
        }

        return this.data.equals(item.data);
      }

      return false;
    }

    @Override 
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> trans) throws NullPointerException {
      try {
        Some<U> some = new Some<>(trans.transform(this.data));
        return some;
      } catch (Exception e) {
        throw new NullPointerException();
      }
    }

    @Override 
    public Maybe<T> filter(BooleanCondition<? super T> cond) {
      if (this.data != null && !cond.test(this.data)) {
        return super.<T>none();
      }
      return this;
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> trans) {
      @SuppressWarnings("unchecked")
      Maybe<U> fmp = (Maybe<U>) trans.transform(this.data);
      return fmp;
    }

    @Override
    public T orElse(T t) {
      return this.data;
    }

    @Override
    public T orElseGet(Producer<? extends T> producer) {
      return this.data;
    }
  }
}

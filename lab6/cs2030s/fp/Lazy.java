package cs2030s.fp;

/**
 * A calss contains specific values and functions.
 * CS2030S Lab 5
 * AY20/21 Semester 2
 *
 * @param <T> The type of the value produced.
 */

public class Lazy<T> {
  private Producer<? extends T> producer;
  private Maybe<T> value;

  /**
   * A constructor to store value when initialized.
   *
   * @param t the parameters which represent some value.
   */
  private Lazy(Producer<? extends T> t) {
    this.value = Maybe.none();
    this.producer = t;
  }
  /**
   * A method with given value.
   *
   * @param v value.
   * @param <U> the type.
   * @return the object.
   */

  public static <U> Lazy<U> of(U v) {
    Lazy<U> lazy = new Lazy<U>(() -> v);
    U val = lazy.get();

    return lazy;
  }

  /** 
   * set up a method which let producer be the parameter.
   *
   * @param t  producer.
   * @param <U> the value which will be produced by producer.
   * @return a new object.
   */

  public static <U> Lazy<U> of(Producer<? extends U> t) {
    return new Lazy<U>(t);
  }

  /**
   * set up a method which return the value if it is availiable,otherwise not.
   *
   * @return a instance which is encapsulated by instance.
   */

  public T get() {
    if (this.value instanceof Maybe.None) {
      this.value = Maybe.some(this.value.orElseGet(producer));

      return this.value.orElseGet(producer);
    } else {
      return this.value.orElseGet(producer);
    }
  }

  /**
   * set up a map method.
   *
   * @param transformer which contains a function has single argument.
   * @param <U> the return type
   * @return the lazy object
   */

  public <U> Lazy<U> map(Transformer<? super T, ? extends U> transformer) {
    return new Lazy<U>(() -> transformer.transform(this.get()));
  }

  /** 
   * set up a flatmap method.
   *
   * @param  transformer which contains a function has single argument.
   * @param <U> the return type.
   * @return a new lazy object.
   */

  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> transformer) {
    return new Lazy<U>(() -> transformer.transform(this.get()).get());
  }

  /**
   * set up a filter method.
   *
   * @param condition boolean condition.
   * @return a lazy object.
   */

  public Lazy<Boolean> filter(BooleanCondition<? super T> condition) {
    return new Lazy<Boolean>(() -> condition.test(this.get()));
  }
  
  /** set up a combine method.
   *
   *  @param lazy an object
   *  @param combiner which takes two arguments
   *  @param <U> the type of the second argument of the combiner
   *  @param <R> the type of first argument of the combiner
   *  @return  a new lazy object
   */

  public <U, R> Lazy<R> combine(Lazy<? extends U> lazy, 
      Combiner<? super T, ? super U, ? extends R> combiner) {
    return new Lazy<R>(() -> combiner.combine(this.get(), lazy.get()));
  }

  /**
   * return the string of some instances.
   */

  @Override
  public String toString() {
    if (this.value instanceof Maybe.None) {
      return "?";
    }
    
    if (this.get() == null) {
      return "null";
    }

    return this.get().toString();
  }

  /**
   * compare the value.
   *
   * @param o the object.
   * @return a boolean.
   */

  @Override
  public boolean equals(Object o) {
    if (o instanceof Lazy) {
      @SuppressWarnings("unchecked")
      Lazy<T> obj = (Lazy<T>) o;

      if (obj.get() == null && this.get() == null) {
        return true;
      }

      if (obj.get() == null || this.get() == null) {
        return false;
      }
      return this.get().equals(obj.get());
    }
    if (this == o) {
      return true;
    }

    return false;
  }








}


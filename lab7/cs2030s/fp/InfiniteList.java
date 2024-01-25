package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class InfiniteList<T> {

  /**
   * the private fields.
   */

  private final Lazy<Maybe<T>> head;
  private final Lazy<InfiniteList<T>> tail;
  private static InfiniteList<?> sen = new SenTinel();

  /** 
   * a private static class SenTinel extends InfiniteList.
   */

  private static class SenTinel extends InfiniteList<Object> {

    SenTinel() {
      super();
    }

    @Override
    public Object head() throws NoSuchElementException {
      throw new java.util.NoSuchElementException();

    }

    @Override
    public InfiniteList<Object> tail() {
      return this;
    }

    @Override
    public <R> InfiniteList<R> map(Transformer<? super Object, ? extends R> maper) {
      return InfiniteList.sentinel();
    }

    @Override
    public InfiniteList<Object> filter(BooleanCondition<? super Object> file) {
      return sentinel();
    }

    @Override
    public boolean isSentinel() {
      return true;
    }

    @Override
    public List<Object> toList() {
      return List.of();
    }

    @Override
    public InfiniteList<Object> limit(long l) {
      return sentinel();
    }

    @Override
    public <U> U reduce(U identity, Combiner<U, ? super Object, U> accumulator) {
      return identity;
    }

    @Override
    public long count() {
      return 0;
    }

    @Override
    public String toString() {
      return "-";
    }


  }

  /** 
   * the private constructor with no parameters to initialize
   * the InfiniteList.
   */

  private InfiniteList() { 
    this.head = null; 
    this.tail = null;
  }

  /**
   * the static method which create an InfiniteList.
   *
   * @param <T> the type.
   * @param producer function which generate head of the list.
   *
   * @return a infinitelist.
   */

  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    return new InfiniteList<>(Lazy.of(() -> 
          Maybe.some(producer.produce())), Lazy.of(() -> 
          InfiniteList.generate(producer)));
  }

  /** 
   * a static iterate method which create an InfiniteList.
   *
   * @param  <T> the type.
   * @param seed first element of the list.
   * @param next which represent as a Transformer.
   *
   * @return  a infinitelist.
   */

  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    return new InfiniteList<>(Lazy.of(Maybe.some(seed)),
        Lazy.of(() -> InfiniteList.iterate(next.transform(seed),
            next)));
  }

  /**
   * private constructor to initialize the InfiniteList.
   *
   * @param head type T.
   * @param tail producer which type is InfiniteList.
   */

  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head = Lazy.of(Maybe.some(head));
    this.tail = Lazy.of(() -> tail.produce());
  }

  /**
   * private constructor to initialize the InfiniteList.
   *
   * @param head Lazy with the type is Maybe.
   * @param tail Lazy with type InfiniteList.
   */

  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  /**
   * head method which produces head of the infinite list.
   *
   * @return T type of element.
   */

  public T head() {
    return this.head.get().orElseGet(() -> this.tail.get().head()
        );
  }

  /**
   * tail method which produces tail of intinite list.
   *
   * @return T type of Infinitelist.
   */

  public InfiniteList<T> tail() {
    return this.head.get().map((x) -> this.tail.get())
      .orElseGet(() -> this.tail.get().tail());
  }

  /**
   * The map method (lazily) applies the given transformation to each element
   * in the list and returns the resulting InfiniteList.
   *
   * @param mapper represents the Transformer.
   * @param <R> the return type.
   * 
   * @return A infinite list
   */

  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    return new InfiniteList<>(this.head.map(x -> x.map(mapper)),
        this.tail.map(y -> y.map(mapper)));
  }

  /**
   * filter method to filter out elements in the list that fail a given BooleanCondition.
   *
   * @param predicate takes in BooleanCondition to test elements in list
   * 
   * @return an infinitelist that pass boolean condition
   */

  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    return new InfiniteList<>(this.head.map(x ->
          x.filter(predicate)), this.tail.map(y ->
          y.filter(predicate)));
  }

  /**
   * Sentinel to represent a list that contains nothing and is used to mark the end of the list.
   *
   * @param <T> the type of elements. 
   *
   * @return instance of SenTinel.
   */

  public static <T> InfiniteList<T> sentinel() {
    @SuppressWarnings("unchecked")
    InfiniteList<T> temp = (InfiniteList<T>) sen;
    return temp;
  }

  /**
   * limit method that takes in a value n and truncate the InfiniteList 
   * to a finite list with at most n elements.
   *
   * @param n long.
   * 
   * @return a finite list.
   */

  public InfiniteList<T> limit(long n) {
    if (n <= 0) {
      return sentinel();
    }

    return new InfiniteList<>(this.head(),
        () -> this.tail().limit(n - 1));
  } 

  /**
   * The method takes in a BooleanCondition, and truncates the list as soon as 
   * it finds an element that evaluates the condition to false.
   *
   * @param predicate takes in BooleanCondition.
   *
   * @return an Infinitelist
   */

  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    Lazy<Maybe<T>> con = Lazy.of(() ->
        Maybe.some(this.head()).filter(predicate));

    return new InfiniteList<>(con,
        Lazy.of(() -> con.get().map((x) -> 
            this.tail().takeWhile(predicate)).orElseGet(() ->
            sentinel())));

  }

  /**
   *  isSentinel method that returns true if the list is an instance of Sentinel 
   *  and returns false otherwise.
   *
   *  @return false;
   */

  public boolean isSentinel() {
    return false;
  }

  /**
   * a method which takes in type U and combiner as parameters
   * and return type U.
   *
   * @param identity type U.
   * @param <U> output of the combining operation.
   * @param accumulator combiner
   *
   * @return U.
   */

  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    U u = this.head.get().map(x ->
        accumulator.combine(identity, x)).orElse(identity);

    return this.tail.get().reduce(u, accumulator);

  }

  /**
   * count the number in a list.
   *
   * @return long.
   */

  public long count() {
    return this.reduce(0L, (x, y) -> x + 1);
  }

  /**
   * toList method that collects the elements in the InfiniteList into a java.util.List.
   *
   * @return a List.
   */

  public List<T> toList() {
    InfiniteList<T> i = this;
    List<T> list = new ArrayList<T>();

    while (!(i.isSentinel())) {
      i.head.get().map(x -> list.add(x));
      i = i.tail.get();
    }

    return list;
  }

  /**
   * represent the elements in the list with type String.
   *
   * @return a string of the element.
   */

  public String toString() {
    return "[" + this.head.toString() + " " + this.tail.toString() + "]";
  }
}

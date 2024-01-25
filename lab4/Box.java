public class Box<T> {
  private final T con;
  public static final Box<?> emptybox = new Box<>(null);

  public Box(T con) {
    this.con = con;
  }

  public static <T> Box<T> of(T con) {
    if (con == null) {
      return null;
    }     
    return new Box<>(con);

  } 

  public T get() {
    return con;
  }

  public static <T> Box<T> ofNullable(T con) {
    if (con == null) {
      return empty();
    }
    return Box.of(con);
  }

  @SuppressWarnings("unchecked")
  public static <T> Box<T> empty() {
    return (Box<T>) emptybox;
  }

  public boolean isPresent() {
    return con != null;
  }

  public Box<T> filter(BooleanCondition<? super T> boocon) {
    if (boocon.test(con)) {
      return this;
    }
    return empty();
  }

  public <U> Box<U> map(Transformer<? super T, ? extends U> transformer) {
    if (!isPresent()) {
      return empty();
    }

    return new Box<U>(transformer.transform(con));
  }

  @Override
  @SuppressWarnings("unchecked") 
  public boolean equals(Object obj) {
    if (obj instanceof Box<?>) {
      Box<T> box = (Box<T>) obj;
      if (con == null) {
        return box.get() == null;
      } 
      return con.equals(box.get());
    }
    return false;
  } 

  @Override
  public String toString() {
    if (con == null) {
      return "[]";
    } else {
      return "[" + con + "]";
    } 
  }

}

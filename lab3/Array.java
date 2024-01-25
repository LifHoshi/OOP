/**
 * The Array<T> for CS2030S 
 *
 * @author Lifu
 * @version CS2030S AY21/22 Semester 2
 */
class Array<T extends Comparable<T>> {
  private T[] array;

  Array(int size) {
    @SuppressWarnings({"unchecked", "rawtypes"})
    T[] m = (T[]) new Comparable[size];
    this.array = m;
   }

  public void set(int index, T item) {
    this.array[index] = item;
  }

  public T get(int index) {
    return array[index];
  }

  public T min() {
    T n = array[0];

    for (int i = 1; i < array.length ; ++i) {
	    if(n.compareTo(array[i]) >0) {
		   n = array[i];
	    }
    }

    return n;
  }

  public int length() {
    return array.length;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}

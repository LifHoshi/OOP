public class LongerThan implements BooleanCondition<String> {
  private final int pp;

  LongerThan(int pp) {
    this.pp = pp;
  }

  @Override
  public boolean test(String s) {
    if (s == null) {
      return false;
    }
    return s.length() > pp;
  }
}



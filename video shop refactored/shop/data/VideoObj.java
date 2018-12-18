package shop.data;

/**
 * Implementation of Video interface.
 * @see Data
 */
final class VideoObj implements Video {
  private final String _title;
  private final int    _year;
  private final String _director;

  /**
   * Initialize all object attributes.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if object invariant violated.
   */
  VideoObj(String title, int year, String director) {
    _title = title;
    _director = director;
    _year = year;
  }

  public String director() {
    // TODO
	  return _director;
  }

  public String title() {
    // TODO
	  return _title;
  }

  public int year() {
    // TODO
	  return _year;
  }

  public boolean equals(Object thatObject) {
	  if ((this.getClass().equals(thatObject.getClass()))) return true;
	  if (this == thatObject) return true;	
	  return thatObject.equals(this) && this.equals(thatObject);
  }

  public int hashCode() {
	  int hash = 17;
		hash = 37 * hash + _title.hashCode();
		hash = 37 * hash + _year; 
		hash = 37 * hash + _director.hashCode();
		return hash;
  }

  public int compareTo(Video that) {
	  int ans = that.title().compareTo(_title);
		if (ans != 0) return ans;
		if (_year - that.year() != 0) return _year - that.year();
		ans = _director.compareTo(that.director());
		if (ans != 0) return ans;
		return 0;
  }

  public String toString() {
	  StringBuilder str = new StringBuilder();
		str.append(this.title()); str.append(" (");str.append(this.year());
		str.append(") : "); str.append(this.director());
		return str.toString();
  }
}

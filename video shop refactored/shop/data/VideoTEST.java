package shop.data;

import junit.framework.Assert;
import junit.framework.TestCase;

public class VideoTEST extends TestCase {
  public VideoTEST(String name) {
    super(name);
  }
  
  @SuppressWarnings("deprecation")
  public void testConstructorAndAttributes() {
  	String title1 = "XX";
      String director1 = "XY";
      String title2 = " XX ";
      String director2 = " XY ";
      int year = 2002;

      Video v1 = Data.newVideo(title1, year, director1);
      Assert.assertSame(title1, v1.title());
      Assert.assertEquals(year, v1.year());
      Assert.assertSame(director1, v1.director());

      Video v2 = Data.newVideo(title2, year, director2);
      Assert.assertEquals(title1, v2.title());
      Assert.assertEquals(director1, v2.director());
    }

    public void testConstructorExceptionYear() {
  	  try {
  	      Data.newVideo("X", 1800, "Y");
  	      Assert.fail();
  	    } catch (IllegalArgumentException e) { }
  	    try {
  	      Data.newVideo("X", 5000, "Y");
  	      Assert.fail();
  	    } catch (IllegalArgumentException e) { }
  	    try {
  	      Data.newVideo("X", 1801, "Y");
  	      Data.newVideo("X", 4999, "Y");
  	    } catch (IllegalArgumentException e) {
  	      Assert.fail();
  	    }
    }

    public void testConstructorExceptionTitle() {
  	  try {
  	      Data.newVideo(null, 2002, "Y");
  	      Assert.fail();
  	    } catch (IllegalArgumentException e) { }
  	    try {
  	      Data.newVideo("", 2002, "Y");
  	      Assert.fail();
  	    } catch (IllegalArgumentException e) { }
  	    try {
  	      Data.newVideo(" ", 2002, "Y");
  	      Assert.fail();
  	    } catch (IllegalArgumentException e) { }
    }

    public void testConstructorExceptionDirector() {
  	  try {
  	      Data.newVideo("X", 2002, null);
  	      Assert.fail();
  	    } catch (IllegalArgumentException e) { }
  	  try {
  	      Data.newVideo("X", 2002, "");
  	      Assert.fail();
  	    } catch (IllegalArgumentException e) { }
  	  try {
  	      Data.newVideo("X", 2002, " ");
  	      Assert.fail();
  	    } catch (IllegalArgumentException e) { }
      // TODO
    }

    @SuppressWarnings("deprecation")
  public void testHashCode() {
      Assert.assertEquals
        (-875826552,
         new VideoObj("None", 2009, "Zebra").hashCode());
      Assert.assertEquals
        (-1391078111,
         new VideoObj("Blah", 1954, "Cante").hashCode());
    }

    @SuppressWarnings("deprecation")
  public void testEquals() { 
  	VideoObj V1 = new VideoObj("Blah", 1954, "Cante");
  	VideoObj V2 = new VideoObj("Blah", 1954, "Cante");
  	Assert.assertTrue((V1.equals(V2)));
    }

    @SuppressWarnings("deprecation")
  public void testCompareTo() { 
  	VideoObj V1 = new VideoObj("None", 2009, "Zebra");VideoObj V2 = new VideoObj("Blah", 1954, "Cante");
  	int ans = V1.title().compareTo(V2.title());
  	Assert.assertTrue(ans != V1.compareTo(V2));
    }

    @SuppressWarnings("deprecation")
  public void testToString() {
  	VideoObj VO = new VideoObj("None", 2009, "Zebra");
  	Object S1 = new String();
  	S1 = VO.toString();
  	Object S2 = new String();
  	S2 = "None (2009) : Zebra";
  	Assert.assertTrue(S1.equals(S2));
  	S2 = "Blah (1954) : Cante";
  	Assert.assertTrue(!(S1.equals(S2)));
    }
  // TODO: complete the tests
}

package shop.data;

import junit.framework.Assert;
import junit.framework.TestCase;
import shop.command.UndoableCommand;
import shop.command.CommandHistory;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

// TODO: complete the tests
public class InventoryTEST extends TestCase {
  public InventoryTEST(String name) {
    super(name);
  }
  
  
  InventorySet s = new InventorySet();
  final VideoObj v1 = new VideoObj( "A", 2000, "B" );
  final VideoObj v1copy = new VideoObj( "A", 2000, "B" );
  final VideoObj v2 = new VideoObj( "B", 2000, "B" );
  
  public void testSize() {
	  assertEquals( 0, s.size() );
      s.addNumOwned(v1,  1); assertEquals( 1, s.size() );
      s.addNumOwned(v1,  2); assertEquals( 1, s.size() );
      s.addNumOwned(v2,  1); assertEquals( 2, s.size() );
      s.addNumOwned(v2, -1); assertEquals( 1, s.size() );
      s.addNumOwned(v1, -3); assertEquals( 0, s.size() );
      try { s.addNumOwned(v1, -3); } catch ( IllegalArgumentException e ) {}
      	assertEquals( 0, s.size() );
  }

  public void testAddNumOwned() {
	  Assert.assertEquals( 0, s.size() );
      s.addNumOwned(v1, 1);     Assert.assertEquals( v1, s.get(v1).video() );
      							Assert.assertEquals( 1, s.get(v1).numOwned() );
      s.addNumOwned(v1, 2);     Assert.assertEquals( 3, s.get(v1).numOwned() );
      s.addNumOwned(v1, -1);    Assert.assertEquals( 2, s.get(v1).numOwned() );
      s.addNumOwned(v2, 1);     Assert.assertEquals( 2, s.get(v1).numOwned() );
      s.addNumOwned(v1copy, 1); Assert.assertEquals( 3, s.get(v1).numOwned() );
                				Assert.assertEquals( 2, s.size() );
      s.addNumOwned(v1, -3);    
      							Assert.assertEquals( 1, s.size() );
      try { s.addNumOwned(null, 1);   Assert.fail(); } catch ( IllegalArgumentException e ) {}
  }

  public void testCheckOutCheckIn() {
	  try { s.checkOut(null);     Assert.fail(); } catch ( IllegalArgumentException e ) {}
	  try { s.checkIn(null);      Assert.fail(); } catch ( IllegalArgumentException e ) {}
	        s.addNumOwned(v1, 2); Assert.assertTrue( s.get(v1).numOut() == 0 && s.get(v1).numRentals() == 0 );
	        s.checkOut(v1);       Assert.assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 1 );
	  try { s.addNumOwned(v1,-3); Assert.fail(); } catch ( IllegalArgumentException e ) {}
	  try { s.addNumOwned(v1,-2); Assert.fail(); } catch ( IllegalArgumentException e ) {}
	        s.addNumOwned(v1,-1); Assert.assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 1 );
	        s.addNumOwned(v1, 1); Assert.assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 1 );
	        s.checkOut(v1);       Assert.assertTrue( s.get(v1).numOut() == 2 && s.get(v1).numRentals() == 2 );

  }

  public void testClear() {
	  s.addNumOwned(v1, 2); Assert.assertEquals( 1, s.size() );
      s.addNumOwned(v2, 2); Assert.assertEquals( 2, s.size() );
      s.clear();            Assert.assertEquals( 0, s.size() );
try { s.checkOut(v2);       Assert.fail(); } catch ( IllegalArgumentException e ) {}
  }

  public void testGet() {
	  s.addNumOwned(v1, 1);
	    Record r1 = s.get(v1);
	    Record r2 = s.get(v1);
	    Assert.assertTrue( r1 == r2 );
  }

  public void testIterator1() {
	  Set<Video> NeAn = new HashSet<Video>(); InventorySet inventory = new InventorySet(); 
	  Video v1 = new VideoObj("Smith", 2001, "Smith"); Video v2 = new VideoObj("Bench", 1997, "Bench"); 
	  inventory.addNumOwned(v1,10); inventory.addNumOwned(v2,20); NeAn.add(v1); NeAn.add(v2); 
	  Iterator<Record> Iter = inventory.iterator();
	  
	  while(Iter.hasNext()) { Record Rec = Iter.next(); 
	  Assert.assertTrue(NeAn.contains(Rec.video())); 
	  }
  }
  public void testIterator2() {
	  List<Video> NeAn = new ArrayList<Video>(); 
	  InventorySet inventory = new InventorySet(); 
	  Video v1 = new VideoObj("Smith", 2001, "Smith"); Video v2 = new VideoObj("Bench", 1997, "Bench"); 
	  NeAn.add(v2); NeAn.add(v1); inventory.addNumOwned(v1,10); inventory.addNumOwned(v2,20); 
	  Comparator<Record> Compar = new Comparator<Record>() 
	 { public int compare(Record r1, Record r2) { return r1.video().year() - r2.video().year(); } }; 
	  Iterator<Record> Iter = inventory.iterator(Compar); 
	  Iterator IterA = NeAn.iterator(); 
	  while (Iter.hasNext()) { Assert.assertSame(IterA.next(), Iter.next().video()); } 
  }
}

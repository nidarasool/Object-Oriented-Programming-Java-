package shop.data;

import java.util.HashMap;

import shop.command.RerunnableCommand;
import shop.command.UndoableCommand;

/**
 * A static class for accessing data objects.
 */
public class Data {
  private Data() {}
  private static HashMap<Integer,Video> hashmap = new HashMap<Integer,Video>();

	private static int hasher (Object keya, Object keyb, Object keyc) {
		return keya.hashCode () + 17 * keyb.hashCode () + 37 * keyc.hashCode ();
	}
  /**
   * Returns a new Inventory.
   */
  static public final Inventory newInventory() {
    return new InventorySet();
  }

  /**
   * Factory method for Video objects.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if Video invariant violated.
   */
  
  //Assignment 7:
  /**
	 * newVideo now both creates and manages flyweight objects. Makes sure the flyweights
	 * are shared properly.  If a flyweight is requested by a client the
	 * Flyweight Factory object gives an existing instance or if none exists then it
	 * creates one.
	 */
  
  static public Video newVideo(String title, int year, String director) {
	  if ((title == null) || (director == null)|| (year <= 1800)|| (year >= 5000)) {
			throw new IllegalArgumentException("Year is too much or too little");}
	  if(director==null||director.isEmpty()||(director.trim().isEmpty())){
		  throw new IllegalArgumentException("Title is null or empty"); }
	  String tit=title.replaceAll("\\s", "");
	  String dir=director.replaceAll("\\s", "");
	  Integer key = hasher(tit, year, dir);
		Video v = hashmap.get(key);
		if ((v == null)|| !(v.title().equals(tit))|| (v.year() != year)|| !(v.title().equals(title))) {
			v = new VideoObj(tit, year, dir);
			hashmap.put(key, v);
		}
		return v;
     }

  /**
   * Returns a command to add or remove copies of a video from the inventory.
   * <p>The returned command has the following behavior:</p>
   * <ul>
   * <li>If a video record is not already present (and change is
   * positive), a record is created.</li>
   * <li>If a record is already present, <code>numOwned</code> is
   * modified using <code>change</code>.</li>
   * <li>If <code>change</code> brings the number of copies to be less
   * than one, the record is removed from the inventory.</li>
   * </ul>
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   * @throws IllegalArgumentException if <code>inventory<code> not created by a call to <code>newInventory</code>.
   */
  static public UndoableCommand newAddCmd(Inventory inventory, Video video, int change) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return new CmdAdd((InventorySet) inventory, video, change);
  }

  /**
   * Returns a command to check out a video.
   * @param video the video to be checked out.
   */
  static public UndoableCommand newOutCmd(Inventory inventory, Video video) {
	  if (!(inventory instanceof InventorySet))
	      throw new IllegalArgumentException();
	    return new CmdOut((InventorySet) inventory, video);
  }
  
  /**
   * Returns a command to check in a video.
   * @param video the video to be checked in.
   */
  static public UndoableCommand newInCmd(Inventory inventory, Video video) {
	  if (!(inventory instanceof InventorySet))
	      throw new IllegalArgumentException();
	    return new CmdIn((InventorySet)inventory, video);
  }
  
  /**
   * Returns a command to remove all records from the inventory.
   */
  static public UndoableCommand newClearCmd(Inventory inventory) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return new CmdClear((InventorySet) inventory);
  }

  /**
   * Returns a command to undo that will undo the last successful UndoableCommand. 
   */
  static public RerunnableCommand newUndoCmd(Inventory inventory) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return ((InventorySet)inventory).getHistory().getUndo();
  }

  /**
   * Returns a command to redo that last successfully undone command. 
   */
  static public RerunnableCommand newRedoCmd(Inventory inventory) {
	  if (!(inventory instanceof InventorySet))
	      throw new IllegalArgumentException();
	    return ((InventorySet)inventory).getHistory().getRedo();
	    //TODO
  }
}  

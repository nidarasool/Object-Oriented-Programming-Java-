package shop.ui;

public class UIFactory {
	
	
  private UIFactory() {}
   private static UI _UI= null;

   public static UI makeUI (double val) {
	  if(val <= 0.5) {
		  _UI = new TextUI();
	  }
	  else {
		  _UI = new PopupUI();
	  }
    return _UI;
  }
}
   
  
 
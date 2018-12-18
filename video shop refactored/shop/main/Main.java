package shop.main;



import shop.ui.UI;
import shop.ui.UIFactory;
import shop.data.Data;

public class Main {
  private Main() {}
  public static void main(String[] args) {
    UI ui;
  
    ui = UIFactory.makeUI(Math.random());
  
    Control control = new Control(Data.newInventory(), ui);
    control.run();
  }
}

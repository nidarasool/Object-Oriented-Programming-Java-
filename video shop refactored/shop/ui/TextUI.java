package shop.ui;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;





final class TextUI implements UI {
  final BufferedReader _in;
  final PrintStream _out;

  public TextUI() {
    _in = new BufferedReader(new InputStreamReader(System.in));
    _out = System.out;
  }

  public void displayMessage(String message) {
    _out.println(message);
  }

  public void displayError(String message) {
    _out.println(message);
  }

  private String getResponse() {
    String result;
    try {
      result = _in.readLine();
    } catch (IOException e) {
      throw new UIError(); // re-throw UIError
    }
    if (result == null) {
      throw new UIError(); // input closed
    }
    return result;
  }
  @Override
  public void processMenu(UIMenu[] menu) {
	  
	  
    _out.println(menu[0].getHeading());
    _out.println();
    _out.println("Enter choice by number:");

    for (int i = 1; i < menu.length; i++) {
      _out.println("  " + i + ". " + menu[i].getPrompt());
    }

    String response = getResponse();
    int selection;
    try {
      selection = Integer.parseInt(response, 10);
      if ((selection < 0) || (selection >= menu.length))
        selection = 0;
    } catch (NumberFormatException e) {
      selection = 0;
    }

    menu[selection].runAct().run();
  }
  @Override
  public String[] processForm(UIForm[] form) {
    // TODO
	  
	  String [] str = new String[form.length];
	  _out.println(form[0].getHeading());
	  _out.println();
	  for(int i = 0; i < form.length;i++) {
		  _out.println(form[i].getPrompt() +": ");
		  str[i] = getResponse();
		 
		  
		  if(form[i]== null || !(form[i].runTest().run(str[i]))) {
			  _out.println("Wrong Input");
			  _out.println("Try Again!");
			  i--;
		  }
		  
	  }
	  
    return str;
  }

}

package shop.ui;

import javax.swing.JOptionPane;

final class PopupUI implements UI {	
	public void displayMessage(String message) {
	    JOptionPane.showMessageDialog(null,message);
	  }
	public void displayError(String message) {
	    JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);
	  }
	public String[] processForm(UIForm[] form) {
		  String [] str = new String[form.length];
		  for(int i = 0; i < form.length;i++) {
			  
			  str[i] = JOptionPane.showInputDialog(form[i].getHeading() + "\n" + form[i].getPrompt());
			  if(form[i]== null || !(form[i].runTest().run(str[i]))) {
				  JOptionPane.showMessageDialog(null,"Wrong Input \n Try Again");
				  i--;
			  }
		  }
  
	    return str;
	  }
	@Override
	public void processMenu(UIMenu[] menu) {
		StringBuilder b = new StringBuilder();
		b.append(menu[0].getHeading());
		b.append("\n");
		b.append("Enter choice by number;");
		b.append("\n");
		
		for(int i = 1;i < menu.length; i++ ) {
			b.append("  " + i + ". " + menu[i].getPrompt());
			 b.append("\n");
		}
	    String response = JOptionPane.showInputDialog(b.toString());
	    if (response == null) {
	      response = "";
	    }
	    int selection;
	    try {
	      selection = Integer.parseInt(response, 10);
	      if ((selection < 0) || (selection >= menu.length))
	        selection = 0;
	    } catch (NumberFormatException e) {
	      selection = 0;
	    } 
	    menu[selection].runAct().run();
	}}
package shop.ui;

import shop.ui.UIMenuAction;

public interface UIMenu {
	
	public UIMenuAction runAct();
	public String getPrompt();
	public String getHeading();
}

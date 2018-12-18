package shop.main;

import shop.ui.UI;
import shop.ui.UIMenu;
import shop.ui.UIMenuAction;
import shop.ui.UIError;
import shop.ui.UIForm;
import shop.ui.UIFormTest;
import shop.data.Data;
import shop.data.Inventory;
import shop.data.Video;
import shop.data.Record;
import shop.command.Command;
import java.util.Iterator;
import java.util.Comparator;

class Control {
	private static UIMenu[] menuStart;
	private static UIMenu[] menuExit;
	private static UIForm[] videoFORM;
	private static UIForm[] numberFORM;
	private static State state;
  private static Inventory _inventory;
  private static UI _ui;
  
  Control(Inventory inventory, UI uiE) {
    _inventory = inventory;
    _ui = uiE;
    
    state=State.START;
    menuStart =(UIMenu[]) Start.values();
    menuExit = (UIMenu[]) Exit.values();
    videoFORM = (UIForm[]) VideoForm.values();
    numberFORM = (UIForm[]) Number.values();
  }
  
  
    
   /** 
    UIFormBuilder f = new UIFormBuilder();
    f.add("Title", _stringTest);
    f.add("Year", yearTest);
    f.add("Director", _stringTest);
    _getVideoForm = f.toUIForm("Enter Video");
  }**/
  
  void run() {
    try {
      while (state != State.EXITED) {
    	  if(state==State.START) {
    		  _ui.processMenu(menuStart);
    	  }	  
    	  else {
        _ui.processMenu(menuExit);
      }
    }     
    }catch (UIError e) {
      _ui.displayError("UI closed");
    }
  }
  
  enum Start implements UIMenu{
	  DEFAULT("Default",
		      new UIMenuAction() {
	        public void run() {
	          _ui.displayError("doh!");
	        }
	      }),
	  
	  ADD("Add/Remove copies of a video",
		      new UIMenuAction() {
	        public void run() {
	          String[] result1 = _ui.processForm(videoFORM);
	          Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

	          //UIFormBuilder f = new UIFormBuilder();
	          //f.add("Number of copies to add/remove", _numberTest);
	          String[] result2 = _ui.processForm(numberFORM);
	                                             
	          Command c = Data.newAddCmd(_inventory, v, Integer.parseInt(result2[0]));
	          if (! c.run()) {
	            _ui.displayError("Command failed");
	          }
	        }
	      }),
	  IN("Check in a video",
		      new UIMenuAction() {
	        public void run() {
	        	String[] result1 = _ui.processForm(videoFORM);
	            Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

	                                               
	            Command c = Data.newInCmd(_inventory, v);
	            if (! c.run()) {
	              _ui.displayError("Command failed, try again");
	            }  // TODO
	        }
	      }),
	  OUT("Check out a video",
		      new UIMenuAction() {
	        public void run() {
	        	String[] result1 = _ui.processForm(videoFORM);
	            Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

	                                               
	            Command c = Data.newOutCmd(_inventory, v);
	            if (! c.run()) {
	              _ui.displayError("Command failed, try again");
	            }  // TODO
	        }
	      }),
	  PRINT("Print the inventory",
		      new UIMenuAction() {
	        public void run() {
	          _ui.displayMessage(_inventory.toString());
	        }
	      }),
	  CLEAR("Clear the inventory",
		      new UIMenuAction() {
	        public void run() {
	          if (!Data.newClearCmd(_inventory).run()) {
	            _ui.displayError("Command failed");
	          }
	        }
	      }),
	  UNDO("Undo",
			      new UIMenuAction() {
	        public void run() {
	          if (!Data.newUndoCmd(_inventory).run()) {
	            _ui.displayError("Command failed");
	          }
	        }
	      }),
	  REDO("Redo",
		      new UIMenuAction() {
	        public void run() {
	          if (!Data.newRedoCmd(_inventory).run()) {
	            _ui.displayError("Command failed");
	          }
	        }
	      }),
	  TOPTEN("Print top ten all time rentals in order",
	    		new UIMenuAction()
	    {

	        public void run()
	        {
	            // TODO
	            if (_inventory.size() > 0)
	            {
	                Iterator<Record> it = _inventory.iterator(new java.util.Comparator<Record>()
	                {

	                    public int compare(Record r1, Record r2)
	                    {
	                        return r2.numRentals() - r1.numRentals();
	                    }
	                });

	                StringBuilder b = new StringBuilder();
	                int counter = 1;
	                b.append("Top Ten All Time Rentals!: \n");
	                while (it.hasNext() && counter < 11)
	                {
	                	Record r = it.next();
	                    b.append(" "+counter + " [" + r + ":" + r.numRentals() + "," + r.numOut() + "]\n");

	                    counter++;
	                }
	                _ui.displayMessage(b.toString());
	            } else
	            {
	                _ui.displayError("Inventory is Empty!");
	            }
	        }
	        // DONE
	    }),
	  BOGUS("Initialize with bogus contents",
		      new UIMenuAction() {
	        public void run() {
	          Data.newAddCmd(_inventory, Data.newVideo("a", 2000, "m"), 1).run();
	          Data.newAddCmd(_inventory, Data.newVideo("b", 2000, "m"), 2).run();
	          Data.newAddCmd(_inventory, Data.newVideo("c", 2000, "m"), 3).run();
	          Data.newAddCmd(_inventory, Data.newVideo("d", 2000, "m"), 4).run();
	          Data.newAddCmd(_inventory, Data.newVideo("e", 2000, "m"), 5).run();
	          Data.newAddCmd(_inventory, Data.newVideo("f", 2000, "m"), 6).run();
	          Data.newAddCmd(_inventory, Data.newVideo("g", 2000, "m"), 7).run();
	          Data.newAddCmd(_inventory, Data.newVideo("h", 2000, "m"), 8).run();
	          Data.newAddCmd(_inventory, Data.newVideo("i", 2000, "m"), 9).run();
	          Data.newAddCmd(_inventory, Data.newVideo("j", 2000, "m"), 10).run();
	          Data.newAddCmd(_inventory, Data.newVideo("k", 2000, "m"), 11).run();
	          Data.newAddCmd(_inventory, Data.newVideo("l", 2000, "m"), 12).run();
	          Data.newAddCmd(_inventory, Data.newVideo("m", 2000, "m"), 13).run();
	          Data.newAddCmd(_inventory, Data.newVideo("n", 2000, "m"), 14).run();
	          Data.newAddCmd(_inventory, Data.newVideo("o", 2000, "m"), 15).run();
	          Data.newAddCmd(_inventory, Data.newVideo("p", 2000, "m"), 16).run();
	          Data.newAddCmd(_inventory, Data.newVideo("q", 2000, "m"), 17).run();
	          Data.newAddCmd(_inventory, Data.newVideo("r", 2000, "m"), 18).run();
	          Data.newAddCmd(_inventory, Data.newVideo("s", 2000, "m"), 19).run();
	          Data.newAddCmd(_inventory, Data.newVideo("t", 2000, "m"), 20).run();
	          Data.newAddCmd(_inventory, Data.newVideo("u", 2000, "m"), 21).run();
	          Data.newAddCmd(_inventory, Data.newVideo("v", 2000, "m"), 22).run();
	          Data.newAddCmd(_inventory, Data.newVideo("w", 2000, "m"), 23).run();
	          Data.newAddCmd(_inventory, Data.newVideo("x", 2000, "m"), 24).run();
	          Data.newAddCmd(_inventory, Data.newVideo("y", 2000, "m"), 25).run();
	          Data.newAddCmd(_inventory, Data.newVideo("z", 2000, "m"), 26).run();
	        }
	      });
	  private final String heading;
	  private final String prompt;
	  private final UIMenuAction action;
	  	Start(String prompt,UIMenuAction action){
	  		this.heading = "Main Menu";
	  		this.prompt = prompt;
	  		this.action= action;
	  	}
		public UIMenuAction runAct() {
			return action;
		}
		public String getPrompt() {
			return prompt;
		}
		
		public String getHeading() {
			return heading;
		}

  }
  
  enum Number implements UIForm{
	  NUMBER("Number of copies to add/remove", Test.NUMBER.getTest());
	  
	  private String heading;
	  private String prompt;
	  private UIFormTest test;
	  Number(String prompt, UIFormTest test){
		  this.prompt=prompt;
		  this.test=test;
		  this.heading="Number Form";
	  }
	  public UIFormTest runTest() {
		  return test;
	  }
	  public String getPrompt() {
		  return prompt;
	  }
	  public String getHeading() {
		  return heading;
	  }
  }
  
  
  enum Exit implements UIMenu {
		
		DEFAULT("Default", new UIMenuAction() { public void run() {} }),
		
		YES("Yes",
	      new UIMenuAction() {
	        public void run() {
	          state = State.EXITED;
	        }
	      }),
		
		NO("No",
	      new UIMenuAction() {
	        public void run() {
	          state = State.START;
	        }
	      });
		
		private final String heading;
		private final String prompt;
		private final UIMenuAction action;
		
		Exit(String prompt, UIMenuAction action){
			this.heading = "Exit Menu";
			this.prompt = prompt;
			this.action = action;
		}

		public UIMenuAction runAct() {
			return action;
		}

		public String getPrompt() {
			return prompt;
		}

		public String getHeading() {
			return heading;
		}
		
	}

	 enum VideoForm implements UIForm {
		  
		  TITLE("Title",Test.STRING.getTest()),
		  
		  YEAR("Year", Test.YEAR.getTest()),
		
		DIRECTOR("Director", Test.STRING.getTest());
		
		
		
		  
	   
	    private String heading;
	    private String prompt;
	    private UIFormTest test;
	    VideoForm(String prompt, UIFormTest test){
	  	  	this.prompt = prompt;
	  	  	this.test = test;
	  	  	this.heading = "Video Form";
	    }


	public UIFormTest runTest() {
		
		return test;
	}

	public String getPrompt() {
		
		return prompt;
	}

	public String getHeading() {
		return heading;
	}
	    

	}

  
  
  
  enum State{
	  START, EXIT, EXITED;
  }
  
  
  enum Test{
	  NUMBER(new UIFormTest() {
		  public boolean run(String input) {
			  try {
				  Integer.parseInt(input);
				  return true;
			  } catch (NumberFormatException e) {
				  return false;
			  }
		  }
	  }),
	  STRING(new UIFormTest(){
		  public boolean run(String input) {
			  return ! "".equals(input.trim()); 
		  }
	  }),
	  YEAR (new UIFormTest() {
		  public boolean run(String input) {
          try {
              int i = Integer.parseInt(input);
              return i > 1800 && i < 5000;
            } catch (NumberFormatException e) {
              return false;
            }
          }
        });
	private UIFormTest test;
	Test(UIFormTest test){
		this.test=test;
	}
	
	public UIFormTest getTest() {
		return test;
	}
  }
}
 

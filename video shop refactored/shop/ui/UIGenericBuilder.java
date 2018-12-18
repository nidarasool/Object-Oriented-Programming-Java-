package shop.ui;

import java.util.ArrayList;
import java.util.List;
import shop.ui.Factory;

class UIGenericBuilder<F, T, Test extends Factory<F,T>>{
  private List<Pair<String, T>> _menu;
  private Factory<F,T> fac;

  public UIGenericBuilder(Test factory) {
	if(factory == null) throw new IllegalArgumentException();
    _menu = new ArrayList<Pair<String,T>>();
    fac = factory;
  }
  
  public F toForm(String heading) {
    if (null == heading)
      throw new IllegalArgumentException();
    if (_menu.size() < 1)
      throw new IllegalStateException();
    List<Pair<String,T>> array = new ArrayList<Pair<String,T>>(_menu.size());
    for (int i = 0; i < _menu.size(); i++)
      array.add(_menu.get(i));
   return fac.newForm(heading, array); 
  }
  public void add(String prompt, T test) {
    _menu.add(new Pair<String,T>(prompt, test));
  }
}

package shop.ui;

import java.util.List;

public interface Factory<F, T> {
	public F newForm(String heading,List<Pair<String, T>> array);
}

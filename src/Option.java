package src;

import java.util.ArrayList;

public class Option {
	public static void main(String[] args) {}

	private int id;
	private String optionTitle = "";
	private String optionText = "";
	private Question parent;
	private ArrayList<Integer> connectionList;

	public Option(Question parent, int id) {
		this.id = id;
		this.parent = parent;
	}

	public void setTitle(String title) {
		optionTitle = title;
		//trigger redraw
	}

	public void setText(String text) {
		optionText = text;
		//trigger redraw
	}

	public void addConnection(int connectionID) {
		connectionList.add(connectionID);
		//trigger redraw
	}

	public void remove() {
		parent.removeOption(id);
		connectionList = null;
	}
}
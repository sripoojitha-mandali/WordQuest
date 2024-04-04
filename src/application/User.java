package application;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class User extends Person {
	
	private Map<String, List<Word>> words;
	private String level = "easy";
	
	public User(String username, String password, String firstname, String lastname) throws IOException {
		super(username, password, firstname, lastname);
		Program p = new Program();
		words = new HashMap<>();
		List<Word> l = new ArrayList<>();
		l.addAll(p.getWords().get("easy"));
		words.put("easy", l);
		l = new ArrayList<>();
		l.addAll(p.getWords().get("medium"));
		words.put("medium", l);
		l = new ArrayList<>();
		l.addAll(p.getWords().get("hard"));
		words.put("hard", l);
	}
	
	public void updateWords() throws IOException {
		updateList("easy");
		updateList("medium");
		updateList("hard");
	}
	
	public void updateList(String s) throws IOException {
		Program p = new Program();
		List<Word> l1 = new ArrayList<>();
		l1.addAll(p.getWords().get(s));
		List<Word> l2 = words.get(s);
		boolean b = false;
		int i = 0;
		int j = 0;
		while (i < l2.size()) {
			b = true;
			while (j < l1.size()) {
				if (l1.get(j).getValue().equals(l2.get(i).getValue())) {
					l1.remove(j);
					b = false;
					break;
				}
				j++;
			}
			if (b) {
				l2.remove(i);
			} else {
				i++;
			}
			j = 0;
		}
		for (Word w : l1) {
			l2.add(w);
		}
	}

	public Map<String, List<Word>> getWords() {
		return words;
	}

	public void setWords(Map<String, List<Word>> words) {
		this.words = words;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
}

package application;

public class Word {
	
	private String value;
	private String meaning;
	private int level;
	private int proficiency;
	
	public Word() {
		value = "";
		meaning = "";
		level = 0;
		proficiency = 0;
	}
	
	public Word(String value, String meaning, int level) {
		this.value = value;
		this.meaning = meaning;
		this.level = level;
		this.proficiency = 0;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getProficiency() {
		return proficiency;
	}

	public void setProficiency(int proficiency) {
		this.proficiency = proficiency;
	}
}

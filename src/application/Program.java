package application;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Program {
	
	private Map<String, List<Word>> words;
	private List<User> users;
	
	public Program() throws IOException {
		this.users = new ArrayList<>();
		this.words = new HashMap<>();
		words.put("easy", new ArrayList<Word>());
		words.put("medium", new ArrayList<Word>());
		words.put("hard", new ArrayList<Word>());
		readUsers();
		readWords();
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Map<String, List<Word>> getWords() {
		return words;
	}

	public void setEasy(Map<String, List<Word>> words) {
		this.words = words;
	}
	
	public int getLevel(String s) {
		if (s.equals("easy")) {
			return 0;
		} else if (s.equals("medium")) {
			return 1;
		}
		return 2;
	}

	public void readUsers() throws IOException {
		String filepath = "data/users.json";
        Reader reader = Files.newBufferedReader(Paths.get(filepath));
        this.users = new Gson().fromJson(reader, new TypeToken<List<User>>() {}.getType());
        reader.close();
    }
	
	public void writeUsers() throws IOException {
		String filepath = "data/users.json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fw = new FileWriter(filepath);
        gson.toJson(users, fw);
        fw.close();
    }
	
	public void readWords() throws IOException {
		String filepath = "data/words.json";
        Reader reader = Files.newBufferedReader(Paths.get(filepath));
        List<Word> allWords = new Gson().fromJson(reader, new TypeToken<List<Word>>() {}.getType());
        for (Word w: allWords) {
        	if (w.getLevel() == 0) {
        		words.get("easy").add(w);
        	} else if (w.getLevel() == 1) {
        		words.get("medium").add(w);
        	} else if (w.getLevel() == 2) {
        		words.get("hard").add(w);
        	}
        }
        reader.close();
	}
	
	public void writeWords() throws IOException {
		String filepath = "data/words.json";
		List<Word> allWords = new ArrayList<>(); 
		allWords.addAll(words.get("easy"));
		allWords.addAll(words.get("medium"));
		allWords.addAll(words.get("hard"));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter fw = new FileWriter(filepath);
		gson.toJson(allWords, fw);
		fw.close();
	}
}

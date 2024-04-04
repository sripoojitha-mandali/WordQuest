package application;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserPracticeController implements Initializable {
	
	private User user;
	private int userid;
	private Program program;
	private Word word;
	private List<Word> l;
	private boolean face;
	private Map<Integer, Color> colors;
	@FXML private AnchorPane ap;
	@FXML private Rectangle box;
	@FXML private Text text;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			program = new Program();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUsers() {
		try {
			program.writeUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setUserId(int userid) {
		colors = new HashMap<>();
		colors.put(0, Color.rgb(251, 100, 0));
		colors.put(1, Color.rgb(255, 183, 3));
		colors.put(2, Color.rgb(78, 186, 34));
		this.userid = userid;
		this.user = program.getUsers().get(userid);
		l = user.getWords().get(user.getLevel());
		Random r = new Random();
		this.word = l.get(r.nextInt(l.size()));
		text.setText(word.getValue());
		box.setFill(colors.get(word.getProficiency()));
		face = true;
	}
	
	public void knowBtn() {
		if (word.getProficiency()<2) {
			word.setProficiency(word.getProficiency()+1);
		}
		updateUsers();
		Random r = new Random();
		this.word = l.get(r.nextInt(l.size()));
		text.setText(word.getValue());
		box.setFill(colors.get(word.getProficiency()));
		face = true;
	}
	
	public void flip() {
		if (face) {
			text.setText(word.getMeaning());
			face = false;
		} else {
			text.setText(word.getValue());
			face = true;
		}
	}
	
	public void dontKnowBtn() {
		if (word.getProficiency()>0) {
			word.setProficiency(word.getProficiency()-1);
		}
		updateUsers();
		Random r = new Random();
		this.word = l.get(r.nextInt(l.size()));
		text.setText(word.getValue());
		box.setFill(colors.get(word.getProficiency()));
		face = true;
	}
	
	public void back(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
		Parent root = loader.load();
		UserController controller = loader.getController();
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		controller.setUserId(userid);
		stage.show();
	}
	
	public void logoout(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show(); 
	}

}

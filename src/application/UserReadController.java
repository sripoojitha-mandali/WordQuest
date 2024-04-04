package application;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class UserReadController implements Initializable {
	
	private User user;
	private int userid;
	private Program program;
	@FXML private TableView<Word> wordTable;
    @FXML private TableColumn<Word, String> wordColumn;
    @FXML private TableColumn<Word, String> meaningColumn;
    @FXML private TableColumn<Word, String> profColumn;
    
	@Override
	public void initialize(URL url, ResourceBundle rb) {
        try {
        	this.program = new Program();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	public void populateTable() {
		ObservableList<Word> words = FXCollections.observableArrayList();
        for (Word w: user.getWords().get(user.getLevel())) {
        	words.add(w);
        }
        wordColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("value"));
		meaningColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("meaning"));
		profColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("proficiency"));
		wordTable.setItems(words);
	}
	
	public void setUserId(int userid) {
		this.userid = userid;
		this.user = program.getUsers().get(userid);
        populateTable();
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

package application;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class AdminWordController implements Initializable {
	
	private Program program;
	private int selectedRow = -1;
	@FXML private ComboBox<String> levelComboBox;
	@FXML private TableView<Word> wordTable;
    @FXML private TableColumn<Word, String> wordColumn;
    @FXML private TableColumn<Word, String> meaningColumn;
    @FXML private Button deleteBtn;
    @FXML private TextField wordTxtField;
    @FXML private TextArea meaningTxtField;
	
	public AdminWordController() throws IOException {
		this.program = new Program();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {

        // Fills values in combo box with 3 levels
        levelComboBox.getItems().addAll("Easy", "Medium", "Hard");
        levelComboBox.setValue("Easy");
        
		// Fills in the values with all the words belonging to the level selected in the combo box
		populateTable();
		
		// Makes Cells editable
		wordTable.setEditable(true);
        wordColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        meaningColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	}
	
	public void populateTable() {
		ObservableList<Word> words = FXCollections.observableArrayList();
        for (Word w: program.getWords().get(levelComboBox.getValue().toLowerCase())) {
        	words.add(w);
        }
        wordColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("value"));
		meaningColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("meaning"));
		wordTable.getItems().clear();
		wordTable.setItems(words);
	}
	
	public void updateWords() {
		try {
			program.writeWords();
		} catch (Exception e) {
			System.out.println("Error writing to json in AdminWord");
			e.printStackTrace();
		}
	}
	
	public void comboBoxChanged() {
		populateTable();
	}
	
	public void addWord() {
		String s = levelComboBox.getValue().toLowerCase();
		System.out.println(s);
		program.getWords().get(s).add(new Word(wordTxtField.getText(), meaningTxtField.getText(), program.getLevel(s)));
		wordTxtField.clear();
		meaningTxtField.clear();
		populateTable();
		updateWords();
	}
	
	public void deleteWord() {
		if (selectedRow<0) {
			return;
		}
		String s = levelComboBox.getValue().toLowerCase();
		program.getWords().get(s).remove(selectedRow);
		populateTable();
		updateWords();
		selectedRow = -1;
	}
	
	public void selectRow() {
		selectedRow = wordTable.getSelectionModel().getSelectedIndex();
	}
	
	public void changeWordCellEvent(CellEditEvent<Word, String> edittedCell) {
		int ind =  edittedCell.getTablePosition().getRow();
        program.getWords().get(levelComboBox.getValue().toLowerCase()).get(ind).setValue(edittedCell.getNewValue().toString());
        populateTable();
        updateWords();
	}
	
	public void changeMeaningCellEvent(CellEditEvent<Word, String> edittedCell) {
		int ind =  edittedCell.getTablePosition().getRow();
        program.getWords().get(levelComboBox.getValue().toLowerCase()).get(ind).setMeaning(edittedCell.getNewValue().toString());
        populateTable();
        updateWords();
	}
	
	public void back(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
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

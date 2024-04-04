package application;

import java.io.IOException;

import javafx.event.ActionEvent;

public interface SceneControllerInterface {
	
	public void login(ActionEvent event) throws IOException;
	
	public void userLogin(ActionEvent event) throws IOException;
	
	public void adminLogin(ActionEvent event) throws IOException;
	
//	public void logout(ActionEvent event) throws IOException;
//	
//	public void selectEasy(ActionEvent event) throws IOException;
//	
//	public void selectMedium(ActionEvent event) throws IOException;
//	
//	public void selectHard(ActionEvent event) throws IOException;
//	
//	public void back(ActionEvent event) throws IOException;
//	
//	public void addWord(ActionEvent event) throws IOException;
//	
//	public void editWord(ActionEvent event) throws IOException;
//	
//	public void deleteWord(ActionEvent event) throws IOException;
//	
//	public void viewWord(ActionEvent event) throws IOException;
//	
	public void selectWord(ActionEvent event) throws IOException;
	
//	public void selectUser(ActionEvent event) throws IOException;
//	
//	public void addUser(ActionEvent event) throws IOException;
//	
//	public void editUser(ActionEvent event) throws IOException;
//	
//	public void deleteUser(ActionEvent event) throws IOException;
//	
//	public void viewUser(ActionEvent event) throws IOException;
}

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class ContentScene {
	@FXML MenuItem BackListMenu;
	@FXML MenuItem SaveAll;
	@FXML MenuItem OpenImage;
	@FXML MenuItem SaveImage;
	@FXML MenuItem Undo;
	@FXML MenuItem Clear;
	@FXML MenuItem About;

	@FXML ImageView BookImage;
	@FXML Rectangle ImageArea;

	@FXML TextField NameText;
	@FXML TextField AuthorText;
	@FXML TextField PageNum;
	@FXML TextField PubliserText;
	@FXML TextArea MemoText;

	public void backButton(ActionEvent ev){
		MainWindow.singleton.changePage(SceneState.LIST);
		return;
	}
	public void saveAllText(ActionEvent ev){
		return;
	}
	public void openImage(ActionEvent ev){
		return;
	}
	public void SaveImage(ActionEvent ev){
		return;
	}
	public void undo(ActionEvent ev){
		return;
	}
	public void clear(ActionEvent ev){
		return;
	}

}

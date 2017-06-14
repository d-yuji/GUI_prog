package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

public class Starter{
	@FXML Button Button1;
	@FXML Hyperlink Start;
	@FXML
	public void doAction(ActionEvent ev){
		try {
			MainWindow.singleton.changePage(SceneState.LIST);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//System.out.println("Hello World");
	}

}
package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

public class Starter extends AbstractScene{
	@FXML Button Button1;
	@FXML Hyperlink Start;
	@FXML

	@Override
	public void changeScene(ActionEvent ev) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			MainWindow.singleton.changePage(SceneState.LIST);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	@Override
	public void closeEvent(ActionEvent ev) {
		// TODO 自動生成されたメソッド・スタブ

	}
	@Override
	public void initialize() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
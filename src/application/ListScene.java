package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class ListScene extends AbstractScene {
	FileChooser fc = new FileChooser();

	@FXML Button Content1;
	@FXML Button Content2;
	@FXML Button Content3;
	@FXML TextArea text;
	@FXML TextArea text2;
	@FXML GridPane gridPane;
	@FXML MenuItem idOpen;

	@FXML
	public void doAction(ActionEvent ev){
		try {
			Button btn = new Button();
			btn.setText("added Button");
			gridPane.getChildren().add( btn);
			System.out.println("add");
			MainWindow.singleton.ShowStage();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//System.out.println("Hello World");
	}
	@FXML
	public void changeState(ActionEvent ev){
		try{
			MainWindow.singleton.changePage(SceneState.CONTENT);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@FXML
	public void Load(ActionEvent ev){
		String url = "src/data/";
		File dirPath = new File(url);
		File[] files = dirPath.listFiles();
		String loadtext;
		int i = 0;
		while(files.length>i){
			try {
				loadtext = new Scanner(files[i]).useDelimiter("\\Z").next();
				text.setText(loadtext);
			} catch (FileNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			i++;
		}
	}
	public void openFile(ActionEvent ev){
		System.out.println("open");
		File openFile = fc.showOpenDialog(MainWindow.singleton.stage);
		System.out.println(openFile.getPath());
		try {
			String loadtext = new Scanner(openFile).useDelimiter("\\Z").next();
			System.out.println(loadtext);
			text.setText(loadtext);
			//TODO フォーカスされていないタブのテキストエリアに書き込みをしようとするとエラーが発生する
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}
}

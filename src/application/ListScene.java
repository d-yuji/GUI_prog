package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class ListScene{
	FileChooser fc = new FileChooser();

	@FXML Button Content1;
	@FXML Button Content2;
	@FXML Button Content3;
	@FXML Button Content4;
	@FXML TextArea text;
	@FXML TextArea text2;
	@FXML GridPane gridPane;
	@FXML MenuItem idOpen;
	@FXML MenuItem idClose;
	@FXML TabPane tab;

	@FXML
	public void doAction(ActionEvent ev){

		try {
			Button btn = new Button();
			btn.setText("add");
			gridPane.getChildren().add( btn);
			System.out.println("add");
			MainWindow.singleton.ShowStage();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//System.out.println("Hello World");
		return;
	}
	@FXML
	public void changeState(ActionEvent ev){
		try{
			MainWindow.singleton.changePage(SceneState.CONTENT);
		}catch(Exception e){
			e.printStackTrace();
		}
		return;
	}
	@FXML
	public void Load(ActionEvent ev){
		String url = "src/bookdata/txt";
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
		return;
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
		return;
	}
	public void addTabitem(ActionEvent ev){
		tab.getTabs().add(new Tab ("Add tab"));
		return;
	}
	public void closeEvent(ActionEvent ev){
		System.exit(0);
		return;
	}
}

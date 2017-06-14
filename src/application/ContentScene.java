package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

public class ContentScene {

	private String bookTitle;
	private String bookAuthor;
	private String pageNum;
	private String publisher;

	@FXML MenuItem backButtonMenu;
	public void backButton(ActionEvent ev){
		MainWindow.singleton.changePage(SceneState.LIST);
		return;
	}

	@SuppressWarnings("resource")
	public void loadContent(Pane pane){
		String url = "src/bookdata/txt";
		File dirPath = new File(url);
		File[] files = dirPath.listFiles();
		String text;
		int i = 0;
		while(files.length>i){
			try {
//					System.out.println(i+":"+files[i].getName());
				pane.getChildren().add(new Label((i+1)+":"+files[i].getName()+"\n\n"));
				text = new Scanner(files[i]).useDelimiter("\\Z").next();
				pane.getChildren().add(new Label(text));
			} catch (FileNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			i++;
		}
	}


	public void showContentData(){

	}

}

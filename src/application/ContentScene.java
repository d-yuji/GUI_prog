package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ContentScene extends AbstractScene {

	private String bookTitle;
	private String bookAuthor;
	private String pageNum;
	private String publisher;

	private static EditScene edit = new EditScene();
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		BorderPane pane = new BorderPane();
		Button btn = new Button();
		btn.setText("edit");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
					try {
						edit.start(primaryStage);
					} catch (Exception e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}
			}
		);
		VBox box = new VBox();
		box.getChildren().add(new Label("content"));
		this.loadContent(box);
		pane.setCenter(box);
		pane.setBottom(btn);
		Scene scene = new Scene(pane, 1366, 768);
		primaryStage.setScene(scene);//Scene:表示する内容を組み込む

	}

	@Override
	public Pane loadFXMLLayout() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void showLayout() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void moveScene() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@SuppressWarnings("resource")
	public void loadContent(Pane pane){
		String url = "src/data/";
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

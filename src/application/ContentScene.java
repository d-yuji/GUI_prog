package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
		pane.setCenter(new Label("content"));
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

	public void loadContent(){

	}

	public void showContentData(){

	}

}

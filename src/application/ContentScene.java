package application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ContentScene extends AbstractScene {
	private String bookTitle;
	private String bookAuthor;
	private String pageNum;
	private String publisher;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		BorderPane pane = new BorderPane();
		System.out.println("test");
		Scene scene = new Scene(pane, 1366, 768);
		scene.getStylesheets().add("application/css/application.css");
		primaryStage.getIcons().add(new Image("application/res/images/icon.png"));
		primaryStage.setTitle("icontest");
		primaryStage.setScene(scene);//Scene:表示する内容を組み込む
		primaryStage.show();//ウィンドウの表示
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

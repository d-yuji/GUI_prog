package application;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class EditScene extends AbstractScene {
	private static AbstractScene content = new ContentScene();
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		Button btn = new Button();
		Button fopen = new Button();
 		BorderPane pane = new BorderPane();
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(
					new ExtensionFilter("Text Files", "*.txt"),
					new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
					new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
					new ExtensionFilter("All Files", "*.*")
			);
		btn.setText("move state");
		fopen.setText("open file");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
					try {
						content.start(primaryStage);
					} catch (Exception e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}
			}
		);
		fopen.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
					try {
							fileOpen(fc,primaryStage,pane);
					} catch (Exception e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}
		});
		pane.setCenter(new Label("edit"));
		pane.setBottom(btn);
		pane.setLeft(fopen);
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
	public void addImage(){

	}
	public void editImage(){

	}
	public void saveText(){

	}
	private static void fileOpen(FileChooser fc,Stage stage,BorderPane pane){
		File openFile = fc.showOpenDialog(stage);
		if(openFile != null){
			try {
				String text = new Scanner(openFile).useDelimiter("\\Z").next();
				pane.setLeft(new Label(text));
//				Writer writer = new FileWriter(fc.showSaveDialog(stage));
//				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

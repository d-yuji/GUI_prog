package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class EditScene  {

	public void start(Stage primaryStage) throws Exception {
		Button btn = new Button();
		Button fopen = new Button();
		Button fsave = new Button();
		VBox box = new VBox();
 		BorderPane pane = new BorderPane();
		FileChooser fc = new FileChooser();
		TextArea textarea = new TextArea();
		fc.getExtensionFilters().addAll(
					new ExtensionFilter("Text Files", "*.txt"),
					new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
					new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
					new ExtensionFilter("All Files", "*.*")
		);
		btn.setText("move content");
		fopen.setText("open file");
		fsave.setText("save File");

		textarea.setText("file value");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
					try {

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
							fileOpen(fc,primaryStage,pane,textarea);
					} catch (Exception e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}
		});
		fsave.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
					try {
							saveText(fc,primaryStage,textarea);
					} catch (Exception e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}
		});
		box .getChildren().add(fopen);
		box.getChildren().add(fsave);
		pane.setBottom(btn);
		pane.setLeft(box);
		pane.setCenter(textarea);
		Scene scene = new Scene(pane, 1366, 768);
		primaryStage.setScene(scene);//Scene:表示する内容を組み込む
	}

	public void addImage(){

	}
	public void editImage(){

	}
	private static void saveText(FileChooser fc,Stage stage,TextArea area){
		try {
			FileWriter filewriter = new FileWriter(fc.showSaveDialog(stage));
			filewriter.write(area.getText());
			filewriter.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	private static void fileOpen(FileChooser fc,Stage stage,BorderPane pane,TextArea area){
		File openFile = fc.showOpenDialog(stage);
		if(openFile != null){
			try {
				@SuppressWarnings("resource")
				String text = new Scanner(openFile).useDelimiter("\\Z").next();
				area.setText(text);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

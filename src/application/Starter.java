package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Starter extends Application {
	SceneState startState = SceneState.CONTENT;

	@Override
	public void start(Stage primaryStage) {
			Button btn = new Button();
			FileChooser fc = new FileChooser();
			BorderPane pane = new BorderPane();
			Image img = new Image("application/res/images/icon.png");
			ImageView imgView = new ImageView();
			imgView.setImage(img);

			fc.getExtensionFilters().addAll(
						new ExtensionFilter("Text Files", "*.txt"),
						new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
						new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
						new ExtensionFilter("All Files", "*.*")
			);
			btn.setText("open file");
			btn.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						File openFile = fc.showOpenDialog(primaryStage);
						if(openFile != null){
							System.out.println("get file path");
							try {
								Writer writer = new FileWriter(fc.showSaveDialog(primaryStage));
//								writer.write("test");
								writer.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			);

			pane.setRight(imgView);
			pane.setBottom(btn);
			Scene scene = new Scene(pane, 1366, 768);
			scene.getStylesheets().add("application/css/application.css");
			primaryStage.getIcons().add(new Image("application/res/images/icon.png"));
			primaryStage.setTitle("icontest");
			primaryStage.setScene(scene);//Scene:表示する内容を組み込む
			primaryStage.show();//ウィンドウの表示
	}
	public static void main(String[] args) {
		launch(args);//GUIの起動
	}
}
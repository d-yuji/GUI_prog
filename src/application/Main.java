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
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
			Button btn = new Button();
			FileChooser fc = new FileChooser();
			StackPane root = new StackPane();//Pane:コンポーネントを埋め込む
			BorderPane pane = new BorderPane();
			fc.getExtensionFilters().addAll(
						new ExtensionFilter("Text Files", "*.txt"),
						new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
						new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
						new ExtensionFilter("All Files", "*.*"));

			btn.setText("open file");
			btn.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
//						System.out.println("Hello World!");
						File openFile = fc.showOpenDialog(primaryStage);
						if(openFile != null){
							System.out.println("get file path");
//							fc.showSaveDialog(primaryStage);
							try {
								Writer writer = new FileWriter(fc.showSaveDialog(primaryStage));
								writer.write("test");
								writer.close();
							} catch (IOException e) {
								// TODO 自動生成された catch ブロック
								e.printStackTrace();
							}
						}
					}
				}
			);


			Label label = new Label("This is JavaFX!");
			root.getChildren().add(btn);
			pane.setCenter(label);
			pane.setBottom(btn);
			Scene scene = new Scene(pane, 1366, 768);

			primaryStage.setTitle("icontest");
			primaryStage.setScene(scene);//Scene:表示する内容を組み込む
			primaryStage.show();//ウィンドウの表示
	}
	public static void main(String[] args) {
		launch(args);//GUIの起動
	}
}
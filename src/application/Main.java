package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
			Button btn = new Button();
			FileChooser file = new FileChooser();
			btn.setText("open file");
			btn.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
//						System.out.println("Hello World!");
						file.showOpenDialog(primaryStage);
					}
				}
			);

			StackPane root = new StackPane();//Pane:コンポーネントを埋め込む
			BorderPane pane = new BorderPane();
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
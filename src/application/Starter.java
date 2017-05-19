package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Starter extends Application {
	private static AbstractScene sceneContent = new ContentScene();
	@Override
	public void start(Stage primaryStage) {
			Button btn = new Button();
			BorderPane pane = new BorderPane();
			Image img = new Image("application/res/images/icon.png");
			ImageView imgView = new ImageView();
			imgView.setImage(img);

			btn.setText("start");
			btn.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						try {
							sceneContent.start(primaryStage);
						} catch (Exception e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}
					}
				}
			);
			pane.setCenter(btn);
			pane.setBottom(new Label("Starter"));
			Scene scene = new Scene(pane, 1366, 768);
			primaryStage.getIcons().add(new Image("application/res/images/icon.png"));
			primaryStage.setTitle("icontest");
			primaryStage.setScene(scene);//Scene:表示する内容を組み込む
			primaryStage.show();//ウィンドウの表示

	}
	public static void main(String[] args) {
		launch(args);//GUIの起動
	}
}
package application;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class AbstractScene extends Application{
		Image img = new Image("application/res/images/icon.png");
		Stage primaryStage;
		public abstract Pane loadFXMLLayout();
		public abstract void showLayout();
		public abstract void moveScene();
		public void setIcon(){
			primaryStage.getIcons().add(img);
		}

}

package application;

import javafx.application.Application;
import javafx.scene.layout.Pane;

public abstract class AbstractScene extends Application{
		public abstract Pane loadFXMLLayout();
		public abstract void showLayout();
		public abstract void moveScene();

}

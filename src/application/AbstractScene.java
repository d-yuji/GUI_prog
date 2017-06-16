package application;

import javafx.event.ActionEvent;

public abstract class AbstractScene{
	public abstract void changeScene(ActionEvent ev);
	public abstract void closeEvent(ActionEvent ev);
	public abstract void initialize();
}

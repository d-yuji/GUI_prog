package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainWindow extends Application {
	public static MainWindow singleton;
	private Parent root;
	Stage stage;
	private  int pageIndex = 4;
	private Image iconImage = new Image("application/res/images/icon.png");
	private SceneState state;
	private  String[] PAGE = new String[] {
			"Add",
			"Content",
			"Edit",
			"List",
			"Starter"
	};

	public void start(Stage primaryStage) throws Exception{
		singleton = this;
		stage = primaryStage;
		stage.getIcons().add(iconImage);
		setPage(pageIndex);
	}
	public void changePage(SceneState next){
		state = next;
		switch (next){
			case ADD:
				pageIndex = 0;
				break;
			case CONTENT:
				pageIndex = 1;
				break;
			case EDIT:
				pageIndex = 2;
				break;
			case LIST:
				pageIndex = 3;
				break;
			case STARTER:
				pageIndex = 4;
				break;
		}
		setPage(pageIndex);
	}

	public void changePage(SceneState next,String filename){
		state = next;
		switch (next){
			case ADD:
				pageIndex = 0;
				break;
			case CONTENT:
				pageIndex = 1;
				ContentScene.filename = filename;
				break;
			case EDIT:
				pageIndex = 2;
				break;
			case LIST:
				pageIndex = 3;
				break;
			case STARTER:
				pageIndex = 4;
				break;
		}
		setPage(pageIndex);
	}


	private void setPage(int nextIndex){
		try{
			root = FXMLLoader.load(getClass().getResource("../fxml/"+PAGE[nextIndex]+".fxml"));
			stage.setTitle(PAGE[nextIndex]);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static MainWindow getInstance(){
		return singleton;
	}
	public void ShowStage(){
		stage.show();
	}
	public static void main(String[] args){
		System.out.println("Start");
		launch(args);
	}
}

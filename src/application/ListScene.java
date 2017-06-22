package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class ListScene extends AbstractScene{
	FileChooser fc = new FileChooser();
	@FXML Hyperlink GUI;
	@FXML TabPane tabParent;

	@FXML
	public void addTab(ActionEvent ev){
		Tab addTab = new Tab("test");
		AnchorPane pane = new AnchorPane();
		SplitPane spane = new SplitPane();
		spane.getItems().addAll(new Label("test1"), new Label("test2"));
		spane.setDividerPositions(100, 200);
		pane.getChildren().add(spane);
		addTab.setContent(pane);
		tabParent.getTabs().add(addTab);

		return;
	}

	/*
	@FXML
	public void doAction(ActionEvent ev){

		try {
			Button btn = new Button();
			btn.setText("add");
			gridPane.getChildren().add( btn);
			System.out.println("add");
			MainWindow.singleton.ShowStage();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//System.out.println("Hello World");
		return;
	}
	*/
	@FXML
	public void changeScene(ActionEvent ev){
		try{
			MainWindow.singleton.changePage(SceneState.CONTENT);
		}catch(Exception e){
			e.printStackTrace();
		}
		return;
	}
	@FXML
	public void Load(ActionEvent ev){
		String url = "src/bookdata/txt";
		File dirPath = new File(url);
		File[] files = dirPath.listFiles();
		String loadtext;
		Label text = new Label();
		int i = 0;
		while(files.length>i){
			try {
				loadtext = new Scanner(files[i]).useDelimiter("\\Z").next();
				text.setText(loadtext);
			} catch (FileNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			i++;
		}
		return;
	}
	/*
	public void openFile(ActionEvent ev){
		System.out.println("open");
		File openFile = fc.showOpenDialog(MainWindow.singleton.stage);
		System.out.println(openFile.getPath());
		try {
			String loadtext = new Scanner(openFile).useDelimiter("\\Z").next();
			System.out.println(loadtext);
			text.setText(loadtext);
			//TODO フォーカスされていないタブのテキストエリアに書き込みをしようとするとエラーが発生する
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return;
	}
	*/
	public void loadXML(ActionEvent ev) throws ParserConfigurationException, FileNotFoundException, SAXException, IOException{
			String url = "src/bookdata/xml";
			VBox box = new VBox();
			Pane xmlTab = new Pane();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new FileInputStream(url+"/data_1.xml"));
			Element root = doc.getDocumentElement();
			walkXML(box,root);
			xmlTab.getChildren().add(box);
	}

	public void walkXML(VBox box, org.w3c.dom.Node node){
		for(org.w3c.dom.Node ch = node.getFirstChild(); ch != null; ch = ch.getNextSibling()){
			System.out.println(ch.getNodeName()+"::");
			if(ch.getNodeType() == Node.ELEMENT_NODE){
				box.getChildren().add(new Label(ch.getNodeName()));
				walkXML(box,ch);
			}
			else if(ch.getNodeType() == Node.TEXT_NODE && ch.getNodeValue().trim().length() !=0){
				if(ch.getParentNode().getNodeName() == "img"){
					Image img = new Image(ch.getNodeValue());
					System.out.println(ch.getNodeValue());
					ImageView imageView = new ImageView(img);
					imageView.setFitHeight(300);
					imageView.setFitWidth(200);
					box.getChildren().add(imageView);
				}
				else{
					box.getChildren().add(new Label("value::"+ch.getNodeValue()));
				}
			}
		}
	}


	@Override
	public void closeEvent(ActionEvent ev){
		System.exit(0);
		return;
	}

	@Override
	public void initialize() {

	}


}

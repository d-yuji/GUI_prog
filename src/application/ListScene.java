package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class ListScene{
	FileChooser fc = new FileChooser();
	@FXML Hyperlink GUI;
	@FXML TabPane tabParent;
	@FXML TitledPane BookList;
	@FXML Pane BookListPane;
	@FXML MenuItem newdata;
	ContentScene cs = new ContentScene();
	 String url = "src/bookdata/xml";

	@FXML
	private void changeScene(ActionEvent ev){
		try{
			MainWindow.singleton.changePage(SceneState.CONTENT);
		}catch(Exception e){
			e.printStackTrace();
		}
		return;
	}

	private void walkXML(VBox box, org.w3c.dom.Node node){
		for(org.w3c.dom.Node ch = node.getFirstChild(); ch != null; ch = ch.getNextSibling()){
			if(ch.getNodeType() == Node.ELEMENT_NODE){
				box.getChildren().add(new Label(ch.getNodeName()));
				walkXML(box,ch);
			}
			else if(ch.getNodeType() == Node.TEXT_NODE && ch.getNodeValue().trim().length() !=0){
				if(ch.getParentNode().getNodeName() == "img"){
					Image img = new Image(ch.getNodeValue());
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

	@FXML
	private void addNewBookXML(ActionEvent ev) throws ParserConfigurationException, FileNotFoundException, TransformerException{
		TextInputDialog textIn  = new TextInputDialog( "book_data" );
		textIn.setContentText("type new file name");
		textIn.setHeaderText("New File");
		textIn.setTitle("New Data File");
		String filename = textIn.showAndWait().orElse("");
		if(filename.length() != 0){
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			DOMImplementation domImpl=builder.getDOMImplementation();
			Document document = domImpl.createDocument("","book",null);

			Element book = document.getDocumentElement();
			Element name = document.createElement("name");
			book.appendChild(name);
			Element author = document.createElement("author");
			book.appendChild(author);
			Element publisher = document.createElement("publisher");
			book.appendChild(publisher);
			Element img = document.createElement("img");
			book.appendChild(img);
			Element page = document.createElement("page");
			book.appendChild(page);
			Element memo = document.createElement("memo");
			book.appendChild(memo);

			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transformer = transFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			File newXML = new File(url+"/"+filename+".xml");
			FileOutputStream os = new FileOutputStream(newXML);
			StreamResult result = new StreamResult(os);
			transformer.transform(source, result);
			TitledPaneClick(new MouseEvent(null, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null));
		}
		System.out.println(filename);
	}
	@FXML
	private void closeEvent(ActionEvent ev){
		System.exit(0);
		return;
	}

	@FXML
	private void TitledPaneClick(MouseEvent event) {
		File dirPath = new File(url);
		File[] files = dirPath.listFiles();
		VBox booklinkBox = new VBox();
		int len = files.length;
		BookListPane.getChildren().clear();

		for(int i =0; i<len; i++){
			Hyperlink booklink = new Hyperlink(files[i].getName());

			booklink.setOnAction(new EventHandler<ActionEvent>() {
					String filename = booklink.getText();
					@Override
					public void handle(ActionEvent e) {
						Tab addTab = new Tab(filename);
						VBox controllVBox = new VBox();
						Hyperlink edit = new Hyperlink("edit");
						Hyperlink close = new Hyperlink("close");
						BorderPane bpane = new BorderPane();
						ScrollPane spane=new ScrollPane();
						VBox xmlBox = new VBox();
						ScrollPane scPane = new ScrollPane(new Label("data"));

						close.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent e) {
								System.out.println("close");
								tabParent.getTabs().remove(addTab);
							}
						});

						edit.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent e) {
								MainWindow.singleton.changePage(SceneState.CONTENT,filename);
								System.out.println("change");
							}
						});

						controllVBox.getChildren().add(edit);
						controllVBox.getChildren().add(close);

						spane.setContent(controllVBox);
						bpane.setLeft(spane);
						bpane.setCenter(scPane);

						try{
							DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
							DocumentBuilder db = dbf.newDocumentBuilder();
							Document doc = db.parse(new FileInputStream(url+"/"+booklink.getText()));
							Element root = doc.getDocumentElement();
							walkXML(xmlBox,root);
							scPane.setContent(xmlBox);
						}catch (Exception error){
							error.printStackTrace();
						}

						bpane.setCenter(scPane);
						addTab.setContent(bpane);
						tabParent.getTabs().add(addTab);
				}
			});
			booklinkBox.getChildren().add(booklink);
		}
		BookListPane.getChildren().add(booklinkBox);
	}
}

package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
import org.xml.sax.SAXException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
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

public class ListScene extends AbstractScene{
	FileChooser fc = new FileChooser();
	@FXML Hyperlink GUI;
	@FXML TabPane tabParent;
	@FXML TitledPane BookList;
	@FXML Pane BookListPane;
	@FXML MenuItem newdata;

	 String url = "src/bookdata/xml";

	@FXML
	public void addTab(ActionEvent ev){
		Tab addTab = new Tab("test");
		VBox pane = new VBox();
		Hyperlink close = new Hyperlink("close");
		Hyperlink edit = new Hyperlink("edit");

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
				MainWindow.singleton.changePage(SceneState.CONTENT);
			}
		});

		pane.getChildren().add(edit);
		pane.getChildren().add(close);

		SplitPane spane = new SplitPane();
		spane.getItems().addAll(new Label("test1"), new Label("test2"));
		spane.setDividerPositions(0, 1);
		pane.getChildren().add(spane);
		addTab.setContent(pane);
		tabParent.getTabs().add(addTab);

		return;
	}

	@FXML
	public void changeScene(ActionEvent ev){
		try{
			MainWindow.singleton.changePage(SceneState.CONTENT);
		}catch(Exception e){
			e.printStackTrace();
		}
		return;
	}
	/*
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
	}*/
	/*
	public void openFile(ActionEvent ev){
		System.out.println("open");
		File openFile = fc.showOpenDialog(MainWindow.singleton.stage);
		System.out.println(openFile.getPath());
		VBox box = new VBox();
		Label text=new Label();
		try {
			String loadtext = new Scanner(openFile).useDelimiter("\\Z").next();
			System.out.println(loadtext);
			text.setText(loadtext);
			box.getChildren().add(text);
			//TODO フォーカスされていないタブのテキストエリアに書き込みをしようとするとエラーが発生する
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return;
	}*/

	public void loadXML(ActionEvent ev) throws ParserConfigurationException, FileNotFoundException, SAXException, IOException{
			String url = "src/bookdata/xml";
			VBox box = new VBox();
			Pane xmlPane = new Pane();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new FileInputStream(url+"/data_1.xml"));
			Element root = doc.getDocumentElement();
			walkXML(box,root);
			xmlPane.getChildren().add(box);
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

	@FXML
	public void addNewBookXML(ActionEvent ev) throws ParserConfigurationException, FileNotFoundException, TransformerException{
		TextInputDialog textIn  = new TextInputDialog( "book_data" );
		textIn.setContentText("type new file name");
		textIn.setHeaderText("New File");
		textIn.setTitle("New Data File");
		String filename = textIn.showAndWait().orElse("");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		DOMImplementation domImpl=builder.getDOMImplementation();
		Document document = domImpl.createDocument("","root",null);

		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transformer = transFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		File newXML = new File(url+"/"+filename+".xml");
		FileOutputStream os = new FileOutputStream(newXML);
		StreamResult result = new StreamResult(os);
		transformer.transform(source, result);
		System.out.println(filename);
	}

	@Override
	public void closeEvent(ActionEvent ev){
		System.exit(0);
		return;
	}
	@Override
	public void initialize() {

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
//						Hyperlink open = new Hyperlink("open");

						BorderPane bpane = new BorderPane();
						ScrollPane spane=new ScrollPane();
						VBox xmlBox = new VBox();
						ScrollPane scPane = new ScrollPane(new Label("data"));

/*						open.setOnAction(new EventHandler<ActionEvent>(){
							@Override
							public void handle(ActionEvent event) {

							}
						});
*/
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
								MainWindow.singleton.changePage(SceneState.CONTENT);
							}
						});

						controllVBox.getChildren().add(edit);
						controllVBox.getChildren().add(close);
//						controllVBox.getChildren().add(open);

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

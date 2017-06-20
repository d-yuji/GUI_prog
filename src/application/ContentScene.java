package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ContentScene {

	@FXML MenuItem BackListMenu;
	@FXML MenuItem SaveAll;
	@FXML MenuItem OpenImage;
	@FXML MenuItem SaveImage;
	@FXML MenuItem Undo;
	@FXML MenuItem Clear;
	@FXML MenuItem About;

	@FXML ImageView BookImage;
	@FXML Rectangle ImageArea;

	@FXML TextField NameText;
	@FXML TextField AuthorText;
	@FXML TextField PageNum;
	@FXML TextField PubliserText;
	@FXML TextArea MemoText;

	public void backButton(ActionEvent ev){
		MainWindow.singleton.changePage(SceneState.LIST);
		return;
	}
	public void saveAllText(ActionEvent ev) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException, TransformerException{
		String url = "src/bookdata/xml";
		File dirPath = new File(url);
		File[] files = dirPath.listFiles();
		//String filename = "data_"+(files.length+1)+".xml" ;
		//System.out.println(filename);
		String filename = "data_1.xml";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new FileInputStream(url+"/data_1.xml"));
		Element root = doc.getDocumentElement();
		walkXML(root);
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		tf.transform(new DOMSource(doc), new StreamResult(url+"/data_1.xml"));
		return;
	}

	public void walkXML(org.w3c.dom.Node node){
		for(org.w3c.dom.Node ch = node.getFirstChild(); ch != null; ch = ch.getNextSibling()){

			if(ch.getNodeType() == Node.ELEMENT_NODE){
				walkXML(ch);
			}
			else if(ch.getNodeType() == Node.TEXT_NODE && ch.getNodeValue().trim().length() !=0){
				if(ch.getParentNode().getNodeName() == "img"){
					System.out.println("Now not implement");
				}
				else if(ch.getParentNode().getNodeName() == "name"){
					ch.setNodeValue(NameText.getText());
				}
			}
		}
	}

	public void openImage(ActionEvent ev){
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(
				new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
				new ExtensionFilter("All Files", "*.*")
		);
		try{
			File openFile = fc.showOpenDialog(MainWindow.singleton.stage);
			Image openimage = new Image(openFile.toURI().toString());
			BookImage.setImage(openimage);
			ImageArea.setOpacity(0);
		}catch(Exception e){
			e.printStackTrace();
		}

		return;
	}
	public void SaveImage(ActionEvent ev){
		return;
	}
	public void undo(ActionEvent ev){
		return;
	}
	public void clear(ActionEvent ev){
		return;
	}

}

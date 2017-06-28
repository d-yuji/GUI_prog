package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
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

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ContentScene {
	String url = "src/bookdata/xml";
	String imageurl = "src/bookdata/images/";
	String xmlurl = "bookdata/images/";
	static String filename;
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
	@FXML TextField PublisherText;
	@FXML TextArea MemoText;

	@FXML
	private void backButton(ActionEvent ev){
		MainWindow.singleton.changePage(SceneState.LIST);
		return;
	}

	@FXML
	private void saveAllText(ActionEvent ev) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException, TransformerException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new FileInputStream(url+ "/"+filename));
		Element root = doc.getDocumentElement();

		walkwriteXML(root);

		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		tf.setOutputProperty("indent", "yes");
		tf.transform(new DOMSource(doc), new StreamResult(url+"/"+filename));
		System.out.println("save text");
		return;
	}

	private boolean saveImage(WritableImage img, String base, String fmt) throws IOException{
		File newImagefile = new File(imageurl+base + "." + fmt);
		return ImageIO.write(SwingFXUtils.fromFXImage(img, null), fmt, newImagefile);
	}

	private void walkwriteXML(org.w3c.dom.Node node){
		System.out.println("start walking");
		for(org.w3c.dom.Node ch = node.getFirstChild(); ch != null; ch = ch.getNextSibling()){
			if(ch.getNodeType() == Node.ELEMENT_NODE){
				if(ch.getFirstChild() != null){
					walkwriteXML(ch);
				}else{
					switch(ch.getNodeName()){
					case "name":
						ch.setTextContent(NameText.getText());
						break;
					case "author":
						ch.setTextContent(AuthorText.getText());
						break;
					case "publisher":
						ch.setTextContent(PublisherText.getText());
						break;
					case "img":
						WritableImage writableImage = BookImage.snapshot(null, null);
						try {
							saveImage(writableImage,filename,"png");
						} catch (IOException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}
						ch.setTextContent(xmlurl+filename+".png");
						break;
					case "page":
						ch.setTextContent(PageNum.getText());
						break;
					case "memo":
						ch.setTextContent(MemoText.getText());
						break;
					}
				}
			}
			else if(ch.getNodeType() == Node.TEXT_NODE && ch.getNodeValue().trim().length() !=0){
				switch(ch.getParentNode().getNodeName()){
					case "name":
						ch.setNodeValue(NameText.getText());
						break;
					case "author":
						ch.setNodeValue(AuthorText.getText());
						break;
					case "publisher":
						ch.setNodeValue(PublisherText.getText());
						break;
					case "img":

						break;
					case "page":
						ch.setNodeValue(PageNum.getText());
						break;
					case "memo":
						ch.setNodeValue(MemoText.getText());
						break;
				}
			}
		}
	}

	private void walkloadXML(org.w3c.dom.Node node){
		for(org.w3c.dom.Node ch = node.getFirstChild(); ch != null; ch = ch.getNextSibling()){
			if(ch.getNodeType() == Node.ELEMENT_NODE){
				walkloadXML(ch);
			}
			else if(ch.getNodeType() == Node.TEXT_NODE && ch.getNodeValue().trim().length() !=0){
				switch(ch.getParentNode().getNodeName()){
					case "name":
						NameText.setText(ch.getNodeValue());
						break;
					case "author":
						AuthorText.setText(ch.getNodeValue());
						break;
					case "publisher":
						PublisherText.setText(ch.getNodeValue());
						break;
					case "img":
						Image img = new Image(ch.getNodeValue());
						BookImage.setImage(img);
						ImageArea.setOpacity(0);
						break;
					case "page":
						PageNum.setText(ch.getNodeValue());
						break;
					case "memo":
						MemoText.setText(ch.getNodeValue());
						break;
				}
			}
		}
	}

	@FXML
	private void openImage(ActionEvent ev){
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

	@FXML
	private void initialize() throws ParserConfigurationException, FileNotFoundException, SAXException, IOException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new FileInputStream(url+ "/"+filename));
		Element root = doc.getDocumentElement();
		walkloadXML(root);
	}

	@FXML
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

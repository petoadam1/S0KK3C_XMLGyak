package doms0kk3c1026;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;

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

public class DomWriterS0KK3C {

	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document doc = builder.newDocument();
		
		Element root = doc.createElementNS("doms0kk3c", "users");
		doc.appendChild(root);
		
		root.appendChild(createUser(doc, "1", "Adam", "Peto", "mernokinformatikus"));
		root.appendChild(createUser(doc, "2", "Balazs", "Leho", "sportolo"));
		root.appendChild(createUser(doc, "3", "Adam", "Szendy", "gazdasz"));
		
		TransformerFactory transformerfactory = TransformerFactory.newInstance();
		Transformer transf = transformerfactory.newTransformer();
		
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		DOMSource source = new DOMSource(doc);
		File myFile = new File("users1S0KK3C.xml");
		
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);
		
		transf.transform(source, console);
	}
	
	public static Node createUser(Document doc, String id, String firstName, String lastName, String profession) {
		Element user = doc.createElement("user");
		
		user.setAttribute("id", id);
		user.appendChild(creatUserElement(doc, "firstname", firstName));
		user.appendChild(creatUserElement(doc, "lastname", lastName));
		user.appendChild(creatUserElement(doc, "profession", profession));
		
		return user;
	}
	
	private static Node createUserElement(Document doc, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}

}

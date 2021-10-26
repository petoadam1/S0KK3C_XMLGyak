package doms0kk3c1026;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomReadS0KK3C {

	private static final String FILENAME = "/home/stud2019/peto8/eclipse-workspace/Adatkezeles_XML-ben/XML-ek/usersS0KK3C.xml";

	public static void main(String[] args) throws SAXException,IOException, ParserConfigurationException {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	      try {

	          // optional, but recommended
	          // process XML securely, avoid attacks like XML External Entities (XXE)
	          dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

	          // parse XML file
	          DocumentBuilder db = dbf.newDocumentBuilder();

	          Document doc = db.parse(new File(FILENAME));

	          // optional, but recommended
	          // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	          doc.getDocumentElement().normalize();

	          System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
	          System.out.println("------");

	          // get <staff>
	          NodeList list = doc.getElementsByTagName("user");

	          for (int temp = 0; temp < list.getLength(); temp++) {

	              Node node = list.item(temp);

	              if (node.getNodeType() == Node.ELEMENT_NODE) {

	                  Element element = (Element) node;

	                  // get staff's attribute
	                  String id = element.getAttribute("id");

	                  // get text
	                  String firstname = element.getElementsByTagName("firstName").item(0).getTextContent();
	                  String lastname = element.getElementsByTagName("lastName").item(0).getTextContent();
	                  String profession= element.getElementsByTagName("profession").item(0).getTextContent();

	                  System.out.println("Current Element :" + node.getNodeName());
	                  System.out.println("User Id : " + id);
	                  System.out.println("First Name : " + firstname);
	                  System.out.println("Last Name : " + lastname);
	                  System.out.println("Profession : " + profession);

	              }
	          }

	      } catch (ParserConfigurationException | SAXException | IOException e) {
	          e.printStackTrace();
	      }

	}
}

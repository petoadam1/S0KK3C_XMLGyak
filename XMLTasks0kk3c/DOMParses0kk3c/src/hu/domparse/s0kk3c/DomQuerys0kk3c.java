package hu.domparse.s0kk3c;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQuerys0kk3c {

	public static void main(String[] args)
			throws ParserConfigurationException, IOException, SAXException, TransformerException {

		File xmlFile = new File("XMLs0kk3c.xml"); // xml fájl bekérése
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // olvasás lehetővé tétele, // XML dokumentumból DOM objektum lehetővé tétele
		DocumentBuilder dBuilder = factory.newDocumentBuilder(); // XML fájl, Document lekéréséhez
		Document doc = dBuilder.parse(xmlFile); // dokument lekérése
		doc.getDocumentElement().normalize();

		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		System.out.println("------------------------------");
		LoadVendegQuery(doc);

	}

	public static void LoadVendegQuery(Document doc) throws TransformerException {
		NodeList nodeList = doc.getElementsByTagName("vendeg"); // vendeg elemek listázása
		String vendeg;
		Element element = null;
		Node nNode = null;
		for (int i = 0; i < nodeList.getLength(); i++) {
			nNode = nodeList.item(i);
			element = (Element) nNode;
			String nev = element.getElementsByTagName("nev").item(0).getTextContent();
			System.out.println((i + 1) + ") " + nev);

		}
		// Tánciskola választása
		System.out.println("Írja be annak a vendég nevét, amelyiknek az ételeit szeretné látni: ");
		Scanner sc = new Scanner(System.in);
		vendeg = sc.nextLine();
		for (int i = 0; i < nodeList.getLength(); i++) {
			nNode = nodeList.item(i);
			element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				if (vendeg.equals("Pető Ádám")) {
					LoadEtelQuery(doc, "01");
					break;
				}
				if (vendeg.equals("Iványi Fruzsina")) {
					LoadEtelQuery(doc, "02");
					break;
				}
			}
		}
		sc.close();

	}

	public static void LoadEtelQuery(Document doc, String id) throws TransformerException {
		NodeList nodeList = doc.getElementsByTagName("etel");
		int etel = 0;

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nNode = nodeList.item(i);
			Element element = (Element) nNode;
			String vId = element.getAttribute("vendegId");
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (id.equals(vId)) {
					etel += 1;
					System.out.println(etel + ". etel adatai:");
					String etelkod = element.getAttribute("etelkod");
					DomReads0kk3c.ReadEtelById(doc, etelkod);

				}
			}
		}

	}
}

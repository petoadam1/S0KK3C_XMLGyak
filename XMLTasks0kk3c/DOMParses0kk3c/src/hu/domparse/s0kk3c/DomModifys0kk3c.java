package hu.domparse.s0kk3c;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DomModifys0kk3c {

	public static void main(String[] args)
			throws ParserConfigurationException, IOException, SAXException, TransformerException {

		File xmlFile = new File("XMLs0kk3c.xml"); // xml fájl bekérése
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // olvasás lehetõvé tétele, // XML dokumentumból DOM objektum lehetővé tétele
		DocumentBuilder dBuilder = factory.newDocumentBuilder(); // XML fájl, Document lekéréséhez
		Document doc = dBuilder.parse(xmlFile); // dokument lekérése
		doc.getDocumentElement().normalize();
		System.out.println("XML Módosítása");
		System.out.println("Adja meg mit szeretne módosítani: ");
		System.out.println("1 Nyersanyag módosítása\n2 Etel módosítása\n3 Termelo módosítása\n4 Vendeg módosítása");
		Modify(doc);

	}
	public static void Modify(Document doc) throws TransformerException {
		int nyersanyagSzama = doc.getElementsByTagName("nyersanyag").getLength(); // nyersanyag számának lekérdezése
		int etelSzama = doc.getElementsByTagName("etel").getLength(); // etel számának lekérdezése
		int termeloSzama = doc.getElementsByTagName("termelo").getLength(); // termelo számának lekérdezése
		int vendegSzama = doc.getElementsByTagName("vendeg").getLength(); // vendeg számának lekérdezése
																				
		Scanner scan = new Scanner(System.in);
		System.out.println("Adja meg a sorszamot: ");
		int readCategory = scan.nextInt();
		switch (readCategory) {
		case 1:
			ModifyNyersanyag(doc, nyersanyagSzama);
			break;
		case 2:
			ModifyEtel(doc, etelSzama);
			break;
		case 3:
			ModifyTermelo(doc, termeloSzama);
			break;
		case 4:
			ModifyVendeg(doc, vendegSzama);
			break;
		}
		scan.close();
	}
	
	private static void ModifyNyersanyag(Document doc, int nyersanyagSzama) throws TransformerException {
		// Kiiratjuk a jelenlegi nyersanyagokat, majd lekérdezzük melyiket kívánja módosítani
		System.out.println("Melyik nyersanyag adatait szeretné módosítani?");
		for (int i = 1; i < nyersanyagSzama + 1; i++) {
			System.out.println(i + ". nyersanyag");
			DomReads0kk3c.ReadNyersanyagById(doc, String.valueOf(i));
			System.out.println("-------------------------------------------");
		}
		String id = ReadId();
		System.out.println(id);
		// Bekérjük az új adatokat
		Scanner scanner = new Scanner(System.in);
		System.out.print("Tipus: ");
		String tipus = scanner.nextLine();
		scanner.close();
		// lekérdezzük az Elementeket, majd setTextContent-el módosítjuk.
		NodeList nodeList = doc.getElementsByTagName("nyersanyag");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nNode = nodeList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nNode;
				String sid = element.getAttribute("azonosito");
				if (sid.equals(id)) {
					Node node1 = element.getElementsByTagName("tipus").item(0);
					node1.setTextContent(tipus);
					System.out.println("Sikeres módosítás");
				}
			}
		}
		ModifyXML(doc); // Létrehozzuk az XML-t
	}
	
	private static void ModifyEtel(Document doc, int etelSzama) throws TransformerException {
		// Kiiratjuk a jelenlegi eteleket, majd lekérdezzük melyiket kívánja módosítani
		System.out.println("Melyik etel adatait szeretné módosítani?");
		for (int i = 1; i < etelSzama + 1; i++) {
			System.out.println(i + ". etel");
			DomReads0kk3c.ReadEtelById(doc, String.valueOf(i));
			System.out.println("-------------------------------------------");
		}
		String id = ReadId();
		// Bekérjük az új adatokat
		Scanner sc = new Scanner(System.in);
		System.out.print("Nev: ");
		String nev = sc.nextLine();
		sc.close();
		// lekérdezzük az Elementeket, majd setTextContent-el módosítjuk.
		NodeList nodeList = doc.getElementsByTagName("etel");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nNode = nodeList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nNode;
				String sid = element.getAttribute("etelkod");
				if (sid.equals(id)) {
					Node node1 = element.getElementsByTagName("nev").item(0);
					node1.setTextContent(nev);
					System.out.println("Sikeres módosítás");
				}
			}
		}
		ModifyXML(doc); // Létrehozzuk az XML-t
	}
	private static void ModifyTermelo(Document doc, int termeloSzama) throws TransformerException {
		// Kiiratjuk a jelenlegi termeloket, majd lekérdezzük melyiket kívánja módosítani
		System.out.println("Melyik termelo adatait szeretné módosítani?");
		for (int i = 1; i < termeloSzama + 1; i++) {
			System.out.println(i + ". termelo");
			DomReads0kk3c.ReadTermeloById(doc, String.valueOf(i));
			System.out.println("-------------------------------------------");
		}
		String id = ReadId();
		// Bekérjük az új adatokat
		Scanner sc = new Scanner(System.in);
		System.out.print("Nev: ");
		String nev = sc.nextLine();
		System.out.print("Telephely: ");
		String telephely = sc.nextLine();
		sc.close();
		// lekérdezzük az Elementeket, majd setTextContent-el módosítjuk.
		NodeList nodeList = doc.getElementsByTagName("termelo");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nNode = nodeList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nNode;
				String sid = element.getAttribute("tid");
				if (sid.equals(id)) {
					Node node1 = element.getElementsByTagName("nev").item(0);
					node1.setTextContent(nev);
					Node node2 = element.getElementsByTagName("telephely").item(0);
					node2.setTextContent(telephely);
					System.out.println("Sikeres módosítás");
				}
			}
		}
		ModifyXML(doc); // Létrehozzuk az XML-t
	}
	private static void ModifyVendeg(Document doc, int vendegSzama) throws TransformerException {
		// Kiiratjuk a jelenlegi vendegeket, majd lekérdezzük melyiket kívánja módosítani
		System.out.println("Melyik vendeg adatait szeretné módosítani?");
		for (int i = 1; i < vendegSzama + 1; i++) {
			System.out.println(i + ". vendeg");
			DomReads0kk3c.ReadVendegById(doc, String.valueOf(i));
			System.out.println("-------------------------------------------");
		}
		String id = ReadId();
		// Bekérjük az új adatokat
		Scanner sc = new Scanner(System.in);
		System.out.print("Nev: ");
		String nev = sc.nextLine();
		System.out.print("Eletkor: ");
		String eletkor = sc.nextLine();
		System.out.print("Nem: ");
		String nem = sc.nextLine();
		sc.close();
		// lekérdezzük az Elementeket, majd setTextContent-el módosítjuk.
		NodeList nodeList = doc.getElementsByTagName("vendeg");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nNode = nodeList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nNode;
				String sid = element.getAttribute("vendegkod");
				if (sid.equals(id)) {
					Node node1 = element.getElementsByTagName("nev").item(0);
					node1.setTextContent(nev);
					Node node2 = element.getElementsByTagName("eletkor").item(0);
					node2.setTextContent(eletkor);
					Node node3 = element.getElementsByTagName("nem").item(0);
					node3.setTextContent(nem);
					System.out.println("Sikeres módosítás");
				}
			}
		}
		ModifyXML(doc); // Létrehozzuk az XML-t
	}
	
	public static String ReadId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("\nid:");
		String id = sc.nextLine();
		//sc.close();
		return id;
	}
	public static void ModifyXML(Document doc) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("XMLs0kk3c.xml"));
		transformer.transform(source, result);
	}

}

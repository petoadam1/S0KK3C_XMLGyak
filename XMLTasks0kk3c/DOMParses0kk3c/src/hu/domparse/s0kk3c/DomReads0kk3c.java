package hu.domparse.s0kk3c;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomReads0kk3c {

	public static void main(String[] args) {
		try {
			File xmlFile = new File("XMLs0kk3c.xml"); // xml fájl, amelyből olvasunk
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // XML dokumentumból DOM objektum lehetővé tétele
			DocumentBuilder dBuilder = factory.newDocumentBuilder(); // XML fájl, Document lekéréséhez
			Document doc = dBuilder.parse(xmlFile); // dokument lekérése
			System.out.println(doc);
			doc.getDocumentElement().normalize();
			System.out.println("Étterem adatok lekérése");
			System.out.println(xmlFile);
			System.out.println(doc);
			Read(doc); // fő metódus
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	public static void Read(Document doc) {
		NodeList nList = doc.getElementsByTagName("etterem"); // Etterem taggal rendelkezõ elemek lekérése
																// listába
		for (int i = 0; i < nList.getLength(); i++) { // listán végigmegyünk
			Node nNode = nList.item(i); // lekérjük a lista aktuális elemét, majd elementé konvertáljuk
			Element element = (Element) nNode;
			// Lekérjük az attribútumokat, majd azok segítségével meghívjuk a definiált metódusokat
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				String nev = element.getElementsByTagName("nev").item(0).getTextContent(); // darabszám lekérdezése
				String elerhetoseg = element.getElementsByTagName("elerhetoseg").item(0).getTextContent();
				String cim = element.getElementsByTagName("cim").item(0).getTextContent();
				String etelId = element.getAttribute("etelId");
				String nyersanyagId = element.getAttribute("nyersanyagId");
				System.out.println("\n-----------------------------------" + (i + 1) + ". Étterem-----------------------------------");
				System.out.println("\n\tNév:\t" + nev);
				System.out.println("\n\tElérhetőség:\t" + elerhetoseg);
				System.out.println("\n\tCím:\t" + cim);
				ReadEtelById(doc, etelId);
				ReadNyersanyagById(doc, nyersanyagId);
				
			}
		}
	}
	
	public static void ReadEtelById(Document doc, String id) {
		NodeList nList = doc.getElementsByTagName("etel");
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("etelkod").equals(id)) {
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					System.out.println("Etel adatok: \n\tNev:\t" + nev);
					String vendegId = element.getAttribute("vendegId");
					String rendelesId = element.getAttribute("rendelesId");
					ReadVendegById(doc, vendegId);
					ReadRendelesById(doc, rendelesId);
				}
			}
		}
	}
	public static void ReadVendegById(Document doc, String id) {
		NodeList nList = doc.getElementsByTagName("vendeg");
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("vendegkod").equals(id)) {
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String eletkor = element.getElementsByTagName("eletkor").item(0).getTextContent();
					String nem = element.getElementsByTagName("nem").item(0).getTextContent();
					System.out.println("Vendeg adatok: \n\tNev:\t" + nev + "\n\tEletkor:\t" + eletkor + "\n\tNem:\t" + nem);
				}
			}
		}
	}
	public static void ReadRendelesById(Document doc, String id) {
		NodeList nList = doc.getElementsByTagName("rendeles");
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("rszam").equals(id)) {
					String datum = element.getElementsByTagName("datum").item(0).getTextContent();
					System.out.println("Rendeles adatok: \n\tDatum:\t" + datum);
					String rendeloId = element.getAttribute("rendeloId");
					ReadRendeloById(doc, rendeloId);
				}
			}
		}
	}
	public static void ReadRendeloById(Document doc, String id) {
		NodeList nList = doc.getElementsByTagName("rendelo");
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("vevokod").equals(id)) {
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String lakcim = element.getElementsByTagName("lakcim").item(0).getTextContent();
					System.out.println("Rendelo adatok: \n\tNev:\t" + nev + "\n\tLakcim:\t" + lakcim);
				}
			}
		}
	}
	public static void ReadNyersanyagById(Document doc, String id) {
		NodeList nList = doc.getElementsByTagName("nyersanyag");
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("azonosito").equals(id)) {
					String tipus = element.getElementsByTagName("tipus").item(0).getTextContent();
					System.out.println("Nyersanyag adatok: \n\tTipus:\t" + tipus);
					String termeloId = element.getAttribute("termeloId");
					ReadTermeloById(doc, termeloId);
				}
			}
		}
	}
	public static void ReadTermeloById(Document doc, String id) {
		NodeList nList = doc.getElementsByTagName("termelo");
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("tid").equals(id)) {
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String telephely = element.getElementsByTagName("telephely").item(0).getTextContent();
					System.out.println("Termelo adatok: \n\tNev:\t" + nev + "\n\tTelephely:\t" + telephely);
				}
			}
		}
	}

}

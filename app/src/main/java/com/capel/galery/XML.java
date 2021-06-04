package com.capel.galery;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/** Lista de Metodos:
 * <br/> - getDocument(String fileUrl) 
 * <br/> - getNodeList(Document doc, String xpath)
 * <br/> - getRoot(Document doc)
 * <br/> - getText(Node n)
 * <br/> - getText(NodeList n)
 * <br/> - getTextByTag(Document doc, String tag)
 * <br/> - getNodeName(Node n)
 * <br/> - getNodeName(NodeList n)
 * <br/> - getAttributeText(Node n)
 * <br/> - getAttributeText(NodeList n)
 * <br/> - getAttributeName(Node n)
 * <br/> - getAttributeName(NodeList n)
 * <br/> - updateText(Node n, String newText)
 * <br/> - createDocument(Document doc, String url)
 * 
 * @version 1.0
 * @author Marc Merino
 * @see https://wiket.esteveterradas.cat/index.php/DAM2_M06_UF1_Gesti%C3%B3n_de_ficheros_XML
 */
public class XML {

	/**@param fileUrl La ruta del archivo XML
	 * 
	 * @return Devuelve un Document del arbol XML 
	 */
	public static Document getDocument(String fileUrl) {
		File f = new File(fileUrl);
	
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			return (Document) db.parse(f);
			
		} catch (ParserConfigurationException e) {
			System.out.println("Error al crear el lector de xml " + e.getCause());
		} catch (SAXException | IOException e) {
			System.out.println("Error al leer el archivo de xml " + e.getCause());
		}	
		return null;
	}
	
	/**@param doc El Document con el arbol XML
	 * @param xpath La ruta XPath de lo nodo que queremos extraer
	 * 
	 * @return Una lista de nodos que se ubican en la ruta XPath pasada como parametro
	 */
	public static NodeList getNodeList(Document doc, String xpath) {
		XPathFactory pathFactory = XPathFactory.newInstance();
		XPath path = pathFactory.newXPath();
		
		try {
			XPathExpression pathEx = path.compile(xpath);			
			return (NodeList) pathEx.evaluate(doc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			System.out.println("Error al extraer el NodeList del Document" + e.getCause());
		}
		return null;
	}
	
	/**@param doc El Document con el arbol XML
	 * 
	 * @return El nodo raiz del documento pasado como parametro
	 */
	public static Node getRoot(Document doc) {		
		return (Node) doc.getDocumentElement();
	}
	
	/**@param n Nodo del cual se quiere extraer su contenido
	 * 
	 * @return El texto del nodo pasado como parametro
	 */
	public static String getText(Node n) {
		return n.getTextContent();
	}

	/**@param n Nodo del cual se quiere extraer su contenido
	 *
	 * @return El texto del nodo pasado como parametro
	 */
	public static int getNumber(Node n) {
		return Integer.parseInt(n.getTextContent());
	}
	
	/**@param n La lista de nodos de la cual se quiere extraer el contenido de cada nodo
	 * 
	 * @return Un array de Strings con el texto de cada nodo de la NodeList
	 */
	public static ArrayList<String> getText(NodeList n) {
		ArrayList<String> s = new ArrayList<String>(n.getLength());

		for (int i = 0; i < n.getLength(); i++) {
			s.add(XML.getText(n.item(i)));
		}
		return s;
	}

	/**@param n La lista de nodos de la cual se quiere extraer el contenido de cada nodo
	 *
	 * @return Un array de Strings con el texto de cada nodo de la NodeList
	 */
	public static ArrayList<Integer> getNumber(NodeList n) {
		ArrayList<Integer> s = new ArrayList<>(n.getLength());

		for (int i = 0; i < n.getLength(); i++) {
			s.add(XML.getNumber(n.item(i)));
		}
		return s;
	}
	
	/**@param doc El Document con el arbol XML
	 * @param tag Una String con el nombre de la etiqueta a buscar
	 * 
	 * @return Un ArrayList de Strings con el contenido de todos los nodos con la tag pasada como parametro
	 */
	public static ArrayList<String> getTextByTag(Document doc, String tag) {
		return XML.getText(doc.getElementsByTagName(tag));
	}
	
	/**@param doc El Document con el arbol XML
	 * @param tag Una String con el nombre de la etiqueta a buscar
	 * 
	 * @return El NodeList especificado en la tag
	 */
	public static Node getNodeByTag(Document doc, String tag) {
		return doc.getElementsByTagName(tag).item(0);
	}
	
	/**@param doc El Document con el arbol XML
	 * @param tag Una String con el nombre de la etiqueta a buscar
	 * 
	 * @return El NodeList especificado en la tag
	 */
	public static NodeList getNodeListByTag(Document doc, String tag) {
		return doc.getElementsByTagName(tag);
	}

	/**@param doc El Document con el arbol XML
	 * @param tag Una String con el nombre de la etiqueta a buscar
	 *
	 * @return Un ArrayList de Eneteros con el contenido de todos los nodos con la tag pasada como parametro
	 */
	public static ArrayList<Integer> getNumberByTag(Document doc, String tag) {
		return XML.getNumber(doc.getElementsByTagName(tag));
	}
	
	/**@param n Nodo del cual se quiere extraer el nombre del nodo
	 * 
	 * @return El nombre del nodo pasado como parametro
	 */
	public static String getNodeName(Node n) {
		return n.getNodeName();
	}
	
	/**@param n La lista de nodos de la cual se quiere extraer el nombre de cada nodo
	 * 
	 * @return Un ArrayList de Strings con el nombre de cada nodo
	 */
	public static ArrayList<String> getNodeName(NodeList n) {
		ArrayList<String> s = new ArrayList<String>(n.getLength());
		for (int i = 0; i < n.getLength(); i++) {
			s.add(XML.getNodeName(n.item(i)));
		}
		return s;
	}
	
	/**@param n El nodo del cual se quiere extraer los atributos
	 * 
	 * @return Un ArrayList de Strings que devuelve los atributos del nodo
	 */
	public static ArrayList<String> getAttributeText(Node n) {
		ArrayList<String> s = new ArrayList<String>(n.getAttributes().getLength());
		for (int i = 0; i < n.getAttributes().getLength(); i++) {
			s.add(n.getAttributes().item(i).getTextContent());
		}
		return s;
	}
	
	/**@param n El nodo del cual se quiere extraer los atributos
	 * 
	 * @return Un ArrayList de Strings que devuelve el valor de los atributos de la lista de nodos
	 */
	public static ArrayList<String> getAttributeText(NodeList n) {
		ArrayList<String> s = new ArrayList<String>();
		for (int i = 0; i < n.getLength(); i++) {
			s.addAll(XML.getAttributeText(n.item(i)));
		}
		return s;
	}
	
	/**@param n Un nodo del cual se quieren extraer los nombres de los atributos
	 * 
	 * @return Un ArrayList de Strings que devuelve el nombre de los atributos del nodo
	 */
	public static ArrayList<String> getAttributeName(Node n) {
		ArrayList<String> s = new ArrayList<String>();
		for (int i = 0; i < n.getAttributes().getLength(); i++) {
			s.add(n.getAttributes().item(i).getNodeName());
		}
		return s;
	}
	
	/**@param n la lista de nodos de la cual se quieren extraer los nombres de los atributos
	 * 
	 * @return Un ArrayList de Strings que devuelve el nombre de los atributos de la lista de nodos
	 */
	public static ArrayList<String> getAttributeName(NodeList n) {
		ArrayList<String> s = new ArrayList<String>();
		for (int i = 0; i < n.getLength(); i++) {
			s.addAll(XML.getAttributeName(n.item(i)));
		}
		return s;
	}
	
	/**@param n el nodo el cual se le quiere actualizar el texto
	 * @param newText el nuevo texto del nodo
	 * 
	 * @return true si se ha podido actualizar y false si ha habido algun problema
	 */
	public static boolean updateText(Node n, String newText) {
		try {
			n.setTextContent(newText);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static boolean addNode(Document doc, Node fatherNode, String name) {
		try {
			fatherNode.appendChild(doc.createElement(name));
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	
	/**@param doc el documento a escribir 
	 * 
	 * @param url del archivo xml
	 */
	public static void createDocument(Document doc, String url) {
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Result output = new StreamResult(new File(url));
			Source input = new DOMSource(doc);
			transformer.transform(input, output);
			
		} catch (TransformerException | TransformerFactoryConfigurationError e) {
			System.out.println("Error al crear el documento XML");
		}
	}
	
	/**@param rootNode
	 * @param url
	 */
	public static void createDocument(String rootNode, String url) {
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Result output = new StreamResult(new File(url));
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			transformer.transform(new DOMSource(db.newDocument().createElement(rootNode)), output);
			
		} catch (TransformerException | TransformerFactoryConfigurationError e) {
			System.out.println("Error al crear el documento XML");
		} catch (ParserConfigurationException e) {
			System.out.println("Error al generar el nodo raiz el documento XML");
		}
	}
	

}

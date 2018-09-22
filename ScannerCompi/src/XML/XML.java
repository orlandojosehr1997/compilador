/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import scannercompi.Scanner;
import scannercompi.Identificador;
import java.io.File;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author orlandojose
 */
public class XML 
{
    public static void writeXML(Scanner scanner) 
    {
        try 
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Scanner");
            doc.appendChild(rootElement);

             
            Element tokens = doc.createElement("Tokens");
            rootElement.appendChild(tokens);
            /*
            
	    // set attribute to user element
            Attr attribute = doc.createAttribute("ID");
            attribute.setValue("1");
            passwordNode.setAttributeNode(attribute);
             
            // name elements
            Element passwordElement = doc.createElement("Password");
            passwordElement.appendChild(doc.createTextNode(theSystem.getPassword()));
            passwordNode.appendChild(passwordElement);
            */
            for(Identificador token: scanner.getTokenslist())
            {
                // user elements
                Element userNode = doc.createElement("Token");
		tokens.appendChild(userNode);

                /*
		// set attribute to user element
	        Attr attr = doc.createAttribute("ID");
                attr.setValue(user.getId());
		userNode.setAttributeNode(attr);
                */
                
                // name elements
                Element nombre = doc.createElement("Nombre");
                nombre.appendChild(doc.createTextNode(token.getNombre()));
		userNode.appendChild(nombre);

		// password elements
		Element tipo = doc.createElement("Tipo");
		tipo.appendChild(doc.createTextNode(token.getTipoToken().name()));
		userNode.appendChild(tipo);
                
                Element apariciones = doc.createElement("Apariciones");
		apariciones.appendChild(doc.createTextNode(token.getApariciones()));
		userNode.appendChild(apariciones);
            }
            
            Element errors = doc.createElement("Errores");
            rootElement.appendChild(errors);
            
            for(String error: scanner.getErrores())
            {
                // user elements
                Element nodoError = doc.createElement("Error");
                errors.appendChild(nodoError);

                /*
                // set attribute to user element
                Attr attr = doc.createAttribute("ID");
                attr.setValue(student.getId());
                studentNode.setAttributeNode(attr);
                */
                // name elements
                Element nombreError = doc.createElement("Mensaje");
                nombreError.appendChild(doc.createTextNode(error));
                nodoError.appendChild(nombreError);
            }
            
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("scanner.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);
            //System.out.println("File saved!");
        } 
        catch (ParserConfigurationException | TransformerException pce) 
        {
            pce.printStackTrace();
        }
    }
    /*
    public static void readXML(BiblioTech theSystem) 
    {
        try 
        {
            File fXmlFile = new File("storage.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        
            NodeList userList = doc.getElementsByTagName("User");
            NodeList studentList = doc.getElementsByTagName("Student");
            NodeList bookList = doc.getElementsByTagName("Book");
            NodeList audiosvList = doc.getElementsByTagName("Audiovisual");
            NodeList audioResList = doc.getElementsByTagName("AudiovisualReservation");
            NodeList bookResList = doc.getElementsByTagName("BookReservation");
            NodeList passwordList = doc.getElementsByTagName("System");
        
            for (int temp = 0; temp < userList.getLength(); temp++) 
            {
                Node nNode = userList.item(temp);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) 
                {
                    Element eElement = (Element) nNode;
                
                    String id = eElement.getAttribute("ID");
                    String name = eElement.getElementsByTagName("Name").item(0).getTextContent();
                    String pass = eElement.getElementsByTagName("Password").item(0).getTextContent();
                   
                    User u = new User(id,pass,name);
                
                    theSystem.getUsers().add(u);
                
                    //System.out.println("User ID : " + id);
                    //System.out.println("Name : " + name);
                    //System.out.println("Password : " + pass);
                }
            }

            for (int temp = 0; temp < studentList.getLength(); temp++) 
            {
                Node nNode = studentList.item(temp);
                
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                
                if (nNode.getNodeType() == Node.ELEMENT_NODE) 
                {
                    Element eElement = (Element) nNode;
                
                    String id = eElement.getAttribute("ID");
                    String name = eElement.getElementsByTagName("Name").item(0).getTextContent();

                    Student s = new Student(id,name);
                    
                    theSystem.getStudents().add(s);
                    
                    //System.out.println("Student ID : " + id);
                    //System.out.println("Name : " + name);
                }
            }
       
            for (int temp = 0; temp < bookList.getLength(); temp++) 
            {
                Node nNode = bookList.item(temp);
                
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                
                if (nNode.getNodeType() == Node.ELEMENT_NODE) 
                {
                    Element eElement = (Element) nNode;
                
                    String id = eElement.getAttribute("ID");
                    String name = eElement.getElementsByTagName("Name").item(0).getTextContent();
                    int year = Integer.parseInt(eElement.getElementsByTagName("Year").item(0).getTextContent());
                    String editorial = eElement.getElementsByTagName("Editorial").item(0).getTextContent();
                    String type = eElement.getElementsByTagName("Type").item(0).getTextContent();
                    
                    if(type.equals("physic"))
                    {
                        int stock = Integer.parseInt(eElement.getElementsByTagName("Stock").item(0).getTextContent());
                        theSystem.addPhysicBook(id, name, year, editorial, stock);
                    }
                    else
                    {
                        theSystem.addDigitalBook(id, name, year, editorial);
                    }
                }
            }
        
            for (int temp = 0; temp < audiosvList.getLength(); temp++) 
            {
                Node nNode = audiosvList.item(temp);
                
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                
                if (nNode.getNodeType() == Node.ELEMENT_NODE) 
                {
                    Element eElement = (Element) nNode;
                
                    String id = eElement.getAttribute("ID");
                    String type = eElement.getElementsByTagName("Type").item(0).getTextContent();
                    String brand = eElement.getElementsByTagName("Brand").item(0).getTextContent();
                    String description = eElement.getElementsByTagName("Description").item(0).getTextContent();
                    int stock = Integer.parseInt(eElement.getElementsByTagName("Stock").item(0).getTextContent());
                    
                    theSystem.addAudiovisual(id, type, brand, description, stock);
                }
            }
        
            for (int temp = 0; temp < audioResList.getLength(); temp++) 
            {
                Node nNode = audioResList.item(temp);
                
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                
                if (nNode.getNodeType() == Node.ELEMENT_NODE) 
                {
                    Element eElement = (Element) nNode;
                
                    String idAudiovisual = eElement.getElementsByTagName("ID_Audiovisual").item(0).getTextContent();
                    String idStudent = eElement.getElementsByTagName("ID_Student").item(0).getTextContent();
                    Date date = new Date(Long.valueOf(eElement.getElementsByTagName("Date").item(0).getTextContent()));
                    if (theSystem.existAudiovisual(idAudiovisual) && theSystem.existStudent(idStudent))
                    {
                        AudiovisualReservation a = new AudiovisualReservation(theSystem.obtainAudiovisual(idAudiovisual),theSystem.obtainStudent(idStudent),date);
                        theSystem.getAudioReserve().add(a);
                    }
                }
            }

            for (int temp = 0; temp < bookResList.getLength(); temp++) 
            {
                Node nNode = bookResList.item(temp);
                
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                
                if (nNode.getNodeType() == Node.ELEMENT_NODE) 
                {
                    Element eElement = (Element) nNode;
                
                    String idBook = eElement.getElementsByTagName("ID_Book").item(0).getTextContent();
                    String idStudent = eElement.getElementsByTagName("ID_Student").item(0).getTextContent();
                    Date date = new Date(Long.valueOf(eElement.getElementsByTagName("Date").item(0).getTextContent()));
                    if (theSystem.existAudiovisual(idBook) && theSystem.existStudent(idStudent))
                    {
                        BookReservation b = new BookReservation(theSystem.obtainBook(idBook),theSystem.obtainStudent(idStudent),date);
                        theSystem.getBookReserve().add(b);
                    }
                }
            }
        
            for (int temp = 0; temp < passwordList.getLength(); temp++) 
            {
                Node nNode = passwordList.item(temp);
                
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                
                if (nNode.getNodeType() == Node.ELEMENT_NODE) 
                {
                    Element eElement = (Element) nNode;
                
                    String id = eElement.getAttribute("ID");
                    String password1 = eElement.getElementsByTagName("Password").item(0).getTextContent();
                    //System.out.println("Password leido: " + password1);
                    theSystem.setPassword(password1);
                }
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
    } 
    */
}

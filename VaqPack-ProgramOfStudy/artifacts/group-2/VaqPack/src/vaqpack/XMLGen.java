/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpack;

import java.io.File;
import java.util.*;
import javafx.scene.control.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Gonzalo
 */

public class XMLGen {
    
    public XMLGen(ArrayList<CheckBox> NodeName, String filename){
        try {
              
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("ABET-outcomes");
            doc.appendChild(rootElement);

                for (int i = 0; i <NodeName.size(); i++) {                    
					if(NodeName.get(i).isSelected())
					{
						Element e = doc.createElement("item");
						switch(i)
						{
							case 0:
								e.appendChild(doc.createTextNode("(a) An ability to apply knowledge of computing and mathematics appropriate to the program's student outcomes and to the discipline."));			
								rootElement.appendChild(e);
								break;
							case 1:
								e.appendChild(doc.createTextNode("(b) An ability to analyze a problem, and identify and define the computing requirements appropriate to its solution.\""));
								
                    rootElement.appendChild(e);
								break;
							case 2:
								e.appendChild(doc.createTextNode("(c) An ability to design, implement, and evaluate a computer-based system, process, component, or program to meet desired needs."));
								
                    rootElement.appendChild(e);
								break;
							case 3:
								e.appendChild(doc.createTextNode("(d) An ability to function effectively on teams to accomplish a common goal."));
								
								
                    rootElement.appendChild(e);break;
							case 4:
								e.appendChild(doc.createTextNode("(e) An understanding of professional, ethical, legal, security and social issues and responsibilities."));
								
                    rootElement.appendChild(e);
								break;
							case 5:
								e.appendChild(doc.createTextNode("(f) An ability to communicate effectively with a range of audiences."));
								
                    rootElement.appendChild(e);
								break;
							case 6:
								e.appendChild(doc.createTextNode("(g) An ability to analyze the local and global impact of computing on individuals, organizations, and society."));
								
                    rootElement.appendChild(e);
								break;
							case 7:
								e.appendChild(doc.createTextNode("(h) Recognition of the need for and an ability to engage in continuing professional development."));
								
                    rootElement.appendChild(e);
								break;
							case 8:
								e.appendChild(doc.createTextNode("(i) An ability to use current techniques, skills, and tools necessary for computing practice."));
								
                    rootElement.appendChild(e);
								break;
							case 9:
								e.appendChild(doc.createTextNode("(j) An ability to apply mathematical foundations, algorithmic principles, and computer science theory in the modeling and design of computer-based systems in a way that demonstrates comprehension of the tradeoffs involved in design choices."));
								
                    rootElement.appendChild(e);
								break;
							case 10:
								e.appendChild(doc.createTextNode("(k) An ability to apply design and development principles in the construction of software systems of varying complexity."));
								
                    rootElement.appendChild(e);
								break;
						}
					}
                }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filename+".xml"));


            transformer.transform(source, result);

            System.out.println("File saved!");

            } 
        catch (ParserConfigurationException pce) {
                    pce.printStackTrace();
            } 
        catch (TransformerException tfe) {
                    tfe.printStackTrace();
            }
    }
    
}
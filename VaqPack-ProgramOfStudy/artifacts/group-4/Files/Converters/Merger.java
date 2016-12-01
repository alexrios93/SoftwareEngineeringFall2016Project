/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml2htmlprototype;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * This class merges multiple XML files together under one <tag>.
 *
 * @author Germex
 */
public class Merger {

    //List of files of the given direcotry
    private ArrayList<File> filesGathered = new ArrayList<>();

    //Single instance of the convert
    private static Merger instance = null;

    //Constructor
    /**
     *
     */
    protected Merger() {
        // Exists only to defeat instantiation.
    }

    /**
     * Get single instance of the converter
     *
     * @return
     */
    public static Merger getInstance() {
        if (instance == null) {
            instance = new Merger();
        }
        return instance;
    }

    /**
     * Merges all files given to it from a List. Also, gives user the ability to declare the root
     * element (<tag>) in the new xml document.
     *
     * @param list
     * @param jointXmlRootNode
     * @param outName
     */
    public static void mergeMultipleXMLDocs(List<File> list, String jointXmlRootNode, String outName) {
        File in;

        //String xmlFileName;
        try {
            DocumentBuilderFactory dbFactory;
            DocumentBuilder dBuilder;

            Document mergedDoc;

            FileOutputStream fos = new FileOutputStream(outName);
            StreamResult xmlStreamSourceObject = new StreamResult(fos);

            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();

            mergedDoc = dBuilder.newDocument();
            Element mainRootElement = mergedDoc.createElement(jointXmlRootNode); // doc.createElementNS();
            mergedDoc.appendChild(mainRootElement);

            //Get Top Level Node
            for (File inFile : list) {
                in = new File(inFile.getAbsolutePath());
                Document current_doc1 = dBuilder.parse(in);
                Node n = mergedDoc.importNode(current_doc1.getFirstChild(), true);
                mainRootElement.appendChild(n);
            }

            // Output DOM XML to xml file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");

            DOMSource source = new DOMSource(mergedDoc);
            transformer.transform(source, xmlStreamSourceObject);

            System.out.println("The Merged xml is:" + outName);

        } catch (Exception e) {
        }

    }

    /**
     * Merge all course files in a directory and output an HTML from all the
     * files.
     *
     * type = "course" -> merges all courses type = "abet" -> merges all abet
     *
     * @param directoryName
     * @param jointXMLRootNode
     * @param xslFile
     * @param type
     * @param output
     */
    public void mergeFilesFromDirectory2HTML(String directoryName, String jointXMLRootNode, String xslFile, String type, String output) {

        //Get all files and put them into a arraylist
        generateAllFilesInDirectory(directoryName, filesGathered, type);

        File in;
        //String xmlFileName;
        try {
            DocumentBuilderFactory dbFactory;
            DocumentBuilder dBuilder;

            Document mergedDoc;

            FileOutputStream fos = new FileOutputStream(output);
            StreamResult xmlStreamSourceObject = new StreamResult(fos);

            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();

            mergedDoc = dBuilder.newDocument();
            Element mainRootElement = mergedDoc.createElement(jointXMLRootNode); // doc.createElementNS();
            mergedDoc.appendChild(mainRootElement);

            //Get Top Level Node
            for (File inFile : filesGathered) {
                in = new File(inFile.getAbsolutePath());
                Document current_doc1 = dBuilder.parse(in);
                Node n = mergedDoc.importNode(current_doc1.getFirstChild(), true);
                mainRootElement.appendChild(n);
            }

            // Output DOM XML to xml file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");

            DOMSource source = new DOMSource(mergedDoc);
            transformer.transform(source, xmlStreamSourceObject);

            System.out.println("The Merged xml is:  " + output);

        } catch (Exception e) {
            System.out.println("This error is in the mergeFiles method" + e);
        }

        //clean list
        clearFileList();

    }

    /**
     * Clears list after it is used. Avoids memory leaks and an overly large
     * list from being made and continually added to.
     */
    private void clearFileList() {
        filesGathered.clear();
    }

    private void generateAllFilesInDirectory(String directoryName, ArrayList<File> filesGathered, String type) {
        try {
            File directory = new File(directoryName);

            // get all the files from a directory
            File[] fList = directory.listFiles();

            for (File file : fList) {
                if (file.isFile()) {

                    //get only 'ABET' or 'outcomes'
                    if (file.getName().contains(type)) {
                        filesGathered.add(file);
                        //get only courses
                    } else if ("course".equals(type) && file.getName().length() == 13) {
                        filesGathered.add(file);
                    }

                } else if (file.isDirectory()) {
                    //Recursively call in for any sub directories
                    generateAllFilesInDirectory(file.getAbsolutePath(), filesGathered, type);
                }

            }
        } catch (NullPointerException e) {
            System.out.print("Error in generateAllFilesInDirectory method " + e);
        }
    }
}

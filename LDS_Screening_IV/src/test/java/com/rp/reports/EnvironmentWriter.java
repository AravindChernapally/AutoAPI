package com.rp.reports;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class EnvironmentWriter {
    private HashMap<String, String> _envProps;

    public EnvironmentWriter() {
        _envProps = new HashMap<>();
    }

    /**
     * Add an environment property to the collection.
     * @param key
     * @param value
     */
    public void add(String key, String value) {
        if (_envProps.containsKey(key)) {
            // We just overwrite if added more than once.
            _envProps.replace(key, value);
        } else {
            _envProps.put(key, value);
        }
    }

    public void generateEnvironmentFile(String reportLocation, String environmentFileName) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;

        if (_envProps.isEmpty()) {
            System.out.println("Environment properties collection is empty. Nothing to write in the environment file.");
            return;
        }

        try {
            docBuilder = dbFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("environment");
            _envProps.forEach((key, value) -> {
               Element parameterElement = doc.createElement("parameter");
               Element keyElement = doc.createElement("key");
               keyElement.appendChild(doc.createTextNode(key));
               Element valueElement = doc.createElement("value");
               valueElement.appendChild(doc.createTextNode(value));
               parameterElement.appendChild(keyElement);
               parameterElement.appendChild(valueElement);
               rootElement.appendChild(parameterElement);
            });

            doc.appendChild(rootElement);

            // Convert the document to a string to write to the output file.
            DOMSource domSource = new DOMSource(doc);
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);

            String xmlContent = sw.toString();

            // Write out the file.
            Path directoryPath = Paths.get(reportLocation);
            Path path = Paths.get(reportLocation + "/" + environmentFileName);
            Files.createDirectories(directoryPath);
            Files.writeString(path, xmlContent);
        } catch (Exception e) {
            System.out.println("Error while generating environment file. Error is: " + e.getLocalizedMessage());
        }
    }
}

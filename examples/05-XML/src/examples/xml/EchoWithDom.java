package examples.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class EchoWithDom {

    private int depth = 0;
    private String[] NODE_TYPES = {
        "", "ELEMENT", "ATTRIBUTE", "TEXT", "CDATA",
        "ENTITY_REF", "ENTITY", "PROCESSING_INST",
        "COMMENT", "DOCUMENT", "DOCUMENT_TYPE",
        "DOCUMENT_FRAG", "NOTATION"};

    public static void main(String[] args) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("world.xml");
        
        new EchoWithDom().echo(doc);
    }

    private void echo(Node node) {
        print(node);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            NamedNodeMap atts = node.getAttributes();
            ++depth;
            for (int i = 0; i < atts.getLength(); i++) {
                echo(atts.item(i));
            }
            --depth;
        }

        depth++;
        for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            echo(child);
        }
        depth--;
    }

    private void print(Node node) {
        for (int i = 0; i < depth; i++) {
            System.out.print("\t");
        }

        System.out.print(NODE_TYPES[node.getNodeType()] + ":");
        System.out.print("Name: " + node.getNodeName());
        System.out.println("  Value: " + node.getNodeValue());
    }
}
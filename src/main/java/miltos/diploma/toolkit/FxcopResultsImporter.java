package miltos.diploma.toolkit;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * This class is responsible for importing the issues found by FxCop 15.0
 * in to a data structure.
 */
public class FxcopResultsImporter {

    /**
     * Parse issues in the Fxcop XML file that correspond to a property
     * into an IssueSet object
     *
     * @param path
     *      the directory location of the XML file
     * @return
     *      object representing all found violations of the current property
     */
    public IssueSet parseIssues(String path) throws ParserConfigurationException, IOException, SAXException {

        File scanResults = new File(path);
        String propertyName = scanResults.getName().substring(0, scanResults.getName().length()-4);
        IssueSet issues = new IssueSet(propertyName);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder loader = factory.newDocumentBuilder();
        Document document = loader.parse("Results/Analysis/SingleProjectResults/java-baseModel-perfect-score/FxcopFindingsProperty01.xml");
        DocumentTraversal trav = (DocumentTraversal) document;

        NodeIterator it = trav.createNodeIterator(document.getDocumentElement(), NodeFilter.SHOW_ALL, null, true);

        for (Node node = it.nextNode(); node != null; node = it.nextNode()) {
            if (node.getNodeName().equals("Message")) {

                Node messageNode = node;
                Node issueNode = it.nextNode();
                Issue issue = new Issue();

                NamedNodeMap messageAttributes = messageNode.getAttributes();
                NamedNodeMap issueAttributes = issueNode.getAttributes();

                issue.setRuleName(messageAttributes.getNamedItem("TypeName").getNodeValue());
                issue.setRuleSetName(messageAttributes.getNamedItem("Category").getNodeValue());
                issue.setPackageName(null);
                issue.setDescription(issueNode.getNodeValue());
                issue.setExternalInfoUrl(messageAttributes.getNamedItem("CheckId").getNodeValue());
//                issue.setPriority(null);
//                issue.setBeginLine(null);
//                issue.setEndLine(null);
//                issue.setBeginCol(null);
//                issue.setEndCol(null);
            }
        }
        throw new RuntimeException("TODO complete writing method");

    }
}

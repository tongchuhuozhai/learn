package bc.com;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Utility class for parsing XML content.
 * 
 * @author raoaming
 * 
 */
public class XMLParserAPN {

	private Element root;

	public XMLParserAPN(InputStream is) {
		if (is == null)
			return;

		DocumentBuilder docBuilder;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = dbf.newDocumentBuilder();
			Document doc = docBuilder.parse(is);
			root = doc.getDocumentElement();
		} catch (Exception e) {
			return;
		}
	}

	
	// <item>value</item>
	public String getValueByTag(String tagName) {
		if (root == null)
			return null;
		NodeList list = root.getElementsByTagName(tagName);
		if (list == null || list.getLength() == 0)
			return null;
		Node node = list.item(0).getFirstChild();
		if (node == null) {
			return null;
		} else {
			return node.getNodeValue();
		}
	}
	
	public int getTagCount(String tagName){
		NodeList list = root.getElementsByTagName(tagName);
		return list.getLength();
	}
	
	public String getValueByTag1(String tagName, int index) {
		if (root == null)
			return null;
		NodeList list = root.getElementsByTagName(tagName);
		if (list == null || list.getLength() == 0)
			return null;
		Node node = list.item(index).getFirstChild();
		if (node == null) {
			return null;
		} else {
			return node.getNodeValue();
		}
	}

	public Map<String, String> getDataByTag(String tag) {
		if (root == null)
			return null;
		Map<String, String> dataMap = new HashMap<String, String>();
		NodeList nodeList = root.getElementsByTagName(tag);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			for (int j = 0; j < n.getAttributes().getLength(); j++) {
				Node item = n.getAttributes().item(j);
				String nodeName = item.getNodeName();
				String nodeValue = item.getNodeValue();
				dataMap.put(nodeName, nodeValue);
			}
		}
		return dataMap;

	}

	public Map<String, String> getDataByTag1(String tag, int index) {
		if (root == null)
			return null;
		Map<String, String> dataMap = new HashMap<String, String>();
		NodeList nodeList = root.getElementsByTagName(tag);

		Node n = nodeList.item(index);
		for (int j = 0; j < n.getAttributes().getLength(); j++) {
			Node item = n.getAttributes().item(j);
			String nodeName = item.getNodeName();
			String nodeValue = item.getNodeValue();
			dataMap.put(nodeName, nodeValue);
		}
		return dataMap;

	}

}

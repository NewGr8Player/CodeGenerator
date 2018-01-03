package com.xavier.config;

import com.xavier.db.model.Column;
import com.xavier.db.model.util.JavaTypeResolver;
import com.xavier.db.model.util.JdbcTypeResolver;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.Serializable;
import java.util.*;

public class TypeMapping implements Serializable {

	private static final String MAPING_FILE = "TypeMapping.xml";

	private String mappginFile;

	private Map<Integer, String> typeMap;
	private Map<Integer, String> fullTypeMap;

	public TypeMapping(String classPath) {
		this.mappginFile = getClass().getClassLoader().getResource(classPath) + MAPING_FILE;
		typeMap = new HashMap<>();
		fullTypeMap = new HashMap<>();
	}

	public void loadMappgin() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			Document doc = docBuilder.parse(mappginFile);
			Element rootNode = doc.getDocumentElement();

			NodeList nodeList = rootNode.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node childNode = nodeList.item(i);

				if (childNode.getNodeType() != Node.ELEMENT_NODE) {
					continue;
				}

				if ("map".equals(childNode.getNodeName())) { //$NON-NLS-1$
					Properties attrs = parseAttributes(childNode);
					int sqlType = JdbcTypeResolver.getJdbcType(attrs.getProperty("sqlType"));
					typeMap.put(sqlType, attrs.getProperty("javaType"));
					fullTypeMap.put(sqlType, attrs.getProperty("fullJavaType"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Properties parseAttributes(Node node) {
		Properties attributes = new Properties();
		NamedNodeMap nnm = node.getAttributes();
		for (int i = 0; i < nnm.getLength(); i++) {
			Node attribute = nnm.item(i);
			String value = attribute.getNodeValue();
			attributes.put(attribute.getNodeName(), value);
		}

		return attributes;
	}

	public String calculateJavaType(Column column) {
		String javaType = typeMap.get(column.getJdbcType());

		if (javaType == null) {
			javaType = JavaTypeResolver.calculateJavaType(column);
		}
		return javaType;
	}

	public String calculateFullJavaType(Column column) {
		String javaType = fullTypeMap.get(column.getJdbcType());

		if (javaType == null) {
			javaType = JavaTypeResolver.calculateFullJavaType(column);
		}
		return javaType;
	}

	public String[] getAllJavaTypes() {
		Set<String> javaTypeSet = new HashSet<String>();
		javaTypeSet.addAll(typeMap.values());
		if (javaTypeSet.isEmpty()) {
			return JavaTypeResolver.getAllJavaTypes();
		}

		String[] values = new String[javaTypeSet.size()];
		int index = 0;
		for (String itemValue : javaTypeSet) {
			values[index++] = itemValue;
		}
		return values;
	}
}

package xmpp_2;

import org.apache.vysper.xml.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.vysper.xml.fragment.Namespaces;
import org.apache.vysper.xml.fragment.XMLElementVerifier;
import org.apache.vysper.xml.*;

import java.util.Date;

public class XMLElement {
	
	private String namespaceURI;
	private String namespacePrefix;
	private List<Attribute> attributes;
	private Map<String, String> namespaces;
	private List<XMLFragment> innerFragments;
	protected XMLElementVerifier xmlElementVerifier;

	public  XMLElement(String namespaceURI, String name, String namespacePrefix, Attribute[] attributes,
			XMLFragment[] innerFragments) 
	{
			this(namespaceURI, name, namespacePrefix, attributes, innerFragments, null);
	}
	
	
	public XMLElement(String namespaceURI2, String name,
			String namespacePrefix2, Attribute[] attributes2,
			XMLFragment[] innerFragments2, Object object) {
		// TODO Auto-generated constructor stub
	}


	public interface XMLFragment {
	}
	
	
	
	public class Attribute {
		private String namespaceUri;
	
	private String name;
	private String value;
	
	public Attribute(String name, String value) {
	this(null, name, value);
	}
	public Attribute(String namespaceUri, String name, String value) {
		if (name == null)
		throw new IllegalArgumentException("name must not be null");
		if (value == null)
		throw new IllegalArgumentException("value must not be null");
		this.namespaceUri = namespaceUri == null ? Namespaces.DEFAULT_NAMESPACE_URI : namespaceUri;
		this.name = name;
		this.value = value;
		}
		
	public String getName() {
	return name;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getNamespaceUri() {
	return namespaceUri;
	}
	@Override
	public boolean equals(Object o) {
	if (this == o)
	return true;
		if (o == null || !(o instanceof Attribute))
	return false;
	
	final Attribute attribute = (Attribute) o;
	
	if (namespaceUri != null ? !namespaceUri.equals(attribute.namespaceUri) : attribute.namespaceUri != null)
	return false;
	if (name != null ? !name.equals(attribute.name) : attribute.name != null)
	return false;
	if (value != null ? !value.equals(attribute.value) : attribute.value != null)
	return false;
	
	return true;
	}
	
	@Override
	public int hashCode() {
	int result;
	result = (namespaceUri != null ? namespaceUri.hashCode() : 0);
	result = (name != null ? name.hashCode() : 0);
	result = 29 * result + (value != null ? value.hashCode() : 0);
	return result;
	}
	}

}

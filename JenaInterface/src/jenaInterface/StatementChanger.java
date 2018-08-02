package jenaInterface;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class StatementChanger {
	
	private JenaController jc;
	private Resource resource;
	private Property property;
	
	StatementChanger(JenaController jc, String resourceName, String propertyName) {
		this.jc = jc;
		resource = jc.getModel().getResource(resourceName);
		property = jc.getModel().getProperty(propertyName);
	}

	public void changeObject(ObjectChanger objectChanger) {
		objectChanger.apply(jc.getModel().getProperty(resource, property));
		jc.notifyListeners();
	}

}

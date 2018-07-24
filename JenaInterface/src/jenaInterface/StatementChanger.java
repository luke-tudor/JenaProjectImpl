package jenaInterface;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class StatementChanger {
	
	private JenaController ji;
	
	private Resource resource;
	private Property property;
	
	StatementChanger(JenaController ji, String resourceName, String propertyName) {
		this.ji = ji;
		resource = ji.getModel().getResource(resourceName);
		property = ji.getModel().getProperty(propertyName);
	}
	
//	public void changeLiteralObject(boolean newLiteral) {
//		ji.getModel().getProperty(resource, property).changeLiteralObject(newLiteral);
//		ji.updateModel();
//	}

	public void changeLiteralObject(LiteralObjectChanger literalObjectChanger) {
		literalObjectChanger.apply(ji.getModel().getProperty(resource, property));
		ji.updateModel();
	}

}

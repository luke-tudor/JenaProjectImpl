package jenaInterface;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * Used to modify entity property values in the ontology from the monitored
 * program. Uses callback mechanism to convert supplied ObjectChanger objects to
 * changes in the ontology.
 * 
 * @author Luke Tudor
 * @version October 2018
 * @see ObjectChanger
 */
public class StatementChanger {

	private JenaController jc;
	private Resource resource;
	private Property property;

	/**
	 * Internal constructor, should only be accessed from within the monitoring
	 * program.
	 * 
	 * @param jc
	 * @param resourceName
	 * @param propertyName
	 */
	StatementChanger(JenaController jc, String resourceName, String propertyName) {
		this.jc = jc;
		resource = jc.getModel().getResource(resourceName);
		property = jc.getModel().getProperty(propertyName);
	}

	/**
	 * Method used to change ontology entity property values using supplied
	 * ObjectChanger.
	 * 
	 * @param objectChanger
	 * @see ObjectChanger
	 */
	public void changeObject(ObjectChanger objectChanger) {
		objectChanger.apply(jc.getModel().getProperty(resource, property));
		jc.notifyListeners();
	}

}

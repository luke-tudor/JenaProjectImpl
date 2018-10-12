package jenaInterface;

import com.hp.hpl.jena.rdf.model.Statement;

/**
 * Listener interface that should be implemented by monitored programs and
 * registered with JenaController class to receive updates on a specific
 * property on a specific object
 * 
 * @author Luke Tudor
 * @version October 2018
 * @see JenaController
 */
public interface Listener {

	/**
	 * 
	 * @return the name of the resource with ontology prefix.
	 */
	String resourceName();

	/**
	 * 
	 * @return the name of the property with ontology prefix.
	 */
	String propertyName();

	/**
	 * Contains code to be run when an update of the listened object is recieved
	 * from the ontology.
	 * 
	 * @param result - the updated value from the ontology
	 */
	void apply(Statement result);

}

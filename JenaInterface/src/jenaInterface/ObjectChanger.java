package jenaInterface;

import com.hp.hpl.jena.rdf.model.Statement;

/**
 * Callback-like interface used to specify changes to the ontology, used by
 * StatementChanger as an input. Typically implemented by an anonymous class.
 * 
 * @author Luke Tudor
 * @version October 2018
 * @see StatementChanger
 */
public interface ObjectChanger {

	/**
	 * Contains code to be run when changes should be applied.
	 * 
	 * @param property to be updated
	 */
	void apply(Statement property);

}

package jenaInterface;

import com.hp.hpl.jena.rdf.model.Statement;

public interface Listener {
	
	String resourceName();
	
	String propertyName();
	
	void apply(Statement result);

}

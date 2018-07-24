package jenaInterface;

import com.hp.hpl.jena.rdf.model.Statement;

public abstract class Listener {
	
	protected abstract String resourceName();
	
	protected abstract String propertyName();
	
	protected abstract void apply(Statement result);

}

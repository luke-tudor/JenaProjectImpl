package jenaInterface;

import com.hp.hpl.jena.rdf.model.Statement;

public interface ObjectChanger {
	
	void apply(Statement property);

}

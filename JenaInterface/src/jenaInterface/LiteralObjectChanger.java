package jenaInterface;

import com.hp.hpl.jena.rdf.model.Statement;

public interface LiteralObjectChanger {
	
	void apply(Statement property);

}

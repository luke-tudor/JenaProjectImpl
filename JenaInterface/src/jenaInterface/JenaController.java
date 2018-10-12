package jenaInterface;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.riot.RDFDataMgr;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;

/**
 * Main class for program monitor API. Acts as the entry point for all monitored
 * programs. See tests for use patterns.
 * 
 * @author Luke Tudor
 * @version October 2018
 */
public class JenaController {

	private InfModel model;
	private List<Listener> listeners;

	/**
	 * Constructs program monitor instance. Takes ontology model location and rules
	 * files location as strings. Locations are usually absolute file paths or URLs,
	 * but relative file paths should also work.
	 * 
	 * @param modelLocation
	 * @param rulesLocation
	 */
	public JenaController(String modelLocation, String rulesLocation) {
		Model m = RDFDataMgr.loadModel(modelLocation);
		Reasoner reasoner = new GenericRuleReasoner(Rule.rulesFromURL(rulesLocation));
		model = ModelFactory.createInfModel(reasoner, m);
		listeners = new ArrayList<Listener>();
	}

	/**
	 * Creates object used to modify an ontology entity's property object. Takes
	 * resource and property names as strings. Parameters should be prefixed with
	 * the ontology prefix.
	 * 
	 * @param resourceName
	 * @param propertyName
	 * @return StatementChanger
	 */
	public StatementChanger makeStatementChanger(String resourceName, String propertyName) {
		return new StatementChanger(this, resourceName, propertyName);
	}

	/**
	 * Registers a listener object to receive updates when the listened for property
	 * is changed
	 * 
	 * @param listener
	 */
	public void registerStatementListener(Listener listener) {
		listeners.add(listener);
	}

	/**
	 * Internal method used to access ontology model from within the package.
	 * 
	 * @return
	 */
	Model getModel() {
		return model;
	}

	/**
	 * Internal method used to trigger updates to be sent back to the monitored
	 * program.
	 */
	void notifyListeners() {
		for (Listener l : listeners) {
			Statement s = model.getProperty(model.getResource(l.resourceName()), model.getProperty(l.propertyName()));
			l.apply(s);
		}
	}

}

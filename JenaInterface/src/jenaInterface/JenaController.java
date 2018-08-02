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

public class JenaController {
	
	private InfModel model;
	private List<Listener> listeners;

	public JenaController(String absolutePathToModel, String rulesURL) {
		Model m = RDFDataMgr.loadModel(absolutePathToModel);
		Reasoner reasoner = new GenericRuleReasoner(Rule.rulesFromURL(rulesURL));
		model = ModelFactory.createInfModel(reasoner, m);
		listeners = new ArrayList<Listener>();
	}
	
	public StatementChanger makeStatementChanger(String resourceName, String propertyName) {
		return new StatementChanger(this, resourceName, propertyName);
	}
	
	public void registerStatementListener(Listener listener) {
		listeners.add(listener);
	}
	
	Model getModel() {
		return model;
	}

	void notifyListeners() {
		for (Listener l : listeners) {
			Statement s = model.getProperty(model.getResource(l.resourceName()), model.getProperty(l.propertyName()));
			l.apply(s);
		}
	}

}

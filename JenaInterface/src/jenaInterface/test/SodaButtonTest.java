package jenaInterface.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Statement;

import jenaInterface.JenaController;
import jenaInterface.Listener;
import jenaInterface.ObjectChanger;
import jenaInterface.StatementChanger;

public class SodaButtonTest {

	private static final String PREFIX = LightControlSystemSuite.PREFIX;
	private static final String MODEL_LOC = LightControlSystemSuite.MODEL_LOC;
	private static final String RULES_LOC = LightControlSystemSuite.RULES_LOC;

	private JenaController jenaController;
	private StatementChanger sodaChanger;

	@Before
	public void setUp() throws Exception {
		jenaController = new JenaController(MODEL_LOC, RULES_LOC);
		sodaChanger = jenaController.makeStatementChanger(PREFIX + "sodaFridge",
				PREFIX + "hasSodaBottlesRemaining");
		sodaChanger.changeObject(new ObjectChanger() {

			@Override
			public void apply(Statement property) {
				property.changeLiteralObject(0);
			}
			
		});
	}

	@Test
	public void testButton() {
		jenaController.registerStatementListener(new Listener() {

			@Override
			public String resourceName() {
				return PREFIX + "sodaRefillButton";
			}

			@Override
			public String propertyName() {
				return PREFIX + "hasRefill";
			}

			@Override
			public void apply(Statement result) {
				assertFalse(result.getBoolean());
			}

		});
		jenaController.registerStatementListener(new Listener() {

			@Override
			public String resourceName() {
				return PREFIX + "sodaFridge";
			}

			@Override
			public String propertyName() {
				return PREFIX + "hasSodaBottlesRemaining";
			}

			@Override
			public void apply(Statement result) {
				assertEquals(5, result.getInt());
			}

		});
		sodaChanger.changeObject(new ObjectChanger() {

			@Override
			public void apply(Statement property) {
				property.changeLiteralObject(5);
			}
			
		});
		sodaChanger.changeObject(new ObjectChanger() {

			@Override
			public void apply(Statement property) {
				property.changeLiteralObject(0);
			}
			
		});
	}

}

package jenaInterface.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Statement;

import jenaInterface.JenaController;
import jenaInterface.Listener;
import jenaInterface.ObjectChanger;
import jenaInterface.StatementChanger;

public class KitchenTest {

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
	}

	@Test
	public void testNoMoreSoda() {
		jenaController.registerStatementListener(new Listener() {

			@Override
			public String resourceName() {
				return PREFIX + "alert";
			}

			@Override
			public String propertyName() {
				return PREFIX + "hasFridgeMessage";
			}

			@Override
			public void apply(Statement result) {
				assertEquals("All soda has run out!", result.getString());
			}

		});
		sodaChanger.changeObject(new ObjectChanger() {

			@Override
			public void apply(Statement property) {
				property.changeLiteralObject(0);
			}

		});
	}

	@Test
	public void testSoda() {
		jenaController.registerStatementListener(new Listener() {

			@Override
			public String resourceName() {
				return PREFIX + "alert";
			}

			@Override
			public String propertyName() {
				return PREFIX + "hasFridgeMessage";
			}

			@Override
			public void apply(Statement result) {
				assertEquals("", result.getString());
			}

		});
		sodaChanger.changeObject(new ObjectChanger() {

			@Override
			public void apply(Statement property) {
				property.changeLiteralObject(1);
			}

		});
	}

}

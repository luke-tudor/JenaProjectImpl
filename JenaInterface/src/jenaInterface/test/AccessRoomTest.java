package jenaInterface.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Statement;

import jenaInterface.JenaController;
import jenaInterface.Listener;
import jenaInterface.ObjectChanger;
import jenaInterface.StatementChanger;

/**
 * @author Luke Tudor
 * @version October 2018
 * @see LightControlSystemSuite
 *
 */
public class AccessRoomTest {

	private static final String PREFIX = LightControlSystemSuite.PREFIX;
	private static final String MODEL_LOC = LightControlSystemSuite.MODEL_LOC;
	private static final String RULES_LOC = LightControlSystemSuite.RULES_LOC;

	private JenaController jenaController;
	private StatementChanger accessCardChanger;

	@Before
	public void setUp() throws Exception {
		jenaController = new JenaController(MODEL_LOC, RULES_LOC);
		accessCardChanger = jenaController.makeStatementChanger(PREFIX + "person", PREFIX + "hasAccessCard");
	}

	@Test
	public void testAccess() {
		jenaController.registerStatementListener(new Listener() {

			@Override
			public String resourceName() {
				return PREFIX + "accessRoom";
			}

			@Override
			public String propertyName() {
				return PREFIX + "isOpen";
			}

			@Override
			public void apply(Statement result) {
				assertTrue(result.getBoolean());
			}

		});
		accessCardChanger.changeObject(new ObjectChanger() {

			@Override
			public void apply(Statement property) {
				property.changeObject(property.getModel().getResource(PREFIX + "accessCard").asResource());
			}

		});
	}

	@Test
	public void testNoAccess() {
		jenaController.registerStatementListener(new Listener() {

			@Override
			public String resourceName() {
				return PREFIX + "accessRoom";
			}

			@Override
			public String propertyName() {
				return PREFIX + "isOpen";
			}

			@Override
			public void apply(Statement result) {
				assertFalse(result.getBoolean());
			}

		});
		accessCardChanger.changeObject(new ObjectChanger() {

			@Override
			public void apply(Statement property) {
				property.changeObject(property.getModel().getResource(PREFIX + "noCard").asResource());
			}

		});
	}

}

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
public class LightSwitchTest {

	private static final String PREFIX = LightControlSystemSuite.PREFIX;
	private static final String MODEL_LOC = LightControlSystemSuite.MODEL_LOC;
	private static final String RULES_LOC = LightControlSystemSuite.RULES_LOC;

	private JenaController jenaController;
	private StatementChanger lightSwitchChanger;

	@Before
	public void setUp() throws Exception {
		jenaController = new JenaController(MODEL_LOC, RULES_LOC);
		lightSwitchChanger = jenaController.makeStatementChanger(PREFIX + "lightSwitch", PREFIX + "isDeviceTurnedOn");
	}

	@Test
	public void testLightSwitchOn() {
		jenaController.registerStatementListener(new Listener() {

			@Override
			public String resourceName() {
				return PREFIX + "customRoomLight";
			}

			@Override
			public String propertyName() {
				return PREFIX + "isDeviceTurnedOn";
			}

			@Override
			public void apply(Statement result) {
				assertTrue(result.getBoolean());
			}

		});
		lightSwitchChanger.changeObject(new ObjectChanger() {

			@Override
			public void apply(Statement property) {
				property.changeLiteralObject(true);
			}

		});
	}

	@Test
	public void testLightSwitchOff() {
		jenaController.registerStatementListener(new Listener() {

			@Override
			public String resourceName() {
				return PREFIX + "customRoomLight";
			}

			@Override
			public String propertyName() {
				return PREFIX + "isDeviceTurnedOn";
			}

			@Override
			public void apply(Statement result) {
				assertFalse(result.getBoolean());
			}

		});
		lightSwitchChanger.changeObject(new ObjectChanger() {

			@Override
			public void apply(Statement property) {
				property.changeLiteralObject(false);
			}

		});
	}

}

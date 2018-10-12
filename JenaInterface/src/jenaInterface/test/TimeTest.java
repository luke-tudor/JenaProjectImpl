package jenaInterface.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Statement;

import jenaInterface.JenaController;
import jenaInterface.Listener;
import jenaInterface.ObjectChanger;
import jenaInterface.StatementChanger;

/**
 * Used to test the time taken to access the ontology.
 * 
 * @author Luke Tudor
 * @version October 2018
 *
 */
public class TimeTest {

	private static final String PREFIX = LightControlSystemSuite.PREFIX;
	private static final String MODEL_LOC = LightControlSystemSuite.MODEL_LOC;
	private static final String RULES_LOC = LightControlSystemSuite.RULES_LOC;

	private JenaController jenaController;
	private StatementChanger lightSwitchChanger;

	private long endTime;

	@Before
	public void setUp() throws Exception {
		jenaController = new JenaController(MODEL_LOC, RULES_LOC);
		lightSwitchChanger = jenaController.makeStatementChanger(PREFIX + "lightSwitch", PREFIX + "isDeviceTurnedOn");
	}

	@Test
	public void testTime() {
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
				endTime = System.nanoTime();
				assertTrue(result.getBoolean());
			}

		});
		long startTime = System.nanoTime();
		lightSwitchChanger.changeObject(new ObjectChanger() {

			@Override
			public void apply(Statement property) {
				property.changeLiteralObject(true);
			}

		});
		System.out.println((endTime - startTime) / 1000000 + " milliseconds");
	}

}

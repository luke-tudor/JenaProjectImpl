package jenaInterface.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Statement;

import jenaInterface.JenaController;
import jenaInterface.Listener;
import jenaInterface.LiteralObjectChanger;
import jenaInterface.StatementChanger;

public class LightDetectorTest {
	
	private static final String PREFIX = LightControlSystemSuite.PREFIX;
	private static final String MODEL_LOC = LightControlSystemSuite.MODEL_LOC;
	private static final String RULES_LOC = LightControlSystemSuite.RULES_LOC;
	
	private JenaController jenaController;
	private StatementChanger lightDetector;

	@Before
	public void setUp() throws Exception {
		jenaController = new JenaController(MODEL_LOC, RULES_LOC);
		lightDetector = jenaController.makeStatementChanger(PREFIX + "lightDetector", PREFIX + "detectLightIntensity");
	}

	@Test
	public void testLightIntensity() {
		jenaController.registerStatementListener(new Listener() {

			@Override
			protected String resourceName() {
				return PREFIX + "meetingRoomLight";
			}

			@Override
			protected String propertyName() {
				return PREFIX + "lightHasIntensity";
			}

			@Override
			protected void apply(Statement result) {
				assertEquals(60, result.getInt());
			}
			
		});
		lightDetector.changeLiteralObject(new LiteralObjectChanger() {

			@Override
			public void apply(Statement property) {
				property.changeLiteralObject(40);
			}
			
		});
	}
	
}

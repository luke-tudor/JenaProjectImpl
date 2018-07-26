package jenaInterface.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Statement;

import jenaInterface.JenaController;
import jenaInterface.Listener;
import jenaInterface.LiteralObjectChanger;
import jenaInterface.StatementChanger;

public class RestrictedRoomTest {
	
	private static final String PREFIX = LightControlSystemSuite.PREFIX;
	private static final String MODEL_LOC = LightControlSystemSuite.MODEL_LOC;
	private static final String RULES_LOC = LightControlSystemSuite.RULES_LOC;
	
	private JenaController jenaController;
	private StatementChanger restrictedRoomDetector;

	@Before
	public void setUp() throws Exception {
		jenaController = new JenaController(MODEL_LOC, RULES_LOC);
		restrictedRoomDetector = jenaController.makeStatementChanger(PREFIX + "restrictedRoomMotionDetector", PREFIX + "detectMotion");
	}

	@Test
	public void testNotInRestrictedRoom() {
		jenaController.registerStatementListener(new Listener() {

			@Override
			protected String resourceName() {
				return PREFIX + "alert";
			}

			@Override
			protected String propertyName() {
				return PREFIX + "isInRestrictedRoom";
			}

			@Override
			protected void apply(Statement result) {
				assertFalse(result.getBoolean());
			}
			
		});
		restrictedRoomDetector.changeLiteralObject(new LiteralObjectChanger() {

			@Override
			public void apply(Statement property) {
				property.changeLiteralObject(false);
			}
			
		});
	}
	
	@Test
	public void testInRestrictedRoom() {
		jenaController.registerStatementListener(new Listener() {

			@Override
			protected String resourceName() {
				return PREFIX + "alert";
			}

			@Override
			protected String propertyName() {
				return PREFIX + "isInRestrictedRoom";
			}

			@Override
			protected void apply(Statement result) {
				assertTrue(result.getBoolean());
			}
			
		});
		restrictedRoomDetector.changeLiteralObject(new LiteralObjectChanger() {

			@Override
			public void apply(Statement property) {
				property.changeLiteralObject(true);
			}
			
		});
	}
	
}

package main;

import com.hp.hpl.jena.rdf.model.Statement;

import jenaInterface.Listener;
import jenaInterface.LiteralObjectChanger;
import jenaInterface.JenaController;
import jenaInterface.StatementChanger;

public class MainTest {

	public static void main(String[] args) {
		final String PREFIX = "http://www.semanticweb.org/ontologies/2008/9/SensorNetwork_3_4.owl#";
		
		JenaController jenaController = new JenaController("c:/Users/USER/Desktop/softeng700/projectcode/LCS.owl", "file:///C:/Users/USER/Desktop/softeng700/projectcode/lcs-rules.txt");
		
		StatementChanger motionDetector = jenaController.makeStatementChanger(PREFIX + "meetingRoomMotionDetector", PREFIX + "detectMotion");
		
		jenaController.registerStatementListener(new Listener() {

			@Override
			protected String resourceName() {
				return PREFIX + "meetingRoomLight";
			}
			
			@Override
			protected String propertyName() {
				return PREFIX + "isDeviceTurnedOn";
			}
			
			@Override
			protected void apply(Statement result) {
				System.out.println("should light be on? " + result.getBoolean());
			}

		});
		
		jenaController.registerStatementListener(new Listener() {

			@Override
			protected String resourceName() {
				return PREFIX + "lightDetector";
			}
			
			@Override
			protected String propertyName() {
				return PREFIX + "detectLightIntensity";
			}
			
			@Override
			protected void apply(Statement result) {
				System.out.println(result);
			}

		});
		
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
				System.out.println(result);
			}
			
		});
		
		motionDetector.changeLiteralObject(new LiteralObjectChanger() {

			@Override
			public void apply(Statement property) {
				property.changeLiteralObject(false);
			}
			
		});
		motionDetector.changeLiteralObject(new LiteralObjectChanger() {

			@Override
			public void apply(Statement property) {
				property.changeLiteralObject(true);
			}
			
		});
		
		StatementChanger lightDetector = jenaController.makeStatementChanger(PREFIX + "lightDetector", PREFIX + "detectLightIntensity");
		lightDetector.changeLiteralObject(new LiteralObjectChanger() {

			@Override
			public void apply(Statement property) {
				property.changeLiteralObject(40);
			}
			
		});
		
	}
}

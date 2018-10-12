package jenaInterface.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all Java tests for the various rules except for the speed test which is
 * run separately. Ignore all log4j warnings since this program doesn't use
 * log4j.
 * 
 * @author Luke Tudor
 * @version October 2018
 * @see TimeTest
 */

@RunWith(Suite.class)

@Suite.SuiteClasses({ MeetingRoomLightTest.class, LightDetectorTest.class, RestrictedRoomTest.class,
		LightSwitchTest.class, AccessRoomTest.class, KitchenTest.class, SodaButtonTest.class })

public class LightControlSystemSuite {
	public static final String PREFIX = "http://www.semanticweb.org/ontologies/2008/9/SensorNetwork_3_4.owl#";
	public static final String MODEL_LOC = "c:/Users/USER/Desktop/softeng700/projectcode/LCS.owl";
	public static final String RULES_LOC = "file:///C:/Users/USER/Desktop/softeng700/projectcode/lcs-rules.txt";
}
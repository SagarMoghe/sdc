package asdc.hl.statemachineTest.statesTest;

import asdc.hl.statemachine.StateMachine;
import asdc.hl.statemachine.states.DummyState;
import asdc.hl.statemachine.states.State;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DummyStateTest {
    private static State dummyState;

    @BeforeClass
    public static void setup() {
        dummyState = new DummyState(new StateMachine(), 2);
    }

    @Test
    public void onRunTest() {
        assertNull(dummyState.onRun());
    }

    @Test
    public void printSeasonSimulationTest() {
        ByteArrayOutputStream welcomeMessage = new ByteArrayOutputStream();
        System.setOut(new PrintStream(welcomeMessage));
        String expectedOutput = "Simulating the season number: 2\r\n";

        dummyState.onRun();

        assertEquals(expectedOutput, welcomeMessage.toString());
    }
}

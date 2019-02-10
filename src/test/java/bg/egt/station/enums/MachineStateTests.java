package bg.egt.station.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MachineStateTests {

	@Test
	public void nextState_OfLocked_IsPaid() {
		MachineState machineState  = MachineState.LOCKED;
		MachineState expectedState = MachineState.PAID;
		MachineState actualState   = machineState.nextState();
		assertEquals(expectedState, actualState);
	}

	@Test
	public void nextState_OfPaid_IsOpen() {
		MachineState machineState = MachineState.PAID;
		MachineState expectedState = MachineState.OPEN;
		MachineState actualState   = machineState.nextState();
		assertEquals(expectedState, actualState);
	}

	@Test
	public void nextState_OfOpen_IsLocked() {
		MachineState machineState = MachineState.OPEN;
		MachineState expectedState = MachineState.LOCKED;
		MachineState actualState   = machineState.nextState();
		assertEquals(expectedState, actualState);
	}

	@Test
	public void keyUsed_WhenStateIsLocked_IsOpen() {
		MachineState machineState = MachineState.LOCKED;
		MachineState expectedState = MachineState.OPEN;
		MachineState actualState   = machineState.keyUsed();
		assertEquals(expectedState, actualState);
	}
	
	@Test
	public void keyUsed_WhenStateIsPaid_IsOpen() {
		MachineState currentState = MachineState.PAID;
		MachineState expectedState = MachineState.OPEN;
		MachineState actualState   = currentState.keyUsed();
		assertEquals(expectedState, actualState);
	}
	
	@Test
	public void keyUsed_WhenStateIsOpen_IsLocked() {
		MachineState currentState = MachineState.OPEN;
		MachineState expectedState = MachineState.LOCKED;
		MachineState actualState   = currentState.keyUsed();
		assertEquals(expectedState, actualState);
	}
}

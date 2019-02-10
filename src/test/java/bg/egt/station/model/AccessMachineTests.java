package bg.egt.station.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import bg.egt.station.enums.MachineState;

@RunWith(MockitoJUnitRunner.class)
public class AccessMachineTests {

	@Test
	public void emptyConstructor_DefaultMachineState_IsLocked() {
		AccessMachine accessMachine = new AccessMachineImpl();
		MachineState expectedState = MachineState.LOCKED;
		MachineState actualState = accessMachine.getMachineState();
		assertEquals(expectedState, actualState);
	}

	@Test
	public void emptyConstructor_DefaultKeyActive_IsFalse() {
		AccessMachine accessMachine = new AccessMachineImpl();
		boolean keyActive = accessMachine.isKeyActive();
		assertFalse(keyActive);
	}

	@Test
	public void putCoin_WhenKeyNotActiveAndMachineStateLocked_ReturnTrue() {
		AccessMachine accessMachineMock = new AccessMachineImpl().setKeyActive(false)
				.setMachineState(MachineState.LOCKED);
		boolean putCoin = accessMachineMock.putCoin();
		assertTrue(putCoin);
	}

	@Test
	public void putCoin_WhenKeyNotActiveAndMachineStatePaid_ReturnFalse() {
		AccessMachine accessMachineMock = new AccessMachineImpl().setKeyActive(false)
				.setMachineState(MachineState.PAID);
		boolean coinAccepted = accessMachineMock.putCoin();
		assertFalse(coinAccepted);
	}

	@Test
	public void putCoin_WhenKeyNotActiveAndMachineStateOpen_ReturnFalse() {
		AccessMachine accessMachineMock = new AccessMachineImpl().setKeyActive(false)
				.setMachineState(MachineState.OPEN);
		boolean coinAccepted = accessMachineMock.putCoin();
		assertFalse(coinAccepted);
	}

	@Test
	public void putCoin_WhenKeyActiveAndMachineStateLocked_ReturnFalse() {
		AccessMachine accessMachineMock = new AccessMachineImpl().setKeyActive(true)
				.setMachineState(MachineState.LOCKED);
		boolean coinAccepted = accessMachineMock.putCoin();
		assertFalse(coinAccepted);
	}

	@Test
	public void putCoin_WhenKeyActiveAndMachineStatePaid_ReturnFalse() {
		AccessMachine accessMachineMock = new AccessMachineImpl().setKeyActive(true).setMachineState(MachineState.PAID);
		boolean coinAccepted = accessMachineMock.putCoin();
		assertFalse(coinAccepted);
	}

	@Test
	public void putCoin_WhenKeyActiveAndMachineStateOpen_ReturnFalse() {
		AccessMachine accessMachineMock = new AccessMachineImpl().setKeyActive(true).setMachineState(MachineState.OPEN);
		boolean coinAccepted = accessMachineMock.putCoin();
		assertFalse(coinAccepted);
	}

	@Test
	public void canPass_WhenMachineStateLocked_ReturnFalse() {
		AccessMachine accessMachineMock = new AccessMachineImpl().setMachineState(MachineState.LOCKED);
		boolean canPass = accessMachineMock.canPass();
		assertFalse(canPass);
	}

	@Test
	public void canPass_WhenMachineStatePaid_ReturnTrue() {
		AccessMachine accessMachineMock = new AccessMachineImpl().setMachineState(MachineState.PAID);
		boolean canPass = accessMachineMock.canPass();
		assertTrue(canPass);
	}

	@Test
	public void canPass_WhenMachineStateOpen_ReturnTrue() {
		AccessMachine accessMachineMock = new AccessMachineImpl().setMachineState(MachineState.OPEN);
		boolean canPass = accessMachineMock.canPass();
		assertTrue(canPass);
	}

	@Test
	public void key_WhenMachineStateLocked_BecomeOpen() {
		AccessMachine accessMachine = new AccessMachineImpl();
		accessMachine.setMachineState(MachineState.LOCKED);
		MachineState expectedState = MachineState.OPEN;
		accessMachine.key();
		MachineState actualState = accessMachine.getMachineState();
		assertEquals(expectedState, actualState);
	}

	@Test
	public void key_WhenMachineStatePaid_BecomeOpen() {
		AccessMachine accessMachine = new AccessMachineImpl();
		accessMachine.setMachineState(MachineState.PAID);
		MachineState expectedState = MachineState.OPEN;
		accessMachine.key();
		MachineState actualState = accessMachine.getMachineState();
		assertEquals(expectedState, actualState);
	}

	@Test
	public void key_WhenMachineStateOpen_BecomeLocked() {
		AccessMachine accessMachine = new AccessMachineImpl();
		accessMachine.setMachineState(MachineState.OPEN);
		MachineState expectedState = MachineState.LOCKED;
		accessMachine.key();
		MachineState actual = accessMachine.getMachineState();
		assertEquals(expectedState, actual);
	}

	@Test
	public void key_WhenKeyActiveFalse_BecomeTrue() {
		AccessMachine accessMachine = new AccessMachineImpl();
		accessMachine.setKeyActive(false);
		accessMachine.key();
		boolean keyActive = accessMachine.isKeyActive();
		assertTrue(keyActive);
	}

	@Test
	public void key_WhenKeyActiveTrue_BecomeFalse() {
		AccessMachine accessMachine = new AccessMachineImpl();
		accessMachine.setKeyActive(true);
		accessMachine.key();
		boolean keyActive = accessMachine.isKeyActive();
		assertFalse(keyActive);
	}

	@Test
	public void goThrough_WhenKeyActiveAndMachineStateLocked_MachineStateIsLocked() {
		AccessMachine accessMachine = new AccessMachineImpl();
		accessMachine.setKeyActive(true).setMachineState(MachineState.LOCKED);
		MachineState expectedState = MachineState.LOCKED;
		accessMachine.goThrough();
		MachineState actualState = accessMachine.getMachineState();
		assertEquals(expectedState, actualState);
	}

	@Test
	public void goThrough_WhenKeyActiveAndMachineStateOpen_MachineStateIsOpen() {
		AccessMachine accessMachine = new AccessMachineImpl();
		accessMachine.setKeyActive(true).setMachineState(MachineState.OPEN);
		MachineState expectedState = MachineState.OPEN;
		accessMachine.goThrough();
		MachineState actualState = accessMachine.getMachineState();
		assertEquals(expectedState, actualState);
	}

	@Test
	public void goThrough_WhenKeyNotActiveAndMachineStateLocked_MachineStateIsLocked() {
		AccessMachine accessMachine = new AccessMachineImpl();
		accessMachine.setKeyActive(false).setMachineState(MachineState.LOCKED);
		MachineState expectedState = MachineState.LOCKED;
		accessMachine.goThrough();
		MachineState actualState = accessMachine.getMachineState();
		assertEquals(expectedState, actualState);
	}

	@Test
	public void goThrough_WhenKeyNotActiveAndMachineStatePaid_MachineStateIsLocked() {
		AccessMachine accessMachine = new AccessMachineImpl();
		accessMachine.setKeyActive(false).setMachineState(MachineState.PAID);
		MachineState expectedState = MachineState.LOCKED;
		accessMachine.goThrough();
		MachineState actualState = accessMachine.getMachineState();
		assertEquals(expectedState, actualState);
	}
}

package bg.egt.station.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class DoorTests {

	@Test
	public void emptyConstructor_AccessMachine_NotNull() {
		Door door = new Door();
		assertNotNull(door);
	}

	@Test
	public void getAccessMachine_AccessMachine_NotNull() {
		Door door = new Door();
		AccessMachine accessMachine = door.getAccessMachine();
		assertNotNull(accessMachine);
	}

	@Test
	public void goThrough_WhenCantPass_ReturnFalse() {
		Door door = new Door();
		AccessMachine accessMachineMock = mock(AccessMachine.class);
		when(accessMachineMock.canPass()).thenReturn(false);
		door.setAccessMachine(accessMachineMock);
		boolean wentTrough  = door.goThrough();
		assertFalse(wentTrough);
	}
	
	@Test
	public void goThrough_WhenCanPass_ReturnTrue() {
		Door door = new Door();
		AccessMachine accessMachineMock = mock(AccessMachine.class);
		when(accessMachineMock.canPass()).thenReturn(true);
		door.setAccessMachine(accessMachineMock);
		boolean wentTrough = door.goThrough();
		assertTrue(wentTrough);
	}
}

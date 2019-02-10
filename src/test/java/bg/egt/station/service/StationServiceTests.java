package bg.egt.station.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import bg.egt.station.constants.Messages;
import bg.egt.station.enums.Action;
import bg.egt.station.model.AccessMachine;
import bg.egt.station.model.Door;

@RunWith(MockitoJUnitRunner.class)
public class StationServiceTests {
	
	@Mock
	private Door doorMock;
	
	@Mock
	private List<Door> stationDoorsMock;
	
	@Mock
	private AccessMachine accessMachineMock;
	
	@Test
	public void emptyConstructor_doorsList_NotNull() {
		StationService stationService = new StationServiceImpl();
		List<Door> stationDoors = stationService.getStationDoors();
		assertNotNull(stationDoors);
	}

	@Test
	public void emptyConstructor_doorsList_SizeIsOne() {
		StationService stationService = new StationServiceImpl();
		List<Door> stationDoors = stationService.getStationDoors();
		int expectedSize = 1;
		int actualSize = stationDoors.size();
		assertEquals(expectedSize, actualSize);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void getStationDoors_ModifyingList_ThrowsUnsuportedOperationException() {
		StationService stationService = new StationServiceImpl();
		List<Door> stationDoors = stationService.getStationDoors();
		stationDoors.add(new Door());
	}

	@Test
	public void addDoor_doorsListSize_IncrementByOne() {
		StationService stationService = new StationServiceImpl();
		List<Door> stationDoors = stationService.getStationDoors();
		int beforeAddDoorsCount = stationDoors.size();
		stationService.addDoor();
		int expectedSize = beforeAddDoorsCount + 1;
		int actualSize = stationDoors.size();
		assertEquals(expectedSize, actualSize);
	}

	@Test
	public void removeDoor_doorsListSize_decrementByOne() {
		StationService stationService = new StationServiceImpl();
		List<Door> stationDoors = stationService.getStationDoors();
		int beforeAddDoorsCount = stationDoors.size();
		stationService.removeDoor(0);
		int expectedSize = beforeAddDoorsCount - 1;
		int actualSize = stationDoors.size();
		assertEquals(expectedSize, actualSize);
	}

	@Test
	public void processAction_GoThroughPassed_ReturnPassedMessage() {
		StationService stationService = new StationServiceImpl();
		when(this.doorMock.goThrough()).thenReturn(true);
		when(this.stationDoorsMock.get(0)).thenReturn(this.doorMock);
		stationService.setStationDoors(this.stationDoorsMock);
		int doorIndex = 0;
		String expectedMsg = String.format(Messages.GO_THROUGH_PASSED, doorIndex + 1);
		String actualMsg = stationService.processAction(doorIndex, Action.GO_THROUGH);
		assertEquals(expectedMsg, actualMsg);
	}

	@Test
	public void processAction_GoThroughNotPassed_ReturnNotPassedMessage() {
		StationService stationService = new StationServiceImpl();
		when(this.doorMock.goThrough()).thenReturn(false);
		when(this.stationDoorsMock.get(0)).thenReturn(this.doorMock);
		stationService.setStationDoors(this.stationDoorsMock);
		int doorIndex = 0;
		String expectedMsg = String.format(Messages.GO_THROUGH_NOT_PASSED, doorIndex + 1);
		String actualMsg = stationService.processAction(0, Action.GO_THROUGH);
		assertEquals(expectedMsg, actualMsg);
	}

	@Test
	public void processAction_UseKey_ReturnUseKeyMessage() {
		StationService stationService = new StationServiceImpl();
		int doorIndex = 0;
		String expectedMsg = String.format(Messages.USE_KEY, doorIndex + 1);
		String actualMsg = stationService.processAction(0, Action.USE_KEY);
		assertEquals(expectedMsg, actualMsg);
	}

	@Test
	public void processAction_PutCoinAccepted_ReturnCoinAcceptedMessage() {
		StationService stationService = new StationServiceImpl();
		when(this.accessMachineMock.putCoin()).thenReturn(true);
		when(this.doorMock.getAccessMachine()).thenReturn(this.accessMachineMock);
		when(this.stationDoorsMock.get(0)).thenReturn(this.doorMock);
		stationService.setStationDoors(this.stationDoorsMock);
		int doorIndex = 0;
		String expectedMsg = String.format(Messages.PUT_COIN_ACCEPTED, doorIndex + 1);
		String actualMsg = stationService.processAction(0, Action.PUT_COIN);
		assertEquals(expectedMsg, actualMsg);
	}

	@Test
	public void processAction_PutCoinNotAccepted_ReturnCoinNotAcceptedMessage() {
		StationService stationService = new StationServiceImpl();
		when(this.accessMachineMock.putCoin()).thenReturn(false);
		when(this.doorMock.getAccessMachine()).thenReturn(this.accessMachineMock);
		when(this.stationDoorsMock.get(0)).thenReturn(this.doorMock);
		stationService.setStationDoors(this.stationDoorsMock);
		int doorIndex = 0;
		String expectedMsg = String.format(Messages.PUT_COIN_NOT_ACCEPTED, doorIndex + 1);
		String actualMsg = stationService.processAction(0, Action.PUT_COIN);
		assertEquals(expectedMsg, actualMsg);
	}
}

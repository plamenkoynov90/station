package bg.egt.station.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import bg.egt.station.constants.Messages;
import bg.egt.station.enums.Action;
import bg.egt.station.model.Door;

@Service
public class StationServiceImpl implements StationService {

	private List<Door> stationDoors;

	public StationServiceImpl() {
		setStationDoors(new ArrayList<>());
		this.stationDoors.add(new Door());
	}

	@Override
	public List<Door> getStationDoors() {
		return Collections.unmodifiableList(this.stationDoors);
	}

	@Override
	public StationService setStationDoors(List<Door> stationDoors) {
		this.stationDoors = stationDoors;
		return this;
	}

	@Override
	public void addDoor() {
		this.stationDoors.add(new Door());
	}

	@Override
	public void removeDoor(int index) {
		this.stationDoors.remove(index);
	}

	@Override
	public String processAction(int index, Action action) {
		Door door = this.getStationDoors().get(index);
		int doorNumber = index + 1;
		switch (action) {
		case GO_THROUGH:
			return goThroughAction(door, doorNumber);
		case USE_KEY:
			return useKeyAction(door, doorNumber);
		case PUT_COIN:
			return putCoinAction(door, doorNumber);
		default:
			return Messages.NO_SUCH_ACTION;
		}
	}

	private String goThroughAction(Door door, int doorNumber) {
		boolean passed = door.goThrough();
		if (passed) {
			return String.format(Messages.GO_THROUGH_PASSED, doorNumber);
		} else {
			return String.format(Messages.GO_THROUGH_NOT_PASSED, doorNumber);
		}
	}

	private String useKeyAction(Door door, int doorNumber) {
		door.getAccessMachine().key();
		return String.format(Messages.USE_KEY, doorNumber);
	}

	private String putCoinAction(Door door, int doorNumber) {
		boolean coinAccepted = door.getAccessMachine().putCoin();
		if (coinAccepted) {
			return String.format(Messages.PUT_COIN_ACCEPTED, doorNumber);
		} else {
			return String.format(Messages.PUT_COIN_NOT_ACCEPTED, doorNumber);
		}
	}
}

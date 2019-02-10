package bg.egt.station.service;

import java.util.List;

import bg.egt.station.enums.Action;
import bg.egt.station.model.Door;

public interface StationService {
	
	List<Door> getStationDoors();

	StationService setStationDoors(List<Door> stationDoors);
	
	void addDoor();
	
	void removeDoor(int id);
	
	String processAction(int doorId, Action action);

	
}

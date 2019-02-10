package bg.egt.station.model;

import bg.egt.station.enums.MachineState;

public interface AccessMachine {

	MachineState getMachineState();

	AccessMachine setMachineState(MachineState machineState);

	boolean isKeyActive();

	AccessMachine setKeyActive(boolean keyActive);

	boolean putCoin();

	boolean canPass();

	void key();

	void goThrough();

}

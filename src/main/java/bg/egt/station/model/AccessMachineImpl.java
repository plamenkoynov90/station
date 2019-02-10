package bg.egt.station.model;

import bg.egt.station.enums.MachineState;

public class AccessMachineImpl implements AccessMachine {

	private MachineState machineState;
	private boolean keyActive;
	
	public AccessMachineImpl() {
		this.setMachineState(MachineState.LOCKED);
		this.setKeyActive(false);
	}

	@Override
	public MachineState getMachineState() {
		return machineState;
	}

	@Override
	public AccessMachine setMachineState(MachineState machineState) {
		this.machineState = machineState;
		return this;
	}

	@Override
	public boolean isKeyActive() {
		return this.keyActive;
	}

	@Override
	public AccessMachine setKeyActive(boolean keyActive) {
		this.keyActive = keyActive;
		return this;
	}

	@Override
	public boolean putCoin() {
		if (!this.isKeyActive() && this.getMachineState().equals(MachineState.LOCKED)) {
			this.setMachineState(this.getMachineState().nextState());
			return true;
		}
		return false;
	}

	@Override
	public boolean canPass() {
		if (this.getMachineState().equals(MachineState.LOCKED)) {
			return false;
		}
		return true;
	}

	@Override
	public void key() {
		this.setKeyActive(!this.isKeyActive());
		this.setMachineState(this.getMachineState().keyUsed());
	}

	@Override
	public void goThrough() {
		if (!this.keyActive && this.machineState.equals(MachineState.PAID)) {
			this.setMachineState(this.machineState.nextState().nextState());
		}
	}

}

package bg.egt.station.model;

public class Door {

	private AccessMachine accessMachine;

	public Door() {
		this.setAccessMachine(new AccessMachineImpl());
	}

	public AccessMachine getAccessMachine() {
		return accessMachine;
	}

	public Door setAccessMachine(AccessMachine accessMachine) {
		this.accessMachine = accessMachine;
		return this;
	}
	
	public boolean goThrough() {
		if(this.accessMachine.canPass()) {
			this.getAccessMachine().goThrough();
			return true;
		}
		return false;
	}
}

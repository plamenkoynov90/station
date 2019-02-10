package bg.egt.station.enums;

public enum MachineState {
	LOCKED {
		@Override
		public MachineState nextState() {
			return PAID;
		}
	},
	PAID {
		@Override
		public MachineState nextState() {
			return OPEN;
		}
	},
	OPEN {
		@Override
		public MachineState nextState() {
			return LOCKED;
		}
	};

	public abstract MachineState nextState();

	public MachineState keyUsed() {
		if (this.equals(OPEN)) {
			return LOCKED;
		} else {
			return OPEN;
		}
	}
}

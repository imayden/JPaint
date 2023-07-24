package view;

// ydeng24@depaul.edu

public enum EventName {
	CHOOSE_SHAPE{
		@Override
		public String toString() {
			return "CHOOSE SHAPE";
		}
	},
	CHOOSE_PRIMARY_COLOR {
		@Override
		public String toString() {
			return "CHOOSE PRIMARY COLOR";
		}
	},
	CHOOSE_SECONDARY_COLOR {
		@Override
		public String toString() {
			return "CHOOSE SECONDARY COLOR";
		}
	},
	CHOOSE_SHADING_TYPE {
		@Override
		public String toString() {
			return "CHOOSE SHADING TYPE";
		}
	},
	CHOOSE_START_POINT_ENDPOINT_MODE {
		@Override
		public String toString() {
			return "CHOOSE MOUSE MODE";
		}
	},

	UNDO,
	REDO,
	COPY,
	PASTE,
	DELETE,
	GROUP,
	UNGROUP
}

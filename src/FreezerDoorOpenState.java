
/**
 * Represents the state of the microwave when the door is open. When the
 * microwave has its door opened, the run method of this class is called. After
 * that, when an event occurs, the handle method is invoked.
 */
public class FreezerDoorOpenState extends FreezerState
		implements FreezerDoorCloseListener {
	private static FreezerDoorOpenState instance;

	/**
	 * For the singleton pattern
	 * 
	 * @return the object
	 */
	public static FreezerDoorOpenState instance() {
		if (instance == null) {
			instance = new FreezerDoorOpenState();
		}
		return instance;
	}

	/**
	 * Private to make it a singleton
	 */
	private FreezerDoorOpenState() {
	}

	/**
	 * Process door closed event
	 */
	@Override
	public void doorClosed(FreezerDoorCloseEvent event) {
		context.changeCurrentState(FreezerDoorClosedState.instance());
	}

	/**
	 * When the Freezer leaves from this state, this method is called to remove
	 * the state as a listener for the appropriate events.
	 */
	@Override
	public void leave() {
		FreezerDoorCloseManager.instance().removeDoorCloseListener(this);
	}

	/**
	 * Initialize the state
	 */
	@Override
	public void run() {
		FreezerDoorCloseManager.instance().addDoorCloseListener(this);
		display.turnLightOn();
		display.doorOpened();
	}
}
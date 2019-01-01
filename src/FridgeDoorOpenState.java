
/**
 * Represents the state of the microwave when the door is open. When the
 * microwave has its door opened, the run method of this class is called. After
 * that, when an event occurs, the handle method is invoked.
 */
public class FridgeDoorOpenState extends FridgeState
		implements FridgeDoorCloseListener {
	private static FridgeDoorOpenState instance;

	/**
	 * For the singleton pattern
	 * 
	 * @return the object
	 */
	public static FridgeDoorOpenState instance() {
		if (instance == null) {
			instance = new FridgeDoorOpenState();
		}
		return instance;
	}

	/**
	 * Private to make it a singleton
	 */
	private FridgeDoorOpenState() {
	}

	/**
	 * Process door closed event
	 */
	@Override
	public void doorClosed(FridgeDoorCloseEvent event) {
		context.changeCurrentState(FridgeDoorClosedState.instance());

	}

	/**
	 * When the Fridge leaves from this state, this method is called to remove
	 * the state as a listener for the appropriate events.
	 */
	@Override
	public void leave() {
		FridgeDoorCloseManager.instance().removeDoorCloseListener(this);
	}

	/**
	 * Initialize the state
	 */
	@Override
	public void run() {
		FridgeDoorCloseManager.instance().addDoorCloseListener(this);
		display.turnLightOn();
		display.doorOpened();
	}

}
/**
 * Represents the state of the microwave when the cook button has been pressed
 * with the door closed. At that time, the run method of this class is called.
 * After that, when an event occurs, the handle method is invoked.
 *
 */
public class FridgeCoolingState extends FridgeState
		implements FridgeCoolingRequestListener, FridgeDoorOpenListener {
	private static FridgeCoolingState instance;

	/**
	 * For singleton
	 * 
	 * @return the object
	 */
	public static FridgeCoolingState instance() {
		if (instance == null) {
			instance = new FridgeCoolingState();
		}
		return instance;
	}

	/**
	 * Private for the singleton pattern
	 */
	private FridgeCoolingState() {
	}

	@Override
	public void coolRequested(FridgeCoolingRequestEvent event) {
	}

	/**
	 * Process door open request
	 */
	@Override
	public void doorOpened(FridgeDoorOpenEvent event) {
		context.changeCurrentState(FridgeDoorOpenState.instance());
	}

	/**
	 * When the Microwave leaves from this state, this method is called to
	 * remove the state as a listener for the appropriate events.
	 */
	@Override
	public void leave() {
		FridgeCoolingRequestManager.instance()
				.removeCoolingRequestListener(this);
		FridgeDoorOpenManager.instance().removeDoorOpenListener(this);
		display.notCooling();
	}

	/**
	 * Initializes the state Adds itself as a listener to managers Updates the
	 * displays
	 * 
	 */
	@Override
	public void run() {
		FridgeDoorOpenManager.instance().addDoorOpenListener(this);
		FridgeCoolingRequestManager.instance().addCoolingRequestListener(this);
		display.startCooling();
	}

}
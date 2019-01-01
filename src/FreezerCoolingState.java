/**
 * Represents the state of the microwave when the cook button has been pressed
 * with the door closed. At that time, the run method of this class is called.
 * After that, when an event occurs, the handle method is invoked.
 *
 */
public class FreezerCoolingState extends FreezerState
		implements FreezerCoolingRequestListener, FreezerDoorOpenListener {
	private static FreezerCoolingState instance;

	/**
	 * For singleton
	 * 
	 * @return the object
	 */
	public static FreezerCoolingState instance() {
		if (instance == null) {
			instance = new FreezerCoolingState();
		}
		return instance;
	}

	/**
	 * Private for the singleton pattern
	 */
	private FreezerCoolingState() {
	}

	/**
	 * Process Cook request
	 */
	@Override
	public void coolRequested(FreezerCoolingRequestEvent event) {
	}

	/**
	 * Process door open request
	 */
	@Override
	public void doorOpened(FreezerDoorOpenEvent event) {
		context.changeCurrentState(FreezerDoorOpenState.instance());
	}

	/**
	 * When the Microwave leaves from this state, this method is called to
	 * remove the state as a listener for the appropriate events.
	 */
	@Override
	public void leave() {
		FreezerCoolingRequestManager.instance()
				.removeCoolingRequestListener(this);
		FreezerDoorOpenManager.instance().removeDoorOpenListener(this);
		display.notCoolingFreezer();
	}

	/**
	 * Initializes the state Adds itself as a listener to managers Updates the
	 * dosplays
	 * 
	 */
	@Override
	public void run() {
		FreezerDoorOpenManager.instance().addDoorOpenListener(this);
		FreezerCoolingRequestManager.instance().addCoolingRequestListener(this);
		display.startCoolingFreezer();
	}
}
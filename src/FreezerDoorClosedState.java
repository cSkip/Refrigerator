/**
 * Represents the state of the microwave when the door is closed. When the
 * microwave has its door closed, the run method of this class is called. After
 * that, when an event occurs, the handle method is invoked.
 */
public class FreezerDoorClosedState extends FreezerState
		implements FreezerCoolingRequestListener, FreezerDoorOpenListener {
	private static FreezerDoorClosedState instance;

	/**
	 * returns the instance
	 * 
	 * @return this object
	 */
	public static FreezerDoorClosedState instance() {
		if (instance == null) {
			instance = new FreezerDoorClosedState();
		}
		return instance;
	}

	/**
	 * Private to make it a singleton
	 */
	private FreezerDoorClosedState() {
	}

	@Override
	public void coolRequested(FreezerCoolingRequestEvent event) {
		context.changeCurrentState(FreezerCoolingState.instance());
	}

	/**
	 * handle door open event
	 * 
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
				.removeCoolingRequestListener(instance);
		FreezerDoorOpenManager.instance().removeDoorOpenListener(instance);
	}

	/**
	 * initialize the state
	 * 
	 */
	@Override
	public void run() {
		FreezerCoolingRequestManager.instance()
				.addCoolingRequestListener(instance);
		FreezerDoorOpenManager.instance().addDoorOpenListener(instance);
		display.doorClosed();
		display.turnLightOff();
	}
}
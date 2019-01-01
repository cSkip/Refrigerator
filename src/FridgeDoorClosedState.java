/**
 * Represents the state of the fridge when the door is closed. When the fridge
 * has its door closed, the run method of this class is called. After that, when
 * an event occurs, the handle method is invoked.
 */
public class FridgeDoorClosedState extends FridgeState
		implements FridgeCoolingRequestListener, FridgeDoorOpenListener {
	private static FridgeDoorClosedState instance;

	/**
	 * returns the instance
	 * 
	 * @return this object
	 */
	public static FridgeDoorClosedState instance() {
		if (instance == null) {
			instance = new FridgeDoorClosedState();
		}
		return instance;
	}

	/**
	 * Private to make it a singleton
	 */
	private FridgeDoorClosedState() {
	}

	@Override
	public void coolRequested(FridgeCoolingRequestEvent event) {
		context.changeCurrentState(FridgeCoolingState.instance());
	}

	/**
	 * handle door open event
	 * 
	 */
	@Override
	public void doorOpened(FridgeDoorOpenEvent event) {
		context.changeCurrentState(FridgeDoorOpenState.instance());
	}

	/**
	 * When the fridge leaves from this state, this method is called to remove
	 * the state as a listener for the appropriate events.
	 */
	@Override
	public void leave() {
		FridgeCoolingRequestManager.instance()
				.removeCoolingRequestListener(instance);
		FridgeDoorOpenManager.instance().removeDoorOpenListener(instance);
	}

	/**
	 * initialize the state
	 * 
	 */
	@Override
	public void run() {
		FridgeCoolingRequestManager.instance()
				.addCoolingRequestListener(instance);
		FridgeDoorOpenManager.instance().addDoorOpenListener(instance);
		display.doorClosed();
		display.turnLightOff();
	}
}
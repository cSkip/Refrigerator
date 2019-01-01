/**
 * The context is an obserer for the clock and stores the context info for
 * states
 *
 */
public class FreezerContext {
	private static FreezerDisplay freezerDisplay;
	private static FreezerContext instance;

	public static FreezerDisplay freezerDisplay() {
		return freezerDisplay;
	}

	public static FreezerContext getInstance() {
		return instance;
	}

	/**
	 * Return the instance
	 *
	 * @return the object
	 */
	public static FreezerContext instance() {
		if (instance == null) {
			instance = new FreezerContext();
		}
		return instance;
	}

	public static void setFreezerDisplay(FreezerDisplay freezerDisplay) {
		FreezerContext.freezerDisplay = freezerDisplay;
	}

	public static void setInstance(FreezerContext instance) {
		FreezerContext.instance = instance;
	}

	private FreezerState currentState;

	/**
	 * Make it a singleton
	 */
	private FreezerContext() {
		instance = this;
		freezerDisplay = FreezerDisplay.instance();
		currentState = FreezerDoorClosedState.instance();
	}

	/**
	 * Called from the states to change the current state
	 *
	 * @param nextState
	 *            the next state
	 */
	public void changeCurrentState(FreezerState nextState) {
		currentState.leave();
		currentState = nextState;
		nextState.run();
	}

	public FreezerState getCurrentState() {
		return currentState;
	}

	/**
	 * Gets the display
	 *
	 * @return the display
	 */
	public FreezerDisplay getDisplay() {
		return freezerDisplay;
	}

	/**
	 * lets door closed state be the starting state adds the object as an
	 * observable for clock
	 */
	public void initialize() {
		instance.changeCurrentState(FreezerDoorClosedState.instance());
	}

	public void setCurrentState(FreezerState currentState) {
		this.currentState = currentState;
	}
}
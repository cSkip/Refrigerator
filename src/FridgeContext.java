/**
 * The context is an observer for the clock and stores the context info for
 * states
 *
 */
public class FridgeContext {
	private static FridgeDisplay fridgeDisplay;
	private static FridgeContext instance;

	public static FridgeDisplay fridgeDisplay() {
		return fridgeDisplay;
	}

	public static FridgeContext getInstance() {
		return instance;
	}

	/**
	 * Return the instance
	 * 
	 * @return the object
	 */
	public static FridgeContext instance() {
		if (instance == null) {
			instance = new FridgeContext();
		}
		return instance;
	}

	public static void setFridgeDisplay(FridgeDisplay fridgeDisplay) {
		FridgeContext.fridgeDisplay = fridgeDisplay;
	}

	public static void setInstance(FridgeContext instance) {
		FridgeContext.instance = instance;
	}

	private FridgeState currentState;

	/**
	 * Make it a singleton
	 */
	private FridgeContext() {
		instance = this;
		fridgeDisplay = FridgeDisplay.instance();
		currentState = FridgeDoorClosedState.instance();
	}

	/**
	 * Called from the states to change the current state
	 * 
	 * @param nextState
	 *            the next state
	 */
	public void changeCurrentState(FridgeState nextState) {
		currentState.leave();
		currentState = nextState;
		nextState.run();
	}

	public FridgeState getCurrentState() {
		return currentState;
	}

	/**
	 * Gets the display
	 * 
	 * @return the display
	 */
	public FridgeDisplay getDisplay() {
		return fridgeDisplay;
	}

	/**
	 * lets door closed state be the starting state adds the object as an
	 * observable for clock
	 */
	public void initialize() {
		instance.changeCurrentState(FridgeDoorClosedState.instance());
	}

	public void setCurrentState(FridgeState currentState) {
		this.currentState = currentState;
	}
}
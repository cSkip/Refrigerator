/**
 * Super class for all states
 */
public abstract class FridgeState {
	protected static FridgeContext context;
	protected static FridgeDisplay display;

	/**
	 * Initialzies the context and display
	 */
	protected FridgeState() {
		context = FridgeContext.instance();
		display = context.getDisplay();
	}

	/**
	 * When the Microwave leaves from this state, this method is called to
	 * remove the state as a listener for the appropriate events.
	 */
	public abstract void leave();

	/**
	 * Initializes the state
	 */
	public abstract void run();
}

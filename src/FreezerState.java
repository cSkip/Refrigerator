/**
 * Super class for all states
 *
 *
 */
public abstract class FreezerState {
	protected static FreezerContext context;
	protected static FreezerDisplay display;

	/**
	 * Initialzies the context and display
	 */
	protected FreezerState() {
		context = FreezerContext.instance();
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

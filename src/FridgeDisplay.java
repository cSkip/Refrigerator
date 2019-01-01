import java.util.Observable;

/**
 * Specifies what the display system should do. Note that the implementation has
 * a lot of freedom to choose its display.
 */
@SuppressWarnings("deprecation")
public abstract class FridgeDisplay extends Observable {
	protected static FridgeContext context;
	protected static FridgeDisplay instance;

	/**
	 * For singleton
	 * 
	 * @return the object
	 */
	public static FridgeDisplay instance() {
		return instance;
	}

	/**
	 * Initializes the context and instance
	 */
	protected FridgeDisplay() {
		instance = this;
		context = FridgeContext.instance();
	}

	public abstract void displayFridgeTemperature(double fridgeTemperature);

	/**
	 * Indicate that the door is now closed
	 */
	public abstract void doorClosed();

	/**
	 * Indicate that the door is now open
	 */
	public abstract void doorOpened();

	/**
	 * Do the initializations to make the context an observer
	 */
	public void initialize() {
		context.initialize();
	}

	/**
	 * indicate that cooking has ended
	 */
	public abstract void notCooling();

	/**
	 * indicate that cooking has begun
	 */
	public abstract void startCooling();

	/**
	 * Indicate that the light is off
	 */
	public abstract void turnLightOff();

	/**
	 * Indicate that the light is on
	 */
	public abstract void turnLightOn();
}
import java.util.Observable;

/**
 * Implements a clock as a Runnable. Extends Observable to ease communication
 *
 */
@SuppressWarnings("deprecation")
public class Clock extends Observable implements Runnable {
	public enum Events {
		CLOCK_TICKED_EVENT
	}

	private static Clock instance;

	/**
	 * To get the instance
	 * 
	 * @return returns the clock
	 */
	public static Clock instance() {
		if (instance == null) {
			instance = new Clock();
		}
		return instance;
	};

	private Thread thread = new Thread(this);

	/**
	 * Start the thread
	 */
	private Clock() {
		thread.start();
	}

	/**
	 * Infinite loop to generate the clock ticks Notify all users when clock
	 * ticks
	 */
	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(1000);
				setChanged();
				notifyObservers(Events.CLOCK_TICKED_EVENT);
			}
		} catch (InterruptedException ie) {
		}
	}
}

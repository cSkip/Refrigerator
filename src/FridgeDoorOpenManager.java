import java.util.EventListener;

import javax.swing.event.EventListenerList;

/**
 * Orchestrates clicks on the Door Open button. It maintains a list of listeners
 * for the FridgeDoorOpenEvent and invokes their doorOpened method when the
 * button is clicked.
 *
 */
public class FridgeDoorOpenManager {
	private static FridgeDoorOpenManager instance;

	/**
	 * Returns the only instance of the class
	 * 
	 * @return the only instance of the class
	 */
	public static FridgeDoorOpenManager instance() {
		if (instance == null) {
			instance = new FridgeDoorOpenManager();
		}
		return instance;
	}

	private EventListenerList listenerList = new EventListenerList();

	/**
	 * Private to make it a singleton
	 */
	private FridgeDoorOpenManager() {
	}

	/**
	 * Adds a listener
	 * 
	 * @param listener
	 *            an object that wants to listen to the event
	 */
	public void addDoorOpenListener(FridgeDoorOpenListener listener) {
		listenerList.add(FridgeDoorOpenListener.class, listener);
	}

	/**
	 * Handles the request to open the door.
	 * 
	 * @param event
	 *            the CookRequestEvent object
	 */
	public void processEvent(FridgeDoorOpenEvent event) {
		EventListener[] listeners = listenerList
				.getListeners(FridgeDoorOpenListener.class);
		for (int index = 0; index < listeners.length; index++) {
			((FridgeDoorOpenListener) listeners[index]).doorOpened(event);
		}
	}

	/**
	 * Removes a listener
	 * 
	 * @param listener
	 *            the object to be removed
	 */
	public void removeDoorOpenListener(FridgeDoorOpenListener listener) {
		listenerList.remove(FridgeDoorOpenListener.class, listener);
	}
}

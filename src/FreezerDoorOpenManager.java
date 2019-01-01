import java.util.EventListener;

import javax.swing.event.EventListenerList;

/**
 * Orchestrates clicks on the Door Open button. It maintains a list of listeners
 * for the FridgeDoorOpenEvent and invokes their doorOpened method when the
 * button is clicked.
 */
public class FreezerDoorOpenManager {
	private static FreezerDoorOpenManager instance;

	/**
	 * Returns the only instance of the class
	 * 
	 * @return the only instance of the class
	 */
	public static FreezerDoorOpenManager instance() {
		if (instance == null) {
			instance = new FreezerDoorOpenManager();
		}
		return instance;
	}

	private EventListenerList listenerList = new EventListenerList();

	/**
	 * Private to make it a singleton
	 */
	private FreezerDoorOpenManager() {
	}

	/**
	 * Adds a listener
	 * 
	 * @param listener
	 *            an object that wants to listen to the event
	 */
	public void addDoorOpenListener(FreezerDoorOpenListener listener) {
		listenerList.add(FreezerDoorOpenListener.class, listener);
	}

	/**
	 * Handles the request to open the door.
	 * 
	 * @param event
	 *            the CookRequestEvent object
	 */
	public void processEvent(FreezerDoorOpenEvent event) {
		EventListener[] listeners = listenerList
				.getListeners(FreezerDoorOpenListener.class);
		for (int index = 0; index < listeners.length; index++) {
			((FreezerDoorOpenListener) listeners[index]).doorOpened(event);
		}
	}

	/**
	 * Removes a listener
	 * 
	 * @param listener
	 *            the object to be removed
	 */
	public void removeDoorOpenListener(FreezerDoorOpenListener listener) {
		listenerList.remove(FreezerDoorOpenListener.class, listener);
	}
}

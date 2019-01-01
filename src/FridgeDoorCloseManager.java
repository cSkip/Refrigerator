import java.util.EventListener;

import javax.swing.event.EventListenerList;

/**
 * Orchestrates clicks on the Door Close button. It maintains a list of
 * listeners for the FridgeDoorCloseEvent and invokes their doorClosed method
 * when the button is clicked.
 *
 */
public class FridgeDoorCloseManager {
	private static FridgeDoorCloseManager instance;

	/**
	 * Returns the only instance of the class
	 * 
	 * @return the only instance of the class
	 */
	public static FridgeDoorCloseManager instance() {
		if (instance == null) {
			instance = new FridgeDoorCloseManager();
		}
		return instance;
	}

	private EventListenerList listenerList = new EventListenerList();

	/**
	 * Private to make it a singleton
	 */
	private FridgeDoorCloseManager() {
	}

	/**
	 * Adds a listener
	 * 
	 * @param listener
	 *            an object that wants to listen to the event
	 */
	public void addDoorCloseListener(FridgeDoorCloseListener listener) {
		listenerList.add(FridgeDoorCloseListener.class, listener);
	}

	/**
	 * Handles the request to close the door.
	 * 
	 * @param event
	 *            the CookRequestEvent object
	 */
	public void processEvent(FridgeDoorCloseEvent event) {
		EventListener[] listeners = listenerList
				.getListeners(FridgeDoorCloseListener.class);
		for (int index = 0; index < listeners.length; index++) {
			((FridgeDoorCloseListener) listeners[index]).doorClosed(event);
		}
	}

	/**
	 * Removes a listener
	 * 
	 * @param listener
	 *            the object to be removed
	 */
	public void removeDoorCloseListener(FridgeDoorCloseListener listener) {
		listenerList.remove(FridgeDoorCloseListener.class, listener);
	}
}

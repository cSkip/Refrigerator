
import java.util.EventListener;

import javax.swing.event.EventListenerList;

/**
 * Orchestrates clicks on the Door Close button. It maintains a list of
 * listeners for the FridgeDoorCloseEvent and invokes their doorClosed method
 * when the button is clicked.
 */
public class FreezerDoorCloseManager {
	private static FreezerDoorCloseManager instance;

	/**
	 * Returns the only instance of the class
	 * 
	 * @return the only instance of the class
	 */
	public static FreezerDoorCloseManager instance() {
		if (instance == null) {
			instance = new FreezerDoorCloseManager();
		}
		return instance;
	}

	private EventListenerList listenerList = new EventListenerList();

	/**
	 * Private to make it a singleton
	 */
	private FreezerDoorCloseManager() {
	}

	/**
	 * Adds a listener
	 * 
	 * @param listener
	 *            an object that wants to listen to the event
	 */
	public void addDoorCloseListener(FreezerDoorCloseListener listener) {
		listenerList.add(FreezerDoorCloseListener.class, listener);
	}

	/**
	 * Handles the request to close the door.
	 * 
	 * @param event
	 *            the CookRequestEvent object
	 */
	public void processEvent(FreezerDoorCloseEvent event) {
		EventListener[] listeners = listenerList
				.getListeners(FreezerDoorCloseListener.class);
		for (int index = 0; index < listeners.length; index++) {
			((FreezerDoorCloseListener) listeners[index]).doorClosed(event);
		}
	}

	/**
	 * Removes a listener
	 * 
	 * @param listener
	 *            the object to be removed
	 */
	public void removeDoorCloseListener(FreezerDoorCloseListener listener) {
		listenerList.remove(FreezerDoorCloseListener.class, listener);
	}
}

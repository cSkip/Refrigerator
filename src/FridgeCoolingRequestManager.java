import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class FridgeCoolingRequestManager {
	private static FridgeCoolingRequestManager instance;

	/**
	 * Returns the only instance of the class
	 * 
	 * @return the only instance of the class
	 */
	public static FridgeCoolingRequestManager instance() {
		if (instance == null) {
			instance = new FridgeCoolingRequestManager();
		}
		return instance;
	}

	private EventListenerList listenerList = new EventListenerList();

	/**
	 * Private to make it a singleton
	 */
	private FridgeCoolingRequestManager() {
	}

	/**
	 * Adds a listener
	 * 
	 * @param listener
	 *            an object that wants to listen to the event
	 */
	public void addCoolingRequestListener(
			FridgeCoolingRequestListener listener) {
		listenerList.add(FridgeCoolingRequestListener.class, listener);
	}

	/**
	 * Handles the request to cook.
	 * 
	 * @param event
	 *            the CookRequestEvent object
	 */
	public void processEvent(FridgeCoolingRequestEvent event) {
		EventListener[] listeners = listenerList
				.getListeners(FridgeCoolingRequestListener.class);
		for (int index = 0; index < listeners.length; index++) {
			((FridgeCoolingRequestListener) listeners[index])
					.coolRequested(event);
		}
	}

	/**
	 * Removes a listener
	 * 
	 * @param listener
	 *            the object to be removed
	 */
	public void removeCoolingRequestListener(
			FridgeCoolingRequestListener listener) {
		listenerList.remove(FridgeCoolingRequestListener.class, listener);
	}
}

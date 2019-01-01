import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class FreezerCoolingRequestManager {
	private static FreezerCoolingRequestManager instance;

	/**
	 * Returns the only instance of the class
	 * 
	 * @return the only instance of the class
	 */
	public static FreezerCoolingRequestManager instance() {
		if (instance == null) {
			instance = new FreezerCoolingRequestManager();
		}
		return instance;
	}

	private EventListenerList listenerList = new EventListenerList();

	/**
	 * Private to make it a singleton
	 */
	private FreezerCoolingRequestManager() {
	}

	/**
	 * Adds a listener
	 * 
	 * @param listener
	 *            an object that wants to listen to the event
	 */
	public void addCoolingRequestListener(
			FreezerCoolingRequestListener listener) {
		listenerList.add(FreezerCoolingRequestListener.class, listener);
	}

	/**
	 * Handles the request to cook.
	 * 
	 * @param event
	 *            the CookRequestEvent object
	 */
	public void processEvent(FreezerCoolingRequestEvent event) {
		EventListener[] listeners = listenerList
				.getListeners(FreezerCoolingRequestListener.class);
		for (int index = 0; index < listeners.length; index++) {
			((FreezerCoolingRequestListener) listeners[index])
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
			FreezerCoolingRequestListener listener) {
		listenerList.remove(FreezerCoolingRequestListener.class, listener);
	}
}

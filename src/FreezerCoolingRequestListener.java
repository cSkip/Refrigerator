import java.util.EventListener;

public interface FreezerCoolingRequestListener extends EventListener {
	/**
	 * Processes cook requests
	 * 
	 * @param event
	 *            the object that represents the event
	 */
	public void coolRequested(FreezerCoolingRequestEvent event);
}

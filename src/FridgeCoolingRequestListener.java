import java.util.EventListener;

/**
 * Any class may be a CookRequestListener to process cook requests
 */
public interface FridgeCoolingRequestListener extends EventListener {
	/**
	 * Processes cook requests
	 * 
	 * @param event
	 *            the object that represents the event
	 */
	public void coolRequested(FridgeCoolingRequestEvent event);
}

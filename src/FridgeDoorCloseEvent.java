import java.util.EventObject;

/**
 * Represents the door close request
 *
 */
public class FridgeDoorCloseEvent extends EventObject {

	/**
	 * Generated Serial ID.
	 */
	private static final long serialVersionUID = -6924340940633273373L;

	public FridgeDoorCloseEvent(Object source) {
		super(source);
	}
}
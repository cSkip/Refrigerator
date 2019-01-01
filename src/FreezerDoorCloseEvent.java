import java.util.EventObject;

/**
 * Represents the door close request
 *
 */
public class FreezerDoorCloseEvent extends EventObject {

	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = 8809957119434548236L;

	public FreezerDoorCloseEvent(Object source) {
		super(source);
	}
}

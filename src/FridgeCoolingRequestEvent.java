import java.util.EventObject;

public class FridgeCoolingRequestEvent extends EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8673753057779275003L;

	/**
	 * Constructor simply calls the super class's constructor with the supplied
	 * source
	 * 
	 * @param source
	 *            whatever we get
	 */
	public FridgeCoolingRequestEvent(Object source) {
		super(source);
	}

}

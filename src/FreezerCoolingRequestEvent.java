import java.util.EventObject;

public class FreezerCoolingRequestEvent extends EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1086011378680145602L;

	/**
	 * Constructor simply calls the super class's constructor with the supplied
	 * source
	 * 
	 * @param source
	 *            whatever we get
	 */
	public FreezerCoolingRequestEvent(Object source) {
		super(source);
	}

}

/**
 * The button that represents door open
 *
 */
public class FreezerDoorOpenButton extends GUIButton {

	/**
	 * Generated Serial ID.
	 */
	private static final long serialVersionUID = 4662101121414939869L;

	/**
	 * Create the button with the proper display
	 *
	 * @param string
	 *            the text to be put
	 */
	public FreezerDoorOpenButton(String string) {
		super(string);
	}

	/**
	 * Create the FridgeDoorOpenEvent and tell the context that the button has
	 * been clicked.
	 */
	@Override
	public void inform(GUIDisplay source) {
		FreezerDoorOpenManager.instance()
				.processEvent(new FreezerDoorOpenEvent(source));

	}
}
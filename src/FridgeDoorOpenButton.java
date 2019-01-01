/**
 * The button that represents door open
 *
 */
public class FridgeDoorOpenButton extends GUIButton {

	/**
	 * Generated Serial ID.
	 */
	private static final long serialVersionUID = 6146798430916197646L;

	/**
	 * Create the button with the proper display
	 * 
	 * @param string
	 *            the text to be put
	 */
	public FridgeDoorOpenButton(String string) {
		super(string);
	}

	@Override
	public void inform(GUIDisplay source) {
		FridgeDoorOpenManager.instance()
				.processEvent(new FridgeDoorOpenEvent(source));
	}
}
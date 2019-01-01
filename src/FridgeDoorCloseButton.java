/**
 * Represents the door close button
 *
 */
public class FridgeDoorCloseButton extends GUIButton {

	/**
	 * Generated Serial ID.
	 */
	private static final long serialVersionUID = -6792597533337434083L;

	/**
	 * Crates the button with the required label
	 * 
	 * @param string
	 *            the label
	 */
	public FridgeDoorCloseButton(String string) {
		super(string);
	}

	/**
	 * Tell the context to send it to the right listener
	 */
	@Override
	public void inform(GUIDisplay source) {
		FridgeDoorCloseManager.instance()
				.processEvent(new FridgeDoorCloseEvent(source));
	}
}

public class FreezerDoorCloseButton extends GUIButton {

	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = 7264920505860689549L;

	/**
	 * Creates the button with the required label
	 *
	 * @param string
	 *            the label
	 */
	public FreezerDoorCloseButton(String string) {
		super(string);
	}

	/**
	 * Tell the context to send it to the right listener
	 */
	@Override
	public void inform(GUIDisplay source) {
		FreezerDoorCloseManager.instance()
				.processEvent(new FreezerDoorCloseEvent(source));
	}
}
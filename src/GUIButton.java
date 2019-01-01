import javax.swing.JButton;

/**
 * The abstract GUI JButton object. Helps to get rid of conditionals
 *
 */
public abstract class GUIButton extends JButton {

	/**
	 * Generated Serial ID.
	 */
	private static final long serialVersionUID = 8118052829428757523L;

	/**
	 * Create the button with the proper text
	 * 
	 * @param string
	 *            the text
	 */
	public GUIButton(String string) {
		super(string);
	}

	/**
	 * Tell the listener that the button has been clicked.
	 * 
	 * @param context
	 *            the Microwave context
	 * @param guiDisplay
	 *            the GUI
	 */
	public abstract void inform(GUIDisplay guiDisplay);
}
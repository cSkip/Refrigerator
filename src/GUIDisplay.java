import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * This class holds the
 * code for the graphical user interface for simulating a fridge and freezer
 * combo unit. The system loads a config file before proceeding, if the config
 * file fails to load, the program aborts. The user must enter a valid room
 * temperature before anything will happen. After a valid room temperature is
 * entered, the fridge and freezer will begin with their temperatures set to the
 * room temperature. After the user enters a valid desired temperature for the
 * fridge or freezer, and their respective door is closed, the fridge or freezer
 * will begin cooling itself to meet that temperature. If the door is opened at
 * any time, the cooling stops. Note regarding deprecation suppression: Observer
 * and Observable have been depreciated since Java 9, the suppression is just
 * there to keep the IDE from complaining.
 *
 */
public class GUIDisplay implements ActionListener {

	/**
	 * A class to hold the implementations of the FridgeDisplay methods.
	 */
	private class FreezerGUIDisplay extends FreezerDisplay {

		@Override
		public void displayFreezerTemperature(double temperature) {
			DecimalFormat temperatureFormatter = new DecimalFormat("0.000");
			frame.freezerTempLabel.setText("Freezer Temp: "
					+ temperatureFormatter.format(temperature));
		}

		@Override
		public void doorClosed() {
			frame.freezerDoorLabel.setText("Freezer Door Closed");
		}

		@Override
		public void doorOpened() {
			frame.freezerDoorLabel.setText("Freezer Door Open");
		}

		@Override
		public void notCoolingFreezer() {
			frame.freezerCompressorLabel.setText("Freezer Idle");
		}

		@Override
		public void startCoolingFreezer() {
			frame.freezerCompressorLabel.setText("Freezer Cooling");
		}

		@Override
		public void turnLightOff() {
			frame.freezerLightLabel.setText("Fridge Light OFF");
		}

		@Override
		public void turnLightOn() {
			frame.freezerLightLabel.setText("Fridge Light ON");
		}

	}

	/**
	 * A class to hold the GUI elements and set them up.
	 */
	private class FridgeFreezerGUIDisplay extends JFrame {

		/**
		 * Generated Serial ID
		 */
		private static final long serialVersionUID = -7455371078247380568L;

		private JPanel bottomPanel = new JPanel();

		private TitledBorder bottomPanelTitle = BorderFactory
				.createTitledBorder("Status");

		private JPanel contentPanel = new JPanel();

		private Border contentPanelPadding = BorderFactory.createEmptyBorder(30,
				30, 30, 30);

		private JButton desiredFreezerTempButton = new JButton(
				"Set Desired Freezer Temp");

		private JTextField desiredFreezerTempField = new JTextField();

		private JLabel desiredFreezerTempLabel = new JLabel(
				"Desired Freezer Temp:");

		private JButton desiredFridgeTempButton = new JButton(
				"Set Desired Fridge Temp");

		private JTextField desiredFridgeTempField = new JTextField();

		private JLabel desiredFridgeTempLabel = new JLabel(
				"Desired Fridge Temp:");

		private JLabel freezerCompressorLabel = new JLabel("Freezer Idle");

		private GUIButton freezerDoorCloseButton = new FreezerDoorCloseButton(
				"Close Freezer Door");

		private JLabel freezerDoorLabel = new JLabel("Freezer Door Closed");

		private GUIButton freezerDoorOpenButton = new FreezerDoorOpenButton(
				"Open Freezer Door");

		private JLabel freezerLightLabel = new JLabel("Freezer Light OFF");

		private JLabel freezerTempLabel = new JLabel("Freezer Temp: -");

		private JLabel fridgeCompressorLabel = new JLabel("Fridge Idle");

		private GUIButton fridgeDoorCloseButton = new FridgeDoorCloseButton(
				"Close Fridge Door");

		private JLabel fridgeDoorLabel = new JLabel("Fridge Door Closed");

		private GUIButton fridgeDoorOpenButton = new FridgeDoorOpenButton(
				"Open Fridge Door");

		private JLabel fridgeLightLabel = new JLabel("Fridge Light OFF");

		private JLabel fridgeTempLabel = new JLabel("Fridge Temp: -");

		private JPanel middlePanel = new JPanel();

		private JTextField roomTempField = new JTextField();

		private JLabel roomTempLabel = new JLabel("Room Temp:");

		private JButton setRoomTempButton = new JButton("Set Room Temp");

		private JPanel topPanel = new JPanel();

		private FridgeFreezerGUIDisplay() {
			super("Fridge and Freezer");
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			setSize(750, 600);
			setLocationRelativeTo(null);
			getContentPane().setLayout(new GridLayout(1, 1, 50, 50));

			contentPanel.setBorder(contentPanelPadding);
			getContentPane().add(contentPanel);
			contentPanel.setLayout(new GridLayout(3, 1, 50, 50));

			contentPanel.add(topPanel);
			topPanel.setLayout(new GridLayout(3, 3, 50, 25));

			contentPanel.add(middlePanel);
			middlePanel.setLayout(new GridLayout(2, 2, 50, 50));

			bottomPanelTitle.setTitleJustification(TitledBorder.LEFT);
			contentPanel.add(bottomPanel);
			bottomPanel.setBorder(bottomPanelTitle);
			bottomPanel.setLayout(new GridLayout(2, 4, 50, 50));

			topPanel.add(roomTempLabel);
			topPanel.add(roomTempField);
			topPanel.add(setRoomTempButton);
			topPanel.add(desiredFridgeTempLabel);
			topPanel.add(desiredFridgeTempField);
			topPanel.add(desiredFridgeTempButton);
			topPanel.add(desiredFreezerTempLabel);
			topPanel.add(desiredFreezerTempField);
			topPanel.add(desiredFreezerTempButton);

			middlePanel.add(fridgeDoorOpenButton);
			middlePanel.add(fridgeDoorCloseButton);
			middlePanel.add(freezerDoorOpenButton);
			middlePanel.add(freezerDoorCloseButton);

			bottomPanel.add(fridgeLightLabel);
			bottomPanel.add(fridgeDoorLabel);
			bottomPanel.add(fridgeTempLabel);
			bottomPanel.add(fridgeCompressorLabel);

			bottomPanel.add(freezerLightLabel);
			bottomPanel.add(freezerDoorLabel);
			bottomPanel.add(freezerTempLabel);
			bottomPanel.add(freezerCompressorLabel);

			setRoomTempButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					double temperature = Double
							.parseDouble(roomTempField.getText());
					if (temperature <= ConfigProperties.instance().getRoomHigh()
							&& temperature >= ConfigProperties.instance()
									.getRoomLow()) {
						if (ConfigProperties.instance()
								.getRoomTemperature() == -999) {
							ConfigProperties.instance()
									.setTemperatures(temperature);
							JOptionPane.showMessageDialog(null,
									"Room temperature successfully set to: "
											+ temperature);
						} else {
							ConfigProperties.instance()
									.setRoomTemperature(temperature);
							JOptionPane.showMessageDialog(null,
									"Room temperature successfully set to: "
											+ temperature);
						}
					} else if (temperature > ConfigProperties.instance()
							.getRoomHigh()) {
						JOptionPane.showMessageDialog(null,
								"Room temperature not set! Temperature must be less than: "
										+ ConfigProperties.instance()
												.getRoomHigh());
					} else if (temperature < ConfigProperties.instance()
							.getRoomLow()) {
						JOptionPane.showMessageDialog(null,
								"Room temperature not set! Temperature must be greater than: "
										+ ConfigProperties.instance()
												.getRoomLow());
					}
				}
			});

			desiredFridgeTempButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					double temperature = Double
							.parseDouble(desiredFridgeTempField.getText());
					if (temperature <= ConfigProperties.instance()
							.getFridgeHigh()
							&& temperature >= ConfigProperties.instance()
									.getFridgeLow()) {
						ConfigProperties.instance()
								.setFridgeDesiredTemperature(temperature);
						JOptionPane.showMessageDialog(null,
								"Fridge temperature successfully set to: "
										+ temperature);
					} else if (temperature > ConfigProperties.instance()
							.getFridgeHigh()) {
						JOptionPane.showMessageDialog(null,
								"Fridge temperature not set! Temperature must be less than: "
										+ ConfigProperties.instance()
												.getFridgeHigh());
					} else if (temperature < ConfigProperties.instance()
							.getFridgeLow()) {
						JOptionPane.showMessageDialog(null,
								"Fridge temperature not set! Temperature must be greater than: "
										+ ConfigProperties.instance()
												.getFridgeLow());
					}
				}
			});

			desiredFreezerTempButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					double temperature = Double
							.parseDouble(desiredFreezerTempField.getText());
					if (temperature <= ConfigProperties.instance()
							.getFreezerHigh()
							&& temperature >= ConfigProperties.instance()
									.getFreezerLow()) {
						ConfigProperties.instance()
								.setFreezerDesiredTemperature(temperature);
						JOptionPane.showMessageDialog(null,
								"Freezer temperature successfully set to: "
										+ temperature);
					} else if (temperature > ConfigProperties.instance()
							.getFreezerHigh()) {
						JOptionPane.showMessageDialog(null,
								"Freezer temperature not set! Temperature must be less than: "
										+ ConfigProperties.instance()
												.getFreezerHigh());
					} else if (temperature < ConfigProperties.instance()
							.getFreezerLow()) {
						JOptionPane.showMessageDialog(null,
								"Freezer temperature not set! Temperature must be greater than: "
										+ ConfigProperties.instance()
												.getFreezerLow());
					}
				}
			});

			fridgeDoorOpenButton.addActionListener(GUIDisplay.this);
			fridgeDoorCloseButton.addActionListener(GUIDisplay.this);
			freezerDoorOpenButton.addActionListener(GUIDisplay.this);
			freezerDoorCloseButton.addActionListener(GUIDisplay.this);

			setVisible(true);
		}
	}

	private class FridgeGUIDisplay extends FridgeDisplay {

		@Override
		public void displayFridgeTemperature(double temperature) {
			DecimalFormat temperatureFormatter = new DecimalFormat("0.000");
			frame.fridgeTempLabel.setText(
					"Fridge Temp: " + temperatureFormatter.format(temperature));
		}

		@Override
		public void doorClosed() {
			frame.fridgeDoorLabel.setText("Fridge Door Closed");
		}

		@Override
		public void doorOpened() {
			frame.fridgeDoorLabel.setText("Fridge Door Open");
		}

		@Override
		public void notCooling() {
			frame.fridgeCompressorLabel.setText("Fridge Idle");
		}

		@Override
		public void startCooling() {
			frame.fridgeCompressorLabel.setText("Fridge Cooling");
		}

		@Override
		public void turnLightOff() {
			frame.fridgeLightLabel.setText("Fridge Light OFF");
		}

		@Override
		public void turnLightOn() {
			frame.fridgeLightLabel.setText("Fridge Light ON");
		}

	}

	private static FridgeFreezerGUIDisplay frame;

	private static GUIDisplay guiDisplay;

	/**
	 * If the singleton has not yet been created, create it, else return the
	 * instance.
	 * 
	 * @return guiDisplay The singleton instance of GUIDisplay
	 */
	public static GUIDisplay instance() {
		if (guiDisplay == null) {
			return (guiDisplay = new GUIDisplay());
		} else {
			return guiDisplay;
		}
	}

	/**
	 * Main method to start the program. Attempts to initialize the config file,
	 * aborts if it fails.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		if (ConfigProperties.instance().readConfigFile()) {
			GUIDisplay.instance();
		} else {
			JOptionPane.showMessageDialog(null,
					"Error opening properties.config, aborting!");
			return;
		}
	}

	/**
	 * Private constructor for singleton pattern. Initializes the GUI frame, the
	 * Fridge instance, the Freezer instance, the FridgeDisplay, and the
	 * FreezerDisplay.
	 */
	private GUIDisplay() {

		frame = new FridgeFreezerGUIDisplay();

		Fridge.instance();

		Freezer.instance();

		FridgeDisplay fridgeDisplay = new FridgeGUIDisplay();

		fridgeDisplay.initialize();

		FreezerDisplay freezerDisplay = new FreezerGUIDisplay();

		freezerDisplay.initialize();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		((GUIButton) event.getSource()).inform(this);
	}
}

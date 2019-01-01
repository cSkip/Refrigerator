import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class reads in the properties.config file that specifies the
 * specifications in which the fridge and freezer should run. There properties
 * are then held in variables, methods call the appropriate getter and setter
 * methods when interacting with these fields.
 */
public class ConfigProperties {
	/**
	 * A singleton instance of the class.
	 */
	private static ConfigProperties config;

	/**
	 * If the singleton has not yet been created, create it, else return the
	 * instance.
	 * 
	 * @return config The singleton instance of ConfigProperties
	 */
	public static ConfigProperties instance() {
		if (config == null) {
			return (config = new ConfigProperties());
		} else {
			return config;
		}
	}

	/**
	 * A double variable to hold the difference in temperature the freezer
	 * should wait before engaging the compressor and cooling the freezer.
	 */
	private double freezerCompressorStartDiff;

	/**
	 * A double variable to hold the cooling rate of the freezer.
	 */
	private double freezerCoolRate;

	/**
	 * A double variable to hold the user-entered desired temperature of the
	 * freezer. Initialized as a sentinel value of 999.
	 */
	private double freezerDesiredTemperature = 999;

	/**
	 * A double variable to hold the maximum temperature allowed for the
	 * freezer.
	 */
	private double freezerHigh;

	/**
	 * A double variable to hold the minimum temperature allowed for the
	 * freezer.
	 */
	private double freezerLow;

	/**
	 * A double variable to hold the rate in which the freezer loses its
	 * temperature when the freezer door is closed.
	 */
	private double freezerRateLossDoorClosed;

	/**
	 * A double variable to hold the rate in which the freezer loses its
	 * temperature when the freezer door is open.
	 */
	private double freezerRateLossDoorOpen;

	/**
	 * A double variable to hold the difference in temperature the fridge should
	 * wait before engaging the compressor and cooling the fridge.
	 */
	private double fridgeCompressorStartDiff;

	/**
	 * A double variable to hold the cooling rate of the fridge.
	 */
	private double fridgeCoolRate;

	/**
	 * A double variable to hold the user-entered desired temperature of the
	 * fridge. Initialized as a sentinel value of 999.
	 */
	private double fridgeDesiredTemperature = 999;

	/**
	 * A double variable to hold the maximum temperature allowed for the fridge.
	 */
	private double fridgeHigh;

	/**
	 * A double variable to hold the minimum temperature allowed for the fridge.
	 */
	private double fridgeLow;

	/**
	 * A double variable to hold the rate in which the fridge loses its
	 * temperature when the fridge door is closed.
	 */
	private double fridgeRateLossDoorClosed;

	/**
	 * A double variable to hold the rate in which the fridge loses its
	 * temperature when the fridge door is open.
	 */
	private double fridgeRateLossDoorOpen;

	/**
	 * A double variable to hold the maximum temperature allowed for the room
	 * temperature.
	 */
	private double roomHigh;

	/**
	 * A double variable to hold the minimum temperature allowed for the room
	 * temperature.
	 */
	private double roomLow;

	/**
	 * A double variable to hold the user-entered temperature of the room.
	 * Initialized as a sentinel value of -999.
	 */
	private double roomTemperature = -999;

	/**
	 * @return the freezerCompressorStartDiff
	 */
	public double getFreezerCompressorStartDiff() {
		return freezerCompressorStartDiff;
	}

	/**
	 * @return the freezerCoolRate
	 */
	public double getFreezerCoolRate() {
		return freezerCoolRate;
	}

	/**
	 * @return the freezerDesiredTemperature
	 */
	public double getFreezerDesiredTemperature() {
		return freezerDesiredTemperature;
	}

	/**
	 * @return the freezerHigh
	 */
	public double getFreezerHigh() {
		return freezerHigh;
	}

	/**
	 * @return the freezerLow
	 */
	public double getFreezerLow() {
		return freezerLow;
	}

	/**
	 * @return the freezerRateLossDoorClosed
	 */
	public double getFreezerRateLossDoorClosed() {
		return freezerRateLossDoorClosed;
	}

	/**
	 * @return the freezerRateLossDoorOpen
	 */
	public double getFreezerRateLossDoorOpen() {
		return freezerRateLossDoorOpen;
	}

	/**
	 * @return the fridgeCompressorStartDiff
	 */
	public double getFridgeCompressorStartDiff() {
		return fridgeCompressorStartDiff;
	}

	/**
	 * @return the fridgeCoolRate
	 */
	public double getFridgeCoolRate() {
		return fridgeCoolRate;
	}

	/**
	 * @return the fridgeDesiredTemperature
	 */
	public double getFridgeDesiredTemperature() {
		return fridgeDesiredTemperature;
	}

	/**
	 * @return the fridgeHigh
	 */
	public double getFridgeHigh() {
		return fridgeHigh;
	}

	/**
	 * @return the fridgeLow
	 */
	public double getFridgeLow() {
		return fridgeLow;
	}

	/**
	 * @return the fridgeRateLossDoorClosed
	 */
	public double getFridgeRateLossDoorClosed() {
		return fridgeRateLossDoorClosed;
	}

	/**
	 * @return the fridgeRateLossDoorOpen
	 */
	public double getFridgeRateLossDoorOpen() {
		return fridgeRateLossDoorOpen;
	}

	/**
	 * @return the roomHigh
	 */
	public double getRoomHigh() {
		return roomHigh;
	}

	/**
	 * @return the roomLow
	 */
	public double getRoomLow() {
		return roomLow;
	}

	/**
	 * @return the roomTemperature
	 */
	public double getRoomTemperature() {
		return roomTemperature;
	}

	/**
	 * Reads the properties.config file, which should be located in the root of
	 * the project/package. Then loads the properties contained in the file to
	 * their respective variables. The variables are named identically to their
	 * properties for ease of identification. If the file is successfully
	 * loaded, the method returns true.
	 * 
	 * @return true if file load was successful
	 */
	public boolean readConfigFile() {

		Properties configProperties = new Properties();

		try {
			InputStream fileInput = new FileInputStream("properties.config");
			configProperties.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		this.setFreezerCompressorStartDiff(Double.parseDouble(
				configProperties.getProperty("FreezerCompressorStartDiff")));
		this.setFreezerCoolRate(Double
				.parseDouble(configProperties.getProperty("FreezerCoolRate")));
		this.setFreezerHigh(Double
				.parseDouble(configProperties.getProperty("FreezerHigh")));
		this.setFreezerLow(
				Double.parseDouble(configProperties.getProperty("FreezerLow")));
		this.setFreezerRateLossDoorClosed(Double.parseDouble(
				configProperties.getProperty("FreezerRateLossDoorClosed")));
		this.setFreezerRateLossDoorOpen(Double.parseDouble(
				configProperties.getProperty("FreezerRateLossDoorOpen")));
		this.setFridgeCompressorStartDiff(Double.parseDouble(
				configProperties.getProperty("FridgeCompressorStartDiff")));
		this.setFridgeHigh(
				Double.parseDouble(configProperties.getProperty("FridgeHigh")));
		this.setFridgeLow(
				Double.parseDouble(configProperties.getProperty("FridgeLow")));
		this.setFridgeRateLossDoorClosed(Double.parseDouble(
				configProperties.getProperty("FridgeRateLossDoorClosed")));
		this.setFridgeRateLossDoorOpen(Double.parseDouble(
				configProperties.getProperty("FridgeRateLossDoorOpen")));
		this.setFridgeCoolRate(Double
				.parseDouble(configProperties.getProperty("FridgeCoolRate")));
		this.setRoomHigh(
				Double.parseDouble(configProperties.getProperty("RoomHigh")));
		this.setRoomLow(
				Double.parseDouble(configProperties.getProperty("RoomLow")));

		return true;
	}

	/**
	 * @param freezerCompressorStartDiff
	 *            the freezerCompressorStartDiff to set
	 */
	public void setFreezerCompressorStartDiff(
			double freezerCompressorStartDiff) {
		this.freezerCompressorStartDiff = freezerCompressorStartDiff;
	}

	/**
	 * @param freezerCoolRate
	 *            the freezerCoolRate to set
	 */
	public void setFreezerCoolRate(double freezerCoolRate) {
		this.freezerCoolRate = freezerCoolRate;
	}

	/**
	 * @param freezerDesiredTemperature
	 *            the freezerDesiredTemperature to set
	 */
	public void setFreezerDesiredTemperature(double freezerDesiredTemperature) {
		this.freezerDesiredTemperature = freezerDesiredTemperature;
	}

	/**
	 * @param freezerHigh
	 *            the freezerHigh to set
	 */
	public void setFreezerHigh(double freezerHigh) {
		this.freezerHigh = freezerHigh;
	}

	/**
	 * @param freezerLow
	 *            the freezerLow to set
	 */
	public void setFreezerLow(double freezerLow) {
		this.freezerLow = freezerLow;
	}

	/**
	 * @param freezerRateLossDoorClosed
	 *            the freezerRateLossDoorClosed to set
	 */
	public void setFreezerRateLossDoorClosed(double freezerRateLossDoorClosed) {
		this.freezerRateLossDoorClosed = freezerRateLossDoorClosed;
	}

	/**
	 * @param freezerRateLossDoorOpen
	 *            the freezerRateLossDoorOpen to set
	 */
	public void setFreezerRateLossDoorOpen(double freezerRateLossDoorOpen) {
		this.freezerRateLossDoorOpen = freezerRateLossDoorOpen;
	}

	/**
	 * @param fridgeCompressorStartDiff
	 *            the fridgeCompressorStartDiff to set
	 */
	public void setFridgeCompressorStartDiff(double fridgeCompressorStartDiff) {
		this.fridgeCompressorStartDiff = fridgeCompressorStartDiff;
	}

	/**
	 * @param fridgeCoolRate
	 *            the fridgeCoolRate to set
	 */
	public void setFridgeCoolRate(double fridgeCoolRate) {
		this.fridgeCoolRate = fridgeCoolRate;
	}

	/**
	 * @param fridgeDesiredTemperature
	 *            the fridgeDesiredTemperature to set
	 */
	public void setFridgeDesiredTemperature(double fridgeDesiredTemperature) {
		this.fridgeDesiredTemperature = fridgeDesiredTemperature;
	}

	/**
	 * @param fridgeHigh
	 *            the fridgeHigh to set
	 */
	public void setFridgeHigh(double fridgeHigh) {
		this.fridgeHigh = fridgeHigh;
	}

	/**
	 * @param fridgeLow
	 *            the fridgeLow to set
	 */
	public void setFridgeLow(double fridgeLow) {
		this.fridgeLow = fridgeLow;
	}

	/**
	 * @param fridgeRateLossDoorClosed
	 *            the fridgeRateLossDoorClosed to set
	 */
	public void setFridgeRateLossDoorClosed(double fridgeRateLossDoorClosed) {
		this.fridgeRateLossDoorClosed = fridgeRateLossDoorClosed;
	}

	/**
	 * @param fridgeRateLossDoorOpen
	 *            the fridgeRateLossDoorOpen to set
	 */
	public void setFridgeRateLossDoorOpen(double fridgeRateLossDoorOpen) {
		this.fridgeRateLossDoorOpen = fridgeRateLossDoorOpen;
	}

	/**
	 * @param roomHigh
	 *            the roomHigh to set
	 */
	public void setRoomHigh(double roomHigh) {
		this.roomHigh = roomHigh;
	}

	/**
	 * @param roomLow
	 *            the roomLow to set
	 */
	public void setRoomLow(double roomLow) {
		this.roomLow = roomLow;
	}

	/**
	 * @param roomTemperature
	 *            the roomTemperature to set
	 */
	public void setRoomTemperature(double roomTemperature) {
		this.roomTemperature = roomTemperature;
	}

	/**
	 * Method to set the room temperature, fridge temperature, and freezer
	 * temperature. This is done when room temperature is first changed from its
	 * sentinel value.
	 * 
	 * @param roomTemperature
	 *            The temperature to set roomTemperature, fridgeTemperature, and
	 *            freezerTemperature
	 */
	public void setTemperatures(double roomTemperature) {
		this.roomTemperature = roomTemperature;
		Fridge.instance().setFridgeTemperature(roomTemperature);
		Freezer.instance().setFreezerTemperature(roomTemperature);
	}
}
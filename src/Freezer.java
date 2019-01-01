import java.util.Observable;
import java.util.Observer;

/**
 * This class contains the logic to observe the clock and update the freezer's
 * temperature and change the freezer states as needed according to the current
 * freezer temperature and the freezer door state. Holds the field for the
 * freezer's current temperature.
 */
@SuppressWarnings("deprecation")
public class Freezer implements Observer {
	/**
	 * A singleton instance of the class.
	 */
	private static Freezer freezer;

	/**
	 * If the singleton has not yet been created, create it, else return the
	 * instance.
	 * 
	 * @return freezer The singleton instance of Freezer
	 */
	public static Freezer instance() {
		if (freezer == null) {
			return (freezer = new Freezer());
		} else {
			return freezer;
		}
	}

	/**
	 * Double variable to hold the current temperature of the freezer.
	 */
	private double freezerTemperature;

	/**
	 * Private constructor for the singleton pattern. Initializes the freezer
	 * instance, then passes it to the Clock instance to observe it whenever the
	 * clock ticks.
	 */
	private Freezer() {
		freezer = this;
		Clock.instance().addObserver(freezer);
		this.freezerTemperature = ConfigProperties.instance().getFreezerLow();
	}

	/**
	 * @return the freezerTemperature
	 */
	public double getFreezerTemperature() {
		return freezerTemperature;
	}

	/**
	 * @param freezerTemperature
	 *            the freezerTemperature to set
	 */
	public void setFreezerTemperature(double freezerTemperature) {
		this.freezerTemperature = freezerTemperature;
	}

	/**
	 * Method to observe the clock each time it ticks and update the freezer's
	 * temperature and states as necessary.
	 * 
	 * @param clock
	 * @param value
	 */
	@Override
	public void update(Observable clock, Object value) {

		if (ConfigProperties.instance().getRoomTemperature() != -999) {

			FreezerDisplay.instance()
					.displayFreezerTemperature(this.getFreezerTemperature());

			if (FreezerContext.instance().getCurrentState()
					.equals(FreezerDoorOpenState.instance())) {
				if (this.getFreezerTemperature() < ConfigProperties.instance()
						.getRoomTemperature()) {
					this.setFreezerTemperature(
							this.getFreezerTemperature() + (1 / ConfigProperties
									.instance().getFreezerRateLossDoorOpen()));
					FreezerDisplay.instance().displayFreezerTemperature(
							this.getFreezerTemperature());
				}
			}

			if (FreezerContext.instance().getCurrentState()
					.equals(FreezerDoorClosedState.instance())) {
				if (this.getFreezerTemperature() < ConfigProperties.instance()
						.getRoomTemperature()) {
					this.setFreezerTemperature(this.getFreezerTemperature()
							+ (1.0 / ConfigProperties.instance()
									.getFreezerRateLossDoorClosed()));
					FreezerDisplay.instance().displayFreezerTemperature(
							this.getFreezerTemperature());
				}
			}

			if (FreezerContext.instance().getCurrentState()
					.equals(FreezerCoolingState.instance())) {
				this.setFreezerTemperature(this.getFreezerTemperature() - (1.0
						/ ConfigProperties.instance().getFreezerCoolRate()));
				FreezerDisplay.instance().displayFreezerTemperature(
						this.getFreezerTemperature());

				if (this.getFreezerTemperature() <= ConfigProperties.instance()
						.getFreezerDesiredTemperature()) {
					FreezerContext.instance().changeCurrentState(
							FreezerDoorClosedState.instance());
				}
			}

			if (this.getFreezerTemperature() >= (ConfigProperties.instance()
					.getFreezerDesiredTemperature()
					+ ConfigProperties.instance()
							.getFreezerCompressorStartDiff())) {
				if (FreezerContext.instance().getCurrentState()
						.equals(FreezerDoorClosedState.instance())) {
					FreezerContext.instance()
							.changeCurrentState(FreezerCoolingState.instance());
				}

			}
		}
	}
}

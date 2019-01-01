import java.util.Observable;
import java.util.Observer;

/**
 * This class contains the logic to update the fridge's temperature and change
 * the fridge states as needed according to the current fridge temperature and
 * the fridge door state. Hold the field for the fridge's current temperature.
 */
@SuppressWarnings("deprecation")
public class Fridge implements Observer {

	private static Fridge fridge;

	public static Fridge instance() {
		if (fridge == null) {
			return (fridge = new Fridge());
		} else {
			return fridge;
		}
	}

	private double fridgeTemperature;

	private Fridge() {
		fridge = this;
		Clock.instance().addObserver(fridge);
		this.fridgeTemperature = ConfigProperties.instance().getFridgeLow();
	}

	/**
	 * @return the fridgeTemperature
	 */
	public double getFridgeTemperature() {
		return fridgeTemperature;
	}

	/**
	 * @param fridgeTemperature
	 *            the fridgeTemperature to set
	 */
	public void setFridgeTemperature(double fridgeTemperature) {
		this.fridgeTemperature = fridgeTemperature;
	}

	@Override
	public void update(Observable clock, Object value) {

		if (ConfigProperties.instance().getRoomTemperature() != -999) {

			FridgeDisplay.instance()
					.displayFridgeTemperature(this.getFridgeTemperature());

			if (FridgeContext.instance().getCurrentState()
					.equals(FridgeDoorOpenState.instance())) {
				if (this.getFridgeTemperature() < ConfigProperties.instance()
						.getRoomTemperature()) {
					this.setFridgeTemperature(
							this.getFridgeTemperature() + (1 / ConfigProperties
									.instance().getFridgeRateLossDoorOpen()));
					FridgeDisplay.instance().displayFridgeTemperature(
							this.getFridgeTemperature());
				}
			}

			if (FridgeContext.instance().getCurrentState()
					.equals(FridgeDoorClosedState.instance())) {
				if (this.getFridgeTemperature() < ConfigProperties.instance()
						.getRoomTemperature()) {
					this.setFridgeTemperature(this.getFridgeTemperature()
							+ (1.0 / ConfigProperties.instance()
									.getFridgeRateLossDoorClosed()));
					FridgeDisplay.instance().displayFridgeTemperature(
							this.getFridgeTemperature());
				}
			}

			if (FridgeContext.instance().getCurrentState()
					.equals(FridgeCoolingState.instance())) {
				this.setFridgeTemperature(this.getFridgeTemperature() - (1.0
						/ ConfigProperties.instance().getFridgeCoolRate()));
				FridgeDisplay.instance()
						.displayFridgeTemperature(this.getFridgeTemperature());

				if (this.getFridgeTemperature() <= ConfigProperties.instance()
						.getFridgeDesiredTemperature()) {
					FridgeContext.instance().changeCurrentState(
							FridgeDoorClosedState.instance());
				}
			}

			if (this.getFridgeTemperature() >= (ConfigProperties.instance()
					.getFridgeDesiredTemperature()
					+ ConfigProperties.instance()
							.getFridgeCompressorStartDiff())) {
				if (FridgeContext.instance().getCurrentState()
						.equals(FridgeDoorClosedState.instance())) {
					FridgeContext.instance()
							.changeCurrentState(FridgeCoolingState.instance());
				}

			}
		}
	}
}

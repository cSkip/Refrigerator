
import java.util.EventListener;

public interface FreezerDoorCloseListener extends EventListener {
	public void doorClosed(FreezerDoorCloseEvent event);
}

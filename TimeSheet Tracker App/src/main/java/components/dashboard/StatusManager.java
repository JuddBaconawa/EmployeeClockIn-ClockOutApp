package components.dashboard;

import java.util.ArrayList;
import java.util.List;

public class StatusManager {


    // holds all status light indicators
    private List<StatusIndicator> indicators = new ArrayList<>();

    // The app starts with the employee clocked out
    private String currentStatus = "out";

    // a card calls this once to connect its own light
    public void register(StatusIndicator indicator) {
        indicators.add(indicator);

        indicator.setStatus(currentStatus);
    }

    public void updateStatus(String status) {

        currentStatus = status;

        for (StatusIndicator indicator : indicators) {
          indicator.setStatus(status);
        }

    }



}

package components.dashboard;

public interface TimeUpdateListener {

  // Interface for components that need to update when time changes (e.g., ClockPanel updates TimeGraphPanel)
    void onTimeUpdate();
  
}

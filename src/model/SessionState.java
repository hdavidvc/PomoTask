package model;

public class SessionState {

    public int pomodorosCompleted;
    int timeRemaining;
    public Boolean isPaused = false;
    public Boolean isRunning = false;
    public String actualPhase = PomodoroPhase.Work.name();

    public int getTimeRemaining() {
        return timeRemaining * 60;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }
}

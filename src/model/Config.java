package model;

public class Config {
    private int workDuration;
    private int shortBreakDuration;
    private int longBreakDuration ;
    private int countBeforeLongBreak;

    public int getWorkDuration() {
        return workDuration;
    }

    public int getShortBreakDuration() {
        return shortBreakDuration;
    }

    public int getLongBreakDuration() {
        return longBreakDuration;
    }

    public int getCountBeforeLongBreak() {
        return countBeforeLongBreak;
    }

    private void setWorkDuration(int workDuration) {
        this.workDuration = workDuration;
    }

    private void setShortBreakDuration(int shortBreakDuration) {
        this.shortBreakDuration = shortBreakDuration;
    }

    private void setLongBreakDuration(int longBreakDuration) {
        this.longBreakDuration = longBreakDuration;
    }

    private void setCountBeforeLongBreak(int countBeforeLongBreak) {
        this.countBeforeLongBreak = countBeforeLongBreak;
    }

    public void updateConfFromUser(int workTime, int shortBreak, int longBreak, int countPomodoros) {
        setWorkDuration(workTime);
        setShortBreakDuration(shortBreak);
        setLongBreakDuration(longBreak);
        setCountBeforeLongBreak(countPomodoros);
    }
    public void updateConfDefault(int workTime, int shortBreak, int longBreak, int countPomodoros) {
        setWorkDuration(workTime);
        setShortBreakDuration(shortBreak);
        setLongBreakDuration(longBreak);
        setCountBeforeLongBreak(countPomodoros);
    }
}

package util;

public class PomoUtil {


    public void cleanConsole() {
        for (int i = 0; i < 4; i++) {
            System.out.println();
        }
    }

    public String formatTime(int timeRemaining) {
        int minutes = timeRemaining / 60;
        int seconds = timeRemaining % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

    public void menuPomodoro(){
        System.out.println("\n🎯 Pomodoro Iniciado ");

        System.out.println("Comandos disponibles:");
        System.out.println("- pause   → Pausar");
        System.out.println("- resume  → Reanudar");
        System.out.println("- cancel  → Cancelar");
        System.out.println("- exit    → Salir del programa");
    }

}

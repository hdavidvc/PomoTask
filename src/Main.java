import controller.PomodoroController;
import model.Config;
import util.PomoUtil;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int keIn;
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        Config config = new Config();
        PomoUtil util = new PomoUtil();
        PomodoroController pomodoro = new PomodoroController(config);
        do {
            System.out.println("Bienvenidos a PomoTask");
            System.out.println("Selecciona una opcion del menu:");
            System.out.println("1- Iniciar Pomodoro");
            System.out.println("2- Configurar Pomodoro");
            System.out.println("3- Salir\n");

            keIn = scanner.nextInt();
            util.cleanConsole();

            switch (keIn) {
                case 1:
                    if (config.getWorkDuration() <= 0) config.updateConfDefault(25,5,10,3);
                    pomodoro.startCycle();
                    System.out.println();
                    exit = true;
                    break;
                case 2:
                    System.out.println("Tiempo de trabajo:");
                    int workDuration = scanner.nextInt();
                    System.out.println("Tiempo de descanso corto:");
                    int shortBreakDuration = scanner.nextInt();
                    System.out.println("Tiempo de descanso largo:");
                    int longBreakDuration = scanner.nextInt();
                    System.out.println("Cantidad de ciclos para tipo de descanso:");
                    int countBeforeLongBreak = scanner.nextInt();
                    config.updateConfFromUser(workDuration,shortBreakDuration,longBreakDuration,countBeforeLongBreak);
                    break;
                case 3:
                    exit = true;
                    util.cleanConsole();
                    System.out.println("!!!Adios!!!");
                    break;
            }
        }while (!exit);


    }


}
package controller;

import model.Config;
import model.PomodoroPhase;
import model.SessionState;
import util.PomoUtil;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PomodoroController {

    private static ScheduledExecutorService scheduler;
    private static final SessionState state = new SessionState();
    private static Config config = null;
    private static final PomoUtil util = new PomoUtil();

    private static int timeRemaining;


    public PomodoroController(Config config) {
        PomodoroController.config = config;
    }

    public void startCycle() {
        if (state.isRunning) return;

        SwitchTimeRemaining();

        scheduler = Executors.newSingleThreadScheduledExecutor();
        timeRemaining = state.getTimeRemaining();

        Runnable task = () -> {
          if (!state.isPaused) {
              state.isRunning = true;
              if (timeRemaining <= 0) {
                  System.out.print("\r⏰ ¡Tiempo "+ "de "+ state.actualPhase+" completado!          \n");

                  if (state.actualPhase.equals(PomodoroPhase.Work.name()))  state.pomodorosCompleted++;
                  scheduler.shutdown();
                  state.isRunning = false;
                  nextPhase();
                  return;
              }
              System.out.print("\rFase Actual: " + state.actualPhase+ " - ⏳ Tiempo restante: " + util.formatTime(timeRemaining)+ " > ");
              timeRemaining--;
          }
        };

        scheduler.scheduleAtFixedRate(task,0,1, TimeUnit.SECONDS);

        // Hilo 2: Entrada de usuario
        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);

            while (state.isRunning) {
                String action = scanner.nextLine().trim().toLowerCase();

                System.out.print("> ");
                phaseAccion(action);
            }
            scanner.close();

        });

        inputThread.setDaemon(true);
        inputThread.start();

    }

    public static void phaseAccion(String action) {
        action = action.trim().toLowerCase();

        switch (action) {
            case "pause":
                pause();
                break;
            case "resume":
                resume();
                break;
            case "cancel":
                cancel();
                break;
            case "exit":
                cancel();
                System.out.println("👋 Saliendo...");
                break;
            default:
                System.out.println("⚠️ Comando no reconocido.");
        }

    }

    public static void pause() {
        state.isPaused = true;
        System.out.println("⏸️ Temporizador en pausa.");
    }

    public static void resume() {
        state.isPaused = false;
        System.out.println("▶️ Temporizador reanudado.");
    }

    public static void cancel() {
        state.isRunning = false;
        scheduler.shutdownNow();
        System.out.println("❌ Temporizador cancelado.");
    }

    public void nextPhase() {
        if (state.pomodorosCompleted ==  config.getCountBeforeLongBreak() && state.actualPhase.equals(PomodoroPhase.Work.name())){
            state.actualPhase = PomodoroPhase.Long_Break.name();
            state.pomodorosCompleted = 0;
        } else if (state.actualPhase.equals(PomodoroPhase.Long_Break.name()) || state.actualPhase.equals(PomodoroPhase.Short_Break.name())){
            state.actualPhase = PomodoroPhase.Work.name();
        } else {
            state.actualPhase = PomodoroPhase.Short_Break.name();
        }

        startCycle();
    }

    public static void SwitchTimeRemaining() {

        if (state.actualPhase.equals(PomodoroPhase.Work.name())){
            state.setTimeRemaining(config.getWorkDuration());
            util.menuPomodoro();
        }
        else if (state.actualPhase.equals(PomodoroPhase.Long_Break.name())){
            state.setTimeRemaining(config.getLongBreakDuration());
        }
        else {
            state.setTimeRemaining(config.getShortBreakDuration());
        }
    }
}

import java.text.SimpleDateFormat;
import java.util.Date;

// Class responsible for displaying the current time and date
class TimeDisplay implements Runnable {
    private boolean continueRunning = true;

    @Override
    public void run() {
        while (continueRunning) {
            try {
                // Format for displaying date and time
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
                String formattedDate = formatter.format(new Date());
                System.out.println("Current Time: " + formattedDate);

                // Sleep for 1 second before updating again
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("TimeDisplay thread was interrupted.");
                Thread.currentThread().interrupt(); // restore interrupt status
            }
        }
    }

    public void stopClock() {
        continueRunning = false;
    }
}

// Class responsible for running background logic (simulated update task)
class TimeUpdater implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                // Simulate some background processing or time update logic
                System.out.println("[Updater] Background task running...");
                Thread.sleep(3000); // Simulate background task taking time
            } catch (InterruptedException e) {
                System.out.println("TimeUpdater thread was interrupted.");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

// Main class that sets up and starts both threads
public class TimeTrackerApp {
    public static void main(String[] args) {
        // Create instances of the runnable classes
        TimeDisplay displayRunnable = new TimeDisplay();
        TimeUpdater updaterRunnable = new TimeUpdater();

        // Create threads
        Thread displayThread = new Thread(displayRunnable, "DisplayThread");
        Thread updaterThread = new Thread(updaterRunnable, "UpdaterThread");

        // Set priorities
        displayThread.setPriority(Thread.MAX_PRIORITY); // Highest priority for display
        updaterThread.setPriority(Thread.MIN_PRIORITY); // Lower priority for background task

        // Start threads
        displayThread.start();
        updaterThread.start();

        // Let the threads run for 20 seconds before stopping the program
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        // Stop the display thread after simulation
        displayRunnable.stopClock();
        System.out.println("Clock stopped. Program exiting...");
    }
}


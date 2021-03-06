package ch.hslu.ad.sw01;

import ch.hslu.demo.DemoApp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class AhaDemo {
    private static final Logger LOGGER = LogManager.getLogger(DemoApp.class);

    static int calledTask1 = 0;
    static int calledTask2 = 0;
    static int calledTask3 = 0;
    private static long durationInMilliseconds = 0;

    /**
     * Starts task1 4 times, task2 n * 3 times and task3 n^2 * 2 times.
     * @param n the multiplicator
     * @throws InterruptedException
     */
    static void task(final int n) throws InterruptedException {
        durationInMilliseconds = System.currentTimeMillis();
        task1(); task1(); task1(); task1();
        for (int i = 0; i < n; i++) {
            task2(); task2(); task2();
            for (int j = 0; j < n; j++) {
                task3(); task3();
            }
        }
        LOGGER.info("Called task 1 {} times.", calledTask1);
        LOGGER.info("Called task 2 {} times.", calledTask2);
        LOGGER.info("Called task 3 {} times.", calledTask3);
        LOGGER.info("The program took {} milliseconds to execute.",
                System.currentTimeMillis() - durationInMilliseconds);
    }

    /**
     * Reset value of all member and class variables.
     */
    static void reset() {
        calledTask1 = 0;
        calledTask2 = 0;
        calledTask3 = 0;
        durationInMilliseconds = 0;
    }

    /**
     * Execute task 1
     * @throws InterruptedException
     */
    private static void task1() throws InterruptedException {
        calledTask1++;
        Thread.sleep(5);
    }

    /**
     * Execute task 2
     * @throws InterruptedException
     */
    private static void task2() throws InterruptedException {
        calledTask2++;
        Thread.sleep(5);
    }

    /**
     * Execute task 3
     * @throws InterruptedException
     */
    private static void task3() throws InterruptedException {
        calledTask3++;
        Thread.sleep(5);
    }
}

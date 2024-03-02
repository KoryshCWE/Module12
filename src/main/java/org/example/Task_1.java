package org.example;
class Task_1 {
    public static void main(String[] args) {
        Thread timeThread = new Thread(new TimeDisplay());
        timeThread.start();

        Thread messageThread = new Thread(new MessageDisplay());
        messageThread.start();
    }

    static class TimeDisplay implements Runnable {
        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            while (true) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = (currentTime - startTime) / 1000;
                System.out.println("Elapsed time: " + elapsedTime + " seconds");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class MessageDisplay implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println("Минуло 5 секунд");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
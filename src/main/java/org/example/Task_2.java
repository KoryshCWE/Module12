package org.example;
public class Task_2 {
    private int n;
    private int current = 1;
    private final Object monitor = new Object();

    public Task_2(int n) {
        this.n = n;
    }

    public void fizz() {
        synchronized (monitor) {
            while (current <= n) {
                if (current % 3 == 0 && current % 5 != 0) {
                    System.out.println("fizz");
                    current++;
                    monitor.notifyAll();
                } else {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void buzz() {
        synchronized (monitor) {
            while (current <= n) {
                if (current % 5 == 0 && current % 3 != 0) {
                    System.out.println("buzz");
                    current++;
                    monitor.notifyAll();
                } else {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void fizzbuzz() {
        synchronized (monitor) {
            while (current <= n) {
                if (current % 3 == 0 && current % 5 == 0) {
                    System.out.println("fizzbuzz");
                    current++;
                    monitor.notifyAll();
                } else {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void number() {
        synchronized (monitor) {
            while (current <= n) {
                if (current % 3 != 0 && current % 5 != 0) {
                    System.out.println(current);
                    current++;
                    monitor.notifyAll();
                } else {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Task_2 fizzBuzz = new Task_2(15);

        // Створення та запуск потоків A, B, C, D
        Thread threadA = new Thread(fizzBuzz::fizz);
        Thread threadB = new Thread(fizzBuzz::buzz);
        Thread threadC = new Thread(fizzBuzz::fizzbuzz);
        Thread threadD = new Thread(fizzBuzz::number);

        // Запуск потоків A, B, C, D
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        // Очікування завершення виконання всіх потоків D
        threadD.join();
    }
}




package org.example;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
public class Task_2 {
        private int n;
        // Створення черги для чисел, які діляться на 3, 5, або 15
        private BlockingQueue<String> queueA = new LinkedBlockingQueue<>();
        private BlockingQueue<String> queueB = new LinkedBlockingQueue<>();
        private BlockingQueue<String> queueC = new LinkedBlockingQueue<>();
        private BlockingQueue<String> queueD = new LinkedBlockingQueue<>();

        public Task_2(int n) {
            this.n = n;
        }

        public void fizz() throws InterruptedException {
            // Перевірка чисел, які діляться на 3, додавання до черги A
            for (int i = 3; i <= n; i += 3) {
                if (i % 5 != 0) {
                    queueA.put(Integer.toString(i));
                }
            }
        }

        public void buzz() throws InterruptedException {
            // Перевірка чисел, які діляться на 5, додавання до черги B
            for (int i = 5; i <= n; i += 5) {
                if (i % 3 != 0) {
                    queueB.put(Integer.toString(i));
                }
            }
        }

        public void fizzbuzz() throws InterruptedException {
            // Перевірка чисел, які діляться на 15 (тобто одночасно на 3 і 5), додавання до черги C
            for (int i = 15; i <= n; i += 15) {
                queueC.put(Integer.toString(i));
            }
        }

        public void number() throws InterruptedException {
            // Виведення чисел або відповідних рядків з черги D
            for (int i = 1; i <= n; i++) {
                String result;
                // Перевірка умов для виведення fizz, buzz, fizzbuzz
                if (i % 3 == 0 && i % 5 == 0) {
                    result = "fizzbuzz";
                } else if (i % 3 == 0) {
                    result = "fizz";
                } else if (i % 5 == 0) {
                    result = "buzz";
                } else {
                    result = Integer.toString(i);
                }
                // Перевірка, чи є значення в черзі A, B, C і видаляння їх із черги
                if (!queueA.isEmpty() && Integer.parseInt(queueA.peek()) == i) {
                    result = "fizz";
                    queueA.take();
                } else if (!queueB.isEmpty() && Integer.parseInt(queueB.peek()) == i) {
                    result = "buzz";
                    queueB.take();
                } else if (!queueC.isEmpty() && Integer.parseInt(queueC.peek()) == i) {
                    result = "fizzbuzz";
                    queueC.take();
                }
                // Додавання результату у чергу D та виведення його
                queueD.put(result);
                System.out.println(result);
            }
        }

        public static void main(String[] args) throws InterruptedException {
            org.example.Task_2 fizzBuzz = new org.example.Task_2(15);

            // Створення та запуск потоків A, B, C, D
            Thread threadA = new Thread(() -> {
                try {
                    fizzBuzz.fizz();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            Thread threadB = new Thread(() -> {
                try {
                    fizzBuzz.buzz();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            Thread threadC = new Thread(() -> {
                try {
                    fizzBuzz.fizzbuzz();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            Thread threadD = new Thread(() -> {
                try {
                    fizzBuzz.number();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            // Запуск потоків A, B, C, D
            threadA.start();
            threadB.start();
            threadC.start();
            threadD.start();

            // Очікування завершення виконання всіх потоків D
            threadD.join();
        }
    }


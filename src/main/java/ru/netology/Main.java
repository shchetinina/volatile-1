package ru.netology;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static AtomicInteger counter3LettersWord = new AtomicInteger(0);
    private static AtomicInteger counter4LettersWord = new AtomicInteger(0);
    private static AtomicInteger counter5LettersWord = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        int generatedArrayLength = 100_000;
        String[] texts = RandomTextArrayGenerator.generateRandomTextArray(generatedArrayLength);

        Thread palindromCounterThread = new Thread(() -> {
            for (String text : texts) {
                boolean isPalindrom = PalindromChecker.checkPalindrom(text);
                if (isPalindrom) incrementCounter(text.length());
            }
        });

        Thread oneCharStringCounterThread = new Thread(() -> {
            for (String text : texts) {
                boolean oneCharString = true;
                char first = text.charAt(0);
                for (int j = 1; j < text.length(); j++) {
                    if (text.charAt(j) != first) {
                        oneCharString = false;
                        break;
                    }
                }
                if (oneCharString) incrementCounter(text.length());
            }
        });

        Thread alphabetOrderCounterThread = new Thread(() -> {
            for (String text : texts) {
                boolean rightOrder = true;
                for (int j = 0; j < text.length() - 1; j++) {
                    if (text.charAt(j) > text.charAt(j + 1)) {
                        rightOrder = false;
                        break;
                    }
                }
                if (rightOrder) incrementCounter(text.length());
            }
        });

        palindromCounterThread.start();
        oneCharStringCounterThread.start();
        alphabetOrderCounterThread.start();

        palindromCounterThread.join();
        oneCharStringCounterThread.join();
        alphabetOrderCounterThread.join();

        returnResultOutput();
    }

    private static void incrementCounter(int textLength) {
        switch (textLength) {
            case 3:
                counter3LettersWord.getAndIncrement();
                break;
            case 4:
                counter4LettersWord.getAndIncrement();
                break;
            case 5:
                counter5LettersWord.getAndIncrement();
                break;
        }
    }

    private static void returnResultOutput() {
        System.out.printf(
                "Красивых слов с длиной 3: %d шт\n" +
                        "Красивых слов с длиной 4: %d шт\n" +
                        "Красивых слов с длиной 5: %d шт",
                counter3LettersWord.get(),
                counter4LettersWord.get(),
                counter5LettersWord.get()
        );
    }
}
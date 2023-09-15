import java.util.Scanner;

public class StepTracker {
    Scanner scanner;
    MonthData[] monthToData = new MonthData[12];
    int goalByStepsPerDay = 10000;
    Converter converter = new Converter();
    MonthData monthData;

    public StepTracker(Scanner scan) {
        scanner = scan;

        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    void addNewNumberStepsPerDay() {
        System.out.println("Введите номер месяца");
        int month = scanner.nextInt();
            if ((month > 12) | (month < 1)) {
                System.out.println("Номер вводимого месяца должен быть от 1 до 12 включительно");
                return;
            }

        System.out.println("Введите день от 1 до 30 (включительно)");
        int day = scanner.nextInt();
            if ((day > 30) | (day < 1)) {
                System.out.println("Номер вводимого дня должен быть от 1 до 30 включительно");
                return;
            }

        System.out.println("Введите количество шагов");
        int numberOfSteps = scanner.nextInt();
            if (numberOfSteps < 1) {
                System.out.println("Количество шагов должно быть положительным числом");
                return;
            }

        MonthData monthData = monthToData[month - 1];
        monthData.days[day - 1] = numberOfSteps;
    }

    void changeStepGoal() {
        System.out.println("Введите новую цель по количеству шагов в день");
        int target = scanner.nextInt();
            if ((target < 1)) {
                System.out.println("Количество шагов должно быть положительным числом");
                return;
            }

        goalByStepsPerDay = target;
    }

    void printStatistic() {
        System.out.println("Введите номер месяца");
        int month = scanner.nextInt();
        if ((month > 12) | (month < 1)) {
            System.out.println("Номер вводимого месяца должен быть от 1 до 12 включительно");
            return;
        }

        monthData = monthToData[month - 1];
        int sumSteps = monthData.sumStepsFromMonth();

        System.out.println("Общая статистика по дням:");
        monthData.printDaysAndStepsFromMonth();
        System.out.println();

        System.out.println("Сумма шагов за месяц: " + sumSteps);
        System.out.println("Максимальное количество шагов, пройденое за день в этом месяце: " + monthData.maxSteps());
        System.out.println("Среднее количество шагов пройденое за месяц: " + sumSteps / 30);
        System.out.println("Пройдено за месяц дистанции в км: " + converter.convertToKm(sumSteps));
        System.out.println("Количество сожжённых килокалорий за месяц: " + converter.convertStepsToKilocalories(sumSteps));
        System.out.println("Максимальное количество подряд идущих дней, когда количество шагов за день было равно или выше целевого: " + monthData.bestSeries(goalByStepsPerDay));
        System.out.println();
    }
}

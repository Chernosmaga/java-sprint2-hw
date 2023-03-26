import java.util.Scanner;
public class Main {
    static Scanner read = new Scanner(System.in);
    static FilesReader filesReader = new FilesReader();
    static DataReconciliation dataReconciliation = new DataReconciliation();
    static MonthlyReport monthlyReport = new MonthlyReport();
    static YearlyReport yearlyReport = new YearlyReport();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\nВыберите действие из списка ниже:");

            printMenu();

            int command = read.nextInt();
            if (command == 1) {
                filesReader.monthFilesReader();
                System.out.println("Все месячные отчёты успешно считаны");
            } else if (command == 2) {
                filesReader.yearFilesReader();
                System.out.println("Годовой отчёт успешно считан");
            } else if (command == 3) {
                dataReconciliation.dataReconciliation();
                System.out.println("\nСверка отчётов выполнена");
            } else if (command == 4) {
                monthlyReport.monthlyReport();
            } else if (command == 5) {
                yearlyReport.yearlyReport();
            } else if (command == 6) {
                System.out.println("Выход...");
                return;
            } else {
                System.out.println("Такой команды нет");
            }
        }
    }

    static void printMenu() {
        System.out.println("1 - Считать все месячные отчёты\n2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты\n4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте\n6 - Выйти из приложения");
    }
}
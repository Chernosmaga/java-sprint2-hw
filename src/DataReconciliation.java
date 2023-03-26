import java.util.ArrayList;
import java.util.HashMap;

public class DataReconciliation {
    static HashMap<Integer, ArrayList<Month>> globalMonthMap = new HashMap<>(); // ключ - месяц, значения - примитивы
    static HashMap<Integer, ArrayList<Year>> globalYearMap = new HashMap<>(); // ключ - год, значения - примитивы
    static ArrayList<Month> monthList = new ArrayList<>(); // лист со значениями месяца
    static ArrayList<Year> yearList = new ArrayList<>(); // лист со значениями года

    public void dataReconciliation() {
        if (globalMonthMap.size() == 0 && globalYearMap.size() == 0) { // если один из отчётов не считан, то метод не выполнится
            System.out.println("Невозможно провести сверку итогов, так как месячный и годовой отчёт не считаны");
            return;
        }

        HashMap<Integer, Integer> monthProfits = new HashMap<>(); // ключ - месяц, значение - сумма
        HashMap<Integer, Integer> monthExpenses = new HashMap<>(); // мапа с суммированными расходами по всем месячными файлам

        for (int i = 1; i <= 3; i++) { // получаю номер месяца
            ArrayList<Month> local = globalMonthMap.get(i); // "разархивирую" по ключу мапу и создаю отдельный лист для месяца
            for (int j = 0; j < local.size(); j++) { // прохожусь по элементам мв списке
                Month item = local.get(j); // "разархивирую" объекты, которые были сохранены в мапу
                if (item.isExpense) {
                    monthExpenses.put(i, monthExpenses.getOrDefault(i, 0) + item.quantity * item.sumOfOne); // сюда расходы
                } else {
                    monthProfits.put(i, monthProfits.getOrDefault(i, 0) + item.quantity * item.sumOfOne); // сюда доходы
                }
            }
        }

        HashMap<Integer, Integer> yearProfits = new HashMap<>(); // аналогично с годовыми объектами
        HashMap<Integer, Integer> yearExpenses = new HashMap<>();

        for (Year key : yearList) { // здесь "разархивирую" объекты листа, чтоб не трогать мапу
            if (key.isExpense) {
                yearExpenses.put(key.month, key.amount);
            } else {
                yearProfits.put(key.month, key.amount);
            }
        }

        if (monthProfits.size() != yearProfits.size()) { // если мапы разной длины, то уже ошибка
            System.out.println("Ошибка! Файлы с месячными или годовыми отчётами некорректны");
        }
        for (Integer key : monthProfits.keySet()) { // здесь прохожусь по ключам мап в методе сверки
            if (!monthProfits.get(key).equals(yearProfits.get(key))) { // здесь по их значениям
                System.out.println("Ошибка! Сверка отчётов прошла не успешно");
            }
        }

        if (monthExpenses.size() != yearExpenses.size()) {
            System.out.println("Ошибка! Файлы с месячными или годовыми отчётами некорректны");
        }
        for (Integer key : monthExpenses.keySet()) {
            if(!monthExpenses.get(key).equals(yearExpenses.get(key))) {
                System.out.println("Ошибка! Сверка отчётов прошла не успешно");
            }
        }
    }
}


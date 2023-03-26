import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    public void yearlyReport() { // годовой отчёт

        if (DataReconciliation.globalYearMap.size() == 0) { // проверка на считывание годового отчёта, если мапа пуста, то отчёт не считали
            System.out.println("Ошибка в считывании годового отчёта\nПопробуйте снова");
            return;
        }

        int profit = 0;
        int expense = 0;
        int valueOfProfits = 0;
        int valueOfExpenses = 0;

        HashMap<Integer, Integer> localMap = new HashMap<>(); // в эту мапу сохраняю прибыль по месяцам
        ArrayList<Integer> profits = new ArrayList<>(); // лист для доходов
        ArrayList<Integer> expenses = new ArrayList<>(); // другой для расходов

        for (Year checker : DataReconciliation.yearList) { // раскидываю значения по листам
            if (!checker.isExpense) { // проверка на неравенство, чтоб первым было добавлено значение прибыли
                valueOfProfits = checker.amount; // иначе значения в localMap уйдут в минус, потому что начальные значения переменных 0
                profit += checker.amount;
                profits.add(valueOfProfits);
            } else {
                valueOfExpenses = checker.amount;
                expense += checker.amount;
                expenses.add(valueOfExpenses);
            }
            checker.amount = valueOfProfits - valueOfExpenses; // добавляю прибыль отнимая расходы от доходов
            localMap.put(checker.month, checker.amount); // кладу значения в мапу
        }

        for (Integer yearName : DataReconciliation.globalYearMap.keySet()) { // по ключу получаю год
            System.out.println("Год " + yearName + "\n");

            for (int i = 1; i < 4; i++) {
                System.out.println("Месяц " + i + "\nПрибыль: " + localMap.get(i)); // получаю значение месяца из листа, и прибыль
            }
        }

        int averageExpense = expense / expenses.size(); // ищу средний расход
        int averageProfit = profit / profits.size(); // делю общую сумму на количество элементов в листе
        System.out.println();
        System.out.println("Средний расход: " + averageExpense);
        System.out.println("Средний доход: " + averageProfit);
    }
}

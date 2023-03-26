import java.util.HashMap;

public class MonthlyReport {
    public void monthlyReport() { // месячный отчёт
        if (DataReconciliation.globalMonthMap.size() == 0) { // проверка на считывание месячного отчёта, если мапа пуста, то отчёт не считали
            System.out.println("Вы не считали месячный отчёт\nПожалуйста, попробуйте снова");
            return;
        }

        for (Integer month : DataReconciliation.globalMonthMap.keySet()) {
            DataReconciliation.monthList = DataReconciliation.globalMonthMap.get(month); // получаю ключ - месяц из мапы
            System.out.println("Месяц " + month);
            HashMap<String, Integer> profits = new HashMap<>(); // мапа с доходами, ключ - название товара
            HashMap<String, Integer> expenses = new HashMap<>(); // мапа с расходами

            for (Month sort : DataReconciliation.monthList) { // добавляю данные из globalMonthMap в две созданные, разделяя на доходы и расходы
                if (sort.isExpense) { // если true, то кладём в expense
                    expenses.put(sort.itemName, (sort.quantity * sort.sumOfOne));
                } else { // иначе - доходы
                    profits.put(sort.itemName, (sort.quantity * sort.sumOfOne));
                }
            }

            String product = "";
            Integer maxNum = 0;
            for (String prod : profits.keySet()) { // получаю значение самого прибыльного товара
                Integer iterator = profits.get(prod);
                if (iterator > maxNum) { // если итерируемое значение больше максимального,
                    maxNum = iterator; // то присваиваю максимальному - это значение
                    product = prod; // по ключу получаю название товара, которому принадлежит самый большой доход
                }

            }
            System.out.println("Самый прибыльный товар " + product + " на сумму: " + maxNum);

            Integer maximum = 0;
            for (String prod : expenses.keySet()) { // аналогичная операция с поиском максимальной траты
                Integer iterator = expenses.get(prod);
                if (iterator > maximum) {
                    maximum = iterator;
                    product = prod;
                }
            }
            System.out.println("Самая большая трата " + maximum + " на товар: " + product);
        }
    }
}
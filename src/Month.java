public class Month {
    // класс с объектами для присвоения каждому
    // файлу месяца в зависимости от итерации
    String itemName;
    boolean isExpense;
    int quantity;
    int sumOfOne;

    public Month(String itemName, boolean isExpense, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
}

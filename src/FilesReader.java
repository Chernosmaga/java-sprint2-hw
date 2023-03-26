import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilesReader {
    public void monthFilesReader() {
        for (int i = 1; i <= 3; i++) { // итерирую по месяцам файлы, что получить ключ для месячной мапы - месяц
            DataReconciliation.monthList = readMonthFiles("resources/m.20210" + i + ".csv"); // путь к файлу зависит от итерации
            DataReconciliation.globalMonthMap.put(i, DataReconciliation.monthList); // сохраняю элементы в globalMonthMap
        }
    }
    public void yearFilesReader() {
        int yearName = 2021;
        DataReconciliation.yearList = readYearFiles("resources/y." + yearName + ".csv");
        DataReconciliation.globalYearMap.put(yearName, DataReconciliation.yearList);
    }
    public ArrayList<Month> readMonthFiles(String path) { // считывание месячных файлов
        ArrayList<Month> months = new ArrayList<>(); // пустой лист для заполнения элементов из каждого файла
        List<String> content = readFileContents(path);
        for (int j = 1; j < content.size(); j++) { // итерируя, разделяю лист запятыми
            String[] parts = content.get(j).split(",");
            String itemName = parts[0]; // присваиваю значения из листа каждому примитиву
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);

            months.add(new Month(itemName, isExpense, quantity, sumOfOne)); // сохраняю примитивы с помощью конструктора, чтоб положить в мапу
        }
        return months; // возвращаю лист с примитивными типами для каждого месяца в globalMonthMap
    }

    public ArrayList<Year> readYearFiles(String path) {
        ArrayList<Year> years = new ArrayList<>();
        List<String> content = readFileContents(path);
        for (int i = 1; i < content.size(); i++) {
            String line = content.get(i);
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            years.add(new Year(month, amount, isExpense));
        }
        return years;
    }

    List<String> readFileContents(String path) { // метод для считывания файлов
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
}

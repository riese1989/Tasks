import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class main {
    public static String[] returnArray;
    public static void main(String[] args) {
        HashMap<String, String> numbers = new HashMap<>();
        String name;
        String phone;
        boolean nextIteration = true;
        while (nextIteration) {
            System.out.println("Введите данные");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (contains(numbers,command)){
                System.out.println("\nИмя " + returnArray[0]);
                System.out.println("Телефон " + returnArray[1]+"\n");
                continue;
            }
            if (command.equals("exit")) {
                nextIteration = false;
                continue;
            }
            if (command.equals("list")) {
                listMap(numbers);
                continue;
            }
            if (command.replaceAll("\\d", "").equals("")) {
                System.out.println("Введите имя");
                scanner = new Scanner(System.in);
                name = scanner.nextLine();
                phone = command;
            } else {
                System.out.println("Введите телефон");
                scanner = new Scanner(System.in);
                name = command;
                phone = scanner.nextLine();
            }
            System.out.println("");
            numbers.put(name, phone);
        }
    }

    public static boolean contains (HashMap<String, String> numbers, String value)   {
        returnArray = new String[2];
        for (Map.Entry<String, String> entry : numbers.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(value) || entry.getValue().equalsIgnoreCase(value)) {
                returnArray[0] = entry.getKey();
                returnArray[1] = entry.getValue();
                return true;
            }
        }
        return false;
    }

    public static void listMap(HashMap<String, String> numbers) {
        for (Map.Entry<String, String> entry : numbers.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.print("\n");
    }
}

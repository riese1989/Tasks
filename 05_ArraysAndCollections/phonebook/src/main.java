import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        HashMap<String, String> numbers = new HashMap<>();
        String name;
        String phone;
        boolean nextIteration = true;
        while (nextIteration) {
            System.out.println("Введите данные");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (numbers.containsKey(command)) {
                System.out.println("Имя " + command);
                System.out.println("Телефон " + numbers.get(command));
                continue;
            }
            if (numbers.containsValue(command)) {
                System.out.println("Имя " + findKey(numbers, command));
                System.out.println("Телефон " + command);
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
                System.out.println("Введите имя");
                scanner = new Scanner(System.in);
                name = command;
                phone = scanner.nextLine();
            }
            numbers.put(name, phone);
        }
    }

    public static String findKey(HashMap<String, String> numbers, String value) {
        for (Map.Entry<String, String> entry : numbers.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static void listMap(HashMap<String, String> numbers) {
        for (Map.Entry<String, String> entry : numbers.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.print("\n");
    }
}

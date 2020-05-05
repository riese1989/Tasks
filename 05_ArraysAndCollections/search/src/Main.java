import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите нужное число номеров от 1 до " + 58 * 10 * 58 * 58 * 199);
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> autoNumbers = generateNumber(scanner.nextInt());
        ArrayList<String> autoNumbers2 = autoNumbers;
        System.out.println("Введите номер, который хотите найт");
        scanner = new Scanner(System.in);
        String findNumber = scanner.nextLine();
        System.out.println(searchBrute(autoNumbers, findNumber, (int) System.nanoTime())); //поиск перебором
        Collections.sort(autoNumbers);
        System.out.println(searchBinary(autoNumbers, findNumber, (int) System.nanoTime())); //бинарный поиск
        System.out.println(searchHashSet(autoNumbers2, findNumber, (int) System.nanoTime())); //HashSet поиск
        System.out.println(searchTreeSet(autoNumbers2, findNumber, (int) System.nanoTime())); //TreeSet поиск
    }

    public static ArrayList<String> generateNumber(Integer count) {
        ArrayList<String> result = new ArrayList<>();
        int codeSymb = 65;
        String autoNumber;
        char[] symbols = new char[58];
        for (int i = 0; i < 58; i++, codeSymb++) {
            symbols[i] = (char) codeSymb;
            if (codeSymb == 90) {
                codeSymb = 1039;
            }
        }
        for (int i = 0; i < 58 && result.size() < count; i++) {
            for (int j = 0; j < 58 && result.size() < count; j++) {
                for (int k = 0; k < 58 && result.size() < count; k++) {
                    for (Integer number = 0; number <= 9 && result.size() < count; number++) {
                        for (Integer region = 1; region <= 199 && result.size() < count; region++) {
                            autoNumber = symbols[i] + number.toString() + number.toString() + number.toString()
                                    + symbols[j] + symbols[k];
                            if (region < 10) {
                                autoNumber += "0";
                            }
                            autoNumber += region.toString();
                            result.add(autoNumber);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static String searchBrute(ArrayList<String> autonumbers, String findNumber, Integer start) {
        Integer time = null;
        String result;
        if (autonumbers.contains(findNumber)) {
            result = "номер найден,";
            time = (int) System.nanoTime() - start;
        } else {
            result = "номер  не найден,";
            time = (int) System.nanoTime() - start;
        }
        return "Поиск перебором: " + result + " поиск занял " + time.toString() + " нс";
    }

    public static String searchBinary(ArrayList<String> autonumbers, String findNumber, Integer start) {
        Integer time;
        if (Collections.binarySearch(autonumbers, findNumber) >= 0) {
            time = (int) System.nanoTime() - start;
            return "Бинарный поиск: номер найден, поиск занял " + time.toString() + " нс";
        }
        time = (int) System.nanoTime() - start;
        return "Бинарный поиск: номер  не найден, поиск занял " + time.toString() + " нс";
    }

    public static String searchHashSet(ArrayList<String> autonumbers, String findNumber, Integer start) {
        Integer time;
        HashSet<String> hash = new HashSet<>(autonumbers);
        if (hash.contains(findNumber)) {
            time = (int) System.nanoTime() - start;
            return "Поиск в HashSet: номер найден, поиск занял " + time.toString() + " нс";
        }
        time = (int) System.nanoTime() - start;
        return "Поиск в HashSet: номер не найден, поиск занял " + time.toString() + " нс";
    }

    public static String searchTreeSet(ArrayList<String> autonumbers, String findNumber, Integer start) {
        Integer time;
        TreeSet<String> tree = new TreeSet<>(autonumbers);
        if (tree.contains(findNumber)) {
            time = (int) System.nanoTime() - start;
            return "Поиск в TreeSet: номер найден, поиск занял " + time.toString() + " нс";
        }
        time = (int) System.nanoTime() - start;
        return "Поиск в TreeSet: номер не найден, поиск занял " + time.toString() + " нс";
    }

    public static void list(ArrayList<String> autonumbers) {
        for (String autonumber : autonumbers) {
            System.out.println(autonumber);
        }
    }
}

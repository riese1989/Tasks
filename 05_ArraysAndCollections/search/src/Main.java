import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> autoNumbers = new ArrayList<>();
        for (int i = 0; i < 3000000; i++) {
            autoNumbers.add(generator());
        }
        ArrayList<String> autoNumbers2 = autoNumbers;
        System.out.println(autoNumbers.get(0));
        System.out.println("Введите номер, который хотите найти");
        Scanner scanner = new Scanner(System.in);
        String findNumber = scanner.nextLine();
        System.out.println(bruteForce(autoNumbers, findNumber, (int) System.nanoTime())); //поиск перебором
        Collections.sort(autoNumbers2);
        System.out.println(binary(autoNumbers, findNumber, (int) System.nanoTime())); //бинарный поиск
        System.out.println(searchHashSet(autoNumbers, findNumber, (int) System.nanoTime())); //HashSet поиск
        System.out.println(searchTreeSet(autoNumbers, findNumber, (int) System.nanoTime())); //TreeSet поиск
    }

    public static String generator() {
        int i = 0;
        int codeSymb;
        int region;
        char[] letters = new char[3];
        String number;
        String digit = Integer.toString((int) (Math.random() * 10));
        while (true) {
            region = (int) (Math.random() * 200);
            if (region != 0) {
                break;
            }
        }
        while (i < 3) {
            codeSymb = (int) (Math.random() * 100);
            if (codeSymb >= 65 && codeSymb <= 90) {
                letters[i] = (char) codeSymb;
                i++;
            }
        }
        number = letters[0] + digit + digit + digit + letters[1] + letters[2];
        if (region < 10) {
            number += "0";
        }
        number += Integer.toString(region);
        return number;
    }

    public static String bruteForce(ArrayList<String> autonumbers, String findNumber, Integer start) {
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

    public static String binary(ArrayList<String> autonumbers, String findNumber, Integer start) {
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
        HashSet <String> hash = new HashSet<>(autonumbers);
        if (hash.contains(findNumber)) {
            time = (int) System.nanoTime() - start;
            return "Поиск в HashSet: номер найден, поиск занял " + time.toString() + " нс";
        }
        time = (int) System.nanoTime() - start;
        return "Поиск в HashSet: номер не найден, поиск занял " + time.toString() + " нс";
    }

    public static String searchTreeSet(ArrayList<String> autonumbers, String findNumber, Integer start) {
        Integer time;
        TreeSet <String> tree = new TreeSet<>(autonumbers);
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

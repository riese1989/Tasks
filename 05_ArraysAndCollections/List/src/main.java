import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        boolean nextIteration = true;
        ArrayList<String> toDoList = new ArrayList<>();
        toDoList.add("Дело 1");
        toDoList.add("Дело 2");
        while (nextIteration) {
            System.out.println("Введите команду");
            Scanner scanner = new Scanner(System.in);
            String[] comand = scanner.nextLine().split(" ", 3);
            switch (comand[0].toUpperCase()) {
                case "LIST": {
                    for (String thing : toDoList) {
                        System.out.println(thing);
                    }
                    break;
                }
                case "ADD": {
                    if (comand.length == 3) {
                        toDoList.add(Integer.parseInt(comand[1]), comand[2]);
                    } else {
                        toDoList.add(comand[1]);
                    }
                    break;

                }
                case "EDIT": {
                    toDoList.set(Integer.parseInt(comand[1]), comand[2]);
                    break;
                }
                case "DELETE": {
                    toDoList.remove(Integer.parseInt(comand[1]));
                    break;
                }
                case "EXIT": {
                    nextIteration = false;
                    break;
                }
            }
        }
        System.out.println("Завершено");
    }
}

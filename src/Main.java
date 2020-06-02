import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String str = "=======================";
        Tasks.setCountTasksAll(customScanner("Сколько всего обращений"));
        Tasks.setNoneAppTasks(customScanner("Сколько неназначенных"));
        Employee pestov = new Employee(customScanner("Сколько на мне"), "pestov");
        pestov.setTaskWaiting(customScanner("У меня в ожидании"));
        pestov.setTasksWithTasks(customScanner("У меня с заданиями"));
        Employee batanov = new Employee(Tasks.getCountTasksAll() -Tasks.getNoneAppTasks() - pestov.getCountTaskOne(), "batanov");
        batanov.setTaskWaiting(customScanner("У Миши в ожидании"));
        batanov.setTasksWithTasks(customScanner("У Миши с заданиями"));
        System.out.println(str + "\nРезультат\n" + str);
        if (Tasks.getNoneAppTasks() > 0)    {
            Tasks.listTasks = new ArrayList<>();
        }
        for (int i = 1; i <=Tasks.getNoneAppTasks(); i++)    {
            System.out.println("Введите номер инцидента");
            Scanner scanner = new Scanner(System.in);
            String number = scanner.nextLine();
            Employee empl = choiceAssignTask(pestov, batanov);
            Tasks.listTasks.add(new Tasks(number,empl, TaskStatus.NOTE_DONE));
            System.out.println("Назначено на " + empl.getFamily());
            empl.setCountTaskOne(empl.getCountTaskOne()+1);
            Employee.setAppCountTask(Employee.getAppCountTask()+1);
        }
    }

    public static Employee choiceAssignTask(Employee empl1, Employee empl2) {
        if ((int) (Math.random() * 2) == 0) {
            if (empl1.getCountTaskOne() <= Tasks.countAppTasks()/2) {
                return empl1;
            }
            else    {
                return empl2;
            }
        }
        else    {
            if (empl2.getCountTaskOne() <= Tasks.countAppTasks()/2) {
                return empl2;
            }
        }
        return empl1;
    }

    public static Integer customScanner(String message)   {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}

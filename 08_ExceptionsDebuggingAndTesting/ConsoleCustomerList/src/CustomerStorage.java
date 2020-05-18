import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data)
    {
       try {
           String[] components = data.split("\\s+");
           String name = components[0];
           String surname = components[1];
           String email = components[2];
           String phone = components[3];
           String fullName = name + " " + surname;
           String clearPhone = phone.replaceFirst("\\+","");
           if   (email.indexOf("@") == 0 || email.indexOf(".") == email.length()-1 ||
                   email.indexOf(".") - email.indexOf("@") == 1 ||
                   email.length() - email.replaceAll("@","").length() > 1 ||
                   email.length() - email.replaceAll("\\.","").length() > 1 ||
                   !email.contains(".") ||
                   !email.contains("@")) {
               throw new Exception("Wrong email");
           }
           if   ((phone.indexOf("+") == 0 && phone.length() != 12) || clearPhone.length() != 11 ||
                   !clearPhone.matches("[-+]?\\d*\\.?\\d+") ||
                   phone.indexOf("+") > 0) {
               throw new Exception("Wrong phone");
           }
           storage.put(fullName, new Customer(fullName, components[3], components[2]));
       }
       catch (Exception ex) {
           ex.printStackTrace();
       }
    }

    public void listCustomers()
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        storage.remove(name);
    }

    public int getCount()
    {
        return storage.size();
    }
}
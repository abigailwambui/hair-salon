import java.util.List;
import org.sql2o.*;

    public class Stylist {
        private String name;
        private int phoneNumber;
        private int age;

    public Stylist(String name, int phoneNumber, int age) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getphoneNumber() {
        return phoneNumber;

    }

    public int getAge() {
        return age;

    }
    }
import java.util.List;
import org.sql2o.*;

    public class Stylist {
        private String name;
        private int phoneNumber;
        private int age;
        private String email;

    public Stylist(String name, int phoneNumber, int age, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.email = email;
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

    public String getEmail() {
        return email;
    }
    }
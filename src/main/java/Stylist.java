import java.util.List;
import org.sql2o.*;

    public class Stylist {
        private String name;
        private int phoneNumber;
        private int age;
        private String email;
        private String workExperience;

    public Stylist(String name, int phoneNumber, int age, String email, String workExperience) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.email = email;
        this.workExperience = workExperience;
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

    public String getWorkExperience() {
        return workExperience;
    }
    }
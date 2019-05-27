import java.util.List;
import org.sql2o.*;

    public class Stylist {
        private String name;
        private int phoneNumber;

    public Stylist(String name, int phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getphoneNumber() {
        return phoneNumber;

    }
    }
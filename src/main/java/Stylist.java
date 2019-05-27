import java.util.List;
import org.sql2o.*;

    public class Stylist {
        private String name;
        private int phoneNumber;
        private int age;
        private String email;
        private String workExperience;
        private int id;

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

    public int getId() {
        return id;
    }

    public static List<Stylist> all(){
        try(Connection con = DB.sql2o.open()){
        String sql  = "SELECT id, name, phoneNumber, age, email, workExperience FROM stylists";
            return con.createQuery(sql).executeAndFetch(Stylist.class);
        }
    }

        @Override
    public boolean equals(Object otherStylist){
        if (!(otherStylist instanceof Stylist)){
            return false;
        } else {
            Stylist newStylist = (Stylist) otherStylist;
            return  this.getName().equals(newStylist.getName()) &&
                    this.getphoneNumber() == newStylist.getphoneNumber() &&
                    this.getAge() == newStylist.getAge() &&
                    this.getEmail().equals(newStylist.getEmail()) &&
                    this.getWorkExperience().equals(newStylist.getWorkExperience()) &&
                    this.getId() == newStylist.getId();
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO stylists (name, phoneNumber, age, email, workExperience) VALUES (:name, :phoneNumber, :age, :email, :workExperience)";
           this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("phoneNumber", this.phoneNumber)
                    .addParameter("age", this.age)
                    .addParameter("email", this.email)
                    .addParameter("workExperience", this.workExperience)
                    .executeUpdate()
                    .getKey();
        }
    }

    }
    
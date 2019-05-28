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

    public static Stylist find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT id, name, phoneNumber, age, email, workExperience FROM stylists where id=:id";
            Stylist stylist = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Stylist.class);
            return stylist;
        }
    }

    public void updateStylist(String name, int phoneNumber, int age, String email, String workExperience) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE stylists SET name = :name, phoneNumber = :phoneNumber, age = :age, email = :email, workExperience = :workExperience WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("phoneNumber", phoneNumber)
                    .addParameter("age", age)
                    .addParameter("email", email)
                    .addParameter("workExperience", workExperience)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public List<Client> every() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM clients where stylistId=:id";
            return con.createQuery(sql)
                .addParameter("id", this.id)
                .executeAndFetch(Client.class);
        }
    }

    public void deleteStylist() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM stylists WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            String sql2 = "DELETE FROM clients WHERE stylistId=:id; ";
            con.createQuery(sql2)
                .addParameter("id", id)
                .executeUpdate();
        }
    }

    }
    
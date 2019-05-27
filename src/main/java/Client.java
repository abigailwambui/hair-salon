import java.util.List;
import org.sql2o.*;

public class Client {
    private String name;
    private String gender;
    private int phoneNumber;
    private int stylistId;
    private int id;

    public Client(String name, String gender, int phoneNumber, int stylistId) {
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.stylistId = stylistId;
    }

public String getName(){
    return name;
}

public String getGender(){
    return gender;
}

public int getphoneNumber(){
    return phoneNumber;
}
public int getId(){
    return id;
}

public int getstylistId(){
    return stylistId;
}

public static List<Client> all(){
        try(Connection con = DB.sql2o.open()){
        String sql  = "SELECT * FROM clients";
            return con.createQuery(sql).executeAndFetch(Client.class);
        }

    }

    @Override
    public boolean equals(Object otherClient){
        if (!(otherClient instanceof Client)){
            return false;
        } else {
            Client newClient = (Client) otherClient;
            return  this.getName().equals(newClient.getName()) &&
                    this.getGender().equals(newClient.getGender()) &&
                    this.getphoneNumber() == newClient.getphoneNumber() &&
                    this.getId() == newClient.getId() &&
                    this.getstylistId() == newClient.getstylistId();
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO clients (name, gender, phoneNumber, stylistId) VALUES (:name, :gender, :phoneNumber, :stylistId)";
           this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("gender", this.gender)
                    .addParameter("phoneNumber", this.phoneNumber)
                    .addParameter("stylistId", this.stylistId)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static Client find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM clients where id=:id";
            Client Client = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Client.class);
            return Client;
        }
    }

    public void update(String name, String gender, int phoneNumber, int stylistId) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE clients SET name = :name, gender = :gender, phoneNumber = :phoneNumber, stylistId = :stylistId WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("gender", gender)
                    .addParameter("phoneNumber", phoneNumber)
                    .addParameter("id", id)
                    .addParameter("stylistId", stylistId)
                    .executeUpdate();
        }
    }

    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM clients WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
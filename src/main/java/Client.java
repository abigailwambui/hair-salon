import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
    private String name;
    private String gender;
    private int phoneNumber;
    private int id;

    public Client(String name, String gender, int phoneNumber) {
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
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

public static List<Client> all(){
        String sql  = "SELECT id, name, gender, phoneNumber FROM client";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Client.class);
        }

    }

    @Override
    public boolean equals(Object otherClient){
        if(!(otherClient instanceof Client)){
            return false;
        } else {
            Client client = (Client) otherClient;
            return this.getName().equals(client.getName()) &&
                    this.getGender().equals(client.getGender()) &&
                    this.getphoneNumber() == client.getphoneNumber() &&
                    this.getId() == client.getId();
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO client (name, gender, phoneNumber) VALUES (:name, :gender, :phoneNumber)";
           this.id = (int) con.createQuery(sql, true)
                    .addParameter("title", this.name)
                    .addParameter("company", this.gender)
                    .addParameter("location", this.phoneNumber)
                    .executeUpdate()
                    .getKey();
        }
    }
}
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StylistTest{

    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "abigail", "123");
    }

     @Test 
    public void stylists_instantiatesCorrectly_true() {
        Stylist myStylist = new Stylist("Jackline Mumbi", 1234567890);
        assertEquals(true, myStylist instanceof Stylist);
    }

    @Test 
    public void getName_stylistInstantiatesWithName_Jackline() {
        Stylist myStylist = new Stylist("Jackline Mumbi", 1234567890);
        assertEquals("Jackline Mumbi", myStylist.getName());
    }

    @Test 
    public void getphoneNumber_stylistInstantiatesWithphoneNumber_Integer() {
        Stylist myStylist = new Stylist("Jackline Mumbi", 1234567890);
        assertEquals(1234567890, myStylist.getphoneNumber());
    }


    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
        String sql = "DELETE FROM clients *;";
        con.createQuery(sql).executeUpdate();
        }
    }
}
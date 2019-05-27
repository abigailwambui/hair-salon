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
        Stylist myStylist = new Stylist("Jackline Mumbi");
        assertEquals(true, myStylist instanceof Stylist);
    }

    @Test 
    public void getName_stylistInstantiatesWithName_Jackline() {
        Stylist myStylist = new Stylist("Jackline Mumbi");
        assertEquals("Jackline Mumbi", myStylist.getName());
    }


    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
        String sql = "DELETE FROM clients *;";
        con.createQuery(sql).executeUpdate();
        }
    }
}
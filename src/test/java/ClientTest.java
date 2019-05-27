import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "abigail", "123");
    }

    @Test 
    public void myClient_instantiatesCorrectly_true() {
        Client myClient = new Client("Zion Mwema", "male", 0711556677, 1);
        assertEquals(true, myClient instanceof Client);
    }

    @Test 
    public void myClient_instantiatesWithName_String() {
        Client myClient = new Client("Zion Mwema", "male", 0711556677, 1);
        assertEquals("Zion Mwema", myClient.getName());
    }

    @Test 
    public void myClient_instantiatesWithGender_String() {
        Client myClient = new Client("Zion Mwema", "male", 0711556677, 1);
        assertEquals("male", myClient.getGender());
    }
    
    @Test 
    public void myClient_instantiatesWithphoneNumber_Integer() {
        Client myClient = new Client("Zion Mwema", "male", 0711556677, 1);
        assertEquals(0711556677, myClient.getphoneNumber());
    }

    @Test 
    public void myClient_instantiatesWithStylistId_Integer() {
        Client myClient = new Client("Zion Mwema", "male", 0711556677, 1);
        assertEquals(1, myClient.getstylistId());
    }

    @Test
    public void equals_returnsTrueIfAllInstancesAreTheSame_true(){
        Client firstClient = new Client("Zion Mwema", "male", 0711556677, 1);
        Client secondClient = new Client("Zion Mwema", "male", 0711556677, 1);
        assertTrue(firstClient.equals(secondClient));
    }

    @Test
    public void save_returnsTrueIfInstancesAreTheSame(){
        Client myClient = new Client("Zion Mwema", "male", 0711556677, 1);
        myClient.save();
        assertTrue(Client.all().get(0).equals(myClient));
    }

    @Test
    public void save_assignsIdToObject() {
      Client myClient = new Client("Zion Mwema", "male", 0711556677, 1);
      myClient.save();
      Client savedClient = Client.all().get(0);
      assertEquals(myClient.getId(), savedClient.getId());
      }

    @Test
    public void all_returnAllInstancesOfClientAs_True(){
        Client firstClient = new Client("Zion Mwema", "male", 0711556677, 1);
        firstClient.save();
        Client secondClient = new Client("Sophia Rehema", "female", 0711556677, 1);
        secondClient.save();
        assertEquals(true, Client.all().get(0).equals(firstClient));
        assertEquals(true, Client.all().get(1).equals(secondClient));
    }

    @Test
    public void getId_myClientInstantiatesWithAnId(){
        Client myClient = new Client("Zion Mwema", "male", 0711556677, 1);
        myClient.save();
        assertTrue(myClient.getId()>0);
    }

     @Test
    public void find_returnsmyClientWithSameId_secondClient() {
        Client firstClient = new Client("Zion Mwema", "male", 0711556677, 1);
        firstClient.save();
        Client secondClient = new Client("Sophia Rehema", "female", 0711556677, 1);
        secondClient.save();
        assertEquals(Client.find(secondClient.getId()), secondClient);
    }

    @Test
    public void update_updatesClientDescription_true() {
        Client myClient = new Client("Zachariah Jabari", "male", 0711556677, 1);
        myClient.save();
        myClient.update("Sophia Rehema", "female", 0711556677, 1);
        Client updated = new Client("Sophia Rehema", "female", 0711556677, 1);
        assertEquals(updated.getName(), Client.find(myClient.getId()).getName());
    }

    @Test
    public void delete_deletesClient_true() {
        Client myClient = new Client("Zion Mwema", "male", 0711556677, 1);
        myClient.save();
        int myClientId = myClient.getId();
        myClient.delete();
        assertEquals(null, Client.find(myClientId));
    }

    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
        String sql = "DELETE FROM clients *;";
        con.createQuery(sql).executeUpdate();
        }
    }
}
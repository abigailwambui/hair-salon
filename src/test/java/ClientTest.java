import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "abigail", "hair_salon");
    }

    @Test 
    public void client_instantiatesCorrectly_true() {
        Client client = new Client("Zion Mwema", "male", 0711556677);
        assertEquals(true, client instanceof Client);
    }

    @Test 
    public void client_instantiatesWithName_String() {
        Client client = new Client("Zion Mwema", "male", 0711556677);
        assertEquals("Zion Mwema", client.getName());
    }

    @Test 
    public void client_instantiatesWithGender_String() {
        Client client = new Client("Zion Mwema", "male", 0711556677);
        assertEquals("male", client.getGender());
    }
    
    @Test 
    public void client_instantiatesWithphoneNumber_Integer() {
        Client client = new Client("Zion Mwema", "male", 0711556677);
        assertEquals(0711556677, client.getphoneNumber());
    }

    @Test 
    public void client_instantiatesWithStylistId_Integer() {
        Client client = new Client("Zion Mwema", "male", 0711556677);
        assertEquals(0711556677, client.getphoneNumber());
    }

    @Test
    public void equals_returnsTrueIfAllInstancesAreTheSame_true(){
        Client firstClient = new Client("Zion Mwema", "male", 0711556677);
        Client secondClient = new Client("Zion Mwema", "male", 0711556677);
        assertTrue(firstClient.equals(secondClient));
    }

    @Test
    public void save_returnsTrueIfInstancesAreTheSame(){
        Client client = new Client("Zion Mwema", "male", 0711556677);
        client.save();
        assertTrue(Client.all().get(0).equals(client));
    }

    @Test
    public void all_returnAllInstancesOfClientAs_True(){
        Client firstClient = new Client("Zion Mwema", "male", 0711556677);
        firstClient.save();
        Client secondClient = new Client("Zion Mwema", "male", 0711556677);
        secondClient.save();
        assertEquals(true, Client.all().get(0).equals(firstClient));
        assertEquals(true, Client.all().get(1).equals(secondClient));
    }

    @Test
    public void save_assignsIdToObject(){
        Client client = new Client("Zion Mwema", "male", 0711556677);
        client.save();
        Client savedClient = Client.all().get(0);
        assertEquals(client.getId(), savedClient.getId());
    }

    @Test
    public void getId_clientInstantiatesWithAnId(){
        Client client = new Client("Zion Mwema", "male", 0711556677);
        client.save();
        assertTrue(client.getId()>0);
    }

    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
        String sql = "DELETE FROM clients *;";
        con.createQuery(sql).executeUpdate();
        }
    }
}
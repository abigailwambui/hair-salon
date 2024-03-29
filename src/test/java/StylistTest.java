import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StylistTest{

    @Rule 
    public DatabaseRule database = new DatabaseRule();

    @Test 
    public void stylists_instantiatesCorrectly_true() {
        Stylist myStylist = new Stylist("Jackline Mumbi", 1234567890, 24, "abigailw15njuguna@gmail.com", "I have worked for 10years as a stylist for Shakira");
        assertEquals(true, myStylist instanceof Stylist);
    }

    @Test 
    public void getName_stylistInstantiatesWithName_Jackline() {
        Stylist myStylist = new Stylist("Jackline Mumbi", 1234567890, 24, "abigailw15njuguna@gmail.com", "I have worked for 10years as a stylist for Shakira");
        assertEquals("Jackline Mumbi", myStylist.getName());
    }

    @Test 
    public void getphoneNumber_stylistInstantiatesWithphoneNumber_Integer() {
        Stylist myStylist = new Stylist("Jackline Mumbi", 1234567890, 24, "abigailw15njuguna@gmail.com", "I have worked for 10years as a stylist for Shakira");
        assertEquals(1234567890, myStylist.getphoneNumber());
    }

    @Test 
    public void getage_stylistInstantiatesWithage_Integer() {
        Stylist myStylist = new Stylist("Jackline Mumbi", 1234567890, 24, "abigailw15njuguna@gmail.com", "I have worked for 10years as a stylist for Shakira");
        assertEquals(24, myStylist.getAge());
    }

    @Test 
    public void getemail_stylistInstantiatesWithemail_String() {
        Stylist myStylist = new Stylist("Jackline Mumbi", 1234567890, 24, "abigailw15njuguna@gmail.com", "I have worked for 10years as a stylist for Shakira");
        assertEquals("abigailw15njuguna@gmail.com", myStylist.getEmail());
    }

    @Test 
    public void getexperience_stylistInstantiatesWithexperience_String() {
        Stylist myStylist = new Stylist("Jackline Mumbi", 1234567890, 24, "abigailw15njuguna@gmail.com", "I have worked for 10years as a stylist for Shakira");
        assertEquals("I have worked for 10years as a stylist for Shakira", myStylist.getWorkExperience());
    }

    @Test 
    public void getId_stylistsInstantiateWithAnId_1() {
        Stylist myStylist = new Stylist("Jackline Mumbi", 1234567890, 24, "abigailw15njuguna@gmail.com", "I have worked for 10years as a stylist for Shakira");
        myStylist.save();
        assertTrue(myStylist.getId() > 0);
    }

    @Test
    public void all_returnAllInstancesOfStylistAs_True(){
        Stylist firstStylist = new Stylist("Jackline Mumbi", 1234567890, 24, "abigailw15njuguna@gmail.com", "I have worked for 10years as a stylist for Shakira");
        firstStylist.save();
        Stylist secondStylist = new Stylist("Sophia Rehema", 0711556677, 25, "Sophieburrus@gmail.com", "I have worked as a stylist for Beyonce for 2yrs");
        secondStylist.save();
        assertEquals(true, Stylist.all().get(0).equals(firstStylist));
        assertEquals(true, Stylist.all().get(1).equals(secondStylist));
    }

    @Test
    public void equals_returnsTrueIfAllInstancesAreTheSame_true(){
        Stylist firstStylist = new Stylist("Jackline Mumbi", 1234567890, 24, "abigailw15njuguna@gmail.com", "I have worked for 10years as a stylist for Shakira");
        Stylist secondStylist = new Stylist("Jackline Mumbi", 1234567890, 24, "abigailw15njuguna@gmail.com", "I have worked for 10years as a stylist for Shakira");
        assertTrue(firstStylist.equals(secondStylist));
    }

    @Test
    public void save_savesintoDatabase_true(){
        Stylist myStylist = new Stylist("Jackline Mumbi", 1234567890, 24, "abigailw15njuguna@gmail.com", "I have worked for 10years as a stylist for Shakira");
        myStylist.save();
        assertTrue(Stylist.all().get(0).equals(myStylist));
    }

    @Test
    public void find_returnsmyStylistWithSameId_secondStylist() {
        Stylist firstStylist = new Stylist("Jackline Mumbi", 1234567890, 24, "abigailw15njuguna@gmail.com", "I have worked for 10years as a stylist for Shakira");
        firstStylist.save();
        Stylist secondStylist = new Stylist("Sophia Rehema", 0711556677, 25, "Sophieburrus@gmail.com", "I have worked as a stylist for Beyonce for 2yrs");
        secondStylist.save();
        assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
    }

    @Test
    public void update_updatesStylitDescription_true() {
        Stylist myStylist = new Stylist("Jackline Mumbi", 1234567890, 24, "abigailw15njuguna@gmail.com", "I have worked for 10years as a stylist for Shakira");
        myStylist.save();
        myStylist.updateStylist("Zara Versache", 0711556677, 26, "zaraversache@gmail.com", "I have worked as a stylist for Lizzie for 2yrs");
        Stylist updated = new Stylist("Zara Versache", 0711556677, 26, "zaraversache@gmail.com", "I have worked as a stylist for Lizzie for 2yrs");
        assertEquals(updated.getName(), Stylist.find(myStylist.getId()).getName());
    }

    @Test 
    public void getClients_retrievesAllClientsFromDatabase_clientsList() {
        Stylist myStylist = new Stylist("Jackline Mumbi", 1234567890, 24, "abigailw15njuguna@gmail.com", "I have worked for 10years as a stylist for Shakira");
        myStylist.save();
        Client firstClient = new Client("Zion Mwema", "male", 0711556677, myStylist.getId());
        firstClient.save();
        assertTrue(myStylist.every().contains(firstClient));
    }

    @Test
    public void delete_deletesStylist_true() {
        Stylist myStylist = new Stylist("Jackline Mumbi", 1234567890, 24, "abigailw15njuguna@gmail.com", "I have worked for 10years as a stylist for Shakira");
        myStylist.save();
        Client firstClient = new Client("Zion Mwema", "male", 0711556677, myStylist.getId());
        firstClient.save();
        Client secondClient = new Client("Sophia Rehema", "female", 0711556677, myStylist.getId());
        secondClient.save();
        int myStylistId = myStylist.getId();
        int myClientId = firstClient.getId();
        int mySecondClientId = secondClient.getId();
        myStylist.deleteStylist();
        assertEquals(null, Stylist.find(myStylistId));
        assertEquals(null, Client.find(myClientId));
        assertEquals(null, Client.find(mySecondClientId));
    }


    
}
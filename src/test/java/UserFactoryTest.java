import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class UserFactoryTest {

    UserInteractor ui;
    UserFactory uf;
    /*We need 2 of each type of user to test. One which is created by their constructor, and the other
    is created by the factory. The point of the test is to make sure that the two object created
    have all the same properties. Note that this inadvertently tests that the UserInteractor is
    reading and writing files properly, as the test obtains the users created from the end of the
    list which is read from the database.*/
    HR hr ;
    HR hrFac;
    SalaryWorker sw;
    SalaryWorker swFac;
    WageWorker ww;
    WageWorker wwFac;
    Volunteer v;
    Volunteer vFac;

    
    @Before
    public void setUp(){
        ui = new UserInteractor();
        uf = new UserFactory();
        int n = UserFactory.numUsers;
        sw = new SalaryWorker("Boitor", "William", "Male", "william.boitor@gmail.com", "Senior Actuarial Analyst",
                n + 1, 6475504453L, "2003-01-06", 150000.00F, new char[]{'a', 'b', 'c', '1', '2', '3'});
        ww = new WageWorker("Nikan", "Ali", "Male", "ali.nikan@mail.utoronto.ca", "Cook", n + 2,
                6473333333L, "2003-02-01", 30.44F, new char[]{'t', 'u', 'r', 't', 'l', 'e'});
        v = new Volunteer("Ding", "Megan", "Female", "megan.ding@mail.utoronto.ca", "Coding Intern",
                n + 3, 6475555555L, "2003-09-22", new char[]{'l', 'o', 'l'});
        hr = new HR("Khazan", "Ahmed", "Male", "ahmed.khazan@mail.utoronto.ca", n + 4,
                6478888888L, "2003-11-11", new char[]{'p','a','s','s','w', 'o', 'r', 'd'});
        uf.makeUser("Boitor", "William", "Male", "2003", "01",
                "06", 6475504453L, "william.boitor@gmail.com", "Senior Actuarial Analyst",
                "Salary Worker", "abc123", 150000F);
        uf.makeUser("Nikan", "Ali", "Male", "2003", "02", "01",
                6473333333L, "ali.nikan@mail.utoronto.ca", "Cook", "Wage Worker",
                "turtle", 30.44F);
        uf.makeUser("Ding", "Megan", "Female", "2003", "09",
                "22", 6475555555L, "megan.ding@mail.utoronto.ca", "Coding Intern",
                "Entities.Volunteer", "lol", 0);
        uf.makeUser("Khazan", "Ahmed", "Male", "2003", "11", "11,",
                6478888888L, "ahmed.khazan@mail.utorontp.ca", "Entities.HR", "Entities.HR", "password", 0);
        ArrayList<User> users = ui.readData();
        hrFac = users.get(users.size() - 1);
        vFac = users.get(users.size() - 2);
        wwFac = users.get(users.size() - 3);
        swFac = users.get(users.size() - 4);

    }

    @Test(timeout = 50)
    public void testEqualSW(){
        assertEquals(sw.getSurname(), swFac.getSurname());
        assertEquals(sw.getFirstname(), swFac.getFirstname());
        assertEquals(sw.getGender(), swFac.getGender());
        assertEquals(sw.getDob(), swFac.getDob());
        assertEquals(sw.getEmail(), swFac.getEmail());
        assertEquals(sw.getRoleName(), swFac.getRoleName());
        assertEquals(sw.getYearlySalary(), swFac.getYearlySalary());
        assertEquals(sw.getUserNum(), swFac.getUserNum());
        assertEquals(sw.getPassword(), swFac.getPassword());
    }

    @Test(timeout = 50)
    public void testEqualWW(){
        assertEquals(ww.getSurname(), wwFac.getSurname());
        assertEquals(ww.getFirstname(), wwFac.getFirstname());
        assertEquals(ww.getGender(), wwFac.getGender());
        assertEquals(ww.getDob(), wwFac.getDob());
        assertEquals(ww.getEmail(), wwFac.getEmail());
        assertEquals(ww.getRoleName(), wwFac.getRoleName());
        assertEquals(ww.getHourlyWage(), wwFac.getHourlyWage());
        assertEquals(ww.getUserNum(), wwFac.getUserNum());
        assertEquals(ww.getPassword(), wwFac.getPassword());
    }

    @Test(timeout = 50)
    public void testEqualHR(){
        assertEquals(hr.getSurname(), hrFac.getSurname());
        assertEquals(hr.getFirstname(), hrFac.getFirstname());
        assertEquals(hr.getGender(), hrFac.getGender());
        assertEquals(hr.getDob(), hrFac.getDob());
        assertEquals(hr.getEmail(), hrFac.getEmail());
        assertEquals(hr.getUserNum(), hrFac.getUserNum());
        assertEquals(hr.getPassword(), hrFac.getPassword());
    }

    @Test(timeout = 50)
    public void testEqualVolunteer(){
        assertEquals(v.getSurname(), vFac.getSurname());
        assertEquals(v.getFirstname(), vFac.getFirstname());
        assertEquals(v.getGender(), vFac.getGender());
        assertEquals(v.getDob(), vFac.getDob());
        assertEquals(v.getEmail(), vFac.getEmail());
        assertEquals(v.getRoleName(), vFac.getRoleName());
        assertEquals(v.getUserNum(), vFac.getUserNum());
        assertEquals(v.getPassword(), vFac.getPassword());
    }


}

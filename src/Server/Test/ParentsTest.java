package Server.Test;

import Server.Entity.Adult;
import Server.Entity.Child;
import Server.Repository.AdultRepository;
import Server.Result;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParentsTest {
    private static Adult mom;
    private static Child child;
    private AdultRepository adultRepository = new AdultRepository();

    @BeforeAll
    static void createParents() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date calendarDay;
        try {
            calendarDay = simpleDateFormat.parse("2006-05-04");
            child = new Child("Jon", "Snow", "SNWJHN96T27V730G", calendarDay);
        }catch(Exception e) {}
        child.save();
        try {
            calendarDay = simpleDateFormat.parse("1986-05-04");
            mom = new Adult("Catelyn", "Tully", "CRLTLL93D65L153G", calendarDay, "7263017233");
        }catch(Exception e) {}
        mom.getChildren().add(child);
        mom.save();
    }

    @AfterAll
    static void deleteParents() {
        mom.delete();
        child.delete();
    }

    @Test
    void readChildWithParents() {
        Adult readAdult = adultRepository.getAdultByFiscalCode(mom.getFiscalCode());

        assertNotNull(readAdult, "read child error");
        assertTrue(readAdult.getChildren().size() >= 1, "Failed join");
    }

    @Test
    void addParent() {
        assertTrue(child.getParents().add(mom), "Failed add parent operation");

        Result result = child.save();
        assertTrue(result.isSuccess(), "Error during saving operation " + result.getMessages().toString());
    }
}

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.gik.task4.MyDBWork;
import ru.gik.task4.User;
import ru.gik.task4.UsersLoginsApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

@SpringBootApplication
public class UnitTest {
    @Test
    public void createUser() {
        ApplicationContext ctx = SpringApplication.run(UsersLoginsApplication.class);
        MyDBWork mdb = ctx.getBean(MyDBWork.class);

        Random rand = new Random();
        // Setting the upper bound to generate the
        // random numbers in specific range
        int upperbound = 1000;
        // Generating random numbers from 0 - 24
        // using nextInt()
        int int_random = rand.nextInt(upperbound);
        String un="Test"+ int_random;
        //записать новую запись в БД
        mdb.save(new User( un, "Ivanov Petr Vasilievich"));

        User u = mdb.findByUsername(un);
        String uname=u.getUsername();
        Assert.assertEquals(uname,un);
    }
    /*public void updateUser() {
        ApplicationContext ctx = SpringApplication.run(ru.gik.task4.UnitTest.class);
        MyDBWork mdb = ctx.getBean(MyDBWork.class);
        //записать новую запись в БД
        mdb.save(new User( "Test001", "Ivanov Petr Vasilievich"));

        User u = mdb.findByUsername("Test001");

        u.setUsername("!!!");
        mdb.save(u);

        String uname=u.getUsername();
        Assert.assertEquals(uname,"!!!");

    }*/


}
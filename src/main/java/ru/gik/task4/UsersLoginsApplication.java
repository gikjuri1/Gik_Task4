package ru.gik.task4;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class UsersLoginsApplication {
    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(UsersLoginsApplication.class, args);

        Conveyer conv = ctx.getBean(Conveyer.class);
        LogRecord[] lr= conv.perform();
        System.out.println("UsersLoginsApplication LogRecord.Login="+lr[0].Login());

        MyDBWork mdb = ctx.getBean(MyDBWork.class);
        //записать новую запись в БД
        mdb.save(new User( lr[0].Login(), lr[0].FamilyName()+" " + lr[0].AName()+ " " + lr[0].ParentName()));


        //...scripts in pga

        // получить 1 запись и обновить её
/*        Optional<User> u = mdb.findById(3);
        u.ifPresent(System.out::println);
        u.ifPresent(usr -> {
            usr.setUsername(usr.getUsername() + "!!!");
            mdb.save(usr);
        });
        //получить все записи
        List<User> ls = mdb.findAll(Sort.by(Sort.Order.asc("fio")));
        System.out.println("\n\n");
        ls.forEach(System.out::println);
//        //изменим коллекцию
        ls.forEach(usr->{usr.setUsername("FIO: "+usr.getUsername());});
//        //записать коллекцию в БД
        mdb.saveAll(ls);
//        //получить все записи
        ls = mdb.findAll(Sort.by(Sort.Order.asc("username")));
        System.out.println("\n\n");
        ls.forEach(System.out::println);
//
//        //добавим новую запись через нативный SQL
        int res = mdb.insertUser("Lapina001", "Lapina Elena Viktorovna");
        ls = mdb.findAll(Sort.by(Sort.Order.asc("username")));
        System.out.println("\n\n");
        ls.forEach(System.out::println);*/
//
//        //обновим запись через нативный SQL
        int res = mdb.updateUsersSetUsernameForIdNative("Kukuyev005",1);
        List<User> ls = mdb.findAll(Sort.by(Sort.Order.asc("username")));
        System.out.println("\n\n");
        ls.forEach(System.out::println);
//
        HashSet<Login> group1 = new HashSet();
        Login l1=new Login("QuarkXpress");
        l1.setUser(ls.get(1));
        group1.add(l1);

        group1.add(new Login("Word"));
        group1.add(new Login("Excel"));
        group1.add(new Login("Corel"));
        group1.add(new Login("Photoshop"));

        ls.get(1).logins = new HashSet<>();
        ls.get(1).logins.addAll(group1);
        mdb.saveAll(ls);


    }
    }

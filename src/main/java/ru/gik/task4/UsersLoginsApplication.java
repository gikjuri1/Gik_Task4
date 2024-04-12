package ru.gik.task4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
public class UsersLoginsApplication {


    public static void main(String[] args) throws ParseException {

        ApplicationContext ctx = SpringApplication.run(UsersLoginsApplication.class, args);
        //Чтение из файлов и проверки
        Conveyer conv = ctx.getBean(Conveyer.class);
        LogRecord[] lr= conv.perform();
        //conv.perform();
        //System.out.println("UsersLoginsApplication LogRecord.Login="+lr[0].Login());

        //Запись в базу
        MyDBWork mdb = ctx.getBean(MyDBWork.class);
        DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
        Date ad;
        for (int i = 0; i < lr.length; i++) {
            if(lr[i]==null) break;
            //Проверка - заполнена ли дата доступа
            if (!lr[i].Access_date().isBlank()) {

                //Проверка - есть ли запись в таблице users
                User probe = new User();
                probe.setUsername(lr[i].Login());
                Example<User> example=Example.of(probe);
                if(!mdb.exists(example)) {
                    //записать новую запись в таблицу users
                    mdb.save(new User(lr[i].Login(), lr[i].FamilyName() + " " + lr[i].AName() + " " + lr[i].ParentName()));
                }
                //записать записи в таблицу logins
                //List<User> ls = mdb.findAll(Sort.by(Sort.Order.asc("username")));
                List<User> ls = mdb.findByUsername(lr[i].Login());
                HashSet<Login> group1 = new HashSet();
                ad = df.parse(lr[i].Access_date());
                Login l1 = new Login(lr[i].Application(), ad);
                l1.setUser(ls.get(1));
                group1.add(l1);
                ls.get(1).logins = new HashSet<>();
                ls.get(1).logins.addAll(group1);
                mdb.saveAll(ls);
            }
        }

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
        /*int res = mdb.updateUsersSetUsernameForIdNative("Kukuyev005",1);
        List<User> ls = mdb.findAll(Sort.by(Sort.Order.asc("username")));
        System.out.println("\n\n");
        ls.forEach(System.out::println);*/
        //Добавляем дочерние логины
        /*HashSet<Login> group1 = new HashSet();
        Login l1=new Login("QuarkXpress");
        l1.setUser(ls.get(1));
        group1.add(l1);

        group1.add(new Login("Word"));
        group1.add(new Login("Excel"));
        group1.add(new Login("Corel"));
        group1.add(new Login("Photoshop"));

        ls.get(1).logins = new HashSet<>();
        ls.get(1).logins.addAll(group1);
        mdb.saveAll(ls);*/


    }
    }

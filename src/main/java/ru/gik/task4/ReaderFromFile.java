package ru.gik.task4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

@Component
@Loggable
public class ReaderFromFile implements ConveyerDataReader {

    public ReaderFromFile(@Value("${spring.application.pathinput}") String path) {
        this.pathinput = path;
    }
//    public ReaderFromFile(String path) {
//        this.path = path;
//    }

    //public ReaderFromFile(String path) {
    //    this.path = path;
    //}

    // public ReaderFromFile(){};


    public String getPath() {
        return pathinput;
    }

//    public void setPath(String path) {
//        this.path = path;
//    }


    //@Value("${spring.application.pathinput}")
    private String pathinput;

    @Override
    public String read() {
        System.out.println("inputpath = " + getPath());
        String res = "";
        try {
            Scanner sc = new Scanner(new File(getPath()));
            int c = 0;

            while (sc.hasNextLine() && c < 10) {
                res += ("\n" + sc.nextLine());
                c++;
            }
            System.out.println(res);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return res;
    }
    public LogRecord[] readRecords() {
        System.out.println("inputpath = " + getPath());

        LogRecord[] res= new LogRecord[10];
        LogRecord lr;
        //String res="";
        try {
            Scanner sc = new Scanner(new File(getPath()));
            //sc.useDelimiter(" ");
            Integer cc=1;
            String login="";
            String family_name="";
            String aname="";
            String parent_name="";
            String access_date="";
            String application="";
            while (sc.hasNextLine() && cc<=10){
                if(sc.hasNext()) {
                    login = sc.next();
                    family_name=sc.next();
                    aname=sc.next();
                    parent_name=sc.next();
                    access_date=sc.next();
                    application=sc.next();
                    System.out.println("login="+login);
                    System.out.println("Family name="+family_name);
                    System.out.println("Name="+aname);
                    System.out.println("Parent Name="+parent_name);
                    System.out.println("Access Date="+access_date);
                    System.out.println("Application="+application);

                    lr=new LogRecord(login, family_name, aname, parent_name, access_date, application);
                    System.out.println("LogRecord login="+lr.Login());
                    res[cc-1]=lr;
                    System.out.println("res.Login()="+res[cc-1].Login());
                    cc++;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}


package ru.gik.task4;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Conveyer {

    @Setter
    @Getter
    @Autowired
    ConveyerDataReader dr;
    @Setter
    @Getter
    @Autowired
    ConveyerDataWriter dw;

    @Autowired
    public List<ConveyerDataChecker> checks;// = new ArrayList<>();

    public LogRecord[] perform() {
        /*String arg = dr.read();
        for (ConveyerDataChecker chk : checks) {
            arg = chk.check(arg);
        }
        dw.write(arg);*/

        //Чтение из файлов
        LogRecord[] lr =dr.readRecords();
        LogRecord[] lres=new LogRecord[10];
        System.out.println("Conveyer LogRecord.Login="+lr[0].Login());

        //LogRecord l=lr[0];
        for (ConveyerDataChecker chk : checks) {
            for (int i = 0; i < lr.length; i++){
                if(lr[i]==null) break;
                lres[i] = chk.check(lr[i]);
            }
        }

        return lres;
    }

}

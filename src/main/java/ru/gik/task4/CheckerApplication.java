package ru.gik.task4;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class CheckerApplication implements ConveyerDataChecker{
    @Override
    public LogRecord check(LogRecord lr) {

        String newApplication=lr.Application();
        if(lr.Application()!="web" && lr.Application()!="mobile"){
            newApplication="other:"+lr.Application();
        }
        LogRecord ret = new LogRecord(lr.Login(),lr.FamilyName(),lr.AName(),lr.ParentName(),lr.Access_date(),newApplication);

        return ret;
    }

}

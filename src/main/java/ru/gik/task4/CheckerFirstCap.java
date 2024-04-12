package ru.gik.task4;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class CheckerFirstCap implements ConveyerDataChecker  {
    @Override
    public LogRecord check(LogRecord lr) {

        String newFamilyName=lr.FamilyName().substring(0,1).toUpperCase()+lr.FamilyName().substring(1);
        String newAName=lr.AName().substring(0,1).toUpperCase()+lr.AName().substring(1);
        String newParentName=lr.ParentName().substring(0,1).toUpperCase()+lr.ParentName().substring(1);
        LogRecord ret = new LogRecord(lr.Login(),newFamilyName,newAName,newParentName,lr.Access_date(),lr.Application());

        return ret;
    }
}

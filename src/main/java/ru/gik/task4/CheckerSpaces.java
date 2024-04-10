package ru.gik.task4;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class CheckerSpaces implements ConveyerDataChecker {
    @Override
    public String check(String str) {
        return str.trim();
    }
}

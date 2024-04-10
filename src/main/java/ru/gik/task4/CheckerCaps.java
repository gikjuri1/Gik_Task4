package ru.gik.task4;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class CheckerCaps implements ConveyerDataChecker {
    @Override
    public String check(String str) {
        return str.toUpperCase();
    }
}

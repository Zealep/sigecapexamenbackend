package com.sigecap.sigecapexamenbackend.util;

import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        boolean result = NumberUtils.isCreatable("45.23232323");
        System.out.println(result);
    }
}

package com.sigecap.sigecapexamenbackend.util;


import org.jsoup.Jsoup;

import java.io.FileWriter;
import java.io.IOException;

public class Util {

    public static String convertHtmlToString(String html) throws IOException {
            return Jsoup.parse(html).text();
    }
}

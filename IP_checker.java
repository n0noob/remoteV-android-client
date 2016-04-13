package com.example.anoop.tcp_client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Anoop on 4/9/2016.
 */
public class IP_checker {
    public static boolean match_ip(String text) {
        Pattern p = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
        Matcher m = p.matcher(text);
        return m.find();
    }
}

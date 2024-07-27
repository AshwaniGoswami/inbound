package com.kpdcl.inbound.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
@Service
public class DateUtil {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String formatDate(long timestamp) {
        return dateFormat.format(new Date(timestamp));
    }
}

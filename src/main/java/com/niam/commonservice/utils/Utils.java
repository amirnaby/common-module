package com.niam.commonservice.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class Utils {

    private Utils() {
    }

    public static String getServerIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "UnKnown";
        }
    }

    public static TrackingNumbers getTrackingNumbers(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String trackCodeValue = "";
        String externalTrackCodeValue = "";

        if (args != null) {
            for (Object arg : args) {
                if (arg != null) {
                    String input = arg.toString();
                    trackCodeValue = extractTrackCode(input, "trackCode");
                    externalTrackCodeValue = extractTrackCode(input, "externalTrackCode");
                }
            }
        }

        return new TrackingNumbers(trackCodeValue, externalTrackCodeValue);
    }

    public static String extractTrackCode(String input, String field) {
        if (input.contains(field + "=")) {
            int startIndex = input.indexOf(field + "=") + (field + "=").length();
            int endIndex = input.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = input.indexOf("}", startIndex);
            }
            return input.substring(startIndex, endIndex);
        } else return "";
    }

    public record TrackingNumbers(String trackCodeValue, String externalTrackCodeValue) {
    }
}

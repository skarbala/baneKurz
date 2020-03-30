package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String generateBuildName() {
        Date date = new Date(System.currentTimeMillis());
        var sdf = new SimpleDateFormat("HH:mm dd.MM.yyyy");
        return "Build v 2.0.1".concat(" ").concat(sdf.format(date));
    }

 }

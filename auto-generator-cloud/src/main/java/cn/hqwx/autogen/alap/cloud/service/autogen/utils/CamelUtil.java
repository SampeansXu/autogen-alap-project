package cn.hqwx.autogen.alap.cloud.service.autogen.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 说明
 * @Author: xushengbin@hqwx.com
 * @Date: 2021-10-10
 */
public class CamelUtil {

    final static String Septal_Line = "-";

    /**
     * 连接线转驼峰，abc-abca-bc->AbcAbcaBc
     */
    public static String lineToCamel(String name) {
        StringBuilder result = new StringBuilder();
        if ((name == null) || (name.isEmpty())) {
            return "";
        }
        if (!name.contains(Septal_Line)) {
            result.append(name.substring(0, 1).toUpperCase());
            result.append(name.substring(1).toLowerCase());
            return result.toString();
        }
        String[] camels = name.split(Septal_Line);
        for (String camel : camels) {
            if (!camel.isEmpty()) {
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }

        return result.toString();
    }

    /**
     * 驼峰转连接线，AbcAbcaBc->abc-abca-bc
     */
    public static String camelToLine(String name) {
        StringBuilder result = new StringBuilder();
        if ((name != null) && (name.length() > 0)) {
            result.append(name.substring(0, 1).toLowerCase());
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                if ((s.equals(s.toUpperCase())) && (!Character.isDigit(s.charAt(0)))) {
                    result.append(Septal_Line);
                }
                result.append(s.toLowerCase());
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String lineToCamel = lineToCamel("test-demo-pro");
        System.out.println(lineToCamel);
        System.out.println(camelToLine(lineToCamel));
    }
}

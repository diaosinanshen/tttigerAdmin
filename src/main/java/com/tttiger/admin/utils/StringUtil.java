package com.tttiger.admin.utils;


import java.security.MessageDigest;

/**
 * 字符串相关方法
 *
 * @author 秦浩桐
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }


    public static boolean isNotEmpty(String str) {
        return !StringUtil.isEmpty(str);
    }

    /**
     * 将以逗号分隔的字符串转换成字符串数组
     *
     * @param valStr 字符串数组
     * @return String[] 切分好的数组
     */
    public static String[] strList(String valStr) {
        int i = 0;
        String TempStr = valStr;
        String[] returnStr = new String[valStr.length() + 1 - TempStr.replace(",", "").length()];
        valStr = valStr + ",";
        while (valStr.indexOf(',') > 0) {
            returnStr[i] = valStr.substring(0, valStr.indexOf(','));
            valStr = valStr.substring(valStr.indexOf(',') + 1, valStr.length());

            i++;
        }
        return returnStr;
    }

    /**
     * 首字母转小写
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();

        }
    }


    /**
     * 首字母转大写
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 给定一个url判断url是否是一级路径
     * 一级路径:/root
     * 二级路径:/root/path
     *
     * @param path
     * @return
     */
    public static boolean isRootPath(String path) {
        return path.indexOf("/") == path.lastIndexOf("/");
    }


    /**
     * 盐值加密字符
     *
     * @param param 加密字符串
     * @param salt  盐
     * @return 加密字符串
     */
    public static String md5(String param, String salt) {
        return md5(param + salt);
    }

    /**
     * 加密字符串
     *
     * @param s 字符串
     * @return 加密字符串
     */
    public static String md5(String s) {
        char[] hexDigits =
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes("utf-8");
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    private StringUtil(){}
}

package com.example.bookshop.util;

import org.omg.PortableServer.IdUniquenessPolicyOperations;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class StringFormatUtil {
    /**
     * 判断给定字符串 是否为空或者包含空格
     * @param input 需要检测的字符串
     * @return 是否含任何形式的空格
     */
    public static boolean hasEmpty(String input) {
        if (input == null || input.length() == 0)
            return true;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == ' ' || c == '\t' || c == '\r' || c == '\n') {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断给定的字符串是否是一个合法的11位手机号码
     * @param phoneNum 需要检测的手机号码
     * @return 是否是一个合法的11位手机号码
     */
    public static boolean isPhoneNum(String phoneNum) {
        Pattern phone = Pattern
                .compile("^[1]([3-9])[0-9]{9}$");
        if (hasEmpty(phoneNum)) return false;
        return phone.matcher(phoneNum).matches();
    }

    /**
     * 判断输入是否为double类型的数字
     * @param input 需要检测的字符串
     * @return 是否为double类型的数字
     */
    public static boolean isDouble(String input) {
        try {
            Double.parseDouble(input.trim());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 获取当前系统时间 yyyy-MM-dd
     * @return 当前系统时间 yyyy-MM-dd
     */
    public static String getDataTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }

    /**
     * 判断一个字符串是否全是数字组成
     * @param str 待检测的字符串
     * @return 此字符串是否全是数字
     */
    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str.trim());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 判断给定的路径是否为图片，适用的图片格式为：bmp/gif/jpeg/jpg/png/raw/tif
     * @param imagePath 图片路径
     * @return 是否为图片路径
     */
    public static boolean isImage(String imagePath) {
        MimetypesFileTypeMap mtftp = new MimetypesFileTypeMap();
        mtftp.addMimeTypes("image bmp gif jpeg jpg png raw tif");
        String mimetype= mtftp.getContentType(imagePath);
        String type = mimetype.split("/")[0];
        if(!type.equals("image")) return false;

        try {
            BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
            if (bufferedImage != null) return true;
        } catch (IOException e) {
            //e.printStackTrace();
        }
        try {
            URL url = new URL(imagePath);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            return urlCon.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将传入的字符串数组转成SQL需要的数组形式
     * @param arr 字符串数组
     * @return SQL需要的数组形式
     */
    public static String StrArrToSqlIn(String[] arr) {
        if (arr == null || arr.length == 0) return null;

        StringBuilder buffer = new StringBuilder();
        for (String s : arr) {
            buffer.append("'").append(s).append("'").append(",");
        }

        return buffer.toString().substring(0, buffer.length() - 1);
    }

    /**
     * 将int数组转换成SQL需要的数组
     * @param arr int数组
     * @return SQL数组
     */
    public static String intArrToSqlIn(int[] arr) {
        if (arr == null || arr.length == 0) return null;

        StringBuilder buffer = new StringBuilder();
        for (int i : arr) {
            buffer.append(i).append(",");
        }
        return buffer.toString().substring(0, buffer.length() - 1);
    }

    /**
     * 将double[][2]数组转成SQL语句需要的字符串
     * 例如将price在区间为[20,30],[100,200]的转成 price BETWEEN 20.0 AND 30.0 OR price BETWEEN 100.0 AND 200.0
     * @param arr 查找的范围区间
     * @param attribute 查找的属性
     * @return 转成的SQL语句
     */
    public static String doubleArrToSqlRangeIn(double[][] arr, String attribute) {
        if (arr == null || arr.length == 0 || attribute == null || attribute.length() == 0)
            return null;

        StringBuilder buffer = new StringBuilder();
        for (double[] one : arr) {
            buffer.append(attribute).append(" BETWEEN ").append(one[0]).append(" AND ").append(one[1]).append(" OR ");
        }
        return buffer.toString().substring(0 ,buffer.length() - 4);
    }

    /**
     * 将格式为{"num1,num2","num3,num4"}类型的一维数组转换成{{num1, num2}, {num1, num2}}的double类型的数组
     * @param arr 一维字符串类型的数组，中间以 , 切分
     * @return 切分后的double类型的二维数组
     */
    public static double[][] splitWebStrArr(String[] arr) {
        double[][] result = new double[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            String[] strArr = arr[i].split("a");
            double[] one = new double[] {Double.parseDouble(strArr[0]), Double.parseDouble(strArr[1])};
            result[i] = one;
        }
        return result;
    }

    /**
     * 将输入字符串前后加上%
     * @param str 输入字符串
     * @return %str%
     */
    public static String addLikeSymbol(String str) {
        return "%" + str + "%";
    }

    /**
     * 随机生成5位id
     * @return 生成的id
     */
    public static String randomString()
    {
        String allChar = "0123456789";
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for(int i=0;i<5;i++)
        {
            sb.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return sb.toString();
    }
}

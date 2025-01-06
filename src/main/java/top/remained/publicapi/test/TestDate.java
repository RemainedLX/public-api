package top.remained.publicapi.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author lx
 * @date 2025/1/6
 * @description
 */
public class TestDate {
    public static void main(String[] args)  {
        System.out.println(LocalDateTime.now());
    }
    public void test1() throws ParseException{
        String date = "2024-01-28";
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(format1.format(new Date(date))); 不行
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy年MM月dd日");
        Date parse = format1.parse(date);
        System.out.println(format2.format(parse));
    }
}
package test.com.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class stime {
    public static void main(String[] args){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stime=format.format(new Date());
        System.out.println(stime);
    }
}

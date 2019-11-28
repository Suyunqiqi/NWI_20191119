package test.com.dao;

import com.dao.DaoImpl;
import org.junit.Test;

public class DaoTest {

    @Test
    public void testConn(){
        DaoImpl di=new DaoImpl();
        try {
            di.creatConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(di.getConn());
    }
}
package com.dao;

import java.sql.ResultSet;

public interface DaoInterface {
    void creatConn() throws Exception;
    void creatPtmt(String sql,Object...objects) throws Exception;
    ResultSet query() throws Exception;
    int update() throws Exception;
    void closeConn() throws Exception;
    void closePtmt() throws Exception;
    void closers(ResultSet rs) throws Exception;

}

package org.example.hutool;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelTest {

    public static void main(String[] args) throws Throwable {
        ExcelReader reader = ExcelUtil.getReader("C:\\Users\\11622\\Desktop\\filterBank.xls");
        int rowCount = reader.getRowCount();
        List<Map<String, Object>> maps = reader.readAll();
        System.out.println(rowCount);
        int readLine = 1000;
        int currentLine = 0;
        while (currentLine <= rowCount) {
            List<List<Object>> read = reader.read(currentLine, readLine);
            System.out.println(read);
        }


//        action();
    }

    public static void action() throws Throwable {

        try {
            int ColumnCount;
            //int RowCount;
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://192.168.0.108:3306/huichengtest?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai"; //换成要连接的数据库信息
            String user = "root";
            String password = "Huicheng@123";
            Class.forName ( driver );
            Connection conn = (Connection) DriverManager.getConnection ( url, user, password );
            if (!conn.isClosed ()) {
                System.out.println ( "数据库连接成功：" );




                String sqls = "SELECT * FROM discount_bank_config "; //sql
                PreparedStatement ps = conn.prepareStatement ( sqls );
                ResultSet rs = ps.executeQuery ();
                List list = new ArrayList<String>();

                ResultSetMetaData rsmd = rs.getMetaData ();
                while (rs.next ()) {
                    ColumnCount = rsmd.getColumnCount ();
                    Map<String,Object> rowData = new HashMap<String,Object>();
                    for (int i = 1; i <= ColumnCount; i++) {
                        rowData.put(rsmd.getColumnName(i),rs.getObject(i));
                    }
                    //将ResultSet结果集写入list
                    list.add(rowData);
                }
                System.out.println ( list );
                ps.close ();
                conn.close ();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }
}

package com.isoft.framecujdbc.db;

import com.isoft.framecujdbc.domain.Angajat;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iurasun
 */
public class TestSingleton {
    public static void main(String[] args) throws SQLException, IOException {
        try {
            System.out.println("apel 1");
            MyDataSource dataSource =  MyDataSource.getInstance();  
            
            
             
            Connection conn=dataSource.getConnection();


            if(conn !=null){
                System.out.println("conexiunea creata cu successssssssssss");
            }
            

           testConnection(conn);           
            




            //new MyDataSource();
            // MyDataSource dataSource1 =  new MyDataSource();  //new MyDataSource();
            
//            System.out.println("apel 2");
//            MyDataSource dataSource2 =  MyDataSource.getInstance();    //new MyDataSource();
//            // MyDataSource dataSource2 =  new MyDataSource();
//            
//            System.out.println("id 1 = "+dataSource1.getId());
//            System.out.println("id 2 = "+dataSource2.getId());
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void testConnection(Connection conn) throws SQLException {

           
        String sql="SELECT * FROM ANGAJATI;";
        
        Statement stmt =conn.createStatement();
        
        ResultSet rs=stmt.executeQuery(sql);
        
        while(rs.next()){
             
            Angajat angajat = new Angajat( rs.getInt(1), 
                                           rs.getString(2), 
                                           rs.getString(3), 
                                           rs.getInt(4)  );
            
            System.out.println(angajat);
            
        }
        
    
    }
}

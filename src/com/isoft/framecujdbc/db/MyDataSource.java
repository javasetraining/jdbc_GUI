package com.isoft.framecujdbc.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author iurasun
 */
public class MyDataSource {

   
   private DbProperties dbProperties;// = new DbProperties();// din loadP()
   private Connection connection; 
   
    //singleton
    private static MyDataSource instance;

    public static MyDataSource getInstance() throws ClassNotFoundException, IOException {
        if (instance == null) {
            instance = new MyDataSource();
        }

        return instance;
    }

    private MyDataSource() throws ClassNotFoundException, IOException {

    
            // pentru testare singleton
            System.out.println("s-a creat un obiect");
            id = ++contor;
            //////////////////
            
            
            loadProperties();
            System.out.println("proprietatile incarcate cu succes");
            
            Class.forName(dbProperties.getDbdrivername());
            System.out.println("driver incarcat cu suuces!");
        
    }
    //end singleton
  

    private void loadProperties() throws IOException {
     //de facut!!!!
        
        DbPropertiesUtil propsService=DbPropertiesUtil.getInstance();
       
        dbProperties = propsService.citeste(Constants.PROPS_FILE_NAME);
        
    }

    public Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed()){
            createConnection();
        }
        
        return connection;
        
    }

    private void createConnection() throws SQLException {
      
      connection = DriverManager.getConnection(dbProperties.getDburl(),
                                    dbProperties.getDbusername(), 
                                    dbProperties.getDbpassword());
    }
    
    
    
    
    
    // pentru testare singleton
    private static int contor;
    private int id;

    public int getId() {
        return id;
    }

}

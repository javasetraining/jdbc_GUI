
package com.isoft.framecujdbc.db;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author iurasun
 */
public class DbPropertiesUtil {
    

    ////
    
    private static DbPropertiesUtil instance ;

    
    public static DbPropertiesUtil getInstance() {
        
        if(instance  == null){
            instance =new DbPropertiesUtil();
        }
        return instance;
    }
    
      private DbPropertiesUtil(){
      
      }      
            
//end sigleton
    
    public  void scrie(DbProperties dbProperties, String fileName) throws IOException{
            
        Properties props=new Properties();
        
        props.setProperty("dburl", dbProperties.getDburl() );
        props.setProperty("dbusername", dbProperties.getDbusername() );
        props.setProperty("dbpassword", dbProperties.getDbpassword() );
        props.setProperty("dbdrivername", dbProperties.getDbdrivername() );
 
        props.store(new FileOutputStream(fileName), new java.util.Date().toString() );
        
    }
     
    public DbProperties citeste(String fileName) throws IOException{
    
        DbProperties dbProperties = new DbProperties();
        
        Properties props=new Properties();
        props.load(new FileInputStream(fileName));
        
        dbProperties.setDburl( props.getProperty("dburl") );
        dbProperties.setDbusername(props.getProperty("dbusername") );
        dbProperties.setDbpassword(props.getProperty("dbpassword") );
        dbProperties.setDbdrivername(props.getProperty("dbdrivername") );
         
        return dbProperties;
        
    }
    
}

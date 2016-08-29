
package com.isoft.framecujdbc.db;

/**
 *
 * @author iurasun
 */
public class DbProperties  {
    private String dburl = "jdbc:mysql://localhost:3306/test19";
    private String dbusername = "root";
    private String dbpassword = "free";
    private String dbdrivername = "com.mysql.jdbc.Driver";

    public DbProperties() {
    }

    public DbProperties(String dburl, String dbusername, String dbpassword, String dbdrivername) {
        this.dburl = dburl;
        this.dbusername=dbusername;
        this.dbpassword=dbpassword;
        this.dbdrivername=dbdrivername;
        
    }

    public String getDburl() {
        return dburl;
    }

    public void setDburl(String dburl) {
        this.dburl = dburl;
    }

    public String getDbusername() {
        return dbusername;
    }

    public void setDbusername(String dbusername) {
        this.dbusername = dbusername;
    }

    public String getDbpassword() {
        return dbpassword;
    }

    public void setDbpassword(String dbpassword) {
        this.dbpassword = dbpassword;
    }

    public String getDbdrivername() {
        return dbdrivername;
    }

    public void setDbdrivername(String dbdrivername) {
        this.dbdrivername = dbdrivername;
    }

    
    
    
    
    
}

package com.isoft.framecujdbc.dao.impl;

import com.isoft.framecujdbc.db.MyDataSource;
import com.isoft.framecujdbc.domain.Departament;
import com.isoft.framecujdbc.dao.DepartamentDaoIntf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DepartamentDaoImpl implements DepartamentDaoIntf {

    private Connection conn;

    public DepartamentDaoImpl(Connection conn) {
        this.conn = conn;
    }

    public DepartamentDaoImpl() {
        try {checkConnection();
            conn =  MyDataSource.getInstance().getConnection() ;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DepartamentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DepartamentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
    
    @Override
    public List<Departament> findAll() throws Exception {
        List<Departament> listaDepartamenti = new ArrayList<Departament>();
        checkConnection();
        try {
           
            String sql = "SELECT * FROM departament"; //SQL 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Departament departament = new Departament(
                        rs.getInt(1),
                        rs.getString(2)
                );
                listaDepartamenti.add(departament);
            }

        } catch (Exception e) {
            LOG.severe(e.toString());
            e.printStackTrace(new PrintStream(new FileOutputStream ("errors.txt"), true));
            throw new Exception("exceptie la obtinerea listei departamentilor.. ");
        }

        return listaDepartamenti;
    }
    private static final Logger LOG = Logger.getLogger(DepartamentDaoImpl.class.getName());

    @Override
    public void save(Departament departament)throws Exception {
        checkConnection();
         try {
           
            String sql = "INSERT INTO departament VALUES(null, '"+departament.getDenumire()+"');"; //SQL 
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();

        } catch (Exception e) {
            LOG.severe(e.toString());
            throw new Exception (e);
        }

        
    }

    @Override
    public Departament findById(int id) throws Exception {
        checkConnection();
          Departament departament=null;
          
        try {
           
            String sql = "SELECT * FROM departament WHERE id="+id; //SQL 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
              departament = new Departament(
                        rs.getInt(1),
                        rs.getString(2)
                );
          
            }

        } catch (Exception e) {
            LOG.severe(e.toString());
            throw new Exception("exceptie la obtinerea departamentului.. ");
        }

        
        return departament;
        
        
    }

    @Override
    public void update(Departament departament) throws Exception {
        PreparedStatement pstmt=null;
        try {
            
            checkConnection();
            conn.setAutoCommit(false); // true implicit
      
           // System.out.println("DEP =  "+departament.toFullString());
            
            //UPDATE `test19`.`departament` SET `denumire`='ssssssssssssssssssssssssssssss22' WHERE `id`='24';

            String sql = "UPDATE departament SET denumire=? WHERE id=?";
            
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,departament.getDenumire());
            pstmt.setInt(2,departament.getId());
            
            pstmt.executeUpdate();
            conn.commit();

        } catch (Exception e) {
            LOG.severe(e.toString());
           
            conn.rollback();
        } finally{
            pstmt.close();
        }
    }

    @Override
    public void delete(Departament departament) throws Exception{
         try {
             checkConnection();
            String sql = "DELETE FROM departament WHERE id="+departament.getId(); //SQL 
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            LOG.severe(e.toString());
        }
    }

    @Override
    public List<Departament> findAll(Departament departament) throws Exception {
      
         List<Departament> listaDepartamenti = new ArrayList<Departament>();
        checkConnection();
        try {
           
            String sql = "SELECT * FROM ANGAJAT  WHERE denumire LIKE ?"; 
              
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,departament.getDenumire());
            ResultSet rs = pstmt.executeQuery();
            
           Departament dDepartament = null;
            while (rs.next()) {
                 dDepartament= new Departament(
                        rs.getInt(1),
                        rs.getString(2)
                );
                listaDepartamenti.add(dDepartament);
            }

        } catch (Exception e) {
            LOG.severe(e.toString());
            e.printStackTrace(new PrintStream(new FileOutputStream ("errors.txt"), true));
            throw new Exception("exceptie la obtinerea listei departamentilor.. ");
        }

        return listaDepartamenti;
    }

  void checkConnection(){
        try {
            if(conn==null || conn.isClosed()){
        
            conn =  MyDataSource.getInstance().getConnection() ;
             
            }
        } catch (Exception ex) {
            Logger.getLogger(DepartamentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
    
}

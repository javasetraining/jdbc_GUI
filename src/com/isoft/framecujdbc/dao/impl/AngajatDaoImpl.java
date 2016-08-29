package com.isoft.framecujdbc.dao.impl;

import com.isoft.framecujdbc.dao.AngajatDaoIntf;
import com.isoft.framecujdbc.domain.Departament;
import com.isoft.framecujdbc.domain.Angajat;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author iurasun
 */
public class AngajatDaoImpl implements AngajatDaoIntf {

    private Connection conn;

    public AngajatDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Angajat> findAll() throws Exception {
        List<Angajat> listaAngajati = new ArrayList<Angajat>();
        
        try {
           
            String sql = "SELECT * FROM ANGAJAT"; //SQL 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Angajat angajat = new Angajat(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                );
                listaAngajati.add(angajat);
            }

        } catch (Exception e) {
            LOG.severe(e.toString());
            e.printStackTrace(new PrintStream(new FileOutputStream ("errors.txt"), true));
            throw new Exception("exceptie la obtinerea listei angajatilor.. ");
        }

        return listaAngajati;
    }
    private static final Logger LOG = Logger.getLogger(AngajatDaoImpl.class.getName());

    @Override
    public void save(Angajat angajat) {
        
         try {
           if(angajat.getDepartament()==null || angajat.getDepartament().getId()==0){
             throw new Exception("Departamentul nu este selectat");
           }
             
            String sql = "INSERT INTO ANGAJAT VALUES(null, '"+angajat.getNume()+"', '"+angajat.getPrenume()+"', "+angajat.getSalariu()+",  "+angajat.getDepartament().getId()+" );"; //SQL 
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();

        } catch (Exception e) {
            LOG.severe(e.toString());
        }

        
    }

    @Override
    public Angajat findById(int id) throws Exception {
        
          Angajat angajat=null;
          
        try {
           
            String sql = "SELECT * FROM ANGAJAT WHERE id="+id; //SQL 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
              angajat = new Angajat(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                );
          
            }

        } catch (Exception e) {
            LOG.severe(e.toString());
            throw new Exception("exceptie la obtinerea angajatului.. ");
        }

        
        return angajat;
        
        
    }

    @Override
    public void update(Angajat angajat) throws Exception {
        try {
            
            
            conn.setAutoCommit(false); // true implicit
            
//            String sql = "UPDATE ANGAJAT SET nume='"+angajat.getNume()
//                    +"', prenume='"+angajat.getPrenume()
//                    +"', slariu="+angajat.getSalariu()
//                    +" WHERE id="+angajat.getId();
//            
//            Statement stmt = conn.createStatement();
//            stmt.executeUpdate(sql);
//
//            
            String sql = "UPDATE ANGAJAT SET nume=? , prenume=? , slariu=? WHERE id=?";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,angajat.getNume());
            pstmt.setString(2,angajat.getPrenume());
            pstmt.setInt(3,angajat.getSalariu());
            pstmt.setInt(4,angajat.getId());
            
            
            
            pstmt.executeUpdate(sql);
            conn.commit();
            
            pstmt.close();
            

        } catch (Exception e) {
            LOG.severe(e.toString());
            
            conn.rollback();
        }
    }

    @Override
    public void delete(Angajat angajat) throws Exception{
         try {
            String sql = "DELETE FROM ANGAJAT WHERE id="+angajat.getId(); //SQL 
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            LOG.severe(e.toString());
        }
    }

    @Override
    public List<Angajat> findAll(Angajat angajat) throws Exception {
      
         List<Angajat> listaAngajati = new ArrayList<Angajat>();
        
        try {
           
            String sql = "SELECT * FROM ANGAJAT  WHERE nume LIKE ? AND prenume LIKE ? AND slariu > ?"; //SQL 
            
            Departament departament =angajat.getDepartament();
            boolean hasDepartament=false;
            if( departament !=null && departament.getId() > 0){
              sql+=" AND idDepartament=?"; 
              hasDepartament=true;
            }
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, angajat.getNume()+"%");
            pstmt.setString(2, angajat.getPrenume()+"%");
            pstmt.setInt(3,angajat.getSalariu() );
            if(hasDepartament){
               pstmt.setInt(4,departament.getId() );
            }
            
            
            ResultSet rs = pstmt.executeQuery();
            
           Angajat angajatLocal = null;
            while (rs.next()) {
               
                angajatLocal = new Angajat(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4), 
                        new Departament()
                );
                
                if(hasDepartament){
                  angajatLocal.setDepartament(departament);
                }
                
                
                listaAngajati.add(angajatLocal);
            }

        } catch (Exception e) {
            LOG.severe(e.toString());
            e.printStackTrace(new PrintStream(new FileOutputStream ("errors.txt"), true));
            throw new Exception("exceptie la obtinerea listei angajatilor.. ");
        }

        return listaAngajati;
    }
    
    
}

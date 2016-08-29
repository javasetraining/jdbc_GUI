package com.isoft.framecujdbc.servicii;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iurasun
 */
public class FileTextUtil {

    public static void writeTextToFile(String text, String fileName){
    
        
        FileOutputStream fos=null;
        
        try {
            
            fos=new FileOutputStream(fileName); // genereaza exceptie verificata de compilator
            fos.write(text.getBytes() );
            fos.close();
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileTextUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileTextUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    public static String readFileToText(String fileName){
    
        String text=null;
        
        FileInputStream fis=null;
        
        try {
            fis=new FileInputStream(fileName);
            
            int size = fis.available();
            byte[] bytes=new byte[size];
            
            fis.read(bytes);
            fis.close();
            
            text= new String(bytes);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileTextUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileTextUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return text;
        
    }
    
    
     public static void writeBytesToFile(byte[] bytes, String fileName){
    
        
        FileOutputStream fos=null;
        
        try {
            
            fos=new FileOutputStream(fileName); // genereaza exceptie verificata de compilator
            fos.write(bytes);
            fos.close();
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileTextUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileTextUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    public static byte[] readFileToBytes(String fileName){
    
       
        FileInputStream fis=null;
         byte[] bytes=null;
        try {
            fis=new FileInputStream(fileName);
            
            int size = fis.available();
            bytes=new byte[size];
            
            fis.read(bytes);
            fis.close();
            
          
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileTextUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileTextUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return bytes;
        
    }
    
    public static void copiazaFile(String fileDeCirtot, String fileDeScris) {
        writeBytesToFile( readFileToBytes(fileDeCirtot) , fileDeScris);
        
    }
 
    
}

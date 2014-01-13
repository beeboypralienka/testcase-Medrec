/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Services;

import com.mysql.jdbc.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import medrecapp.Dao.PerawatDao;
import medrecapp.Entity.Perawat;
import medrecapp.Interfaces.PerawatInterface;
import medrecapp.KoneksiDatabase.KoneksiDB;

/**
 *
 * @author Hady
 */
public class PerawatService {
    private Connection connection;
    private PerawatInterface prwi;

    public PerawatService() {
        this.connection = KoneksiDB.getConnection();
        this.prwi = new PerawatDao(connection);
    }
    
    public void serviceInsertPerawat(Perawat p){
        try{
            connection.setAutoCommit(false);
            prwi.insertPerawat(p);
            connection.setAutoCommit(true);
        }catch(SQLException se){
            try{
                connection.rollback();
                connection.setAutoCommit(true);
            }catch(SQLException see){
                Logger.getLogger(PerawatService.class.getName()).log(Level.SEVERE, null, see);
            }
            Logger.getLogger(PerawatService.class.getName()).log(Level.SEVERE, null, se);
        }
    }
    
    public void serviceUpdatePerawat(Perawat p, String noPerawat){
        try{
            connection.setAutoCommit(false);
            prwi.updatePerawat(p, noPerawat);
            connection.setAutoCommit(true);
        }catch(SQLException se){
            try{
                connection.rollback();
                connection.setAutoCommit(true);
            }catch(SQLException see){
                Logger.getLogger(PerawatService.class.getName()).log(Level.SEVERE, null, see);
            }
            Logger.getLogger(PerawatService.class.getName()).log(Level.SEVERE, null, se);
        }
    }
    
    public void serviceDeletePerawat(String noPerawat){
        try{
            connection.setAutoCommit(false);
            prwi.deletePerawat(noPerawat);
            connection.setAutoCommit(true);
        }catch(SQLException se){
            try{
                connection.rollback();
                connection.setAutoCommit(true);
            }catch(SQLException see){
                Logger.getLogger(PerawatService.class.getName()).log(Level.SEVERE, null, see);
            }
            Logger.getLogger(PerawatService.class.getName()).log(Level.SEVERE, null, se);
        }
    }
    
    public List serviceGetAllPerawat(){
        try{
            return prwi.getAllPerawat();
        }catch(SQLException se){
            Logger.getLogger(PerawatService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public List serviceGetAllPerawatByNo(String noPerawat){
        try{
            return prwi.getAllPerawatByNo(noPerawat);
        }catch(SQLException se){
            Logger.getLogger(PerawatService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public List serviceGetAllPerawatByNm(String nmPerawat){
        try{
            return prwi.getAllPerawatByNm(nmPerawat);
        }catch(SQLException se){
            Logger.getLogger(PerawatService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public String serviceGetMaxNoPerawat(){
        try{
            return prwi.getMaxNoPerawat();
        }catch(SQLException se){
            Logger.getLogger(PerawatService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }
}

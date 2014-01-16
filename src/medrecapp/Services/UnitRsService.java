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
import medrecapp.Dao.UnitRsDao;
import medrecapp.Entity.UnitRs;
import medrecapp.Interfaces.UnitRsInterface;
import medrecapp.KoneksiDatabase.KoneksiDB;

/**
 *
 * @author Hady
 */
public class UnitRsService {
    private Connection connection;
    private UnitRsInterface uri;

    public UnitRsService() {
        this.connection = KoneksiDB.getConnection();
        this.uri = new UnitRsDao(connection);
    }
    
    public void serviceInsertUnitRs(UnitRs ur){
        try{
            connection.setAutoCommit(false);
            uri.insertUnitRs(ur);
            connection.setAutoCommit(true);
        }catch(SQLException se){
            try{
                connection.rollback();
                connection.setAutoCommit(true);
            }catch(SQLException see){
                Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, see);
            }
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
        }
    }
    
    public void serviceUpdateUnitRs(UnitRs ur, String idUnit){
        try{
            connection.setAutoCommit(false);
            uri.updateUnitRs(ur, idUnit);
            connection.setAutoCommit(true);
        }catch(SQLException se){
            try{
                connection.rollback();
                connection.setAutoCommit(true);
            }catch(SQLException see){
                Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, see);
            }
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
        }
    }
    
    public void serviceDeleteDokter(String idUnit){
        try{
            connection.setAutoCommit(false);
            uri.deleteUnitRs(idUnit);
            connection.setAutoCommit(true);
        }catch(SQLException se){
            try{
                connection.rollback();
                connection.setAutoCommit(true);
            }catch(SQLException see){
                Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, see);
            }
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
        }
    }
    
    public List serviceGetAllUnitRs(){
        try{
            return uri.getAllUnitRs();
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }
}

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
import medrecapp.Dao.PendaftaranDao;
import medrecapp.Entity.Pendaftaran;
import medrecapp.Interfaces.PendaftaranInterface;
import medrecapp.KoneksiDatabase.KoneksiDB;

/**
 *
 * @author Hady
 */
public class PendaftaranService {
    private Connection connection;
    private PendaftaranInterface pi;

    public PendaftaranService() {
        this.connection = KoneksiDB.getConnection();
        this.pi = new PendaftaranDao(connection);
    }
    
    public void serviceInsertPendaftaran(Pendaftaran pn){
        try{
            connection.setAutoCommit(false);
            pi.insertPendaftaran(pn);
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
    
    public void serviceUpdateDokter(Pendaftaran pn, String noDaftar){
        try{
            connection.setAutoCommit(false);
            pi.updatePendaftaran(pn, noDaftar);
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
    
    public void serviceDeletePendaftaran(String noDaftar){
        try{
            connection.setAutoCommit(false);
            pi.deletePendaftaran(noDaftar);
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
    
    public List serviceGetAllPendaftaran(){
        try{
            return pi.getAllPendaftaran();
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }
}

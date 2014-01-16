/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Interfaces;

import java.sql.SQLException;
import java.util.List;
import medrecapp.Entity.Pendaftaran;

/**
 *
 * @author Fachrul Pralienka BM
 */
public interface PendaftaranInterface {

    public void insertPendaftaran(Pendaftaran pn)throws SQLException;
    public void updatePendaftaran(Pendaftaran pn, String noDaftar)throws SQLException;
    public void deletePendaftaran(String noDaftar)throws SQLException;
    public List getAllPendaftaran()throws SQLException;


}

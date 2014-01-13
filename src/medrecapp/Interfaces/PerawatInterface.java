/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Interfaces;

import java.sql.SQLException;
import java.util.List;
import medrecapp.Entity.Perawat;

/**
 *
 * @author Fachrul Pralienka BM
 */
public interface PerawatInterface {

    // Methid standar CRUD perawat
    public void insertPerawat(Perawat pr)throws SQLException;
    public void updatePerawat(Perawat pr, String noPerawat)throws SQLException;
    public void deletePerawat(String noPerawat)throws SQLException;
    public List getAllPerawat()throws SQLException;

    // Method untuk mencari perawat via JTextfield
    public List getAllPerawatByNo(String noPerawat)throws SQLException;
    public List getAllPerawatByNm(String nmPerawat)throws SQLException;

    // Method untuk memanggil nilai maximal nomor perawat
    public String getMaxNoPerawat()throws SQLException;

    // Mthod untuk menge-test data perawat

}

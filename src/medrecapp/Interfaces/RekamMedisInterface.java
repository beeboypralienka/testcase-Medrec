/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Interfaces;

import java.sql.SQLException;
import java.util.List;
import medrecapp.Entity.RekamMedis;

/**
 *
 * @author Fachrul Pralienka BM
 */
public interface RekamMedisInterface {

    // Insert pendaftaran pasien ke poliklinik
    public void insertRekamMedis(RekamMedis rm)throws SQLException;

    // Ubah Status Pasien, masuk ke Poliklinik
    public void updatePasienMasukPoli(String noDaftar)throws SQLException;

//    public void deleteRekamMedis(String noDaftar)throws SQLException;
//    public List getAllRekamMedis()throws SQLException;
    
    // Menampilkan rekam medis berdasarkan Nomor Pendaftaran
    public List getRekamMedisByNoDaftar(String noDaftar)throws SQLException;
    
    // Menampilkan riwayat rekam medis berdasarkan Nomor RM Pasien
    public List getRekamMedisByNoRm(String noRm)throws SQLException;

    // Membuat nomor pendaftaran otomatis
    public String generateNomor(String tanggal)throws SQLException;

    // Menampilkan pasien poliklinik yang "Antri" berdasarkan Poliklinik dan Tanggal
    public List getRekamMedisPasienAntri(String poli, String tanggal)throws SQLException;

    // Menampilkan pasien poliklinik berdasarkan Poliklinik dan Tanggal
    public List getRekamMedisPasienPoli(String poli, String tanggal)throws SQLException;
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Dao;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import medrecapp.Entity.RekamMedis;
import medrecapp.Interfaces.RekamMedisInterface;

/**
 *
 * @author Hady
 */
public class RekamMedisDao implements RekamMedisInterface{
    private Connection connection;

    private final String insertRekamMedis = "INSERT INTO rekam_medis "
            + "(no_daftar, no_rm, no_staf, bagian_spesialis, id_jaminan, no_dokter, status, tgl_daftar, no_perawat) "
            + "VALUES(?,?,?,?,?,?,?,?, null)";

    private final String updatePasienMasukPoli =
            "UPDATE rekam_medis SET status='Ditangani' WHERE no_daftar=?";

//    private final String deleteRekamMedis = "DELETE FROM rekam_medis WHERE no_daftar=?";
//    private final String getAllRekamMedis = "SELECT * FROM rekam_medis";

    private final String getRekamMedisByNoDaftar = 
            "SELECT p.nm_perawat, r.nadi, r.temperatur, r.pernapasan, r. kesadaran, r.anamnesa, r. tinggi_bdn, "
            + "r.berat_bdn, r.tensi_darah, r.diagnosis, r.terapi "
            + "FROM rekam_medis r, perawat p "
            + "WHERE r.no_perawat = p.no_perawat AND no_daftar = ?";

    private final String getRekamMedisByNoRm =
            "SELECT no_daftar, st.nm_staf, sp.nm_spesialis, rm.id_jaminan, dk.nm_dokter, rm.tgl_daftar, rm.status "
            + "FROM rekam_medis rm, staf st, spesialis sp, jaminan jm, dokter dk "
            + "WHERE no_rm = ? AND st.no_staf = rm.no_staf AND sp.id_spesialis = rm.bagian_spesialis AND "
            + "jm.id_jaminan = rm.id_jaminan AND dk.no_dokter = rm.no_dokter ORDER BY tgl_daftar ASC";

    private final String generateNomor = "SELECT MAX(SUBSTR(no_daftar,9,12))+1 "
            + "FROM rekam_medis WHERE no_daftar LIKE ?";

    private final String getRekamMedisPasienAntri =
            "SELECT r.no_daftar, r.no_rm, p.nm_pas, d.nm_dokter, r.id_jaminan, r.status "
            + "FROM rekam_medis r, pasien p, dokter d, spesialis s "
            + "WHERE r.no_rm = p.no_rm AND r.no_dokter = d.no_dokter AND r.bagian_spesialis = s.id_spesialis AND "
            + "status='antri' AND s.nm_spesialis like ? AND tgl_daftar=?";

    private final String getRekamMedisPasienPoli =
            "SELECT r.no_daftar, r.no_rm, p.nm_pas, d.nm_dokter, r.id_jaminan, r.status "
            + "FROM rekam_medis r, pasien p, dokter d, spesialis s "
            + "WHERE r.no_rm = p.no_rm AND r.no_dokter = d.no_dokter AND r.bagian_spesialis = s.id_spesialis AND "
            + "status!='antri' AND s.nm_spesialis like ? AND tgl_daftar=?";

    public RekamMedisDao(Connection connection) {
        this.connection = connection;
    }    

    public void insertRekamMedis(RekamMedis rm) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(insertRekamMedis);
            ps.setString(1, rm.getNoDaftar());
            ps.setString(2, rm.getNoRm());
            ps.setString(3, rm.getNoStaf());
            ps.setString(4, rm.getBagianSpesialis());
            ps.setString(5, rm.getIdJaminan());
            ps.setString(6, rm.getNoDokter());
            ps.setString(7, rm.getStatus());
            ps.setString(8, rm.getTglDaftar());
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data rekam medis berhasil ditambah!", "Insert Rekam Medis", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Insert Rekam Medis Gagal!",JOptionPane.ERROR_MESSAGE);
        }
    }

//    public void updateRekamMedis(RekamMedis rm, String noDaftar) throws SQLException {
//        try{
//            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(updateRekamMedis);
//            ps.setString(1, rm.getNoRm());
//            ps.setString(2, rm.getNoStaf());
//            ps.setString(3, rm.getBagianSpesialis());
//            ps.setString(4, rm.getIdJaminan());
//            ps.setString(5, rm.getNoDokter());
//            ps.setString(6, rm.getNoPerawat());
//            ps.setInt(7, rm.getNadi());
//            ps.setInt(8, rm.getTemperatur());
//            ps.setInt(9, rm.getPernapasan());
//            ps.setString(10, rm.getKesadaran());
//            ps.setString(11, rm.getAnamnesa());
//            ps.setFloat(12, rm.getTinggiBdn());
//            ps.setFloat(13, rm.getBeratBdn());
//            ps.setString(14, rm.getTensiDarah());
//            ps.setString(15, rm.getDiagnosis());
//            ps.setString(16, rm.getTerapi());
//            ps.setString(17, rm.getStatus());
//            ps.setString(18, noDaftar);
//            ps.executeUpdate();
//            ps.close();
//            JOptionPane.showMessageDialog(null, "Data rekam medis berhasil diubah!", "Update Rekam Medis", JOptionPane.INFORMATION_MESSAGE);
//        }catch(SQLException se){
//            JOptionPane.showMessageDialog(null, se.getMessage(),"Update Rekam Medis Gagal!",JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    public void deleteRekamMedis(String noDaftar) throws SQLException {
//        try{
//            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(deleteRekamMedis);
//            ps.setString(1, noDaftar);
//            ps.executeUpdate();
//            ps.close();
//            JOptionPane.showMessageDialog(null, "Data rekam medis berhasil dihapus!","Delete Rekam Medis",JOptionPane.INFORMATION_MESSAGE);
//        }catch(SQLException se){
//            JOptionPane.showMessageDialog(null, se.getMessage(),"Delete Rekam Medis Gagal!",JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    public List getAllRekamMedis() throws SQLException {
//        try{
//            List list = new ArrayList();
//            Statement s = (Statement) connection.createStatement();
//            ResultSet rs = s.executeQuery(getAllRekamMedis);
//            while(rs.next()){
//                RekamMedis rm = new RekamMedis();
//                rm.setNoDaftar(rs.getString("no_daftar"));
//                rm.setNoRm(rs.getString("no_rm"));
//                rm.setNoStaf(rs.getString("no_staf"));
//                rm.setBagianSpesialis(rs.getString("bagian_spesialis"));
//                rm.setIdJaminan(rs.getString("id_jaminan"));
//                rm.setNoDokter(rs.getString("no_dokter"));
//                rm.setNoPerawat(rs.getString("no_perawat"));
//                rm.setNadi(rs.getInt("nadi"));
//                rm.setTemperatur(rs.getInt("temperatur"));
//                rm.setPernapasan(rs.getInt("pernapasan"));
//                rm.setKesadaran(rs.getString("kesadaran"));
//                rm.setAnamnesa(rs.getString("anamnesa"));
//                rm.setTinggiBdn(rs.getFloat("tinggi_bdn"));
//                rm.setBeratBdn(rs.getFloat("berat_bdn"));
//                rm.setTensiDarah(rs.getString("tensi_darah"));
//                rm.setDiagnosis(rs.getString("diagnosis"));
//                rm.setTerapi(rs.getString("terapi"));
//                rm.setStatus(rs.getString("status"));
//                rm.setTglDaftar(rs.getString("tgl_daftar"));
//                list.add(rm);
//            }
//            rs.close();
//            s.close();
//            return list;
//        }catch(SQLException se){
//            JOptionPane.showMessageDialog(null, se.getMessage(),"Get All Rekam Medis Gagal!", JOptionPane.ERROR_MESSAGE);
//            return null;
//        }
//    }

    public List getRekamMedisByNoRm(String noRm) throws SQLException {
        try{
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getRekamMedisByNoRm);
            ps.setString(1, noRm);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                RekamMedis rm = new RekamMedis();
                rm.setNoDaftar(rs.getString("no_daftar"));
                rm.setNoStaf(rs.getString("nm_staf"));
                rm.setBagianSpesialis(rs.getString("nm_spesialis"));
                rm.setIdJaminan(rs.getString("id_jaminan"));
                rm.setNoDokter(rs.getString("nm_dokter"));
                rm.setTglDaftar(rs.getString("tgl_daftar"));
                rm.setStatus(rs.getString("status"));
                
                list.add(rm);
            }
            rs.close();
            ps.close();
            return list;
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Get Rekam Medis By Nomor RM Gagal!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public String generateNomor(String tanggal) throws SQLException {
        try {
            String hasil,generate = null;
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(generateNomor);
            ps.setString(1, tanggal+"%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                hasil = rs.getString(1);
                if(hasil == null){
                    generate = tanggal+"0001";
                }else{
                    if(hasil.length() == 1){
                        generate = tanggal + "000"+ hasil;
                    }else if(hasil.length() == 2){
                        generate = tanggal + "00"+ hasil;
                    }else if(hasil.length() == 3){
                        generate = tanggal + "0"+ hasil;
                    }else{
                        generate = tanggal + hasil;
                    }
                }
            }
            rs.close();
            ps.close();
            return generate;
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se.getMessage(), "Generate Max Nomor Pendaftaran Gagal!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public List getRekamMedisByNoDaftar(String noDaftar) throws SQLException {
        try{
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getRekamMedisByNoDaftar);
            ps.setString(1, noDaftar);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                RekamMedis rm = new RekamMedis();

                /* Pemeriksaan Awal */
                rm.setBeratBdn(rs.getFloat("berat_bdn"));
                rm.setTinggiBdn(rs.getFloat("tinggi_bdn"));
                rm.setTensiDarah(rs.getString("tensi_darah"));
                rm.setNadi(rs.getInt("nadi"));
                rm.setTemperatur(rs.getInt("temperatur"));
                rm.setPernapasan(rs.getInt("pernapasan"));
                rm.setKesadaran(rs.getString("kesadaran"));
                rm.setNoPerawat(rs.getString("nm_perawat"));

                /* Pemeriksaan Akhir */
                rm.setAnamnesa(rs.getString("anamnesa"));
                rm.setDiagnosis(rs.getString("diagnosis"));
                rm.setTerapi(rs.getString("terapi"));
                
                list.add(rm);
            }
            rs.close();
            ps.close();
            return list;
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Get Rekam Medis By Nomor Pendaftaran Gagal!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public List getRekamMedisPasienAntri(String poli, String tanggal) throws SQLException {
        try{
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getRekamMedisPasienAntri);
            ps.setString(1, "%"+poli+"%");
            ps.setString(2, tanggal);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                RekamMedis rm = new RekamMedis();
                rm.setNoDaftar(rs.getString("no_daftar"));
                rm.setNoRm(rs.getString("no_rm"));
                rm.setNmPas(rs.getString("nm_pas"));
                rm.setNoDokter(rs.getString("nm_dokter"));
                rm.setIdJaminan(rs.getString("id_jaminan"));
                rm.setStatus(rs.getString("status"));
                list.add(rm);                
            }            
            rs.close();
            ps.close();
            return list;
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Get Rekam Medis Pasien Antri Gagal!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public List getRekamMedisPasienPoli(String poli, String tanggal) throws SQLException {
        try{
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getRekamMedisPasienPoli);
            ps.setString(1, "%"+poli+"%");
            ps.setString(2, tanggal);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                RekamMedis rm = new RekamMedis();
                rm.setNoDaftar(rs.getString("no_daftar"));
                rm.setNoRm(rs.getString("no_rm"));
                rm.setNmPas(rs.getString("nm_pas"));
                rm.setNoDokter(rs.getString("nm_dokter"));
                rm.setIdJaminan(rs.getString("id_jaminan"));
                rm.setStatus(rs.getString("status"));
                list.add(rm);                
            }            
            rs.close();
            ps.close();
            return list;
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Get Rekam Medis Pasien Poli Gagal!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void updatePasienMasukPoli(String noDaftar) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(updatePasienMasukPoli);
            ps.setString(1, noDaftar);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data pasien berhasil masuk poli!", "Update Rekam Medis", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Update Rekam Medis Gagal!",JOptionPane.ERROR_MESSAGE);
        }
    }
}

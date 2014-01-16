/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import medrecapp.Entity.Pendaftaran;
import medrecapp.Interfaces.PendaftaranInterface;

/**
 *
 * @author Hady
 */
public class PendaftaranDao implements PendaftaranInterface{
    private Connection connection;

    private final String insertPendaftaran = "INSERT INTO pendaftaran VALUES(?,?,?,?,?,?)";
    private final String updatePendaftaran =
            "UPDATE pendaftaran SET no_rm=?, no_staf=?, id_unit_rs=?, id_jaminan=?, no_dokter=? WHERE no_daftar=?";
    private final String deletePendaftaran = "DELETE FROM pendaftaran WHERE no_daftar=?";
    private final String getAllPendaftaran = "SELECT * FROM pendaftaran";
    
    public PendaftaranDao(Connection connection) {
        this.connection = connection;
    }

    public void insertPendaftaran(Pendaftaran pn) throws SQLException {
         try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(insertPendaftaran);
            ps.setString(1, pn.getNoDaftar());
            ps.setString(2, pn.getNoRm());
            ps.setString(3, pn.getNoStaf());
            ps.setString(4, pn.getIdUnitRs());
            ps.setString(5, pn.getIdJaminan());
            ps.setString(5, pn.getNoDokter());
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Pendaftaran berhasil ditambah!", "Insert Pendaftaran", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Insert Pendaftaran Gagal!",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updatePendaftaran(Pendaftaran pn, String noDaftar) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(updatePendaftaran);
            ps.setString(1, pn.getNoRm());
            ps.setString(2, pn.getNoStaf());
            ps.setString(3, pn.getIdUnitRs());
            ps.setString(4, pn.getIdJaminan());
            ps.setString(5, pn.getNoDokter());
            ps.setString(6, noDaftar);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Pendaftaran berhasil diubah!", "Update Pendaftaran", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Update Pendaftaran Gagal!",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deletePendaftaran(String noDaftar) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(deletePendaftaran);
            ps.setString(1, noDaftar);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data pendaftaran berhasil dihapus!","Delete Pendaftaran",JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Delete Pendaftaran Gagal!",JOptionPane.ERROR_MESSAGE);
        }
    }

    public List getAllPendaftaran() throws SQLException {
        try{
            List list = new ArrayList();
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllPendaftaran);
            while(rs.next()){
                Pendaftaran pn = new Pendaftaran();
                pn.setNoDaftar(rs.getString("no_daftar"));
                pn.setNoRm(rs.getString("nm_rm"));
                pn.setNoStaf(rs.getString("no_staf"));
                pn.setIdUnitRs(rs.getString("id_unit_rs"));
                pn.setIdJaminan(rs.getString("id_jaminan"));
                pn.setNoDokter(rs.getString("no_dokter"));
                list.add(pn);
            }
            rs.close();
            s.close();
            return list;
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Get All Pendaftaran Gagal!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}

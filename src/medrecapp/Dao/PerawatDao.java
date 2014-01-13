/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import medrecapp.Entity.Perawat;
import medrecapp.Interfaces.PerawatInterface;

/**
 *
 * @author Hady
 */
public class PerawatDao implements PerawatInterface{
    private Connection connection;

    private final String insertPerawat = "INSERT INTO perawat VALUES(?,?,?,?)";
    private final String updatePerawat =
            "UPDATE perawat SET nm_perawat=?, tgl_kerja_per=?, per_spesialis=? WHERE no_perawat=?";
    private final String deletePerawat = "DELETE FROM perawat WHERE no_perawat=?";
    private final String getAllPerawat = 
            "SELECT p.no_perawat, p.nm_perawat, p.tgl_kerja_per, s.nm_spesialis "
            + "FROM perawat p, spesialis s "
            + "WHERE p.per_spesialis = s.id_spesialis ORDER BY p.no_perawat ASC";

    /* Query untuk mencari perawat via JTextField */
    private final String getAllByNoPerawat = "SELECT * FROM perawat WHERE no_perawat LIKE ?";
    private final String getAllByNmPerawat = "SELECT * FROM perawat WHERE nm_perawat LIKE ?";

    /* Query untuk memanggil nilai maximal NoPerawat+1 */
    private final String getMaxNoPerawat   = "SELECT MAX(SUBSTR(no_perawat,5,7))+1 FROM perawat";

    public PerawatDao(Connection connection) {
        this.connection = connection;
    }

    public void insertPerawat(Perawat pr) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(insertPerawat);
            ps.setString(1, pr.getNoPerawat());
            ps.setString(2, pr.getNmPerawat());
            ps.setString(3, pr.getTglKerjaPer());
            ps.setString(4, pr.getPerSpesialis());
            ps.executeUpdate();
            ps.close();
            //JOptionPane.showMessageDialog(null, "Data perawat berhasil ditambah!", "Insert Perawat", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Insert Perawat - Data perawat berhasil ditambah!");
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(),"Insert Perawat Gagal!",JOptionPane.ERROR_MESSAGE);
            System.out.println("Insert Perawat Gagal - "+se.getMessage());
        }
    }

    public void updatePerawat(Perawat pr, String noPerawat) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(updatePerawat);
            ps.setString(1, pr.getNmPerawat());
            ps.setString(2, pr.getTglKerjaPer());
            ps.setString(3, pr.getPerSpesialis());
            ps.setString(4, noPerawat);
            ps.executeUpdate();
            ps.close();
            //JOptionPane.showMessageDialog(null, "Data perawat berhasil diubah!", "Update Perawat", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Update Perawat - Data perawat berhasil diubah!");
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(),"Update Perawat Gagal!",JOptionPane.ERROR_MESSAGE);
            System.out.println("Update Perawat Gagal - "+se.getMessage());
        }
    }

    public void deletePerawat(String noPerawat) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(deletePerawat);
            ps.setString(1, noPerawat);
            ps.executeUpdate();
            ps.close();
            //JOptionPane.showMessageDialog(null, "Data perawat berhasil dihapus!","Delete Perawat",JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Delete Perawat - Data perawat berhasil dihapus!");
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(),"Delete Perawat Gagal!",JOptionPane.ERROR_MESSAGE);
            System.out.println("Delete Perawat Gagal - "+se.getMessage());
        }
    }

    public List getAllPerawat() throws SQLException {
        try{
            List list = new ArrayList();
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllPerawat);
            while(rs.next()){
                Perawat pr = new Perawat();
                pr.setNoPerawat(rs.getString("no_perawat"));
                pr.setNmPerawat(rs.getString("nm_perawat"));
                pr.setTglKerjaPer(rs.getString("tgl_kerja_per"));
                pr.setPerSpesialis(rs.getString("nm_spesialis"));
                list.add(pr);
            }            
            rs.close();
            s.close();
            System.out.println("Get All Perawat - Berhasil dipanggil!");
            return list;
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(),"Get All Perawat Gagal!", JOptionPane.ERROR_MESSAGE);
            System.out.println("Get All Perawat Gagal - "+se.getMessage());
            return null;
        }
    }

    public List getAllPerawatByNo(String noPerawat) throws SQLException {
        try {
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getAllByNoPerawat);
            ps.setString(1, "%" + noPerawat + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Perawat p = new Perawat();
                p.setNoPerawat(rs.getString("no_perawat"));
                p.setNmPerawat(rs.getString("nm_perawat"));
                p.setTglKerjaPer(rs.getString("tgl_kerja_per"));
                p.setPerSpesialis(rs.getString("per_spesialis"));
                list.add(p);
            }
            ps.close();
            rs.close();
            return list;
        } catch (Throwable t) {
            //JOptionPane.showMessageDialog(null, t.getMessage(), "Get All Perawat By Nomor Gagal!", JOptionPane.ERROR_MESSAGE);
            System.out.println("Get All Perawat By Nomor Gagal - "+t.getMessage());
            return null;
        }
    }

    public List getAllPerawatByNm(String nmPerawat) throws SQLException {
        try {
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getAllByNmPerawat);
            ps.setString(1, "%" + nmPerawat + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Perawat p = new Perawat();
                p.setNoPerawat(rs.getString("no_perawat"));
                p.setNmPerawat(rs.getString("nm_perawat"));
                p.setTglKerjaPer(rs.getString("tgl_kerja_per"));
                p.setPerSpesialis(rs.getString("per_spesialis"));
                list.add(p);
            }
            ps.close();
            rs.close();
            return list;
        } catch (Throwable t) {
            //JOptionPane.showMessageDialog(null, t.getMessage(), "Get All Perawat By Nama Gagal!", JOptionPane.ERROR_MESSAGE);
            System.out.println("Get All Perawat By Nama Gagal - "+t.getMessage());
            return null;
        }
    }

    public String getMaxNoPerawat() throws SQLException {
        try {
            String max = null, hasil = null;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement(getMaxNoPerawat);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                max = rs.getString(1);
                if (max.length() == 1) {
                    hasil = "00" + max;
                } else if (max.length() == 2) {
                    hasil = "0" + max;
                } else {
                    hasil = max;
                }
            }
            return hasil;
        } catch (Throwable t) {
            //JOptionPane.showMessageDialog(null, t.getMessage(), "Get Max Nomor Perawat Gagal!", JOptionPane.ERROR_MESSAGE);
            System.out.println("Get Max Nomor Perawat Gagal - "+t.getMessage());
            return null;
        }
    }
}

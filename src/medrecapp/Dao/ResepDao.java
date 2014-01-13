/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import medrecapp.Entity.Resep;
import medrecapp.Interfaces.ResepInterface;

/**
 *
 * @author Hady
 */
public class ResepDao implements ResepInterface{
    private Connection connection;

    private final String insertResep = "INSERT INTO resep VALUES(?,?,?)";
    private final String updateResep =
            "UPDATE resep SET no_daftar=?, tgl_resep=? WHERE no_resep=?";
    private final String deleteResep = "DELETE FROM resep WHERE no_resep=?";
    private final String getAllResep = "SELECT * FROM resep";

    public ResepDao(Connection connection) {
        this.connection = connection;
    }

    public void insertResep(Resep rs) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(insertResep);
            ps.setString(1, rs.getNoResep());
            ps.setString(2, rs.getNoDaftar());
            ps.setString(3, rs.getTglResep());
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Resep berhasil ditambah!", "Insert Resep", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Insert Resep Gagal!",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateResep(Resep rs, String noResep) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(updateResep);
            ps.setString(1, rs.getNoDaftar());
            ps.setString(2, rs.getTglResep());
            ps.setString(3, noResep);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data resep berhasil diubah!", "Update Resep", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Update Resep Gagal!",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteResep(String noResep) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(deleteResep);
            ps.setString(1, noResep);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data resep berhasil dihapus!","Delete Resep",JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Delete Resep Gagal!",JOptionPane.ERROR_MESSAGE);
        }
    }

    public List getAllResep() throws SQLException {
        try{
            List list = new ArrayList();
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllResep);
            while(rs.next()){
                Resep rsp = new Resep();
                rsp.setNoResep(rs.getString("no_resep"));
                rsp.setNoDaftar(rs.getString("no_daftar"));
                rsp.setTglResep(rs.getString("tgl_resep"));
                list.add(rsp);
            }
            rs.close();
            s.close();
            return list;
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Get All Resep Gagal!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
}

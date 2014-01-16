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
import medrecapp.Entity.Staf;
import medrecapp.Interfaces.StafInterface;

/**
 *
 * @author Hady
 */
public class StaffDao implements StafInterface{
    private Connection connection;

    private final String insertStaf = "INSERT INTO dokter VALUES(?,?,?)";
    private final String updateStaf =
            "UPDATE staf SET nm_staf=?, alamat_staf=?, WHERE no_staf=?";
    private final String deleteStaf = "DELETE FROM staf WHERE no_staf=?";
    private final String getAllStaf = "SELECT * FROM staf";

    public StaffDao(Connection connection) {
        this.connection = connection;
    }

    public void insertStaf(Staf st) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(insertStaf);
            ps.setString(1, st.getNoStaf());
            ps.setString(2, st.getNmStaf());
            ps.setString(3, st.getAlamatStaf());
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data staf berhasil ditambah!", "Insert Staf", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Insert Staf Gagal!",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateStaf(Staf st, String noStaf) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(updateStaf);
            ps.setString(1, st.getNmStaf());
            ps.setString(2, st.getAlamatStaf());
            ps.setString(3, noStaf);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data staf berhasil diubah!", "Update Staf", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Update Staf Gagal!",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteStaf(String noStaf) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(deleteStaf);
            ps.setString(1, noStaf);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data staf berhasil dihapus!","Delete Staf",JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Delete Staf Gagal!",JOptionPane.ERROR_MESSAGE);
        }
    }

    public List getAllStaf() throws SQLException {
        try{
            List list = new ArrayList();
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllStaf);
            while(rs.next()){
                Staf st = new Staf();
                st.setNoStaf(rs.getString("no_staf"));
                st.setNmStaf(rs.getString("nm_staf"));
                st.setAlamatStaf(rs.getString("alamat_staf"));
                list.add(st);
            }
            rs.close();
            s.close();
            return list;
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Get All Staf Gagal!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}

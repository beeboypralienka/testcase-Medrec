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
import medrecapp.Entity.UnitRs;
import medrecapp.Interfaces.UnitRsInterface;

/**
 *
 * @author Hady
 */
public class UnitRsDao implements UnitRsInterface{
    private Connection connection;

    private final String insertUnitRs = "INSERT INTO unit_rs VALUES(?,?)";
    private final String updateUnitRs =
            "UPDATE unit_rs SET ket_unit_rs=? WHERE id_unit_rs=?";
    private final String deleteUnitRs = "DELETE FROM unit_rs WHERE id_unit_rs=?";
    private final String getAllUnitRs = "SELECT * FROM unit_rs";

    public UnitRsDao(Connection connection) {
        this.connection = connection;
    }

    public void insertUnitRs(UnitRs urs) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(insertUnitRs);
            ps.setString(1, urs.getIdUnitRs());
            ps.setString(2, urs.getKetUnitRs());
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data unit rumah sakit berhasil ditambah!", "Insert Unit Rumah Sakit", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Insert Unit Rumah Sakit Gagal!",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateUnitRs(UnitRs urs, String idUnitRs) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(updateUnitRs);
            ps.setString(1, urs.getKetUnitRs());
            ps.setString(2, idUnitRs);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data unit rumah sakit berhasil diubah!", "Update Unit Rumah Sakit", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Update Unit Rumah Sakit Gagal!",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteUnitRs(String idUnitRs) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(deleteUnitRs);
            ps.setString(1, idUnitRs);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data unit rumah sakit berhasil dihapus!","Delete Unit Rumah Sakit",JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Delete Unit Rumah Sakit Gagal!",JOptionPane.ERROR_MESSAGE);
        }
    }

    public List getAllUnitRs() throws SQLException {
        try{
            List list = new ArrayList();
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllUnitRs);
            while(rs.next()){
                UnitRs urs = new UnitRs();
                urs.setIdUnitRs(rs.getString("id_unit_rs"));
                urs.setKetUnitRs(rs.getString("ket_unit_rs"));
                list.add(urs);
            }
            rs.close();
            s.close();
            return list;
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(),"Get All Unit Rumah Sakit Gagal!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}

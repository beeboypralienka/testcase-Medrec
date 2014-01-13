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
public class StafDao implements StafInterface{
    private Connection connection;

    /* Query standar untuk CRUD */
    private final String insertStaf = "INSERT INTO staf VALUES(?,?,?)";
    private final String updateStaf =
            "UPDATE staf SET nm_staf=?, alamat_staf=? WHERE no_staf=?";
    private final String deleteStaf = "DELETE FROM staf WHERE no_staf=?";
    private final String getAllStaf = "SELECT * FROM staf";

    /* Query untuk memanggil staf berdasarkan nama */
    private final String getAllStafByNama = "SELECT * FROM staf WHERE nm_staf = ? ";

    /* Query untuk melakukan pencarian staf via JTextfield */
    private final String getAllByNoStaf = "SELECT * FROM staf WHERE no_staf LIKE ?";
    private final String getAllByNmStaf = "SELECT * FROM staf WHERE nm_staf LIKE ?";

    /* Query untuk memanggil nilai maximal Nomor Staf+1 */
    private final String getMaxNoStaf   = "SELECT MAX(SUBSTR(no_staf,5,7))+1 FROM staf";

    public StafDao(Connection connection) {
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
            //JOptionPane.showMessageDialog(null, "Data staf berhasil ditambah!", "Insert Staf", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Insert Staf - Data staf berhasil ditambah!");
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(),"Insert Staf Gagal!",JOptionPane.ERROR_MESSAGE);
            System.out.println("Insert Staf Gagal - "+se.getMessage());
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
            //JOptionPane.showMessageDialog(null, "Data staf berhasil diubah!", "Update Staf", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Update Staf - Data staf berhasil diubah!");
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(),"Update Staf Gagal!",JOptionPane.ERROR_MESSAGE);
            System.out.println("Update Staf Gagal - "+se.getMessage());
        }
    }

    public void deleteStaf(String noStaf) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(deleteStaf);
            ps.setString(1, noStaf);
            ps.executeUpdate();
            ps.close();
            //JOptionPane.showMessageDialog(null, "Data staf berhasil dihapus!","Delete Staf",JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Delete Staf - Data staf berhasil dihapus!");
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(),"Delete Staf Gagal!",JOptionPane.ERROR_MESSAGE);
            System.out.println("Delete Staf Gagal - "+se.getMessage());
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
            //JOptionPane.showMessageDialog(null, se.getMessage(),"Get All Staf Gagal!", JOptionPane.ERROR_MESSAGE);
            System.out.println("Get All Staf Gagal - "+se.getMessage());
            return null;
        }
    }

    public String[] getAllNmStaf(int row) throws SQLException {
        try{
            String[] data = new String[row];
            Statement st = (Statement) connection.createStatement();
            ResultSet rs = st.executeQuery(getAllStaf);
            Staf sf = new Staf();
            while(rs.next()){
                sf.setNmStaf(rs.getString("nm_staf"));
                String nmStaf = sf.getNmStaf();
                data[rs.getRow()-1] = nmStaf;
            }
            st.close();
            rs.close();
            return data;
        }catch(Throwable t){
            //JOptionPane.showMessageDialog(null, t.getMessage(), "Get Nama Staf Gagal!", JOptionPane.ERROR_MESSAGE);
            System.out.println("Get Nama Staf Gagal - "+t.getMessage());
            return null;
        }
    }

    public String getIDStaf(String nama) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(getAllStafByNama);
            ps.setString(1, nama);
            ResultSet rs = ps.executeQuery();
            Staf sf = new Staf();
            String pop = null;
            while(rs.next()){
                sf.setNoStaf(rs.getString("no_staf"));
                pop = sf.getNoStaf();
            }
            ps.close();
            rs.close();
            return pop;
        }catch(Throwable t){
            //JOptionPane.showMessageDialog(null, t.getMessage(), "Get Nomor Spesialis Gagal!", JOptionPane.ERROR_MESSAGE);
            System.out.println("Get Nomor Spesialis Gagal - "+t.getMessage());
            return null;
        }
    }

    public List getAllStafByNo(String noStaf) throws SQLException {
        try {
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getAllByNoStaf);
            ps.setString(1, "%" + noStaf + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Staf s = new Staf();
                s.setNoStaf(rs.getString("no_staf"));
                s.setNmStaf(rs.getString("nm_staf"));
                s.setAlamatStaf(rs.getString("alamat_staf"));
                list.add(s);
            }
            ps.close();
            rs.close();
            return list;
        } catch (Throwable t) {
            //JOptionPane.showMessageDialog(null, t.getMessage(),"Get All Staf By Nomor Gagal!", JOptionPane.ERROR_MESSAGE);
            System.out.println("Get All Staf By Nomor Gagal - "+t.getMessage());
            return null;
        }
    }

    public List getAllStafByNm(String nmStaf) throws SQLException {
        try {
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getAllByNmStaf);
            ps.setString(1, "%" + nmStaf + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Staf s = new Staf();
                s.setNoStaf(rs.getString("no_staf"));
                s.setNmStaf(rs.getString("nm_staf"));
                s.setAlamatStaf(rs.getString("alamat_staf"));
                list.add(s);
            }
            ps.close();
            rs.close();
            return list;
        } catch (Throwable t) {
            //JOptionPane.showMessageDialog(null, t.getMessage(),"Get All Staf By Nama Gagal!", JOptionPane.ERROR_MESSAGE);
            System.out.println("Get All Staf By Nama Gagal - "+t.getMessage());
            return null;
        }
    }

    public String getMaxNoStaf() throws SQLException {
        try {
            String max = null, hasil = null;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement(getMaxNoStaf);
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
            //JOptionPane.showMessageDialog(null, t.getMessage(),"Get Max Nomor Staf Gagal!", JOptionPane.ERROR_MESSAGE);
            System.out.println("Get Max Nomor Staf Gagal - "+t.getMessage());
            return null;
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medrecapp.Services;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import com.mysql.jdbc.Statement;
import java.util.ArrayList;
import java.util.List;
import medrecapp.Entity.Perawat;
import medrecapp.TabelModel.TabelModelPerawat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class PerawatServiceTest {

    private List<Perawat> list;
    private Connection connection;
    private final String getAllPerawat =
            "SELECT p.no_perawat, p.nm_perawat, p.tgl_kerja_per, s.nm_spesialis "
            + "FROM perawat p, spesialis s "
            + "WHERE p.per_spesialis = s.id_spesialis ORDER BY p.no_perawat ASC";

    public PerawatServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        list = new ArrayList<Perawat>();
    }

    @After
    public void tearDown() {
    }
    /**
     * Test of serviceInsertPerawat method, of class PerawatService.
     */
    @Test
    public void testServiceInsertPerawat() {
        System.out.print("serviceInsertPerawat: ");

        String ExpNoPerawat    = "PER.003";
        String ExpNmPerawat    = "Lina Meiga";
        String ExpTglKerja     = "2013-05-04";
        String ExpPerSpesialis = "SP.PD";

        Perawat p = new Perawat();
        p.setNoPerawat(ExpNoPerawat);
        p.setNmPerawat(ExpNmPerawat);
        p.setTglKerjaPer(ExpTglKerja);
        p.setPerSpesialis(ExpPerSpesialis);

        PerawatService instance = new PerawatService();
        instance.serviceInsertPerawat(p);

        TabelModelPerawat tabelModelPerawat = new TabelModelPerawat();
        tabelModelPerawat.setData(instance.serviceGetAllPerawatByNo(ExpNoPerawat));
        
        assertEquals(ExpNmPerawat, tabelModelPerawat.getValueAt(0, 1));
        assertEquals(ExpTglKerja, tabelModelPerawat.getValueAt(0, 2));
        assertEquals(ExpPerSpesialis, tabelModelPerawat.getValueAt(0, 3));

    }

    /**
     * Test of serviceUpdatePerawat method, of class PerawatService.
     */
    @Test
    public void testServiceUpdatePerawat() {
        System.out.print("serviceUpdatePerawat: ");

        String ExpNoPerawat    = "PER.003";
        String ExpNmPerawat    = "Linawati";
        String ExpTglKerja     = "2009-10-03";
        String ExpPerSpesialis = "SP.PD";

        Perawat p = new Perawat();
        p.setNmPerawat(ExpNmPerawat);
        p.setTglKerjaPer(ExpTglKerja);
        p.setPerSpesialis(ExpPerSpesialis);
        PerawatService instance = new PerawatService();
        instance.serviceUpdatePerawat(p, ExpNoPerawat);

        TabelModelPerawat tabelModelPerawat = new TabelModelPerawat();
        tabelModelPerawat.setData(instance.serviceGetAllPerawatByNo(ExpNoPerawat));

        assertEquals(ExpNmPerawat, tabelModelPerawat.getValueAt(0, 1));
        assertEquals(ExpTglKerja, tabelModelPerawat.getValueAt(0, 2));
        assertEquals(ExpPerSpesialis, tabelModelPerawat.getValueAt(0, 3));
    }
    /**
     * Test of serviceDeletePerawat method, of class PerawatService.
     */
    @Test
    public void testServiceDeletePerawat() {
        System.out.print("serviceDeletePerawat: ");

        String ExpNoPerawat = "PER.003";

        PerawatService instance = new PerawatService();
        instance.serviceDeletePerawat(ExpNoPerawat);

        TabelModelPerawat tabelModelPerawat = new TabelModelPerawat();
        tabelModelPerawat.setData(instance.serviceGetAllPerawatByNo(ExpNoPerawat));

        assertEquals(0, tabelModelPerawat.getRowCount());
    }
    /**
     * Test of serviceGetAllPerawat method, of class PerawatService.
     */    

    @Test
    public void testServiceGetAllPerawat() {
        System.out.print("serviceGetAllPerawat: ");
        PerawatService instance = new PerawatService();

        TabelModelPerawat tabelModelPerawat = new TabelModelPerawat();
        tabelModelPerawat.setData(instance.serviceGetAllPerawat());

        try{
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
        }catch(Throwable t){
            // Sengaja dikosongin dulu
        }

        for(int i=0; i<list.size(); i++){
            assertEquals(list.get(i).getNoPerawat(), tabelModelPerawat.getValueAt(i, 0));
            assertEquals(list.get(i).getNmPerawat(), tabelModelPerawat.getValueAt(i, 1));
            assertEquals(list.get(i).getTglKerjaPer(), tabelModelPerawat.getValueAt(i, 2));
            assertEquals(list.get(i).getPerSpesialis(), tabelModelPerawat.getValueAt(i, 3));
        }

    }
}

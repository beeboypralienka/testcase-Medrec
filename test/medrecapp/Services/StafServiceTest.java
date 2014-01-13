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
import medrecapp.Entity.Staf;
import medrecapp.TabelModel.TabelModelStaf;
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
public class StafServiceTest {

    private List<Staf> list;
    private Connection connection;
    private final String getAllStaf = "SELECT * FROM staf";

    public StafServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        list = new ArrayList<Staf>();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of serviceInsertStaf method, of class StafService.
     */
    @Test
    public void testServiceInsertStaf() {
        System.out.print("serviceInsertStaf: ");

        String ExpNoStaf     = "STF.003";
        String ExpNmStaf     = "Danu Simatupang";
        String ExpAlamatStaf = "Jl. GOR Dharma Ayu No. 77";

        Staf s = new Staf();
        s.setNoStaf(ExpNoStaf);
        s.setNmStaf(ExpNmStaf);
        s.setAlamatStaf(ExpAlamatStaf);

        StafService instance = new StafService();
        instance.serviceInsertStaf(s);

        TabelModelStaf tabelModelStaf = new TabelModelStaf();
        tabelModelStaf.setData(instance.serviceGetAllStafByNo(ExpNoStaf));

        assertEquals(ExpNoStaf, tabelModelStaf.getValueAt(0, 0));
        assertEquals(ExpNmStaf, tabelModelStaf.getValueAt(0, 1));
        assertEquals(ExpAlamatStaf, tabelModelStaf.getValueAt(0, 2));

    }

    /**
     * Test of serviceUpdateDokter method, of class StafService.
     */
    @Test
    public void testServiceUpdateStaf() {
        System.out.print("serviceUpdateStaf: ");

        String ExpNoStaf     = "STF.003";
        String ExpNmStaf     = "Joko Tole";
        String ExpAlamatStaf = "Jl. GOR Wiralodra No. 77";

        Staf s = new Staf();
        s.setNmStaf(ExpNmStaf);
        s.setAlamatStaf(ExpAlamatStaf);
        
        StafService instance = new StafService();
        instance.serviceUpdateStaf(s, ExpNoStaf);

        TabelModelStaf tabelModelStaf = new TabelModelStaf();
        tabelModelStaf.setData(instance.serviceGetAllStafByNo(ExpNoStaf));

        assertEquals(ExpNoStaf, tabelModelStaf.getValueAt(0, 0));
        assertEquals(ExpNmStaf, tabelModelStaf.getValueAt(0, 1));
        assertEquals(ExpAlamatStaf, tabelModelStaf.getValueAt(0, 2));
        
    }

    /**
     * Test of serviceDeleteStaf method, of class StafService.
     */
    @Test
    public void testServiceDeleteStaf() {
        System.out.print("serviceDeleteStaf: ");

        String ExpNoStaf = "STF.003";

        StafService instance = new StafService();
        instance.serviceDeleteStaf(ExpNoStaf);

        TabelModelStaf tabelModelStaf = new TabelModelStaf();
        tabelModelStaf.setData(instance.serviceGetAllStafByNo(ExpNoStaf));

        assertEquals(0, tabelModelStaf.getRowCount());
        
    }

    /**
     * Test of serviceGetAllDokter method, of class StafService.
     */
    @Test
    public void testServiceGetAllStaf() {
        System.out.print("serviceGetAllStaf: ");
        StafService instance = new StafService();

        TabelModelStaf tabelModelStaf = new TabelModelStaf();
        tabelModelStaf.setData(instance.serviceGetAllStaf());

        try{
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
        }catch(Throwable t){
            // Sengaja dikosongin
        }
        

        for(int i=0; i<list.size(); i++){
            assertEquals(list.get(i).getNoStaf(), tabelModelStaf.getValueAt(i, 0));
            assertEquals(list.get(i).getNmStaf(), tabelModelStaf.getValueAt(i, 1));
            assertEquals(list.get(i).getAlamatStaf(), tabelModelStaf.getValueAt(i, 2));
        }
        
    }

}
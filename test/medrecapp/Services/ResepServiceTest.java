/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Services;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import java.util.List;
import medrecapp.Entity.Resep;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ResepServiceTest {

    public ResepServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of serviceInsertResep method, of class ResepService.
     */
    @Test
    public void a_insertResep() {
        System.out.println("1. serviceInsertResep");
//        Resep r = new Resep();
//        r.setNoResep("000000001");
//        r.setNoDaftar("090920130001");
//        r.setTglResep("2013-12-20");
//        ResepService instance = new ResepService();
//        instance.serviceInsertResep(r);
    }

    /**
     * Test of serviceUpdateResep method, of class ResepService.
     */
    @Test
    public void b_updateResep() {
        System.out.println("2. serviceUpdateResep");
//        Resep r = new Resep();
//        r.setNoDaftar("090920130001");
//        r.setTglResep("2013-11-20");
//        String noResep = "000000001";
//        ResepService instance = new ResepService();
//        instance.serviceUpdateResep(r, noResep);
    }

    /**
     * Test of serviceDeleteResep method, of class ResepService.
     */
    @Test
    public void c_deleteResep() {
        System.out.println("3. serviceDeleteResep");
//        String noResep = "000000001";
//        ResepService instance = new ResepService();
//        instance.serviceDeleteResep(noResep);
    }

    /**
     * Test of serviceGetAllResep method, of class ResepService.
     */
    @Test
    public void d_getAllResep() {
        System.out.println("4. serviceGetAllResep");
//        ResepService instance = new ResepService();
//        List expResult = null;
//        List result = instance.serviceGetAllResep();
//        assertEquals(expResult, result);
    }

}
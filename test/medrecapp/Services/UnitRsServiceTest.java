/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Services;

import java.util.List;
import medrecapp.Entity.UnitRs;
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
public class UnitRsServiceTest {

    public UnitRsServiceTest() {
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
     * Test of serviceInsertUnitRs method, of class UnitRsService.
     */
    @Test
    public void testServiceInsertUnitRs() {
        System.out.println("serviceInsertUnitRs");
        UnitRs ur = new UnitRs();
        ur.setIdUnitRs("POLI JANTUNG");
        ur.setKetUnitRs("Poliklinik Spesialis Penyakit Jantung");

        UnitRsService instance = new UnitRsService();
        instance.serviceInsertUnitRs(ur);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of serviceUpdateUnitRs method, of class UnitRsService.
     */
    @Test
    public void testServiceUpdateUnitRs() {
        System.out.println("serviceUpdateUnitRs");
        UnitRs ur = new UnitRs();
        ur.setKetUnitRs("Poliklinik Jantung");
        String idUnit = "POLI JANTUNG";
        UnitRsService instance = new UnitRsService();
        instance.serviceUpdateUnitRs(ur, idUnit);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of serviceDeleteDokter method, of class UnitRsService.
     */
    @Test
    public void testServiceDeleteDokter() {
        System.out.println("serviceDeleteDokter");
        String idUnit = "POLI JANTUNG";
        UnitRsService instance = new UnitRsService();
        instance.serviceDeleteDokter(idUnit);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of serviceGetAllDokter method, of class UnitRsService.
     */
//    @Test
//    public void testServiceGetAllDokter() {
//        System.out.println("serviceGetAllDokter");
//        UnitRsService instance = new UnitRsService();
//        List expResult = null;
//        List result = instance.serviceGetAllDokter();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}
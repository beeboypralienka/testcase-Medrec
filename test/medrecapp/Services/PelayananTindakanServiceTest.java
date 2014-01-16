/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medrecapp.Services;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import medrecapp.Entity.Pasien;
import medrecapp.Entity.Staf;
import medrecapp.Entity.Dokter;
import medrecapp.Entity.Perawat;
import medrecapp.Entity.Jaminan;
import medrecapp.Entity.Spesialis;
import java.sql.ResultSet;
import com.mysql.jdbc.Statement;
import java.util.ArrayList;
import medrecapp.TabelModel.TabelModelPelayananTindakan;
import com.mysql.jdbc.Connection;
import java.util.List;
import medrecapp.Dao.RekamMedisDao;
import medrecapp.Entity.PelayananTindakan;
import medrecapp.Entity.RekamMedis;
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
public class PelayananTindakanServiceTest {

    private List<PelayananTindakan> list;
    private Connection connection;
    private PelayananTindakanService instance;
    private TabelModelPelayananTindakan tabelModelPelayananTindakan;
    private String expNoDaftar;
    private String expNoTindakan;
    private PelayananTindakan pt;
    private final String getAllPelayananTindakan = "SELECT * FROM pelayanan_tindakan";

    public PelayananTindakanServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
//        Insert data spesialis
        Spesialis sp = new Spesialis();
        SpesialisService sps = new SpesialisService();
        sp.setIdSpesialis("Sp.PD");
        sp.setNmSpesialis("Penyakit Dalam");
        sp.setTarifKonsul(50000);
        sps.serviceInsertSpesialis(sp);
//        System.out.println("Spesialis Insert: "+SpesialisDao.hasilInsert);

//        Insert data jaminan
        Jaminan jm = new Jaminan();
        JaminanService jms = new JaminanService();
        jm.setIdJaminan("KJS");
        jm.setNmJaminan("Kartu Jakarta Sehat");
        jm.setKetJaminan("Jaminan Kesehatan untuk warga Jakarta yang kurang mampu");
        jms.serviceInsertJaminan(jm);
//        System.out.println("Jaminan Insert: "+JaminanDao.hasilInsert);

//        Insert data perawat
        Perawat p = new Perawat();
        PerawatService ps = new PerawatService();
        p.setNoPerawat("PER.003");
        p.setNmPerawat("Fitriya Rahmawati");
        p.setTglKerjaPer("2009-09-09");
        p.setPerSpesialis("Sp.PD");
        ps.serviceInsertPerawat(p);
//        System.out.println("Perawat Insert: "+PerawatDao.hasilInsertPerawat);

//        Insert data dokter
        Dokter d = new Dokter();
        DokterService ds = new DokterService();
        d.setNoDokter("DOK.003");
        d.setNmDokter("HARYONO");
        d.setIdSpesialis("Sp.PD");
        d.setTglKerjaDok("2008-01-02");
        d.setAlamatDok("Jakarta");
        ds.serviceInsertDokter(d);
//        System.out.println("Dokter Insert: "+DokterDao.hasilInsert);

//        Insert data staf
        Staf sf = new Staf();
        StafService sfs = new StafService();
        sf.setNoStaf("STF.003");
        sf.setNmStaf("Asnar Sudirja");
        sf.setAlamatStaf("Jakarta Timur");
        sfs.serviceInsertStaf(sf);
//        System.out.println("Staf Insert: "+StafDao.hasilInsert);

//        Insert data pasien
        Pasien pn = new Pasien();
        PasienService pns = new PasienService();
        pn.setNoRm("000003");
        pn.setNmPas("Udin Samsudin");
        pn.setJkPas("L");
        pn.setTglLahir("1990-09-04");
        pn.setAgama("Islam");
        pn.setAlamatPas("Malang");
        pns.serviceInsertPasien(pn);
//        System.out.println("Pasien Insert: "+PasienDao.hasilInsert);

//        Insert data rekam medis
        RekamMedis rm = new RekamMedis();
        RekamMedisService rms = new RekamMedisService();
        rm.setNoDaftar("090920130001");
        rm.setNoRm("000003");
        rm.setNoStaf("STF.003");
        rm.setBagianSpesialis("Sp.PD");
        rm.setIdJaminan("KJS");
        rm.setNoDokter("DOK.003");
        rm.setStatus("Antri");
        rm.setTglDaftar("2013-09-09");
        rms.serviceInsertRekamMedis(rm);
//        System.out.println("Rekam Medis Insert: "+RekamMedisDao.hasilInsertRekamMedis);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {

//        delete data rekam medis
        RekamMedisService rms = new RekamMedisService();
        rms.serviceDeleteRekmed();
//        System.out.println("Rekmed Delete: "+RekamMedisDao.hasilDeleteRekamMedis);

//        delete data jaminan
        JaminanService jms = new JaminanService();
        jms.serviceDeleteJaminan("KJS");
//        System.out.println("Jaminan Delete: "+JaminanDao.hasilDelete);

//        delete data perawat
        PerawatService ps = new PerawatService();
        ps.serviceDeletePerawat("PER.003");
//        System.out.println("Perawat Delete: "+PerawatDao.hasilDeletePerawat);

//        delete data dokter
        DokterService ds = new DokterService();
        ds.serviceDeleteDokter("DOK.003");
//        System.out.println("Dokter Delete: "+DokterDao.hasilDelete);

//        delete data staf
        StafService sfs = new StafService();
        sfs.serviceDeleteStaf("STF.003");
//        System.out.println("Staf Delete: "+StafDao.hasilDelete);

//        delete data pasien
        PasienService pns = new PasienService();
        pns.serviceDeletePasien("000003");
//        System.out.println("Pasien Delete: "+PasienDao.hasilDelete);

//        delete data spesialis
        SpesialisService ss = new SpesialisService();
        ss.serviceDeleteSpesialis("Sp.PD");
//        System.out.println("Spesialis Delete: "+SpesialisDao.hasilDelete);
    }

    @Before
    public void setUp() {
        list = new ArrayList<PelayananTindakan>();
        pt = new PelayananTindakan();
        instance = new PelayananTindakanService();
        tabelModelPelayananTindakan = new TabelModelPelayananTindakan();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of serviceInsertPelayananTindakan method, of class PelayananTindakanService.
     */
    @Test
    public void a_insertPelayananTindakan() {
        System.out.println("1. serviceInsertPelayananTindakan");

//        expNoDaftar = "090920130001";
//        expNoTindakan = "";
//
//        pt.setNoDaftar(expNoDaftar);
//        pt.setNoTindakan(expNoTindakan);
//        instance.serviceInsertPelayananTindakan(pt);
//
//        tabelModelPelayananTindakan.setData(instance.serviceGetAllByNoDaftar(expNoDaftar));
//        assertEquals(expNoDaftar, tabelModelPelayananTindakan.getValueAt(0, 1));
//        assertEquals(expNoTindakan, tabelModelPelayananTindakan.getValueAt(0, 2));
    }

    /**
     * Test of serviceUpdatePelayananTindakan method, of class PelayananTindakanService.
     */
    @Test
    public void b_updatePelayananTindakan() {
        System.out.println("2. serviceUpdatePelayananTindakan");
//        expNoDaftar = "";
//        expNoTindakan = "";
//
//        pt.setNoDaftar(expNoDaftar);
//        pt.setNoTindakan(expNoTindakan);
//        instance.serviceUpdatePelayananTindakan(expNoTindakan, pt);
//
//        tabelModelPelayananTindakan.setData(instance.serviceGetAllByNoDaftar(expNoDaftar));
//        assertEquals(expNoDaftar, tabelModelPelayananTindakan.getValueAt(0, 1));
//        assertEquals(expNoTindakan, tabelModelPelayananTindakan.getValueAt(0, 2));
    }

    /**
     * Test of serviceDeletePelayananTindakan method, of class PelayananTindakanService.
     */
    @Test
    public void c_deletePelayananTindakan() {
        System.out.println("3. serviceDeletePelayananTindakan");
//        expNoDaftar = "";
//        instance.serviceDeletePelayananTindakan(expNoDaftar);
//        tabelModelPelayananTindakan.setData(instance.serviceGetAllByNoDaftar(expNoDaftar));
//        assertEquals(0, tabelModelPelayananTindakan.getRowCount());
    }

    /**
     * Test of serviceGetAllPelayananTindakan method, of class PelayananTindakanService.
     */
    @Test
    public void d_getAllPelayananTindakan() {
        System.out.println("4. serviceGetAllPelayananTindakan");
//        tabelModelPelayananTindakan.setData(instance.serviceGetAllPelayananTindakan());
//        try {
//            Statement s = (Statement) connection.createStatement();
//            ResultSet rs = s.executeQuery(getAllPelayananTindakan);
//            while (rs.next()) {
//                pt.setNoDaftar(rs.getString("no_daftar"));
//                pt.setNoTindakan(rs.getString("no_tindakan"));
//                list.add(pt);
//            }
//            rs.close();
//            s.close();
//        } catch (Throwable t) {
//            // Sengaja dikosongin dulu
//        }
//
//        for (int i = 0; i < list.size(); i++) {
//            assertEquals(list.get(i).getNoDaftar(), tabelModelPelayananTindakan.getValueAt(i, 0));
//            assertEquals(list.get(i).getNoTindakan(), tabelModelPelayananTindakan.getValueAt(i, 1));
//        }
    }
}

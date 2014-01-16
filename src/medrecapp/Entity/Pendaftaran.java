/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Entity;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class Pendaftaran {
    private String noDaftar;
    private String noRm;
    private String noStaf;
    private String idUnitRs;
    private String idJaminan;
    private String noDokter;

    public String getIdJaminan() {
        return idJaminan;
    }

    public void setIdJaminan(String idJaminan) {
        this.idJaminan = idJaminan;
    }

    public String getIdUnitRs() {
        return idUnitRs;
    }

    public void setIdUnitRs(String idUnitRs) {
        this.idUnitRs = idUnitRs;
    }

    public String getNoDaftar() {
        return noDaftar;
    }

    public void setNoDaftar(String noDaftar) {
        this.noDaftar = noDaftar;
    }

    public String getNoDokter() {
        return noDokter;
    }

    public void setNoDokter(String noDokter) {
        this.noDokter = noDokter;
    }

    public String getNoRm() {
        return noRm;
    }

    public void setNoRm(String noRm) {
        this.noRm = noRm;
    }

    public String getNoStaf() {
        return noStaf;
    }

    public void setNoStaf(String noStaf) {
        this.noStaf = noStaf;
    }

}

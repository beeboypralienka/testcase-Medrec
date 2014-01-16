/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Interfaces;

import java.sql.SQLException;
import java.util.List;
import medrecapp.Entity.UnitRs;

/**
 *
 * @author Fachrul Pralienka BM
 */
public interface UnitRsInterface {

    public void insertUnitRs(UnitRs urs)throws SQLException;
    public void updateUnitRs(UnitRs urs, String idUnitRs)throws SQLException;
    public void deleteUnitRs(String idUnitRs)throws SQLException;
    public List getAllUnitRs()throws SQLException;

}

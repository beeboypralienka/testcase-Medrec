/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.TabelModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import medrecapp.Entity.UnitRs;

/**
 *
 * @author Hady
 */
public class TabelModelUnitRs extends AbstractTableModel{
    
    public List<UnitRs> list = new ArrayList<UnitRs>();

    public void setData(List<UnitRs>listUnitRs){
        this.list = listUnitRs;
        fireTableDataChanged();
    }

    public UnitRs getUnitRs(int i){
        return list.get(i);
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "ID Unit RS";
            case 1:
                return "Keterangan";
        }
        return null;
    }

    public int getRowCount() {
        return list.size();
    }

    public int getColumnCount() {
        return 2;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getIdUnitRs();
            case 1:
                return list.get(rowIndex).getKetUnitRs();
            default:
                return null;
        }
    }
    
}

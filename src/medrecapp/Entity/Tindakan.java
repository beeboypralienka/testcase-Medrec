/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Entity;

/**
 *
 * @author Hady
 */
public class Tindakan {
    private String noTindakan;
    private String nmTindakan;
    private String tndkSpesialis;
    private String ketTindakan;
    
    public String getNoTindakan(){
        return noTindakan;
    }
    
    public void setNoTindakan(String noTindakan){
        this.noTindakan = noTindakan;
    }
    
    public String getNmTindakan(){
        return nmTindakan;
    }
    
    public void setNmTindakan(String nmTindakan){
        this.nmTindakan = nmTindakan;
    }
    
    public String getTndkSpesialis(){
        return tndkSpesialis;
    }
    
    public void setTndkSpesialis(String tndkSpesialis){
        this.tndkSpesialis = tndkSpesialis;        
    }
    
    public String getKetTindakan(){
        return ketTindakan;
    }
    
    public void setKetTindakan(String ketTindakan){
        this.ketTindakan = ketTindakan;
    }
}
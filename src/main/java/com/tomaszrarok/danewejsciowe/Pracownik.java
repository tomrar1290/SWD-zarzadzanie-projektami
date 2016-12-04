/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomaszrarok.danewejsciowe;

import java.util.List;

/**
 *
 * @author tomasz.rarok
 */
public class Pracownik {
  public final String name;
  private List<String> umiejetnosci;
  private Double kosztGodzinyPracy;
  private Double procentowyKosztDouczenia; 

  public Pracownik(String name){
    this.name = name;
  }
  //<editor-fold defaultstate="collapsed" desc="Getter & Setter">
  public List<String> getUmiejetnosci() {
    return umiejetnosci;
  }
  
  public void setUmiejetnosci(List<String> umiejetnosci) {
    this.umiejetnosci = umiejetnosci;
  }
  
  public Double getKosztGodzinyPracy() {
    return kosztGodzinyPracy;
  }
  
  public void setKosztGodzinyPracy(Double kosztGodzinyPracy) {
    this.kosztGodzinyPracy = kosztGodzinyPracy;
  }
  
  public Double getProcentowyKosztDouczenia() {
    return procentowyKosztDouczenia;
  }
  
  public void setProcentowyKosztDouczenia(Double procentowyKosztDouczenia) {
    this.procentowyKosztDouczenia = procentowyKosztDouczenia;
  }
//</editor-fold>
  
}

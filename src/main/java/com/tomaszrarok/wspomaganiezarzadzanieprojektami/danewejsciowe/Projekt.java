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
public class Projekt {
  private List<String> wymaganiaUmiejetnosci;
  private List<Zadanie> zadania;
  private Double minimalnaZgodnoscUmiejetnosci;

  //<editor-fold defaultstate="collapsed" desc="Getter & Setter">
  public List<String> getWymagania() {
    return wymaganiaUmiejetnosci;
  }
  
  public void setWymagania(List<String> wymagania) {
    this.wymaganiaUmiejetnosci = wymagania;
  }
  
  public List<Zadanie> getZadania() {
    return zadania;
  }
  
  public void setZadania(List<Zadanie> zadania) {
    this.zadania = zadania;
  }
  
  public Double getMinimalnaZgodnoscUmiejetnosci() {
    return minimalnaZgodnoscUmiejetnosci;
  }
  
  public void setMinimalnaZgodnoscUmiejetnosci(Double minimalnaZgodnoscUmiejetnosci) {
    this.minimalnaZgodnoscUmiejetnosci = minimalnaZgodnoscUmiejetnosci;
  }
//</editor-fold>

}

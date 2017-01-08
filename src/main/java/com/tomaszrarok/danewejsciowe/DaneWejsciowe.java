/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomaszrarok.danewejsciowe;

import com.tomaszrarok.decyzja.ZbiorMozliwychDecyzji;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tomasz Rarok
 */
public class DaneWejsciowe {

    private Projekt projekt;
    private List<Pracownik> pracownicy;
    private Map<String, Double> priorytety;

    public DaneWejsciowe(Projekt projekt, List<Pracownik> pracownicy) {
        this.projekt = projekt;
        this.pracownicy = pracownicy;
    }

    public DaneWejsciowe(Map<String, Double> priorytety, Projekt projekt, List<Pracownik> pracownicy) {
        this.priorytety = priorytety;
        this.projekt = projekt;
        this.pracownicy = pracownicy;
    }

    public Projekt getProjekt() {
        return projekt;
    }

    public void setProjekt(Projekt projekt) {
        this.projekt = projekt;
    }

    public List<Pracownik> getPracownicy() {
        return pracownicy;
    }

    public void setPracownicy(List<Pracownik> pracownicy) {
        this.pracownicy = pracownicy;
    }

    public String validation() {
        String validation;
        validation = validatePriorytety();
        if (validation == null) {
            validation = validateProjekt();
        }
        if (validation == null) {
            validation = validatePracownicy();
        }
        return validation;
    }

    public Map<String, Double> getPriorytety() {
        return priorytety;
    }

    public void setPriorytety(Map<String, Double> priorytety) {
        this.priorytety = priorytety;
    }

    private String validatePriorytety() {
        if (priorytety == null || priorytety.size() != 3) {
            return "Brak priorytetow dla a priori";
        }
        if (!priorytety.containsKey(ZbiorMozliwychDecyzji.DW_KOSZT) || priorytety.get(ZbiorMozliwychDecyzji.DW_KOSZT) == null) {
            return "Brak kosztu";
        }
        if (!priorytety.containsKey(ZbiorMozliwychDecyzji.DW_CZAS) || priorytety.get(ZbiorMozliwychDecyzji.DW_CZAS) == null) {
            return "Brak czasu";
        }
        if (!priorytety.containsKey(ZbiorMozliwychDecyzji.DW_UMIEJETNOSC) || priorytety.get(ZbiorMozliwychDecyzji.DW_UMIEJETNOSC) == null) {
            return "Brak umiejetnosci";
        }
        for (Map.Entry<String, Double> entry : priorytety.entrySet()) {

            if (entry.getValue() <= 1.0) {
                return "Akceptowalny poziom kryterium powinien byc wiekszy niz 1.0";
            }

        }
        return null;
    }

    private String validateProjekt() {
        if (projekt == null) {
            return "Brak projektu";
        }
        if (projekt.getZadania() == null || projekt.getZadania().isEmpty()) {
            return "Brak zadan";
        }
        if (projekt.getWymagania() == null || projekt.getWymagania().isEmpty()) {
            return "Brak wymagan";
        }

        for (String wymaganie : projekt.getWymagania()) {
            if (wymaganie == null || wymaganie.trim().equals("")) {
                return "Wymagania musza byc uzupelnione";
            }
        }
        for (Zadanie zadanie : projekt.getZadania()) {
            if (zadanie == null || zadanie.getPrzewidywanyCzas() == null || zadanie.getPrzewidywanyCzas() <= 0.0) {
                return "Zadania musza byc uzupelnione";
            }
        }
        return null;
    }

    private String validatePracownicy() {
        if (pracownicy == null || pracownicy.isEmpty()) {
            return "Pracownicy musza byc uzupelnieni";
        }

        for (Pracownik pracownik : pracownicy) {
            if (pracownik == null
                    || pracownik.getKosztGodzinyPracy() == null
                    || pracownik.getProcentowyKosztDouczenia() == null
                    || pracownik.getUmiejetnosci() == null
                    || pracownik.getUmiejetnosci().isEmpty()) {
                return "Pracownik zle uzupelniony";
            }
            if (pracownik.getKosztGodzinyPracy() <= 0.0) {
                return "Pracownik powinien cos kosztowac";
            }
            if (pracownik.getProcentowyKosztDouczenia() <= 0.0) {
                return "Pracownik powinien miec jakis koszt douczenia";
            }
            for (String umiejetnosc : pracownik.getUmiejetnosci()) {
                if (umiejetnosc == null || umiejetnosc.trim().equals("")) {
                    return "Umiejetnosci pracownika musza byc uzupelnione";
                }
            }
        }
        return null;
    }

}

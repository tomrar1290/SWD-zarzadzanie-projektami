/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomaszrarok.decyzja;

import com.tomaszrarok.danewejsciowe.Pracownik;
import com.tomaszrarok.danewejsciowe.Projekt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tomasz.rarok
 */
public class Decyzja {

    final private Projekt projekt;
    final private Map<Integer, Pracownik> pracownikZadanie;

    private Double kosztDecyzji = null;
    private Double czasDecyzji = null;
    private Double niezgodnoscUmiejetnosci = null;
    private Double odlegloscOdDecyzjiIdealnej = null;

    public Decyzja(List<Integer> mozliwaDecyzja, Projekt projekt, Map<Integer, Pracownik> pracownicy) {
        this.projekt = projekt;
        this.pracownikZadanie = new HashMap<>();

        int index=0;
        for (Integer i : mozliwaDecyzja) {
            pracownikZadanie.put(i, pracownicy.get(mozliwaDecyzja.get(index)));
            index++;
        }
    }

    public Double pobierzKosztDecyzji() {
        if (kosztDecyzji != null) {
            return kosztDecyzji;
        }

        kosztDecyzji = 0.0;

        // FIXME koszt razy ilosc godzin na zadanie
        for (Map.Entry<Integer, Pracownik> entry : pracownikZadanie.entrySet()) {
            //kosztDecyzji += entry.getValue().getKosztGodzinyPracy();
        }

        return kosztDecyzji;
    }

    public Double pobierzCzasDecyzji() {
        if (czasDecyzji != null) {
            return czasDecyzji;
        }

        czasDecyzji = 0.0;

        // FIXME czas zadania * procent od nie posiadanych umiejetnosci
        for (Map.Entry<Integer, Pracownik> entry : pracownikZadanie.entrySet()) {
            //czasDecyzji += entry.getValue().getDlugoscWykonywaniaZadania();
        }

        return czasDecyzji;
    }

    public Double pobierzNiezgodnoscUmiejetnosci() {
        if (niezgodnoscUmiejetnosci == null) {
            List<String> temp;

            niezgodnoscUmiejetnosci = 0.0;
            for (Map.Entry<Integer, Pracownik> entry : pracownikZadanie.entrySet()) {

                temp = new ArrayList<String>(entry.getValue().getUmiejetnosci());
                temp.retainAll(projekt.getWymagania());

                //suma niezgodnosci
                niezgodnoscUmiejetnosci += 100 - (temp.size() * 100.0f) / projekt.getWymagania().size();
            }

            //srednia niezgodnosc ze wszystkich
            niezgodnoscUmiejetnosci /= pracownikZadanie.size();
        }
        return niezgodnoscUmiejetnosci;
    }

    private Double pojedynczaNiezgodnosc(Map.Entry<Integer, Pracownik> entry) {
        List<String> temp;

        temp = new ArrayList<String>(entry.getValue().getUmiejetnosci());
        temp.retainAll(projekt.getWymagania());

        //suma niezgodnosci
        return 100 - (temp.size() * 100.0) / projekt.getWymagania().size();
    }

    private Double pojedynczyCzas(Map.Entry<Integer, Pracownik> entry) {

        Double procent = entry.getValue().getProcentowyKosztDouczenia() / 100;
                
        return null;
        //return projekt.getWymagania().size();
    }

    private Double pojedynczyKoszt(Map.Entry<Integer, Pracownik> entry) {
        List<String> temp;

        temp = new ArrayList<String>(entry.getValue().getUmiejetnosci());
        temp.retainAll(projekt.getWymagania());

        //suma niezgodnosci
        return 100 - (temp.size() * 100.0) / projekt.getWymagania().size();
    }

    public String toString() {
        String pracownicy = "";
        for (Map.Entry<Integer, Pracownik> entrySet : pracownikZadanie.entrySet()) {
            Pracownik value = entrySet.getValue();
            pracownicy += " " + value.name;
        }
        return "Koszt: " + pobierzKosztDecyzji() + " Czas: " + pobierzCzasDecyzji() + pracownicy + " Niezgodnosc: " + pobierzNiezgodnoscUmiejetnosci();
    }

    public Double obliczOdlegloscOdDecyzjiIdealnej(Double czas, Double koszt, Double niezgodnosc) {
        odlegloscOdDecyzjiIdealnej = Math.sqrt(
                Math.pow(pobierzKosztDecyzji() - koszt, 2)
                + Math.pow(pobierzCzasDecyzji() - czas, 2)
                + Math.pow(pobierzNiezgodnoscUmiejetnosci() - niezgodnosc, 2)
        );

        return odlegloscOdDecyzjiIdealnej;
    }
}

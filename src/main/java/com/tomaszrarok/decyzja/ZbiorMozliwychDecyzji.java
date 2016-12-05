/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomaszrarok.decyzja;

import com.tomaszrarok.danewejsciowe.Pracownik;
import com.tomaszrarok.danewejsciowe.Projekt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tomasz Rarok
 */
public class ZbiorMozliwychDecyzji {

    private Double najmniejszyCzas = 0.0;
    private Double najmniejszyKoszt = 0.0;
    private Double najmniejszaNiezgodnosc = 0.0;

    private List<Decyzja> listaDecyzji;
    private Projekt projekt;

    public ZbiorMozliwychDecyzji(Projekt projekt, HashMap<Integer, Pracownik> map) {

        this.projekt = projekt;

        Integer[] wszyscyPracownicyZadania = new Integer[map.size() * projekt.getZadania().size()];

        Integer index = 0;
        for (Map.Entry<Integer, Pracownik> entry : map.entrySet()) {
            Integer key = entry.getKey();
            for (Integer i = 0; i < projekt.getZadania().size(); i++) {
                wszyscyPracownicyZadania[index] = key;
                index++;
            }
        }

        KombinacjaDecyzji kombinacjaDecyzji = new KombinacjaDecyzji(wszyscyPracownicyZadania, projekt.getZadania().size());
        List<List<Integer>> listaMozliwychDecyzji = kombinacjaDecyzji.getListaMozliwychDecyzji();

        listaDecyzji = new ArrayList<>();

        for (List<Integer> mozliwaDecyzja : listaMozliwychDecyzji) {
            listaDecyzji.add(new Decyzja(mozliwaDecyzja, projekt, map));
        }
    }

    private void pokazNajkrotszyCzas() {

        //posortuj po czasie
        Collections.sort(listaDecyzji, new Comparator<Decyzja>() {
            @Override
            public int compare(final Decyzja o1, final Decyzja o2) {
                if (o1.pobierzCzasDecyzji() > o2.pobierzCzasDecyzji()) {
                    return 1;
                } else if (o1.pobierzCzasDecyzji() < o2.pobierzCzasDecyzji()) {
                    return -1;
                }
                return 0;
            }
        });

        //System.out.println("Czas");
        for (Decyzja listaDecyzji1 : listaDecyzji) {
            //System.out.println(listaDecyzji1.toString());
        }

        najmniejszyCzas = listaDecyzji.get(0).pobierzCzasDecyzji();

    }

    private void pokazNajmniejszyKoszt() {
        //posortuj po koszcie
        Collections.sort(listaDecyzji, new Comparator<Decyzja>() {
            @Override
            public int compare(final Decyzja o1, final Decyzja o2) {
                if (o1.pobierzKosztDecyzji() > o2.pobierzKosztDecyzji()) {
                    return 1;
                } else if (o1.pobierzKosztDecyzji() < o2.pobierzKosztDecyzji()) {
                    return -1;
                }
                return 0;
            }
        });

        //System.out.println("Koszt:");
        for (Decyzja listaDecyzji1 : listaDecyzji) {
            //System.out.println(listaDecyzji1.toString());
        }

        najmniejszyKoszt = listaDecyzji.get(0).pobierzKosztDecyzji();
    }

    private void pokazNajmiejszaNiezgodnosc() {
        //posortuj po niezgodnosci
        Collections.sort(listaDecyzji, new Comparator<Decyzja>() {
            @Override
            public int compare(final Decyzja o1, final Decyzja o2) {
                if (o1.pobierzNiezgodnoscUmiejetnosci() > o2.pobierzNiezgodnoscUmiejetnosci()) {
                    return 1;
                } else if (o1.pobierzNiezgodnoscUmiejetnosci() < o2.pobierzNiezgodnoscUmiejetnosci()) {
                    return -1;
                }
                return 0;
            }
        });

        //System.out.println("Niezgodnosc:");
        for (Decyzja listaDecyzji1 : listaDecyzji) {
            //System.out.println(listaDecyzji1.toString());
        }

        najmniejszaNiezgodnosc = listaDecyzji.get(0).pobierzNiezgodnoscUmiejetnosci();
    }

    public List<Decyzja> pobierzWedlugNajlepszej() {
        
        pokazNajkrotszyCzas();
        pokazNajmniejszyKoszt();
        pokazNajmiejszaNiezgodnosc();

        //posortuj po odleglosci idealnej
        Collections.sort(listaDecyzji, new Comparator<Decyzja>() {
            @Override
            public int compare(final Decyzja o1, final Decyzja o2) {
                if (o1.obliczOdlegloscOdDecyzjiIdealnej(najmniejszyCzas, najmniejszyKoszt, najmniejszaNiezgodnosc) > o2.obliczOdlegloscOdDecyzjiIdealnej(najmniejszyCzas, najmniejszyKoszt, najmniejszaNiezgodnosc)) {
                    return 1;
                } else if (o1.obliczOdlegloscOdDecyzjiIdealnej(najmniejszyCzas, najmniejszyKoszt, najmniejszaNiezgodnosc) < o2.obliczOdlegloscOdDecyzjiIdealnej(najmniejszyCzas, najmniejszyKoszt, najmniejszaNiezgodnosc)) {
                    return -1;
                }
                return 0;
            }
        });

        System.out.println("Idealnie:");

        for (Decyzja listaDecyzji1 : listaDecyzji) {
            System.out.println(listaDecyzji1.toString());
        }
        
        return new ArrayList<Decyzja>(listaDecyzji);
    }

}

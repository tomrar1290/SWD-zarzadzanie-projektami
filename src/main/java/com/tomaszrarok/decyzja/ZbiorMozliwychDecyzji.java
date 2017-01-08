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
import java.util.Iterator;
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
    private final Projekt projekt;
    private final Map<String, Double> priorytety;

    public ZbiorMozliwychDecyzji(Projekt projekt, Map<Integer, Pracownik> map, Map<String, Double> priorytety) {

        this.projekt = projekt;
        this.priorytety = priorytety;

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
            System.out.println(mozliwaDecyzja.toString());
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

        return new ArrayList<Decyzja>(listaDecyzji);
    }

    public List<Decyzja> pobierzWedlugApriori() {
        List<Decyzja> listaApriori = new ArrayList<>(listaDecyzji);
        //zapisujemy parametr, po wykonaniu petli mozemy posortowac
        String parametrOptymalizacji = null;
        for (Map.Entry<String, Double> entry : priorytety.entrySet()) {
            Iterator<Decyzja> iter = listaApriori.iterator();
            while (iter.hasNext()) {
                parametrOptymalizacji = entry.getKey();
                if (entry.getKey().equals(DW_KOSZT)) {
                    if (iter.next().pobierzKosztDecyzji() > entry.getValue() * najmniejszyKoszt) {
                        iter.remove();
                    }
                }
                if (entry.getKey().equals(DW_CZAS)) {
                    if (iter.next().pobierzCzasDecyzji() > entry.getValue() * najmniejszyCzas) {
                        iter.remove();
                    }
                }
                if (entry.getKey().equals(DW_UMIEJETNOSC)) {
                    if (iter.next().pobierzNiezgodnoscUmiejetnosci() > entry.getValue() * najmniejszaNiezgodnosc) {
                        iter.remove();
                    }
                }
            }
        }

        pokazPoOstatnimKrytzerium(parametrOptymalizacji);
        return listaApriori;
    }
    
    public static final String DW_UMIEJETNOSC = "umiejetnosc";
    public static final String DW_CZAS = "czas";
    public static final String DW_KOSZT = "koszt";

    private void pokazPoOstatnimKrytzerium(String parametrOptymalizacji) {
        if (parametrOptymalizacji.equals(DW_KOSZT)) {
            pokazNajmniejszyKoszt();
        }
        if (parametrOptymalizacji.equals(DW_CZAS)) {
            pokazNajkrotszyCzas();
        }
        if (parametrOptymalizacji.equals(DW_UMIEJETNOSC)) {
            pokazNajmiejszaNiezgodnosc();
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomaszrarok;

import com.tomaszrarok.danewejsciowe.Pracownik;
import com.tomaszrarok.danewejsciowe.Projekt;
import com.tomaszrarok.danewejsciowe.Zadanie;
import com.tomaszrarok.decyzja.Decyzja;
import com.tomaszrarok.decyzja.KombinacjaDecyzji;
import com.tomaszrarok.decyzja.ZbiorMozliwychDecyzji;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tomasz.rarok
 */
public class Main {

    public static void main(String[] args) {

        
        //Zadania
        Zadanie zadanie1 = new Zadanie(5.0);
Zadanie zadanie2 = new Zadanie(7.0);
Zadanie zadanie3 = new Zadanie(10.0);
Zadanie zadanie4 = new Zadanie(4.0);
Zadanie zadanie5 = new Zadanie(6.0);
Zadanie zadanie6 = new Zadanie(20.0);
Zadanie zadanie7 = new Zadanie(4.0);

        List zadania = new ArrayList( Arrays.asList(zadanie1, zadanie2, zadanie3, zadanie4, zadanie5, zadanie6, zadanie7));
        //PROJEKT
        Projekt projekt = new Projekt();
        projekt.setZadania(zadania);
        projekt.setWymagania(new ArrayList<>(Arrays.asList(new String[]{"umiejetnosc1", "umiejetnosc2", "umiejetnosc3", "umiejetnosc4", "umiejetnosc5", "umiejetnosc6"})));

        //PRACOWNIK 1
        Pracownik pracownik1 = new Pracownik("p1");
        pracownik1.setDlugoscWykonywaniaZadania(3.0f);
        pracownik1.setKosztGodzinyPracy(20.0f);
        pracownik1.setUmiejetnosci(new ArrayList<>(Arrays.asList(new String[]{"umiejetnosc1", "umiejetnosc2", "umiejetnosc3", "umiejetnosc4", "umiejetnosc5"})));

        //PRACOWNIK 2
        Pracownik pracownik2 = new Pracownik("p2");
        pracownik2.setDlugoscWykonywaniaZadania(4.0f);
        pracownik2.setKosztGodzinyPracy(18.0f);
        pracownik2.setUmiejetnosci(new ArrayList<>(Arrays.asList(new String[]{"umiejetnosc1", "umiejetnosc2", "umiejetnosc3", "umiejetnosc4"})));

        //PRACOWNIK 3
        Pracownik pracownik3 = new Pracownik("p3");
        pracownik3.setDlugoscWykonywaniaZadania(4.0f);
        pracownik3.setKosztGodzinyPracy(15.0f);
        pracownik3.setUmiejetnosci(new ArrayList<>(Arrays.asList(new String[]{"umiejetnosc1", "umiejetnosc2", "umiejetnosc3"})));

        //PRACOWNIK 4
        Pracownik pracownik4 = new Pracownik("p4");
        pracownik4.setDlugoscWykonywaniaZadania(2.0f);
        pracownik4.setKosztGodzinyPracy(25.0f);
        pracownik4.setUmiejetnosci(new ArrayList<String>(Arrays.asList(new String[]{"umiejetnosc1", "umiejetnosc2"})));

       
        HashMap<Integer, Pracownik> mapaPracownikow = new HashMap<>();
        mapaPracownikow.put(1, pracownik1);
        mapaPracownikow.put(2, pracownik2);
        mapaPracownikow.put(3, pracownik3);
        mapaPracownikow.put(4, pracownik4);
        
        

        ZbiorMozliwychDecyzji zmd = new ZbiorMozliwychDecyzji(projekt, mapaPracownikow);
        
        zmd.pokazNajblizszeIdealnej();

    }

}

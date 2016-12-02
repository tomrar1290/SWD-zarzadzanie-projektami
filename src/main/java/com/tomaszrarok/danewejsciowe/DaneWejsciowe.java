/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomaszrarok.danewejsciowe;

import java.util.List;

/**
 *
 * @author Tomasz Rarok
 */
public class DaneWejsciowe {
    private Projekt projekt;
    private List<Pracownik> pracownicy;

    public DaneWejsciowe(Projekt projekt, List<Pracownik> pracownicy) {
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
    
    
}

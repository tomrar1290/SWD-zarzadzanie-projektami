/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomaszrarok.decyzja;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author tomasz.rarok
 */
public class KombinacjaDecyzji {
  
  private final List<int []> listaMozliwychDecyzji;

  public List<int[]> getListaMozliwychDecyzji() {
    return listaMozliwychDecyzji;
  }
  
  public KombinacjaDecyzji(int [] listaWszystkichPracownikowZadan, int listaZadanProjekt){
     listaMozliwychDecyzji = new ArrayList<>();     
     zapiszMozliweKombinacje(listaWszystkichPracownikowZadan, listaWszystkichPracownikowZadan.length, listaZadanProjekt);     
  }
  
  // Głowna funkcja do wyszukania kombinacji o rozmiarze r z tablicy arr[] o rozmiarze n
  private void zapiszMozliweKombinacje(int arr[], int n, int r) {
    // przechowuj kolejne kombinacje w tablicy data
    int[] data = new int[r];

    // posortuj tablice, by uniknąć duplikatów
    Arrays.sort(arr);

    // Print all combination using temprary array 'data[]'
    znajdzKombinacje(arr, data, 0, n - 1, 0, r);
  }

  private void znajdzKombinacje(int arr[], int data[], int start, int end, int index, int r) {

    if (index == r) {
      //kombinacja ma rozmiar szukany r, szukaj kolejnych
      listaMozliwychDecyzji.add( Arrays.copyOf(data, data.length) );     
      return;
    }

    for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
      data[index] = arr[i];
      znajdzKombinacje(arr, data, i + 1, end, index + 1, r);

      // usuń duplikaty, pętla idzie od lewej stron, wszystko po prawej będzie dodane rekurencyjnie
      while (i < arr.length - 1 && arr[i] == arr[i + 1]) {
        i++;
      }
    }
  }
  
}

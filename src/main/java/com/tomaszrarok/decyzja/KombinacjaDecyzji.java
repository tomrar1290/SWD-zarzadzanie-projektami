/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomaszrarok.decyzja;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author tomasz.rarok
 */
public class KombinacjaDecyzji {
  
  private final List<List<Integer>> listaMozliwychDecyzji;

  public List<List<Integer>> getListaMozliwychDecyzji() {
    //System.out.println(listaMozliwychDecyzji.toString());
    return listaMozliwychDecyzji;
  }
  
  public KombinacjaDecyzji(Integer [] listaWszystkichPracownikowZadan, Integer listaZadanProjekt){
     listaMozliwychDecyzji = new ArrayList<>();     
     zapiszMozliweKombinacje(listaWszystkichPracownikowZadan, listaWszystkichPracownikowZadan.length, listaZadanProjekt);     
  }
  
  // Głowna funkcja do wyszukania kombinacji o rozmiarze r z tablicy arr[] o rozmiarze n
  private void zapiszMozliweKombinacje(Integer arr[], Integer n, Integer r) {
    // przechowuj kolejne kombinacje w tablicy data
    Integer[] data = new Integer[r];

    // posortuj tablice, by uniknąć duplikatów
    Arrays.sort(arr);

    // Print all combination using temprary array 'data[]'
    znajdzKombinacje(arr, data, 0, n - 1, 0, r);
  }

  private void znajdzKombinacje(Integer arr[], Integer data[], Integer start, Integer end, Integer index, Integer r) {

    if (Objects.equals(index, r)) {
      System.out.println(Arrays.toString(data));
      //kombinacja ma rozmiar szukany r, szukaj kolejnych
      listaMozliwychDecyzji.addAll( pobierzPermutacje(Arrays.copyOf(data, data.length)) );     
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
  
    private Set<List<Integer>> pobierzPermutacje(Integer [] combination) {       
      Set<List<Integer>> setOfArray = new HashSet<>();
        
      pobierzPermutacje(combination, 0, setOfArray); 
      //System.err.println(setOfArray.toString());
      
      return setOfArray;
  }
    
    private void pobierzPermutacje(Integer[] input, Integer startindex, Set<List<Integer>> setOfArrays) {
        int size = input.length;

        if (size == startindex + 1) {
            setOfArrays.add(Arrays.asList( Arrays.copyOf(input, input.length)));
        } else {
            for (int i = startindex; i < size; i++) {

                int temp = input[i];
                input[i] = input[startindex];
                input[startindex] = temp;
                pobierzPermutacje(input, startindex + 1, setOfArrays);
            }
        }
    }

  
}

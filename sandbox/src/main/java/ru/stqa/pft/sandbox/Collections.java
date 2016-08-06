package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by eshkuratova on 04.08.2016.
 */
public class Collections {

  public static void main(String[] args){
    String[] langs={"java","C#", "python","php"};
    List<String> languages = new ArrayList<String>();
    languages= Arrays.asList("Java","PHP","C#");
    /*languages.add("java");
    languages.add("C#");
    languages.add("php");*/
    for(String l:languages){
      System.out.println("Я хочу выучить язык "+l);
    }
    for(int i=0;i<languages.size();i++){
      System.out.println("Я хочу выучить язык " +languages.get(i));
    }
    for(int i=0;i<langs.length;i++){
      System.out.println("Я хочу выучить язык "+langs[i]);
    }
    for(String l:langs){
      System.out.println("Я хочу выучить язык "+l);
    }
  }
}

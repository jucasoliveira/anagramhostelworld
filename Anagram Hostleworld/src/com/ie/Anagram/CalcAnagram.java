package com.ie.Anagram;

import java.util.Arrays;
import java.util.Scanner;


public class CalcAnagram {
	public static void main(String[] args) {
		Scanner ent = new Scanner(System.in);
        String s, r;
        System.out.println("write the word/frase:");
        // String input
        s = ent.nextLine();
        r = ent.nextLine();
        // Verifying if the strings are anagram or not
        if (isAnagram(s, r)) {
            System.out.println(s+" is anagram of "+r);
        }
        else{
        	System.out.println(s+" isn't anagram of "+r);
        	
        }
    }
	
	public static boolean isAnagram(String s1, String s2) {
		
		//Calling normalize method to eliminate case sensitive
		String s3 = normalize(s1);
		String s4 = normalize(s2);
		
	    //If both strings don't have the same size, isn't anagram
        if ( s3.length() != s4.length() ) {
        	return false;
                       
        }
        //Tranfrom in arrays to order
        char[] c1 = s3.toCharArray();
        char[] c2 = s4.toCharArray();
        //Order to make shure an simplyfing comparison
        Arrays.sort(c1);
        Arrays.sort(c2);
        //Created new strings based on the ordernated array
        String sc3 = new String(c1);
        String sc4 = new String(c2);
        return sc3.equals(sc4); 	
    }
	
	//Method created to eliminate case sensitive
	private static String normalize(String word) {
	    return word.toLowerCase().replaceAll("[^a-z0-9]", "");
	}
  
}

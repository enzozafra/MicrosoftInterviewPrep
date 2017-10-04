import java.util.*;
import java.io.*;

class ArrayAndStringsPractice {

  // determine if a string has all unique characters
  /**
    * If not allowed to use extra data structures, we can compare each chacter
    * to every other character in the string. O(n^2) solution

    * Or if allowed to edit the array, we can sort then linearly check neighboring
    * characters that are identical. O(n log n) solution because sort.
    */

  public static boolean isUnique(String input) {
    // assuming ascii, we can check if the length is > 128. if more than 128
    // then we know that at least one char is not unique.
    if (input.length() > 128) {
      return false;
    }

    // Truth table for each character
    boolean[] array = new boolean[128];

    char[] strArray = input.toCharArray();
    for (char item : strArray) {
      int val = (int) item;
      if (array[val]) {
        return false;
      }
      else {
        array[val] = true;
      }
    }
    return true;
  }

  // Determine if two strings are permutations of one another
  /**
    * Should ask interviewer if case-sensitive and if whitespace matter.
    * Solution 1: sort string and then compare O(n log n) due to sort
    * Solution 2: hashmap that tracks how many times a character is seen
      Solution 3: instead of hashmap, initialize an array int[128] if ascii
    */

  // helper method that creates the number of occurences of a char
  public static HashMap<Character, Integer> createMap(String input) {
    HashMap<Character, Integer> output = new HashMap<Character, Integer>();
    for (int i = 0; i < input.length(); i++) {
      char item = input.charAt(i);
      int value = 0;
      if (output.containsKey(item)) {
        value = output.get(item);
      }
      output.put(item, value + 1);
    }
    return output;
  }

  // checker for permutation
  public static boolean checkPermutation(String input1, String input2) {
    if (input1.length() != input2.length()) {
      return false;
    }
    HashMap<Character, Integer> occur1 = createMap(input1);
    HashMap<Character, Integer> occur2 = createMap(input2);

    for (char item : occur1.keySet()) {
      if(occur1.get(item) != occur2.get(item)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String [] args) {
    testUnique();
    testPermutation();
  }

  public static void testUnique() {
    String test1 = "abcdefghijklmnopqrstuvwxyz";
    String test2 = "racecar";
    String test3 = "";
    String test4 = "has 2 wite ";
    String test5 = "has 1wite";

    if (isUnique(test1)) {
      System.out.println("String '" + test1 + "' is unique");
    } else {
      System.out.println("String '" + test1 + "' is NOT unique");
    }
    if (isUnique(test2)) {
      System.out.println("String '" + test2 + "' is unique");
    } else {
      System.out.println("String '" + test2 + "' is NOT unique");
    }
    if (isUnique(test3)) {
      System.out.println("String '" + test3 + "' is unique");
    } else {
      System.out.println("String '" + test3 + "' is NOT unique");
    }
    if (isUnique(test4)) {
      System.out.println("String '" + test4 + "' is unique");
    } else {
      System.out.println("String '" + test4 + "' is NOT unique");
    }
    if (isUnique(test5)) {
      System.out.println("String '" + test5 + "' is unique");
    } else {
      System.out.println("String '" + test5 + "' is NOT unique");
    }
  }

  public static void testPermutation() {
    String test1_s1 = "God";
    String test1_s2 = "god";

    String test2_s1 = " test";
    String test2_s2 = "estt ";

    String test3_s1 = "";
    String test3_s2 = "god";

    String test4_s1 = "   ";
    String test4_s2 = "   ";

    String test5_s1 = "   ";
    String test5_s2 = " ";

    String test6_s1 = "permutation";
    String test6_s2 = "repttmuaion";

    String test7_s1 = "permutationnot";
    String test7_s2 = "repttmuaionlol";

    if (checkPermutation(test1_s1, test1_s2)) {
      System.out.println("String1: " + test1_s1 + " String2: " + test1_s2 + " is permutation");
    } else {
      System.out.println("String1: " + test1_s1 + " String2: " + test1_s2 + " is NOT permutation");
    }

    if (checkPermutation(test2_s1, test2_s2)) {
      System.out.println("String1: " + test2_s1 + " String2: " + test2_s2 + " is permutation");
    } else {
      System.out.println("String1: " + test2_s1 + " String2: " + test2_s2 + " is NOT permutation");
    }

    if (checkPermutation(test3_s1, test3_s2)) {
      System.out.println("String1: " + test3_s1 + " String2: " + test3_s2 + " is permutation");
    } else {
      System.out.println("String1: " + test3_s1 + " String2: " + test3_s2 + " is NOT permutation");
    }

    if (checkPermutation(test4_s1, test4_s2)) {
      System.out.println("String1: " + test4_s1 + " String2: " + test4_s2 + " is permutation");
    } else {
      System.out.println("String1: " + test4_s1 + " String2: " + test4_s2 + " is NOT permutation");
    }

    if (checkPermutation(test5_s1, test5_s2)) {
      System.out.println("String1: " + test5_s1 + " String2: " + test5_s2 + " is permutation");
    } else {
      System.out.println("String1: " + test5_s1 + " String2: " + test5_s2 + " is NOT permutation");
    }

    if (checkPermutation(test6_s1, test6_s2)) {
      System.out.println("String1: " + test6_s1 + " String2: " + test6_s2 + " is permutation");
    } else {
      System.out.println("String1: " + test6_s1 + " String2: " + test6_s2 + " is NOT permutation");
    }

    if (checkPermutation(test7_s1, test7_s2)) {
      System.out.println("String1: " + test7_s1 + " String2: " + test7_s2 + " is permutation");
    } else {
      System.out.println("String1: " + test7_s1 + " String2: " + test7_s2 + " is NOT permutation");
    }
  }
}

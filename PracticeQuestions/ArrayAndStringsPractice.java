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

  public static void urlify(char[] str, int trueLength) {
    int spaceCount = 0, index;
    for (int i = 0; i < trueLength; i++) {
      if (str[i] == ' ') {
        spaceCount++;
      }
    }
    index = trueLength + spaceCount * 2; // need to triple the space value
    if (trueLength < str.length) {
      // end of array
      str[trueLength] = '\0';
    }
    for (int i = trueLength - 1; i >= 0; i--) {
      if(str[i] == ' ') {
        str[index - 1] = '0';
        str[index - 2] = '2';
        str[index - 3] = '%';
        index = index - 3;
      } else {
        str[index - 1] = str[i];
        index --;
      }
    }
  }

  public static boolean palin_perm(String input) {
    input = input.toLowerCase();
    int countOdd = 0;
    int[] count = new int[128];
    for (int i = 0; i < input.length(); i++) {
      int x = input.charAt(i);
      if(x == ' ') {
        continue;
      }
      else {
        count[x]++;
        if ((count[x] % 2) == 1) {
          countOdd++;
        } else {
          countOdd--;
        }
      }
    }
    return countOdd <= 1;
  }

  static boolean oneEditReplace(String s1, String s2) {
    boolean foundDifference = false;
    for (int i = 0; i < s1.length(); i++) {
      if (s1.charAt(i) != s2.charAt(i)) {
        if (foundDifference) {
          return false;
        }
        foundDifference = true;
      }
    }
    return true;
  }

  static boolean oneEditInsert(String s1, String s2) {
    int index1 = 0;
    int index2 = 0;
    while (index2 < s2.length() && index1 < s1.length()) {
      if (s1.charAt(index1) != s2.charAt(index2)) {
        if (index1 != index2) {
          return false;
        }
        index2++;
      } else {
        index1++;
        index2++;
      }
    }
    return true;
  }

  public static String compression(String s1) {
    if (s1 == "") {
      return s1;
    }

    StringBuilder sb = new StringBuilder();
    int count = 0;
    char prev = s1.charAt(0);

    for (int i = 0; i < s1.length(); i++) {
      char item = s1.charAt(i);
      if (item == prev) {
        count++;
      } else {
        sb.append(prev);
        sb.append(String.valueOf(count));
        count = 1;
      }
      prev = item;
    }
    sb.append(prev);
    sb.append(String.valueOf(count));
    return sb.length() < s1.length() ? sb.toString() : s1;
  }

  // Cleaner solution to compression also check length first to avoid SB time.
  public static String compression_better(String s1) {
    // check final length and return input string if it would be longer
    int finalLength = countCompression(s1);
    if (finalLength >= s1.length()) return s1;

    StringBuilder compressed = new StringBuilder();
    int count = 0;
    for (int i = 0; i < s1.length(); i++) {
      count++;

      // if next is different from current, append to SB.
      if (i + 1 >= s1.length() || s1.charAt(i) != s1.charAt(i+1)) {
        compressed.append(s1.charAt(i));
        compressed.append(count);
        count = 0;
      }
    }
    return compressed.toString();
  }

  public static int countCompression(String s1) {
    int compressedLength = 0;
    int count = 0;
    for (int i = 0; i < s1.length(); i++) {
      count++;

      // if next is different from current, append to SB.
      if (i + 1 >= s1.length() || s1.charAt(i) != s1.charAt(i+1)) {
        compressedLength += 1 + String.valueOf(count).length();
        count = 0;
      }
    }
    return compressedLength;
  }

  public static boolean oneaway(String first, String second) {
    if (first.length() == second.length()) {
      return oneEditReplace(first, second);
    } else if (first.length() + 1 == second.length()) {
      return oneEditInsert(first, second);
    } else if (first.length() - 1 == second.length()) {
      return oneEditInsert(second, first);
    }
    return false;
  }


  public static void main(String [] args) {
    // testUnique();
    // testPermutation();
    // testurlify();
    // testpalindrome();
    test_compression();
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

  public static void testurlify() {
    char[] test = "I am a normal string".toCharArray();
    urlify(test, test.length);
    System.out.println(test);

    test = "I am a string with spaces in the end    ".toCharArray();
    urlify(test, test.length - 4);
    System.out.println(test);

    test = "     I am a string with spaces in the beginning".toCharArray();
    urlify(test, test.length - 5);
    System.out.println(test);

    test = "I     am     a string with spaces      in the middle".toCharArray();
    urlify(test, test.length - 13);
    System.out.println(test);

  }

  public static void testpalindrome() {
    String input = "Tact Coa";
    if (palin_perm(input)) {
      System.out.println(input + " is true!");
    } else {
      System.out.println(input + " is false :(");
    }

    String input1 = "Tact                     Coa";
    if (palin_perm(input1)) {
      System.out.println(input1 + " is true!");
    } else {
      System.out.println(input1 + " is false :(");
    }

    String input2 = "Tact Coa     ";
    if (palin_perm(input2)) {
      System.out.println(input2 + " is true!");
    } else {
      System.out.println(input2 + " is false :(");
    }

    String input3 = "            Tact Coa";
    if (palin_perm(input3)) {
      System.out.println(input3 + " is true!");
    } else {
      System.out.println(input3 + " is false :(");
    }

    String input4 = "";
    if (palin_perm(input4)) {
      System.out.println(input4 + " is true!");
    } else {
      System.out.println(input4 + " is false :(");
    }

    String input5 = "TactCoa";
    if (palin_perm(input5)) {
      System.out.println(input5 + " is true!");
    } else {
      System.out.println(input5 + " is false :(");
    }

    String input6 = "Tact Coat";
    if (palin_perm(input6)) {
      System.out.println(input6 + " is true!");
    } else {
      System.out.println(input6 + " is false :(");
    }

    String input7 = "Taactt Coaccat";
    if (palin_perm(input7)) {
      System.out.println(input7 + " is true!");
    } else {
      System.out.println(input7 + " is false :(");
    }
  }

  public static void test_compression() {
    String test1 = "aabcccccaaa";
    String test2 = "abcdefghijklmnop";
    String test3 = "aaaaaaaaaa";
    String test4 = "";
    String test5 = "aabb";

    System.out.println("Uncompressed: " + test1 + " compressed: " + compression(test1));
    System.out.println("Uncompressed: " + test2 + " compressed: " + compression(test2));
    System.out.println("Uncompressed: " + test3 + " compressed: " + compression(test3));
    System.out.println("Uncompressed: " + test4 + " compressed: " + compression(test4));
    System.out.println("Uncompressed: " + test5 + " compressed: " + compression(test5));
  }
}

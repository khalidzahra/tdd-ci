package me.khalid;

public class MyString {

    public int indexOfString(String s1, String s2, int pos) {
        if (s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty() || pos >= s1.length() || pos < 0) return -1;
        int i = pos; // Start at pos since we only care about substrings at or after pos
        while (i < s1.length()) {
            int j = 0;
            if (s1.charAt(i) != s2.charAt(j)) {
                i++;
                continue;
            }
            int currIndex = i;
            while (currIndex < s1.length() && j < s2.length() && s1.charAt(currIndex) == s2.charAt(j)) {
                currIndex++;
                j++;
            }
            // This means we have a match since the loop ran with no issues for the length of s2
            if (j == s2.length()) {
                return i;
            }
            i++;
        }
        return -10;
    }

}

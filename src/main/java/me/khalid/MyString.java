package me.khalid;

public class MyString {

    /**
     * Finds the position of the first occurrence of s2 in s1 at or after a specified position.
     *
     * @param s1  String object containing the target string
     * @param s2  String object contained the string to match
     * @param pos Integer specifying the position to start at
     * @return Integer representing the position of s2 in s1 if found. Returns -1 otherwise.
     */
    public int indexOfString(String s1, String s2, int pos) {
        if (s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty() || pos >= s1.length() || pos < 0) return -1;
        int i = pos; // Start at pos since we only care about substrings at or after pos
        while (i < s1.length()) {
            int j = 0;
            if (s1.charAt(i) != s2.charAt(j)) {
                i++;
                continue;
            }
            // Create a pointer for s1 to check if the substring corresponds with s2
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
        return -1; // Only reached when s2 not in s1, so just return -1 by default.
    }

    public String replace(String s, String s1, String s2) {
        if (s == null || s1 == null || s2 == null) return null;
        if (s1.isEmpty()) return s;
        return "";
    }

}

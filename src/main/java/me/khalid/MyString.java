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

    /**
     * Replaces all occurrences of s1 in s with s2.
     *
     * @param s String object containing target string.
     * @param s1 String object containing string to be replaced.
     * @param s2 String object containing string to replace s1.
     * @param myString MyString object to be used to access the indexOfString method.
     * @return Returns the string after replacements are made. Returns original s if s1 is empty,
     *         or null if any of the strings are null.
     */
    public String replace(String s, String s1, String s2, MyString myString) {
        if (s == null || s1 == null || s2 == null) return null;
        if (s1.isEmpty()) return s;
        int lastFound = myString.indexOfString(s, s1, 0); // Contains the last index identified where s1 was found in s
        int currentIndex = 0;
        StringBuilder finalString = new StringBuilder();
        // Keep looping as long as s1 is found in s
        while (lastFound > -1) {
            // Only add portions of s between instances of s1
            for (int i = currentIndex; i < lastFound; i++) {
                finalString.append(s.charAt(i));
            }
            finalString.append(s2);
            currentIndex = lastFound + s1.length(); // Next time start adding from after the current s1 instance
            lastFound = myString.indexOfString(s, s1, lastFound + 1);
        }
        // Add the rest of s into the final string
        for (int i = currentIndex; i < s.length(); i++) {
            finalString.append(s.charAt(i));
        }
        return finalString.toString(); // Only reached when no more occurrences of s2 are found
    }

}

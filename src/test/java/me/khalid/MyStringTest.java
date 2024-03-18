package me.khalid;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.AdditionalMatchers.*;
import static org.mockito.ArgumentMatchers.eq;

public class MyStringTest {

    /**
     * Data structure used for representing test cases for the indexOfString method
     */
    class IndexOfStringCase {
        public String s1;
        public String s2;
        public int pos;
        public int expected;

        public IndexOfStringCase(String s1, String s2, int pos, int expected) {
            this.s1 = s1;
            this.s2 = s2;
            this.pos = pos;
            this.expected = expected;
        }
    }

    /**
     * Data structure used for representing test cases for the replace method
     */
    class ReplaceCase {
        public String s;
        public String s1;
        public String s2;
        public String expected;

        public ReplaceCase(String s, String s1, String s2, String expected) {
            this.s = s;
            this.s1 = s1;
            this.s2 = s2;
            this.expected = expected;
        }
    }

    private static MyString myString;

    @BeforeClass
    public static void setUpBeforeClass() {
        myString = new MyString();
    }

    @Test
    public void testIndexOfString() {
        List<IndexOfStringCase> cases = Arrays.asList(
                new IndexOfStringCase(null, null, 10, -1),
                new IndexOfStringCase("", "", 10, -1),
                new IndexOfStringCase("aabb", "ab", 10, -1),
                new IndexOfStringCase("aabb", "ab", -1, -1),
                new IndexOfStringCase("aabb", "ab", 0, 1),
                new IndexOfStringCase("hihellowelcome", "lowel", 5, 5),
                new IndexOfStringCase("himynameiskhalid", "meiskha", 3, 6),
                new IndexOfStringCase("himynameiskhalid", "z", 0, -1),
                new IndexOfStringCase("himynameiskhalid", "khalidz", 0, -1),
                new IndexOfStringCase("himynameiskhalid", "thisstringismuchbiggerthans1", 0, -1),
                new IndexOfStringCase("himynameiskhalid", "", 0, -1),
                new IndexOfStringCase("", "thisstringismuchbiggerthans1", 0, -1)
        );
        cases.forEach(testCase -> assertEquals(testCase.expected, myString.indexOfString(testCase.s1, testCase.s2, testCase.pos)));
    }

    @Test
    public void testReplace() {
        List<ReplaceCase> cases = Arrays.asList(
                new ReplaceCase(null, null, null, null),
                new ReplaceCase(null, "", "", null),
                new ReplaceCase("", null, "", null),
                new ReplaceCase("", "", null, null),
                new ReplaceCase("string", "", "", "string"),
                new ReplaceCase("thisisastring", "", "", "thisisastring"),
                new ReplaceCase("thisisastring", "this", "that", "thatisastring"),
                new ReplaceCase("thisisastring", "is", "aa", "thaaaaastring"),
                new ReplaceCase("thisisastring", "is", "", "thastring"),
                new ReplaceCase("thisisastring", "is", "thisisaprettylargestring", "ththisisaprettylargestringthisisaprettylargestringastring"),
                new ReplaceCase("thisisastring", "notfound", "thisisaprettylargestring", "thisisastring")
        );

        cases.forEach(testCase -> assertEquals(testCase.expected, myString.replace(testCase.s, testCase.s1, testCase.s2, myString)));
    }

    @Test
    public void testReplaceMocked() {
        List<ReplaceCase> cases = Arrays.asList(
                new ReplaceCase(null, null, null, null),
                new ReplaceCase(null, "", "", null),
                new ReplaceCase("", null, "", null),
                new ReplaceCase("", "", null, null),
                new ReplaceCase("string", "", "", "string"),
                new ReplaceCase("thisisastring", "", "", "thisisastring"),
                new ReplaceCase("thisisastring", "is", "aa", "thaaaaastring"),
                new ReplaceCase("thisisastring", "is", "", "thastring"),
                new ReplaceCase("thisisastring", "is", "thisisaprettylargestring", "ththisisaprettylargestringthisisaprettylargestringastring"),
                new ReplaceCase("thisisastring", "notfound", "thisisaprettylargestring", "thisisastring")
        );

        MyString myStringMocked = Mockito.mock(MyString.class);

        // Handle the only time a match is found
        Mockito.when(myStringMocked.indexOfString("thisisastring", "this", 0))
                .thenReturn(0);
        // Handle other values of pos
        Mockito.when(myStringMocked.indexOfString(eq("thisisastring"), eq("this"), not(eq(0))))
                .thenReturn(-1);
        assertEquals("thatisastring", myString.replace("thisisastring", "this", "that", myStringMocked));

        // Handle both cases where a match is found
        Mockito.when(myStringMocked.indexOfString(eq("thisisastring"), eq("is"), leq(2)))
                .thenReturn(2);
        Mockito.when(myStringMocked.indexOfString(eq("thisisastring"), eq("is"), and(geq(3), leq(4))))
                .thenReturn(4);
        // Handle other values of pos
        Mockito.when(myStringMocked.indexOfString(eq("thisisastring"), eq("is"), geq(5)))
                .thenReturn(-1);
        assertEquals("thaaaaastring", myString.replace("thisisastring", "is", "aa", myStringMocked));
    }

    /*

      indexOfString Property-based testing

     */

    @Property
    public void indexOfStringReturnIndexWithinLength(@ForAll String s, @ForAll String s1) {
        MyString myStringProperty = new MyString(); // Property tests cannot access objects in BeforeClass for some reason
        int index = myStringProperty.indexOfString(s, s1, 0);
        if (index != -1) {
            assertTrue(index >= 0 && index < s.length());
        }
    }

    @Property
    public void indexOfStringCorrectlyFound(@ForAll String s, @ForAll String s1) {
        MyString myStringProperty = new MyString(); // Property tests cannot access objects in BeforeClass for some reason
        int index = myStringProperty.indexOfString(s, s1, 0);
        if (index != -1) {
            boolean match = true;
            int s1Index = 0;
            for (int i = index; i < index + s1.length(); i++, s1Index++) {
                if (s.charAt(i) != s1.charAt(s1Index)) {
                    match = false;
                    break;
                }
            }
            assertTrue(match);
        }
    }

    @Property
    public void indexOfStringNotFound(@ForAll String s, @ForAll String s1) {
        MyString myStringProperty = new MyString(); // Property tests cannot access objects in BeforeClass for some reason
        int index = myStringProperty.indexOfString(s, s1, 0);
        if (!s.contains(s1)) {
            assertEquals(-1, index);
        }
    }

    /*

        replace Property-based testing

     */

    @Property
    void testReplaceReplacesAllOcurrences(@ForAll String s, @ForAll String s1, @ForAll String s2) {
        assumeFalse(s1.equals(s2)); // Make sure s2 is not the same as s1
        assumeFalse(s1.isEmpty()); // Make sure s1 is not empty

        MyString myStringProperty = new MyString(); // Property tests cannot access objects in BeforeClass for some reason
        // Some random strings were being regarded as regex by the replaceAll function
        String sanitizedS1 = Pattern.compile(".*\\Q" + s1 + "\\E.*").pattern();
        assertEquals(s.replaceAll(sanitizedS1, s2), myStringProperty.replace(s, s1, s2, myStringProperty));
    }


}
package me.khalid;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
            new ReplaceCase("", "", null, null)
        );
        cases.forEach(testCase -> assertEquals(testCase.expected, myString.replace(testCase.s, testCase.s1, testCase.s2)));
    }

}
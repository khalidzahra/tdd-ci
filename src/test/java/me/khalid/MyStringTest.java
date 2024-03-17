package me.khalid;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyStringTest {

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
                new IndexOfStringCase("aabb", "ab", -1, -1)
        );
        cases.forEach(testCase -> assertEquals(testCase.expected, myString.indexOfString(testCase.s1, testCase.s2, testCase.pos)));
    }

}
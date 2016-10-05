package kumagai.Fukkatsu2.logictest;

import java.util.*;
import junit.framework.*;
import kumagai.Fukkatsu2.logic.WordPatternGenerator2;

public class WordPatternGenerator2Test
	extends TestCase
{
	public void test0()
	{
		String [] splitted = "aaa|bbb".split("\\|");
		assertEquals(2, splitted.length);
		assertEquals("aaa", splitted[0]);
		assertEquals("bbb", splitted[1]);
	}

	public void test1()
	{
		ArrayList<String> list = new ArrayList<String>();

		list.add("aaa");
		list.add("bbb|ccc");
		list.add("111|222");

		ArrayList<String> phrases = new WordPatternGenerator2(list);

		assertEquals(4, phrases.size());
		assertEquals("aaabbb111", phrases.get(0));
		assertEquals("aaabbb222", phrases.get(1));
		assertEquals("aaaccc111", phrases.get(2));
		assertEquals("aaaccc222", phrases.get(3));
	}
}

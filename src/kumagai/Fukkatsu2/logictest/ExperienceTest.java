package kumagai.Fukkatsu2.logictest;

import junit.framework.*;
import kumagai.Fukkatsu2.logic.ローレシアの王子の経験値;

public class ExperienceTest
	extends TestCase
{
	public void test01()
	{
		assertEquals(
			1,
			new ローレシアの王子の経験値().getLevel(0));
	}

	public void test02()
	{
		assertEquals(
			1,
			new ローレシアの王子の経験値().getLevel(1));
	}

	public void test03()
	{
		assertEquals(
			1,
			new ローレシアの王子の経験値().getLevel(11));
	}

	public void test04()
	{
		assertEquals(
			2,
			new ローレシアの王子の経験値().getLevel(12));
	}

	public void test05()
	{
		assertEquals(
			49,
			new ローレシアの王子の経験値().getLevel(999999));
	}

	public void test06()
	{
		assertEquals(
			50,
			new ローレシアの王子の経験値().getLevel(1000000));
	}

	public void test11()
	{
		assertEquals(
			12,
			new ローレシアの王子の経験値().getPointForNextLevel(0));
	}

	public void test12()
	{
		assertEquals(
			20,
			new ローレシアの王子の経験値().getPointForNextLevel(12));
	}

	public void test13()
	{
		assertEquals(
			1,
			new ローレシアの王子の経験値().getPointForNextLevel(999999));
	}

	public void test14()
	{
		assertEquals(
			0,
			new ローレシアの王子の経験値().getPointForNextLevel(1000000));
	}
}

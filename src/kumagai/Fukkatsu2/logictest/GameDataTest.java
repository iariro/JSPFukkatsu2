package kumagai.Fukkatsu2.logictest;

import junit.framework.*;
import kumagai.Fukkatsu2.logic.GameData;

public class GameDataTest
	extends TestCase
{
	public void test名前1()
	{
		GameData gamedata = new GameData();
		gamedata.setローレシアの王子の名前("あいうえ");

		assertEquals("あいうえ", gamedata.getローレシアの王子の名前());
	}

	public void test名前2()
	{
		GameData gamedata = new GameData();
		gamedata.setローレシアの王子の名前("あいう");

		assertEquals("あいう　", gamedata.getローレシアの王子の名前());
	}

	public void test名前3()
	{
		GameData gamedata = new GameData();
		gamedata.setローレシアの王子の名前("あいうえお");

		assertEquals("あいうえ", gamedata.getローレシアの王子の名前());
	}
}

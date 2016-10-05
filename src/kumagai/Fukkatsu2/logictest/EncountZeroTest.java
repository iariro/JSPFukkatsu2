package kumagai.Fukkatsu2.logictest;

import junit.framework.*;
import kumagai.Fukkatsu2.logic.*;

public class EncountZeroTest
	extends TestCase
{
	public void test1()
		throws IllegalCharacterException
	{
		GameData gamedata = new GameData();

		gamedata.setローレシアの王子の名前("えにくす");

		CompressedGameDataBitArray compressed =
			EncountZero.getEncountZero(gamedata);

		Jumon jumon = new Jumon(compressed.getJumonCode());

		assertEquals(
			"ろごこさぽのぴわぎずぞばぶぼぴぺあう",
			jumon.getJumonStringNoReturn());
	}

	public void test2()
		throws IllegalCharacterException
	{
		GameData gamedata = new GameData();

		gamedata.setローレシアの王子の名前("えにくす");

		Player rooreshia = new Player(0);
		gamedata.playerCollection.add(rooreshia);

		rooreshia.itemCollection.add(new ItemAndEquipment(1));
		rooreshia.itemCollection.add(new ItemAndEquipment(2));

		CompressedGameDataBitArray compressed =
			EncountZero.getEncountZero(gamedata);

		Jumon jumon = new Jumon(compressed.getJumonCode());

		assertEquals(
			"ろごきひたゆへしせつとなぬのひまむうゆ",
			jumon.getJumonStringNoReturn());
	}

	public void test3()
		throws IllegalCharacterException
	{
		GameData gamedata = new GameData();

		gamedata.setローレシアの王子の名前("えにくす");

		Player rooreshia = new Player(0);
		gamedata.playerCollection.add(rooreshia);

		rooreshia.itemCollection.add(new ItemAndEquipment(1));
		rooreshia.itemCollection.add(new ItemAndEquipment(2));

		Player samarutoria = new Player(0);
		gamedata.playerCollection.add(samarutoria);

		samarutoria.itemCollection.add(new ItemAndEquipment(3));

		CompressedGameDataBitArray compressed =
			EncountZero.getEncountZero(gamedata);

		Jumon jumon = new Jumon(compressed.getJumonCode());

		assertEquals(
			"ろごきしあへゆとぞいえおきけさそちじへまむもがぼ",
			jumon.getJumonStringNoReturn());
	}

	public void test4()
		throws IllegalCharacterException
	{
		GameData gamedata = new GameData();

		gamedata.setローレシアの王子の名前("えにくす");

		Player rooreshia = new Player(0);
		Player samarutoria = new Player(0);
		Player muunburuku = new Player(0);

		gamedata.playerCollection.add(rooreshia);
		rooreshia.itemCollection.add(new ItemAndEquipment(1));
		rooreshia.itemCollection.add(new ItemAndEquipment(2));

		gamedata.playerCollection.add(samarutoria);
		samarutoria.itemCollection.add(new ItemAndEquipment(3));

		gamedata.playerCollection.add(muunburuku);
		muunburuku.itemCollection.add(new ItemAndEquipment(4, true));

		CompressedGameDataBitArray compressed =
			EncountZero.getEncountZero(gamedata);

		Jumon jumon = new Jumon(compressed.getJumonCode());

		assertEquals(
			"ろごきせうまぐほぽこしすそちてぬのぼゆらるろぜおきけさたにね",
			jumon.getJumonStringNoReturn());
	}
}

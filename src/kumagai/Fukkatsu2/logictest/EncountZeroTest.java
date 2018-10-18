package kumagai.Fukkatsu2.logictest;

import junit.framework.TestCase;
import kumagai.Fukkatsu2.logic.CompressedGameDataBitArray;
import kumagai.Fukkatsu2.logic.GameData;
import kumagai.Fukkatsu2.logic.IllegalCharacterException;
import kumagai.Fukkatsu2.logic.ItemAndEquipment;
import kumagai.Fukkatsu2.logic.Jumon;
import kumagai.Fukkatsu2.logic.Player;

public class EncountZeroTest
	extends TestCase
{
	public void test1()
		throws IllegalCharacterException
	{
		GameData gamedata = new GameData();

		gamedata.setローレシアの王子の名前("えにくす");

		CompressedGameDataBitArray compressed = gamedata.trickEncountZero();

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

		CompressedGameDataBitArray compressed = gamedata.trickEncountZero();

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

		CompressedGameDataBitArray compressed = gamedata.trickEncountZero();

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

		CompressedGameDataBitArray compressed = gamedata.trickEncountZero();

		Jumon jumon = new Jumon(compressed.getJumonCode());

		assertEquals(
			"ろごきせうまぐほぽこしすそちてぬのぼゆらるろぜおきけさたにね",
			jumon.getJumonStringNoReturn());
	}
}

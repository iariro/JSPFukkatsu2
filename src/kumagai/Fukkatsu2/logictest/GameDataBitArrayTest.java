package kumagai.Fukkatsu2.logictest;

import java.util.*;
import junit.framework.*;
import kumagai.Fukkatsu2.logic.*;

/**
 * JumonBitArray, GameData, ExtendedGameDataBitArrayのテスト。
 */
public class GameDataBitArrayTest
	extends TestCase
{
	public void testConstructorIndexer()
	{
		byte[] byteArray = new byte[] { 0x26 };

		JumonBitArray array = new JumonBitArray(byteArray);

		assertEquals(6, array.size());
		assertEquals(true, array.get(0).booleanValue());
		assertEquals(false, array.get(1).booleanValue());
		assertEquals(false, array.get(2).booleanValue());
		assertEquals(true, array.get(3).booleanValue());
		assertEquals(true, array.get(4).booleanValue());
		assertEquals(false, array.get(5).booleanValue());
	}

	public void testCount()
	{
		JumonBitArray array = new JumonBitArray(6);
		assertEquals(6, array.size());
	}

	public void testGetAsInt()
	{
		JumonBitArray array = new JumonBitArray(new byte[] { 0x26 });
		assertEquals(0x26, array.getAsInt(0, 6));
	}

	public void testGetPart()
	{
		JumonBitArray array = new JumonBitArray(new byte[] { 0x26 });
		JumonBitArray part = array.getPart(1, 3);

		assertEquals(false, part.get(0).booleanValue());
		assertEquals(false, part.get(1).booleanValue());
		assertEquals(true, part.get(2).booleanValue());
	}

	public void testGameDataBitArray1()
		throws IllegalCharacterException, InvalidItemException, InvalidJumonException
	{
		GameData gamedata = new GameData();

		Player ro = new Player(10000);
		Player sa = new Player(20000);
		Player mu = new Player(30000);

		ro.itemCollection.add(new ItemAndEquipment(10));

		gamedata.セーブポイント = 5;
		gamedata.setローレシアの王子の名前("とんぬら");
		gamedata.ゴールド = 12345;
		gamedata.バリエーション = 11;
		gamedata.月のかけら = true;
		gamedata.水門 = true;
		gamedata.水のはごろも = false;
		gamedata.船 = false;
		gamedata.少女 = false;
		gamedata.サマルトリア = 2;
		gamedata.命の紋章 = true;
		gamedata.水の紋章 = true;
		gamedata.月の紋章 = false;
		gamedata.星の紋章 = true;
		gamedata.太陽の紋章 = true;
		gamedata.playerCollection.add(ro);
		gamedata.playerCollection.add(sa);
		gamedata.playerCollection.add(mu);

		ExtendedGameDataBitArray array =
			new ExtendedGameDataBitArray(gamedata);
		ArrayList<Player> playerCollection = array.getPlayerCollection();

		assertEquals(5, array.getセーブポイント());
		assertEquals("とんぬら", array.getローレシアの王子の名前());
		assertEquals(12345, array.getゴールド());
		assertEquals(11, array.getバリエーション());
		assertEquals(true, array.get月のかけら());
		assertEquals(true, array.get水門());
		assertEquals(false, array.get水のはごろも());
		assertEquals(false, array.get船());
		assertEquals(false, array.get少女());
		assertEquals(2, array.getサマルトリア());
		assertEquals(true, array.get命の紋章());
		assertEquals(true, array.get水の紋章());
		assertEquals(false, array.get月の紋章());
		assertEquals(true, array.get星の紋章());
		assertEquals(true, array.get太陽の紋章());
		assertEquals(3, playerCollection.size());
		assertEquals(10000, playerCollection.get(0).経験値);
		assertEquals(1, playerCollection.get(0).itemCollection.size());
		assertEquals(10, playerCollection.get(0).itemCollection.get(0).getCode());
		assertEquals(20000, playerCollection.get(1).経験値);
		assertEquals(0, playerCollection.get(1).itemCollection.size());
		assertEquals(30000, playerCollection.get(2).経験値);
		assertEquals(0, playerCollection.get(2).itemCollection.size());
	}

	public void testGameDataBitArray2()
		throws IllegalCharacterException
	{
		GameData gamedata = new GameData();

		Player ro = new Player(10000);
		Player sa = new Player(20000);
		Player mu = new Player(30000);

		ro.itemCollection.add(new ItemAndEquipment(1));
		ro.itemCollection.add(new ItemAndEquipment(1));
		ro.itemCollection.add(new ItemAndEquipment(1));
		ro.itemCollection.add(new ItemAndEquipment(1));
		ro.itemCollection.add(new ItemAndEquipment(1));
		ro.itemCollection.add(new ItemAndEquipment(1));
		ro.itemCollection.add(new ItemAndEquipment(1));
		ro.itemCollection.add(new ItemAndEquipment(1));

		sa.itemCollection.add(new ItemAndEquipment(1));
		sa.itemCollection.add(new ItemAndEquipment(1));
		sa.itemCollection.add(new ItemAndEquipment(1));
		sa.itemCollection.add(new ItemAndEquipment(1));
		sa.itemCollection.add(new ItemAndEquipment(1));
		sa.itemCollection.add(new ItemAndEquipment(1));
		sa.itemCollection.add(new ItemAndEquipment(1));
		sa.itemCollection.add(new ItemAndEquipment(1));

		mu.itemCollection.add(new ItemAndEquipment(1));
		mu.itemCollection.add(new ItemAndEquipment(1));
		mu.itemCollection.add(new ItemAndEquipment(1));
		mu.itemCollection.add(new ItemAndEquipment(1));
		mu.itemCollection.add(new ItemAndEquipment(1));
		mu.itemCollection.add(new ItemAndEquipment(1));
		mu.itemCollection.add(new ItemAndEquipment(1));
		mu.itemCollection.add(new ItemAndEquipment(1));

		gamedata.setローレシアの王子の名前("とんぬら");
		gamedata.playerCollection.add(ro);
		gamedata.playerCollection.add(sa);
		gamedata.playerCollection.add(mu);

		ExtendedGameDataBitArray array =
			new ExtendedGameDataBitArray(gamedata);

		assertEquals(314, array.size());
	}
}

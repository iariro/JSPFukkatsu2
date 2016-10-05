package kumagai.Fukkatsu2.logictest;

import java.util.*;
import junit.framework.*;
import kumagai.Fukkatsu2.logic.*;

public class JumonTest
	extends TestCase
{
	public void testあいうえお()
		throws IllegalCharacterException
	{
		Jumon jumon = new Jumon("あいうえお");

		assertEquals(0, jumon.get(0));
		assertEquals(1, jumon.get(1));
		assertEquals(2, jumon.get(2));
		assertEquals(3, jumon.get(3));
		assertEquals(4, jumon.get(4));
	}

	public void test不正な文字()
		throws IllegalCharacterException
	{
		try
		{
			new Jumon("漢");
			fail();
		}
		catch (IllegalCharacterException exception)
		{
		}
	}

	public void testぺぺぺ呪文からデータ取得()
		throws InvalidItemException, InvalidJumonException, IllegalCharacterException
	{
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("ゆうて いみや おうきむ");
		stringBuilder.append("こうほ りいゆ うじとり");
		stringBuilder.append("やまあ きらぺ ぺぺぺぺ");
		stringBuilder.append("ぺぺぺ ぺぺぺ ぺぺぺぺ");
		stringBuilder.append("ぺぺぺ ぺぺぺ ぺぺぺぺ ぺぺ");

		Jumon jumon =
			new Jumon(
				stringBuilder.toString());

		CompressedGameDataBitArray compressedGameDataBitArray =
			new CompressedGameDataBitArray(jumon.getPlainArray());
		GameData gamedata =
			new GameData(
				new ExtendedGameDataBitArray(compressedGameDataBitArray));

		List<Player> playerCollection = gamedata.playerCollection;

		assertEquals(1, gamedata.セーブポイント);
		assertEquals("もょもと", gamedata.getローレシアの王子の名前());
		assertEquals(27671, gamedata.ゴールド);
		assertEquals(3, gamedata.バリエーション);
		assertEquals(false, gamedata.月のかけら);
		assertEquals(false, gamedata.水門);
		assertEquals(false, gamedata.水のはごろも);
		assertEquals(false, gamedata.船);
		assertEquals(true, gamedata.少女);
		assertEquals(1, gamedata.サマルトリア);
		assertEquals(true, gamedata.命の紋章);
		assertEquals(true, gamedata.水の紋章);
		assertEquals(false, gamedata.月の紋章);
		assertEquals(false, gamedata.星の紋章);
		assertEquals(true, gamedata.太陽の紋章);
		assertEquals(1, playerCollection.size());
		assertEquals(942197, playerCollection.get(0).経験値);
		assertEquals(0, playerCollection.get(0).itemCollection.size());
		assertEquals(
			compressedGameDataBitArray.getチェックサム１(),
			compressedGameDataBitArray.getチェックサム２());
	}

	public void testはにまる呪文からデータ取得()
		throws IllegalCharacterException, InvalidJumonException, InvalidItemException
	{
		Jumon jumon =
			new Jumon(
				"こゆわ　るめむ　すじぐが\r\n" +
				"れろぱ　むゆほ　らべにぜ\r\n" +
				"ぶぽべ　あきい　きりくす\r\n" +
				"ずふべ　そのた　らわぷそ\r\n" +
				"ずびぐ　つらひ　きぴたふ　へ");

		CompressedGameDataBitArray compressedGameDataBitArray =
			new CompressedGameDataBitArray(jumon.getPlainArray());
		ExtendedGameDataBitArray gamedataBitArray =
			new ExtendedGameDataBitArray(compressedGameDataBitArray);

		GameData gamedata = new GameData(gamedataBitArray);

		List<Player> playerCollection = gamedata.playerCollection;

		assertEquals(5, gamedata.セーブポイント);
		assertEquals("はにまる", gamedata.getローレシアの王子の名前());
		assertEquals(48362, gamedata.ゴールド); // ?
		assertEquals(14, gamedata.バリエーション);
		assertEquals(true, gamedata.月のかけら);
		assertEquals(true, gamedata.水門);
		assertEquals(false, gamedata.水のはごろも);
		assertEquals(true, gamedata.船);
		assertEquals(true, gamedata.少女);
		assertEquals(3, gamedata.サマルトリア);
		assertEquals(true, gamedata.命の紋章);
		assertEquals(true, gamedata.水の紋章);
		assertEquals(true, gamedata.月の紋章);
		assertEquals(true, gamedata.星の紋章);
		assertEquals(true, gamedata.太陽の紋章);
		assertEquals(3, playerCollection.size());

		assertEquals(1000000, playerCollection.get(0).経験値);
		assertEquals(8, playerCollection.get(0).itemCollection.size());
		assertEquals(16, playerCollection.get(0).itemCollection.get(0).getCode());
		assertEquals(64 + 9, playerCollection.get(0).itemCollection.get(1).getCode());
		assertEquals(64 + 27, playerCollection.get(0).itemCollection.get(2).getCode());
		assertEquals(64 + 32, playerCollection.get(0).itemCollection.get(3).getCode());
		assertEquals(64 + 35, playerCollection.get(0).itemCollection.get(4).getCode());
		assertEquals(57, playerCollection.get(0).itemCollection.get(5).getCode());
		assertEquals(12, playerCollection.get(0).itemCollection.get(6).getCode());
		assertEquals(23, playerCollection.get(0).itemCollection.get(7).getCode());

		assertEquals(1000000, playerCollection.get(1).経験値);
		assertEquals(8, playerCollection.get(1).itemCollection.size());
		assertEquals(64 + 9, playerCollection.get(1).itemCollection.get(0).getCode());
		assertEquals(64 + 19, playerCollection.get(1).itemCollection.get(1).getCode());
		assertEquals(64 + 29, playerCollection.get(1).itemCollection.get(2).getCode());
		assertEquals(40, playerCollection.get(1).itemCollection.get(3).getCode());
		assertEquals(39, playerCollection.get(1).itemCollection.get(4).getCode());
		assertEquals(50, playerCollection.get(1).itemCollection.get(5).getCode());
		assertEquals(64 + 33, playerCollection.get(1).itemCollection.get(6).getCode());
		assertEquals(8, playerCollection.get(1).itemCollection.get(7).getCode());

		assertEquals(1000000, playerCollection.get(2).経験値);
		assertEquals(6, playerCollection.get(2).itemCollection.size());
		assertEquals(64 + 4, playerCollection.get(2).itemCollection.get(0).getCode());
		assertEquals(64 + 19, playerCollection.get(2).itemCollection.get(1).getCode());
		assertEquals(29, playerCollection.get(2).itemCollection.get(2).getCode());
		assertEquals(61, playerCollection.get(2).itemCollection.get(3).getCode());
		assertEquals(41, playerCollection.get(2).itemCollection.get(4).getCode());
		assertEquals(11, playerCollection.get(2).itemCollection.get(5).getCode());

		assertEquals(
			compressedGameDataBitArray.getチェックサム１(),
			compressedGameDataBitArray.getチェックサム２());
	}

	public void testはにまるデータから呪文生成()
		throws IllegalCharacterException
	{
		GameData gamedata = new GameData();

		gamedata.セーブポイント = 5;
		gamedata.setローレシアの王子の名前("はにまる");
		gamedata.ゴールド = 48362;
		gamedata.バリエーション = 14;
		gamedata.月のかけら = true;
		gamedata.水門 = true;
		gamedata.水のはごろも = false;
		gamedata.船 = true;
		gamedata.少女 = true;
		gamedata.サマルトリア = 3;
		gamedata.命の紋章 = true;
		gamedata.水の紋章 = true;
		gamedata.月の紋章 = true;
		gamedata.星の紋章 = true;
		gamedata.太陽の紋章 = true;

		Player ro = new Player(1000000);
		ro.itemCollection.add(new ItemAndEquipment(16));
		ro.itemCollection.add(new ItemAndEquipment(64 + 9));
		ro.itemCollection.add(new ItemAndEquipment(64 + 27));
		ro.itemCollection.add(new ItemAndEquipment(64 + 32));
		ro.itemCollection.add(new ItemAndEquipment(64 + 35));
		ro.itemCollection.add(new ItemAndEquipment(57));
		ro.itemCollection.add(new ItemAndEquipment(12));
		ro.itemCollection.add(new ItemAndEquipment(23));
		gamedata.playerCollection.add(ro);

		Player sa = new Player(1000000);
		sa.itemCollection.add(new ItemAndEquipment(64 + 9));
		sa.itemCollection.add(new ItemAndEquipment(64 + 19));
		sa.itemCollection.add(new ItemAndEquipment(64 + 29));
		sa.itemCollection.add(new ItemAndEquipment(40));
		sa.itemCollection.add(new ItemAndEquipment(39));
		sa.itemCollection.add(new ItemAndEquipment(50));
		sa.itemCollection.add(new ItemAndEquipment(64 + 33));
		sa.itemCollection.add(new ItemAndEquipment(8));
		gamedata.playerCollection.add(sa);

		Player mu = new Player(1000000);
		mu.itemCollection.add(new ItemAndEquipment(64 + 4));
		mu.itemCollection.add(new ItemAndEquipment(64 + 19));
		mu.itemCollection.add(new ItemAndEquipment(29));
		mu.itemCollection.add(new ItemAndEquipment(61));
		mu.itemCollection.add(new ItemAndEquipment(41));
		mu.itemCollection.add(new ItemAndEquipment(11));
		gamedata.playerCollection.add(mu);

		ExtendedGameDataBitArray extendedGameDataBitArray =
			new ExtendedGameDataBitArray(gamedata);
		CompressedGameDataBitArray compressedGameDataBitArray =
			new CompressedGameDataBitArray(extendedGameDataBitArray);
		Jumon jumon =
			new Jumon(compressedGameDataBitArray.getJumonCode());

		assertEquals(
			"こゆわ るめむ すじぐが\r\n" +
			"れろぱ むゆほ らべにぜ\r\n" +
			"ぶぽべ あきい きりくす\r\n" +
			"ずふべ そのた らわぷそ\r\n" +
			"ずびぐ つらひ きぴたふ へ",
			jumon.getJumonStringOnly());
	}

	public void testとんぬら呪文からデータ取得()
		throws IllegalCharacterException, InvalidItemException, InvalidJumonException
	{
		Jumon jumon =
			new Jumon("ぬもじ ばざか すごぜぶ ぴねふ みやり わげ");

		CompressedGameDataBitArray compressedGameDataBitArray =
			new CompressedGameDataBitArray(jumon.getPlainArray());
		GameData gamedata =
			new GameData(
				new ExtendedGameDataBitArray(compressedGameDataBitArray));

		List<Player> playerCollection = gamedata.playerCollection;

		assertEquals(0, gamedata.セーブポイント);
		assertEquals("とんぬら", gamedata.getローレシアの王子の名前());
		assertEquals(0, gamedata.ゴールド);
		assertEquals(0, gamedata.バリエーション);
		assertEquals(false, gamedata.月のかけら);
		assertEquals(false, gamedata.水門);
		assertEquals(false, gamedata.水のはごろも);
		assertEquals(false, gamedata.船);
		assertEquals(false, gamedata.少女);
		assertEquals(0, gamedata.サマルトリア);
		assertEquals(false, gamedata.命の紋章);
		assertEquals(false, gamedata.水の紋章);
		assertEquals(false, gamedata.月の紋章);
		assertEquals(false, gamedata.星の紋章);
		assertEquals(false, gamedata.太陽の紋章);
		assertEquals(1, playerCollection.size());
		assertEquals(
			compressedGameDataBitArray.getチェックサム１(),
			compressedGameDataBitArray.getチェックサム２());
	}

	public void testとんぬらデータから呪文生成()
		throws IllegalCharacterException
	{
		GameData gamedata = new GameData();

		gamedata.セーブポイント = 0;
		gamedata.setローレシアの王子の名前("とんぬら");
		gamedata.ゴールド = 0;
		gamedata.バリエーション = 0;
		gamedata.月のかけら = false;
		gamedata.水門 = false;
		gamedata.水のはごろも = false;
		gamedata.船 = false;
		gamedata.少女 = false;
		gamedata.サマルトリア = 0;
		gamedata.命の紋章 = false;
		gamedata.水の紋章 = false;
		gamedata.月の紋章 = false;
		gamedata.星の紋章 = false;
		gamedata.太陽の紋章 = false;
		gamedata.playerCollection.add(new Player(0));

		ExtendedGameDataBitArray extendedGameDataBitArray =
			new ExtendedGameDataBitArray(gamedata);
		CompressedGameDataBitArray compressedGameDataBitArray =
			new CompressedGameDataBitArray(extendedGameDataBitArray);
		Jumon jumon =
			new Jumon(compressedGameDataBitArray.getJumonCode());

		assertEquals(
			"ぬもじ ばざか すごぜぶ\r\nぴねふ みやり わげ",
			jumon.getJumonStringOnly());
	}

	public void testろろのあ呪文からデータ取得()
		throws IllegalCharacterException, InvalidItemException, InvalidJumonException
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("いひさ さぶけ かひぼぴ");
		stringBuilder.append("べぴせ じばぐ とけとざ");
		stringBuilder.append("なする ぬしに ぬにみが");
		stringBuilder.append("ためろ たらざ たまあこ");
		stringBuilder.append("つえの うびじ ちむやし むめ");
		Jumon jumon =
			new Jumon(
				stringBuilder.toString());

		CompressedGameDataBitArray compressedGameDataBitArray =
			new CompressedGameDataBitArray(jumon.getPlainArray());
		GameData gamedata =
			new GameData(
				new ExtendedGameDataBitArray(compressedGameDataBitArray));

		List<Player> playerCollection = gamedata.playerCollection;

		assertEquals("ろろのあ", gamedata.getローレシアの王子の名前());
		assertEquals(65535, gamedata.ゴールド);
		assertEquals(
			compressedGameDataBitArray.getチェックサム１(),
			compressedGameDataBitArray.getチェックサム２());
		assertEquals(1000000, playerCollection.get(0).経験値);
	}

	public void testとんぬら()
		throws IllegalCharacterException, InvalidItemException, InvalidJumonException
	{
		Jumon jumon =
			new Jumon(
				"えほが　つさみ　けろりむ\r\n" +
				"まぞず　ざなま　ねざぽへ\r\n" +
				"かるぼ　れにぜ　がつへよ\r\n" +
				"りひべ　ぬいぴ　しまぱわ\r\n" +
				"られざ　わぺく　しぎにさ　す");

		CompressedGameDataBitArray compressedGameDataBitArray =
			new CompressedGameDataBitArray(jumon.getPlainArray());
		GameData gamedata =
			new GameData(
				new ExtendedGameDataBitArray(compressedGameDataBitArray));

		assertEquals("とんぬら", gamedata.getローレシアの王子の名前());
		assertEquals(
			compressedGameDataBitArray.getチェックサム１(),
			compressedGameDataBitArray.getチェックサム２());
	}

	public void test福引券呪文からデータ取得()
		throws IllegalCharacterException, InvalidJumonException, InvalidItemException
	{
		Jumon jumon =
			new Jumon(
				"わひは てぷゆ ぷぴほら" +
				"りるぼ むよみ ぼるむぷ" +
				"すぜれ おじぐ ぜぬびぴ" +
				"しずる えざれ きにぺち" +
				"ばぱぞ ちぺば ともさぽ ひけ");

		CompressedGameDataBitArray compressedGameDataBitArray =
			new CompressedGameDataBitArray(jumon.getPlainArray());
		ExtendedGameDataBitArray gamedataBitArray =
			new ExtendedGameDataBitArray(compressedGameDataBitArray);

		//GameData gamedata = new GameData(gamedataBitArray);

		//List<Player> playerCollection = gamedata.PlayerCollection;

		assertEquals(314, gamedataBitArray.size());
		assertEquals(
			compressedGameDataBitArray.getチェックサム１(),
			compressedGameDataBitArray.getチェックサム２());

		assertEquals(
			"わひは てぷゆ ぷぴほら\r\n"+
			"りるぼ むよみ ぼるむぷ\r\n"+
			"すぜれ おじぐ ぜぬびぴ\r\n"+
			"しずる えざれ きにぺち\r\n"+
			"ばぱぞ ちぺば ともさぽ ひけ",
			new Jumon(
				new CompressedGameDataBitArray(
					gamedataBitArray).getJumonCode()).getJumonStringOnly());
	}

	public void testこはやし１呪文からデータ取得()
		throws IllegalCharacterException, InvalidItemException, InvalidJumonException
	{
		Jumon jumon =
			new Jumon(
				"すひぞ ぶたう とあやが" +
				"げくさ せがば やねいり" +
				"ぐぷは ほぞれ ぱれぎば" +
				"ぽした とずぷ ゆびうれ" +
				"ぽぽぽ");

		CompressedGameDataBitArray compressed =
			new CompressedGameDataBitArray(jumon.getPlainArray());

		ExtendedGameDataBitArray extended =
			new ExtendedGameDataBitArray(compressed);

		assertEquals("こはやし", extended.getローレシアの王子の名前());
		assertEquals(3, extended.getゴールド());
	}

	public void testこはやし２呪文からデータ取得()
		throws IllegalCharacterException, InvalidItemException, InvalidJumonException
	{
		Jumon jumon =
			new Jumon(
				"ぐぷぱ ほぞが てあゆが" +
				"ごほは めぞぱ れまうに" +
				"すてぎ ごぐぼ わのぱお" +
				"くかけ おりの なけじの");

		GameData gamedata =
			new GameData(
				new ExtendedGameDataBitArray(
					new CompressedGameDataBitArray(
						jumon.getPlainArray())));

		List<Player> playerCollection = gamedata.playerCollection;

		assertEquals("こはよし", gamedata.getローレシアの王子の名前());
		assertEquals(1, playerCollection.size());
		assertEquals(57412, playerCollection.get(0).経験値);
	}
}

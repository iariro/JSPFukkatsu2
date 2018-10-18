package kumagai.Fukkatsu2.struts2;

import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import kumagai.Fukkatsu2.logic.CompressedGameDataBitArray;
import kumagai.Fukkatsu2.logic.EncountZero;
import kumagai.Fukkatsu2.logic.ExtendedGameDataBitArray;
import kumagai.Fukkatsu2.logic.GameData;
import kumagai.Fukkatsu2.logic.GameDataChecker;
import kumagai.Fukkatsu2.logic.ItemAndEquipment;
import kumagai.Fukkatsu2.logic.Jumon;
import kumagai.Fukkatsu2.logic.Player;
import kumagai.Fukkatsu2.logic.SamarutoriaMuunburukuName;
import kumagai.Fukkatsu2.logic.サマルトリアの王子の経験値;
import kumagai.Fukkatsu2.logic.ムーンブルクの王女の経験値;
import kumagai.Fukkatsu2.logic.ローレシアの王子の経験値;

/**
 * 復活の呪文生成アクション。
 * @author kumagai
 */
@Namespace("/fukkatsu2")
@Result(name="success", location="/fukkatsu2/generatejumon2.jsp")
public class GenerateJumon2Action
{
	public int savepoint;
	public String gold;
	public String variation;
	public String tsukinokakera;
	public String suimon;
	public String hagoromo;
	public String ship;
	public String girl;
	public String monshouInochi;
	public String monshouMizu;
	public String monshouTsuki;
	public String monshouHoshi;
	public String monshouTaiyou;
	public String samarutoriaFlag;

	public String rooreshiaName;
	public String rooreshiaExperience;
	public String rooreshiaLevel;
	public int [] rooreshiaItem = new int [8];

	public String samarutoriaJoin;
	public String samarutoriaName;
	public String samarutoriaExperience;
	public String samarutoriaLevel;
	public int [] samarutoriaItem = new int [8];

	public String muunburukuJoin;
	public String muunburukuName;
	public String muunburukuExperience;
	public String muunburukuLevel;
	public int [] muunburukuItem = new int [8];

	public String [] jumonString;
	public String hexdata;
	public int jumonStyle;
	public String encountZero;

	public String [] errors;
	public int errorsLength;

	/**
	 * ローレシアの王子のアイテム１を割り当て。
	 * @param item ローレシアの王子のアイテム１
	 */
	public void setRooreshiaItem1(int item)
	{
		this.rooreshiaItem[0] = item;
	}

	/**
	 * ローレシアの王子のアイテム２を割り当て。
	 * @param item ローレシアの王子のアイテム２
	 */
	public void setRooreshiaItem2(int item)
	{
		this.rooreshiaItem[1] = item;
	}

	/**
	 * ローレシアの王子のアイテム３を割り当て。
	 * @param item ローレシアの王子のアイテム３
	 */
	public void setRooreshiaItem3(int item)
	{
		this.rooreshiaItem[2] = item;
	}

	/**
	 * ローレシアの王子のアイテム４を割り当て。
	 * @param item ローレシアの王子のアイテム４
	 */
	public void setRooreshiaItem4(int item)
	{
		this.rooreshiaItem[3] = item;
	}

	/**
	 * ローレシアの王子のアイテム５を割り当て。
	 * @param item ローレシアの王子のアイテム５
	 */
	public void setRooreshiaItem5(int item)
	{
		this.rooreshiaItem[4] = item;
	}

	/**
	 * ローレシアの王子のアイテム６を割り当て。
	 * @param item ローレシアの王子のアイテム６
	 */
	public void setRooreshiaItem6(int item)
	{
		this.rooreshiaItem[5] = item;
	}

	/**
	 * ローレシアの王子のアイテム７を割り当て。
	 * @param item ローレシアの王子のアイテム７
	 */
	public void setRooreshiaItem7(int item)
	{
		this.rooreshiaItem[6] = item;
	}

	/**
	 * ローレシアの王子のアイテム８を割り当て。
	 * @param item ローレシアの王子のアイテム８
	 */
	public void setRooreshiaItem8(int item)
	{
		this.rooreshiaItem[7] = item;
	}

	/**
	 * サマルトリアの王子のアイテム１を割り当て。
	 * @param item サマルトリアの王子のアイテム１
	 */
	public void setSamarutoriaItem1(int item)
	{
		this.samarutoriaItem[0] = item;
	}

	/**
	 * サマルトリアの王子のアイテム２を割り当て。
	 * @param item サマルトリアの王子のアイテム２
	 */
	public void setSamarutoriaItem2(int item)
	{
		this.samarutoriaItem[1] = item;
	}

	/**
	 * サマルトリアの王子のアイテム３を割り当て。
	 * @param item サマルトリアの王子のアイテム３
	 */
	public void setSamarutoriaItem3(int item)
	{
		this.samarutoriaItem[2] = item;
	}

	/**
	 * サマルトリアの王子のアイテム４を割り当て。
	 * @param item サマルトリアの王子のアイテム４
	 */
	public void setSamarutoriaItem4(int item)
	{
		this.samarutoriaItem[3] = item;
	}

	/**
	 * サマルトリアの王子のアイテム５を割り当て。
	 * @param item サマルトリアの王子のアイテム５
	 */
	public void setSamarutoriaItem5(int item)
	{
		this.samarutoriaItem[4] = item;
	}

	/**
	 * サマルトリアの王子のアイテム６を割り当て。
	 * @param item サマルトリアの王子のアイテム６
	 */
	public void setSamarutoriaItem6(int item)
	{
		this.samarutoriaItem[5] = item;
	}

	/**
	 * サマルトリアの王子のアイテム７を割り当て。
	 * @param item サマルトリアの王子のアイテム７
	 */
	public void setSamarutoriaItem7(int item)
	{
		this.samarutoriaItem[6] = item;
	}

	/**
	 * サマルトリアの王子のアイテム８を割り当て。
	 * @param item サマルトリアの王子のアイテム８
	 */
	public void setSamarutoriaItem8(int item)
	{
		this.samarutoriaItem[7] = item;
	}

	/**
	 * ムーンブルクの王女のアイテム１を割り当て。
	 * @param item ムーンブルクの王女のアイテム１
	 */
	public void setMuunburukuItem1(int item)
	{
		this.muunburukuItem[0] = item;
	}

	/**
	 * ムーンブルクの王女のアイテム２を割り当て。
	 * @param item ムーンブルクの王女のアイテム２
	 */
	public void setMuunburukuItem2(int item)
	{
		this.muunburukuItem[1] = item;
	}

	/**
	 * ムーンブルクの王女のアイテム３を割り当て。
	 * @param item ムーンブルクの王女のアイテム３
	 */
	public void setMuunburukuItem3(int item)
	{
		this.muunburukuItem[2] = item;
	}

	/**
	 * ムーンブルクの王女のアイテム４を割り当て。
	 * @param item ムーンブルクの王女のアイテム４
	 */
	public void setMuunburukuItem4(int item)
	{
		this.muunburukuItem[3] = item;
	}

	/**
	 * ムーンブルクの王女のアイテム５を割り当て。
	 * @param item ムーンブルクの王女のアイテム５
	 */
	public void setMuunburukuItem5(int item)
	{
		this.muunburukuItem[4] = item;
	}

	/**
	 * ムーンブルクの王女のアイテム６を割り当て。
	 * @param item ムーンブルクの王女のアイテム６
	 */
	public void setMuunburukuItem6(int item)
	{
		this.muunburukuItem[5] = item;
	}

	/**
	 * ムーンブルクの王女のアイテム７を割り当て。
	 * @param item ムーンブルクの王女のアイテム７
	 */
	public void setMuunburukuItem7(int item)
	{
		this.muunburukuItem[6] = item;
	}

	/**
	 * ムーンブルクの王女のアイテム８を割り当て。
	 * @param item ムーンブルクの王女のアイテム８
	 */
	public void setMuunburukuItem8(int item)
	{
		this.muunburukuItem[7] = item;
	}

	/**
	 * 復活の呪文生成アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("generatejumon2")
	public String execute()
		throws Exception
	{
		GameData gameData = new GameData();

		ArrayList<String> errors = new ArrayList<String>();

		for (GenerateJumonFormChecker checker :
			GenerateJumonFormChecker.checkers)
		{
			String error = checker.checkAndCorrect(this);

			if (error != null)
			{
				// エラーあり。

				errors.add(error);
			}
		}

		gameData.setローレシアの王子の名前(rooreshiaName);
		gameData.セーブポイント = savepoint;
		gameData.ゴールド = Integer.valueOf(gold);
		gameData.バリエーション = Integer.valueOf(variation);

		gameData.月のかけら = tsukinokakera != null;
		gameData.水門 = suimon != null;
		gameData.水のはごろも = hagoromo != null;
		gameData.船 = ship != null;
		gameData.少女 = girl != null;
		gameData.命の紋章 = monshouInochi != null;
		gameData.水の紋章 = monshouMizu != null;
		gameData.月の紋章 = monshouTsuki != null;
		gameData.星の紋章 = monshouHoshi != null;
		gameData.太陽の紋章 = monshouTaiyou != null;
		gameData.サマルトリア = Integer.valueOf(samarutoriaFlag);

		Player player = new Player(Integer.valueOf(rooreshiaExperience));

		boolean blank = false;
		boolean skipItem = false;

		for (int i=0 ; i<rooreshiaItem.length ; i++)
		{
			if (rooreshiaItem[i] >= 1)
			{
				// アイテム指定あり。

				player.itemCollection.add(
					new ItemAndEquipment(rooreshiaItem[i]));

				if (blank)
				{
					// すでにアイテム指定なしがあった。

					skipItem = true;
				}
			}
			else
			{
				// アイテム指定なし。

				blank = true;
			}
		}

		gameData.playerCollection.add(player);
		blank = false;

		if (samarutoriaJoin != null)
		{
			// サマルトリアの王子は加わっている。

			player = new Player(Integer.valueOf(samarutoriaExperience));

			for (int i=0 ; i<samarutoriaItem.length ; i++)
			{
				if (samarutoriaItem[i] >= 1)
				{
					// アイテム指定あり。

					player.itemCollection.add(
						new ItemAndEquipment(samarutoriaItem[i]));

					if (blank)
					{
						// すでにアイテム指定なしがあった。

						skipItem = true;
					}
				}
				else
				{
					// アイテム指定なし。

					blank = true;
				}
			}

			gameData.playerCollection.add(player);
			blank = false;

			if (muunburukuJoin != null)
			{
				// ムーンブルクの王女は加わっている。

				player = new Player(Integer.valueOf(muunburukuExperience));

				for (int i=0 ; i<muunburukuItem.length ; i++)
				{
					if (muunburukuItem[i] >= 1)
					{
						// アイテム指定あり。

						player.itemCollection.add(
							new ItemAndEquipment(muunburukuItem[i]));

						if (blank)
						{
							// すでにアイテム指定なしがあった。

							skipItem = true;
						}
					}
					else
					{
						// アイテム指定なし。

						blank = true;
					}
				}

				gameData.playerCollection.add(player);
			}
		}

		boolean invalidItem = false;

		if (samarutoriaJoin == null)
		{
			// サマルトリアの王子は加わっていない。

			for (int i=0 ; i<samarutoriaItem.length ; i++)
			{
				if (samarutoriaItem[i] >= 1)
				{
					// アイテム指定あり。

					invalidItem = true;
				}
			}
		}

		if (muunburukuJoin == null)
		{
			// ムーンブルクの王女は加わっている。

			for (int i=0 ; i<muunburukuItem.length ; i++)
			{
				if (muunburukuItem[i] >= 1)
				{
					// アイテム指定あり。

					invalidItem = true;
				}
			}
		}

		if (invalidItem)
		{
			// 仲間に加わっていないキャラにアイテムが指定された。

			errors.add("仲間に加わっていないキャラにアイテムが指定されています。無視します。");
		}

		if (skipItem)
		{
			// アイテムを飛び飛びに指定した。

			errors.add("アイテムが飛び飛びに指定されています。詰めて格納しました。");
		}

		for (GameDataChecker gameDataChecker : GameDataChecker.checkers)
		{
			String [] error2 = gameDataChecker.checkAndCorrect(gameData);

			if (error2 != null)
			{
				// エラーあり。

				for (String error3 : error2)
				{
					errors.add(error3);
				}
			}
		}

		CompressedGameDataBitArray compressedGameDataBitArray = null;

		if ((encountZero != null) && (encountZero.length() > 0))
		{
			// エンカウントゼロ加工指定あり

			compressedGameDataBitArray = EncountZero.getEncountZero(gameData);
			if (compressedGameDataBitArray == null)
			{
				// エンカウントゼロ加工失敗

				errors.add("エンカウントゼロ加工失敗");
			}
		}

		if (compressedGameDataBitArray == null)
		{
			// エンカウントゼロ指定なしまたは失敗。普通はこっち。

			ExtendedGameDataBitArray extendedGameDataBitArray =
				new ExtendedGameDataBitArray(gameData);
			compressedGameDataBitArray =
				new CompressedGameDataBitArray(extendedGameDataBitArray);
		}

		Jumon jumon = new Jumon(compressedGameDataBitArray.getJumonCode());

		byte [] plainArray = jumon.getPlainArray();
		hexdata = DatatypeConverter.printHexBinary(plainArray);

		String jumonString;

		switch (Integer.valueOf(jumonStyle))
		{
			case 0:
				jumonString = jumon.getJumonStringOnly();
				break;

			case 1:
				jumonString = jumon.getJumonAndSerifuString(gameData, 0);
				break;

			case 2:
				jumonString = jumon.getJumonAndSerifuString(gameData, 1);
				break;

			case 3:
				jumonString = jumon.getJumonAndSerifuString(gameData, 2);
				break;

			case 4:
				jumonString = jumon.getJumonStringByTama();
				break;

			default:
				throw new Exception();
		}

		this.jumonString = jumonString.split("\n");

		this.errors = (String [])errors.toArray(new String [0]);
		this.errorsLength = this.errors.length;

		int level =
			new ローレシアの王子の経験値().getLevel(gameData.playerCollection.get(0).経験値);

		rooreshiaLevel = Integer.toString(level);

		SamarutoriaMuunburukuName names =
			new SamarutoriaMuunburukuName
				(gameData.getローレシアの王子の名前());

		if (gameData.playerCollection.size() >= 2)
		{
			// サマルトリアの王子はいる。

			level =
				new サマルトリアの王子の経験値().getLevel(gameData.playerCollection.get(1).経験値);

			samarutoriaLevel = Integer.toString(level);

			samarutoriaName = names.サマルトリアの王子の名前;
		}

		if (gameData.playerCollection.size() >= 3)
		{
			// ムーンブルクの王女はいる。

			level =
				new ムーンブルクの王女の経験値().getLevel(gameData.playerCollection.get(2).経験値);

			muunburukuLevel = Integer.toString(level);

			muunburukuName = names.ムーンブルクの王女の名前;
		}

		return "success";
	}
}

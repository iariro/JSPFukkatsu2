package kumagai.Fukkatsu2.logic;

import java.util.*;

/**
 * ゲームデータ。
 */
public class GameData
{
	public int セーブポイント;
	public String ローレシアの王子の名前_;
	public int ゴールド;
	public int バリエーション;
	public boolean 月のかけら;
	public boolean 水門;
	public boolean 水のはごろも;
	public boolean 船;
	public boolean 少女;
	public int サマルトリア;
	public boolean 命の紋章;
	public boolean 水の紋章;
	public boolean 月の紋章;
	public boolean 星の紋章;
	public boolean 太陽の紋章;
	public ArrayList<Player> playerCollection;

	/**
	 * ローレシアの王子の名前を取得。
	 * @return ローレシアの王子の名前
	 */
	public String getローレシアの王子の名前()
	{
		return ローレシアの王子の名前_;
	}

	/**
	 * ローレシアの王子の名前を設定。
	 * @param value ローレシアの王子の名前
	 */
	public void setローレシアの王子の名前(String value)
	{
		if (value.length() > 4)
		{
			// ４文字より長い。

			ローレシアの王子の名前_ =
				value.substring(0, 4);
		}
		else if (value.length() < 4)
		{
			// ４文字より短い。

			ローレシアの王子の名前_ = value;

			for (int i=0 ; i<4 - value.length() ; i++)
			{
				ローレシアの王子の名前_ += '　';
			}
		}
		else
		{
			// ４文字。

			ローレシアの王子の名前_ = value;
		}
	}

	/**
	 * データの正当性チェック。
	 * @return エラーメッセージ
	 */
	public String isValid()
	{
		String valid = null;

		for (int i=0 ; i<playerCollection.size() ; i++)
		{
			Player player = playerCollection.get(i);

			Item [] equip = new Item [3];

			for (ItemAndEquipment item : player.itemCollection)
			{
				if (!(item.item.itemKind > 0 &&
					((i == 0 && item.item.ローレシア王子装備可) ||
					 (i == 1 && item.item.サマルトリア王子装備可) ||
					 (i == 2 && item.item.ムーンブルク王女装備可))))
				{
					// 装備できないアイテム。

					if (item.equipment)
					{
						// 装備している。

						System.out.printf(
							"%dが%sを装備しています",
							i,
							item.item.name);

						valid =
							String.format(
								"%dが%sを装備",
								i,
								item.item.name);
					}
				}

				if (item.item.itemKind >= 1 && item.item.itemKind <= 3)
				{
					// 武器・鎧・盾。

					if (equip[item.item.itemKind - 1] == null)
					{
						// 未装備状態。

						equip[item.item.itemKind - 1] = item.item;
					}
					else
					{
						// 装備状態。

						valid =
							String.format(
								"%dが%sに加え%sを装備",
								i,
								equip[item.item.itemKind - 1].name,
								item.item.name);
					}
				}
			}
		}

		return valid;
	}

	/**
	 * 空のデータを構築。
	 */
	public GameData()
	{
		playerCollection = new ArrayList<Player>();
	}

	/**
	 * ビット配列からゲームデータを構築。
	 * @param bitArray ビット配列
	 */
	public GameData(ExtendedGameDataBitArray bitArray)
		throws InvalidItemException, InvalidJumonException
	{
		this.セーブポイント = bitArray.getセーブポイント();
		this.setローレシアの王子の名前(bitArray.getローレシアの王子の名前());
		this.ゴールド = bitArray.getゴールド();
		this.バリエーション = bitArray.getバリエーション();
		this.月のかけら = bitArray.get月のかけら();
		this.水門 = bitArray.get水門();
		this.水のはごろも = bitArray.get水のはごろも();
		this.船 = bitArray.get船();
		this.少女 = bitArray.get少女();
		this.サマルトリア = bitArray.getサマルトリア();
		this.命の紋章 = bitArray.get命の紋章();
		this.水の紋章 = bitArray.get水の紋章();
		this.月の紋章 = bitArray.get月の紋章();
		this.星の紋章 = bitArray.get星の紋章();
		this.太陽の紋章 = bitArray.get太陽の紋章();
		this.playerCollection = bitArray.getPlayerCollection();
	}
}

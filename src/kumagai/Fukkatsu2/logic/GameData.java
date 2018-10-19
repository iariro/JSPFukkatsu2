package kumagai.Fukkatsu2.logic;

import java.util.ArrayList;

/**
 * ゲームデータ。
 */
public class GameData
{
	/**
	 * エンカウントゼロ呪文生成用にバリエーション値とゴールドを加工。
	 * @return 加工済みデータ
	 */
	public CompressedGameDataBitArray trickEncountZero()
		throws IllegalCharacterException
	{
		int variation = (バリエーション & 0x1) + 10;
		int gold = ゴールド;

		ExtendedGameDataBitArray extended = new ExtendedGameDataBitArray(this);
		CompressedGameDataBitArray compressed = new CompressedGameDataBitArray(extended);

		compressed.set(0, true);
		compressed.set(1, false);
		compressed.set(2, false);
		compressed.set(3, false);
		compressed.set(4, true);

		compressed.setInt(56, 0, 2, 0);
		compressed.setInt(66, 0, 4, 0);

		boolean goldDirection = false;

		int inc = gold;
		int dec = gold - 1;

		for (int count=0; count<100000 ; count++)
		{
			compressed.setInt(32, gold, 7, 0);
			compressed.setInt(16, gold, 15, 8);

			compressed.set(48, (variation & 0x1) > 0);

			int checksum = compressed.getEncountZeroChecksum();

			if ((checksum & 0xfe3f) == 0xac3f)
			{
				// エンカウントゼロ条件に合致した。

				compressed.set( 2, (checksum & 0x400) > 0);
				compressed.set( 3, (checksum & 0x200) > 0);
				compressed.set(56, (checksum & 0x100) > 0);
				compressed.set(57, (checksum & 0x80) > 0);
				compressed.set(58, (checksum & 0x40) > 0);
				compressed.set(66, (checksum & 0x20) > 0);
				compressed.set(67, (checksum & 0x10) > 0);
				compressed.set(68, (checksum & 0x8) > 0);
				compressed.set(69, (checksum & 0x4) > 0);
				compressed.set(70, (checksum & 0x2) > 0);
				compressed.set(71, (checksum & 0x1) > 0);

				return compressed;
			}
			else
			{
				// エンカウントゼロ条件に合致しない。

				variation = 21 - variation;

				if (variation == 11)
				{
					if (goldDirection)
					{
						// 増やす番。

						if (gold < 65535)
						{
							// 範囲内。

							gold = inc;
							inc++;
						}
						goldDirection = false;
					}
					else
					{
						// 減らす番。

						if (gold > 0)
						{
							// 範囲内。

							gold = dec;
							dec--;
						}
						else
						{
							//gold = 65535;
						}
						goldDirection = true;
					}
				}
			}
		}

		return null;
	}

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

			if (!player.exist)
			{
				// 存在しない

				continue;
			}

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

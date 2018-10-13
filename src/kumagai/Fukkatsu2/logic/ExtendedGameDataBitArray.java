package kumagai.Fukkatsu2.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * 最大314ビットのゲームデータビット配列。
 */
public class ExtendedGameDataBitArray
	extends JumonBitArray
{
	/**
	 * ローレシアの王子の名前文字。
	 */
	private static final char [] nameChar =
	{
		'あ', 'い', 'う', 'え', 'お',
		'か', 'き', 'く', 'け', 'こ',
		'さ', 'し', 'す', 'せ', 'そ',
		'た', 'ち', 'つ', 'て', 'と',
		'な', 'に', 'ぬ', 'ね', 'の',
		'は', 'ひ', 'ふ', 'へ', 'ほ',
		'ま', 'み', 'む', 'め', 'も',
		'や', 'ゆ', 'よ', 'ら', 'り',
		'る', 'れ', 'ろ', 'わ', 'を',
		'ん', 'っ', 'ゃ', 'ゅ', 'ょ',
		'゛', '゜', '　'
	};

	/**
	 * 指定の文字のインデックスを取得する。
	 * @param ch 対象文字
	 * @return 指定の文字のインデックス
	 */
	private int getNameIndex(char ch)
	{
		for (int i=0 ; i<nameChar.length ; i++)
		{
			if (nameChar[i] == ch)
			{
				// 対象の文字である。

				return i;
			}
		}

		return -1;
	}

	/**
	 * ゲームデータからビット配列を構築。
	 * @param gamedata ゲームデータ
	 */
	public ExtendedGameDataBitArray(GameData gamedata)
		throws IllegalCharacterException
	{
		// 必要な領域計算。
		int size = 72;

		for (int i=0 ; i<gamedata.playerCollection.size() ; i++)
		{
			size +=
				24 + 7 * gamedata.playerCollection.get(i).itemCollection.size();

			if (i < 2)
			{
				// ２人目まで。

				size++;
			}
		}

		if (size < 104)
		{
			// 104ビットに達していない。

			size = 104;
		}

		// 領域確保。
		for (int i=0 ; i<size ; i++)
		{
			add(false);
		}

		if (size < 314)
		{
			// 314ビット未満。

			// パディング分領域確保。チェックサム計算のために必要。
			for (int i=0 ; i<6 - size % 6 ; i++)
			{
				add(false);
			}
		}

		setセーブポイント(gamedata.セーブポイント);
		setローレシアの王子の名前(gamedata.getローレシアの王子の名前());
		setゴールド(gamedata.ゴールド);
		setバリエーション(gamedata.バリエーション);
		set月のかけら(gamedata.月のかけら);
		set水門(gamedata.水門);
		set水のはごろも(gamedata.水のはごろも);
		set船(gamedata.船);
		set少女(gamedata.少女);
		setサマルトリア(gamedata.サマルトリア);
		set命の紋章(gamedata.命の紋章);
		set水の紋章(gamedata.水の紋章);
		set月の紋章(gamedata.月の紋章);
		set星の紋章(gamedata.星の紋章);
		set太陽の紋章(gamedata.太陽の紋章);
		setPlayerCollection(gamedata.playerCollection);
	}

	/**
	 * 圧縮されたビット配列を展開し、ビット配列を構築。
	 * @param bitArray 圧縮されたビット配列
	 */
	public ExtendedGameDataBitArray(CompressedGameDataBitArray bitArray)
		throws InvalidJumonException
	{
		super(bitArray.size() + (bitArray.size() == 312 ? 2 : 0));

		if (bitArray.size() < 63)
		{
			// ビット数が少ない。

			throw new InvalidJumonException(bitArray.size() + "bitとは少なすぎです");
		}

		for (int i=5 ; i<=63 ; i++)
		{
			set(i, bitArray.get(i));
		}

		for (int i=72 ; i<bitArray.size() ; i++)
		{
			set(i, bitArray.get(i));
		}

		if (size() == 314)
		{
			// オーバーフロー分がある。

			set(312, bitArray.get(64));
			set(313, bitArray.get(65));
		}
	}

	/**
	 * 埋め込まれたチェックサムを取得。
	 * @return チェックサム
	 */
	public int getチェックサム１()
	{
		return getAsInt(0, 5) + (getAsInt(66, 6) << 5);
	}

	/**
	 * セーブポイントを取得。
	 * @return セーブポイント
	 */
	public int getセーブポイント()
	{
		return getAsInt(5, 3);
	}

	/**
	 * セーブポイントを割り当てる。
	 * @param value セーブポイント
	 */
	public void setセーブポイント(int value)
	{
		setInt(5, value, 2, 0);
	}

	/**
	 * ローレシアの王子の名前が正当かを取得。
	 * @return エラー文字列
	 */
	public String isValidローレシアの王子の名前()
	{
		int name1, name2, name3, name4;

		name3 = getAsInt( 8, 6);
		name2 = getAsInt(14, 2) << 4;
		name2 += getAsInt(24, 2) << 1;
		name1 = getAsInt(26, 6);
		name2 += getAsInt(40, 1);
		name4 = getAsInt(41, 6);
		name2 += getAsInt(47, 1) << 3;

		name1 -= 10;
		name2 -= 10;
		name3 -= 10;
		name4 -= 10;

		if (name1 >= 0 &&
			name1 < 53 &&
			name2 >= 0 &&
			name2 < 53 &&
			name3 >= 0 &&
			name3 < 53 &&
			name4 >= 0 &&
			name4 < 53)
		{
			// エラーなし。

			return null;
		}
		else
		{
			// エラーあり。

			return String.format("%d %d %d %d", name1, name2, name3, name4);
		}
	}

	/**
	 * ローレシアの王子の名前を取得。
	 * @return ローレシアの王子の名前
	 */
	public String getローレシアの王子の名前()
	{
		int name1, name2, name3, name4;

		name3 = getAsInt( 8, 6);
		name2 = getAsInt(14, 2) << 4;
		name2 += getAsInt(24, 2) << 1;
		name1 = getAsInt(26, 6);
		name2 += getAsInt(40, 1);
		name4 = getAsInt(41, 6);
		name2 += getAsInt(47, 1) << 3;

		name1 -= 10;
		name2 -= 10;
		name3 -= 10;
		name4 -= 10;

		return
			String.format(
			"%c%c%c%c",
			nameChar[name1],
			nameChar[name2],
			nameChar[name3],
			nameChar[name4]);
	}

	/**
	 * ローレシアの王子の名前を取得。
	 * @param value ローレシアの王子の名前
	 */
	public void setローレシアの王子の名前(String value)
		throws IllegalCharacterException
	{
		int name1 = getNameIndex(value.charAt(0));
		int name2 = getNameIndex(value.charAt(1));
		int name3 = getNameIndex(value.charAt(2));
		int name4 = getNameIndex(value.charAt(3));

		if (name1 < 0)
		{
			// 名前１文字目が不正。

			throw
				new IllegalCharacterException
					("ローレシアの王子の名前", 1, value.charAt(0));
		}

		if (name2 < 0)
		{
			// 名前２文字目が不正。

			throw
				new IllegalCharacterException
					("ローレシアの王子の名前", 2, value.charAt(1));
		}

		if (name3 < 0)
		{
			// 名前３文字目が不正。

			throw
				new IllegalCharacterException
					("ローレシアの王子の名前", 3, value.charAt(2));
		}

		if (name4 < 0)
		{
			// 名前４文字目が不正。

			throw
				new IllegalCharacterException
					("ローレシアの王子の名前", 4, value.charAt(3));
		}

		name1 += 10;
		name2 += 10;
		name3 += 10;
		name4 += 10;

		setInt( 8, name3, 5, 0);
		setInt(14, name2, 5, 4);
		setInt(24, name2, 2, 1);
		setInt(26, name1, 5, 0);
		setInt(40, name2, 0, 0);
		setInt(41, name4, 5, 0);
		setInt(47, name2, 3, 3);
	}

	/**
	 * ゴールドを取得。
	 * @return ゴールド値
	 */
	public int getゴールド()
	{
		return (getAsInt(16, 8) << 8) + getAsInt(32, 8);
	}

	/**
	 * ゴールドを設定。
	 * @param value ゴールド値
	 */
	public void setゴールド(int value)
	{
		setInt(32, value, 7, 0);
		setInt(16, value, 15, 8);
	}

	/**
	 * バリエーションを取得。
	 * @return バリエーション値
	 */
	public int getバリエーション()
	{
		return (getAsInt(48, 1) << 3) + getAsInt(56, 3);
	}

	/**
	 * バリエーションを設定。
	 * @param value バリエーション値
	 */
	public void setバリエーション(int value)
	{
		setInt(48, value, 3, 3);
		setInt(56, value, 2, 0);
	}

	/**
	 * 月のかけら情報を取得または設定。
	 * @return 月のかけらフラグ
	 */
	public boolean get月のかけら()
	{
		return get(49);
	}

	/**
	 * 月のかけら情報を設定。
	 * @param value 月のかけらフラグ
	 */
	public void set月のかけら(boolean value)
	{
		set(49, value);
	}

	/**
	 * 水門情報を取得。
	 * @return 水門フラグ
	 */
	public boolean get水門()
	{
		return get(50);
	}

	/**
	 * 水門情報を設定。
	 * @param value 水門フラグ
	 */
	public void set水門(boolean value)
	{
		set(50, value);
	}

	/**
	 * 水のはごろも情報を取得。
	 * @return 水のはごろもフラグ
	 */
	public boolean get水のはごろも()
	{
		return get(51);
	}

	/**
	 * 水のはごろも情報を設定。
	 * @param value 水のはごろもフラグ
	 */
	public void set水のはごろも(boolean value)
	{
		set(51, value);
	}

	/**
	 * 船情報を取得。
	 * @return 船フラグ
	 */
	public boolean get船()
	{
		return get(52);
	}

	/**
	 * 船情報を設定。
	 * @param value 船フラグ
	 */
	public void set船(boolean value)
	{
		set(52, value);
	}

	/**
	 * 少女情報を取得。
	 * @return 少女フラグ
	 */
	public boolean get少女()
	{
		return get(53);
	}

	/**
	 * 少女情報を設定。
	 * @param value 少女フラグ
	 */
	public void set少女(boolean value)
	{
		set(53, value);
	}

	/**
	 * サマルトリア情報を取得。
	 * @return サマルトリアの王子フラグ
	 */
	public int getサマルトリア()
	{
		return getAsInt(54, 2);
	}

	/**
	 * サマルトリア情報を設定。
	 * @param value サマルトリアの王子フラグ
	 */
	public void setサマルトリア(int value)
	{
		set(54, (value & 0x2) > 0);
		set(55, (value & 0x1) > 0);
	}

	/**
	 * 命の紋章情報を取得。
	 * @return 命の紋章フラグ
	 */
	public boolean get命の紋章()
	{
		return get(59);
	}

	/**
	 * 命の紋章情報を設定。
	 * @param value 命の紋章フラグ
	 */
	public void set命の紋章(boolean value)
	{
		set(59, value);
	}

	/**
	 * 水の紋章情報を取得。
	 * @return 水の紋章フラグ
	 */
	public boolean get水の紋章()
	{
		return get(60);
	}

	/**
	 * 水の紋章情報を設定。
	 * @param value 水の紋章フラグ
	 */
	public void set水の紋章(boolean value)
	{
		set(60, value);
	}

	/**
	 * 月の紋章情報を取得。
	 * @return 月の紋章フラグ
	 */
	public boolean get月の紋章()
	{
		return get(61);
	}

	/**
	 * 月の紋章情報を設定。
	 * @param value 月の紋章フラグ
	 */
	public void set月の紋章(boolean value)
	{
		set(61, value);
	}

	/**
	 * 星の紋章情報を取得。
	 * @return 星の紋章フラグ
	 */
	public boolean get星の紋章()
	{
		return get(62);
	}

	/**
	 * 星の紋章情報を設定。
	 * @param value 星の紋章フラグ
	 */
	public void set星の紋章(boolean value)
	{
		set(62, value);
	}

	/**
	 * 太陽の紋章情報を取得。
	 * @return 太陽の紋章フラグ
	 */
	public boolean get太陽の紋章()
	{
		return get(63);
	}

	/**
	 * 太陽の紋章情報を設定。
	 * @param value 太陽の紋章フラグ
	 */
	public void set太陽の紋章(boolean value)
	{
		set(63, value);
	}

	/**
	 * プレイヤー３人分の情報を取得。
	 * @return プレイヤー３人分の情報
	 */
	public ArrayList<Player> getPlayerCollection()
		throws InvalidItemException, InvalidJumonException
	{
		ArrayList<Player> playerCollection = new ArrayList<Player>();

		int offset = 72;
		int itemCount;
		boolean next = true;

		for (int i=0 ; i<3 ; i++)
		{
			if (offset + 20 + 4 > size())
			{
				// ビット数が足りない。

				if (!next)
				{
					break;
				}
				throw
					new InvalidJumonException(
						"少なくとももう" +
						(((offset + 24) - size() + 5) / 6) +
						"文字はあるはずです");
			}

			itemCount = getAsInt(offset + 20, 4);

			if (offset + 20 + 4 + 7 * itemCount + (i < 2 ? 1 : 0) >
				size())
			{
				// ビット数が足りない。

				if (!next)
				{
					break;
				}
				throw
					new InvalidJumonException(
						String.format(
							"%d人目 - 少なくとももう%d文字はあるはずです",
							i + 1,
							(((offset + 20 + 4 + 7 * itemCount +
								(i < 2 ? 1 : 0)) - size() + 5) / 6)));
			}

			playerCollection.add(
				new Player(
					next,
					getAsInt(offset, 16) +
						(getAsInt(offset + 16, 4) << 16),
					itemCount,
					getPart(offset + 24, 7 * itemCount)));

			offset += 20 + 4 + 7 * itemCount;

			if (i < 2)
			{
				// ２人目まで。

				next &= get(offset);

				offset++;
			}
		}

		return playerCollection;
	}

	/**
	 * 余剰ビット有無判定。
	 * @return true=余剰ビットあり／false=なし
	 */
	public boolean hasExcessBit()
		throws InvalidItemException, InvalidJumonException
	{
		int offset = 72;
		int itemCount;
		boolean next = true;
		boolean find = false;

		for (int i=0 ; i<3 && next ; i++)
		{
			if (offset + 20 + 4 > size())
			{
				// ビット数が足りない。

				throw
					new InvalidJumonException(
						"少なくとももう" +
						(((offset + 24) - size() + 5) / 6) +
						"文字はあるはずです");
			}

			itemCount = getAsInt(offset + 20, 4);

			if (offset + 20 + 4 + 7 * itemCount + (i < 2 ? 1 : 0) >
				size())
			{
				// ビット数が足りない。

				throw
					new InvalidJumonException(
						String.format(
							"%d人目 - 少なくとももう%d文字はあるはずです",
							i + 1,
							(((offset + 20 + 4 + 7 * itemCount +
								(i < 2 ? 1 : 0)) - size() + 5) / 6)));
			}

			offset += 20 + 4 + 7 * itemCount;

			if (i < 2)
			{
				// ２人目まで。

				next = get(offset);

				offset++;

				if (! next && offset + 20 + 4 < size())
				{
					// 次のキャラはいないはずなのにビット数は足りる。

					find = true;
				}
			}
		}

		return find;
	}

	/**
	 * プレイヤー３人分の情報を設定。
	 * @param value プレイヤー３人分の情報
	 */
	public void setPlayerCollection(List<Player> value)
	{
		int offset = 72;

		for (int i=0 ; i<value.size() ; i++)
		{
			setInt(offset, value.get(i).経験値, 15, 0);
			offset += 16;

			setInt(offset, value.get(i).経験値, 19, 16);
			offset += 4;

			setInt(offset, value.get(i).itemCollection.size(), 3, 0);
			offset += 4;

			for (int j=0 ; j<value.get(i).itemCollection.size() ; j++)
			{
				setInt(offset, value.get(i).itemCollection.get(j).getCode(), 6, 0);
				offset += 7;
			}

			if (i < 2)
			{
				// ２人目まで。

				set(offset, i + 1 < value.size());

				offset++;
			}
		}
	}

	/**
	 * 内容は正当かをチェック。
	 * @return true=正当
	 * @throws InvalidJumonException
	 */
	public boolean isValid()
		throws InvalidJumonException
	{
		boolean valid = true;
		int offset = 72;
		int itemCount;
		boolean next = true;

		for (int i=0 ; i<3 && next ; i++)
		{
			if (offset + 20 + 4 > size())
			{
				// ビット数が足りない。

				throw
					new InvalidJumonException(
						"少なくとももう" +
						(((offset + 24) - size() + 5) / 6) +
						"文字はあるはずです");
			}

			itemCount = getAsInt(offset + 20, 4);

			if (offset + 20 + 4 + 7 * itemCount + (i < 2 ? 1 : 0) >
				size())
			{
				// ビット数が足りない。

				throw
					new InvalidJumonException(
						"少なくとももう" +
						(((offset + 20 + 4 + 7 * itemCount +
							(i < 2 ? 1 : 0)) - size() + 5) / 6) +
						"文字はあるはずです");
			}

			JumonBitArray itemBitArray =
				getPart(offset + 24, 7 * itemCount);

			for (int j=0 ; j<itemCount && valid ; j++)
			{
				int itemIndex = itemBitArray.getAsInt(7 * j, 7);

				System.out.println(itemIndex);

				if (itemIndex < 0 || itemIndex >= 63)
				{
					// 範囲外。

					valid = false;
				}
			}

			offset += 20 + 4 + 7 * itemCount;

			if (i < 2)
			{
				// ２人目まで。

				next = get(offset);

				offset++;
			}
		}

		return valid;
	}
}

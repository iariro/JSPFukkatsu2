package kumagai.Fukkatsu2.logic;

/**
 * 呪文。
 */
public class Jumon
{
	/**
	 * 呪文に使用する文字。
	 */
	static public final char [] characterSet =
	{
		'あ', 'い', 'う', 'え', 'お',
		'か', 'き', 'く', 'け', 'こ',
		'さ', 'し', 'す', 'せ', 'そ',
		'た', 'ち', 'つ', 'て', 'と',
		'な', 'に', 'ぬ', 'ね', 'の',
		'は', 'ひ', 'ふ', 'へ', 'ほ',
		'ま', 'み', 'む', 'め', 'も',
		'や', 'ゆ', 'よ', 'ら', 'り',
		'る', 'れ', 'ろ', 'わ', 'が',
		'ぎ', 'ぐ', 'げ', 'ご', 'ざ',
		'じ', 'ず', 'ぜ', 'ぞ', 'ば',
		'び', 'ぶ', 'べ', 'ぼ', 'ぱ',
		'ぴ', 'ぷ', 'ぺ', 'ぽ'
	};

	static private final String serifu11 =
		"＊「おうじ　%sよ。\r\n" +
		"　　よくぞ　ぶじで　もどってきた。\r\n" +
		"\r\n";

	static private final String serifu12 =
		"＊「これは　%s　おうじ！\r\n" +
		"　　よくぞ　まいられた。\r\n" +
		"\r\n";

	static private final String serifu13 =
		"＊「おお！%sよ。\r\n" +
		"　　なんと　ここでも　ふっかつの\r\n" +
		"　　じゅもんが　きけるのじゃ。\r\n" +
		"＊「べんりな　よのなかに　なったもの\r\n" +
		"　　よのう。\r\n" +
		"\r\n";

	static private final String serifu21 =
		"＊「%sが　つぎのレべルになる\r\n" +
		"　　には　あと%sポイントの\r\n" +
		"　　けいけんが　ひつようじゃ。\r\n" +
		"\r\n";

	static private final String serifu31 =
		"＊「そなたに　ふっかつのじゅもんを\r\n" +
		"　　おしえよう！\r\n" +
		"\r\n" +
		"%s\r\n\r\n";

	static private final String serifu41 =
		"＊「そなたが　ハーゴンをたおしてくる\r\n" +
		"　　ひを　たのしみに　まっておるぞ！\r\n" +
		"　　では　またあおう　わがむすこよ。";

	static private final String serifu42 =
		"＊「わがむすこ　%s　を\r\n" +
		"　　よろしく　たのむぞよ。";

	static private final String serifu43 =
		"＊「では　また　あおう！\r\n" +
		"　　ロトのしそんたちよ！";

	static private final String serifu5 =
		"どこからともなく　うつくしい\r\n" +
		"こえが　きこえる……。\r\n" +
		"\r\n" +
		"＊「あなたがたに\r\n" +
		"　　ふっかつの　じゅもんを\r\n" +
		"　　おしえましょう。\r\n";

	static private final String serifu6 =
		"＊「いまのを　かきとって\r\n" +
		"　　くれましたか？\r\n" +
		"\r\n";

	static private final String serifu7 =
		"＊「わたしは　いつも　あなたがたを\r\n" +
		"　　みまもっています。わたしと\r\n" +
		"　　いとしきひとの　しそんたちよ。";

	static private final String whitespace = " 　\r\n";

	private byte [] codeArray;

	/**
	 * 呪文文字列を受けてコード配列を取得。
	 * @param jumon 呪文文字列
	 */
	public Jumon(String jumon)
		throws IllegalCharacterException
	{
		int count, length;
		char ch;

		count = 0;
		length = 0;

		for (int i=0 ; i<jumon.length() ; i++)
		{
			ch = jumon.charAt(i);

			if (whitespace.indexOf(ch) < 0)
			{
				// 空白文字ではない。

				length++;
			}
		}

		codeArray = new byte [length];

		for (int i=0 ; i<jumon.length() ; i++)
		{
			ch = jumon.charAt(i);

			if (whitespace.indexOf(ch) < 0)
			{
				// 空白文字ではない。

				int index = -1;

				for (int j=0 ; j < characterSet.length ; j++)
				{
					if (characterSet[j] == ch)
					{
						// 一致した。

						index = j;
						break;
					}
				}

				if (index >= 0)
				{
					// 使用可能な文字。

					codeArray[count] = (byte)index;
				}

				count++;

				if (index < 0)
				{
					// 使用できない文字を検出。

					throw new IllegalCharacterException("呪文", count, ch);
				}
			}
		}
	}

	/**
	 * コード配列を集約する。
	 * @param codeArray コード配列
	 */
	public Jumon(byte [] codeArray)
	{
		this.codeArray = codeArray;
	}

	/**
	 * 文字数を取得。
	 * @return 文字数
	 */
	public int getCodeCount()
	{
		return codeArray.length;
	}

	/**
	 * 文字コードを取得。
	 * @param index インデックス
	 * @return 文字コード
	 */
	public byte get(int index)
	{
		return codeArray[index];
	}

	/**
	 * 呪文文字列を生成。
	 * @return 呪文文字列
	 */
	public String getJumonStringNoReturn()
	{
		StringBuilder builder = new StringBuilder();

		for (int i=0 ; i<codeArray.length ; i++)
		{
			builder.append(characterSet[codeArray[i]]);
		}

		return builder.toString();
	}

	/**
	 * 呪文文字列を生成。
	 * @return 呪文文字列
	 */
	public String getJumonStringOnly()
	{
		StringBuilder builder = new StringBuilder();

		for (int i=0 ; i<codeArray.length ; i++)
		{
			builder.append(characterSet[codeArray[i]]);

			if (i % 10 == 2 || i % 10 == 5)
			{
				// 各行の３文字目，５文字目。

				builder.append(" ");
			}
			else if (i % 10 == 9)
			{
				// 各行の行末。

				if (i < 49)
				{
					// ４行目まで。

					builder.append("\r\n");
				}
				else
				{
					// ５行目。

					builder.append(" ");
				}
			}
		}

		return builder.toString();
	}

	/**
	 * 呪文文字列を生成。
	 * @param gameData ゲームデータ
	 * @param type 0/1/2
	 * @return 呪文文字列
	 */
	public String getJumonAndSerifuString(GameData gameData, int type)
	{
		StringBuilder builder = new StringBuilder();

		int ローレシアの王子の経験値 = 0;
		int サマルトリアの王子の経験値 = 0;
		int ムーンブルクの王女の経験値 = 0;

		if (gameData.playerCollection.size() >= 1)
		{
			ローレシアの王子の経験値 =
				gameData.playerCollection.get(0).経験値;
		}

		if (gameData.playerCollection.size() >= 2)
		{
			サマルトリアの王子の経験値 =
				gameData.playerCollection.get(1).経験値;
		}

		if (gameData.playerCollection.size() >= 3)
		{
			ムーンブルクの王女の経験値 =
				gameData.playerCollection.get(2).経験値;
		}

		boolean ローレシアの王子 = gameData.playerCollection.size() >= 1;
		boolean サマルトリアの王子 = gameData.playerCollection.size() >= 2;
		boolean ムーンブルクの王女 = gameData.playerCollection.size() >= 3;

		for (int i=0 ; i<codeArray.length ; i++)
		{
			if (i % 10 == 0)
			{
				// 各行１文字目。

				if (i == 0)
				{
					// １行目。

					builder.append("　　");
				}
				else if (i >= 10 && i <=40)
				{
					// ２行目から４行目。

					builder.append("\r\n　　");
				}
				else
				{
					// ５行目。

					builder.append(" ");
				}
			}

			builder.append(characterSet[codeArray[i]]);

			if (i + 1 < codeArray.length)
			{
				// まだ続く。

				if (i % 10 == 2 || i % 10 == 5)
				{
					// 各行３文字目６文字目。

					builder.append(" ");
				}
			}
		}

		String name = gameData.getローレシアの王子の名前();

		String ret = null;

		switch (type)
		{
			case 0:
				ret = String.format(serifu11, name);
				break;

			case 1:
				ret = String.format(serifu12, name);
				break;

			case 2:
				ret = String.format(serifu13, name);
				break;
		}

		if (ローレシアの王子)
		{
			// ローレシアの王子は仲間。

			ret +=
				String.format(
					serifu21,
					name,
					new NumberString(
						new ローレシアの王子の経験値().getPointForNextLevel
							(ローレシアの王子の経験値)));
		}

		SamarutoriaMuunburukuName name2 = new SamarutoriaMuunburukuName(name);

		if (サマルトリアの王子)
		{
			// サマルトリアの王子は仲間。

			ret +=
				String.format(
					serifu21,
					name2.サマルトリアの王子の名前,
					new NumberString(
						new サマルトリアの王子の経験値().getPointForNextLevel
							(サマルトリアの王子の経験値)));
		}

		if (ムーンブルクの王女)
		{
			// ムーンブルクの王女は仲間。

			ret +=
				String.format(
					serifu21,
					name2.ムーンブルクの王女の名前,
					new NumberString(
						new ムーンブルクの王女の経験値().getPointForNextLevel
							(ムーンブルクの王女の経験値)));
		}

		ret += String.format(serifu31, builder.toString());

		switch (type)
		{
			case 0:
				ret += serifu41;
				break;

			case 1:
				ret += String.format(serifu42, name2.サマルトリアの王子の名前);
				break;

			case 2:
				ret += serifu43;
				break;
		}

		return ret;
	}

	/**
	 * ふっかつのたま仕様の呪文文字列を生成。
	 * @return 呪文文字列
	 */
	public String getJumonStringByTama()
	{
		StringBuilder builder = new StringBuilder();

		builder.append(serifu5);

		builder.append("\r\n");

		for (int i=0 ; i<codeArray.length ; i++)
		{
			if (i % 10 == 0)
			{
				// 各行１文字目。

				if (i == 0)
				{
					// １行目。

					builder.append("　　");
				}
				else if (i >= 10 && i <=40)
				{
					// ２行目から４行目。

					builder.append("\r\n　　");
				}
				else
				{
					// ５行目。

					builder.append(" ");
				}
			}

			builder.append(characterSet[codeArray[i]]);

			if (i + 1 < codeArray.length)
			{
				// まだ続く。

				if (i % 10 == 2 || i % 10 == 5)
				{
					// 各行３文字目６文字目。

					builder.append(" ");
				}
			}
		}

		builder.append("\r\n");
		builder.append("\r\n");

		builder.append(serifu6);
		builder.append(serifu7);

		return builder.toString();
	}

	/**
	 * シフトを解除したバイト列を取得。
	 * @return シフトを解除したバイト列
	 */
	public byte [] getPlainArray()
	{
		byte shift = (byte)(((codeArray[0] & 0x06) >> 1) + 1);

		byte [] ret = new byte [codeArray.length];

		for (int i=0 ; i<ret.length ; i++)
		{
			ret[i] = codeArray[i];

			if (i >= 1)
			{
				// ２文字目以降。

				ret[i] =
					(byte)((ret[i] + 0x40 - (codeArray[i - 1] + shift)) % 0x40);
			}
		}

		return ret;
	}
}

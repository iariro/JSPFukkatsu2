package kumagai.Fukkatsu2.logic;

import java.util.ArrayList;

/**
 * 復活の呪文生成処理
 */
public class JumonGenerator
{
	/**
	 * 指定の文字列に余計な文字を追加して復活の呪文生成を試みる。
	 * @param phrases 元となるフレーズ
	 */
	static public ArrayList<String> generateWithExtraCharacter(ArrayList<String> phrases)
	{
		int countError = 0;
		boolean loop = true;
		ArrayList<String> jumonList = new ArrayList<>();

		for (int j=0 ; j<phrases.size() ; j++)
		{
			String phrase0 = phrases.get(j);

			for (int i=0 ; i<Jumon.characterSet.length && loop ; i++)
			{
				String phrase = phrase0;

				do
				{
					try
					{
						Jumon jumon = new Jumon(phrase);

						CompressedGameDataBitArray compressed =
							new CompressedGameDataBitArray(jumon.getPlainArray());

						ExtendedGameDataBitArray extended =
							new ExtendedGameDataBitArray(compressed);

						String namecheck = extended.isValidローレシアの王子の名前();

						if (namecheck == null)
						{
							// ローレシアの王子の名前は正しい。

							GameData gameData = new GameData(extended);

							String datacheck = gameData.isValid();

							if (datacheck == null)
							{
								// データ内容は正しい。

								if (compressed.getチェックサム１() ==
									compressed.getチェックサム２())
								{
									// チェックサムは正当。

									if (compressed.isValidTerminate())
									{
										// 端数OK。

										jumonList.add(jumon.getJumonStringOnly());
									}
								}
							}
						}
					}
					catch (Exception exception)
					{
					}

					phrase += Jumon.characterSet[i];

					if (countError >= 20)
					{
						// エラーは２０件を超えた。

						loop = false;
					}
				}
				while (phrase.length() <= 52 && loop);
			}
		}

		return jumonList;
	}
}

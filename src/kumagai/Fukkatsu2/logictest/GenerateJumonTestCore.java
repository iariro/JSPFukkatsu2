package kumagai.Fukkatsu2.logictest;

import java.util.*;
import junit.framework.*;
import kumagai.Fukkatsu2.logic.*;

public class GenerateJumonTestCore
	extends TestCase
{
	public void test()
	{
	}

	protected void _test(String phrase0)
	{
		Calendar start = Calendar.getInstance();

		int count1 = 0;
		int count2 = 0;

		boolean loop = true;

		for (int i=0 ; i<Jumon.characterSet.length && loop ; i++)
		{
			String phrase = phrase0;

			do
			{
				try
				{
					Jumon jumon = new Jumon(phrase);

					count2++;
					CompressedGameDataBitArray compressedGameDataBitArray =
						new CompressedGameDataBitArray(jumon.getPlainArray());

					ExtendedGameDataBitArray extendedGameDataBitArray =
						new ExtendedGameDataBitArray(compressedGameDataBitArray);

					System.out.print(phrase);

					String namecheck = extendedGameDataBitArray.isValidローレシアの王子の名前();

					if (namecheck == null)
					{
						GameData gameData =
							new GameData(extendedGameDataBitArray);

						String datacheck = gameData.isValid();

						if (datacheck == null)
						{
							if (compressedGameDataBitArray.getチェックサム１() ==
								compressedGameDataBitArray.getチェックサム２())
							{
								System.out.println(" OK");
							}
							else
							{
								System.out.println(" check sum NG");
							}
						}
						else
						{
							System.out.println(" " + datacheck);
						}

						count1++;
					}
					else
					{
						System.out.println(" 名前" + namecheck + "が不正です");
					}
				}
				catch (InvalidJumonException exception)
				{
					System.out.println(exception.getMessage());
				}
				catch (InvalidItemException exception)
				{
					System.out.println(exception.getMessage());
				}
				catch (IllegalCharacterException exception)
				{
					System.out.println(exception.getMessage());
				}

				phrase += Jumon.characterSet[i];
			}
			while (phrase.length() <= 52 && loop);
		}

		System.out.println(count2 + "個中" + count1 + "個");

		long time = Calendar.getInstance().getTimeInMillis() - start.getTimeInMillis();

		System.out.println(time);
	}
}

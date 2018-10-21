package kumagai.Fukkatsu2.logictest;

import java.util.ArrayList;
import java.util.Calendar;

import junit.framework.TestCase;
import kumagai.Fukkatsu2.logic.CompressedGameDataBitArray;
import kumagai.Fukkatsu2.logic.ExtendedGameDataBitArray;
import kumagai.Fukkatsu2.logic.GameData;
import kumagai.Fukkatsu2.logic.IllegalCharacterException;
import kumagai.Fukkatsu2.logic.InvalidItemException;
import kumagai.Fukkatsu2.logic.InvalidJumonException;
import kumagai.Fukkatsu2.logic.Jumon;
import kumagai.Fukkatsu2.logic.JumonGenerator;

public class GenerateJumonTestCore
	extends TestCase
{
	public void test()
	{
	}

	protected void _test(String phrase, boolean enableSamarutoria)
	{
		ArrayList<String> phrases = new ArrayList<>();
		phrases.add(phrase);
		ArrayList<Jumon> jumonList = JumonGenerator.generateWithExtraCharacter(phrases, enableSamarutoria);

		for (Jumon jumon : jumonList)
		{
			System.out.println(jumon.getJumonStringNoReturn());
		}
	}

	protected void generateInline(String phrase0)
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

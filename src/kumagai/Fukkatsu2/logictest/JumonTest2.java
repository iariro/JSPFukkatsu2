package kumagai.Fukkatsu2.logictest;

import junit.framework.*;
import kumagai.Fukkatsu2.logic.*;

public class JumonTest2
	extends TestCase
{
	public void test1()
	{
		test("ゆうていみやおうきむこうほりいゆうじとりやまあきらぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺ");
	}

	private void test(String phrase)
	{
		try
		{
			Jumon jumon = new Jumon(phrase);

			for (int i=0 ; i<jumon.getCodeCount() ; i++)
			{
				if (i > 0)
				{
					if (i % 10 == 0)
					{
						System.out.println(new String());
					}
					else
					{
						System.out.print(" ");
					}
				}

				System.out.printf("%2d", jumon.get(i));
			}

			byte [] plainArray = jumon.getPlainArray();

			System.out.println(new String());
			System.out.println("plainArray.Length=" + plainArray.length);

			for (int i=0 ; i<plainArray.length ; i++)
			{
				if (i > 0)
				{
					if (i % 10 == 0)
					{
						System.out.println(new String());
					}
					else
					{
						System.out.print(" ");
					}
				}

				System.out.printf("%2d", plainArray[i]);
			}

			System.out.println(new String());

			CompressedGameDataBitArray compressedGameDataBitArray =
				new CompressedGameDataBitArray(plainArray);

			ExtendedGameDataBitArray extendedGameDataBitArray =
				new ExtendedGameDataBitArray(compressedGameDataBitArray);

			System.out.printf("%dbit\n", extendedGameDataBitArray.size());

			GameData gameData =
				new GameData(extendedGameDataBitArray);

			String datacheck = gameData.isValid();

			if (datacheck == null)
			{
				//if (compressedGameDataBitArray.チェックサム１ ==
				//	compressedGameDataBitArray.チェックサム２)
				//{
					System.out.println(gameData.getローレシアの王子の名前());

					System.out.printf(
						"チェックサム=%d:%d\n",
						compressedGameDataBitArray.getチェックサム１(),
						compressedGameDataBitArray.getチェックサム２());

					System.out.printf(
						"サマルトリア=%d\n",
						extendedGameDataBitArray.getサマルトリア());

					System.out.printf(
						"64=%s 65=%s\n",
						compressedGameDataBitArray.get(64),
						compressedGameDataBitArray.get(65));

					int offset = 72;
					int itemCount = extendedGameDataBitArray.getAsInt(offset + 20, 4);

					System.out.printf("アイテム数=%d\n", itemCount);

					/*JumonBitArray itemBitArray =*/
						extendedGameDataBitArray.getPart(offset + 24, 7 * itemCount);

					/*
					int itemIndex = itemBitArray.GetAsInt(7 * 0, 7);

					System.out.println(new ItemAndEquipment(itemIndex).item.name);
					*/

					offset += 20 + 4 + 7 * itemCount;

					System.out.printf(
						"next=%d:%s\n",
						offset,
						extendedGameDataBitArray.get(offset));

					offset++;

					/*
					for (int i=offset ; i<extendedGameDataBitArray.Count ; i++)
					{
						if (i > 0 && i % 8 == 0)
						{
							System.out.println(String.Empty);
						}

						if (extendedGameDataBitArray[i])
						{
							System.out.print("■");
						}
						else
						{
							System.out.print("□");
						}
					}
					*/
				//}
				//else
				//{
				//	System.out.println(" check sum NG");
				//}
			}
			else
			{
				System.out.println(" " + datacheck);
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
	}
}

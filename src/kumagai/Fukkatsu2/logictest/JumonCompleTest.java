package kumagai.Fukkatsu2.logictest;

import kumagai.Fukkatsu2.logic.CompressedGameDataBitArray;
import kumagai.Fukkatsu2.logic.ExtendedGameDataBitArray;
import kumagai.Fukkatsu2.logic.GameData;
import kumagai.Fukkatsu2.logic.IllegalCharacterException;
import kumagai.Fukkatsu2.logic.InvalidItemException;
import kumagai.Fukkatsu2.logic.InvalidJumonException;
import kumagai.Fukkatsu2.logic.Jumon;

public class JumonCompleTest
	//extends TestCase
{
	int count = 0;

	private void completeJumonRecursive(String jumon, int add)
		throws IllegalCharacterException
	{
		if (count > 10000000)
		{
			return;
		}

		String message;

		try
		{
			count++;

			new GameData(
				new ExtendedGameDataBitArray(
					new CompressedGameDataBitArray(
						new Jumon(jumon).getPlainArray())));

			System.out.println(jumon);
			message = null;
		}
		catch (InvalidItemException exception)
		{
			message = exception.getMessage();
		}
		catch (InvalidJumonException exception)
		{
			message = exception.getMessage();
		}

		if (count % 100000 == 0)
		{
			System.out.printf("%d:%s %s\n", count, jumon, message);
		}

		for (char ch : Jumon.characterSet)
		{
			if (add > 0)
			{
				completeJumonRecursive(jumon + ch, add - 1);
			}
		}
	}

	public void test()
		throws IllegalCharacterException, InvalidItemException, InvalidJumonException
	{
		completeJumonRecursive(
			"すひぞぶたうとあやがげくさせがばやねいりぐぷはほぞれぱれぎばぽしたとずぷゆびうれ", 5);

		System.out.println(count);
	}
}

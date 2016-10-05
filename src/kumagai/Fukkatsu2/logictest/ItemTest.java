package kumagai.Fukkatsu2.logictest;

import junit.framework.*;
import kumagai.Fukkatsu2.logic.*;

public class ItemTest
	extends TestCase
{
	public void testひのきのぼう()
	{
		assertEquals(
			1,
			ItemCollection.getInstance().getCodeFromName("ひのきのぼう"));
	}

	public void testやくそう()
	{
		assertEquals(
			60,
			ItemCollection.getInstance().getCodeFromName("やくそう"));
	}

	public void testあぶないみずぎ()
	{
		assertEquals(
			63,
			ItemCollection.getInstance().getCodeFromName("あぶないみずぎ"));
	}

	public void test見つからない()
	{
		assertEquals(
			-1,
			ItemCollection.getInstance().getCodeFromName("あいうえお"));
	}

	public void testItemAndEquipment01()
	{
		ItemAndEquipment itemAndEquipment = new ItemAndEquipment(73);
		assertEquals("はやぶさのけん", itemAndEquipment.item.name);
		assertTrue(itemAndEquipment.equipment);
	}

	public void testItemAndEquipment02()
	{
		ItemAndEquipment itemAndEquipment =
			new ItemAndEquipment("はがねのつるぎ", true);
		assertEquals(74, itemAndEquipment.getCode());
	}

	public void testItemCollection1()
	{
		System.out.println(ItemCollection.getInstance().get(1));
	}
}

package kumagai.Fukkatsu2.logic;

import java.util.*;

/**
 * プレイヤー情報。
 */
public class Player
{
	public int 経験値;
	public ArrayList<ItemAndEquipment> itemCollection;

	/**
	 * 経験値を割り当てプレイヤー情報を構築。
	 * @param 経験値 経験値
	 */
	public Player(int 経験値)
	{
		itemCollection = new ArrayList<ItemAndEquipment>();

		this.経験値 = 経験値;
	}

	/**
	 * 経験値，アイテム情報を受け、プレイヤー情報を構築。
	 * @param 経験値 経験値
	 * @param itemCount アイテムの個数
	 * @param itemBitArray アイテム情報を内容とするビット配列
	 */
	public Player(int 経験値, int itemCount, JumonBitArray itemBitArray)
		throws InvalidItemException
	{
		this.経験値 = 経験値;

		itemCollection = new ArrayList<ItemAndEquipment>();

		if (itemCount > 8)
		{
			// アイテムは多過ぎる。

			throw new InvalidItemException(itemCount);
		}

		for (int i=0 ; i<itemCount ; i++)
		{
			int index = itemBitArray.getAsInt(7 * i, 7);

			if (index >= 1 && index <= 0x80 &&
				(index % 0x40 >= 1) && (index % 0x40 < 0x3f))
			{
				// アイテムの値は正しい。

				itemCollection.add(new ItemAndEquipment(index));
			}
			else
			{
				// アイテムの値は不正。

				throw new InvalidItemException(i, itemCount, index);
			}
		}
	}
}

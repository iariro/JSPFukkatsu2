package kumagai.Fukkatsu2.struts2;

import java.util.*;
import kumagai.Fukkatsu2.logic.*;

/**
 * 装備判定オブジェクト。
 * @author kumagai
 */
interface ItemEquipmentJudge
{
	/**
	 * 装備判定。
	 * @param item アイテム
	 * @return true=装備できる／false=できない
	 */
	boolean judge(Item item);
}

/**
 * アイテムドロップダウンリストデータ。
 * @author kumagai
 */
public class ItemSelectionList
{
	public ArrayList<IndexAndName> items = new ArrayList<IndexAndName>();

	/**
	 * 指定のプレイヤー用のリストデータを構築する。
	 * @param itemJudge 装備判定オブジェクト
	 */
	public ItemSelectionList(ItemEquipmentJudge itemJudge)
	{
		items.add(new IndexAndName(0, "-"));

		for (int i=0 ; i<ItemCollection.getInstance().size() ; i++)
		{
			Item item = ItemCollection.getInstance().get(i + 1);

			items.add(new IndexAndName(i + 1, item.name));

			if (itemJudge.judge(item))
			{
				// 装備可。

				items.add(new IndexAndName(64 + i + 1, item.name + " E"));
			}
		}
	}
}

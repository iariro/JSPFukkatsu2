package kumagai.Fukkatsu2.logic;

import java.util.*;

/**
 * アイテム一覧。
 * アイテムの番号が１－６４であることに注意。
 */
public class ItemCollection
	extends ArrayList<Item>
{
	/**
	 * ItemCollectionオブジェクトの唯一のインスタンス。
	 */
	static private final ItemCollection instance = new ItemCollection();

	/**
	 * 唯一のインスタンスを取得。
	 * @return インスタンス
	 */
	static public ItemCollection getInstance()
	{
		return instance;
	}

	/**
	 * 一覧中のアイテムの個数。
	 * @return アイテムの個数
	 */
	public int getCount()
	{
		return size();
	}

	/**
	 * アイテム情報からアイテムの番号を取得。
	 * @param item アイテム情報
	 * @return アイテムの番号
	 */
	public int indexOf(Item item)
	{
		return super.indexOf(item) + 1;
	}

	/**
	 * アイテムの番号からアイテム情報を取得。
	 */
	public Item get(int i)
	{
		return super.get(i - 1);
	}

	/**
	 * アイテム一覧情報を構築する。
	 */
	public ItemCollection()
	{
		add(new Item("ひのきのぼう", 1, true, true, true, true, 0));
		add(new Item("せいなるナイフ", 1, true, true, true, true, 0));
		add(new Item("まどうしのつえ", 1, true, true, true, true, 2));
		add(new Item("いかずちのつえ", 1, true, true, true, true, 3));
		add(new Item("こんぼう", 1, true, true, true, false, 0));
		add(new Item("どうのつるぎ", 1, true, true, true, false, 0));
		add(new Item("くさりがま", 1, true, true, true, false, 0));
		add(new Item("てつのやり", 1, true, true, true, false, 1));
		add(new Item("はやぶさのけん", 1, true, true, true, false, 3));
		add(new Item("はがねのつるぎ", 1, true, true, false, false, 1));
		add(new Item("おおかなづち", 1, true, true, false, false, 3));
		add(new Item("はかいのつるぎ", 1, true, true, false, false, 4));
		add(new Item("ドラゴンキラー", 1, true, true, false, false, 3));
		add(new Item("ひかりのつるぎ", 1, true, true, false, false, 3));
		add(new Item("ロトのつるぎ", 1, true, true, false, false, 3));
		add(new Item("いなずまのけん", 1, true, true, false, false, 4));
		add(new Item("ぬののふく", 2, true, true, true, true, 0));
		add(new Item("みかわしのふく", 2, true, true, true, true, 2));
		add(new Item("みずのはごろも", 2, true, true, true, true, 3));
		add(new Item("ミンクのコート", 2, true, true, true, true, 3));
		add(new Item("かわのよろい", 2, true, true, true, false, 0));
		add(new Item("くさりかたびら", 2, true, true, true, false, 0));
		add(new Item("あくまのよろい", 2, true, true, true, false, 4));
		add(new Item("まほうのよろい", 2, true, true, true, false, 3));
		add(new Item("はがねのよろい", 2, true, true, false, false, 1));
		add(new Item("ガイアのよろい", 2, true, true, false, false, 3));
		add(new Item("ロトのよろい", 2, true, true, false, false, 4));
		add(new Item("かわのたて", 3, true, true, true, false, 0));
		add(new Item("ちからのたて", 3, true, true, true, false, 3));
		add(new Item("はがねのたて", 3, true, true, false, false, 1));
		add(new Item("しにがみのたて", 3, true, true, false, false, 4));
		add(new Item("ロトのたて", 3, true, true, false, false, 3));
		add(new Item("ふしぎなぼうし", 4, true, true, true, true, 3));
		add(new Item("てつかぶと", 4, true, true, false, false, 2));
		add(new Item("ロトのかぶと", 4, true, true, false, false, 3));
		add(new Item("ロトのしるし", 0, true, false, false, false, 3));
		add(new Item("ふねのざいほう", 0, true, false, false, false, 3));
		add(new Item("つきのかけら", 0, true, false, false, false, 3));
		add(new Item("ルビスのまもり", 0, true, false, false, false, 4));
		add(new Item("じゃしんのぞう", 0, true, false, false, false, 4));
		add(new Item("せかいじゅのは", 0, true, false, false, false, 3));
		add(new Item("やまびこのふえ", 0, true, false, false, false, 3));
		add(new Item("ラーのかがみ", 0, true, false, false, false, 1));
		add(new Item("あまつゆのいと", 0, true, false, false, false, 2));
		add(new Item("せいなるおりき", 0, true, false, false, false, 3));
		add(new Item("かぜのマント", 5, true, true, true, true, 1));
		add(new Item("あくまのしっぽ", 5, true, true, true, true, 3));
		add(new Item("まよけのすず", 5, true, true, true, true, 2));
		add(new Item("ふっかつのたま", 0, true, false, false, false, 3));
		add(new Item("ゴールドカード", 0, true, false, false, false, 0));
		add(new Item("ふくびきけん", 0, true, false, false, false, 0));
		add(new Item("せいすい", 0, true, false, false, false, 0));
		add(new Item("キメラのつばさ", 0, true, false, false, false, 0));
		add(new Item("みみせん", 0, false, false, false, false, 0));
		add(new Item("きんのかぎ", 0, true, false, false, false, 3));
		add(new Item("ぎんのかぎ", 0, true, false, false, false, 0));
		add(new Item("ろうやのかぎ", 0, true, false, false, false, 3));
		add(new Item("すいもんのかぎ", 0, true, false, false, false, 3));
		add(new Item("どくけしそう", 0, true, false, false, false, 0));
		add(new Item("やくそう", 0, true, false, false, false, 0));
		add(new Item("いのりのゆびわ", 0, true, false, false, false, 0));
		add(new Item("しのオルゴール", 0, false, false, false, false, 0));
		add(new Item("あぶないみずぎ", 0, false, false, false, false, 0));
	}

	/**
	 * アイテム名からアイテムの番号を取得。
	 * @param name アイテム名
	 * @return アイテムの番号
	 */
	public int getCodeFromName(String name)
	{
		for (int i=0 ; i<size() ; i++)
		{
			if (super.get(i).name.equals(name))
			{
				// 名前は一致する。

				return i + 1;
			}
		}

		return -1;
	}
}

package kumagai.Fukkatsu2.logic;

/**
 * 不正なアイテムの例外。
 * @author kumagai
 */
public class InvalidItemException
	extends Exception
{
	/**
	 * 基底クラスを初期化し例外オブジェクトを構築。
	 * @param index 位置
	 * @param itemCount アイテム数
	 * @param item 項目
	 */
	public InvalidItemException(int index, int itemCount, int item)
	{
		super(String.format("アイテム%d/%d=%dが不正です", index, itemCount, item));
	}

	/**
	 * 基底クラスを初期化し例外オブジェクトを構築。
	 * @param itemCount アイテム数
	 */
	public InvalidItemException(int itemCount)
	{
		super(String.format("アイテム%d個は多過ぎ", itemCount));
	}
}

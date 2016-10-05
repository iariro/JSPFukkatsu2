package kumagai.Fukkatsu2.logic;

/**
 * アイテム。
 * @author kumagai
 */
public class Item
{
	public final String name;
	public final int itemKind;
	public final boolean valid;
	public final boolean ローレシア王子装備可;
	public final boolean サマルトリア王子装備可;
	public final boolean ムーンブルク王女装備可;
	public final int getLevel;

	/**
	 * アイテムオブジェクトを構築。
	 * @param name 名前
	 * @param itemKind アイテムの種類
	 * @param valid true=採用アイテム／false=没アイテム
	 * @param ローレシア王子装備可 true=ローレシア王子装備可
	 * @param サマルトリア王子装備可 true=サマルトリア王子装備可
	 * @param ムーンブルク王女装備可 true=ムーンブルク王女装備可
	 * @param getLevel 0=無制限 1=２人必要 2=３人必要 3=船必要 4=月のかけら必要
	 */
	public Item(String name, int itemKind, boolean valid,
		boolean ローレシア王子装備可,
		boolean サマルトリア王子装備可,
		boolean ムーンブルク王女装備可,
		int getLevel)
	{
		this.name = name;
		this.itemKind = itemKind;
		this.valid = valid;
		this.ローレシア王子装備可 = ローレシア王子装備可;
		this.サマルトリア王子装備可 = サマルトリア王子装備可;
		this.ムーンブルク王女装備可 = ムーンブルク王女装備可;
		this.getLevel = getLevel;
	}
}

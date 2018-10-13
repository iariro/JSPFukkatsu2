package kumagai.Fukkatsu2.struts2;

import java.util.ArrayList;

import kumagai.Fukkatsu2.logic.ExperienceTable;
import kumagai.Fukkatsu2.logic.ItemAndEquipment;

/**
 * JSP表示用のキャラクターデータ。
 */
class PayerForJsp
{
	public final boolean exist;
	public final String role;
	public final String name;
	public final int experience;
	public final int level;
	public final ArrayList<ItemAndEquipment> items1;
	public final ArrayList<ItemAndEquipment> items2;
	public final String memo;

	/**
	 * 指定の値のメンバーへの割り当て、アイテムの配列の分割を行う。
	 * @param exist true=存在する／false=しない
	 * @param role 王子・王女の区別
	 * @param name キャラ名
	 * @param experience 経験値
	 * @param experienceTable 経験値テーブル
	 * @param itemCollection アイテムコレクション
	 */
	public PayerForJsp(boolean exist, String role, String name, int experience,
		ExperienceTable experienceTable,
		ArrayList<ItemAndEquipment> itemCollection)
	{
		this.exist = exist;
		this.role = role;
		this.name = name;
		this.experience = experience;
		this.level = experienceTable.getLevel(experience);

		items1 = new ArrayList<ItemAndEquipment>();
		items2 = new ArrayList<ItemAndEquipment>();

		for (int i=0 ; i<itemCollection.size() && i<8 ; i++)
		{
			if (i < 4)
			{
				// １段目分。

				items1.add(itemCollection.get(i));
			}
			else
			{
				// ２段目分。

				items2.add(itemCollection.get(i));
			}
		}

		if (itemCollection.size() > 8)
		{
			memo = String.format("アイテム数本当は%d", itemCollection.size());
		}
		else
		{
			memo = null;
		}
	}
}

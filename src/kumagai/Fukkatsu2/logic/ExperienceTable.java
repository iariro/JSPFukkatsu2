package kumagai.Fukkatsu2.logic;

/**
 * 経験値テーブルの基底クラス。
 */
public class ExperienceTable
{
	/**
	 * 経験値テーブル。
	 */
	public final int [] table;

	/**
	 * 経験値テーブル情報を構築する。
	 * @param table 経験値テーブル情報
	 */
	public ExperienceTable(int [] table)
	{
		this.table = table;
	}

	/**
	 * 経験値からレベルを取得。
	 *
	 * @param experience 経験値
	 *
	 * @return レベル
	 */
	public int getLevel(int experience)
	{
		int i;

		for (i=0 ; i<table.length ; i++)
		{
			if (experience < table[i])
			{
				// テーブルの値が指定の経験値よりも大きい。

				return i;
			}
		}

		return i;
	}

	/**
	 * 次のレベルまでの経験値を取得。
	 *
	 * @param experience 経験値
	 *
	 * @return 次のレベルまでの経験値
	 */
	public int getPointForNextLevel(int experience)
	{
		int level = getLevel(experience);

		return level < table.length ? table[level] - experience : 0;
	}
}

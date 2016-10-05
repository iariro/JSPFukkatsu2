package kumagai.Fukkatsu2.struts2;

import org.apache.struts2.convention.annotation.*;
import kumagai.Fukkatsu2.logic.*;

/**
 * ゲームデータ編集画面表示アクション。
 * @author kumagai
 */
@Namespace("/fukkatsu2")
@Result(name="success", location="/fukkatsu2/generatejumon1.jsp")
public class GenerateJumon1Action
{
	public IndexAndName [] savepoints;
	public ItemSelectionList rooreshiaItems;
	public ItemSelectionList samarutoriaItems;
	public ItemSelectionList muunburukuItems;

	/**
	 * ゲームデータ編集画面表示アクション。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("generatejumon1")
	public String execute()
		throws Exception
	{
		IndexAndName [] savepoints =
			new IndexAndName [SavePoint.values().length];

		for (int i=0 ; i<SavePoint.values().length ; i++)
		{
			savepoints[i] =
				new IndexAndName(i, SavePoint.values()[i].name());
		}

		this.savepoints = savepoints;

		rooreshiaItems =
			new ItemSelectionList(
				new ItemEquipmentJudge()
				{
					public boolean judge(Item item)
					{
						return item.ローレシア王子装備可;
					}
				});

		samarutoriaItems =
			new ItemSelectionList(
				new ItemEquipmentJudge()
				{
					public boolean judge(Item item)
					{
						return item.サマルトリア王子装備可;
					}
				});

		muunburukuItems =
			new ItemSelectionList(
				new ItemEquipmentJudge()
				{
					public boolean judge(Item item)
					{
						return item.ムーンブルク王女装備可;
					}
				});

		return "success";
	}
}

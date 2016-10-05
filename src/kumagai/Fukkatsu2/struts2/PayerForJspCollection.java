package kumagai.Fukkatsu2.struts2;

import java.util.*;
import kumagai.Fukkatsu2.logic.*;

/**
 * JSP表示用のキャラクターデータのコレクション。
 */
class PayerForJspCollection
	extends ArrayList<PayerForJsp>
{
	static private final ExperienceTable ローレシア経験値 =
		new ローレシアの王子の経験値();
	static private final ExperienceTable サマルトリア経験値 =
		new サマルトリアの王子の経験値();
	static private final ExperienceTable ムーンブルク経験値 =
		new ムーンブルクの王女の経験値();

	/**
	 * JSP表示用のキャラクターデータのコレクションを構築する。
	 * @param gamedata ゲームデータ
	 */
	public PayerForJspCollection(GameData gamedata)
	{
		String ローレシアの王子の名前 = gamedata.getローレシアの王子の名前();

		SamarutoriaMuunburukuName samarutoriaMuunburukuName =
			new SamarutoriaMuunburukuName(ローレシアの王子の名前);

		add(
			new PayerForJsp(
				"ローレシアの王子",
				ローレシアの王子の名前,
				gamedata.playerCollection.get(0).経験値,
				ローレシア経験値,
				gamedata.playerCollection.get(0).itemCollection));

		if (gamedata.playerCollection.size() >= 2)
		{
			// サマルトリアの王子は仲間。

			add(
				new PayerForJsp(
					"サマルトリアの王子",
					samarutoriaMuunburukuName.サマルトリアの王子の名前,
					gamedata.playerCollection.get(1).経験値,
					サマルトリア経験値,
					gamedata.playerCollection.get(1).itemCollection));
		}

		if (gamedata.playerCollection.size() >= 3)
		{
			// ムーンブルクの王女は仲間。

			add(
				new PayerForJsp(
					"ムーンブルクの王女",
					samarutoriaMuunburukuName.ムーンブルクの王女の名前,
					gamedata.playerCollection.get(2).経験値,
					ムーンブルク経験値,
					gamedata.playerCollection.get(2).itemCollection));
		}
	}
}

package kumagai.Fukkatsu2.struts2;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import kumagai.Fukkatsu2.logic.CompressedGameDataBitArray;
import kumagai.Fukkatsu2.logic.ExtendedGameDataBitArray;
import kumagai.Fukkatsu2.logic.GameData;
import kumagai.Fukkatsu2.logic.Jumon;
import kumagai.Fukkatsu2.logic.JumonGenerator;
import kumagai.Fukkatsu2.logic.WordPatternGenerator2;

/**
 * ねつ造呪文表示アクション。
 * @author kumagai
 */
@Namespace("/fukkatsu2")
@Result(name="success", location="/fukkatsu2/fabricatejumon2.jsp")
public class FabricateJumon2Action
{
	public String words;
	public LinkedHashMap<String[], GameData> jumonList = new LinkedHashMap<>();

	/**
	 * ねつ造呪文表示アクション。
	 * @return 処理結果
	 */
	@Action("fabricatejumon2")
	public String execute()
		throws Exception
	{
		ArrayList<String> phrases = new WordPatternGenerator2(words.split("\n"));
		ArrayList<String> jumonList = JumonGenerator.generateWithExtraCharacter(phrases);
		for (String jumon : jumonList)
		{
			Jumon jumon2 = new Jumon(jumon);
			CompressedGameDataBitArray compressedGameDataBitArray = new CompressedGameDataBitArray(jumon2.getPlainArray());
			ExtendedGameDataBitArray extendedGameDataBitArray = new ExtendedGameDataBitArray(compressedGameDataBitArray);
			GameData gameData = new GameData(extendedGameDataBitArray);
			this.jumonList.put(jumon2.getJumonStringOnly().split("\n"), gameData);
		}
		return "success";
	}
}

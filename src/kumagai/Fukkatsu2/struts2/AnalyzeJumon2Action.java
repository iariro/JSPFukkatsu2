package kumagai.Fukkatsu2.struts2;

import javax.xml.bind.DatatypeConverter;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import kumagai.Fukkatsu2.logic.CompressedGameDataBitArray;
import kumagai.Fukkatsu2.logic.ExtendedGameDataBitArray;
import kumagai.Fukkatsu2.logic.GameData;
import kumagai.Fukkatsu2.logic.Jumon;
import kumagai.Fukkatsu2.logic.SavePoint;

/**
 * 呪文解析アクション。
 * @author kumagai
 */
@Namespace("/fukkatsu2")
@Results
({
	@Result(name="success", location="/fukkatsu2/analyzejumon2.jsp"),
	@Result(name="error", location="/fukkatsu2/analyzejumon2error.jsp")
})
public class AnalyzeJumon2Action
{
	public String jumon;

	public String [] jumonLines;
	public String hexdata;
	public String error;
	public boolean excessBit;
	public Exception exception;
	public String savePoint;
	public int gold;
	public int variation;
	public boolean 月のかけら;
	public boolean 水門;
	public boolean 水のはごろも;
	public boolean 船;
	public boolean 少女;
	public String サマルトリア王子の状態;
	public boolean 命の紋章;
	public boolean 水の紋章;
	public boolean 月の紋章;
	public boolean 星の紋章;
	public boolean 太陽の紋章;
	public PayerForJspCollection players;
	public boolean encountZero;

	/**
	 * 呪文解析を行う。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("analyzejumon2")
	public String execute()
		throws Exception
	{
		GameData gamedata;

		try
		{
			Jumon jumon = new Jumon(this.jumon);

			jumonLines = jumon.getJumonStringOnly().split("\r\n");

			byte [] plainArray = jumon.getPlainArray();
			hexdata = DatatypeConverter.printHexBinary(plainArray);

			CompressedGameDataBitArray compressed =
				new CompressedGameDataBitArray(plainArray);
			ExtendedGameDataBitArray extendedGameDataBitArray =
				new ExtendedGameDataBitArray(compressed);
			gamedata = new GameData(extendedGameDataBitArray);

			if (compressed.getチェックサム１() !=
				compressed.getチェックサム２())
			{
				// チェックサムは一致する。

				error = "チェックサムエラー";
			}
			encountZero = compressed.isEncountZero();

			excessBit = extendedGameDataBitArray.hasExcessBit();
		}
		catch (Exception exception)
		{
			this.exception = exception;
			this.jumonLines = this.jumon.split("\r\n");

			return "error";
		}

		savePoint = SavePoint.values()[gamedata.セーブポイント].name();
		gold = gamedata.ゴールド;
		variation = gamedata.バリエーション;
		月のかけら = gamedata.月のかけら;
		水門 = gamedata.水門;
		水のはごろも = gamedata.水のはごろも;
		船 = gamedata.船;
		少女 = gamedata.少女;

		命の紋章 = gamedata.命の紋章;
		水の紋章 = gamedata.水の紋章;
		月の紋章 = gamedata.月の紋章;
		星の紋章 = gamedata.星の紋章;
		太陽の紋章 = gamedata.太陽の紋章;

		switch (gamedata.サマルトリア)
		{
			case 0:
				サマルトリア王子の状態 = "初期状態";
				break;
			case 1:
				サマルトリア王子の状態 = "サマルトリアの王の話を聞いた";
				break;
			case 2:
				サマルトリア王子の状態 = "勇者の泉で話を聞いた";
				break;
			case 3:
				サマルトリア王子の状態 = "ローレシアの王の話を聞いた";
				break;
			default:
				サマルトリア王子の状態 =
					Integer.toString(gamedata.サマルトリア);
				break;
		}

		players = new PayerForJspCollection(gamedata);

		return "success";
	}
}

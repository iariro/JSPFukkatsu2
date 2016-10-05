package kumagai.Fukkatsu2.struts2;

import org.apache.struts2.convention.annotation.*;

/**
 * 呪文解析アクション。
 * @author kumagai
 */
@Namespace("/fukkatsu2")
@Result(name="success", location="/fukkatsu2/analyzejumon1.jsp")
public class AnalyzeJumon1Action
{
	/**
	 * 何もしない。
	 * @return 処理結果
	 * @throws Exception
	 */
	@Action("analyzejumon1")
	public String execute()
		throws Exception
	{
		return "success";
	}
}

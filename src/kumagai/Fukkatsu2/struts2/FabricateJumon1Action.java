package kumagai.Fukkatsu2.struts2;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

/**
 * 呪文ねつ造アクション。
 * @author kumagai
 */
@Namespace("/fukkatsu2")
@Result(name="success", location="/fukkatsu2/fabricatejumon1.jsp")
public class FabricateJumon1Action
{
	/**
	 * 呪文ねつ造アクション。
	 * @return 処理結果
	 */
	@Action("fabricatejumon1")
	public String execute()
		throws Exception
	{
		return "success";
	}
}

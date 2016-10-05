
package kumagai.Fukkatsu2.logic;

/**
 * 不正な文字検出時の例外。
 * @author kumagai
 */
public class IllegalCharacterException
	extends Exception
{
	/**
	 * 基底クラスを初期化し例外オブジェクトを構築。
	 * @param item 文字列項目
	 * @param index 文字位置
	 * @param ch 不正な文字
	 */
	public IllegalCharacterException(String item, int index, char ch)
	{
		super(item + "の" + index + "文字目“" + ch + "”が不正です");
	}
}

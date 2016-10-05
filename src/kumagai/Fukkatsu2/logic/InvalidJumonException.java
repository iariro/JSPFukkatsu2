package kumagai.Fukkatsu2.logic;

/**
 * 不正な呪文検出時の例外。
 * @author kumagai
 */
public class InvalidJumonException
	extends Exception
{
	/**
	 * 基底クラスを初期化し例外オブジェクトを構築。
	 * @param message メッセージ
	 */
	public InvalidJumonException(String message)
	{
		super(message);
	}
}

package kumagai.Fukkatsu2.logic;

/**
 * ローレシアの王子の名前から構築可能なサマルトリアの王子・ムーンブルクの王女の
 * 名前。
 * http://msx.s58.xrea.com/hpd2v2.htm
 */
public class SamarutoriaMuunburukuName
{
	/**
	 * ローレシアの王子の名前のコード変換用テーブル。
	 */
	static final private ローレシアの王子の名前テーブル table =
		new ローレシアの王子の名前テーブル();

	/**
	 * サマルトリアの王子の名前テーブル。
	 */
	static private final String [] サマルトリアの王子の名前table =
		new String []
		{
			"ランド",
			"カイン",
			"アーサー",
			"コナン",
			"クッキー",
			"トンヌラ",
			"すけさん",
			"パウロ"
		};

	/**
	 * ムーンブルクの王女の名前テーブル。
	 */
	static private final String [] ムーンブルクの王女の名前table =
		new String []
		{
			"アイリン",
			"マリア",
			"ナナ",
			"あきな",
			"プリン",
			"まいこ",
			"リンダ",
			"サマンサ"
		};

	/**
	 * サマルトリアの王子の名前。
	 */
	public final String サマルトリアの王子の名前;

	/**
	 * ムーンブルクの王女の名前。
	 */
	public final String ムーンブルクの王女の名前;

	/**
	 * ローレシアの王子の名前から情報を構築。
	 * @param name ローレシアの王子の名前
	 */
	public SamarutoriaMuunburukuName(String name)
	{
		// 1～4文字目の合計値をAとする。
		int a =
			table.get(name.charAt(0)) +
			table.get(name.charAt(1)) +
			table.get(name.charAt(2)) +
			table.get(name.charAt(3));

		// 2～4文字目の合計値をBとする。
		int b =
			table.get(name.charAt(1)) +
			table.get(name.charAt(2)) +
			table.get(name.charAt(3));

		// Bの値が256以上の場合はAの値に1を足す。
		// Bの値が255以下の場合はそのまま。
		if (b >= 256)
		{
			a++;
		}

		// Aを64で割り，余りをCとする。
		int c = a % 64;

		// Cのビット5～3の値でサマルトリアの王子の名前が決定する。
		サマルトリアの王子の名前 =
			サマルトリアの王子の名前table[(c & 0x38) / 8];

		// Cのビット2～0の値でムーンブルクの王女の名前が決定する。
		ムーンブルクの王女の名前 =
			ムーンブルクの王女の名前table[(c & 0x07)];
	}
}

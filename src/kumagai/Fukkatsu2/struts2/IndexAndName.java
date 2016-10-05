package kumagai.Fukkatsu2.struts2;

/**
 * インデックスと名前の対。
 * @author kumagai
 */
public class IndexAndName
{
	public int index;
	public String name;

	/**
	 * 指定の値を割り当てる。
	 * @param index インデックス
	 * @param name 名前
	 */
	public IndexAndName(int index, String name)
	{
		this.index = index;
		this.name = name;
	}
}

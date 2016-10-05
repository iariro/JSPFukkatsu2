package kumagai.Fukkatsu2.logic;

/**
 * アイテム情報と装備しているかの情報。
 */
public class ItemAndEquipment
{
	public final Item item;
	public boolean equipment;

	/**
	 * 呪文コードに格納されているコードから情報を構築。
	 * @param code 呪文コードに格納されているコード
	 */
	public ItemAndEquipment(int code)
	{
		item = ItemCollection.getInstance().get(code % 0x40);
		equipment = (code & 0x40) == 0x40;
	}

	/**
	 * 呪文コードに格納されているコードから情報を構築。
	 * @param code 呪文コードに格納されているコード
	 * @param equipment 装備フラグ
	 */
	public ItemAndEquipment(int code, boolean equipment)
	{
		this.item = ItemCollection.getInstance().get(code % 0x40);
		this.equipment = equipment;
	}

	/**
	 * 各メンバーからオブジェクトを構築する。
	 * @param name アイテム名
	 * @param equipment true=装備している／false=していない
	 */
	public ItemAndEquipment(String name, boolean equipment)
	{
		int code = ItemCollection.getInstance().getCodeFromName(name);
		this.item = ItemCollection.getInstance().get(code);
		this.equipment = equipment;
	}

	/**
	 * 呪文コード格納用のコードを取得。
	 * @return 呪文コード格納用のコード
	 */
	public int getCode()
	{
		return
			ItemCollection.getInstance().indexOf(item) +
			(equipment ? 0x40 : 0x00);
	}
}

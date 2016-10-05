package kumagai.Fukkatsu2.logictest;

import junit.framework.*;
import kumagai.Fukkatsu2.logic.*;

public class NameTest
	extends TestCase
{
	public void test01()
	{
		assertEquals(
			10,
			new ローレシアの王子の名前テーブル().get('あ').intValue());
	}

	public void test02()
	{
		SamarutoriaMuunburukuName name =
			new SamarutoriaMuunburukuName("くらけ゛");

		assertEquals("パウロ", name.サマルトリアの王子の名前);
		assertEquals("サマンサ", name.ムーンブルクの王女の名前);
	}
}

package kumagai.Fukkatsu2.logictest;

public class GenerateJumonTest
	extends GenerateJumonTestCore
{
	public void test1()
	{
		String [] jumon = generate("ゆうてい", "みやおう", "きむこう", "ほりいゆうじ", "とりやまあきら");
		assertEquals(1, jumon.length);
		assertEquals("ゆうていみやおうきむこうほりいゆうじとりやまあきらぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺぺ", jumon[0]);
	}

	public void test2()
	{
		String [] jumon = generate("それゆけ", "とらくえ", "すいそうがく", "がつきたずさえ", "たびたて", "ゆうしやたち", "れべるいちらいぶ", "おめてとう");
		assertEquals(1, jumon.length);
		assertEquals("それゆけとらくえすいそうがくがつきたずさえたびたてゆうしやたちれべるいちらいぶおめてとうねねねね", jumon[0]);
	}
}

package kumagai.Fukkatsu2.logictest;

public class GenerateJumonTest
	extends GenerateJumonTestCore
{
	public void test1()
	{
		_test("ゆうていみやおうきむこうほりいゆうじとりやまあきら", false);
	}

	public void _test2()
	{
		_test("ゆうしやえそさつくすぶきにいまこそたびたつすいそうがくらいぶいくよ", false);
	}

	public void _test3()
	{
		_test("ゆうしやえそさつくすぶきにたびするすいそうがくらいぶいきたい", false);
	}

	public void _test4()
	{
		_test("おおゆうしやえそとらくえすいそうはくにてさつくすぶきにたびたつ", false);
	}

	public void _test5()
	{
		_test("ゆうしやえそさつくすぶきにたびたつすいそうがくらいぶへいこう", false);
	}

	public void _test6()
	{
		_test("おおゆうしやえそさつくすぶきにたびするすいそうがくらいぶいくぜ", false);
	}

	public void _test7()
	{
		_test("それゆけとらくえすいそうがくがつきぶきにたびたてゆうしやたちらいぶいくわ", false);
	}

	public void _test8()
	{
		_test("おおとらくえすいそう", false);
	}
}

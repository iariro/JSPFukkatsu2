package kumagai.Fukkatsu2.logic;

import java.util.*;

/**
 * 与えられた単語を組み合わせて２０文字の文字列を生成する。
 */
public class WordPatternGenerator
{
	/**
	 * 再帰呼び出しを使って単語を組み合わせて１８文字以上の文字列を生成する
	 * @param s1 文字列を足していく文字列変数
	 * @param list 単語一式 再帰呼び出しされるごとに減っていく
	 */
	public void generateRecursive(String s1, ArrayList<String> list)
	{
		boolean loop = true;
		ArrayList<String> list2;

		for (int i=0 ; i<list.size() && loop ; i++)
		{
			String s2 = list.get(i);

			if (s1.length() + s2.length() >= 18)
			{
				// 文字数は足りている。

				//loop = generated(s1 + s2);
			}

			if (list.size() > 1)
			{
				// 使用できる文字列はまだある。

				list2 = new ArrayList<String>(list);
				list2.remove(i);

				if (s1.length() < 20)
				{
					// まだ２０文字には達しない。

					generateRecursive(s1 + s2, list2);
				}
			}
		}
	}
}

package kumagai.Fukkatsu2.logic;

import java.util.ArrayList;

/**
 * 指定の文字列パターンの組み合わせを作成する。
 * @author kumagai
 */
public class WordPatternGenerator2
	extends ArrayList<String>
{
	/**
	 * 指定の文字列パターンの組み合わせを作成する。
	 * @param words 文字列パターン
	 */
	public WordPatternGenerator2(String [] words)
	{
		generateRecursive(words, 0, new String());
	}

	/**
	 * 指定の文字列パターンの組み合わせを再帰的に作成する。
	 * @param words 文字列パターン
	 * @param depth 深さ
	 * @param phrase フレーズ
	 */
	private void generateRecursive
		(String [] words, int depth, String phrase)
	{
		if (depth < words.length)
		{
			// 範囲内。

			for (String word : words[depth].split("\\|"))
			{
				generateRecursive(words, depth + 1, phrase + word);
			}
		}
		else
		{
			// 終端に達した。

			add(phrase);
		}
	}
}

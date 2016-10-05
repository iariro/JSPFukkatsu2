package kumagai.Fukkatsu2.logic;

import java.util.*;

/**
 * ビット配列基底部。
 */
public class JumonBitArray
	extends ArrayList<Boolean>
{
	/**
	 * 既定のコンストラクタ。
	 */
	public JumonBitArray()
	{
		// 何もしない。
	}

	/**
	 * 8ビットのうち上位6ビットからビット配列を構築。
	 * @param byteArray バイト配列
	 */
	public JumonBitArray(byte [] byteArray)
	{
		for (int i=0 ; i<byteArray.length ; i++)
		{
			for (int j=0 ; j<6 ; j++)
			{
				add((byteArray[i] & (1 << (5 - j))) > 0);
			}
		}
	}

	/**
	 * 指定のサイズのビット配列を構築。
	 * @param size ビットサイズ
	 */
	public JumonBitArray(int size)
	{
		for (int i=0 ; i<size ; i++)
		{
			add(false);
		}
	}

	/**
	 * 指定のビット配列のコピーを構築。
	 * @param array 元となるビット配列
	 */
	public JumonBitArray(ArrayList<Boolean> array)
	{
		super(array);
	}

	/**
	 * 指定部分をint値として取得。
	 * @param start 取得開始ビット位置
	 * @param bitsize ビットサイズ
	 * @return 取得したint値
	 */
	public int getAsInt(int start, int bitsize)
	{
		int ret = 0;

		for (int i=0 ; i<bitsize ; i++)
		{
			ret = (ret << 1) + (get(start + i) ? 1 : 0);
		}

		return ret;
	}

	/**
	 * 指定の位置に指定の値をセット。
	 * 値のセット開始位置は上位ビットで値のセット終端位置は下位ビットである
	 * ことに注意。
	 * @param startOnArray 配列中のセット開始位置
	 * @param value セットする値
	 * @param startOnValue 値のセット開始ビット位置
	 * @param endOnValue 値のセット終端ビット位置
	 */
	public void setInt
		(int startOnArray, int value, int startOnValue, int endOnValue)
	{
		for (int i=startOnValue ; i>=endOnValue ; i--)
		{
			set(startOnArray, (value & 1 << i) > 0);

			startOnArray++;
		}
	}

	/**
	 * 部分配列を取得。
	 * @param start 取得開始ビット位置
	 * @param bitsize 取得ビットサイズ
	 * @return 部分配列
	 */
	public JumonBitArray getPart(int start, int bitsize)
	{
		ArrayList<Boolean> part = new ArrayList<Boolean>();

		for (int i=0 ; i<bitsize ; i++)
		{
			part.add(get(start + i));
		}

		return new JumonBitArray(part);
	}

	/**
	 * 内容を文字列化して取得。デバッグ用。
	 * @return 文字列化した内容
	 */
	public String toString()
	{
		String ret = new String();

		for (int i=0 ; i<size() ; i++)
		{
			ret += get(i) ? 1 : 0;
		}

		return ret;
	}
}

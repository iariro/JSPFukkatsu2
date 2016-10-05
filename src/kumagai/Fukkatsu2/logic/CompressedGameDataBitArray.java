package kumagai.Fukkatsu2.logic;

/**
 * 最大312ビットに圧縮されたゲームデータビット配列。
 */
public class CompressedGameDataBitArray
	extends JumonBitArray
{
	/**
	 * 呪文コードを受けてビット配列を構築。
	 * @param byteArray 呪文コード
	 */
	public CompressedGameDataBitArray(byte [] byteArray)
	{
		super(byteArray);
	}

	/**
	 * ゲームデータビット配列を受けて、312文字以内に収まるようビット配列を
	 * 構築。
	 * @param array ゲームデータビット配列
	 */
	public CompressedGameDataBitArray(ExtendedGameDataBitArray array)
	{
		int size = array.size();
		boolean overflow = false;

		if (size == 314)
		{
			// 314ビットの配列が指定された。

			overflow = true;
			size = 312;
		}

		for (int i=0 ; i<size ; i++)
		{
			add(array.get(i));
		}

		if (overflow)
		{
			// オーバーした。

			set(64, array.get(312));
			set(65, array.get(313));
		}

		// チェックサム埋め込み。
		int checksum = getチェックサム２();
		setInt(0, checksum, 4, 0);
		setInt(66, checksum, 10, 5);
	}

	/**
	 * 埋め込まれたチェックサムを取得。
	 * @return チェックサム
	 */
	public int getチェックサム１()
	{
		return getAsInt(0, 5) + (getAsInt(66, 6) << 5);
	}

	/**
	 * チェックサムを算出。
	 * @return チェックサム
	 */
	public int getチェックサム２()
	{
		int n = size() / 8;

		// チェックサムを求める。
		int checksum = n * 257;

		for (int t=n-1 ; t>=0 ; t--)
		{
			int b = 0;

			for (int j=0 ; j<8 ; j++)
			{
				int index = t * 8 + j;

				if (index >= 0 && index <= 4 ||
					index >= 66 && index <= 71)
				{
					// チェックサム部分、つまりチェックサム計算非対象部
					// 分である。
				}
				else
				{
					// チェックサム計算対象部分である。

					b |= ((get(index) ? 1 : 0) << (8 - j - 1));
				}
			}

			for (int j=0 ; j<8 ; j++)
			{
				int d =
					((checksum & 0x8000) / 0x8000) +
					((b & 0x80) / 0x80);

				checksum = (checksum * 2) % 0x10000;
				b = (b * 2) % 0x0100;

				if (d == 1)
				{
					checksum ^= 4129;
				}
			}
		}

		return checksum % 0x0800;
	}

	/**
	 * 末尾の正当性チェック。
	 * @return true=正当
	 */
	public boolean isValidTerminate()
	{
		int charNum = size() / 6 - 1;

		if ((charNum & 0x3) < 3)
		{
			if (size() >= charNum * 6 + 6)
			{
				if (get(charNum * 6 + 4) | get(charNum * 6 + 5))
				{
					System.out.printf(
						"[%d]=%s [%d]=%s\n",
						charNum * 6 + 4,
						get(charNum * 6 + 4),
						charNum * 6 + 5,
						get(charNum * 6 + 5));

					return false;
				}
			}
		}

		if ((charNum & 0x3) < 2)
		{
			if (size() >= charNum * 6 + 4)
			{
				if (get(charNum * 6 + 2) | get(charNum * 6 + 3))
				{
					System.out.printf(
						"[%d]=%s [%d]=%s\n",
						charNum * 6 + 2,
						get(charNum * 6 + 2),
						charNum * 6 + 3,
						get(charNum * 6 + 3));

					return false;
				}
			}
		}

		if ((charNum & 0x3) == 0)
		{
			if (size() >= charNum * 6 + 2)
			{
				if (get(charNum * 6) | get(charNum * 6 + 1))
				{
					System.out.printf(
						"[%d]=%s [%d]=%s\n",
						charNum * 6 + 0,
						get(charNum * 6 + 0),
						charNum * 6 + 1,
						get(charNum * 6 + 1));

					return false;
				}
			}
		}

		return true;
	}

	/**
	 * 呪文コード生成。
	 * @return 呪文コード
	 */
	public byte [] getJumonCode()
	{
		byte [] ret;

		ret = new byte [size() / 6];

		int shift = getAsInt(3, 2) + 1;

		for (int i=0 ; i<ret.length ; i++)
		{
			ret[i] = (byte)getAsInt(6 * i, 6);

			if (i >= 1)
			{
				// ２文字目以降。

				ret[i] = (byte)((ret[i] + (ret[i - 1] + shift)) % 0x40);
			}
		}

		return ret;
	}

	/**
	 * ビット内容比較。
	 * @param compressed1 比較対象
	 */
	public void compare(CompressedGameDataBitArray compressed1)
	{
		for (int i=0 ; i<304 ; i++)
		{
			if (i < size() && i < compressed1.size())
			{
				// 範囲内。

				if (get(i) != compressed1.get(i))
				{
					// 値は異なる。

					System.out.printf(
						"%d:%s, %s\n",
						i,
						get(i),
						compressed1.get(i));
				}
			}
		}
	}
}

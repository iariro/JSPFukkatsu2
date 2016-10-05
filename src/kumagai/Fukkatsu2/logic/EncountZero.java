package kumagai.Fukkatsu2.logic;

/**
 * エンカウントゼロ呪文生成ロジック。
 */
public class EncountZero
{
	static private final int [] MCK =
		{
			11765,  5600, 47076, 47076, 26668,  1369, 18922, 18922, 16560, 46575,
			52350, 52350, 60483, 12623, 16173, 16173, 64486,  2791,  7758,  7758,
			64212, 57856, 58408, 58408, 24052, 11253,  2313,  2313, 51346,  8017,
			63711, 63711, 60379, 23045, 50245
		};

	static private final int [] HK =
		new int []
		{
			61622, 30811,     0,     0, 63788, 17412,  8706, 59693, 37105, 29813,
			 1591,  2945,  2426, 52262, 25139, 14723, 45815, 40282, 34358, 47927,
			20769, 55334, 26675, 49295, 20676, 58585, 20065, 59185, 31490, 49549,
			36976, 33923, 53408, 26704, 64691, 35535, 34150, 36360, 47880, 41352,
			53281, 39078, 18547,  7188,  2602, 63769, 34842, 16429, 55774, 42100,
			40577, 17402,  5482, 50702, 39723, 16687,     0,     0,     0, 52379,
			37595, 36172, 36413, 19364, 19083, 10751,     0,     0,     0,     0,
			    0,     0,  5271, 64253, 48511, 26258, 52069, 42419, 41583, 38166,
			62038, 19900,  8958,  9704, 56911, 44838, 43935, 22901, 20793, 55338,
			26677, 49292, 24646, 64664, 31340,  3457, 45101, 43168, 20592,
			58499, 33527, 34138, 36374, 47879,
			57046, 24540,  8025, 53165, 27500,  1281, 62006, 33079, 41622, 43367,
			27806, 12911, 56598, 27307, 14831,  9434,   409, 61562, 46214, 23107,
			60704, 29360, 49524, 44033, 34907, 31744, 14880, 57628, 28814, 14407,
			 9230,  4615, 38905, 36829, 34799, 34774, 30588,  3881,  2862, 63899,
			 4449, 13501, 60136, 17859,  7916, 16321, 57313, 45041,  7126, 15708,
			54837, 25472, 51692, 44141, 28187, 15287, 19564, 61101, 31724,  3393,
			16013,  5116, 15721,  9881, 46478, 23239, 60770, 48682, 41753, 22806,
			10411, 57539, 39302, 18659,  7260, 50869, 27584,  1367, 16038, 58207,
			43771, 38268, 34309, 19352, 55744, 42107, 28208, 51988, 55345, 39054,
			18535,  7198,  2607,  2477, 61504, 46235,   911,  2397, 15491, 60119,
			46442, 38414, 45867, 20751,
			27209, 62757, 35364, 47390, 22703, 55489, 21581,  5675, 29593, 12630,
			54288, 27144, 51464, 24740, 12370, 54418, 65509, 49139, 40952, 32587,
			65444, 33758, 30072, 62983, 52280, 25148, 51506, 24761, 49386, 44238,
			40700, 32713, 54025, 24862, 12431, 59633, 19541,  7687,   953,  2374,
			15980, 12161,  7034, 15626,  6821,   488, 52303, 24074, 49712, 39220,
			18618,  8317, 11315, 57999, 31197,  1219, 49735, 42242, 21121,  8666,
			56406, 42672, 43892, 25869, 26771, 49375, 23650, 59050, 35705, 34237,
			45672, 28067, 64907, 35411, 34088, 17044, 55654, 42024, 21012, 53542,
			36307, 32452,  4053, 51179, 42964, 26493, 63391, 29525,  7552,  2784,
			13767,  9966, 10208, 10087, 55186, 37861, 33420, 47466, 37934, 18967,
			10673, 57422, 48284, 23150
		};

	/**
	 * エンカウントゼロ呪文生成用にバリエーション値とゴールドを加工。
	 * @param gamedata 対象ゲームデータ
	 * @return 加工済みデータ
	 */
	static public CompressedGameDataBitArray getEncountZero(GameData gamedata)
		throws IllegalCharacterException
	{
		int variation = (gamedata.バリエーション & 0x1) + 10;
		int gold = gamedata.ゴールド;

		ExtendedGameDataBitArray extended =
			new ExtendedGameDataBitArray(gamedata);
		CompressedGameDataBitArray compressed =
			new CompressedGameDataBitArray(extended);

		compressed.set(0, true);
		compressed.set(1, false);
		compressed.set(2, false);
		compressed.set(3, false);
		compressed.set(4, true);

		compressed.setInt(56, 0, 2, 0);
		compressed.setInt(66, 0, 4, 0);

		boolean goldDirection = false;

		int inc = gold;
		int dec = gold - 1;

		for (int count=0; count<100000 ; count++)
		{
			compressed.setInt(32, gold, 7, 0);
			compressed.setInt(16, gold, 15, 8);

			compressed.set(48, (variation & 0x1) > 0);

			int checksum = EncountZero.MCK[compressed.size() / 6 - 18];

			for (int i=0 ; i<compressed.size() ; i++)
			{
				checksum ^= (EncountZero.HK[i] * (compressed.get(i) ? 1 : 0));
			}

			if ((checksum & 0xfe3f) != 0xac3f)
			{
				// エンカウントゼロ条件に合致しない。

				variation = 21 - variation;

				if (variation == 11)
				{
					if (goldDirection)
					{
						// 増やす番。

						if (gold < 65535)
						{
							// 範囲内。

							gold = inc;
							inc++;
						}
						goldDirection = false;
					}
					else
					{
						// 減らす番。

						if (gold > 0)
						{
							// 範囲内。

							gold = dec;
							dec--;
						}
						else
						{
							//gold = 65535;
						}
						goldDirection = true;
					}
				}
			}
			else
			{
				// エンカウントゼロ条件に合致した。

				compressed.set( 2, (checksum & 0x400) > 0);
				compressed.set( 3, (checksum & 0x200) > 0);
				compressed.set(56, (checksum & 0x100) > 0);
				compressed.set(57, (checksum & 0x80) > 0);
				compressed.set(58, (checksum & 0x40) > 0);
				compressed.set(66, (checksum & 0x20) > 0);
				compressed.set(67, (checksum & 0x10) > 0);
				compressed.set(68, (checksum & 0x8) > 0);
				compressed.set(69, (checksum & 0x4) > 0);
				compressed.set(70, (checksum & 0x2) > 0);
				compressed.set(71, (checksum & 0x1) > 0);

				return compressed;
			}
		}

		return null;
	}
}

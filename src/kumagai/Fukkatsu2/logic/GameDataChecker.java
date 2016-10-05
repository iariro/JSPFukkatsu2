package kumagai.Fukkatsu2.logic;

import java.util.*;

/**
 * ゲームデータチェックオブジェクト。
 * @author kumagai
 */
public interface GameDataChecker
{
	static public final GameDataChecker [] checkers =
	{
		new SavePointChecker(),
		new GoldChecker(),
		new VariationChecker(),
		new ExperienceChecker(0),
		new ExperienceChecker(1),
		new ExperienceChecker(2),
		new SamarutoriaFlagChecker(),
		new 月のかけらChecker(),
		new 水門Checker(),
		new 水のはごろもChecker(),
		new 船Checker(),
		new 少女Checker(),
		new 命の紋章Checker(),
		new 水の紋章Checker(),
		new 月の紋章Checker(),
		new 星の紋章Checker(),
		new 太陽の紋章Checker(),
		new アイテム入手Checker(),
		new 没アイテムChecker()
	};

	/**
	 * セーブポイントチェック処理。
	 * @author kumagai
	 */
	class SavePointChecker
		implements GameDataChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.logic.GameDataChecker#checkAndCorrect(kumagai.Fukkatsu2.logic.GameData)
		 */
		public String[] checkAndCorrect(GameData gameData)
		{
			String savepoint =
				SavePoint.values()[gameData.セーブポイント].name();

			switch (gameData.セーブポイント)
			{
				case 2:
				case 3:
				case 4:
				case 5:
					if (gameData.playerCollection.size() >= 3)
					{
						// ３人揃っている。

						if (! gameData.船)
						{
							// 船を手に入れていない。

							return
								new String []
								{
									String.format(
										"船を手に入れていないのに%sには行けないはずです。",
									savepoint)
								};
						}
					}
					else
					{
						// ３人揃っていない。

						return
							new String []
							{
								String.format(
									"３人揃っていないのに%sには行けないはずです。",
									savepoint)
							};
					}

					if (gameData.セーブポイント == 5)
					{
						// ロンダルキアの場合。

						if (! gameData.船)
						{
							// 船を手に入れていない。

							return
								new String []
								{
									String.format(
										"船を手に入れずに%sには行けないはずです。",
										savepoint)
								};
						}

						if (! gameData.月のかけら)
						{
							// 月のかけらを使っていない。

							return
								new String []
								{
									String.format(
										"月のかけらを使わずに%sには行けないはずです。",
										savepoint)
								};
						}
					}
					break;

				case 6:
					if (gameData.playerCollection.size() <= 1)
					{
						// １人しかいない。

						return
							new String []
							{
								String.format(
									"１人で%sには行けないはずです。",
									savepoint)
							};
					}
					break;
			}

			return null;
		}
	}

	/**
	 * ゴールドチェック処理。
	 * @author kumagai
	 */
	class GoldChecker
		implements GameDataChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.logic.GameDataChecker#checkAndCorrect(kumagai.Fukkatsu2.logic.GameData)
		 */
		public String[] checkAndCorrect(GameData gameData)
		{
			if (gameData.ゴールド < 0 || gameData.ゴールド > 65535)
			{
				// ゴールドが範囲外。

				int before = gameData.ゴールド;

				if (gameData.ゴールド < 0)
				{
					// ゴールドが負の値。

					gameData.ゴールド = 0;
				}

				if (gameData.ゴールド > 65535)
				{
					// ゴールドが限界を越えている。

					gameData.ゴールド = 65535;
				}

				return
					new String []
					{
						String.format(
							"ゴールド%dが範囲外です。%dに調整しました。",
							before,
							gameData.ゴールド)
					};
			}

			return null;
		}
	}

	/**
	 * バリエーションチェック処理。
	 * @author kumagai
	 */
	class VariationChecker
		implements GameDataChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.logic.GameDataChecker#checkAndCorrect(kumagai.Fukkatsu2.logic.GameData)
		 */
		public String[] checkAndCorrect(GameData gameData)
		{
			if (gameData.バリエーション < 0 || gameData.バリエーション > 15)
			{
				// バリエーションが範囲外。

				int before = gameData.バリエーション;

				if (gameData.バリエーション < 0)
				{
					// バリエーションが負の値。

					gameData.バリエーション = 0;
				}

				if (gameData.バリエーション > 15)
				{
					// バリエーションが限界を越えている。

					gameData.バリエーション = 15;
				}

				return
					new String []
					{
						String.format(
							"バリエーション%dが範囲外です。%dに調整しました。",
							before,
							gameData.バリエーション)
					};
			}

			return null;
		}
	}

	/**
	 * 経験値チェック処理。
	 * @author kumagai
	 */
	class ExperienceChecker
		implements GameDataChecker
	{
		private final int index;

		/**
		 * メンバに値を割り当て。
		 * @param index プレイヤーインデックス
		 */
		ExperienceChecker(int index)
		{
			this.index = index;
		}

		/**
		 * @see kumagai.Fukkatsu2.logic.GameDataChecker#checkAndCorrect(kumagai.Fukkatsu2.logic.GameData)
		 */
		public String[] checkAndCorrect(GameData gameData)
		{
			if (index < gameData.playerCollection.size())
			{
				// 対象のプレイヤーは存在する。

				Player player = gameData.playerCollection.get(index);

				if (player.経験値 < 0 || player.経験値 > 1000000)
				{
					// 経験値が範囲外。

					int before = player.経験値;

					if (player.経験値 < 0)
					{
						// 経験値が負の値。

						player.経験値 = 0;
					}

					if (player.経験値 > 1000000)
					{
						// 経験値が限界を越えている。

						player.経験値 = 1000000;
					}

					String name;

					switch (index)
					{
						case 0:
							name = "ローレシア";
							break;
						case 1:
							name = "サマルトリア";
							break;
						default:
							name = "ムーンブルク";
							break;
					}

					return
						new String []
						{
							String.format(
								"%sの経験値%dが範囲外です。%dに調整しました。",
								name,
								before,
								player.経験値)
						};
				}

				return null;
			}
			else
			{
				// 対象のプレイヤーは存在しない。

				return null;
			}
		}
	}

	/**
	 * サマルトリアの王子のフラグチェック。
	 * @author kumagai
	 */
	class SamarutoriaFlagChecker
		implements GameDataChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.logic.GameDataChecker#checkAndCorrect(kumagai.Fukkatsu2.logic.GameData)
		 */
		public String[] checkAndCorrect(GameData gameData)
		{
			if (gameData.playerCollection.size() >= 2)
			{
				// サマルトリアの王子は加わっている。

				if (gameData.サマルトリア < 3)
				{
					// ローレシアの王の話を聞いていない。

					return
						new String []
						{
							"サマルトリアの王子と出会う段階を経ていません。"
						};
				}
			}

			return null;
		}
	}

	/**
	 * 月のかけらチェック。
	 * @author kumagai
	 */
	class 月のかけらChecker
		implements GameDataChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.logic.GameDataChecker#checkAndCorrect(kumagai.Fukkatsu2.logic.GameData)
		 */
		public String[] checkAndCorrect(GameData gameData)
		{
			if (gameData.月のかけら)
			{
				// 月のかけらを使った状態。

				if (gameData.playerCollection.size() >= 3)
				{
					// プレイヤーは３人いる。

					if (! gameData.船)
					{
						// 船を手に入れていない。

						return new String [] { "船を手に入れずに月のかけらは手に入れられないはずです。" };
					}

					if (! gameData.水門)
					{
						// 水門を開けていない。

						return new String [] { "水門を開けずに月のかけらは手に入れられないはずです。" };
					}
				}
				else
				{
					// プレイヤーは３人いない。

					return new String [] { "３人揃っていないのに月のかけらは手に入れられないはずです。" };
				}
			}

			return null;
		}
	}

	/**
	 * 水門チェック。
	 * @author kumagai
	 */
	class 水門Checker
		implements GameDataChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.logic.GameDataChecker#checkAndCorrect(kumagai.Fukkatsu2.logic.GameData)
		 */
		public String[] checkAndCorrect(GameData gameData)
		{
			if (gameData.水門)
			{
				// 水門を開けている。

				if (gameData.playerCollection.size() >= 3)
				{
					// プレイヤーは３人いる。

					if (! gameData.船)
					{
						// 船を手に入れずに水門を開けている。

						return new String [] { "船を手に入れずに水門を開ける場所まで行けないはずです。" };
					}
				}
				else
				{
					// プレイヤーは３人いない。

					return new String [] { "３人揃っていないのに水門を開ける場所まで行けないはずです。" };
				}
			}

			return null;
		}
	}

	/**
	 * 水のはごろもチェック。
	 * @author kumagai
	 */
	class 水のはごろもChecker
		implements GameDataChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.logic.GameDataChecker#checkAndCorrect(kumagai.Fukkatsu2.logic.GameData)
		 */
		public String[] checkAndCorrect(GameData gameData)
		{
			if (gameData.水のはごろも)
			{
				// 水のはごろもを作成中である。

				if (gameData.playerCollection.size() >= 3)
				{
					// プレイヤーは３人いる。

					if (! gameData.船)
					{
						// 船を手に入れていない。

						return new String [] { "船を手に入れずに水のはごろもを作ってもらえる場所まで行けないはずです。" };
					}
				}
				else
				{
					// プレイヤーは３人いない。

					return new String [] { "３人揃っていないのに水のはごろもを作ってもらえる場所まで行けないはずです。" };
				}
			}

			return null;
		}
	}

	/**
	 * 船チェック。
	 * @author kumagai
	 */
	class 船Checker
		implements GameDataChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.logic.GameDataChecker#checkAndCorrect(kumagai.Fukkatsu2.logic.GameData)
		 */
		public String[] checkAndCorrect(GameData gameData)
		{
			if (gameData.船)
			{
				// 船を手に入れている。

				if (gameData.playerCollection.size() >= 3)
				{
					// プレイヤーは３人いる。

					if (! gameData.少女)
					{
						// 少女を助けていない。

						return new String [] { "少女を助けずに船を手に入れられないはずです。" };
					}
				}
				else
				{
					// プレイヤーは３人いない。

					return new String [] { "３人揃っていないのに船を手に入れる場所まで行けないはずです。" };
				}
			}

			return null;
		}
	}

	/**
	 * 少女チェック。
	 * @author kumagai
	 */
	class 少女Checker
		implements GameDataChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.logic.GameDataChecker#checkAndCorrect(kumagai.Fukkatsu2.logic.GameData)
		 */
		public String[] checkAndCorrect(GameData gameData)
		{
			if (gameData.少女)
			{
				// 少女を助けている。

				if (gameData.playerCollection.size() < 3)
				{
					// プレイヤーは３人いない。

					return new String [] { "３人揃っていないのに少女を助ける場所まで行けないはずです。" };
				}
			}

			return null;
		}
	}

	/**
	 * 命の紋章チェック。
	 * @author kumagai
	 */
	class 命の紋章Checker
		implements GameDataChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.logic.GameDataChecker#checkAndCorrect(kumagai.Fukkatsu2.logic.GameData)
		 */
		public String[] checkAndCorrect(GameData gameData)
		{
			if (gameData.命の紋章)
			{
				// 命の紋章を手に入れている。

				if (gameData.playerCollection.size() >= 3)
				{
					// プレイヤーは３人いる。

					if (! gameData.船)
					{
						// 船を手に入れずに命の紋章を手に入れている。

						return new String [] { "船を手に入れずに命の紋章を手に入れられないはずです。" };
					}

					if (! gameData.月のかけら)
					{
						// 月のかけらを使わずに命の紋章を手に入れている。

						return new String [] { "月のかけらを使わずに命の紋章を手に入れる場所まで行けないはずです。" };
					}

					if (! gameData.船)
					{
						// 船を手に入れずに命の紋章を手に入れている。

						return new String [] { "船を手に入れずに命の紋章を手に入れられないはずです。" };
					}
				}
				else
				{
					// プレイヤーは３人いない。

					return new String [] { "３人揃っていないのに命の紋章を手に入れられないはずです。" };
				}
			}

			return null;
		}
	}

	/**
	 * 水の紋章チェック。
	 * @author kumagai
	 */
	class 水の紋章Checker
		implements GameDataChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.logic.GameDataChecker#checkAndCorrect(kumagai.Fukkatsu2.logic.GameData)
		 */
		public String[] checkAndCorrect(GameData gameData)
		{
			if (gameData.水の紋章)
			{
				// 水の紋章を手に入れている。

				if (gameData.playerCollection.size() >= 3)
				{
					// プレイヤーは３人いる。

					if (! gameData.船)
					{
						// 船を手に入れずに水の紋章を手に入れている。

						return new String [] { "船を手に入れずに水の紋章を手に入れられないはずです。" };
					}
				}
				else
				{
					// プレイヤーは３人いない。

					return new String [] { "３人揃っていないのに水の紋章を手に入れられないはずです。" };
				}
			}

			return null;
		}
	}

	/**
	 * 月の紋章チェック。
	 * @author kumagai
	 */
	class 月の紋章Checker
		implements GameDataChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.logic.GameDataChecker#checkAndCorrect(kumagai.Fukkatsu2.logic.GameData)
		 */
		public String[] checkAndCorrect(GameData gameData)
		{
			if (gameData.月の紋章)
			{
				// 月の紋章を手に入れている。

				if (gameData.playerCollection.size() >= 3)
				{
					// プレイヤーは３人いる。

					if (! gameData.船)
					{
						// 船を手に入れずに月の紋章を手に入れている。

						return new String [] { "船を手に入れずに月の紋章を手に入れられないはずです。" };
					}
				}
				else
				{
					// プレイヤーは３人いない。

					return new String [] { "３人揃っていないのに月の紋章を手に入れられないはずです。" };
				}
			}

			return null;
		}
	}

	/**
	 * 星の紋章チェック。
	 * @author kumagai
	 */
	class 星の紋章Checker
		implements GameDataChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.logic.GameDataChecker#checkAndCorrect(kumagai.Fukkatsu2.logic.GameData)
		 */
		public String[] checkAndCorrect(GameData gameData)
		{
			if (gameData.星の紋章)
			{
				// 星の紋章を手に入れている。

				if (gameData.playerCollection.size() >= 3)
				{
					// プレイヤーは３人いる。

					if (! gameData.船)
					{
						// 船を手に入れずに星の紋章を手に入れている。

						return new String [] { "船を手に入れずに星の紋章を手に入れられないはずです。" };
					}
				}
				else
				{
					// プレイヤーは３人いない。

					return new String [] { "３人揃っていないのに星の紋章を手に入れられないはずです。" };
				}
			}

			return null;
		}
	}

	/**
	 * 太陽の紋章チェック。
	 * @author kumagai
	 */
	class 太陽の紋章Checker
		implements GameDataChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.logic.GameDataChecker#checkAndCorrect(kumagai.Fukkatsu2.logic.GameData)
		 */
		public String[] checkAndCorrect(GameData gameData)
		{
			if (gameData.太陽の紋章)
			{
				// 太陽の紋章を手に入れている。

				if (gameData.playerCollection.size() >= 3)
				{
					// プレイヤーは３人いる。

					if (! gameData.船)
					{
						// 船を手に入れずに太陽の紋章を手に入れている。

						return new String [] { "船を手に入れずに太陽の紋章を手に入れられないはずです。" };
					}
				}
				else
				{
					// プレイヤーは３人いない。

					return new String [] { "３人揃っていないのに太陽の紋章を手に入れられないはずです。" };
				}
			}

			return null;
		}
	}

	/**
	 * 没アイテムチェック。
	 * @author kumagai
	 */
	class 没アイテムChecker
		implements GameDataChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.logic.GameDataChecker#checkAndCorrect(kumagai.Fukkatsu2.logic.GameData)
		 */
		public String[] checkAndCorrect(GameData gameData)
		{
			for (Player player : gameData.playerCollection)
			{
				for (ItemAndEquipment item : player.itemCollection)
				{
					if (item.item.name.equals("みみせん") ||
						item.item.name.equals("しのオルゴール"))
					{
						// 没アイテムである。

						return new String [] { "没アイテムが指定されています。" };
					}
				}
			}

			return null;
		}
	}

	/**
	 * アイテム入手チェック。
	 * @author kumagai
	 */
	class アイテム入手Checker
		implements GameDataChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.logic.GameDataChecker#checkAndCorrect(kumagai.Fukkatsu2.logic.GameData)
		 */
		public String[] checkAndCorrect(GameData gameData)
		{
			int getLevel = 0;

			if (gameData.playerCollection.size() >= 2)
			{
				// ２人いる。

				getLevel = 1;

				if (gameData.playerCollection.size() >= 3)
				{
					// ３人揃っている。

					getLevel = 2;

					if (gameData.船)
					{
						// 船を手に入れている。

						getLevel = 3;

						if (gameData.月のかけら)
						{
							// 月のかけらを使っている。

							getLevel = 4;
						}
					}
				}
			}

			ArrayList<String> errors = new ArrayList<String>();

			for (Player player : gameData.playerCollection)
			{
				for (ItemAndEquipment item : player.itemCollection)
				{
					if (item.item.getLevel > getLevel)
					{
						// 入手できないはずのアイテムである。

						String condition = null;

						switch (item.item.getLevel)
						{
							case 1:
								condition = "２人";
								break;

							case 2:
								condition = "３人揃って";
								break;

							case 3:
								condition = "船を手に入れて";
								break;

							case 4:
								condition = "月のかけらを使って";
								break;
						}

						errors.add(
							String.format(
								"%sは%sいない状態では入手できないはずです。",
								item.item.name,
								condition));
					}

					if (item.item.name.equals("ルビスのまもり"))
					{
						// ルビスのまもりである。

						if (! (gameData.命の紋章 &&
							gameData.水の紋章 &&
							gameData.月の紋章 &&
							gameData.星の紋章 &&
							gameData.太陽の紋章))
						{
							// 紋章が揃っていない。

							errors.add("紋章を揃えずにルビスのまもりを手に入れられないはずです。");
						}
					}
				}
			}

			return (String [])errors.toArray(new String [] {});
		}
	}

	/**
	 * チェックと訂正。
	 * @param gameData ゲームデータ
	 * @return エラーメッセージ
	 */
	String[] checkAndCorrect(GameData gameData);
}

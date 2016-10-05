package kumagai.Fukkatsu2.struts2;

/**
 * 復活の呪文生成アクション用フォームのチェックオブジェクト。
 * @author kumagai
 */
public interface GenerateJumonFormChecker
{
	static public final GenerateJumonFormChecker [] checkers =
	{
		new GoldChecker(),
		new VariationChecker(),
		new MuunburukuJoinChecker(),
		new RooreshiaNameChecker(),
		new RooreshiaExperienceChecker(),
		new SamarutoriaExperienceChecker(),
		new MuunburukuExperienceChecker(),
		new JumonStyleChecker()
	};

	/**
	 * ゴールド欄チェッカー。
	 * @author kumagai
	 */
	class GoldChecker
		implements GenerateJumonFormChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.struts2.GenerateJumonFormChecker#checkAndCorrect(kumagai.Fukkatsu2.struts2.GenerateJumon2Action)
		 */
		public String checkAndCorrect(GenerateJumon2Action form)
		{
			try
			{
				Integer.valueOf(form.gold);
			}
			catch (NumberFormatException exception)
			{
				String before = form.gold;

				form.gold = "0";

				return
					String.format(
						"ゴールド%sが不正です。0を割り当てました。",
						before);
			}

			return null;
		}
	}

	/**
	 * バリエーション値チェッカー。
	 * @author kumagai
	 */
	class VariationChecker
		implements GenerateJumonFormChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.struts2.GenerateJumonFormChecker#checkAndCorrect(kumagai.Fukkatsu2.struts2.GenerateJumon2Action)
		 */
		public String checkAndCorrect(GenerateJumon2Action form)
		{
			try
			{
				Integer.valueOf(form.variation);
			}
			catch (NumberFormatException exception)
			{
				String before = form.variation;

				form.variation = "0";

				return
					String.format(
						"バリエーション%sが不正です。0を割り当てました。",
						before);
			}

			return null;
		}
	}

	/**
	 * ムーンブルクの王女の不正な参加チェック。
	 * @author kumagai
	 */
	class MuunburukuJoinChecker
		implements GenerateJumonFormChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.struts2.GenerateJumonFormChecker#checkAndCorrect(kumagai.Fukkatsu2.struts2.GenerateJumon2Action)
		 */
		public String checkAndCorrect(GenerateJumon2Action form)
		{
			if (form.samarutoriaJoin == null)
			{
				// サマルトリアの王子は加わっていない。

				if (form.muunburukuJoin != null)
				{
					// ムーンブルクの王女は加わっている。

					return "サマルトリアの王子がいないのにムーンブルクの王女がいます。ローレシアの王子のみとします。";
				}
			}

			return null;
		}
	}

	/**
	 * ローレシアの王子の名前チェック。
	 * @author kumagai
	 */
	class RooreshiaNameChecker
		implements GenerateJumonFormChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.struts2.GenerateJumonFormChecker#checkAndCorrect(kumagai.Fukkatsu2.struts2.GenerateJumon2Action)
		 */
		public String checkAndCorrect(GenerateJumon2Action form)
		{
			if (form.rooreshiaName.length() > 4)
			{
				// 名前が長過ぎる。

				String before = form.rooreshiaName;

				form.rooreshiaName = before.substring(0, 4);

				return
					String.format(
						"ローレシアの王子の名前%sが不正です。%sを割り当てました。",
						before,
						form.rooreshiaName);
			}

			return null;
		}
	}

	/**
	 * ローレシアの王子の経験値チェック。
	 * @author kumagai
	 */
	class RooreshiaExperienceChecker
		implements GenerateJumonFormChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.struts2.GenerateJumonFormChecker#checkAndCorrect(kumagai.Fukkatsu2.struts2.GenerateJumon2Action)
		 */
		public String checkAndCorrect(GenerateJumon2Action form)
		{
			try
			{
				Integer.valueOf(form.rooreshiaExperience);
			}
			catch (NumberFormatException exception)
			{
				String before = form.rooreshiaExperience;

				form.rooreshiaExperience = "0";

				return
					String.format(
						"ローレシアの王子の経験値%sが不正です。0を割り当てました。",
						before);
			}

			return null;
		}
	}

	/**
	 * サマルトリアの王子の経験値チェック。
	 * @author kumagai
	 */
	class SamarutoriaExperienceChecker
		implements GenerateJumonFormChecker
	{
		public String checkAndCorrect(GenerateJumon2Action form)
		{
			try
			{
				Integer.valueOf(form.samarutoriaExperience);
			}
			catch (NumberFormatException exception)
			{
				String before = form.samarutoriaExperience;

				form.samarutoriaExperience = "0";

				return
					String.format(
						"サマルトリアの王子の経験値%sが不正です。0を割り当てました。",
						before);
			}

			return null;
		}
	}

	/**
	 * ムーンブルクの王女の経験値チェック。
	 * @author kumagai
	 */
	class MuunburukuExperienceChecker
		implements GenerateJumonFormChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.struts2.GenerateJumonFormChecker#checkAndCorrect(kumagai.Fukkatsu2.struts2.GenerateJumon2Action)
		 */
		public String checkAndCorrect(GenerateJumon2Action form)
		{
			try
			{
				Integer.valueOf(form.muunburukuExperience);
			}
			catch (NumberFormatException exception)
			{
				String before = form.muunburukuExperience;

				form.muunburukuExperience = "0";

				return
					String.format(
						"ムーンブルクの王女の経験値%sが不正です。0を割り当てました。",
						before);
			}

			return null;
		}
	}

	/**
	 * 出力形式チェック。
	 * @author kumagai
	 */
	class JumonStyleChecker
		implements GenerateJumonFormChecker
	{
		/**
		 * @see kumagai.Fukkatsu2.struts2.GenerateJumonFormChecker#checkAndCorrect(kumagai.Fukkatsu2.struts2.GenerateJumon2Action)
		 */
		public String checkAndCorrect(GenerateJumon2Action form)
		{
			switch (form.jumonStyle)
			{
				case 3: // 老人のせりふ
					if (form.samarutoriaJoin == null)
					{
						// サマルトリアの王子は加わっていない。

						return "サマルトリアの王子が加わっていない状態で老人に復活の呪文は教えてもらえないはずです。";
					}

					break;

				case 4: // ふっかつのたま
					if (form.tsukinokakera == null)
					{
						// 月のかけら使っていない。

						return "月のかけらを使わずにふっかつのたまは手に入らないはずです。ふっかつのたまを持っているはぐれメタルと戦うためには少なくとも月のかけらを必要とする月の塔に行く必要があるためです。";
					}
					break;
			}

			return null;
		}
	}

	/**
	 * チェックと訂正処理。
	 * @param form フォームオブジェクト
	 * @return エラーメッセージ文字列
	 */
	String checkAndCorrect(GenerateJumon2Action form);
}

<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ドラゴンクエストII 復活の呪文</title>
	<link rel="stylesheet" type="text/css" href="hatena.css">
</head>

<body>
	<h1>ドラゴンクエストII 復活の呪文</h1>

	<div class=hatena-body>
	<div class=main>

		<div class=day>

			<s:form action="generatejumon2" theme="simple">

			<h2><span class=title>共通</span></h2>
			<div class=body>
			<div class=section>

				セーブポイント：
				<s:select name="savepoint" list="savepoints" listKey="index" listValue="name" />
				<br>

				ゴールド：
				<input type="text" name="gold" value="0">
				<br>

				バリエーション：
				<input type="text" name="variation" value="0">
				<br>

			</div>
			</div>

			<h2><span class=title>イベント</span></h2>
			<div class=body>
			<div class=section>

			<input type="checkbox" name="tsukinokakera">月のかけらを使った
			<input type="checkbox" name="suimon">水門を開けた
			<input type="checkbox" name="hagoromo">水のはごろも作成中
			<input type="checkbox" name="ship">船入手
			<input type="checkbox" name="girl">少女を助けた
			<br>
			<input type="checkbox" name="monshouInochi">命の紋章
			<input type="checkbox" name="monshouMizu">水の紋章
			<input type="checkbox" name="monshouTsuki">月の紋章
			<input type="checkbox" name="monshouHoshi">星の紋章
			<input type="checkbox" name="monshouTaiyou">太陽の紋章
			<br>

			サマルトリアの王子の状態：
			<s:select name="samarutoriaFlag" list="#{ '0':'初期状態','1':'サマルトリアの王の話を聞く','2':'勇者の泉で話を聞く','3':'ローレシアの王の話を聞く' }"/>

			</div>
			</div>

			<h2><span class=title>ローレシアの王子</span></h2>
			<div class=body>
			<div class=section>

			<input type="checkbox" name="rooreshiaJoin" disabled="true" checked="checked" />仲間
			<br>

			名前：<input type="text" name="rooreshiaName" value="とんぬら"><br>
			経験値：<input type="text" name="rooreshiaExperience" value="0" align="right"><br>

			アイテム：<br>
			<s:select name="rooreshiaItem1" list="rooreshiaItems.items" listKey="index" listValue="name" />
			<s:select name="rooreshiaItem2" list="rooreshiaItems.items" listKey="index" listValue="name" />
			<s:select name="rooreshiaItem3" list="rooreshiaItems.items" listKey="index" listValue="name" />
			<s:select name="rooreshiaItem4" list="rooreshiaItems.items" listKey="index" listValue="name" />

			<br>

			<s:select name="rooreshiaItem5" list="rooreshiaItems.items" listKey="index" listValue="name" />
			<s:select name="rooreshiaItem6" list="rooreshiaItems.items" listKey="index" listValue="name" />
			<s:select name="rooreshiaItem7" list="rooreshiaItems.items" listKey="index" listValue="name" />
			<s:select name="rooreshiaItem8" list="rooreshiaItems.items" listKey="index" listValue="name" />

			</div>
			</div>

			<h2><span class=title>サマルトリアの王子</span></h2>
			<div class=body>
			<div class=section>

			<input type="checkbox" name="samarutoriaJoin" />仲間<br>
			経験値：<input type="text" name="samarutoriaExperience" value="0"><br>

			アイテム：<br>
			<s:select name="samarutoriaItem1" list="samarutoriaItems.items" listKey="index" listValue="name" />
			<s:select name="samarutoriaItem2" list="samarutoriaItems.items" listKey="index" listValue="name" />
			<s:select name="samarutoriaItem3" list="samarutoriaItems.items" listKey="index" listValue="name" />
			<s:select name="samarutoriaItem4" list="samarutoriaItems.items" listKey="index" listValue="name" />

			<br>

			<s:select name="samarutoriaItem5" list="samarutoriaItems.items" listKey="index" listValue="name" />
			<s:select name="samarutoriaItem6" list="samarutoriaItems.items" listKey="index" listValue="name" />
			<s:select name="samarutoriaItem7" list="samarutoriaItems.items" listKey="index" listValue="name" />
			<s:select name="samarutoriaItem8" list="samarutoriaItems.items" listKey="index" listValue="name" />

			</div>
			</div>

			<h2><span class=title>ムーンブルクの王女</span></h2>
			<div class=body>
			<div class=section>

			<input type="checkbox" name="muunburukuJoin" />仲間<br>
			経験値：<input type="text" name="muunburukuExperience" value="0"><br>

			アイテム：<br>
			<s:select name="muunburukuItem1" list="muunburukuItems.items" listKey="index" listValue="name" />
			<s:select name="muunburukuItem2" list="muunburukuItems.items" listKey="index" listValue="name" />
			<s:select name="muunburukuItem3" list="muunburukuItems.items" listKey="index" listValue="name" />
			<s:select name="muunburukuItem4" list="muunburukuItems.items" listKey="index" listValue="name" />

			<br>

			<s:select name="muunburukuItem5" list="muunburukuItems.items" listKey="index" listValue="name" />
			<s:select name="muunburukuItem6" list="muunburukuItems.items" listKey="index" listValue="name" />
			<s:select name="muunburukuItem7" list="muunburukuItems.items" listKey="index" listValue="name" />
			<s:select name="muunburukuItem8" list="muunburukuItems.items" listKey="index" listValue="name" />

			</div>
			</div>

			<h2><span class=title>出力形式</span></h2>
			<div class=body>
			<div class=section>

			<s:select name="jumonStyle" list="#{ '0':'呪文のみ','1':'ローレシア王','2':'サマルトリア王','3':'老人のせりふ','4':'ふっかつのたま' }"/>

			</div>
			</div>

			<h2><span class=title>細工</span></h2>
			<div class=body>
			<div class=section>

			<input type="checkbox" name="encountZero" />エンカウントゼロ

			</div>
			</div>

			<s:submit value="生成" />

			</s:form>

		</div>

	</div>
	</div>
</body>
</html>

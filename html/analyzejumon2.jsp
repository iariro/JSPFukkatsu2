<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<title>ドラゴンクエストII 復活の呪文</title>
		<link rel="stylesheet" type="text/css" href="hatena.css">
	</head>

	<body>
		<h1>ドラゴンクエストII 復活の呪文</h1>

		<div class=hatena-body>
		<div class=main>
		<div class=day>

			<h2>呪文</h2>
			<blockquote>
			<s:iterator value="jumonLines">
				<s:property /><br>
			</s:iterator>
			<br>
			<img src="/kumagai/fukkatsu2jumonbmp?hexdata=<s:property value='hexdata' />">

			<br>
			<button onclick="obj=document.getElementById('legend').style; obj.display=(obj.display=='none')?'block':'none';">凡例</button>
			<div id="legend" style="display:none;background:#eeeeee;width:400px;border-style:dashed;border-width:thin;">
			<table style="font-size:80%;">
				<tr><td bgcolor='#ffff55' width='30'></td><td width='150'>共通部</td></tr>
				<tr><td bgcolor='#5555ff' width='30'></td><td width='150'>ローレシア</td></tr>
				<tr><td bgcolor='#55ff55' width='30'></td><td width='150'>サマルトリア</td><td bgcolor='#225522' width='30'></td><td width='150'>サマルトリア不在</td></tr>
				<tr><td bgcolor='#ff5555' width='30'></td><td width='150'>ムーンブルク</td><td bgcolor='#552222' width='30'></td><td width='150'>ムーンブルク不在</td></tr>
				<tr><td></td><td></td><td bgcolor='#555555' width='30'></td><td width='150'>余剰</td></tr>
			</table>
			</div>

			</blockquote>

			<blockquote>
			<s:if test="excessBit">
			<font color='red'>余剰ビットあり</font>
			</s:if>
			<font color='red'><s:property value="error" /></font>
			</blockquote>

			<h2>共通</h2>
			<table>
			<tr>
			<th>セーブポイント</th><td><s:property value="savePoint" /></td>
			</tr>
			<tr>
			<th>ゴールド</th><td><s:property value="gold" /></td>
			</tr>
			<tr>
			<th>バリエーション</th><td><s:property value="variation" /></td>
			</tr>
			</table>

			<h2>イベント</h2>
			<table>
			<tr>
			<th>月のかけら</th><td>入手<s:if test="%{月のかけら}">した</s:if><s:else>していない</s:else></td>
			</tr>
			<tr>
			<th>水門</th><td><s:if test="%{水門}">開けた</s:if><s:else>開けてない</s:else></td>
			</tr>
			<tr>
			<th>水のはごろも</th><td><s:if test="%{水のはごろも}">作成中</s:if><s:else>作成中ではない</s:else></td>
			</tr>
			<tr>
			<th>船</th><td>入手<s:if test="%{船}">した</s:if><s:else>していない</s:else></td>
			</tr>
			<tr>
			<th>ルプガナの少女</th><td><s:if test="%{少女}">助けた</s:if><s:else>助けてない</s:else></td>
			</tr>
			<tr>
			<th>サマルトリア王子の状態</th><td><s:property value="サマルトリア王子の状態" /></td>
			</tr>
			</table>

			<h2>紋章</h2>
			<table>
			<tr>
			<th>命の紋章</th><td><s:if test="%{命の紋章}">あり</s:if><s:else>なし</s:else></td>
			<th>水の紋章</th><td><s:if test="%{水の紋章}">あり</s:if><s:else>なし</s:else></td>
			<th>月の紋章</th><td><s:if test="%{月の紋章}">あり</s:if><s:else>なし</s:else></td>
			<th>星の紋章</th><td><s:if test="%{星の紋章}">あり</s:if><s:else>なし</s:else></td>
			<th>太陽の紋章</th><td><s:if test="%{太陽の紋章}">あり</s:if><s:else>なし</s:else></td>
			</tr>
			</table>

			<s:iterator value="players">
				<h2><s:property value="role" /></h2>
				<s:if test="%{exist}">
					<table>
					<tr>
						<td rowspan="3" style="border-bottom:0px;">
						<s:if test='%{role.equals("ローレシアの王子")}'><img src="cha1.png"></s:if>
						<s:if test='%{role.equals("サマルトリアの王子")}'><img src="cha2.png"></s:if>
						<s:if test='%{role.equals("ムーンブルクの王女")}'><img src="cha3.png"></s:if>
						</td>
						<th>名前</th><td><s:property value="%{name}" /></td>
					</tr>
					<tr><th>経験値</th><td><s:property value="experience" /></td></tr>
					<tr><th>レベル</th><td><s:property value="level" /></td></tr>
					</table>
					<h3>アイテム</h3>
					<table>
					<tr>
					<s:iterator value="items1">
						<td style='width: 10px;'><s:if test="equipment">E</s:if><s:else></s:else></td>
						<td style='width: 120px;'><s:property value="item.name" /></td>
					</s:iterator>
					</tr>
					<tr>
					<s:iterator value="items2">
						<td style='width: 10px;'><s:if test="equipment">E</s:if><s:else></s:else></td>
						<td style='width: 120px;'><s:property value="item.name" /></td>
					</s:iterator>
					<tr>
					</table>
				</s:if>
				<s:else>
					<div style='background-color:lightgray;'>
					<table>
					<tr>
						<td rowspan="3" style="border-bottom:0px;">
						<s:if test='%{role.equals("ローレシアの王子")}'><img src="cha1.png"></s:if>
						<s:if test='%{role.equals("サマルトリアの王子")}'><img src="cha2.png"></s:if>
						<s:if test='%{role.equals("ムーンブルクの王女")}'><img src="cha3.png"></s:if>
						</td>
						<th>名前</th><td><s:property value="%{name}" /></td>
					</tr>
					<tr><th>経験値</th><td><s:property value="experience" /></td></tr>
					<tr><th>レベル</th><td><s:property value="level" /></td></tr>
					</table>
					<h3>アイテム</h3>
					<table>
					<tr>
					<s:iterator value="items1">
						<td style='width: 10px;'><s:if test="equipment">E</s:if><s:else></s:else></td>
						<td style='width: 120px;'><s:property value="item.name" /></td>
					</s:iterator>
					</tr>
					<tr>
					<s:iterator value="items2">
						<td style='width: 10px;'><s:if test="equipment">E</s:if><s:else></s:else></td>
						<td style='width: 120px;'><s:property value="item.name" /></td>
					</s:iterator>
					<tr>
					</table>
					<s:if test="%{memo != null}">
					<font color="red"><s:property value="memo" /></font>
					</s:if>
					</div>
				</s:else>
			</s:iterator>

		</div>
		</div>
		</div>
	</body>
</html>

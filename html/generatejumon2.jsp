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
				<h3>呪文</h3>
				<s:iterator value="jumonString" id="line">
					<s:property value="line" /><br>
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
					<tr><td></td><td bgcolor='#555555' width='30'></td><td width='150'>余剰</td></tr>
				</table>
				</div>

				<br>

				<h3>ゲームデータ</h3>
				<s:if test="rooreshiaName.length()>0">
					ローレシアの王子：
					<s:property value="rooreshiaName" />
					Level.<s:property value="rooreshiaLevel" /><br>
				</s:if>
				<s:if test="samarutoriaName.length()>0">
					サマルトリアの王子：
					<s:property value="samarutoriaName" />
					Level.<s:property value="samarutoriaLevel" /><br>
				</s:if>
				<s:if test="muunburukuName.length()>0">
					ムーンブルクの王女：
					<s:property value="muunburukuName" />
					Level.<s:property value="muunburukuLevel" /><br>
				</s:if>
				<br>

				<s:if test="encountZero.length()>0">
					<span class='highlight'>エンカウントゼロ呪文</span>
				</s:if>
				<s:if test="errorsLength>0">
					<h3>エラー</h3>
					<ul>
						<s:iterator value="errors" id="error">
							<li><span class="error"><s:property value="error" /></span><br>
						</s:iterator>
					</ul>
				</s:if>
			</div>
			</div>
		</div>
	</body>
</html>

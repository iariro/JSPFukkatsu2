<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<title>ドラゴンクエストII 復活の呪文</title>
		<link rel="stylesheet" type="text/css" href="hatena.css">
		<script src="fabricatejumon1.js"></script>
	</head>

	<body>
		<h1>ドラゴンクエストII 復活の呪文</h1>

		<div class=hatena-body>
		<div class=main>

			<h3>呪文</h3>
			<form name="samplejumon">
				サンプル入力：
				<select id="jumonkind" name="jumonkind" onchange="setJumon()">
				</select>
			</form>
			復活の呪文のもととなるフレーズを入力<br>
			<s:form name="jumon" action="fabricatejumon2" theme="simple">
				<s:textarea name="words" rows="5" cols="30" />
				<br>
				<s:submit value="生成" />
			</s:form>

		</div>
		</div>

	</body>
</html>

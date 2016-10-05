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
			</blockquote>

			<h2>エラー</h2>
			<blockquote>
			<s:property value="exception.message" /><br>
			</blockquote>
		</div>
		</div>
		</div>
	</body>
</html>

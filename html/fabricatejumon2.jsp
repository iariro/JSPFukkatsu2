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
			<s:iterator value="jumonList">
				<h3>呪文</h3>
				<s:iterator value="key">
					<s:property /><br>
				</s:iterator>
				<s:property value="%{value.ローレシアの王子の名前}" /><br>
			</s:iterator>

		</div>
		</div>
		</div>
	</body>
</html>

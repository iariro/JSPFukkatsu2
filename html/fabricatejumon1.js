
var jumonString =
	[
		[
			"ゆうていみやおう",
			"ゆうてい\r\n" +
			"みやおう\r\n" +
			"きむこう\r\n" +
			"ほりいゆうじ\r\n" +
			"とりやまあきら"
		],

		[
			"ロンダルキア吹奏楽団",
			"それゆけ\r\n" +
			"とらくえ\r\n" +
			"すいそうがく\r\n" +
			"がつきたずさえ\r\n" +
			"たびたて\r\n" +
			"ゆうしやたち\r\n" +
			"れべるいちらいぶ\r\n" +
			"おめてとう"
		]
	];

window.onload = function()
{
	var select = document.getElementById('jumonkind');

	for (var i=0 ; i<jumonString.length ; i++)
	{
		var option = document.createElement('option');
		option.setAttribute('value', i);
		option.innerHTML = jumonString[i][0];
		select.appendChild(option);
	}
	setJumon();
};

function setJumon()
{
	for (var i=0 ; i<document.samplejumon.jumonkind.options.length ; i++)
	{
		if (document.samplejumon.jumonkind.options[i].selected)
		{
			document.jumon.words.value = jumonString[i][1];
			break;
		}
	}
}

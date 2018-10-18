
var jumonString =
	[
		[
			"一人 スタート直後",
			"ぬもじ　ばざか　すごぜぶ\r\n" +
			"ぴねふ　みやり　わげ"
		],

		[
			"２人",
			"ぐきま　もはぞ　ずゆけあ\r\n" +
			"おるが　りぱく　ぞごぱも\r\n" +
			"ねとと　ぞさき　そぶぷか\r\n" +
			"まひひ　ぴたま　かぱわげ"
		],

		[
			"ゆうていみやおう",
			"ゆうて　いみや　おうきむ\r\n" +
			"こうほ　りいゆ　うじとり\r\n" +
			"やまあ　きらぺ　ぺぺぺぺ\r\n" +
			"ぺぺぺ　ぺぺぺ　ぺぺぺぺ\r\n" +
			"ぺぺぺ　ぺぺぺ　ぺぺぺぺ　ぺぺ"
		],

		[
			"はにまる",
			"こゆわ　るめむ　すじぐが\r\n" +
			"れろぱ　むゆほ　らべにぜ\r\n" +
			"ぶぽべ　あきい　きりくす\r\n" +
			"ずふべ　そのた　らわぷそ\r\n" +
			"ずびぐ　つらひ　きぴたふ　へ"
		],

		[
			"ごくう",
			"こまぷ　たぜら　ずせれれ\r\n" +
			"らまげ　なのつ　うふひむ\r\n" +
			"たもげ　すじぎ　じとじぎ\r\n" +
			"ちびみ　おいけ　ぬせげぶ\r\n" +
			"あごめ　ぷがび　つとりが　ぎ"
		],

		[
			"ふくびきけん",
			"わひは　てぷゆ　ぷぴほら\r\n" +
			"りるぼ　むよみ　ぼるむぷ\r\n" +
			"すぜれ　おじぐ　ぜぬびぴ\r\n" +
			"しずる　えざれ　きにぺち\r\n" +
			"ばぱぞ　ちぺば ともさぽ　ひけ"
		],

		[
			"途中まで１",
			"すひぞ　ぶたう　とあやが\r\n" +
			"げくさ　せがば　やねいり\r\n" +
			"ぐぷは　ほぞれ　ぱれぎば\r\n" +
			"ぽした　とずぷ　ゆびうれ\r\n"
		],

		[
			"途中まで２",
			"ぐぷぱ　ほぞが　てあゆが\r\n" +
			"ごほは　めぞぱ　れまうに\r\n" +
			"すてぎ　ごぐぼ　わのぱお\r\n" +
			"くかけ　おりの　なけじの"
		],

		[
			"ロンダルキア吹奏楽団",
			"それゆ　けとら　くえすい\r\n" +
			"そうが　くがつ　きたずさ\r\n" +
			"えたび　たてゆ　うしやた\r\n" +
			"ちれべ　るいち　らいぶお\r\n" +
			"めてと　うねね　ねね"
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
};

function setJumon()
{
	for (var i=0 ; i<document.samplejumon.jumonkind.options.length ; i++)
	{
		if (document.samplejumon.jumonkind.options[i].selected)
		{
			document.jumon.jumon.value = jumonString[i][1];
			break;
		}
	}
}

package kumagai.Fukkatsu2.servlet;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import kumagai.Fukkatsu2.logic.CompressedGameDataBitArray;
import kumagai.Fukkatsu2.logic.ExtendedGameDataBitArray;
import kumagai.Fukkatsu2.logic.GameData;
import kumagai.Fukkatsu2.logic.InvalidItemException;
import kumagai.Fukkatsu2.logic.InvalidJumonException;

/**
 * ビットマップイメージ。
 * @author kumagai
 */
public class Fukkatsu2Bitmap
	extends BufferedImage
{
	static final int width = 2;
	static final int height = 20;

	/**
	 * テストコード。
	 * @param args no use
	 */
	public static void main(String[] args)
		throws IOException, SQLException, ParseException, InvalidJumonException, InvalidItemException
	{
		byte[] data = DatatypeConverter.parseHexBinary("241B0D2C1B011E3B01172636180717201B2D1E1139381F031D153D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D3D");
		BufferedImage image = new Fukkatsu2Bitmap(data);

		ImageIO.write(image, "png", new File("../Fukkatsu2Bit.png"));
	}

	/**
	 * ビットマップイメージ構築。
	 * @param plainArray ビット配列
	 */
	public Fukkatsu2Bitmap(byte [] plainArray)
		throws InvalidJumonException, InvalidItemException
	{
		super(width * 6 * plainArray.length, height, BufferedImage.TYPE_INT_BGR);

		Graphics2D graphics = createGraphics();

		// 背景
		graphics.setPaint(new Color(0xee, 0xee, 0xee));
		graphics.fillRect(0, 0, width * 6 * plainArray.length, height);
		Color colorCommon = new Color(0xff, 0xff, 0x55);
		Color colorBack = new Color(0x33, 0x33, 0x33);
		Color colorRo = new Color(0x55, 0x55, 0xff);
		Color colorSa1 = new Color(0x55, 0xff, 0x55);
		Color colorMu1 = new Color(0xff, 0x55, 0x55);
		Color colorSa2 = new Color(0x22, 0x55, 0x22);
		Color colorMu2 = new Color(0x55, 0x22, 0x22);
		Color colorXX = new Color(0x55, 0x55, 0x55);

		CompressedGameDataBitArray compressed =
			new CompressedGameDataBitArray(plainArray);
		ExtendedGameDataBitArray extendedGameDataBitArray =
			new ExtendedGameDataBitArray(compressed);
		GameData gamedata = new GameData(extendedGameDataBitArray);

		int limitRo = 0;
		int limitSa = 0;
		int limitMu = 0;

		if ((plainArray.length * 6) > (72 + 24))
		{
			int itemCount = extendedGameDataBitArray.getAsInt(72 + 20, 4);
			limitRo = 72 + 24 + 7 * itemCount;
		}
		if (extendedGameDataBitArray.size() >= limitRo + 24)
		{
			int itemCount = extendedGameDataBitArray.getAsInt(limitRo + 1 + 20, 4);
			limitSa = limitRo + 1 + 24 + 7 * itemCount;
		}
		if (extendedGameDataBitArray.size() >= limitSa + 24)
		{
			int itemCount = extendedGameDataBitArray.getAsInt(limitSa + 1 + 20, 4);
			limitMu = limitSa + 1 + 24 + 7 * itemCount;
		}

		for (int x=0 ; x<plainArray.length ; x++)
		{
			for (int bit=0 ; bit<6 ; bit++)
			{
				int index = x * 6 + bit;

				Color color;
				if ((plainArray[x] &  (1 << (5 - bit))) > 0)
				{
					if (index < 72)
					{
						color = colorCommon;
					}
					else if (index < limitRo)
					{
						color = colorRo;
					}
					else if (index < limitSa)
					{
						if (gamedata.playerCollection.size() >= 2 &&
							gamedata.playerCollection.get(1).exist)
						{
							color = colorSa1;
						}
						else
						{
							color = colorSa2;
						}
					}
					else if (index < limitMu)
					{
						if (gamedata.playerCollection.size() >= 3 &&
							gamedata.playerCollection.get(2).exist)
						{
							color = colorMu1;
						}
						else
						{
							color = colorMu2;
						}
					}
					else
					{
						color = colorXX;
					}
				}
				else
				{
					color = colorBack;
				}

				graphics.setPaint(color);

				graphics.fillRect(width * (x * 6 + bit), 0, width, height);
			}
		}
	}
}

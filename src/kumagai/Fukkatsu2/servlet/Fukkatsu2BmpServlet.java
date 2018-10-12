package kumagai.Fukkatsu2.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

/**
 * 呪文ビット画像出力サーブレット。
 * @author kumagai
 */
public class Fukkatsu2BmpServlet
	extends HttpServlet
{
	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet
		(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");

		String hexdata = request.getParameter("hexdata");
		byte[] data = DatatypeConverter.parseHexBinary(hexdata);

		try
		{
			BufferedImage image = new Fukkatsu2Bitmap(data);
			ImageIO.write(image, "png", response.getOutputStream());
		}
		catch (Exception exception)
		{
		}
	}
}

package web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**用于动态生成图片的servlet,
 * 配置时url-pattern写成:/code 
 */
public class CodeServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
       //绘制一张图片的过程
		//1.准备一张空白的有尺寸的图片
		BufferedImage image = new BufferedImage(80,30,BufferedImage.TYPE_INT_RGB);//宽100，高30
		//2.获取图片的画笔对象
		Graphics g=image.getGraphics();
		//3.设置画笔颜色
		Random r = new Random();
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		//4.先绘制背景
		g.fillRect(0, 0, 100, 30);
		//5.更改画笔颜色
		g.setColor(new Color(255,255,255));
		//设置字体
		g.setFont(new Font(null,Font.BOLD,20));
		//6.绘制字符串
		//生成的验证码保存到session中
		String number=getNumber(5);
		g.drawString(number,10, 25);
		//7.设置响应流的数据格式
		response.setContentType("image/jpeg");
		//8.获取输出流
		OutputStream ops = response.getOutputStream();
		//9.保存图片到输出流中  ops流向客户端，图片就流到客户端
		ImageIO.write(image,"jpeg",ops);
		HttpSession session = request.getSession();
		session.setAttribute("c_msg",number);
		ops.close();
	}
   public String getNumber(int size){
	  String cs =  "ADSBCVNFGKLJYUIOWEAMFL1238097654";
	  String number = "";
	  Random r = new Random();
	  for(int i = 0;i<size;i++){
		  number+=cs.charAt(r.nextInt(cs.length()));
	  }
	return number;
   }
}

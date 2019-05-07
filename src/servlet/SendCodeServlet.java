package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SMSverification.SMScode;

/**
 * Servlet implementation class SendCodeServlet
 */
@WebServlet("/SendCodeServlet")
public class SendCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//从表单获取接收短信的手机号码
		String phoneNumber = request.getParameter("phoneNumber");
        System.out.println("手机号码：" + phoneNumber);

        if(phoneNumber.trim().equals("") || phoneNumber == null) {
            System.out.println("手机号码为空！");
            response.sendRedirect("index.jsp");
            return ;
        }
        //手机号码格式判断
        if(!Pattern.matches("^1[3|4|5|7|8]\\d{9}$", phoneNumber)) {
            System.out.println("手机格式错误！");
            response.sendRedirect("index.jsp");
            return ;
        }
        //生成一个6位0~9之间的随机字符串
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            buffer.append(random.nextInt(10));
        }

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            if(!SMScode.sendCode(phoneNumber, buffer.toString())) {
                out.println("验证码发送失败！");
            } else {
                //将验证码、手机号码和当前的系统时间存储到session中
                request.getSession().setAttribute("code", buffer.toString());
                request.getSession().setAttribute("number", phoneNumber );
                request.getSession().setAttribute("time", System.currentTimeMillis());
                out.println("验证码发送成功！");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

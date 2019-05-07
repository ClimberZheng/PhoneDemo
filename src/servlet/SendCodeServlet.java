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
		//�ӱ���ȡ���ն��ŵ��ֻ�����
		String phoneNumber = request.getParameter("phoneNumber");
        System.out.println("�ֻ����룺" + phoneNumber);

        if(phoneNumber.trim().equals("") || phoneNumber == null) {
            System.out.println("�ֻ�����Ϊ�գ�");
            response.sendRedirect("index.jsp");
            return ;
        }
        //�ֻ������ʽ�ж�
        if(!Pattern.matches("^1[3|4|5|7|8]\\d{9}$", phoneNumber)) {
            System.out.println("�ֻ���ʽ����");
            response.sendRedirect("index.jsp");
            return ;
        }
        //����һ��6λ0~9֮�������ַ���
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            buffer.append(random.nextInt(10));
        }

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            if(!SMScode.sendCode(phoneNumber, buffer.toString())) {
                out.println("��֤�뷢��ʧ�ܣ�");
            } else {
                //����֤�롢�ֻ�����͵�ǰ��ϵͳʱ��洢��session��
                request.getSession().setAttribute("code", buffer.toString());
                request.getSession().setAttribute("number", phoneNumber );
                request.getSession().setAttribute("time", System.currentTimeMillis());
                out.println("��֤�뷢�ͳɹ���");
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

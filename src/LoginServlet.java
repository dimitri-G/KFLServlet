import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import dao.ConsumersDao;
import vo.consumers;

/**
 * post鍙戦�锛宲ost鎺ユ敹
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //璁剧疆鎺ユ敹缂栫爜
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //鎺ユ敹鐢ㄦ埛鍚嶅拰瀵嗙爜
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        System.out.println(nickname+password);
        try {
            //閫氳繃鐢ㄦ埛鍚嶆煡鎵捐鐢ㄦ埛
            consumers consumer=new ConsumersDao().findByNickname(nickname);
            if (consumer!=null) {
            	System.out.println(nickname+password);
                    //鐢ㄦ埛瀛樺湪锛屽瘑鐮佹纭�
                	PrintWriter out = response.getWriter();
                	if(password.equals(consumer.getPassword())){
                	String result=nickname+","+consumer.getBalance();
                	System.out.println(result);
                	out.write(result);
            		out.flush();
            		out.close();
                	}else{
                		PrintWriter out2 = response.getWriter();
                    	
                    	out2.write("ERROR");
                		out.flush();
                		out.close();
                	}
                
            }
            else{
                //鐢ㄦ埛涓嶅瓨鍦�
            	PrintWriter out = response.getWriter();
            	String result="error";
            	out.write(result);
        		out.flush();
        		out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

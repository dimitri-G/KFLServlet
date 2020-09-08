import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.consumers;
import dao.ConsumersDao;
import dao.ProvidersDao;


public class GetCustomerServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置接收编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        //接收注册用户信息
        String nickname = request.getParameter("nickname"); //非空 //非空
        
        System.out.println(nickname);
        ConsumersDao GoodsDao=new ConsumersDao();
        try {
            //添加用户
            String res="";
            consumers con=GoodsDao.findByNickname(nickname);
            //System.out.println(res+","+password+res.equals(password)+password.equals(res));
                //注册正常
            	PrintWriter out = response.getWriter();
            	res+=con.getPassword()+","+con.getGender()+","+
            	con.getAge()+","+con.getAddress()+","+con.getTelephone()+","+
            			con.getMail()+","+con.getHeight()+","+con.getWeight()+",";
            	System.out.println(res);
            	out.write(res);
        		out.flush();
        		out.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

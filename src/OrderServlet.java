import vo.goods;
import vo.consumers;
import vo.providers;
import vo.orderrelate;
import vo.order;









import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import dao.*;

/**
 * post鍙戦�锛宲ost鎺ユ敹
 */
@WebServlet(name = "OrderServlet")
public class OrderServlet extends HttpServlet {
	
	public static String getRandom2(int len) {
        Random r = new Random();
        StringBuilder rs = new StringBuilder();
        for (int i = 0; i < len; i++) {
            rs.append(r.nextInt(10));
        }
        return rs.toString();
    }
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //璁剧疆鎺ユ敹鏍煎紡
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setContentType("text/html");
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
        String shopping=request.getParameter("shopping");
        String nickname=request.getParameter("UserName");
        OrderDao ord=new OrderDao();
        order or=new order();
        String[] sqlite=shopping.split(",");
        Date currentTime = new Date(); 
        //改变输出格式（自己想要的格式） 
        SimpleDateFormat formatter = new SimpleDateFormat("yy:MM:dd:hh:mm:ss"); 
        //得到字符串时间 
        String s8 = formatter.format(currentTime); 
        or.setNickname(nickname);
        String OrderNumber=s8+getRandom2(5);
        or.setOrdernumber(OrderNumber);
        or.setSumprices(Double.parseDouble(sqlite[0]));
        or.setState(1);
        System.out.println(nickname+OrderNumber);
        OrderrelateDao ort=new OrderrelateDao();
        orderrelate un=new orderrelate(); 
        un.setOrdernumber(OrderNumber);
        
        ConsumersDao cd=new ConsumersDao();
        
        try {
			ord.addOrder(or);
			cd.updateConsumerbalance(Double.parseDouble(sqlite[0]), nickname);
			for(int i=0;i<sqlite.length/4;i++){
				un.setGoodid(sqlite[4*i+1]);
				un.setGoodnumber(Integer.parseInt(sqlite[3+4*i]));
				un.setGoodprice(Double.parseDouble(sqlite[4+4*i]));
				ort.addOrderrelate(un);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        PrintWriter out = response.getWriter();
        ConsumersDao cda=new ConsumersDao();
    	String result="";
		try {
			result = String.valueOf(cda.Consumerbalance(nickname));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	out.write(result);
		out.flush();
		out.close();
        
        
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

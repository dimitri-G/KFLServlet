import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.goods;
import dao.GoodsDao;


public class CreateFoodServlet extends HttpServlet {
	public static String getRandom2(int len) {
        Random r = new Random();
        StringBuilder rs = new StringBuilder();
        for (int i = 0; i < len; i++) {
            rs.append(r.nextInt(10));
        }
        return rs.toString();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //璁剧疆鎺ユ敹缂栫爜
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        //鎺ユ敹娉ㄥ唽鐢ㄦ埛淇℃伅
        String providerid = request.getParameter("providerId"); //闈炵┖ //闈炵┖
        String foodName=request.getParameter("foodName");
        String foodPrice=request.getParameter("foodPrice");
        String foodType=request.getParameter("foodType");
        String foodCon=request.getParameter("foodCon");
        Date currentTime = new Date(); 
        //改变输出格式（自己想要的格式） 
        SimpleDateFormat formatter = new SimpleDateFormat("yy:MM:dd:hh:mm:ss"); 
        //得到字符串时间 
        String s8 = formatter.format(currentTime); 
        //鐢熸垚鏂扮敤鎴峰疄渚�
        goods good=new goods();
        good.setPrice(Double.parseDouble(foodPrice));
        good.setProviderid(providerid);
        good.setName(foodName);
        good.setType(foodType);
        good.setGoodid(s8+getRandom2(3));
        System.out.println(providerid);
        GoodsDao GoodsDao=new GoodsDao();
        try {
            //娣诲姞鐢ㄦ埛
            int res=GoodsDao.addGoods2(good,foodCon);
            //System.out.println(res+","+password+res.equals(password)+password.equals(res));
                //娉ㄥ唽姝ｅ父
            	PrintWriter out = response.getWriter();
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

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GoodsDao;


public class DeleteFoodServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //璁剧疆鎺ユ敹缂栫爜
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        //鎺ユ敹娉ㄥ唽鐢ㄦ埛淇℃伅
        String foodId = request.getParameter("foodId"); //闈炵┖ //闈炵┖

        //鐢熸垚鏂扮敤鎴峰疄渚�
        
        System.out.println(foodId);
        GoodsDao GoodsDao=new GoodsDao();
        try {
            //娣诲姞鐢ㄦ埛
            int res=GoodsDao.deleteGoods(foodId);
            String result="";
            //System.out.println(res+","+password+res.equals(password)+password.equals(res));
                //娉ㄥ唽姝ｅ父
            	PrintWriter out = response.getWriter();
            	if(res==1)
            		result="success";
            	System.out.println(result);
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

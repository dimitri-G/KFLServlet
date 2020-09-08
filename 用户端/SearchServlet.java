
import dao.ConsumersDao;
import dao.GoodsDao;
import dao.ProvidersDao;
import vo.goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * post鍙戦�锛宲ost鎺ユ敹
 */
@WebServlet(name = "SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //璁剧疆鎺ユ敹鏍煎紡
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //鎺ユ敹瑕佹悳绱㈢殑鍟嗗搧鍚�
        String keyword= request.getParameter("keyword");

        ProvidersDao PD=new ProvidersDao();
        String result=null;
        try {
            //鐢ˋrrayList鎺ユ敹鎼滅储鍒扮殑缁撴灉
            
            result=PD.searchFuzzy(keyword);
            if(result!=null){
            	PrintWriter out = response.getWriter();
                System.out.println(result);
            	out.write(result);
        		out.flush();
        		out.close();
            }
            else
            {
            	PrintWriter out = response.getWriter();
                System.out.println(result);
            	out.write("empty");
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

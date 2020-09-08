import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.el.lang.EvaluationContext;

import dao.EvalutionDao;
import dao.ProvidersDao;


public class GetKmeansServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //璁剧疆鎺ユ敹鏍煎紡
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //鎺ユ敹瑕佹悳绱㈢殑鍟嗗搧鍚�
        String keyword= request.getParameter("keyword");

        EvalutionDao PD=new EvalutionDao();
        String result=null;
        try {
            //鐢ˋrrayList鎺ユ敹鎼滅储鍒扮殑缁撴灉
            
            result=PD.getDataTop10ToKmeans();
            PrintWriter out = response.getWriter();
            System.out.println(result);
        	out.write(result);
    		out.flush();
    		out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

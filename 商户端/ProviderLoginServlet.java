import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.providers;
import dao.ProvidersDao;


public class ProviderLoginServlet extends HttpServlet {
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        //璁剧疆鎺ユ敹缂栫爜
	        request.setCharacterEncoding("utf-8");
	        response.setContentType("text/html;charset=utf-8");
	        response.setCharacterEncoding("utf-8");
	        //鎺ユ敹娉ㄥ唽鐢ㄦ埛淇℃伅
	        String providerid = request.getParameter("providerid"); //闈炵┖
	        String password = request.getParameter("providerPwd"); //闈炵┖
	        
	        if(providerid==null||providerid.trim().isEmpty()){
	            //鐢ㄦ埛鍚嶄负绌�
	            return;
	        }
	        //鐢熸垚鏂扮敤鎴峰疄渚�
	        
	        System.out.println(providerid+password);
	        ProvidersDao consumersDao=new ProvidersDao();
	        try {
	            //娣诲姞鐢ㄦ埛
	            String res=consumersDao.selectPwdById(providerid);
	            //System.out.println(res+","+password+res.equals(password)+password.equals(res));
	            if (res.equals(password)){
	                //娉ㄥ唽姝ｅ父
	            	PrintWriter out = response.getWriter();
	            	String result="success";
	            	System.out.println(result);
	            	out.write(result);
	        		out.flush();
	        		out.close();
	            }
	            else{
	                //娉ㄥ唽寮傚父
	            	PrintWriter out = response.getWriter();
	            	String result="ERROR";
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

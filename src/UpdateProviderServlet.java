import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.goods;
import vo.providers;
import dao.GoodsDao;
import dao.ProvidersDao;


public class UpdateProviderServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //璁剧疆鎺ユ敹缂栫爜
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        //鎺ユ敹娉ㄥ唽鐢ㄦ埛淇℃伅
        String providerid = request.getParameter("providerId"); //闈炵┖ //闈炵┖
        String providerName=request.getParameter("providerName");
        String providerPwd=request.getParameter("providerPwd");
        String providerAdr=request.getParameter("providerAdr");
        String providerRemark=request.getParameter("providerRemark");
        String providerPhone=request.getParameter("providerPhone");
        //鐢熸垚鏂扮敤鎴峰疄渚�
        providers provider=new providers();
        provider.setProviderid(providerid);
        provider.setName(providerName);
        provider.setpassword(providerPwd);
        provider.setAddress(providerAdr);
        provider.setRelates(providerRemark);
        provider.setTelephone(providerPhone);
        
        System.out.println(providerid);
        ProvidersDao GoodsDao=new ProvidersDao();
        try {
            //娣诲姞鐢ㄦ埛
            int res=GoodsDao.updateProviders2(provider);
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

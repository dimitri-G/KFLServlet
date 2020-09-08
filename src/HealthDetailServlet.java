import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EvalutionDao;


public class HealthDetailServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("textml;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		String result="";
		String nickname = request.getParameter("nickname");
		String date=request.getParameter("date");
		System.out.println(nickname+"123"+date+"123");
		//ArrayList<providers> result=new ArrayList<providers>();
		EvalutionDao OD=new EvalutionDao();
		try {
			//result=prod.listAllProviders();
			result=OD.selectOrderByProviderIdDate(nickname, date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(123456);
		System.out.println(result);
		PrintWriter out = response.getWriter();
		//ArrayList list = (ArrayList) request.getAttribute("result");
		out.write(result);
		out.flush();
		out.close();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

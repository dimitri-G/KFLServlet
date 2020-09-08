import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;


public class CommitServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("textml;charset=utf-8");
		response.setContentType("text/html");
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		int result=0;
		String ordernumber = request.getParameter("orderNum");
		int ranking=Integer.parseInt(request.getParameter("ranking"));
		int state=Integer.parseInt(request.getParameter("state"));
		String commit = request.getParameter("advice");
		//ArrayList<providers> result=new ArrayList<providers>();
		OrderDao OD=new OrderDao()	;
		try {
			//result=prod.listAllProviders();
			result=OD.updateOrderCommit(ordernumber, ranking, commit, state);
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

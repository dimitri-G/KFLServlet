import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import dao.OrderrelateDao;


public class ChangeOrderStateServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("textml;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		String result="0k";
		int res=0;
		String ordernumber = request.getParameter("orderNum");
		int state=Integer.parseInt(request.getParameter("state"));
		System.out.println(ordernumber);
		//ArrayList<providers> result=new ArrayList<providers>();
		OrderDao OD=new OrderDao()	;
		try {
			//result=prod.listAllProviders();
			res=OD.updateOrderState(ordernumber, state);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(123456);
		if(res!=0)
			result="OK";
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

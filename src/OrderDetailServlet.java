import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import dao.OrderrelateDao;


public class OrderDetailServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("textml;charset=utf-8");
		response.setContentType("text/html");
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		String result="";
		String ordernumber = request.getParameter("orderNum");
		System.out.println(ordernumber);
		//ArrayList<providers> result=new ArrayList<providers>();
		OrderrelateDao OD=new OrderrelateDao()	;
		try {
			//result=prod.listAllProviders();
			result=OD.selectByNumber(ordernumber);
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

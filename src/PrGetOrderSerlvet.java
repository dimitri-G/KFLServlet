import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;


public class PrGetOrderSerlvet extends HttpServlet {	
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			request.setCharacterEncoding("utf-8");
			response.setContentType("textml;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			
			String result="";
			String providername = request.getParameter("providername");
			int state1=Integer.parseInt(request.getParameter("state1"));
			int state2=Integer.parseInt(request.getParameter("state2"));
			System.out.println(providername+state1+state2);
			//ArrayList<providers> result=new ArrayList<providers>();
			OrderDao OD=new OrderDao();
			try {
				//result=prod.listAllProviders();
				result=OD.selectOrderByProvidername(providername, state1, state2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(123456);
			System.out.println(result+result.equals(""));
			if(result.equals(""))
				result+="empty";
			System.out.println(result+result.equals(""));
			PrintWriter out = response.getWriter();
			//ArrayList list = (ArrayList) request.getAttribute("result");
			out.write(result);
			out.flush();
			out.close();
		}
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    }
}

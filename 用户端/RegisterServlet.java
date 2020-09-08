import dao.ConsumersDao;
import vo.consumers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * post鍙戦�锛宲ost鎺ユ敹
 */
@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //璁剧疆鎺ユ敹缂栫爜
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        //鎺ユ敹娉ㄥ唽鐢ㄦ埛淇℃伅
        String nickname = request.getParameter("nickname"); //闈炵┖
        String password = request.getParameter("password"); //闈炵┖
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");
        String address = request.getParameter("address");   //闈炵┖
        String telephone = request.getParameter("telephone");   //闈炵┖
        String mail = request.getParameter("mail");
        String Remarks = request.getParameter("Remarks");
        Double height=Double.parseDouble(request.getParameter("height"));
        Double weight=Double.parseDouble(request.getParameter("weight"));
        
        if(nickname==null||nickname.trim().isEmpty()){
            //鐢ㄦ埛鍚嶄负绌�
            return;
        }
        //鐢熸垚鏂扮敤鎴峰疄渚�
        consumers consumer=new consumers();
        consumer.setNickname(nickname);
        consumer.setPassword(password);
        consumer.setGender(gender);
        consumer.setAge(Integer.valueOf(age));
        consumer.setAddress(address);
        consumer.setTelephone(telephone);
        consumer.setMail(mail);
        
        consumer.setRemarks(Remarks);
        consumer.setHeight(height);
        consumer.setWeight(weight);
        
        
        System.out.println(nickname+password+gender);
        ConsumersDao consumersDao=new ConsumersDao();
        try {
            //娣诲姞鐢ㄦ埛
            int res=consumersDao.addConsumer(consumer);
            if (res==1){
                //娉ㄥ唽姝ｅ父
            	PrintWriter out = response.getWriter();
            	String result="success";
            	out.write(result);
        		out.flush();
        		out.close();
            }
            else{
                //娉ㄥ唽寮傚父
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

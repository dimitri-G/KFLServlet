package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.cj.protocol.Resultset;

import vo.*;

/**
 * 閽堝鏁版嵁搴撶殑澧炪�鍒犮�鏀广�鏌ワ紝骞朵笉鏄叏閮ㄩ兘鑳界敤鍒�
 * */
public class OrderDao {
    private Connection con=null;

    public void initConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AndroidBK", "root", "");
    }

    public void closeConnection() throws Exception{
        con.close();
    }

    /**
     * 鍒楀嚭鍏ㄩ儴璁㈠崟-鐢ㄦ埛
     * */
    public ArrayList<order> listAllOrder() throws Exception{
        ArrayList<order> list=new ArrayList<order>();
        this.initConnection();
        String sql="select * from `order`";
        PreparedStatement ps=con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while(rs.next()) {
            order un=new order();
            un.setSumprices(rs.getFloat("sumprices"));
            un.setOrdertime(rs.getString("ordertime"));
            un.setOrdernumber(rs.getString("ordernumber"));
            un.setNickname(rs.getString("nickname"));
            un.setState(rs.getInt("state"));
            list.add(un);
        }
        this.closeConnection();
        return list;
    }

    public String listAllOrder2() throws Exception{
        ArrayList<order> list=new ArrayList<order>();
        this.initConnection();
        String a="";
        String sql="select * from `order`";
        PreparedStatement ps=con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while(rs.next()) {
            order un=new order();
            un.setSumprices(rs.getFloat("sumprices"));
            un.setOrdertime(rs.getString("ordertime"));
            un.setOrdernumber(rs.getString("ordernumber"));
            un.setNickname(rs.getString("nickname"));
            un.setState(rs.getInt("state"));
            list.add(un);
        }
        this.closeConnection();
        return a;
    }
    
    
    public String selectOrderByNickname(String nickname) throws Exception{
        String a="";
        this.initConnection();
        String sql="select distinct `order`.*,providers.`name` from `order`,providers,orderrelate,goods where `order`.nickname=? and `order`.ordernumber=orderrelate.ordernumber and orderrelate.goodid=goods.goodid and goods.providerid=providers.providerid order by ordernumber desc";
        PreparedStatement ps=con.prepareStatement(sql);
        String sql2="select * from orderrelate,goods where ordernumber=? AND orderrelate.goodid=goods.goodid;";
        PreparedStatement ps2=con.prepareStatement(sql2);
        ps.setObject(1, nickname);
        ResultSet rs=ps.executeQuery();
        while(rs.next()) {
            a+=rs.getString("ordernumber")+","+rs.getString("ordertime")+","
        +rs.getString("sumprices")+","+rs.getInt("state")+","+rs.getString("name")+",";
            ps2.setObject(1, rs.getString("ordernumber"));
            ResultSet rs2=ps2.executeQuery();
            
            while(rs2.next()){
            	a+=rs2.getString("name")+"-"+rs2.getInt("goodnumber")
            			+"-"+rs2.getDouble("price")+"-";
            }
            a+="#";
        }
        this.closeConnection();
        return a;
    }
    
    public String selectOrderByProviderId(String nickname) throws Exception{
        String a="";
        this.initConnection();
        String sql="select distinct `order`.*,providers.`name` from `order`,providers,orderrelate,goods where providers.providerid=? and `order`.ordernumber=orderrelate.ordernumber and orderrelate.goodid=goods.goodid and goods.providerid=providers.providerid order by ordernumber desc";
        PreparedStatement ps=con.prepareStatement(sql);
        String sql2="select * from orderrelate,goods where ordernumber=? AND orderrelate.goodid=goods.goodid;";
        PreparedStatement ps2=con.prepareStatement(sql2);
        ps.setObject(1, nickname);
        ResultSet rs=ps.executeQuery();
        while(rs.next()) {
        	if(rs.getString("commit")!=null)
	            {
        		a+=rs.getString("ordernumber")+","+rs.getString("ordertime")+","
	        +rs.getString("commit")+","+rs.getFloat("ranking")+",";
	            ps2.setObject(1, rs.getString("ordernumber"));
	            ResultSet rs2=ps2.executeQuery();
	            
	            while(rs2.next()){
	            	a+=rs2.getString("name")+"   x"+rs2.getInt("goodnumber")+"\n";
	            }
	            a+="#";
	            }
        }
        if(a.equals(""))
        	a="empty";
        
        this.closeConnection();
        return a;
    }
    
    
    public String selectOrderByProvidername(String nickname,int state1,int state2) throws Exception{
        String a="";
        this.initConnection();
        String sql="select distinct `order`.*,providers.`name` from `order`,providers,orderrelate,goods where providers.providerid=? and(`order`.state=? or `order`.state=?) and `order`.ordernumber=orderrelate.ordernumber and orderrelate.goodid=goods.goodid and goods.providerid=providers.providerid order by ordernumber desc";
        PreparedStatement ps=con.prepareStatement(sql);
        String sql2="select * from orderrelate,goods where ordernumber=? AND orderrelate.goodid=goods.goodid;";
        PreparedStatement ps2=con.prepareStatement(sql2);
        ps.setObject(1, nickname);
        ps.setObject(2, state1);
        ps.setObject(3, state2);
        ResultSet rs=ps.executeQuery();
        while(rs.next()) {
            a+=rs.getString("ordernumber")+","+rs.getString("ordertime")+","
        +rs.getString("sumprices")+","+rs.getInt("state")+","+rs.getString("name")+",";
            ps2.setObject(1, rs.getString("ordernumber"));
            ResultSet rs2=ps2.executeQuery();
            
            while(rs2.next()){
            	a+=rs2.getString("name")+"-"+rs2.getInt("goodnumber")
            			+"-"+rs2.getDouble("price")+"-";
            }
            a+="#";
        }
        this.closeConnection();
        return a;
    }
    
    
    /**
     * 閫氳繃璁㈠崟鍙锋煡鎵惧叿浣撹鍗�
     * */
    public order selectByNumber(String ordernumber) throws Exception{
        this.initConnection();
        order un=new order();
        String sql="select * from `order` where ordernumber=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, ordernumber);
        ResultSet rs=ps.executeQuery();
        if(rs.next()) {
            un.setSumprices(rs.getFloat("sumprices"));
            un.setNickname(rs.getString("nickname"));
            un.setOrdernumber(rs.getString("ordernumber"));
            un.setOrdertime(rs.getString("ordertime"));
        }
        this.closeConnection();
        return un;
    }

    /**
     * 閫氳繃璁㈠崟鍙峰垹闄よ鍗�
     * */
    public int deleteOrder(String ordernumber) throws Exception{
        this.initConnection();
        String sql="delete from `order` where ordernumber=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, ordernumber);
        int count=0;
        count=ps.executeUpdate();
        this.closeConnection();
        return count;
    }

    /**
     * 娣诲姞璁㈠崟
     * */
    public int addOrder(order un) throws Exception{
        this.initConnection();
        Date currentTime = new Date(); 
      //改变输出格式（自己想要的格式） 
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
      //得到字符串时间 
      String s8 = formatter.format(currentTime); 

        String sql="insert into `order`(ordernumber,nickname,ordertime,sumprices,state) values(?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, un.getOrdernumber());
        ps.setObject(2, un.getNickname());
        ps.setObject(3, s8);
        ps.setObject(4, un.getSumprices());
        ps.setObject(5, un.getState());
        int count=0;
        count=ps.executeUpdate();
        this.closeConnection();
        return count;
    }

    /**
     * 淇敼璁㈠崟淇℃伅
     * */
    public int updateOrder(order un) throws Exception{
        this.initConnection();
        String sql="update `order` set nickname=? ordertime=? sumprices=? where ordernumber=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, un.getNickname());
        ps.setObject(2, un.getOrdertime());
        ps.setObject(3, un.getSumprices());
        ps.setObject(4, un.getOrdernumber());
        int count=0;
        count=ps.executeUpdate();
        this.closeConnection();
        return count;
    }
    
    public int updateOrderState(String orderNum,int state) throws Exception{
        this.initConnection();
        int count=0;
        /*String sql1="select state from `order` where ordernumber=?";
        PreparedStatement ps1=con.prepareStatement(sql1);
        ps1.setObject(1, orderNum);
        ResultSet rs=ps1.executeQuery();
        if(state==4&&rs.getInt("state")==5)
        	return count;*/
        String sql="update `order` set state=? where ordernumber=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, state);
        ps.setObject(2, orderNum);
        
        count=ps.executeUpdate();
        this.closeConnection();
        return count;
    }
    
    public int updateOrderCommit(String orderNum,int ranking,String commit,int state) throws Exception{
        this.initConnection();
        int count=0;
        /*String sql1="select state from `order` where ordernumber=?";
        PreparedStatement ps1=con.prepareStatement(sql1);
        ps1.setObject(1, orderNum);
        ResultSet rs=ps1.executeQuery();
        if(state==4&&rs.getInt("state")==5)
        	return count;*/
        String sql="update `order` set state=? ,commit=?, ranking=? where ordernumber=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, state);
        ps.setObject(2, commit);
        ps.setObject(3, ranking);
        ps.setObject(4, orderNum);
        count=ps.executeUpdate();
        
        String sql2="select AVG( `order`.ranking),providers.providerid from `order`,providers,orderrelate,goods WHERE `order`.ordernumber=? and `order`.ordernumber=orderrelate.ordernumber and orderrelate.goodid=goods.goodid and goods.providerid=providers.providerid";
        PreparedStatement ps2=con.prepareStatement(sql2);
        ps2.setObject(1, orderNum);
        ResultSet rs2=ps2.executeQuery();
        
        String sql3="update providers set ranking=? where providers.providerid=?";
        PreparedStatement ps3=con.prepareStatement(sql3);
        ps3.setObject(1, rs2.getFloat("AVG( `order`.ranking)"));
        ps3.setObject(2, rs2.getString("providerid"));
        count=ps3.executeUpdate();
        this.closeConnection();
        return count;
    }
    
    public int updateOrderComplain(String orderNum,String complain,int state) throws Exception{
        this.initConnection();
        int count=0;
        /*String sql1="select state from `order` where ordernumber=?";
        PreparedStatement ps1=con.prepareStatement(sql1);
        ps1.setObject(1, orderNum);
        ResultSet rs=ps1.executeQuery();
        if(state==4&&rs.getInt("state")==5)
        	return count;*/
        String sql="update `order` set state=? , complain=?  where ordernumber=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, state);
        ps.setObject(2, complain);
        ps.setObject(3, orderNum);
        
        count=ps.executeUpdate();
        this.closeConnection();
        return count;
    }
    
    

}


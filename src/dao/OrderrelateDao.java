package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.*;

/**
 * 閽堝鏁版嵁搴撶殑澧炪�鍒犮�鏀广�鏌ワ紝骞朵笉鏄叏閮ㄩ兘鑳界敤鍒�
 * */
public class OrderrelateDao {
    private Connection con=null;

    public void initConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AndroidBK", "root", "");
    }

    public void closeConnection() throws Exception{
        con.close();
    }

    /**
     * 鍒楀嚭鍏ㄩ儴璁㈠崟-鍟嗗搧
     * */
    public ArrayList<orderrelate> listAllOrderrelate() throws Exception{
        ArrayList<orderrelate> list=new ArrayList<orderrelate>();
        this.initConnection();
        String sql="select * from orderrelate";
        PreparedStatement ps=con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while(rs.next()) {
            orderrelate un=new orderrelate();
            un.setGoodprice(rs.getDouble("goodprice"));
            un.setGoodnumber(rs.getInt("goodnumber"));
            un.setOrdernumber(rs.getString("ordernumber"));
            un.setGoodid(rs.getString("goodid"));
            list.add(un);
        }
        this.closeConnection();
        return list;
    }

    /**
     * 閫氳繃璁㈠崟鍙锋煡鎵捐鍗�
     * */
    public String selectByNumber(String ordernumber) throws Exception{
    	String a="";
        this.initConnection();
        String sql="select DISTINCT `order`.*,providers.* from `order`,providers,orderrelate,goods where `order`.ordernumber=? and `order`.ordernumber=orderrelate.ordernumber and orderrelate.goodid=goods.goodid and goods.providerid=providers.providerid";
        PreparedStatement ps=con.prepareStatement(sql);
        String sql2="select * from orderrelate,goods where ordernumber=? AND orderrelate.goodid=goods.goodid;";
        PreparedStatement ps2=con.prepareStatement(sql2);
        int flag=0;
        ps.setObject(1, ordernumber);
        ResultSet rs=ps.executeQuery();  
        while(rs.next()){
        	if(flag<1)
            {a+=rs.getString("ordernumber")+","+rs.getString("ordertime")+","
        +rs.getString("sumprices")+","+rs.getInt("state")+","+rs.getString("name")+","+rs.getString("address")+","
            		+rs.getString("telephone")+","+rs.getString("providerid")+",";
            flag++;
            }
        	ps2.setObject(1, rs.getString("ordernumber"));
            ResultSet rs2=ps2.executeQuery();
            
            while(rs2.next()){
            	a+=rs2.getString("name")+"-"+rs2.getInt("goodnumber")
            			+"-"+rs2.getDouble("price")+"-";
            }
        }     
        this.closeConnection();
        return a;
    }

    /**
     * 閫氳繃璁㈠崟鍙峰垹闄よ鍗�
     * */
    public int deleteOrderrelate(String ordernumber) throws Exception{
        this.initConnection();
        String sql="delete from orderrelate where ordernumber=?";
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
    public int addOrderrelate(orderrelate un) throws Exception{
        this.initConnection();
        String sql="insert into orderrelate values(?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, un.getOrdernumber());
        ps.setObject(2, un.getGoodid());
        ps.setObject(3, un.getGoodnumber());
        ps.setObject(4, un.getGoodprice());
        int count=0;
        count=ps.executeUpdate();
        this.closeConnection();
        return count;
    }

    /**
     * 淇敼璁㈠崟淇℃伅
     * */
    public int updateOrderrelate(orderrelate un) throws Exception{
        this.initConnection();
        String sql="update orderrelate set goodid=? goodnumber=? goodprice=? where ordernumber=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, un.getGoodid());
        ps.setObject(2, un.getGoodnumber());
        ps.setObject(3, un.getGoodprice());
        ps.setObject(4, un.getOrdernumber());
        int count=0;
        count=ps.executeUpdate();
        this.closeConnection();
        return count;
    }

}


package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import vo.*;

/**
 * 閽堝鏁版嵁搴撶殑澧炪�鍒犮�鏀广�鏌ワ紝骞朵笉鏄叏閮借兘鐢ㄥ埌
 * */
public class ConsumersDao {
    private Connection con=null;

    public void initConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AndroidBK", "root", "");
    }

    public void closeConnection() throws Exception{
        con.close();
    }

    /**
     * 鍒楀嚭鍏ㄩ儴鐢ㄦ埛
     * */
    public ArrayList<consumers> listAllConsumer() throws Exception{
        ArrayList<consumers> list=new ArrayList<consumers>();
        this.initConnection();
        String sql="select * from consumers";
        PreparedStatement ps=con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while(rs.next()) {
            consumers un=new consumers();
            un.setNickname(rs.getString("nickname"));
            un.setPassword(rs.getString("password"));
            un.setGender(rs.getString("gender"));
            un.setAge(rs.getInt("age"));
            un.setAddress(rs.getString("address"));
            un.setTelephone(rs.getString("telephone"));
            un.setMail(rs.getString("mail"));
            un.setRemarks(rs.getString("Remarks"));
            list.add(un);
        }
        this.closeConnection();
        return list;
    }

    /**
     * 閫氳繃鐢ㄦ埛鍚嶆煡鎵剧敤鎴�
     * */
    public consumers findByNickname(String nickname) throws Exception{
        this.initConnection();
        consumers un=new consumers();
        String sql="select * from consumers where nickname=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, nickname);
        ResultSet rs=ps.executeQuery();
        if(rs.next()) {
            un.setNickname(rs.getString("nickname"));
            un.setPassword(rs.getString("password"));
            un.setGender(rs.getString("gender"));
            un.setAge(rs.getInt("age"));
            un.setAddress(rs.getString("address"));
            un.setTelephone(rs.getString("telephone"));
            un.setMail(rs.getString("mail"));
            un.setRemarks(rs.getString("Remarks"));
            un.setBalance(rs.getDouble("balance"));
            un.setHeight(rs.getDouble("height"));
            un.setWeight(rs.getDouble("weight"));
        }
        this.closeConnection();
        return un;
    }

    /**
     * 閫氳繃鐢ㄦ埛鍚嶅垹闄や竴涓敤鎴�
     * */
    public int deleteConsumer(String nickname) throws Exception{
        this.initConnection();
        String sql="delete from consumers where nickname=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, nickname);
        int count=0;
        count=ps.executeUpdate();
        this.closeConnection();
        return count;
    }

    /**
     * 娣诲姞涓�釜鐢ㄦ埛锛屾敞鍐屾椂浣跨敤
     * */
    public int addConsumer(consumers un) throws Exception{
        this.initConnection();
        String sql="insert into consumers values(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, un.getNickname());
        ps.setObject(2, un.getPassword());
        ps.setObject(3, un.getGender());
        ps.setObject(4, un.getAge());
        ps.setObject(5, un.getAddress());
        ps.setObject(6, un.getTelephone());
        ps.setObject(7, un.getMail());
        ps.setObject(8, un.getRemarks());
        ps.setObject(9, 10000);
        ps.setObject(10, un.getHeight());
        ps.setObject(11, un.getWeight());
        int count=0;
        count=ps.executeUpdate();
        this.closeConnection();
        return count;
    }

    /**
     * 淇敼鐢ㄦ埛淇℃伅
     * */
    public int updateConsumer(consumers un) throws Exception{
        this.initConnection();
        String sql="update consumers set password=?, gender=? ,age=? ,address=?, telephone=? ,mail=?, Remarks=?,height=?,weight=? where nickname=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, un.getPassword());
        ps.setObject(2, un.getGender());
        ps.setObject(3, un.getAge());
        ps.setObject(4, un.getAddress());
        ps.setObject(5, un.getTelephone());
        ps.setObject(6, un.getMail());
        ps.setObject(7, un.getRemarks());
        ps.setObject(8, un.getNickname());
        ps.setObject(9, un.getHeight());
        ps.setObject(10, un.getWeight());
        int count=0;
        count=ps.executeUpdate();
        this.closeConnection();
        return count;
    }
    
    public int updateConsumerbalance(double price,String name) throws Exception{
        this.initConnection();
        String sql="update consumers set balance=balance-?  where nickname=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, price);
        ps.setObject(2, name);
        int count=0;
        count=ps.executeUpdate();
        this.closeConnection();
        return count;
    }
    public double Consumerbalance(String name) throws Exception{
        this.initConnection();
        String sql="select balance from consumers where nickname=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, name);
        int count=0;
        double balance=0.0;
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        	balance=rs.getDouble("balance");
           
        this.closeConnection();
        return balance;
    }
}


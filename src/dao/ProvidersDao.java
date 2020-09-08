package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vo.*;

/**
 * 閽堝鏁版嵁搴撶殑澧炪�鍒犮�鏀广�鏌ワ紝骞朵笉鏄叏閮ㄩ兘鑳界敤鍒�
 * */
public class ProvidersDao {
    private Connection con=null;

    public void initConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AndroidBK", "root", "");
    }

    public void closeConnection() throws Exception{
        con.close();
    }

    /**
     * 鍒楀嚭鍏ㄩ儴鍟嗗
     * */
    public ArrayList<providers> listAllProviders() throws Exception{
        ArrayList<providers> list=new ArrayList<providers>();
        this.initConnection();
        String sql="select * from providers";
        PreparedStatement ps=con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        String out="";
        while(rs.next()) {
            providers un=new providers();
            un.setProviderid(rs.getString("providerid"));
            un.setName(rs.getString("name"));
            un.setAddress(rs.getString("address"));
            un.setRelates(rs.getString("relates"));
            list.add(un);
            out=out+rs.getString("providerid")+","+rs.getString("name")+
            		","+rs.getString("address")+","+rs.getString("relates")+",";
        }
        this.closeConnection();
        return list;
    }

    public String listAllProviders2() throws Exception{
        this.initConnection();
        String sql="select DISTINCT providers.* from providers,goods where goods.providerid=providers.providerid";
        PreparedStatement ps=con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        String out="";
        while(rs.next()) {
            out=out+rs.getString("providerid")+","+rs.getString("name")+
            		","+rs.getString("address")+","+rs.getString("relates")+",";
        }
        this.closeConnection();
        return out;
    }
    
    public String searchFuzzy(String keyword) throws Exception{
    	this.initConnection();
    	String sql="select DISTINCT providers.providerid,providers.`name`,providers.address,providers.relates from providers ,goods where (providers.name like CONCAT(CONCAT('%',?),'%') or goods.`name` LIKE CONCAT(CONCAT('%',?),'%')or providers.address LIKE CONCAT(CONCAT('%',?),'%') )and providers.providerid=goods.providerid";
        PreparedStatement ps=con.prepareStatement(sql);

        ps.setObject(1, keyword);
        ps.setObject(2, keyword);
        ps.setObject(3, keyword);
        ResultSet rs=ps.executeQuery();
        String out="";
        while(rs.next()) {
            out=out+rs.getString("providerid")+","+rs.getString("name")+
            		","+rs.getString("address")+","+rs.getString("relates")+",";
        }
        this.closeConnection();
        return out;
    }
    
    /**
     * 閫氳繃鑼楄尪鏌ユ壘鍟嗗
     * */
    public ArrayList<providers> selectByName(String name) throws Exception{
        this.initConnection();
        ArrayList<providers> list=new ArrayList<providers>();
        String sql="select * from providers where `name` like ? order by `name` asc";
        String sqlParasValue="%"+name+"%";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, sqlParasValue);
        ResultSet rs=ps.executeQuery();
        if(rs.next()) {
            providers un=new providers();
            un.setAddress(rs.getString("address"));
            un.setRelates(rs.getString("relates"));
            un.setName(rs.getString("name"));
            un.setProviderid(rs.getString("providerid"));
            list.add(un);
        }
        this.closeConnection();
        return list;
    }

    public String selectPwdById(String id) throws Exception{
        this.initConnection();
        String pwd="";
        String sql="select providersPwd from providers where providerid=?";
        String sqlParasValue="%"+id+"%";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, id);
        ResultSet rs=ps.executeQuery();
        if(rs.next()) {
            pwd+=rs.getString("providersPwd");
        }
        this.closeConnection();
        return pwd;
    }
    
    public String selectById(String id) throws Exception{
        this.initConnection();
        String pwd="";
        String sql="select * from providers where providerid=?";
        String sqlParasValue="%"+id+"%";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, id);
        ResultSet rs=ps.executeQuery();
        if(rs.next()) {
            pwd+=rs.getString("name")+","+rs.getString("providersPwd")
            		+","+rs.getString("address")+","+rs.getString("relates")
            		+","+rs.getString("telephone")+",";
        }
        this.closeConnection();
        return pwd;
    }
    
    
    public String selectTurnoverById(String id) throws Exception{
        this.initConnection();
        Date currentTime = new Date(); 
        //改变输出格式（自己想要的格式） 
        SimpleDateFormat formatter = new SimpleDateFormat("yy:MM:dd"); 
        //得到字符串时间 
        String s8 = formatter.format(currentTime); 
        String pwd="";
        String sql="select DISTINCT SUM(`order`.sumprices)as sum1,COUNT(`order`.ordernumber)as count1 from `order`,providers,orderrelate,goods where `order`.ordernumber LIKE CONCAT(CONCAT('%',?),'%') and providers.providerid=? and `order`.ordernumber=orderrelate.ordernumber and orderrelate.goodid=goods.goodid and goods.providerid=providers.providerid";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, s8);
        ps.setObject(2, id);
        ResultSet rs=ps.executeQuery();
        if(rs.next()) {
            pwd+=rs.getString("sum1")+","+rs.getString("count1");
        }
        this.closeConnection();
        return pwd;
    }
    
    /**
     * 鍒犻櫎鍟嗗
     * */
    public int deleteProviders(String name) throws Exception{
        this.initConnection();
        String sql="delete from providers where name=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, name);
        int count=0;
        count=ps.executeUpdate();
        this.closeConnection();
        return count;
    }

    /**
     * 娣诲姞鍟嗗
     * */
    public int addProviders(providers un) throws Exception{
        this.initConnection();
        String sql="insert into providers(providerid,name,address,relates,telephone,providersPwd) values(?,?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, un.getProviderid());
        ps.setObject(2, un.getName());
        ps.setObject(3, un.getAddress());
        ps.setObject(4,un.getRelates());
        ps.setObject(5, un.getTelephone());
        ps.setObject(6,un.getpassword());
        int count=0;
        count=ps.executeUpdate();
        this.closeConnection();
        return count;
    }

    /**
     * 淇敼鍟嗗淇℃伅
     * */
    public int updateProviders(providers un) throws Exception{
        this.initConnection();
        String sql="update providers set providerid=? address=? picture=? relates=? where name=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, un.getProviderid());
        ps.setObject(2, un.getAddress());
        ps.setObject(4, un.getRelates());
        int count=0;
        count=ps.executeUpdate();
        this.closeConnection();
        return count;
    }
    
    
    public int updateProviders2(providers un) throws Exception{
        this.initConnection();
        String sql="update providers set name=? ,address=?,providersPwd=?  ,relates=? ,telephone=? where providerid=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, un.getName());
        ps.setObject(2, un.getAddress());
        ps.setObject(3, un.getpassword());
        ps.setObject(4, un.getRelates());
        ps.setObject(5, un.getTelephone());
        ps.setObject(6, un.getProviderid());
        
        
        int count=0;
        count=ps.executeUpdate();
        this.closeConnection();
        return count;
    }

}


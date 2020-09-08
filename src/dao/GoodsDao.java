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
public class GoodsDao {
    private Connection con=null;

    public void initConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AndroidBK", "root", "");
    }

    public void closeConnection() throws Exception{
        con.close();
    }

    /**
     * 鍒楀嚭鍏ㄩ儴鍟嗗搧
     * */
    public ArrayList<goods> listAllGoods() throws Exception{
        ArrayList<goods> list=new ArrayList<goods>();
        this.initConnection();
        String sql="select * from goods";
        PreparedStatement ps=con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while(rs.next()) {
            goods un=new goods();
            un.setPrice(rs.getDouble("price"));
            un.setRelate(rs.getString("relate"));
            un.setPicture(rs.getString("picture"));
            un.setNumber(rs.getInt("number"));
            un.setName(rs.getString("name"));
            un.setGoodid(rs.getString("goodid"));
            un.setProviderid(rs.getString("providerid"));
            list.add(un);
        }
        this.closeConnection();
        return list;
    }

    /**
     * 妯＄硦鏌ヨ鍟嗗搧
     * */
    public ArrayList<goods> selectByName(String name) throws Exception{
        this.initConnection();
        ArrayList<goods> list=new ArrayList<goods>();
        String sql="select * from goods where `name` like ? order by `name` asc";
        String sqlParasValue="%"+name+"%";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, sqlParasValue);
        ResultSet rs=ps.executeQuery();
        while(rs.next()) {
            goods un=new goods();
            un.setPrice(rs.getDouble("price"));
            un.setRelate(rs.getString("relate"));
            un.setPicture(rs.getString("picture"));
            un.setNumber(rs.getInt("number"));
            un.setName(rs.getString("name"));
            un.setGoodid(rs.getString("goodid"));
            un.setProviderid(rs.getString("providerid"));
            list.add(un);
        }
        this.closeConnection();
        return list;
    }

    public 	String selectByProviderId(String providerId) throws Exception{
        this.initConnection();
        String result="";
        String sql="select * from goods where providerid= ?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, providerId);
        ResultSet rs=ps.executeQuery();
        int i=0;
        while(rs.next()) {
            result+=rs.getString("goodid")+","+rs.getString("name")
            		+","+rs.getString("price")+"#";
            System.out.println(i++);
        }
        if(result.equals(""))
        	result="empty";
        this.closeConnection();
        return result;
    }
    
    public 	String selectByProviderId2(String providerId) throws Exception{
        this.initConnection();
        String result="";
        String sql="select goods.*,goodsrelate.* from goods,goodsrelate where goods.goodid= ? and goods.goodid=goodsrelate.goodid";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, providerId);
        ResultSet rs=ps.executeQuery();
        

        while(rs.next()) {
            result+=rs.getString("name")
            		+","+rs.getString("price")+","+rs.getString("typename")+","+rs.getString("goodcon")+","+"糖 "+rs.getFloat("carbohydrate")+"g\n"
            		+"蛋白质 "+rs.getFloat("protein")+"g\n"+"脂肪 "+rs.getFloat("fat")+"g\n"+"维生素A "+rs.getFloat("vitaminA")+"μg\n"
            		+"维生素E "+rs.getFloat("vitaminE")+"mg\n"+"维生素C "+rs.getFloat("vitaminC")+"mg\n"
            		+"叶酸"+rs.getFloat("folicacid")+"μg\n"+"	维生素B6 "+rs.getFloat("vitaminB6")+"mg\n"
            		+"维生素B12 "+rs.getFloat("vitaminB12")+"mg\n"+"钙 "+rs.getFloat("calcium")+"mg\n"
            		+"铁 "+rs.getFloat("iron")+"mg\n"+"钾 "+rs.getFloat("potassium")+"mg\n"+"锌 "+rs.getFloat("zinc")+"mg\n"	
            		+"膳食纤维 "+rs.getFloat("cholesterol")+"g\n"+"	胆固醇 "+rs.getFloat("dietaryfiber")+"mg\n"+"能量 "+rs.getFloat("energy")+"kcal";
        }
        this.closeConnection();
        return result;
    }
    
    public 	String selectByfoodId(String foodId) throws Exception{
        this.initConnection();
        String result="";
        String sql="select goods.*,goodsrelate.* from goods,goodsrelate where goods.goodid= ? and goods.goodid=goodsrelate.goodid";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, foodId);
        ResultSet rs=ps.executeQuery();
        

        while(rs.next()) {
            result+=rs.getString("name")
            		+","+rs.getString("price")+","+rs.getString("typename")+","+rs.getString("goodcon");
        }
        this.closeConnection();
        return result;
    }
    
    
    
    
    public String selectByProvidersid(String id) throws Exception{
        this.initConnection();
        ArrayList<goods> list=new ArrayList<goods>();
        String sql="select goods.*,goodsrelate.* from goods,goodsrelate where goods.providerid= ? and goods.goodid=goodsrelate.goodid order by 'typeid' asc";

        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs=ps.executeQuery();
        int a=123456;
        String out="";
        int i=1;
        while(rs.next()) {
        	out=out+rs.getString("name")+","+String.valueOf(rs.getInt("price"))+","+String.valueOf(rs.getInt("typeid"))+","
        +rs.getString("typename")+","+String.valueOf(i)+","+rs.getString("goodid")+","+rs.getString("goodCon")+","+"能量 "+rs.getFloat("energy")+"kcal\n"+"糖 "+rs.getFloat("carbohydrate")+"g\n"
        		+"蛋白质 "+rs.getFloat("protein")+"g\n"+"脂肪 "+rs.getFloat("fat")+"g\n"+"维生素A "+rs.getFloat("vitaminA")+"μg\n"
        		+"维生素E "+rs.getFloat("vitaminE")+"mg\n"+"维生素C "+rs.getFloat("vitaminC")+"mg\n"
        		+"叶酸"+rs.getFloat("folicacid")+"μg\n"+"维生素B6 "+rs.getFloat("vitaminB6")+"mg\n"
        		+"维生素B12 "+rs.getFloat("vitaminB12")+"mg\n"+"钙 "+rs.getFloat("calcium")+"mg\n"
        		+"铁 "+rs.getFloat("iron")+"mg\n"+"钾 "+rs.getFloat("potassium")+"mg\n"+"锌 "+rs.getFloat("zinc")+"mg\n"	
        		+"膳食纤维 "+rs.getFloat("cholesterol")+"g\n"+"胆固醇 "+rs.getFloat("dietaryfiber")+"mg\n"+",";
      System.out.print(a);
      i++;
        }
        System.out.println(out);
        this.closeConnection();
        return out;
    }
    
    /**
     * 鍒犻櫎鍟嗗搧
     * */
    public int deleteGoods(String name) throws Exception{
        this.initConnection();
        String sql="delete from goods where goods.goodid=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, name);
        String sql2="delete from goodsrelate where goodsrelate.goodid=?";
        PreparedStatement ps2=con.prepareStatement(sql2);
        ps2.setObject(1, name);
        
         
        int count=0;
        count=ps2.executeUpdate();
        count=ps.executeUpdate();
        this.closeConnection();
        return count;
    }

    /**
     * 娣诲姞鍟嗗搧
     * */
    public int addGoods(goods un) throws Exception{
        this.initConnection();
        String sql="insert into goods values(?,?,?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, un.getPrice());
        ps.setObject(2, un.getRelate());
        ps.setObject(3, un.getPicture());
        ps.setObject(4, un.getNumber());
        ps.setObject(5, un.getName());
        ps.setObject(6, un.getGoodid());
        ps.setObject(7, un.getProviderid());
        int count=0;
        count=ps.executeUpdate();
        this.closeConnection();
        return count;
    }

    public int addGoods2(goods un,String a) throws Exception{
        this.initConnection();
        String sql="insert into goods(price,relate,typename,number,name,goodid,providerid) values(?,?,?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, un.getPrice());
        ps.setObject(2, un.getRelate());
        ps.setObject(3, un.getType());
        ps.setObject(4, un.getNumber());
        ps.setObject(5, un.getName());
        ps.setObject(6, un.getGoodid());
        ps.setObject(7, un.getProviderid());
        int count=0;
        float carbohydrate=0,protein=0,fat=0,A=0,E=0,C=0,fa=0,B6=0,B12=0,cal=0,iron=0,pot=0,zinc=0,chole=0,dietary=0,energy=0;
        count=ps.executeUpdate();
        String sql3="select * from material where materialName=?";
        PreparedStatement ps3=con.prepareStatement(sql3);
        String[] spite=a.split(" ");
        for(int i=0;i<spite.length/2;i++){
        	ps3.setObject(1, spite[2*i]);
        	ResultSet rs=ps3.executeQuery();
        	float f=Float.parseFloat(spite[2*i+1])/100;
        	while(rs.next()){
        		carbohydrate+=f*rs.getFloat("carbohydrate");
        		protein+=f*rs.getFloat("protein");
        		fat+=f*rs.getFloat("fat");
        		A+=f*rs.getFloat("vitaminA");
        		E+=f*rs.getFloat("vitaminE");
        		C+=f*rs.getFloat("vitaminC");
        		B6+=f*rs.getFloat("vitaminB6");
        		B12+=f*rs.getFloat("vitaminB12");
        		fa+=f*rs.getFloat("folicacid");
        		cal+=f*rs.getFloat("calcium");
        		iron+=f*rs.getFloat("iron");
        		pot+=f*rs.getFloat("potassium");
        		zinc+=f*rs.getFloat("zinc");
        		chole+=f*rs.getFloat("cholesterol");
        		dietary+=f*rs.getFloat("dietaryfiber");
        		energy+=f*rs.getFloat("energy");
        		
        		
        	}
        }
        
        String sql2="insert into goodsrelate values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps2=con.prepareStatement(sql2);
        ps2.setObject(1, un.getGoodid());
        ps2.setObject(2, a);
        ps2.setFloat(3, carbohydrate);
        ps2.setFloat(4, protein);        
        ps2.setFloat(5, fat);
        ps2.setFloat(6, A);
        ps2.setFloat(7, E);
        ps2.setFloat(8, C);
        ps2.setFloat(9, fa);
        ps2.setFloat(10, B6);
        ps2.setFloat(11, B12);
        ps2.setFloat(12, cal);
        ps2.setFloat(13, iron);
        ps2.setFloat(14, pot);
        ps2.setFloat(15, zinc);
        ps2.setFloat(16, chole);
        ps2.setFloat(17, dietary);
        ps2.setFloat(18, energy);
        ps2.executeUpdate();
        
        
        this.closeConnection();
        
        return count;
    }
    
    public int updateGoods2(goods un,String a) throws Exception{
        this.initConnection();
        String sql="update goods set price=?,relate=?,typename=?,number=?,name=? where goodid=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, un.getPrice());
        ps.setObject(2, un.getRelate());
        ps.setObject(3, un.getType());
        ps.setObject(4, un.getNumber());
        ps.setObject(5, un.getName());
        ps.setObject(6,un.getGoodid());
        int count=0;
        float carbohydrate=0,protein=0,fat=0,A=0,E=0,C=0,fa=0,B6=0,B12=0,cal=0,iron=0,pot=0,zinc=0,chole=0,dietary=0,energy=0;
        count=ps.executeUpdate();
        String sql3="select * from material where materialName=?";
        PreparedStatement ps3=con.prepareStatement(sql3);
        String[] spite=a.split(" ");
        for(int i=0;i<spite.length/2;i++){
        	ps3.setObject(1, spite[2*i]);
        	ResultSet rs=ps3.executeQuery();
        	float f=Float.parseFloat(spite[2*i+1])/100;
        	while(rs.next()){
        		carbohydrate+=f*rs.getFloat("carbohydrate");
        		protein+=f*rs.getFloat("protein");
        		fat+=f*rs.getFloat("fat");
        		A+=f*rs.getFloat("vitaminA");
        		E+=f*rs.getFloat("vitaminE");
        		C+=f*rs.getFloat("vitaminC");
        		B6+=f*rs.getFloat("vitaminB6");
        		B12+=f*rs.getFloat("vitaminB12");
        		fa+=f*rs.getFloat("folicacid");
        		cal+=f*rs.getFloat("calcium");
        		iron+=f*rs.getFloat("iron");
        		pot+=f*rs.getFloat("potassium");
        		zinc+=f*rs.getFloat("zinc");
        		chole+=f*rs.getFloat("cholesterol");
        		dietary+=f*rs.getFloat("dietaryfiber");
        		energy+=f*rs.getFloat("energy");
        		
        		
        	}
        }
        
        String sql2="update goodsrelate set goodcon=?,carbohydrate=?,protein=?,fat=?,vitaminA=?,vitaminE=?,vitaminC=?,folicacid=?,vitaminB6=?,vitaminB12=?,calcium=?,iron=?,potassium=?,zinc=?,cholesterol=?,dietaryfiber=?,energy=?where goodid=?";

        PreparedStatement ps2=con.prepareStatement(sql2);

        ps2.setObject(1, a);
        ps2.setFloat(2, carbohydrate);
        ps2.setFloat(3, protein);        
        ps2.setFloat(4, fat);
        ps2.setFloat(5, A);
        ps2.setFloat(6, E);
        ps2.setFloat(7, C);
        ps2.setFloat(8, fa);
        ps2.setFloat(9, B6);
        ps2.setFloat(10, B12);
        ps2.setFloat(11, cal);
        ps2.setFloat(12, iron);
        ps2.setFloat(13, pot);
        ps2.setFloat(14, zinc);
        ps2.setFloat(15, chole);
        ps2.setFloat(16, dietary);
        ps2.setFloat(17, energy);
        ps2.setObject(18, un.getGoodid());
        ps2.executeUpdate();
        this.closeConnection();
        
        return count;
    }
    
    
    
    /**
     * 淇敼鍟嗗搧淇℃伅
     * */
    public int updateGoods(goods un) throws Exception{
        this.initConnection();
        String sql="update goods set price=? relate=? picture=? `number`=? goodid=? providerid=? where `name`=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1, un.getPrice());
        ps.setObject(2, un.getRelate());
        ps.setObject(3, un.getPicture());
        ps.setObject(4, un.getNumber());
        ps.setObject(5, un.getGoodid());
        ps.setObject(6, un.getProviderid());
        int count=0;
        count=ps.executeUpdate();
        this.closeConnection();
        return count;
    }

}


package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.math.distribution.NormalDistribution;
import org.apache.commons.math.distribution.NormalDistributionImpl;

import com.kmeans.Kmeans;
import com.kmeans.Kmeans_data;
import com.kmeans.Kmeans_param;


public class EvalutionDao {
	private Connection con=null;

    public void initConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AndroidBK", "root", "");
    }

    public void closeConnection() throws Exception{
        con.close();
    }
    
    public String selectOrderByProviderIdDate(String nickname,String s8) throws Exception{
        String a="";       
        this.initConnection();
        
        float carbohydrate=0,protein=0,fat=0,A=0,E=0,C=0,fa=0,B6=0,B12=0,cal=0,iron=0,pot=0,zinc=0,chole=0,dietary=0,energy=0;
        Date currentTime = new Date(); 
        //�ı������ʽ���Լ���Ҫ�ĸ�ʽ�� 
        SimpleDateFormat formatter = new SimpleDateFormat("yy:MM:dd"); 
        //�õ��ַ�ʱ�� 
        //String s8 = formatter.format(new Date(currentTime.getTime() - (long)24 * 60 * 60 * 1000)); 
        
        String sql2="select DISTINCT goodsrelate.*,orderrelate.goodnumber from orderrelate,goods,goodsrelate,`order` where `order`.ordernumber LIKE CONCAT(CONCAT('%',?),'%') AND orderrelate.goodid=goods.goodid AND `order`.ordernumber=orderrelate.ordernumber and `order`.nickname=? and goods.goodid=goodsrelate.goodid";
        PreparedStatement ps2=con.prepareStatement(sql2);
        ps2.setObject(1, s8);
        ps2.setObject(2, nickname);
        ResultSet rs=ps2.executeQuery();
        int number=0;
        	while(rs.next()){
        		number=rs.getInt("goodnumber");
        		carbohydrate+=number*rs.getFloat("carbohydrate");
        		protein+=number*rs.getFloat("protein");
        		fat+=number*rs.getFloat("fat");
        		A+=number*rs.getFloat("vitaminA");
        		E+=number*rs.getFloat("vitaminE");
        		C+=number*rs.getFloat("vitaminC");
        		B6+=number*rs.getFloat("vitaminB6");
        		B12+=number*rs.getFloat("vitaminB12");
        		fa+=number*rs.getFloat("folicacid");
        		cal+=number*rs.getFloat("calcium");
        		iron+=number*rs.getFloat("iron");
        		pot+=number*rs.getFloat("potassium");
        		zinc+=number*rs.getFloat("zinc");
        		chole+=number*rs.getFloat("cholesterol");
        		dietary+=number*rs.getFloat("dietaryfiber");
        		energy+=number*rs.getFloat("energy");        		        	
        }
        	if(energy==0)
            	{a="empty";
            	this.closeConnection();
                return a;}
        double standard[]=calum(nickname);
        double result[] = new double[17];
        NormalDistributionImpl normalDistribution = 
        		new NormalDistributionImpl(0,1); 
        double S1=normalDistribution.density(0.2);
        normalDistribution = new NormalDistributionImpl(standard[0],standard[0]/3); 
        result[0]=100*normalDistribution.density(energy)/normalDistribution.density(standard[0]);
        System.out.println("@@"+normalDistribution.density(energy));
        System.out.println(normalDistribution.density(standard[0])+"@@");
        System.out.println(result[0]+"@@");
        normalDistribution = new NormalDistributionImpl(standard[1],standard[1]/3); 
        result[1]=100*normalDistribution.density(carbohydrate)/normalDistribution.density(standard[1]);
        
        normalDistribution = new NormalDistributionImpl(standard[2],standard[2]/2); 
        result[2]=100*normalDistribution.density(protein)/normalDistribution.density(standard[2]);
        
        normalDistribution = new NormalDistributionImpl(standard[3],standard[3]/3); 
        result[3]=100*normalDistribution.density(fat)/normalDistribution.density(standard[3]);
        
        normalDistribution = new NormalDistributionImpl(800,800/3); 
        result[4]=100*normalDistribution.density(A)/normalDistribution.density(800);
        
        normalDistribution = new NormalDistributionImpl(14,14/3); 
        result[5]=100*normalDistribution.density(E)/normalDistribution.density(14);
        
        normalDistribution = new NormalDistributionImpl(100,100/3); 
        result[6]=100*normalDistribution.density(C)/normalDistribution.density(100);
        
        normalDistribution = new NormalDistributionImpl(400,400/3); 
        result[7]=100*normalDistribution.density(fa)/normalDistribution.density(400);
        normalDistribution = new NormalDistributionImpl(1.4,1.4/3); 
        result[8]=100*normalDistribution.density(B6)/normalDistribution.density(1.4);
        normalDistribution = new NormalDistributionImpl(2.4,2.4/3); 
        result[9]=100*normalDistribution.density(B12)/normalDistribution.density(2.4);
        normalDistribution = new NormalDistributionImpl(800,800/3); 
        result[10]=100*normalDistribution.density(cal)/normalDistribution.density(800);
        normalDistribution = new NormalDistributionImpl(12,12/3); 
        result[11]=100*normalDistribution.density(iron)/normalDistribution.density(12);
        normalDistribution = new NormalDistributionImpl(2000,2000/3); 
        result[12]=100*normalDistribution.density(pot)/normalDistribution.density(2000);
        normalDistribution = new NormalDistributionImpl(12.5,12.5/3); 
        result[13]=100*normalDistribution.density(zinc)/normalDistribution.density(12.5);
        normalDistribution = new NormalDistributionImpl(25,25/3); 
        result[14]=100*normalDistribution.density(chole)/normalDistribution.density(25);
        normalDistribution = new NormalDistributionImpl(200,200/3); 
        result[15]=100*normalDistribution.density(dietary)/normalDistribution.density(200);
        result[16]=0;
        for(int i=0;i<4;i++){
        	result[16]+=result[i];
        }
        for(int i=4;i<16;i++)
        	result[16]+=0.2*result[i];
        DecimalFormat    df   = new DecimalFormat("######0.00"); 
        result[16]/=(4+12*0.2);       	        	
        /*for(int i=0;i<4;i++)  	
        a+=standard[i]+",";*/
        for(int i=0;i<4;i++)
        	a+=df.format(result[i])+",";
        a+=df.format(result[16])+"#";
        
        /*a+=energy+","+carbohydrate+","+protein+","+fat+","+A+","+E+","+C
        		+","+fa+","+B6+","+B12+","+cal+","+iron+","+pot+","+zinc
        		+","+chole+","+dietary+",";*/
        
        a+="能量(kcal) "+","+energy+","+standard[0]+","
        +"糖 (g)"+","+carbohydrate+","+standard[1]+","
		+"蛋白质(g) "+","+protein+","+standard[2]+","
        +"脂肪(g) "+","+fat+","+standard[3]+","
		+"维生素A(μg) "+","+A+","+800+","
		+"维生素E(mg)"+","+E+","+14+","
		+"维生素C(mg) "+","+C+","+100+","
		+"叶酸(μg)"+","+fa+","+400+","
		+"维生素B6(mg) "+","+B6+","+1.4+","
		+"维生素B12(mg) "+","+B12+","+2.4+","
		+"钙(mg) "+","+cal+","+800+","
		+"铁(mg) "+","+iron+","+12+","
		+"钾(mg)"+","+pot+","+2000+","
		+"锌(mg) "+","+zinc+","+12.5+","
		+"膳食纤维(g) "+","+chole+","+25+","
		+"胆固醇(mg) "+","+dietary+","+200+",";
        
        if(a.equals(""))
        	a="empty";
        
        this.closeConnection();
        return a;
    }
    
    public String getAdvice(String nickname) throws Exception{
    	String a="";
    	this.initConnection();
    	
    	float carbohydrate=0,protein=0,fat=0,A=0,E=0,C=0,fa=0,B6=0,B12=0,cal=0,iron=0,pot=0,zinc=0,chole=0,dietary=0,energy=0;
        Date currentTime = new Date(); 
        //�ı������ʽ���Լ���Ҫ�ĸ�ʽ�� 
        SimpleDateFormat formatter = new SimpleDateFormat("yy:MM:dd"); 
        //�õ��ַ�ʱ�� 
        String s8 = formatter.format(currentTime); 
        
              
        String sql2="select DISTINCT goodsrelate.*,orderrelate.goodnumber from orderrelate,goods,goodsrelate,`order` where `order`.ordernumber LIKE CONCAT(CONCAT('%',?),'%') AND orderrelate.goodid=goods.goodid AND `order`.ordernumber=orderrelate.ordernumber and `order`.nickname=? and goods.goodid=goodsrelate.goodid";
        PreparedStatement ps2=con.prepareStatement(sql2);
        
        ps2.setObject(1, s8);
        ps2.setObject(2, nickname);
        ResultSet rs=ps2.executeQuery();   
        int num=0;
        	while(rs.next()){
        		num=rs.getInt("goodnumber");
        		carbohydrate+=num*rs.getFloat("carbohydrate");
        		protein+=num*rs.getFloat("protein");
        		fat+=num*rs.getFloat("fat");       	
        		energy+=num*rs.getFloat("energy");        		        	
        }
        	DecimalFormat    df   = new DecimalFormat("######0.00");  	        	
        	double standard[]=calum(nickname);
        	double[] result=new double[4];
        	result[0]=standard[0]-energy;
        	result[1]=standard[1]-carbohydrate;
        	result[2]=standard[2]-protein;
        	result[3]=standard[3]-fat;
        	for(int i=0;i<4;i++){
            	a+=df.format(result[i])+",";
            }
    	return a;
    }
    
    public String getWeek(String nickname) throws Exception{
    	String a="";
    	this.initConnection();
    	float[] carbohydrate = new float[8],protein =new float[8],fat =new float[8],energy =new float[8];
        Date currentTime = new Date(); 
        //�ı������ʽ���Լ���Ҫ�ĸ�ʽ�� 
        SimpleDateFormat formatter = new SimpleDateFormat("yy:MM:dd"); 
        //�õ��ַ�ʱ�� 
        int i=0;
        String s8=null;
                               
        String sql2="select DISTINCT goodsrelate.energy,orderrelate.goodnumber from orderrelate,goods,goodsrelate,`order` where `order`.ordernumber LIKE CONCAT(CONCAT('%',?),'%') AND orderrelate.goodid=goods.goodid AND `order`.ordernumber=orderrelate.ordernumber and `order`.nickname=? and goods.goodid=goodsrelate.goodid";
        PreparedStatement ps3=con.prepareStatement(sql2);
        for(i=1;i<8;i++){
        s8 = formatter.format(new Date(currentTime.getTime() - (long)24 * 60 * 60 * 1000*i));
        ps3.setObject(1, s8);
        ps3.setObject(2, nickname);
        ResultSet rs=ps3.executeQuery();     
        energy[i]=0;
        	while(rs.next()){
        		    	
        		energy[i]+=rs.getFloat("energy");        		        	
        }
    	
        }
    	
        a+=formatter.format(currentTime)+",";
        for(int i2=1;i2<8;i2++){
        	a+=energy[i2]+",";
        }
        
        
        
    	return a;
    
    	
    }
    
    
    public double[] calum(String nickname ) throws Exception{
    	 String a="";
         String gender="";
         int age=0;
         double height=0;
         double weight=0;
         double[] result=new double[5];
         int state=0;
         this.initConnection();
         String sql="select distinct * from consumers where nickname=?";
         PreparedStatement ps=con.prepareStatement(sql);
         ps.setObject(1, nickname);
         ResultSet rs=ps.executeQuery();
         while(rs.next()) {
             gender=rs.getString("gender");
             age=rs.getInt("age");
             height=rs.getDouble("height");
             weight=rs.getDouble("weight");
         }
         double kcal=0;
         if(gender.equals("男")){
         	kcal=(66+13.7*weight+5*height-6.8*age)*1.5;
         }else{
         	kcal=1.5*(655+9.6*weight+1.8*height-4.7*age);
         }
    	kcal+=state*300;
    	DecimalFormat  df=new DecimalFormat("######0.00"); 
    	double fat=kcal*0.2/9;
    	double protein=weight*1.5;
    	double sugar=(kcal*0.8-protein*4)/4;    	    	
    	result[0]=((int)100*kcal)/100;
    	result[1]=((int)100*sugar)/100;
    	result[2]=((int)100*protein)/100;
    	result[3]=Double.parseDouble(df.format(fat));
    	//System.out.println(result[3]+(int)100*fat);
    	return result;
    }
    
    public String getDataTop10ToKmeans() throws Exception{
    	 String a="";
         String[] gender=new String[10];
         Double[] number=new Double[10];
         String[][] means=new String[3][10];
         int age=0;
         double[][] data=new double[10][17];
         double[] result=new double[5];
         int state=0;
         int i=0;
         this.initConnection();
         String sql="select DISTINCT goods.*,goodsrelate.*,sum( orderrelate.goodnumber)as a from goods,goodsrelate,`order`,orderrelate where `order`.ordernumber=orderrelate.ordernumber and orderrelate.goodid=goods.goodid   and goods.goodid=goodsrelate.goodid  GROUP BY goods.goodid order by a DESC LIMIT 10;";
         PreparedStatement ps=con.prepareStatement(sql);
         ResultSet rs=ps.executeQuery();
         while(rs.next()) {
            gender[i]=rs.getString("name");
            number[i]=rs.getDouble("a");
            data[i][0]=rs.getFloat("price");
            data[i][1]=rs.getFloat("carbohydrate");
            data[i][2]=rs.getFloat("protein");
            data[i][3]=rs.getFloat("fat");
            data[i][4]=rs.getFloat("vitaminA");
            data[i][5]=rs.getFloat("vitaminE");
            data[i][6]=rs.getFloat("vitaminC");
            data[i][7]=rs.getFloat("vitaminB6");
            data[i][8]=rs.getFloat("vitaminB12");
            data[i][9]=rs.getFloat("folicacid");
            data[i][10]=rs.getFloat("calcium");
            data[i][11]=rs.getFloat("iron");
            data[i][12]=rs.getFloat("potassium");
            data[i][13]=rs.getFloat("zinc");
            data[i][14]=rs.getFloat("cholesterol");
            data[i][15]=rs.getFloat("dietaryfiber");
            data[i][16]=rs.getFloat("energy");  
            i++;      
         }
        Kmeans_data data2 = new Kmeans_data(data, i, 17); // 初始化数据结构
 		Kmeans_param param = new Kmeans_param(); // 初始化参数结构
 		param.initCenterMehtod = Kmeans_param.CENTER_RANDOM; // 设置聚类中心点的初始化模式为随机模式

 		// 做kmeans计算，分两类
 		Kmeans.doKmeans(3, data2, param);

 		// 查看每个点的所属聚类标号
 		System.out.print("The labels of points is: ");
 		
 		for(int j=0;j<3;j++)
 		{	for(int k=0;k<i;k++)
 			{
 				if(data2.labels[k]==j)
 					a+=gender[k]+"月销"+number[k]+"\n";				
 			}
 			a+="#";
 		}		


         
    	return a;
    	
    }
    
    
    
    
    
}

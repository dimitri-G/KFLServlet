package relateInterface;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vo.consumers;
import vo.order;
import vo.providers;

public interface Shopping {
	public String selectByProvidersid(String id);//通过商家id获得商家及其菜品信息
	public int updateOrderState(String orderNum,int state);//更新订单状态
	public String selectCommentByProviderId(String nickname);//获得对商家评价
    public String listAllProviders2();//获得所有商家列表
	public String selectByProviderId(String providerId);//获得商家中菜品信息
	public String selectOrderByNickname(String nickname);//获得用户的所有订单信息
	public String selectOrderByNumber(String ordernumber);//获得订单详细信息
	public int addOrder(order un);//下单
	
	
	
}

package relateInterface;

import vo.goods;
import vo.providers;

public interface ProviderManger {
	public String selectById(String id);//获得商户信息
	public String selectTurnoverById(String id);//获得当天营业情况
	public String selectPwdById(String id);//商户登录
    public int updateProviders2(providers un);//更新用户
	public int addProviders(providers un);//商户注册
	public int addGoods2(goods un,String a);//新建菜品
	public int updateGoods2(goods un,String a);//更新菜品
	public int deleteGoods(String name);//删除菜品
	public 	String selectByfoodId(String foodId);//获得菜品概要信息
	public 	String selectByfoodId2(String foodId);//获得菜品细节信息
    public String selectOrderByProvidername(String nickname,int state1,int state2);//获得订单信息
    
	
}

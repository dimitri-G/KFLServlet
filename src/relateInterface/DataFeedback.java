package relateInterface;

public interface DataFeedback {
	public int updateOrderCommit(String orderNum,int ranking,String commit,int state);//增加用户对菜品评价
	public int updateOrderComplain(String orderNum,String complain,int state);//增加用户投诉；
	
}

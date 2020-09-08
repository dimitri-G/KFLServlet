package relateInterface;

public interface Analyse {
	public String getAdvice(String nickname);//为用户提供每日摄入指导
	public String getWeek(String nickname);//获得用户近7天能量摄入量
	public String getAnalyseByDate(String nickname,String s8);//对某日用户摄入营养健康评估
	public String getDataTop10ToKmeans();//kmeans聚类分析高销量菜品
	
}

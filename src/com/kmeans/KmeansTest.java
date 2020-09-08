package com.kmeans;

public class KmeansTest {
	public static void main(String[] args) {
		double[][] points = { { 0, 0 ,0}, { 4, 10,1 }, { 1, 1,2}, { 5, 8 ,3} }; // 测试数据，四个二维的点
		Kmeans_data data = new Kmeans_data(points, 4, 3); // 初始化数据结构
		Kmeans_param param = new Kmeans_param(); // 初始化参数结构
		param.initCenterMehtod = Kmeans_param.CENTER_RANDOM; // 设置聚类中心点的初始化模式为随机模式

		// 做kmeans计算，分两类
		Kmeans.doKmeans(3, data, param);

		// 查看每个点的所属聚类标号
		System.out.print("The labels of points is: ");
		for (int lable : data.labels) {
			System.out.print(lable + "  ");
		}
	}
}

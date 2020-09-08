package com.kmeans;

/**
 *
 * @param <b>data</b> <i>in double[length][dim]</i><br/>length��instance����꣬��i(0~length-1)��instanceΪdata[i]
 * @param <b>length</b> <i>in</i> instance����
 * @param <b>dim</b> <i>in</i> instanceά��
 * @param <b>label</b> <i>out int[length]</i><br/>�����instance�����ľ�����(0~k-1)
 * @param <b>centers</b> <i>in out double[k][dim]</i><br/>k���������ĵ����꣬��i(0~k-1)�����ĵ�Ϊcenters[i]
 * @author Yuanbo She
 *
 */
public class Kmeans_data {
	public double[][] data;
	public int length;
	public int dim;
	public int[] labels;
	public double[][] centers;
	public int[] centerCounts;
	
	public Kmeans_data(double[][] data, int length, int dim) {
		this.data = data;
		this.length = length;
		this.dim = dim;
	}
}

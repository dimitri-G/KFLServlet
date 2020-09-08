package vo;

import java.math.BigDecimal;

public class orderrelate {
    private String ordernumber;
    private String goodid;
    private int goodnumber;
    private double goodprice;

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getGoodid() {
        return goodid;
    }

    public void setGoodid(String goodid) {
        this.goodid = goodid;
    }

    public int getGoodnumber() {
        return goodnumber;
    }

    public void setGoodnumber(int goodnumber) {
        this.goodnumber = goodnumber;
    }

    public double getGoodprice() {
        return goodprice;
    }

    public void setGoodprice(double goodprice) {
        this.goodprice = goodprice;
    }

	
}

package vo;

import java.math.BigDecimal;

public class order {
    private String ordertime;
    private String ordernumber;
    private double sumprices;
    private String nickname;
    private int state;
    
    public int getState(){
    	return state;
    }
    
    public void setState(int state)
    {
    	this.state=state;
    }
    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public double getSumprices() {
        return sumprices;
    }

    public void setSumprices(double d) {
        this.sumprices = d;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

package vo;

public class consumers {
    private String nickname;    //非空
    private String password;    //非空
    private String gender;
    private int age;
    private String address;     //非空
    private String telephone;   //非空
    private String mail;
    private String Remarks;
    private double balance;
    private double height;
    private double weight;
    
    public  double getHeight()
    {
    	return height;
    }
    
    public double getWeight(){
    	return weight;
    }
    
    public void setHeight(double h){
    	height=h;
    }
    
    public void setWeight(double w){
    	weight=w;
    }
    
    
    public double getBalance(){
    	return balance;
    }
    
    public void setBalance(double balance){
    	this.balance=balance;
    }
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

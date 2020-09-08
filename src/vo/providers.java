package vo;

public class providers {
    private String providerid;
    private String password;
    private String name;
    private String address;
    private String relates;
    private String telephone;
    
    public providers(String _providerid,String _password,String _name,String _address
    		,String _relates,String _telephone){
    	providerid=_providerid;
    	password=_password;
    	name=_name;
    	address=_address;
    	relates=_relates;
    	telephone=_telephone;
    	
    }
    
    public providers(){
    	
    }
    
    
    public void setTelephone(String _telephone){
    	telephone=_telephone;
    }
    
    public String getTelephone(){
    	return telephone;
    }
    
    public String getpassword(){
    	return password;
    }
    
    public void setpassword(String _password){
		password=_password;
    	
    }
    
    public String getProviderid() {
        return providerid;
    }

    public void setProviderid(String providerid) {
        this.providerid = providerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getRelates() {
        return relates;
    }

    public void setRelates(String relates) {
        this.relates = relates;
    }
}

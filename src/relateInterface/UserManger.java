package relateInterface;

import vo.consumers;

public interface UserManger {
	public consumers findByNickname(String nickname);//登录
	public int addConsumer(consumers un);//用户注册
    public int updateConsumer(consumers un);//更新用户信息
}

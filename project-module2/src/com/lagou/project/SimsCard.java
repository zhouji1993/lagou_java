package com.lagou.project;

/*
* 3. 按照要求设计并实现以下实体类和接口。
    3.1 第一步：设计和实现以下类
    （1）手机卡类 特征：卡类型、卡号、用户名、密码、账户余额、通话时长(分钟)、上网流量 行为：显示（卡号 + 用户名 + 当前余额）
    （2）通话套餐类 特征：通话时长、短信条数、每月资费 行为: 显示所有套餐信息
    （3）上网套餐类 特征：上网流量、每月资费 行为：显示所有套餐信息
    （4）用户消费信息类 特征：统计通话时长、统计上网流量、每月消费金额
    3.2 第二步：设计和实现以下枚举类 手机卡的类型总共有 3 种：大卡、小卡、微型卡
    3.3 第三步：实体类的优化 将通话套餐类和上网套餐类中相同的特征和行为提取出来组成抽象套餐类。
    3.4 第四步：创建并实现以下接口
    （1）通话服务接口 抽象方法: 参数1: 通话分钟, 参数2: 手机卡类对象 让通话套餐类实现通话服务接口。
    （2）上网服务接口 抽象方法: 参数1: 上网流量, 参数2: 手机卡类对象 让上网套餐类实现上网服务接口。
3.5 第五步：进行代码测试
编写测试类使用多态格式分别调用上述方法，方法体中打印一句话进行功能模拟即可。

* */
import java.sql.Timestamp;
import java.util.*;

public class SimsCard {

//    卡类型、卡号、用户名、密码、账户余额、通话时长(分钟)、上网流量 行为：显示（卡号 + 用户名 + 当前余额）
     CardType CardType;
    private String PhoneNum;
    private String UserName;
    private String Password;
    private Double balance;
    private Double CCT;
    private Double NetFlow;
    public SimsCard(){};
    public SimsCard( CardType CardType,String PhoneNum,String UserName,String Password,Double balance,Double CCT,Double NetFlow){
        setCardType(CardType);
        setPhoneNum(PhoneNum);
        setUserName(UserName);
        setPassword(Password);
        setBalance(balance);
        setCCT(CCT);
        setNetFlow(NetFlow);

    }

    public void setCardType(CardType cardType) {
        this.CardType = cardType;
    }

    public CardType getCardType() {
        return CardType;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setCCT(Double CCT) {
        this.CCT = CCT;
    }

    public void setNetFlow(Double netFlow) {
        NetFlow = netFlow;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setPerson(Map person) {
        this.person = person;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public String getPassword() {
        return Password;
    }

    public Map getPerson() {
        return person;
    }

    public Double getNetFlow() {
        return NetFlow;
    }

    public Double getCCT() {
        return CCT;
    }

    public Double getBalance() {
        return balance;
    }

    private Map person = new HashMap();

    private Map setPerson() {
        this.balance=balance;
        this.UserName=UserName;
        this.PhoneNum=PhoneNum;
        this.person=person;
        person.put("PhoneNum",PhoneNum);
        person.put("UserName",UserName);
        person.put("balance",balance);
        return person;
    }

    protected void showPerson(){
        System.out.printf("卡号："+person.get("PhoneNum")+"\n 用户名："+person.get("UserName")+"\n 账户余额："+person.get("balance"));
    }

}

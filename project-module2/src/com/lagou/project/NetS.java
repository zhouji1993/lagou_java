package com.lagou.project;

import java.text.MessageFormat;

/*
* （3）上网套餐类 特征：上网流量、每月资费 行为：显示所有套餐信息
*       重写
*
* */
public class NetS extends AbstractUserTalkNew implements TalkServices  {
    private double netFlow ;
    private double netBill;
    CardType cardType;


    public NetS(){ };

    public NetS(double netFlow,double netBill){
        super();
        setNetFlow(netFlow);
    }

    public double getNetFlow() {
        return netFlow;
    }

    public double getNetBill() {
        return netBill;
    }

    public void setNetBill(double netBill) {
        this.netBill = netBill;
    }

    public void setNetFlow(double netFlow) {
        this.netFlow = netFlow;
    }

    public void Netshow(){
        this.netBill = getNetBill();
        this.netFlow = getNetFlow();
        StringBuilder strings=new StringBuilder();
        strings.append("上网流量:" );
        strings.append(netFlow);
        strings.append("\n每月资费:");
        strings.append(netBill);

        System.out.println(strings);

    }


    @Override
    public void showBills(double Bill) {
        System.out.println(MessageFormat.format("Bill{0}", getNetBill()));

    }





    @Override
    public void countTTC(long TTC, SimsCard cardType) {

    }
}

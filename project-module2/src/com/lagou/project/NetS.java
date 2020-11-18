package com.lagou.project;
/*
* （3）上网套餐类 特征：上网流量、每月资费 行为：显示所有套餐信息
*
*
* */
public class NetS {
    private double netFlow ;
    private double netBill;


    public NetS(double netFlow,double netBill){
        setNetBill(netBill);
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


}

package com.lagou.project;

import java.util.ArrayList;
import java.util.Hashtable;


public class Talks {
       /* （2）通话套餐类 特征：通话时长、短信条数、每月资费 行为: 显示所有套餐信息

        */

    private Long TTC;
    private Long SMSCount;
    private Double Bill;
public Talks(Long TTC,Long SMSCount,Double Bill){
    setBill(Bill);
    setSMSCount(SMSCount);
    setTTC(TTC);
}

    public Double getBill() {
        return Bill;
    }

    public Long getSMSCount() { return SMSCount; }

    public Long getTTC() {
        return TTC;
    }

    public void setBill(Double Bill) {
        this.Bill = Bill;
    }

    public void setSMSCount(Long SMSCount) {
        this.SMSCount = SMSCount;
    }

    public void setTTC(Long TTC) {
        this.TTC = TTC;
    }

    public void talksShow(){
         this.Bill=getBill();
         this.SMSCount=getSMSCount();
         this.TTC=getTTC();
         StringBuilder strings=new StringBuilder();
            strings.append("通话时长:" );
            strings.append(TTC);
            strings.append("\n短信条数:");
            strings.append(SMSCount);
            strings.append("\n每月资费:");
            strings.append(Bill);
            System.out.println(strings);
    }

}

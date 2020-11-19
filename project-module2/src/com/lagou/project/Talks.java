package com.lagou.project;

public class Talks extends AbstractUserTalkNew implements TalkServices{
       /* （2）通话套餐类 特征：通话时长、短信条数、每月资费 行为: 显示所有套餐信息

        */
    CardType cardType;
    private Long TTC;
    private Long SMSCount;
    private Double Bill;

    public Talks(){};
public Talks(Long TTC,Long SMSCount,Double Bill ,CardType cardType){
    setBill(Bill);
    setSMSCount(SMSCount);
    setTTC(TTC);
}

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public CardType getCardType() {
        return cardType;
    }

    public Double getBill() {
        return Bill;
    }

    @Override
    public void showBills(double Bill) {
        System.out.println(new StringBuilder().append("Bill:").append(Bill).toString());
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



    @Override
    public void countTTC(long TTC, SimsCard cardType) {
        System.out.println(new StringBuilder().append("test ").append(TTC).append("kapianleixing").append(getCardType()).toString());


}


}

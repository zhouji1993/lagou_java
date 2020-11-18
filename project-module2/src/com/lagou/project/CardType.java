package com.lagou.project;


public  enum CardType {
    BigCard("大卡",0), SmallCard("小卡",1), MicroCard("微型卡",2);
    private String CardName;
    private int IndexNum;

    private CardType(String CardName, int IndexNum){
        this.CardName=CardName;
        this.IndexNum=IndexNum;
    }

    public void setCardName(String cardName) {
        CardName = cardName;
    }

    public void setIndexNum(int indexNum) {
        IndexNum = indexNum;
    }

    public int getIndexNum() {
        return IndexNum;
    }

    public String getCardName() {
        return CardName;
    }
}

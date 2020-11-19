package com.lagou.project;

import com.lagou.project.Services.*;


public class ProjectTest {


    public static void main(String[] args) {
        SimsCard simscard = new SimsCard();
        NetFlowServices newServices = (NetFlowServices) new NetS();
        newServices.countnetFlow(0.0 ,simscard);
        System.out.println("---------------------------------------------------");

        TalkServices newTalkServices = new Talks();

        newTalkServices.countTTC(0,simscard);
        System.out.println("---------------------------------------------------");
        AbstractUserTalkNew abstractPackage= new NetS(9,20);
        abstractPackage.show();

    }










}

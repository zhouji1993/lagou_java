package com.lagou.project;

import java.util.EnumMap;
import com.lagou.project.Services.*;
import com.lagou.project.*;

public class ProjectTest extends AbstractUserTalkNew implements Services {






    @Override
    public void setBill(Double bill) {
        super.setBill(bill);
    }

    @Override
    public Double getBill() {
        return super.getBill();
    }

    @Override
    public void show(double Bill) {
        super.show(Bill);
    }

    @Override
    public double showBills(double Bill) {
        return 0;
    }

    @Override
    public String getscard(EnumMap CardType) {
        return null;
    }

     class newNets extends AbstractUserTalkNew implements Services,NetFlowServices{


        @Override
        public Double getBill() {
            return super.getBill();
        }

         public newNets() {
             super();
         }

         @Override
        public String getscard(EnumMap CardType) {
            return null;
        }
        @Override
         public void show(double Bill ) {
             super.show(Bill);

             System.out.println("");

         }
         @Override
        public  void countnetFlow(double netFlow, String cardType) {

             System.out.println("类型"+cardType+"流量"+netFlow + "账单" + getBill());

        }

        @Override
        public void setBill(Double bill) {
            super.setBill(bill);
        }

        @Override
        public double showBills(double Bill) {
            return Bill;
        }
    }



    public void main(String[] args) {
        double d1 = 0.0;
        double d2 = 1.0;
        long l1 = 0 ;
        int int1 = 0 ;
        String str1 = "";
        System.out.println("Nets show");




    }

}

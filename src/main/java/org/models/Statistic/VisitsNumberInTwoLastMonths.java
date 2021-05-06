package org.models.Statistic;

public class VisitsNumberInTwoLastMonths {
        private int month;
        private int p1;
        private int p2;
        private int p3;
        private int p4;
        private int p5;
        private int p6;


        public VisitsNumberInTwoLastMonths() {
        }

        public VisitsNumberInTwoLastMonths(int month, int p1, int p2, int p3, int p4, int p5, int p6) {
                this.month = month;
                this.p1 = p1;
                this.p2 = p2;
                this.p3 = p3;
                this.p4 = p4;
                this.p5 = p5;
                this.p6 = p6;
        }


        public int getMonth() {
                return month;
        }

        public VisitsNumberInTwoLastMonths setMonth(int month) {
                this.month = month;
                return this;
        }

        public int getP1() {
                return p1;
        }

        public VisitsNumberInTwoLastMonths setP1(int p1) {
                this.p1 = p1;
                return this;
        }

        public int getP2() {
                return p2;
        }

        public VisitsNumberInTwoLastMonths setP2(int p2) {
                this.p2 = p2;
                return this;
        }

        public int getP3() {
                return p3;
        }

        public VisitsNumberInTwoLastMonths setP3(int p3) {
                this.p3 = p3;
                return this;
        }

        public int getP4() {
                return p4;
        }

        public VisitsNumberInTwoLastMonths setP4(int p4) {
                this.p4 = p4;
                return this;
        }

        public int getP5() {
                return p5;
        }

        public VisitsNumberInTwoLastMonths setP5(int p5) {
                this.p5 = p5;
                return this;
        }

        public int getP6() {
                return p6;
        }

        public VisitsNumberInTwoLastMonths setP6(int p6) {
                this.p6 = p6;
                return this;
        }

        @Override
        public String toString() {
                return "VisitsNumberInTwoLastMonths{" +
                        "month=" + month +
                        ", p1=" + p1 +
                        ", p2=" + p2 +
                        ", p3=" + p3 +
                        ", p4=" + p4 +
                        ", p5=" + p5 +
                        ", p6=" + p6 +
                        '}';
        }
}

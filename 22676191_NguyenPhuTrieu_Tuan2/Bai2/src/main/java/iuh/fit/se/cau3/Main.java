package iuh.fit.se.cau3;

//import iuh.fit.se.cau3.state.CreditCardState;
//import iuh.fit.se.cau3.state.PayPalState;
//import iuh.fit.se.cau3.state.PaymentContext;
//import iuh.fit.se.cau3.strategy.CreditCardStrategy;
//import iuh.fit.se.cau3.strategy.PayPalStrategy;
//import iuh.fit.se.cau3.strategy.PaymentContext;

//public class Main {
//    public static void main(String[] args) {
//        PaymentContext order = new PaymentContext(100.0);
//
//        order.setState(new PayPalState());
//
//        order.applyDiscount("GIAMGIA10");
//
//        order.process();
//
//        System.out.println("-------------------------");
//
//        PaymentContext order2 = new PaymentContext(200.0);
//        order2.setState(new CreditCardState());
//        order2.process();
//    }
//}
//public class Main {
//    public static void main(String[] args) {
//        PaymentContext cart = new PaymentContext();
//        double orderAmount = 100.0;
//        String voucher = "SAVE10";
//
//        cart.setStrategy(new CreditCardStrategy("1234-5678-9012"));
//        cart.executePayment(orderAmount, voucher);
//
//        cart.setStrategy(new PayPalStrategy("user@example.com"));
//        cart.executePayment(orderAmount, voucher);
//    }
//}

import iuh.fit.se.cau3.decorator.BasicPayment;
import iuh.fit.se.cau3.decorator.DiscountDecorator;
import iuh.fit.se.cau3.decorator.Payment;
import iuh.fit.se.cau3.decorator.ProcessingFeeDecorator;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Giao dịch 1 ---");
        Payment payPal = new BasicPayment("PayPal");
        payPal.pay(100.0);

        System.out.println("\n--- Giao dịch 2 ---");
        Payment creditCardWithFee = new ProcessingFeeDecorator(
                new BasicPayment("Thẻ tín dụng"), 5.0
        );
        creditCardWithFee.pay(100.0);

        System.out.println("\n--- Giao dịch 3 ---");
        Payment fullCombo = new DiscountDecorator(
                new ProcessingFeeDecorator(
                        new BasicPayment("PayPal"), 2.0
                ), 10.0
        );
        fullCombo.pay(100.0);
    }
}
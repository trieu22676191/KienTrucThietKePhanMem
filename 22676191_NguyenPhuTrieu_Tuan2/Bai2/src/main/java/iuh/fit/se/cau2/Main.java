package iuh.fit.se.cau2;

//import iuh.fit.se.cau2.state.ConsumptionTax;
//import iuh.fit.se.cau2.state.LuxuryTax;
//import iuh.fit.se.cau2.state.TaxContext;
//import iuh.fit.se.cau2.state.VATTax;
//
//public class Main {
//    public static void main(String[] args) {
//        TaxContext context = new TaxContext();
//        double basePrice = 1000.0;
//
//        context.setTaxState(new VATTax());
//        System.out.println("Thuế VAT cho 1000$ là: " + context.calculate(basePrice) + "$");
//
//        context.setTaxState(new LuxuryTax());
//        System.out.println("Thuế hàng xa xỉ cho 1000$ là: " + context.calculate(basePrice) + "$");
//
//        context.setTaxState(new ConsumptionTax());
//        System.out.println("Thuế tiêu thụ cho 1000$ là: " + context.calculate(basePrice) + "$");
//    }
//}

//import iuh.fit.se.cau2.strategy.ConsumptionTax;
//import iuh.fit.se.cau2.strategy.LuxuryTax;
//import iuh.fit.se.cau2.strategy.VATTax;
//import iuh.fit.se.cau2.strategy.Product;
//
//public class Main {
//    public static void main(String[] args) {
//        // 1. Tạo sản phẩm thiết yếu áp dụng thuế VAT
//        Product milk = new Product("Sữa tươi", 20000.0, new VATTax());
//        milk.displayInfo();
//
//        // 2. Tạo sản phẩm áp dụng thuế tiêu thụ
//        Product snack = new Product("Bánh Snack", 10000, new ConsumptionTax());
//        snack.displayInfo();
//
//        // 3. Tạo sản phẩm xa xỉ áp dụng thuế đặc biệt
//        Product iphone = new Product("iPhone 15 Pro Max", 30000000, new LuxuryTax());
//        iphone.displayInfo();
//
//        // 4. Thay đổi chiến lược thuế linh hoạt
//        // Ví dụ: iPhone đang chịu thuế Luxury, giờ chuyển sang VAT
//        System.out.println("Cập nhật lại thuế cho iPhone...");
//        iphone.setTaxStrategy(new VATTax());
//        iphone.displayInfo();
//    }
//}

import iuh.fit.se.cau2.decorator.*;

public class Main {
    public static void main(String[] args) {
        Product laptop = new SimpleProduct("Laptop Dell", 1000.0);
        laptop = new VATTax(laptop);
        System.out.println(laptop.getDescription() + " => Total: $" + laptop.getPrice());

        Product car = new SimpleProduct("Luxury Car", 50000.0);
        car = new SpecialTax(car);
        car = new ConsumptionTax(car);
        car = new VATTax(car);

        System.out.println("\n--- Tax Calculation ---");
        System.out.println("Items: " + car.getDescription());
        System.out.println("Final Price: $" + String.format("%.2f", car.getPrice()));
    }
}
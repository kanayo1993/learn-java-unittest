public class ifLearning {
    public static void handleOrder(Order order, boolean inStock, boolean isRush, boolean isMember) {
        // 条件A: order が null でない
        if (order != null) {
            // 条件B: 在庫がある
            if (inStock) {
                // 条件C: 急ぎ配送
                if (isRush) {
                    chargeRushFee(order);        // P
                }
            } else if (isMember) {              // 条件D: 会員なら在庫切れ時に注文キープ
                putOnBackorder(order);         // Q
            }
        } else if (isMember) {                  // 条件E: order が null かつ会員ならエラー通知
            notifyErrorForMember();           // R
        }
    }

    private static void chargeRushFee(Order order){
        System.out.println("Charging rush fee for order: " + order);
    }
    private static void putOnBackorder(Order order){
        System.out.println("Putting order on backorder: " + order);
    }
    private static void notifyErrorForMember(){
        System.out.println("Error: Order is null but customer is a member - notify support");
    }
}

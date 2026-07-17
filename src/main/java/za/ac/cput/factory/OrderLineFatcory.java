package za.ac.cput.factory;

import za.ac.cput.domain.OrderLine;
import za.ac.cput.util.Helper;

public class OrderLineFatcory {
    public static OrderLine createOrderLine(String orderLineId,int quantity,double unitPrice, double lineTotal,String productId){
        if(Helper.isNullOrEmpty(orderLineId) || Helper.isNullOrEmpty(productId)){
            return null;
        }
        if (quantity <= 0){
            return null;
        }
        return new OrderLine.Builder().setOrderLineId(orderLineId).setQuantity(quantity).setUnitPrice(unitPrice)
                .setLineTotal(lineTotal).setProductId(productId).build();
    }
}

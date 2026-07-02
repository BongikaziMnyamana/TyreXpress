package za.ac.cput.factory;

import za.ac.cput.domain.OrderLine;
import za.ac.cput.util.Helper;

public class OrderLineFatcory {
    public static OrderLine createOrderLine(String orderLineID,int quantity,double unitPrice, double lineTotal,String productID){
        if(Helper.isNullOrEmpty(orderLineID) || Helper.isNullOrEmpty(productID)){
            return null;
        }
        if (quantity <= 0){
            return null;
        }
        return new OrderLine.Builder().setOrderLineID(orderLineID).setQuantity(quantity).setUnitPrice(unitPrice)
                .setLineTotal(lineTotal).setProductID(productID).build();
    }
}

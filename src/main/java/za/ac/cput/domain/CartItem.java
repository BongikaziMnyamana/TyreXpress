package za.ac.cput.domain;

public class CartItem {
    private String cartItemId;
    private int quantity;
    private String productId;
    private String cartId;

    private CartItem(Builder builder) {
        this.cartItemId = builder.cartItemId;
        this.quantity = builder.quantity;
        this.productId = builder.productId;
        this.cartId = builder.cartId;
    }


    public String getCartItemId() {
        return cartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getCartId() {
        return cartId;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId='" + cartItemId + '\'' +
                ", quantity=" + quantity +
                ", productId='" + productId + '\'' +
                ", cartId='" + cartId + '\'' +
                '}';
    }


    public static class Builder {
        private String cartItemId;
        private int quantity;
        private String productId;
        private String cartId;

        public Builder setCartItemId(String cartItemId) {
            this.cartItemId = cartItemId;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setProductId(String productId) {
            this.productId = productId;
            return this;
        }

        public Builder setCartId(String cartId) {
            this.cartId = cartId;
            return this;
        }

        public Builder copy(CartItem cartItem) {
            this.cartItemId = cartItem.cartItemId;
            this.quantity = cartItem.quantity;
            this.productId = cartItem.productId;
            this.cartId = cartItem.cartId;
            return this;
        }

        public CartItem build() {
            return new CartItem(this);
        }
    }
}
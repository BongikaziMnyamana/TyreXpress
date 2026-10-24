package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long productID;
    protected String name;
    @Column(name = "image_url")
    protected String imageUrl;
    protected String brand;
    protected double price;
    protected int stock;

    public Product() {
    }

    public Product(Builder builder) {
        this.productID = builder.productID;
        this.name = builder.name;
        this.brand = builder.brand;
        this.price = builder.price;
        this.stock = builder.stock;
        this.imageUrl = builder.imageUrl;
    }

    public Long getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }

    public static abstract class Builder {
        protected Long productID;
        protected String name;
        protected String brand;
        protected double price;
        protected int stock;
        protected String imageUrl;

        public Builder setProductID(Long productID) {
            this.productID = productID;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setStock(int stock) {
            this.stock = stock;
            return this;
        }


        public Builder copy(Product product) {
            this.productID = product.productID;
            this.name = product.name;
            this.brand = product.brand;
            this.price = product.price;
            this.stock = product.stock;
            this.imageUrl = product.imageUrl;
            return this;
        }
    }
}
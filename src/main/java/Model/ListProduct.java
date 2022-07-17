package Model;

import java.util.ArrayList;
import java.util.List;

public class ListProduct {
    private Product product;
    private List<Product> listProduct;

    public ListProduct(Product product) {
        this.product = product;
        this.listProduct = new ArrayList<>();
    }

    public void addProduct(Product product) {
        listProduct.add(product);
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }
}

package com.sjp.dao;

import com.sjp.entity.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductDao {
    private static List<Product> products = new ArrayList<Product>();

    static {
        for (int i = 0; i < 10; i++)
            products.add(new Product("" + i, "笔记本"+i, "LN00"+i, 33+i));
    }

    public void addProduct(String id, String name, String type, double price){
        products.add(new Product(id, name, type, price));
    }

    public void deleteProduct(String id){
        Iterator<Product> it = products.iterator();
        while (it.hasNext()){
            Product p = it.next();
            if (id.equals(p.getId())){
                it.remove();
                break;
            }
        }
    }

    public List<Product> findAll(){
        return products;
    }

    public Product findByid(String id){
        for (Product product : products){
            if(id.equals(product.getId())){
                return product;
            }
        }
        return null;
    }
}

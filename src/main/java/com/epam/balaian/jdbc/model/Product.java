package com.epam.balaian.jdbc.model;

/**
 * @author Vardan_Balaian
 * @created 1/22/2020
 * @since 1.8
 */
public class Product {

  private long idProduct;
  private String title;
  private String description;
  private long idProductOwner;
  private long idBidding;

  public Product() {}

  public Product(
      long idProduct, String title, String description, long idProductOwner, long idBidding) {
    this.idProduct = idProduct;
    this.title = title;
    this.description = description;
    this.idProductOwner = idProductOwner;
    this.idBidding = idBidding;
  }

  public long getIdProduct() {
    return idProduct;
  }

  public void setIdProduct(long idProduct) {
    this.idProduct = idProduct;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public long getIdProductOwner() {
    return idProductOwner;
  }

  public void setIdProductOwner(long idProductOwner) {
    this.idProductOwner = idProductOwner;
  }

  public long getIdBidding() {
    return idBidding;
  }

  public void setIdBidding(long idBidding) {
    this.idBidding = idBidding;
  }

  @Override
  public String toString() {
    return "Product{"
        + "idProduct="
        + idProduct
        + ", title='"
        + title
        + '\''
        + ", description='"
        + description
        + '\''
        + ", idProductOwner="
        + idProductOwner
        + ", idBidding="
        + idBidding
        + '}';
  }
}

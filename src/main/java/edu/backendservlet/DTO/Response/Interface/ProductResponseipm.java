package edu.backendservlet.DTO.Response.Interface;

import lombok.Getter;

public interface ProductResponseipm {
    Long getId();
    String getAuthor();
    String getDescription();
    String getImageName();
    String getName();
    int getPages();
    String getPublisher();
    int getTotalbuy();
    String getCategoryName();
}

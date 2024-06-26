package com.uep.wap.dto;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class BookDto {
    private Integer id;
    private String title;
    private String author;
    private Integer publicationYear;
    private Boolean availability;
    private Double cost;
    private LocalDate receiptDate;
    private String description;

    private MultipartFile image;

    private String imageFileName;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setReceiptDate(LocalDate receiptDate) {
        this.receiptDate = receiptDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // No-argument constructor
    public BookDto() {
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public Double getCost() {
        return cost;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }

    public String getDescription() {
        return description;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public BookDto(String title, String author, Integer publicationYear, Double cost, LocalDate receiptDate, Boolean availability, MultipartFile image) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.availability = availability;
        this.cost = cost;
        this.receiptDate = receiptDate;
        this.image = image;
    }

//    public String getImageFileName() {
//        return imageFileName;
//    }
//
//    public void setImageFileName(String imageFileName) {
//        this.imageFileName = imageFileName;
//    }

}



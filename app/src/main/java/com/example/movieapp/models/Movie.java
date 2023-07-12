package com.example.movieapp.models;

public class Movie {
    private String name;
    private double rating;
    private String image;
    private String Description;
    private Categories category;
    private int year;

    public Movie() {
    }

    public Movie(String name, double rating, Categories category) {
        this.name = name;
        this.rating = rating;
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public Movie setYear(int year) {
        this.year = year;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Movie setImage(String image) {
        this.image = image;
        return this;

    }

    public String getDescription() {
        return Description;
    }

    public Movie setDescription(String description) {
        Description = description;
        return this;
    }

    public Categories getCategory() {
        return category;
    }

    public Movie setCategory(Categories category) {
        this.category = category;
        return this;

    }

    public String getName() {
        return name;
    }

    public Movie setName(String name) {
        this.name = name;
        return this;
    }

    public double getRating() {
        return rating;
    }

    public Movie setRating(double rating) {
        this.rating = rating;
        return this;

    }
}

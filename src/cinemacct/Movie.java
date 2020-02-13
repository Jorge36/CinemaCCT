/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemacct;

/**
 * Description: Class movie with its attribues and behaviour
 * @author Jorge
 */
public class Movie {
    
    private int id;
    private String title;
    private String description;
    private String genre;
    private int year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Movie(int id, String title, String description, int year, String genre) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.year = year;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Movie" + " Id = " + id + System.lineSeparator() + 
               "Title = " + title + System.lineSeparator() + 
               "Description = " + description + System.lineSeparator() +
               "Genre = " + genre + System.lineSeparator() + 
               "Year = " + year;
    }
  
}

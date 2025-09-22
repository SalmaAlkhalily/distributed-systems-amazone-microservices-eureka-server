package com.amazone.entity;

public class Search  {
    private int searchBy;
    public Search(){
        System.out.println("Enter 1 for searchby category, 2 for searchby price");
    }
    //searchby category->1 , search about skirt
    public Search(int searchBy, String searchAbout) {
        this.searchBy = searchBy;
        this.searchAbout = searchAbout;
    }

    public int getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(int searchBy) {
        this.searchBy = searchBy;
    }

    public String getSearchAbout() {
        return searchAbout;
    }

    public void setSearchAbout(String searchAbout) {
        this.searchAbout = searchAbout;
    }

    private String searchAbout;



}

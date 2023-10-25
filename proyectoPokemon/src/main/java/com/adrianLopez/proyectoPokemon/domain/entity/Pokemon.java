package com.adrianLopez.proyectoPokemon.domain.entity;

public class Pokemon {

    private int id;
    private String name;
    private double height;
    private double weight;
    private int exp;
 
    public Pokemon(String name, int id, double height, double weight, int exp) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.exp = exp;
    }

    public Pokemon(String name, int id) {
        this.id = id;
        this.name = name;
    }

    public Pokemon() {
    }
 
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }

    public int getexp() {
        return exp;
    }
 
    public void setexp(int exp) {
        this.exp = exp;
    }
 
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public double getHeight() {
        return height;
    }
 
    public void setHeight(double height) {
        this.height = height;
    }
 
    public double getWeight() {
        return weight;
    }
 
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}


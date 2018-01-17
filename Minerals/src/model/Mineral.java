/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author unalman
 */
public class Mineral {
    private String name;
    private int timetoextract;
    
    public Mineral(String name)
    {
        this.name=name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTimetoextract(int timetoextract) {
        this.timetoextract = timetoextract;
    }

    public int getTimetoextract() {
        return timetoextract;
    }
    
    
}

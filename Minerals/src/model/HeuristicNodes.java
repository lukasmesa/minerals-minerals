/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Comparator;

/**
 *
 * @author unalman
 */
public class HeuristicNodes implements Comparator<Node>{
    @Override
    public int compare(Node n1, Node n2) 
    {
        return Double.compare(n1.getCost(), n2.getCost());
    }
    
    
}

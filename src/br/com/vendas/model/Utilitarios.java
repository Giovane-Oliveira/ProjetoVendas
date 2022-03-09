/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vendas.model;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Giovane Oliveira
 */
public class Utilitarios {
    
    public void LimpaTela(JPanel container){
    
    Component components[] = container.getComponents();
    for(Component component : components){
    
    if(component instanceof JTextField){
    
    ((JTextField)component).setText(null);
    
    
    }
    
    }
    
    
    }
    
}

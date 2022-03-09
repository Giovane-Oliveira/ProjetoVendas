/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vendas.jdbc;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Giovane Oliveira
 */
public class ConnectionFactory {
   public static java.sql.Connection getConnection(){

try{

return DriverManager.getConnection("jdbc:mysql://127.0.0.1/bdvendas","root","123");

}catch(Exception erro){
throw new RuntimeException(erro);
}
    }

  
} 

   


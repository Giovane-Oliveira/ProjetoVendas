/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vendas.interfaces;

import br.com.vendas.jdbc.ConnectionFactory;
import br.com.vendas.model.Funcionarios;
import br.com.vendas.model.WebServiceCep;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Giovane Oliveira
 */
public interface InterfaceFuncionario {
  
    
   public void cadastrarFuncionario(Funcionarios obj);

    public void excluirFuncionario(Funcionarios obj);

    public void alterarFuncionario(Funcionarios obj);
    
    public List<Funcionarios> listarFuncionarios();
    
     public Funcionarios buscaCep(String cep);

    public Funcionarios consultarFuncionariosPorNome(String nome);

    public List<Funcionarios> listarFuncionariosPorNome(String nome);

    

}

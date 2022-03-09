/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vendas.dao;

import br.com.vendas.interfaces.InterfaceFuncionario;
import br.com.vendas.jdbc.ConnectionFactory;
import br.com.vendas.model.Funcionarios;
import br.com.vendas.model.WebServiceCep;
import br.com.vendas.view.FrmMenu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Giovane Oliveira
 */
public class FuncionariosDAO  {

    private Connection con;


    public void cadastrarFuncionario(Funcionarios obj) {

        try {

            String sql = "insert into tb_funcionarios(nome, rg, cpf, email, senha, cargo, nivel_acesso, telefone, celular,cep, endereco,numero, complemento, bairro, cidade, estado)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getTipoUsuario());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getEstado());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastro realizado com  sucesso");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "" + e);

        }

    }


    public void excluirFuncionario(Funcionarios obj) {

        try {

            String sql = "delete from tb_funcionarios where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Deletado com  sucesso");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "" + e);

        }

    }


    public void alterarFuncionario(Funcionarios obj) {
        try {

            String sql = "update tb_funcionarios set nome=?, rg=?, cpf=?, "
                    + "email=?, senha=?, cargo=?, nivel_acesso=?, telefone=?, celular=?,cep=?, endereco=?,"
                    + "numero=?, complemento=?, "
                    + "bairro=?, cidade=?, estado=? where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getTipoUsuario());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getEstado());
                 stmt.setInt(17, obj.getId());


            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com  sucesso");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "" + e);

        }

    }
    
    
    public void login(String email, String senha){
     
     try{
         String sql = "select * from tb_funcionarios where email=? and senha=?";
      PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,email);
            stmt.setString(2,senha);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
            
               FrmMenu tela  = new FrmMenu();
               tela.usuarioLogado = rs.getString("nome");
               tela.setVisible(true);
                JOptionPane.showMessageDialog(null, "Logado com sucesso");
                
            
                
            
            }else{
            
                       JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos ");
            
            }
    
    
     
     }catch(Exception e){
     
       JOptionPane.showMessageDialog(null, "Erro " + e);
     
     }       
     
    
    }


    public List<Funcionarios> listarFuncionarios() {

        List<Funcionarios> lista = new ArrayList<>();
        try {

            String sql = "select * from tb_funcionarios";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Funcionarios obj = new Funcionarios();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setCargo(rs.getString("cargo"));
                obj.setSenha(rs.getString("senha"));
                obj.setTipoUsuario(rs.getString("nivel_acesso"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));

                lista.add(obj);

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "" + e);

        }
        return lista;

    }
    

     public Funcionarios buscaCep(String cep) {
       
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
       

        Funcionarios obj = new Funcionarios();

        if (webServiceCep.wasSuccessful()) {
            obj.setEndereco(webServiceCep.getLogradouroFull());
            obj.setCidade(webServiceCep.getCidade());
            obj.setBairro(webServiceCep.getBairro());
            obj.setEstado(webServiceCep.getUf());
            return obj;
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
        }
        
     }


    public Funcionarios consultarFuncionariosPorNome(String nome) {

        try {

            String sql = "select * from tb_funcionarios where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Funcionarios obj = new Funcionarios();
            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setTipoUsuario(rs.getString("nivel_acesso"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));   

            }
            return obj;
            
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "" + e);

        }
        return null;

    }


    public List<Funcionarios> listarFuncionariosPorNome(String nome) {

        List<Funcionarios> lista = new ArrayList<>();
        try {

            String sql = "select * from tb_funcionarios where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Funcionarios obj = new Funcionarios();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setTipoUsuario(rs.getString("nivel_acesso"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));

                lista.add(obj);

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "" + e);

        }
        return lista;

    }

    public FuncionariosDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

}

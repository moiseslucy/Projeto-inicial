/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */


import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
              
        //conn = new conectaDAO().connectDB();
        conn = new conectaDAO().connectDB("uc11", "root", "MOLsll422."); 
        
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
           
            prep.setString(3, produto.getStatus());
            prep.setDouble(2, produto.getValor());
            prep.executeUpdate();
            /*Exibição de uma mensagem indicando o sucesso ou falha no cadastro.
            */
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso.");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e.getMessage());
        } finally {
            try {
                if (prep != null) {
                    prep.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
    }
    }
    public ArrayList<ProdutosDTO> listarProdutos(){
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        
        
        conn = new conectaDAO().connectDB("uc11", "root", "MOLsll422.");
        
        String sql = "SELECT * FROM produtos"; 
        
        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultset != null) {
                    resultset.close();
                }
                if (prep != null) {
                    prep.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return listagem;
    }
    
    
 public void venderProduto(int idProduto) {
    PreparedStatement stmt = null;
conn = new conectaDAO().connectDB("uc11", "root", "MOLsll422."); 
    try {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idProduto);
        stmt.executeUpdate();
        System.out.println("Produto vendido com sucesso!");
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
public List<ProdutosDTO> listarProdutosVendidos() {
    List<ProdutosDTO> produtosVendidos = new ArrayList<>();

    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        // SQL para buscar produtos com status "Vendido"
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        // Processar o resultado da consulta
        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            double valor = rs.getDouble("valor");
            String status = rs.getString("status");

            ProdutosDTO produto = new ProdutosDTO(id, nome, valor, status);
            produtosVendidos.add(produto);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Trate qualquer exceção aqui
    } finally {
        // Feche o statement e o ResultSet
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return produtosVendidos;
}
}
   
   


        



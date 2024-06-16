package jpsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import projeto.Usuario;

public class UsuarioDAO {

	Connection conn;

	public ResultSet autenticacaoUsuarioP(Usuario objLogin) {

		conn = new Conexao().conectar();

		try {
			String sql = "SELECT * FROM professor WHERE Email = ? AND Senha = ? ";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setString(1, objLogin.getEmail());
			pst.setString(2, objLogin.getSenha());

			ResultSet rs = pst.executeQuery();
			return rs;

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Usuario" + erro);
			return null;
		}

	}
	
	public ResultSet autenticacaoUsuarioA(Usuario objLoginA) {

		conn = new Conexao().conectar();

		try {
			String sql = "SELECT * FROM aluno WHERE CPF = ? AND Senha = ? ";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setString(1, objLoginA.getCpf());
			pst.setString(2, objLoginA.getSenha());

			ResultSet rs = pst.executeQuery();
			return rs;

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Usuario" + erro);
			return null;
		}

	}

	public ResultSet recuperarUsuarioP(String emailInserido) {
	    conn = new Conexao().conectar();

	    try {
	        String sql = "SELECT * FROM professor WHERE EMAIL = ?";
	        PreparedStatement pst = conn.prepareStatement(sql);

	        pst.setString(1, emailInserido);

	        ResultSet rs = pst.executeQuery();
	        return rs;

	    } catch (SQLException erro) {
	        JOptionPane.showMessageDialog(null, "Erro ao recuperar usuário por EMAIL: " + erro);
	        return null;
	    }
	}
	
	public ResultSet recuperarUsuarioA(String cpfInserido) {
	    conn = new Conexao().conectar();

	    try {
	        String sql = "SELECT * FROM aluno WHERE CPF = ?";
	        PreparedStatement pst = conn.prepareStatement(sql);

	        pst.setString(1, cpfInserido);

	        ResultSet rs = pst.executeQuery();
	        return rs;

	    } catch (SQLException erro) {
	        JOptionPane.showMessageDialog(null, "Erro ao recuperar usuário por CPF: " + erro);
	        return null;
	    }
	}
	
	public ResultSet consultarTreinosPorCPF(String cpf) {
	    String query = "SELECT NOMEDOEXERCICIO, SERIE, REPETICOES, TEMPODESCANSO, TREINONU, NOME_PROFESSOR FROM criartreino WHERE CPF_ALUNO = ?";
	    
	    conn = new Conexao().conectar();
	    
	    try {
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setString(1, cpf);

	        ResultSet rs = pst.executeQuery();
	        
	        return rs; // Retorna o objeto ResultSet com os resultados da consulta

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null; // Em caso de erro, retorne null
	}



}

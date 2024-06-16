package projeto;

import java.sql.ResultSet;

public class Usuario {

	private String email;
	private String senha;
	private String cpf;
	private String senhaA;
	
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenhaA() {
		return senhaA;
	}
	public void setSenhaA(String senhaA) {
		this.senhaA = senhaA;
	}
	 public void setCPF(String string) {
		// TODO Auto-generated method stub
		
	}
}

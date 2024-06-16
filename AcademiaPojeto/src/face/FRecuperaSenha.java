package face;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import jpsql.Conexao;
import jpsql.UsuarioDAO;
import projeto.Usuario;
import utils.Validador;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class FRecuperaSenha extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PreparedStatement pst;

	Conexao conexao = new Conexao();
	private Connection con;
	private JLabel lblData;
	private JLabel lblStatus;
	private JLabel lblRecuperar;
	private JTextField txtRecupera;
	private JLabel lblFoto;
	private JLabel lblNewLabel_2;
	private JButton btnConfirmar;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FRecuperaSenha frame = new FRecuperaSenha();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FRecuperaSenha() {
		setTitle("Lion's Academy");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
				setarData();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(FRecuperaSenha.class.getResource("/img/Logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setForeground(new Color(9, 0, 43));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 279, 636, 34);
		contentPane.add(panel);
		panel.setLayout(null);

		lblStatus = new JLabel("");
		lblStatus.setBounds(590, 0, 36, 29);
		panel.add(lblStatus);
		lblStatus.setIcon(new ImageIcon(FRecuperaSenha.class.getResource("/img/bdoff.png")));

		lblData = new JLabel("");
		lblData.setForeground(SystemColor.text);
		lblData.setBounds(10, 16, 234, 13);
		panel.add(lblData);

		lblRecuperar = new JLabel("CPF/Email");
		lblRecuperar.setForeground(SystemColor.text);
		lblRecuperar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblRecuperar.setBounds(77, 112, 131, 40);
		contentPane.add(lblRecuperar);

		txtRecupera = new JTextField();

		txtRecupera.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtRecupera.setBounds(235, 112, 160, 41);
		contentPane.add(txtRecupera);
		txtRecupera.setColumns(10);
		// Uso do PlainDocument para limitar os campos
		txtRecupera.setDocument(new Validador(50));

		lblFoto = new JLabel("New label");
		lblFoto.setIcon(new ImageIcon(FRecuperaSenha.class.getResource("/img/Logo.png")));
		lblFoto.setBounds(426, 59, 200, 200);
		contentPane.add(lblFoto);

		lblNewLabel_2 = new JLabel("Recuperação de senha");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_2.setForeground(SystemColor.text);
		lblNewLabel_2.setBounds(191, 10, 249, 40);
		contentPane.add(lblNewLabel_2);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setVerticalAlignment(SwingConstants.BOTTOM);
		btnConfirmar.setForeground(new Color(166, 89, 1));
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtRecupera.getText().isEmpty()) {
			        JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos obrigatórios.");
			        return;
			    }
				
				try {
					// professor
					String emailInserido = txtRecupera.getText();
					UsuarioDAO objRecuperaDao = new UsuarioDAO();
					ResultSet rsUsuarioP = objRecuperaDao.recuperarUsuarioP(emailInserido);

					// aluno
					String cpfInserido = txtRecupera.getText();
					UsuarioDAO objRecuperaDaoA = new UsuarioDAO();
					ResultSet rsUsuarioA = objRecuperaDaoA.recuperarUsuarioA(cpfInserido);

					if (rsUsuarioP.next()) {
						String senha = rsUsuarioP.getString("SENHA");
						JOptionPane.showMessageDialog(null, "Sua senha é: " + senha);

						FLogin telaLogin = new FLogin();
						telaLogin.setVisible(true);
						dispose();
					} else if (rsUsuarioA.next()) {
						String senha = rsUsuarioA.getString("SENHA");
						JOptionPane.showMessageDialog(null, "Sua senha é: " + senha);

						FLogin telaLogin = new FLogin();
						telaLogin.setVisible(true);
						dispose();
					} else {
						// Usuário não encontrado, exiba uma mensagem de erro
						JOptionPane.showMessageDialog(null, "CPF ou Email inválido");
					}
				} catch (SQLException erro) {
					JOptionPane.showMessageDialog(null, "Erro ao recuperar usuário: " + erro.getMessage());
				}
			}
		});
		btnConfirmar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		btnConfirmar.setBounds(270, 181, 95, 21);
		contentPane.add(btnConfirmar);

		lblNewLabel = new JLabel("Aluno recupera por CPF");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setBounds(10, 238, 166, 21);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Professor recupera por Email");
		lblNewLabel_1.setForeground(SystemColor.text);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 256, 198, 19);
		contentPane.add(lblNewLabel_1);
	}

	private void status() {
		try {
			con = conexao.conectar();
			if (con == null) {
				lblStatus.setIcon(new ImageIcon(FRecuperaSenha.class.getResource("/img/bdoff.png")));
			} else {
				lblStatus.setIcon(new ImageIcon(FRecuperaSenha.class.getResource("/img/bdon.png")));
			}
		} catch (Exception e) {
			System.out.println();
		}
	}

	private void setarData() {
		Date data = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
		lblData.setText(formatador.format(data));
	}

	public Usuario recuperarUsuarioP(String emailInserido) {
		try {
			con = conexao.conectar();
			String query = "SELECT * FROM professor WHERE Email = ?";
			pst = con.prepareStatement(query);
			pst.setString(1, emailInserido);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setEmail(rs.getString("EMAIL"));

				con.close();
				return usuario;
			} else {
				JOptionPane.showMessageDialog(null, "Usuario não encontrado");
				con.close();
				return null; // Retorna null se o usuário não for encontrado.
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Usuario recuperarUsuarioA(String cpfInserido) {
		try {
			con = conexao.conectar();
			String query = "SELECT * FROM aluno WHERE CPF = ?";
			pst = con.prepareStatement(query);
			pst.setString(1, cpfInserido);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setCPF(rs.getString("CPF"));

				con.close();
				return usuario;
			} else {
				JOptionPane.showMessageDialog(null, "Usuario não encontrado");
				con.close();
				return null; // Retorna null se o usuário não for encontrado.
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

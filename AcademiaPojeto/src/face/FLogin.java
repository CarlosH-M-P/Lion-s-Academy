package face;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import jpsql.Conexao;
import jpsql.UsuarioDAO;
import projeto.Usuario;
import utils.Validador;
import face.FCriarTreino;

public class FLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	Conexao conexao = new Conexao();
	private Connection con;
	private PreparedStatement pst;
	private JPanel panel;
	private JLabel lblData;
	private JLabel lblStatus;
	private JLabel lblFoto;
	private JLabel lblNewLabel_1;
	private JTextField txtLogin;
	private JLabel lblNewLabel_2;
	private JButton btnEsqueci;
	private JButton btnCadastro;
	private JButton btnLogar;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FLogin frame = new FLogin();
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
	public FLogin() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
				setarData();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(FLogin.class.getResource("/img/Logo.png")));
		setTitle("Lion's Academy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 339);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 266, 626, 36);
		contentPane.add(panel);
		panel.setLayout(null);

		lblStatus = new JLabel("");
		lblStatus.setBounds(584, 0, 32, 32);
		panel.add(lblStatus);
		lblStatus.setIcon(new ImageIcon(FLogin.class.getResource("/img/bdoff.png")));

		lblData = new JLabel("");
		lblData.setBackground(new Color(240, 240, 240));
		lblData.setBounds(10, 19, 225, 13);
		panel.add(lblData);
		lblData.setForeground(SystemColor.text);

		lblFoto = new JLabel("New label");
		lblFoto.setIcon(new ImageIcon(FLogin.class.getResource("/img/Logo.png")));
		lblFoto.setBounds(404, 47, 200, 200);
		contentPane.add(lblFoto);

		lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(SystemColor.text);
		lblNewLabel_1.setBounds(117, 80, 65, 30);
		contentPane.add(lblNewLabel_1);

		txtLogin = new JTextField();
		txtLogin.setToolTipText("Email/CPF");
		txtLogin.setBounds(223, 80, 131, 29);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		// Uso do PlainDocument para limitar os campos
		txtLogin.setDocument(new Validador(50));

		lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setForeground(SystemColor.text);
		lblNewLabel_2.setBounds(117, 150, 65, 25);
		contentPane.add(lblNewLabel_2);

		btnEsqueci = new JButton("Esqueci a senha");
		btnEsqueci.setBorder(null);
		btnEsqueci.setBackground(new Color(0, 0, 0));
		btnEsqueci.setForeground(new Color(166, 89, 1));
		btnEsqueci.setToolTipText("Esqueci a senha");
		btnEsqueci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FRecuperaSenha telaRecupera = new FRecuperaSenha();
				telaRecupera.setVisible(true);
				dispose();

			}
		});
		btnEsqueci.setBounds(158, 243, 131, 17);
		contentPane.add(btnEsqueci);

		btnCadastro = new JButton("Cadastrar");
		btnCadastro.setForeground(new Color(166, 89, 1));
		btnCadastro.setToolTipText("Cadastrar");
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FCadastro telaCadastro = new FCadastro();
				telaCadastro.setVisible(true);
				dispose();
			}
		});
		btnCadastro.setBounds(299, 243, 95, 17);
		contentPane.add(btnCadastro);

		btnLogar = new JButton("Logar");
		btnLogar.setForeground(new Color(166, 89, 1));
		btnLogar.setToolTipText("Logar");
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
				    Usuario objLogin = new Usuario();
				    objLogin.setEmail(txtLogin.getText());
				    objLogin.setSenha(String.valueOf(txtSenha.getPassword()));

				    UsuarioDAO objLoginDao = new UsuarioDAO();
				    ResultSet rsusuariodao = objLoginDao.autenticacaoUsuarioP(objLogin);

				    Usuario objLoginA = new Usuario();
				    objLoginA.setCpf(txtLogin.getText());
				    objLoginA.setSenha(String.valueOf(txtSenha.getPassword()));

				    UsuarioDAO objLoginADao = new UsuarioDAO();
				    ResultSet rsUsuarioAdao = objLoginADao.autenticacaoUsuarioA(objLoginA);

				    boolean usuarioEncontrado = false;
				    boolean senhaIncorreta = true;

				    if (txtLogin.getText().isEmpty() || String.valueOf(txtSenha.getPassword()).isEmpty()) {
				        JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos obrigatórios.");
				        return;
				    }

				    if (rsusuariodao.next()) {
				        // Usuário encontrado
				        if (objLogin.getSenha().equals(rsusuariodao.getString("senha"))) {
				            FCriarTreino objFCriaTreino = new FCriarTreino();
				            objFCriaTreino.setVisible(true);
				            dispose();
				            usuarioEncontrado = true;
				            senhaIncorreta = false;
				        }
				    }

				    if (rsUsuarioAdao.next()) {
				        // Usuário encontrado
				        if (objLogin.getSenha().equals(rsUsuarioAdao.getString("senha"))) {
				            FConsultaTreino objFConsultarTreino = new FConsultaTreino();
				            objFConsultarTreino.setVisible(true);
				            dispose();
				            usuarioEncontrado = true;
				            senhaIncorreta = false;
				        }
				    }

				    if (!usuarioEncontrado) {
				        JOptionPane.showMessageDialog(null, "Usuário não encontrado, Por favor cadastre-se");
				    } else if (senhaIncorreta) {
				        JOptionPane.showMessageDialog(null, "Senha incorreta");
				    }
				} catch (SQLException erro) {
				    JOptionPane.showMessageDialog(null, "LoginFace" + erro);
				}


			}
		});
		btnLogar.setBounds(241, 196, 95, 21);
		contentPane.add(btnLogar);

		txtSenha = new JPasswordField();
		txtSenha.setToolTipText("Senha");
		txtSenha.setBounds(223, 150, 131, 26);
		contentPane.add(txtSenha);
		// Uso do PlainDocument para limitar os campos
		txtSenha.setDocument(new Validador(30));

		System.out.println(conexao);
	}

	private void setarData() {
		Date data = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
		lblData.setText(formatador.format(data));
	}

	private void status() {
		try {
			con = conexao.conectar();
			if (con == null) {
				lblStatus.setIcon(new ImageIcon(FLogin.class.getResource("/img/bdoff.png")));
				;
			} else {
				lblStatus.setIcon(new ImageIcon(FLogin.class.getResource("/img/bdon.png")));
				;
			}
			con.close();
		} catch (Exception e) {
			System.out.println();
		}
	}

	private boolean verificarCredenciaisProfessor(String email, String senha) {

		String select = "SELECT * FROM professor WHERE EMAIL = ? AND SENHA = ?";

		try {
			con = conexao.conectar();
			pst = con.prepareStatement(select);
			pst.setString(1, email);
			pst.setString(2, senha);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	private boolean verificarCredenciaisAluno(String cpf, String senhaA) {

		String select = "SELECT * FROM aluno WHERE CPF = ? AND SENHA = ?";

		try {
			con = conexao.conectar();
			pst = con.prepareStatement(select);
			pst.setString(1, cpf);
			pst.setString(2, senhaA);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
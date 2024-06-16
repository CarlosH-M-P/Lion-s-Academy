package face;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import jpsql.Conexao;
import utils.Validador;

public class FCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// Instanciar objetos

	Conexao conexao = new Conexao();
	private Connection con;
	private PreparedStatement pst;

	private final JPanel panel = new JPanel();
	private JLabel lblStatus;
	private JLabel lblData;
	private JLabel lblNewLabel;
	private JTextField txtCPF;
	private JLabel lblNewLabel_1;
	private JLabel lblFoto;
	private JLabel lblNewLabel_2;
	private JTextField txtNome;
	private JLabel lblNewLabel_3;
	private JTextField txtRG;
	private JLabel lblNewLabel_4;
	private JTextField txtEmail;
	private JLabel lblNewLabel_5;
	private JTextField txtTelefone;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JPasswordField txtConfirmSenha;
	private JPasswordField txtSenha;
	private JTextField txtDDD;
	private JComboBox comboSexo;
	private JButton btnLogin;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FCadastro frame = new FCadastro();
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
	public FCadastro() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FCadastro.class.getResource("/img/Logo.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				// meu código
				status();
				setarData();
			}
		});

		setTitle("Lion's Academy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 360);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setForeground(new Color(9, 0, 43));
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 277, 803, 46);
		contentPane.add(panel);
		panel.setLayout(null);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(FCadastro.class.getResource("/img/bdoff.png")));
		lblStatus.setBounds(771, 10, 32, 32);
		panel.add(lblStatus);

		lblData = new JLabel("");
		lblData.setBackground(SystemColor.text);
		lblData.setForeground(SystemColor.text);
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblData.setBounds(10, 27, 245, 19);
		panel.add(lblData);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(265, 23, 85, 19);
		panel.add(btnLimpar);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setForeground(new Color(166, 89, 1));
		btnLimpar.setToolTipText("Limpar");

		JButton btnCadastrar = new JButton("Cadastrar Professor");
		btnCadastrar.setBounds(360, 23, 148, 19);
		panel.add(btnCadastrar);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarP();
			}
		});
		btnCadastrar.setToolTipText("Cadastrar");
		btnCadastrar.setForeground(new Color(166, 89, 1));

		btnLogin = new JButton("Login");
		btnLogin.setToolTipText("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FLogin telaLogin = new FLogin();
				telaLogin.setVisible(true);
				dispose();
			}
		});
		btnLogin.setForeground(new Color(166, 89, 1));
		btnLogin.setBounds(659, 21, 85, 21);
		panel.add(btnLogin);
		
		JButton btnNewButton = new JButton("Cadastrar Aluno");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarA();
			}
		});
		btnNewButton.setForeground(new Color(166, 89, 1));
		btnNewButton.setBounds(518, 21, 131, 21);
		panel.add(btnNewButton);

		lblNewLabel = new JLabel("CPF *");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setBounds(10, 83, 45, 13);
		contentPane.add(lblNewLabel);

		txtCPF = new JTextField();
		txtCPF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "01234456789";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCPF.setBounds(75, 80, 96, 19);
		contentPane.add(txtCPF);
		txtCPF.setColumns(10);
		// Uso do PlainDocument para limitar os campos
		txtCPF.setDocument(new Validador(11));

		lblNewLabel_1 = new JLabel("Senha *");
		lblNewLabel_1.setForeground(SystemColor.text);
		lblNewLabel_1.setBounds(10, 224, 45, 13);
		contentPane.add(lblNewLabel_1);

		lblFoto = new JLabel("");
		lblFoto.setBorder(null);
		lblFoto.setIcon(new ImageIcon(FCadastro.class.getResource("/img/Logo.png")));
		lblFoto.setBounds(532, 58, 193, 200);
		contentPane.add(lblFoto);
		;

		lblNewLabel_2 = new JLabel("Nome *");
		lblNewLabel_2.setForeground(SystemColor.text);
		lblNewLabel_2.setBounds(10, 58, 45, 13);
		contentPane.add(lblNewLabel_2);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				if (caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtNome.setBounds(75, 55, 96, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		// Uso do PlainDocument para limitar os campos
		txtNome.setDocument(new Validador(50));

		lblNewLabel_3 = new JLabel("RG *");
		lblNewLabel_3.setForeground(SystemColor.text);
		lblNewLabel_3.setBounds(10, 106, 45, 13);
		contentPane.add(lblNewLabel_3);

		txtRG = new JTextField();
		txtRG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtRG.setBounds(75, 105, 96, 19);
		contentPane.add(txtRG);
		txtRG.setColumns(10);
		// Uso do PlainDocument para limitar os campos
		txtRG.setDocument(new Validador(9));

		lblNewLabel_4 = new JLabel("Sexo *");
		lblNewLabel_4.setForeground(SystemColor.text);
		lblNewLabel_4.setBounds(10, 133, 45, 13);
		contentPane.add(lblNewLabel_4);

		txtEmail = new JTextField();
		txtEmail.setBounds(75, 155, 96, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		// Uso do PlainDocument para limitar os campos
		txtEmail.setDocument(new Validador(50));

		lblNewLabel_5 = new JLabel("Email *");
		lblNewLabel_5.setForeground(SystemColor.text);
		lblNewLabel_5.setBounds(10, 158, 45, 13);
		contentPane.add(lblNewLabel_5);

		txtTelefone = new JTextField();
		txtTelefone.setText("\r\n");
		txtTelefone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtTelefone.setBounds(110, 180, 96, 19);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);
		// Uso do PlainDocument para limitar os campos
		txtTelefone.setDocument(new Validador(15));

		lblNewLabel_6 = new JLabel("Telefone *");
		lblNewLabel_6.setForeground(SystemColor.text);
		lblNewLabel_6.setBounds(10, 183, 65, 13);
		contentPane.add(lblNewLabel_6);

		lblNewLabel_7 = new JLabel("Confirmar Senha *");
		lblNewLabel_7.setForeground(SystemColor.text);
		lblNewLabel_7.setBounds(10, 254, 109, 13);
		contentPane.add(lblNewLabel_7);

		lblNewLabel_8 = new JLabel("CADASTRO");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_8.setForeground(SystemColor.text);
		lblNewLabel_8.setBounds(360, 10, 125, 35);
		contentPane.add(lblNewLabel_8);

		txtConfirmSenha = new JPasswordField();
		txtConfirmSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		txtConfirmSenha.setBounds(115, 248, 96, 19);
		contentPane.add(txtConfirmSenha);
		// Uso do PlainDocument para limitar os campos
		txtConfirmSenha.setDocument(new Validador(30));

		txtSenha = new JPasswordField();
		txtSenha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtSenha.setEchoChar((char) 0); // Mouse visível
			}

			@Override
			public void mouseExited(MouseEvent e) {
				txtSenha.setEchoChar('*'); // Mouse invisível
			}

		});
		txtSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

			}
		});
		txtSenha.setBounds(115, 221, 96, 19);
		contentPane.add(txtSenha);
		// Uso do PlainDocument para limitar os campos
		txtSenha.setDocument(new Validador(30));

		txtDDD = new JTextField();
		txtDDD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789+-";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtDDD.setBounds(75, 180, 25, 19);
		contentPane.add(txtDDD);
		txtDDD.setColumns(10);
		// Uso do PlainDocument para limitar os campos
		txtDDD.setDocument(new Validador(3));

		comboSexo = new JComboBox();
		comboSexo.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
		comboSexo.setToolTipText("Selecione o sexo");
		comboSexo.setModel(new DefaultComboBoxModel(new String[] { "M", "F" }));
		comboSexo.setBounds(75, 129, 45, 21);
		contentPane.add(comboSexo);

		lblNewLabel_9 = new JLabel("A senha deve conter no mínimo 8 caracteres");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_9.setForeground(SystemColor.text);
		lblNewLabel_9.setBounds(219, 225, 221, 13);
		contentPane.add(lblNewLabel_9);

		lblNewLabel_10 = new JLabel("A senha deve ter no mínimo uma letra maiuscula");
		lblNewLabel_10.setForeground(SystemColor.text);
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_10.setBounds(219, 236, 185, 20);
		contentPane.add(lblNewLabel_10);

		lblNewLabel_11 = new JLabel("A senha deve conter pelo menos um número");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_11.setForeground(SystemColor.text);
		lblNewLabel_11.setBounds(219, 255, 185, 13);
		contentPane.add(lblNewLabel_11);

	} // fim do construtor

	private void status() {
		try {
			con = conexao.conectar();
			if (con == null) {
				lblStatus.setIcon(new ImageIcon(FCadastro.class.getResource("/img/bdoff.png")));
				;
			} else {
				lblStatus.setIcon(new ImageIcon(FCadastro.class.getResource("/img/bdon.png")));
				;
			}
			con.close();
		} catch (Exception e) {
			System.out.println();
		}
	}

	private void setarData() {
		Date data = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
		lblData.setText(formatador.format(data));
	}

	private void adicionarP() {

		if (txtNome.getText().isEmpty() || txtCPF.getText().isEmpty() || txtRG.getText().isEmpty()
				|| comboSexo.getSelectedIndex() == -1 || txtEmail.getText().isEmpty() || txtTelefone.getText().isEmpty()
				|| txtDDD.getText().isEmpty() || String.valueOf(txtSenha.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos obrigatórios.");
			return;
		}

		 if (!validarSenha() || !validarCPF() || !validarRG() || !validarEmail(txtEmail.getText())) {
		        return;
		    }
		

		String insert = "insert into professor(NOME, CPF, RG, SEXO, EMAIL, TELEFONE, DDD, SENHA) "
				+ "values(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			con = conexao.conectar();
			pst = con.prepareStatement(insert);
			pst.setString(1, txtNome.getText());
			pst.setString(2, txtCPF.getText());
			pst.setString(3, txtRG.getText());
			pst.setString(4, (String) comboSexo.getSelectedItem());
			pst.setString(5, txtEmail.getText());
			pst.setString(6, txtTelefone.getText());
			pst.setString(7, txtDDD.getText());
			pst.setString(8, String.valueOf(txtSenha.getPassword()));

			int confirma = pst.executeUpdate();

			if (confirma == 1) {
				JOptionPane.showMessageDialog(null, "Professor cadastrado");
			} else {
				JOptionPane.showMessageDialog(null, "Professor não cadastrado");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void adicionarA() {

		if (txtNome.getText().isEmpty() || txtCPF.getText().isEmpty() || txtRG.getText().isEmpty()
				|| comboSexo.getSelectedIndex() == -1 || txtEmail.getText().isEmpty() || txtTelefone.getText().isEmpty()
				|| txtDDD.getText().isEmpty() || String.valueOf(txtSenha.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos obrigatórios.");
			return;
		}

		 if (!validarSenha() || !validarCPF() || !validarRG() || !validarEmail(txtEmail.getText())) {
		        return;
		    }

		String insert = "insert into aluno(NOME, CPF, RG, SEXO, EMAIL, TELEFONE, DDD, SENHA) "
				+ "values(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			con = conexao.conectar();
			pst = con.prepareStatement(insert);
			pst.setString(1, txtNome.getText());
			pst.setString(2, txtCPF.getText());
			pst.setString(3, txtRG.getText());
			pst.setString(4, (String) comboSexo.getSelectedItem());
			pst.setString(5, txtEmail.getText());
			pst.setString(6, txtTelefone.getText());
			pst.setString(7, txtDDD.getText());
			pst.setString(8, String.valueOf(txtSenha.getPassword()));

			int confirma = pst.executeUpdate();

			if (confirma == 1) {
				JOptionPane.showMessageDialog(null, "Aluno cadastrado");
			} else {
				JOptionPane.showMessageDialog(null, "Aluno não cadastrado");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	

	private void limpar() {
		txtNome.setText("");
		txtCPF.setText("");
		txtRG.setText("");
		txtEmail.setText("");
		comboSexo.setSelectedIndex(-1);
		txtTelefone.setText("");
		txtDDD.setText("");
		txtSenha.setText("");
		txtConfirmSenha.setText("");
		txtNome.requestFocus();

	}

	private boolean validarSenha() {
	    String senha = String.valueOf(txtSenha.getPassword());
	    String confirmarSenha = String.valueOf(txtConfirmSenha.getPassword());

	    // Pelo menos 1 letra maiúscula e 1 número
	    String regex = "^(?=.*[A-Z])(?=.*\\d).+$";

	    if (!senha.equals(confirmarSenha)) {
	        JOptionPane.showMessageDialog(null, "As senhas não coincidem.");
	        return false;
	    }

	    if (senha.length() < 8 || !senha.matches(regex)) {
	        JOptionPane.showMessageDialog(null, "A senha deve ter pelo menos 8 caracteres, incluindo pelo menos 1 letra maiúscula e 1 número.");
	        return false;
	    }

	    return true;
	}


	private boolean validarCPF() {
	    String CPF = txtCPF.getText().replaceAll("[^0-9]", ""); // Remover caracteres não numéricos

	    // Verificar se o CPF possui 11 dígitos
	    if (CPF.length() != 11) {
	        JOptionPane.showMessageDialog(null, "O CPF deve conter 11 dígitos");
	        return false;
	    }

	    // Verificar se todos os dígitos são iguais (dígitos repetidos)
	    if (CPF.matches("(\\d)\\1{10}")) {
	        JOptionPane.showMessageDialog(null, "Esse não é um CPF válido (dígitos repetidos)");
	        return false;
	    }

	    // Calcular o primeiro dígito verificador
	    int soma = 0;
	    for (int i = 0; i < 9; i++) {
	        soma += (CPF.charAt(i) - '0') * (10 - i);
	    }
	    int primeiroDigitoVerificador = 11 - (soma % 11);

	    if (primeiroDigitoVerificador >= 10) {
	        primeiroDigitoVerificador = 0;
	    }

	    // Verificar o primeiro dígito verificador
	    if (primeiroDigitoVerificador != CPF.charAt(9) - '0') {
	        JOptionPane.showMessageDialog(null, "CPF inválido");
	        return false;
	    }

	    // Calcular o segundo dígito verificador
	    soma = 0;
	    for (int i = 0; i < 10; i++) {
	        soma += (CPF.charAt(i) - '0') * (11 - i);
	    }
	    int segundoDigitoVerificador = 11 - (soma % 11);

	    if (segundoDigitoVerificador >= 10) {
	        segundoDigitoVerificador = 0;
	    }

	    // Verificar o segundo dígito verificador
	    if (segundoDigitoVerificador != CPF.charAt(10) - '0') {
	        JOptionPane.showMessageDialog(null, "CPF inválido");
	        return false;
	    }

	    return true;
	}


	private boolean validarRG() {
	    String RG = txtRG.getText();
	   
	    if (RG.length() != 9 || RG.matches("(\\d)\\1{8}")) {
	        JOptionPane.showMessageDialog(null, "RG inválido. Deve conter 9 dígitos e não pode ter dígitos repetidos.");
	        return false;
	    }

	    char primeiroDigito = RG.charAt(0);
	    // Verificar se o primeiro dígito é compatível com RGs de São Paulo (geralmente começa com 4, 5, 6, 7 ou 8)
	    if (primeiroDigito != '4' && primeiroDigito != '5' && primeiroDigito != '6' &&
	        primeiroDigito != '7' && primeiroDigito != '8') {
	        JOptionPane.showMessageDialog(null, "O RG não pertence a São Paulo.");
	        return false;
	    }
	    
	    return true;
	}

	private boolean validarEmail(String email) {
	    // Padrão de expressão regular para verificar se o e-mail está no formato correto
	    String regex = "^[A-Za-z0-9+_.-]+@(hotmail\\.com|gmail\\.com|outlook\\.com)$";

	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(email);

	    if (matcher.matches()) {
	        return true; // O e-mail é válido
	    } else {
	        JOptionPane.showMessageDialog(null, "E-mail inválido. Verifique o formato do e-mail ou o domínio "
	        		+ "(permitidos: hotmail.com, gmail.com, outlook.com).");
	        return false;
	    }
	}



}
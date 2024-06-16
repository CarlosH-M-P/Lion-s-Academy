package face;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jpsql.Conexao;
import utils.Validador;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FCriarTreino extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	Conexao conexao = new Conexao();
	private Connection con;
	private PreparedStatement pst;

	private JLabel lblStatus;
	private JLabel lblData;
	private JLabel lblNewLabel;
	private JTextField txtTreino;
	private JTextField txtRepet;
	private JTextField txtSerie;
	private JTextField txtProf;
	private JLabel lblNewLabel_6;
	private JTextField txtCPFA;
	private JButton btnLogin;
	private JComboBox comboDiv;
	private JComboBox comboDesc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FCriarTreino frame = new FCriarTreino();
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
	public FCriarTreino() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FCriarTreino.class.getResource("/img/Logo.png")));
		setTitle("Criar Treino");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
				setarData();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 360);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setForeground(new Color(9, 0, 43));
		panel.setBounds(0, 287, 626, 36);
		contentPane.add(panel);
		panel.setLayout(null);

		lblStatus = new JLabel("");
		lblStatus.setBounds(584, 0, 32, 32);
		lblStatus.setIcon(new ImageIcon(FCriarTreino.class.getResource("/img/bdoff.png")));
		panel.add(lblStatus);

		lblData = new JLabel("");
		lblData.setForeground(SystemColor.text);
		lblData.setBounds(10, 10, 251, 13);
		panel.add(lblData);

		JButton btnNewButton = new JButton("Cadastrar Treino");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnNewButton.setForeground(new Color(166, 89, 1));
		btnNewButton.setBounds(426, 11, 148, 21);
		panel.add(btnNewButton);

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FLogin telaLogin = new FLogin();
				telaLogin.setVisible(true);
				dispose();
			}
		});
		btnLogin.setForeground(new Color(166, 89, 1));
		btnLogin.setBounds(331, 11, 85, 21);
		panel.add(btnLogin);

		JButton btnNewButton_1 = new JButton("Limpar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnNewButton_1.setForeground(new Color(166, 89, 1));
		btnNewButton_1.setBounds(236, 10, 85, 21);
		panel.add(btnNewButton_1);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FCriarTreino.class.getResource("/img/Logo.png")));
		lblNewLabel.setBounds(372, 45, 200, 200);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Treino*");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(SystemColor.text);
		lblNewLabel_1.setBounds(29, 35, 98, 25);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Repetições*");
		lblNewLabel_2.setForeground(SystemColor.text);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(29, 65, 114, 25);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Séries*");
		lblNewLabel_3.setForeground(SystemColor.text);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(29, 94, 98, 25);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Descanso*");
		lblNewLabel_4.setForeground(SystemColor.text);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(29, 122, 98, 25);
		contentPane.add(lblNewLabel_4);

		txtTreino = new JTextField();
		txtTreino.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "@!#$%¨&*()";
				if (caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtTreino.setBounds(147, 35, 106, 19);
		contentPane.add(txtTreino);
		txtTreino.setColumns(10);
		// Uso do PlainDocument para limitar os campos
		txtTreino.setDocument(new Validador(50));

		txtRepet = new JTextField();
		txtRepet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "!@#$%&*";
				if (caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtRepet.setBounds(147, 65, 106, 19);
		contentPane.add(txtRepet);
		txtRepet.setColumns(10);
		// Uso do PlainDocument para limitar os campos
		txtRepet.setDocument(new Validador(9));
	

		txtSerie = new JTextField();
		txtSerie.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "1234567890";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtSerie.setBounds(147, 94, 106, 19);
		contentPane.add(txtSerie);
		txtSerie.setColumns(10);
		// Uso do PlainDocument para limitar os campos
		txtSerie.setDocument(new Validador(2));

		JLabel lblNewLabel_5 = new JLabel("Professor*");
		lblNewLabel_5.setForeground(SystemColor.text);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(29, 217, 98, 28);
		contentPane.add(lblNewLabel_5);

		txtProf = new JTextField();
		txtProf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				if (caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtProf.setBounds(147, 223, 96, 19);
		contentPane.add(txtProf);
		txtProf.setColumns(10);
		// Uso do PlainDocument para limitar os campos
		txtProf.setDocument(new Validador(50));

		lblNewLabel_6 = new JLabel("CPF do aluno*");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_6.setForeground(SystemColor.text);
		lblNewLabel_6.setBounds(29, 252, 151, 25);
		contentPane.add(lblNewLabel_6);

		txtCPFA = new JTextField();
		txtCPFA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCPFA.setBounds(182, 252, 96, 19);
		contentPane.add(txtCPFA);
		txtCPFA.setColumns(10);
		// Uso do PlainDocument para limitar os campos
		txtCPFA.setDocument(new Validador(11));

		JLabel lblDiv = new JLabel("Divisão*");
		lblDiv.setForeground(SystemColor.text);
		lblDiv.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDiv.setBounds(29, 151, 86, 25);
		contentPane.add(lblDiv);

		comboDesc = new JComboBox();
		comboDesc.setModel(new DefaultComboBoxModel(new String[] {"", "30 Segundos", "1 Minuto", "2 Minutos", "3 Minutos", "4 Minutos", "5 Minutos"}));
		comboDesc.setBounds(147, 122, 106, 22);
		contentPane.add(comboDesc);

		comboDiv = new JComboBox();
		comboDiv.setModel(new DefaultComboBoxModel(new String[] {"", "A", "B", "C", "D", "E"}));
		comboDiv.setBounds(147, 151, 35, 21);
		contentPane.add(comboDiv);
	}

	private void status() {
		try {
			con = conexao.conectar();
			if (con == null) {
				lblStatus.setIcon(new ImageIcon(FCriarTreino.class.getResource("/img/bdoff.png")));
			} else {
				lblStatus.setIcon(new ImageIcon(FCriarTreino.class.getResource("/img/bdon.png")));
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

	private void adicionar() {
		if (txtTreino.getText().isEmpty() || txtRepet.getText().isEmpty() || txtSerie.getText().isEmpty()
				|| comboDesc.getSelectedIndex() == 0 || txtCPFA.getText().isEmpty() || txtProf.getText().isEmpty()
				|| comboDiv.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos obrigatórios.");
			return;
		}

		boolean cpfExiste = verificarCredenciaisAluno(txtCPFA.getText());

		if (cpfExiste) {
			// O CPF existe no banco de dados.
			String insert = "insert into criartreino(NOMEDOEXERCICIO, SERIE, REPETICOES, TEMPODESCANSO, TREINONU, NOME_PROFESSOR, CPF_ALUNO) "
					+ "values(?, ?, ?, ?, ?, ?, ?)";

			try {
				con = conexao.conectar();
				pst = con.prepareStatement(insert);
				pst.setString(1, txtTreino.getText());
				pst.setString(2, txtSerie.getText());
				pst.setString(3, txtRepet.getText());
				pst.setString(4, (String) comboDesc.getSelectedItem());
				pst.setString(5, (String) comboDiv.getSelectedItem());
				pst.setString(6, txtProf.getText());
				pst.setString(7, txtCPFA.getText());

				int confirma = pst.executeUpdate();

				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Treino cadastrado com sucesso.");
				} else {
					JOptionPane.showMessageDialog(null, "Falha ao cadastrar o treino.");
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			// O CPF não existe no banco de dados.
			JOptionPane.showMessageDialog(null, "CPF não encontrado no banco de dados. Verifique o CPF inserido.");
		}
	}

	private void limpar() {
		txtTreino.setText("");
		txtSerie.setText("");
		txtRepet.setText("");
		comboDesc.setSelectedIndex(-1);
		comboDiv.setSelectedIndex(-1);
		txtProf.setText("");
		txtCPFA.setText("");

	}

	private boolean verificarCredenciaisAluno(String cpf) {
		String select = "SELECT CPF FROM aluno WHERE CPF = ?";

		try {
			con = conexao.conectar();
			pst = con.prepareStatement(select);
			pst.setString(1, cpf);
			ResultSet resultSet = pst.executeQuery();

			if (resultSet.next()) {
				String cpfDoBanco = resultSet.getString("CPF");
				return cpf.equals(cpfDoBanco);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false; // CPF não encontrado no banco de dados.
	}
}

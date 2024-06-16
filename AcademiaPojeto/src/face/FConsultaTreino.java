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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import jpsql.Conexao;
import jpsql.UsuarioDAO;
import utils.Validador;

public class FConsultaTreino extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PreparedStatement pst;

	Conexao conexao = new Conexao();
	private Connection con;
	private JLabel lblData;
	private JLabel lblStatus;
	private JTextField txtTreino;
	private JTextField txtRepet;
	private JTextField txtSerie;
	private JTextField txtDesc;
	private JComboBox comboDiv;
	private JTextField txtProf;
	private JTextField txtCPF;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FConsultaTreino frame = new FConsultaTreino();
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
	public FConsultaTreino() {
		setTitle("Consultar Treino");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FConsultaTreino.class.getResource("/img/Logo.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				setarData();
				status();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 408);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.text);
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 337, 635, 34);
		contentPane.add(panel);
		panel.setLayout(null);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(FConsultaTreino.class.getResource("/img/bdoff.png")));
		lblStatus.setBounds(593, 0, 32, 33);
		panel.add(lblStatus);

		lblData = new JLabel("");
		lblData.setForeground(SystemColor.text);
		lblData.setBounds(10, 10, 227, 23);
		panel.add(lblData);

		JButton btnConc = new JButton("Concluido");
		btnConc.setBounds(444, 0, 94, 21);
		panel.add(btnConc);
		btnConc.setForeground(new Color(166, 89, 1));

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		btnLimpar.setForeground(new Color(166, 89, 1));
		btnLimpar.setBounds(349, 0, 85, 21);
		panel.add(btnLimpar);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FLogin telaLogin = new FLogin();
				telaLogin.setVisible(true);
				dispose();
			}
		});
		btnLogin.setForeground(new Color(166, 89, 1));
		btnLogin.setBounds(254, 0, 85, 21);
		panel.add(btnLogin);
		btnConc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtCPF.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos obrigatórios (*).");
					return;
				}

				// Consulta para obter o ID do registro a ser excluído
				String selectIdQuery = "SELECT IDCRIARTREINO FROM criartreino WHERE CPF_ALUNO = ? AND TREINONU = ?";
				String cpf = txtCPF.getText();
				String treinoNu = (String) comboDiv.getSelectedItem();

				try {
				    Connection con = conexao.conectar();
				    PreparedStatement pstSelectId = con.prepareStatement(selectIdQuery);
				    pstSelectId.setString(1, cpf);
				    pstSelectId.setString(2, treinoNu);
				    
				    ResultSet rs = pstSelectId.executeQuery();

				    if (rs.next()) {
				        int idCriarTreino = rs.getInt("IDCRIARTREINO");
				        // Agora você tem o ID do registro que deseja excluir
				        // Você pode usar esse ID para a exclusão

				        String deleteQuery = "DELETE FROM criartreino WHERE IDCRIARTREINO = ?";
				        PreparedStatement pstDelete = con.prepareStatement(deleteQuery);
				        pstDelete.setInt(1, idCriarTreino);

				        int rowsAffected = pstDelete.executeUpdate();

				        if (rowsAffected > 0) {
				            // Os registros foram excluídos com sucesso
				            JOptionPane.showMessageDialog(null, "Treino concluído com sucesso");
				            limpar();
				        } else {
				            // Nenhum registro correspondente ao CPF
				            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado para o CPF: " + cpf);
				        }

				        con.close();
				    } else {
				        // Não foi encontrado um registro correspondente aos critérios
				        JOptionPane.showMessageDialog(null, "Nenhum registro encontrado para o CPF: " + cpf + " e número de treino: " + treinoNu);
				    }
				} catch (SQLException e1) {
				    e1.printStackTrace();
				}

			}
		});

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(FConsultaTreino.class.getResource("/img/Logo.png")));
		lblNewLabel.setBounds(407, 43, 200, 200);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Treinos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(SystemColor.text);
		lblNewLabel_1.setBounds(283, 10, 124, 34);
		contentPane.add(lblNewLabel_1);

		txtTreino = new JTextField();
		txtTreino.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTreino.setEditable(false);
		txtTreino.setBounds(124, 180, 154, 20);
		contentPane.add(txtTreino);
		txtTreino.setColumns(10);

		JButton btnConsul = new JButton("Consultar");
		btnConsul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				try {
					// consultar os treinos do aluno
					String cpf = txtCPF.getText();
					UsuarioDAO objConsultaTreinoDao = new UsuarioDAO();
					ResultSet rsConsultaTreino = objConsultaTreinoDao.consultarTreinosPorCPF(cpf);

					if (rsConsultaTreino.next()) {

						String nomeExercicio = rsConsultaTreino.getString("NOMEDOEXERCICIO");
						String serie = rsConsultaTreino.getString("SERIE");
						String repeticoes = rsConsultaTreino.getString("REPETICOES");
						String tempoDescanso = rsConsultaTreino.getString("TEMPODESCANSO");
						String nomeProfessor = rsConsultaTreino.getString("NOME_PROFESSOR");

						// Atualiza os componentes com os valores obtidos
						txtTreino.setText(nomeExercicio);
						txtSerie.setText(serie);
						txtRepet.setText(repeticoes);
						txtDesc.setText(tempoDescanso);
						txtProf.setText(nomeProfessor);

					} else {
						// Usuário não encontrado, exiba uma mensagem de erro
						JOptionPane.showMessageDialog(null, "Nenhum treino cadastrado");
					}
				} catch (SQLException erro) {
					JOptionPane.showMessageDialog(null, "Erro ao recuperar os treinos: " + erro.getMessage());
				}
			}
		});
		btnConsul.setForeground(new Color(166, 89, 1));
		btnConsul.setBounds(65, 133, 103, 21);
		contentPane.add(btnConsul);

		txtRepet = new JTextField();
		txtRepet.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtRepet.setEditable(false);
		txtRepet.setBounds(124, 209, 154, 20);
		contentPane.add(txtRepet);
		txtRepet.setColumns(10);

		txtSerie = new JTextField();
		txtSerie.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSerie.setEditable(false);
		txtSerie.setBounds(124, 238, 154, 20);
		contentPane.add(txtSerie);
		txtSerie.setColumns(10);

		txtDesc = new JTextField();
		txtDesc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDesc.setEditable(false);
		txtDesc.setBounds(124, 267, 154, 20);
		contentPane.add(txtDesc);
		txtDesc.setColumns(10);

		txtProf = new JTextField();
		txtProf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtProf.setEditable(false);
		txtProf.setBounds(124, 308, 154, 19);
		contentPane.add(txtProf);
		txtProf.setColumns(10);

		txtCPF = new JTextField();
		txtCPF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCPF.setBounds(55, 51, 135, 20);
		contentPane.add(txtCPF);
		txtCPF.setColumns(10);
		// Uso do PlainDocument para limitar os campos
		txtCPF.setDocument(new Validador(11));

		lblNewLabel_2 = new JLabel("CPF");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setForeground(SystemColor.text);
		lblNewLabel_2.setBounds(10, 53, 58, 17);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Professor");
		lblNewLabel_3.setForeground(SystemColor.text);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(10, 308, 110, 27);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Treino");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setForeground(SystemColor.text);
		lblNewLabel_4.setBounds(10, 180, 69, 19);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Repetições");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setForeground(SystemColor.text);
		lblNewLabel_5.setBounds(10, 208, 110, 21);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Séries");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_6.setForeground(SystemColor.text);
		lblNewLabel_6.setBounds(10, 238, 89, 19);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Descanso");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_7.setForeground(SystemColor.text);
		lblNewLabel_7.setBounds(10, 267, 89, 19);
		contentPane.add(lblNewLabel_7);

		 comboDiv = new JComboBox();
		comboDiv.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				preencherComboDiv();
			}
		});
		comboDiv.setBounds(55, 95, 44, 26);
		contentPane.add(comboDiv);

		JLabel lblNewLabel_8 = new JLabel("Dia");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_8.setForeground(SystemColor.text);
		lblNewLabel_8.setBounds(10, 95, 58, 17);
		contentPane.add(lblNewLabel_8);
	}

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

	public void consultarTreinosPorCPF(String cpf) {
		String query = "SELECT NOMEDOEXERCICIO, SERIE, REPETICOES, TEMPODESCANSO, TREINONU, NOME_PROFESSOR FROM criartreino WHERE CPF_ALUNO = ?";

		try {
			Connection con = conexao.conectar();
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, cpf);

			ResultSet rs = pst.executeQuery();

			// Limpa os campos antes de exibir novos resultados
			txtTreino.setText("");
			txtSerie.setText("");
			txtRepet.setText("");
			txtDesc.setText("");
			txtProf.setText("");
			comboDiv.setSelectedIndex(-1);

			if (rs.next()) {
				// Obtém os valores da consulta
				String nomeExercicio = rs.getString("NOMEDOEXERCICIO");
				String serie = rs.getString("SERIE");
				String repeticoes = rs.getString("REPETICOES");
				String tempoDescanso = rs.getString("TEMPODESCANSO");
				String nomeProfessor = rs.getString("NOME_PROFESSOR");

				// Atualiza os componentes com os valores obtidos
				txtTreino.setText(nomeExercicio);
				txtSerie.setText(serie);
				txtRepet.setText(repeticoes);
				txtDesc.setText(tempoDescanso);
				txtProf.setText(nomeProfessor);

			} else {
				// Tratar o caso em que não há resultados
				JOptionPane.showMessageDialog(null, "Nenhum treino encontrado para o CPF: " + cpf);
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluirTreinosPorCPF(String cpf, String treinoNu, String idCriarTreino) {
		String deleteQuery = "DELETE FROM criartreino WHERE CPF_ALUNO = ? AND TREINONU = ?";

		try {
			Connection con = conexao.conectar();
			PreparedStatement pst = con.prepareStatement(deleteQuery);
			pst.setString(1, cpf);
			pst.setString(2, treinoNu);

			int rowsAffected = pst.executeUpdate();

			if (rowsAffected > 0) {
				// Os registros foram excluídos com sucesso
				JOptionPane.showMessageDialog(null, "Treinos concluidos com sucesso para o CPF: " + cpf);
				// Limpar os campos após a exclusão, se necessário
				txtTreino.setText("");
				txtSerie.setText("");
				txtRepet.setText("");
				txtDesc.setText("");
				txtProf.setText("");
				txtCPF.requestFocus();
			} else {
				// Nenhum registro correspondente ao CPF
				JOptionPane.showMessageDialog(null, "Nenhum treino encontrado para o CPF: " + cpf);
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void limpar() {
		txtTreino.setText("");
		txtCPF.setText("");
		txtSerie.setText("");
		txtRepet.setText("");
		txtDesc.setText("");
		txtProf.setText("");
		comboDiv.setSelectedIndex(-1);
		txtCPF.requestFocus();

	}
	
	private void preencherComboDiv() {
	    String query = "SELECT DISTINCT TREINONU FROM criartreino"; // ou outra consulta que retorne valores únicos
	    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

	    try {
	        Connection con = conexao.conectar();
	        PreparedStatement pst = con.prepareStatement(query);
	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            String treinoNu = rs.getString("TREINONU");
	            model.addElement(treinoNu);
	        }

	        comboDiv.setModel(model);
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}

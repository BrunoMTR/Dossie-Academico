import java.awt.EventQueue;
import java.text.*;
import java.awt.print.*;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.sql.SQLException;

import javax.swing.border.EtchedBorder;

public class DossieAcademico {

	private JFrame frame;
	private JTextField tfUsuario;
	private JPasswordField pfPalavraPasse;
	private JPanel painelTurma;
	private Mysql mysql;
	private JTable tabelaTurma;
    private DefaultTableModel modeloTabela;
    private DefaultTableModel modeloTabelaAluno;
    private JTextField tfCadeira;
    private JTextField tfFaculdade;
    private JLabel lblAvatar2; 
    private DefaultComboBoxModel modeloPesos;
    private DefaultComboBoxModel modeloPeriodos;
    private JComboBox cbPeriodo;
    private JLabel lblAvatar3;
    private JComboBox cbPeso3;
    private JComboBox cbPeso2;
    private JComboBox cbPeso1;
    private JLabel lblMensagemPeso;
    private JLabel lblMensagemErro;
    private JPanel painelAluno;
    private String idTurmaSelecionado;
    private int pesoTotalTestes;
    private int idTurma;
    private String codigoAlunoSelecionado;
    private final String [] arrayPesos = {"0","10","20","30","40","50","60","70","80","90","100"};
    private final String [] arrayPeriodos= {"","LABORAL","POS-LABORAL"};
    private JTable tabelaAluno;
    private JTextField tfNome;
    private JTextField tfSobrenome;
    private JTextField tfCodigo;
    private JTextField tfTeste1;
    private JTextField tfTeste2;
    private JTextField tfTeste3;
    private JLabel lblNomeUsuario;
  
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/**
		 * Aplicando o look and feel.
		 */

try {
	for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
        }
    }   
} catch (Exception e) {
    e.printStackTrace();
}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DossieAcademico window = new DossieAcademico();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DossieAcademico() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mysql = new Mysql();
		frame = new JFrame();
		frame.setBounds(130, 20, 1100, 700);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel painelEntrada = new JPanel();
		painelEntrada.setBackground(new Color(243, 190, 148));
		layeredPane.add(painelEntrada, "name_14137033215300");
		painelEntrada.setLayout(null);
		
		JLabel lblUsuario = new JLabel("");
		lblUsuario.setIcon(new ImageIcon("C:\\Users\\Dell\\Documents\\Projectos eclipse\\Dossie\\rapaz.png"));
		lblUsuario.setBounds(451, 268, 37, 36);
		painelEntrada.add(lblUsuario);
		
		tfUsuario = new JTextField();
		tfUsuario.setFont(new Font("SansSerif", Font.BOLD, 14));
		tfUsuario.setForeground(Color.BLACK);
		tfUsuario.setBackground(Color.WHITE);
		tfUsuario.setBounds(500, 268, 167, 28);
		painelEntrada.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		JLabel lblPalavraPasse = new JLabel("");
		lblPalavraPasse.setIcon(new ImageIcon("C:\\Users\\Dell\\Documents\\Projectos eclipse\\Dossie\\chave.png"));
		lblPalavraPasse.setBounds(451, 302, 37, 36);
		painelEntrada.add(lblPalavraPasse);
		
		pfPalavraPasse = new JPasswordField();
		pfPalavraPasse.setFont(new Font("SansSerif", Font.BOLD, 18));
		pfPalavraPasse.setForeground(Color.BLACK);
		pfPalavraPasse.setBackground(Color.WHITE);
		pfPalavraPasse.setBounds(500, 308, 167, 28);
		painelEntrada.add(pfPalavraPasse);
		
		JButton btnCriar = new JButton("Criar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				///*************************************************************************************************************
				
				if(mysql.ValidacaoUsuario(tfUsuario.getText()) == 0 && !tfUsuario.getText().isEmpty() && !pfPalavraPasse.getText().isEmpty()) {
					
					
					String [] partes = mysql.CriarUsuario(tfUsuario.getText().toString(), pfPalavraPasse.getText().toString()).split("-",2); 
					
					modeloTabela.setRowCount(0);
					mysql.BuscaTurma(modeloTabela, mysql.Entrar(partes[0],partes[1]));
					layeredPane.removeAll();
					layeredPane.add(painelTurma);
					layeredPane.repaint();
					layeredPane.revalidate();
					
					lblAvatar3.setText(tfUsuario.getText().toUpperCase());
				}
				else {
					tfUsuario.setBackground(new Color(244,83,28));
		        	pfPalavraPasse.setBackground(new Color(244,83,28));
		        	tfUsuario.setText("");
					pfPalavraPasse.setText("");
		        	tfUsuario.requestFocus();
				}
				
				
			}
		});
		btnCriar.setForeground(Color.BLACK);
		btnCriar.setBackground(new Color(243, 190, 148));
		btnCriar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnCriar.setBounds(465, 376, 103, 38);
		painelEntrada.add(btnCriar);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setForeground(Color.BLACK);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				//se o campo de usuario e de palavra passe estiverem vazios 
				//alterar a cor dos campos de texto
				//levar o foco do ponteiro ao textField
				if(tfUsuario.getText().isEmpty() && pfPalavraPasse.getText().isEmpty()) {
		        	tfUsuario.setBackground(new Color(244,83,28));
		        	pfPalavraPasse.setBackground(new Color(244,83,28));
		        	tfUsuario.requestFocus();
		        	
		        }
				//se os campos nao estiverem vazios faca:
				else if(!tfUsuario.getText().isEmpty() && !pfPalavraPasse.getText().isEmpty()) {
					
					
					//retorna o usuario_id ou 0 caso nao exista
					
						
					try{
						
						if(mysql.Entrar(tfUsuario.getText(),pfPalavraPasse.getText()) != 0) {
							layeredPane.removeAll();
							layeredPane.add(painelTurma);
							layeredPane.repaint();
							layeredPane.revalidate();
							
							
				        	
							
							mysql.BuscaTurma(modeloTabela, mysql.Entrar(tfUsuario.getText(),pfPalavraPasse.getText()));
							lblAvatar3.setText(tfUsuario.getText().toUpperCase());
							lblNomeUsuario.setText(tfUsuario.getText().toUpperCase());
							
						}
						else{
							tfUsuario.setBackground(new Color(244,83,28));
				        	pfPalavraPasse.setBackground(new Color(244,83,28));
				        	tfUsuario.requestFocus();
				        	tfUsuario.setText("");
							pfPalavraPasse.setText("");
							
						}}catch(Exception ex) {
							ex.printStackTrace();
						}
							
							
						}
					}
					 
		
				
			
		});
		btnEntrar.setBackground(new Color(243, 190, 148));
		btnEntrar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnEntrar.setBounds(580, 376, 97, 38);
		painelEntrada.add(btnEntrar);
		
		JLabel lblAvatar = new JLabel("");
		lblAvatar.setIcon(new ImageIcon("C:\\Users\\Dell\\Documents\\Projectos eclipse\\Dossie\\Avatar.png"));
		lblAvatar.setBounds(528, 168, 76, 88);
		painelEntrada.add(lblAvatar);
		
		JLabel lblFundoAzul = new JLabel("");
		lblFundoAzul.setIcon(new ImageIcon("C:\\Users\\Dell\\Documents\\Projectos eclipse\\Dossie\\imageonline-co-roundcorner1.png"));
		lblFundoAzul.setBounds(367, 71, 418, 503);
		painelEntrada.add(lblFundoAzul);
		
		JLabel lblGitHub = new JLabel("BrunoMTR");
		lblGitHub.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblGitHub.setForeground(Color.WHITE);
		lblGitHub.setVerticalAlignment(SwingConstants.BOTTOM);
		lblGitHub.setIcon(new ImageIcon("C:\\Users\\Dell\\Documents\\Projectos eclipse\\Dossie\\LogoGitHub.png"));
		lblGitHub.setBounds(28, 517, 122, 119);
		painelEntrada.add(lblGitHub);
		
		JLabel lblCartoon = new JLabel("");
		lblCartoon.setIcon(new ImageIcon("C:\\Users\\Dell\\Documents\\Projectos eclipse\\Dossie\\gifCartoon.gif"));
		lblCartoon.setBounds(143, 575, 50, 61);
		painelEntrada.add(lblCartoon);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Dell\\Documents\\Projectos eclipse\\Dossie\\bg2.jpg"));
		lblNewLabel_2.setBounds(0, 0, 1084, 661);
		painelEntrada.add(lblNewLabel_2);
		
		painelTurma = new JPanel();
		painelTurma.setBackground(new Color(243, 190, 148));
		layeredPane.add(painelTurma, "name_14140471204300");
		painelTurma.setLayout(null);
		
		lblMensagemErro = new JLabel("PREENCHA TODOS OS CAMPOS");
		lblMensagemErro.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblMensagemErro.setForeground(Color.RED);
		lblMensagemErro.setBounds(135, 484, 237, 16);
		lblMensagemErro.setVisible(false);
		
		lblMensagemPeso = new JLabel("OS PESOS DEVEM SOMAR PRA 100%");
		lblMensagemPeso.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblMensagemPeso.setForeground(Color.RED);
		lblMensagemPeso.setBounds(121, 349, 251, 16);
		lblMensagemPeso.setVisible(false);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(painelEntrada);
				layeredPane.repaint();
				layeredPane.revalidate();
				
				tfCadeira.setText(null);
				tfFaculdade.setText(null);
				cbPeriodo.setSelectedItem("");
				cbPeso1.setSelectedItem("0");
				cbPeso2.setSelectedItem("0");
				cbPeso3.setSelectedItem("0");
				
				
				tfUsuario.requestFocus();
	        	tfUsuario.setText("");
				pfPalavraPasse.setText("");
				
				tfUsuario.setBackground(Color.WHITE);
				pfPalavraPasse.setBackground(Color.WHITE);
				
				modeloTabela.setRowCount(0);
				mysql.BuscaTurma(modeloTabela, mysql.Entrar(tfUsuario.getText(),pfPalavraPasse.getText()));
				
				
			}
		});
		
		JButton btnNewButton = new JButton("Apagar Conta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mysql.ApagarUsuario(mysql.Entrar(tfUsuario.getText(), pfPalavraPasse.getText()));
				layeredPane.removeAll();
				layeredPane.add(painelEntrada);
				layeredPane.repaint();
				layeredPane.revalidate();
				
				tfCadeira.setText(null);
				tfFaculdade.setText(null);
				cbPeriodo.setSelectedItem("");
				cbPeso1.setSelectedItem("0");
				cbPeso2.setSelectedItem("0");
				cbPeso3.setSelectedItem("0");
				
				
				tfUsuario.requestFocus();
	        	tfUsuario.setText("");
				pfPalavraPasse.setText("");
				
				tfUsuario.setBackground(Color.WHITE);
				pfPalavraPasse.setBackground(Color.WHITE);
				
				
				
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setBounds(936, 11, 114, 36);
		painelTurma.add(btnNewButton);
		btnSair.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSair.setForeground(new Color(0, 0, 0));
		btnSair.setBackground(new Color(204, 0, 0));
		btnSair.setIcon(null);
		btnSair.setBounds(17, 6, 86, 36);
		painelTurma.add(btnSair);
		painelTurma.add(lblMensagemPeso);
		painelTurma.add(lblMensagemErro);
		
		lblAvatar3 = new JLabel("");
		lblAvatar3.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblAvatar3.setBounds(264, 109, 94, 16);
		painelTurma.add(lblAvatar3);
		
		JButton btnCriarTurma = new JButton("Criar");
		btnCriarTurma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//so vai criar uma nova turma caso a soma dos pesos dos testes seja igual a 100
				pesoTotalTestes = Integer.parseInt( cbPeso1.getSelectedItem().toString()) + Integer.parseInt( cbPeso2.getSelectedItem().toString()) + Integer.parseInt( cbPeso3.getSelectedItem().toString());   
				if(pesoTotalTestes != 100) {
					lblMensagemPeso.setVisible(true);
				}
					
				else if(!tfCadeira.getText().isEmpty() && !tfFaculdade.getText().isEmpty() && !cbPeriodo.getSelectedItem().toString().equals("")) {
					
					mysql.CriarTurma(mysql.Entrar(tfUsuario.getText(),pfPalavraPasse.getText()), cbPeriodo,tfCadeira, tfFaculdade, cbPeso1, cbPeso2, cbPeso3);
					modeloTabela.setRowCount(0);
					mysql.BuscaTurma(modeloTabela, mysql.Entrar(tfUsuario.getText(),pfPalavraPasse.getText()));
					
					//limpar os campos 
					tfCadeira.setText(null);
					tfFaculdade.setText(null);
					cbPeriodo.setSelectedItem("");
					cbPeso1.setSelectedItem("0");
					cbPeso2.setSelectedItem("0");
					cbPeso3.setSelectedItem("0");
					lblMensagemErro.setVisible(false);
					lblMensagemPeso.setVisible(false);
					
					
					
					
				}
				else {
					lblMensagemErro.setVisible(true);
				}
			
				
				
			}
		});
		btnCriarTurma.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnCriarTurma.setBackground(new Color(243, 190, 148));
		btnCriarTurma.setBounds(109, 394, 86, 36);
		painelTurma.add(btnCriarTurma);
		
		JLabel lblNewLabel_3 = new JLabel("PESO DE TESTES %");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(177, 285, 142, 16);
		painelTurma.add(lblNewLabel_3);
		
		cbPeso3 = new JComboBox();
		cbPeso3.setFont(new Font("SansSerif", Font.BOLD, 14));
		cbPeso3.setBounds(293, 313, 65, 26);
		modeloPesos = new DefaultComboBoxModel(arrayPesos);
		cbPeso3.setModel(modeloPesos);
		painelTurma.add(cbPeso3);
		
		cbPeso2 = new JComboBox();
		cbPeso2.setFont(new Font("SansSerif", Font.BOLD, 14));
		cbPeso2.setBounds(216, 313, 65, 26);
		modeloPesos = new DefaultComboBoxModel(arrayPesos);
		cbPeso2.setModel(modeloPesos);
		painelTurma.add(cbPeso2);
		
		cbPeso1 = new JComboBox();
		cbPeso1.setFont(new Font("SansSerif", Font.BOLD, 14));
		cbPeso1.setBounds(139, 313, 65, 26);
		modeloPesos = new DefaultComboBoxModel(arrayPesos);
		cbPeso1.setModel(modeloPesos);
		painelTurma.add(cbPeso1);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfCadeira.setText(null);
				tfFaculdade.setText(null);
				cbPeriodo.setSelectedItem("");
				cbPeso1.setSelectedItem("0");
				cbPeso2.setSelectedItem("0");
				cbPeso3.setSelectedItem("0");
			}
		});
		scrollPane.setBounds(454, 76, 596, 467);
		painelTurma.add(scrollPane);
		
		tabelaTurma = new JTable();
		tabelaTurma.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabelaTurma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linhaSelecionada = tabelaTurma.getSelectedRow();
				idTurmaSelecionado = tabelaTurma.getModel().getValueAt(linhaSelecionada, 0).toString();
				 mysql.LinhaSelecionada(idTurmaSelecionado,tfCadeira,tfFaculdade,cbPeriodo,cbPeso1,cbPeso2,cbPeso3);
				 //////////////55555555555555555555555555555555555555555555555555555555
				}
		});
		tabelaTurma.setFont(new Font("SansSerif", Font.BOLD, 12));
		tabelaTurma.setForeground(Color.WHITE);
		tabelaTurma.setBackground(new Color(192, 192, 192));
		scrollPane.setViewportView(tabelaTurma);
		Object [] ColunasTabelaTurma = {"#Turma","Cadeira","Periodo","Faculdade","Peso 1","Peso 2"," Peso 3"};
		modeloTabela = new DefaultTableModel(ColunasTabelaTurma,0);
		
		tabelaTurma.setModel(modeloTabela);
		
		tfCadeira = new JTextField();
		tfCadeira.setFont(new Font("SansSerif", Font.BOLD, 14));
		tfCadeira.setForeground(Color.BLACK);
		tfCadeira.setBackground(Color.WHITE);
		tfCadeira.setBounds(156, 154, 193, 28);
		painelTurma.add(tfCadeira);
		tfCadeira.setColumns(10);
		
		JLabel lblCadeira = new JLabel("Cadeira");
		lblCadeira.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblCadeira.setForeground(Color.WHITE);
		lblCadeira.setBounds(78, 160, 55, 16);
		painelTurma.add(lblCadeira);
		
		tfFaculdade = new JTextField();
		tfFaculdade.setFont(new Font("SansSerif", Font.BOLD, 14));
		tfFaculdade.setForeground(Color.BLACK);
		tfFaculdade.setBackground(Color.WHITE);
		tfFaculdade.setBounds(156, 194, 193, 28);
		painelTurma.add(tfFaculdade);
		tfFaculdade.setColumns(10);
		
		JLabel lblFaculdade = new JLabel("Faculdade");
		lblFaculdade.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblFaculdade.setForeground(Color.WHITE);
		lblFaculdade.setBounds(78, 200, 78, 16);
		painelTurma.add(lblFaculdade);
		
		JLabel lblPeriodo = new JLabel("Periodo");
		lblPeriodo.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblPeriodo.setForeground(Color.WHITE);
		lblPeriodo.setBounds(78, 240, 55, 16);
		painelTurma.add(lblPeriodo);
		
		cbPeriodo = new JComboBox();
		cbPeriodo.setFont(new Font("SansSerif", Font.BOLD, 14));
		cbPeriodo.setForeground(Color.WHITE);
		cbPeriodo.setBackground(Color.WHITE);
		cbPeriodo.setBounds(156, 234, 193, 28);
		modeloPeriodos = new DefaultComboBoxModel(arrayPeriodos);
		cbPeriodo.setModel(modeloPeriodos);
		painelTurma.add(cbPeriodo);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tfCadeira.getText().isEmpty()) {
					//////////////VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV
					int idTurmaSelecionada;
					String temporaria = tabelaTurma.getModel().getValueAt(tabelaTurma.getSelectedRow(), 0).toString();
					idTurmaSelecionada = Integer.parseInt(temporaria);
					mysql.ApagarTurma(idTurmaSelecionada);
					
					//apaga tudo na tabela
					modeloTabela.setRowCount(0);
					
					//limpar os campos 
					tfCadeira.setText(null);
					tfFaculdade.setText(null);
					cbPeriodo.setSelectedItem("");
					cbPeso1.setSelectedItem("0");
					cbPeso2.setSelectedItem("0");
					cbPeso3.setSelectedItem("0");
					
					//atualiza os itens da tabela
					mysql.BuscaTurma(modeloTabela, mysql.Entrar(tfUsuario.getText(),pfPalavraPasse.getText()));
					
					//****************falta apagar todos alunos dessa turma*************
					
					
				}
				
				
			}
		});
		btnApagar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnApagar.setForeground(Color.BLACK);
		btnApagar.setBackground(new Color(243, 190, 148));
		btnApagar.setBounds(287, 394, 78, 36);
		painelTurma.add(btnApagar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				///**********************************************************************************88888888888888
				pesoTotalTestes = Integer.parseInt( cbPeso1.getSelectedItem().toString()) + Integer.parseInt( cbPeso2.getSelectedItem().toString()) + Integer.parseInt( cbPeso3.getSelectedItem().toString()); 
				if(!tfCadeira.getText().isEmpty() && !tfFaculdade.getText().isEmpty() && !cbPeriodo.getSelectedItem().toString().equals("")&& pesoTotalTestes == 100) {
					
					mysql.AtualizarTurma(Integer.parseInt(idTurmaSelecionado),cbPeriodo.getSelectedItem().toString(),tfCadeira.getText(),tfFaculdade.getText(),Integer.parseInt( cbPeso1.getSelectedItem().toString()),Integer.parseInt( cbPeso2.getSelectedItem().toString()),Integer.parseInt( cbPeso3.getSelectedItem().toString()));
					mysql.AtualizarMedia(Integer.parseInt(idTurmaSelecionado),cbPeso1,cbPeso2,cbPeso3);
					
					//apaga tudo na tabela
					modeloTabela.setRowCount(0);
					mysql.BuscaTurma(modeloTabela, mysql.Entrar(tfUsuario.getText(),pfPalavraPasse.getText()));
					
					
					//limpar os campos 
					tfCadeira.setText(null);
					tfFaculdade.setText(null);
					cbPeriodo.setSelectedItem("");
					cbPeso1.setSelectedItem("0");
					cbPeso2.setSelectedItem("0");
					cbPeso3.setSelectedItem("0");
					
					lblMensagemErro.setVisible(false);
					lblMensagemPeso.setVisible(false);
					
				}
				else {
					lblMensagemErro.setVisible(true);
					lblMensagemPeso.setVisible(true);
				}
				
			}
		});
		btnAtualizar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnAtualizar.setForeground(Color.BLACK);
		btnAtualizar.setBackground(new Color(243, 190, 148));
		btnAtualizar.setBounds(201, 394, 78, 36);
		painelTurma.add(btnAtualizar);
		
		JButton btnVisualizar = new JButton("Visualizar");
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/////*******************************************************************************=================================================
				int linhaSelecionada = tabelaTurma.getSelectedRow();
				idTurma = Integer.parseInt(tabelaTurma.getModel().getValueAt(linhaSelecionada, 0).toString());
				////////////aqiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
				layeredPane.removeAll();
				layeredPane.add(painelAluno);
				layeredPane.repaint();
				layeredPane.revalidate();
				mysql.BuscarAluno(modeloTabelaAluno,idTurma );
			}
		});
		btnVisualizar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnVisualizar.setBackground(new Color(243, 190, 148));
		btnVisualizar.setForeground(Color.BLACK);
		btnVisualizar.setBounds(711, 581, 102, 36);
		painelTurma.add(btnVisualizar);
		
	    lblAvatar2 = new JLabel("USUARIO: ");
	    lblAvatar2.setHorizontalAlignment(SwingConstants.CENTER);
	    lblAvatar2.setVerticalAlignment(SwingConstants.TOP);
	    lblAvatar2.setForeground(Color.BLACK);
	    lblAvatar2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblAvatar2.setIcon(new ImageIcon("C:\\Users\\Dell\\Documents\\Projectos eclipse\\Dossie\\avatar##.png"));
		lblAvatar2.setBounds(153, 97, 114, 45);
		painelTurma.add(lblAvatar2);
		
		JLabel lblBgAmarelo = new JLabel("");
		lblBgAmarelo.setIcon(new ImageIcon("C:\\Users\\Dell\\Documents\\Projectos eclipse\\Dossie\\imageonline-co-roundcorner1.png"));
		lblBgAmarelo.setBounds(42, 76, 400, 495);
		painelTurma.add(lblBgAmarelo);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Dell\\Documents\\Projectos eclipse\\Dossie\\bg2.jpg"));
		lblNewLabel_1.setBounds(0, 0, 1084, 661);
		painelTurma.add(lblNewLabel_1);
		
		JLabel label = new JLabel("New label");
		label.setBounds(28, 12, 55, 16);
		painelTurma.add(label);
		
		painelAluno = new JPanel();
		painelAluno.setBackground(new Color(0, 21, 79));
		layeredPane.add(painelAluno, "name_14144707325200");
		painelAluno.setLayout(null);
		
		JButton btnCriarPDF = new JButton("Criar PDF");
		btnCriarPDF.setBackground(Color.GREEN);
		btnCriarPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageFormat cabecalho = new MessageFormat("UNIVERCIDADE EDUARDO MONDLANE");
				MessageFormat rodape = new MessageFormat("Pagina{0,number,integer}");
				
				try {
					tabelaAluno.print(JTable.PrintMode.NORMAL,cabecalho,rodape);	
					
				}catch(java.awt.print.PrinterException ex) {
					JOptionPane.showMessageDialog(painelAluno, ex.getMessage());
				}
				
			}
		});
		btnCriarPDF.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnCriarPDF.setBounds(717, 603, 122, 39);
		painelAluno.add(btnCriarPDF);
		
		lblNomeUsuario = new JLabel("New label");
		lblNomeUsuario.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNomeUsuario.setBounds(194, 105, 92, 16);
		painelAluno.add(lblNomeUsuario);
		
		JLabel lblAvatar4 = new JLabel("USUARIO:");
		lblAvatar4.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblAvatar4.setIcon(new ImageIcon("C:\\Users\\Dell\\Documents\\Projectos eclipse\\Dossie\\avatar##.png"));
		lblAvatar4.setBounds(91, 91, 122, 44);
		painelAluno.add(lblAvatar4);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfNome.setText(null);
				tfSobrenome.setText(null);
				tfCodigo.setText(null);
			    tfTeste1.setText(null);
				tfTeste2.setText(null);
				tfTeste3.setText(null);
			}
		});
		
		scrollPane_1.setBounds(430, 50, 629, 552);
		painelAluno.add(scrollPane_1);
		
		tabelaAluno = new JTable();
		tabelaAluno.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabelaAluno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linhaSelecionada = tabelaAluno.getSelectedRow();
				codigoAlunoSelecionado = tabelaAluno.getModel().getValueAt(linhaSelecionada, 3).toString();
				
				mysql.LinhaSelecionadaTabelaAluno(codigoAlunoSelecionado, tfNome, tfSobrenome, tfCodigo, tfTeste1, tfTeste2, tfTeste3);
			}
		});
		tabelaAluno.setBackground(Color.LIGHT_GRAY);
		scrollPane_1.setViewportView(tabelaAluno);
		Object [] ColunasTabelaAluno = {"Id","Nome","Apelido","Codigo","Teste 1","Teste 2","Teste 3","Media"};
		modeloTabelaAluno = new DefaultTableModel(ColunasTabelaAluno,0);
		
		tabelaAluno.setModel(modeloTabelaAluno);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnVoltar.setBackground(new Color(243, 190, 148));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				///////***********************************************************************
				modeloTabelaAluno.setRowCount(0);
				layeredPane.removeAll();
				layeredPane.add(painelTurma);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnVoltar.setBounds(10, 6, 97, 39);
		painelAluno.add(btnVoltar);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNome.setBounds(38, 160, 55, 16);
		painelAluno.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("SansSerif", Font.BOLD, 14));
		tfNome.setBounds(91, 154, 139, 28);
		painelAluno.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblSobrenome = new JLabel("Apelido");
		lblSobrenome.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblSobrenome.setForeground(Color.WHITE);
		lblSobrenome.setBounds(34, 210, 59, 16);
		painelAluno.add(lblSobrenome);
		
		tfSobrenome = new JTextField();
		tfSobrenome.setFont(new Font("SansSerif", Font.BOLD, 14));
		tfSobrenome.setBounds(91, 204, 139, 28);
		painelAluno.add(tfSobrenome);
		tfSobrenome.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblCodigo.setForeground(Color.WHITE);
		lblCodigo.setBounds(34, 264, 55, 16);
		painelAluno.add(lblCodigo);
		
		tfCodigo = new JTextField();
		tfCodigo.setFont(new Font("SansSerif", Font.BOLD, 14));
		tfCodigo.setBounds(91, 258, 139, 28);
		painelAluno.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		JLabel lblTeste1 = new JLabel("Teste 1");
		lblTeste1.setForeground(Color.WHITE);
		lblTeste1.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTeste1.setBounds(262, 160, 55, 16);
		painelAluno.add(lblTeste1);
		
		tfTeste1 = new JTextField();
		tfTeste1.setFont(new Font("SansSerif", Font.BOLD, 14));
		tfTeste1.setBounds(320, 154, 63, 28);
		painelAluno.add(tfTeste1);
		tfTeste1.setColumns(10);
		
		JLabel lblTeste2 = new JLabel("Teste 2");
		lblTeste2.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTeste2.setForeground(Color.WHITE);
		lblTeste2.setBounds(262, 210, 55, 16);
		painelAluno.add(lblTeste2);
		
		tfTeste2 = new JTextField();
		tfTeste2.setFont(new Font("SansSerif", Font.BOLD, 14));
		tfTeste2.setBounds(320, 204, 63, 28);
		painelAluno.add(tfTeste2);
		tfTeste2.setColumns(10);
		
		tfTeste3 = new JTextField();
		tfTeste3.setFont(new Font("SansSerif", Font.BOLD, 14));
		tfTeste3.setBounds(320, 258, 63, 28);
		painelAluno.add(tfTeste3);
		tfTeste3.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Teste 3");
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel_8.setBounds(262, 264, 55, 16);
		painelAluno.add(lblNewLabel_8);
		
		JButton btnAdicionarAluno = new JButton("Adicionar");
		btnAdicionarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(tfTeste1.getText().isBlank()) {
				tfTeste1.setText("0");
				}
			if(tfTeste2.getText().isBlank()) {
				tfTeste2.setText("0");
				}
			if(tfTeste3.getText().isBlank()) {
				tfTeste3.setText("0");
				}
				
				int somatorioNotas = Integer.parseInt(tfTeste1.getText().toString()) + Integer.parseInt(tfTeste2.getText().toString()) + Integer.parseInt(tfTeste3.getText().toString());
				
				if(somatorioNotas <= 60 && !tfNome.getText().isEmpty() && !tfSobrenome.getText().isEmpty() && !tfCodigo.getText().isEmpty()) {
					
						mysql.AdicionarAluno(idTurma, tfNome, tfSobrenome, tfCodigo, tfTeste1, tfTeste2, tfTeste3,painelAluno,cbPeso1,cbPeso2,cbPeso3);
						
					
				
					
					//apaga tudo na tabela
				modeloTabelaAluno.setRowCount(0);
					
				//limpar os campos 
				tfNome.setText(null);
				tfSobrenome.setText(null);
				tfCodigo.setText(null);
			    tfTeste1.setText(null);
				tfTeste2.setText(null);
				tfTeste3.setText(null);
			   
					
			   mysql.BuscarAluno(modeloTabelaAluno,idTurma);
					
				}
			}
		});
		btnAdicionarAluno.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnAdicionarAluno.setBackground(new Color(243, 190, 148));
		btnAdicionarAluno.setBounds(46, 370, 97, 39);
		painelAluno.add(btnAdicionarAluno);
		
		JButton btnApagarAluno = new JButton("Apagar");
		btnApagarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mysql.ApagarAluno(Integer.parseInt(codigoAlunoSelecionado));
				//System.out.print(codigoAlunoSelecionado);
				//apaga tudo na tabela
				modeloTabelaAluno.setRowCount(0);
				
				//limpar os campos 
				tfNome.setText(null);
				tfSobrenome.setText(null);
				tfCodigo.setText(null);
				tfTeste1.setText(null);
				tfTeste2.setText(null);
				tfTeste3.setText(null);
		
				
				mysql.BuscarAluno(modeloTabelaAluno,idTurma);
				/////////////7777777777777777777777777777777777777777777777777777
			}
		});
		btnApagarAluno.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnApagarAluno.setBackground(new Color(243, 190, 148));
		btnApagarAluno.setBounds(155, 370, 97, 39);
		painelAluno.add(btnApagarAluno);
		
		JButton btnAtualizarAluno = new JButton("Atualizar");
		btnAtualizarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tfNome.getText().isEmpty() && !tfSobrenome.getText().isEmpty() && !tfCodigo.getText().isEmpty()) {
					mysql.AtualizarAluno(tfNome.getText(),tfSobrenome.getText(),Integer.parseInt(tfCodigo.getText().toString()),Float.parseFloat(tfTeste1.getText().toString()),Float.parseFloat(tfTeste2.getText().toString()),Float.parseFloat(tfTeste3.getText().toString()),cbPeso1,cbPeso2,cbPeso3);
					
					modeloTabelaAluno.setRowCount(0);
					mysql.BuscarAluno(modeloTabelaAluno,idTurma );
					
					
				//limpar os campos 
				tfNome.setText(null);
				tfSobrenome.setText(null);
				tfCodigo.setText(null);
			    tfTeste1.setText(null);
				tfTeste2.setText(null);
				tfTeste3.setText(null);
					
					
				}
			}
		});
		btnAtualizarAluno.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnAtualizarAluno.setBackground(new Color(243, 190, 148));
		btnAtualizarAluno.setBounds(266, 370, 97, 39);
		painelAluno.add(btnAtualizarAluno);
		
		JLabel lblPapelParedeAlunos2 = new JLabel("");
		lblPapelParedeAlunos2.setIcon(new ImageIcon("C:\\Users\\Dell\\Documents\\Projectos eclipse\\Dossie\\imageonline-co-roundcorner1.png"));
		lblPapelParedeAlunos2.setBounds(10, 68, 487, 476);
		painelAluno.add(lblPapelParedeAlunos2);
		
		JLabel lblPapelParedeAlunos = new JLabel("");
		lblPapelParedeAlunos.setIcon(new ImageIcon("C:\\Users\\Dell\\Documents\\Projectos eclipse\\Dossie\\bg2.jpg"));
		lblPapelParedeAlunos.setBounds(0, 0, 1084, 661);
		painelAluno.add(lblPapelParedeAlunos);
	}
}

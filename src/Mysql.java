import java.sql.*;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
public class Mysql {
	private final String url = "jdbc:mysql://localhost:3306/dossieacademico";
	private final String palavraPasse = "abapai824185120";
	private final String usuario = "root";
	private final String QueryEntrada = "SELECT USUARIO_ID FROM USUARIO WHERE NOME_USUARIO = ? AND PALAVRA_PASSE = ? ; ";
	private final String QueryTurma = "SELECT * FROM TURMA WHERE USUARIO_ID = ?;";
	private final String QueryAluno = "SELECT * FROM ALUNO WHERE ID = ?;";
	private final String QueryLinhaSelecionada = "SELECT * FROM TURMA WHERE ID = ";
	private final String QueryLinhaSelecionadaTabelaAluno = "SELECT * FROM ALUNO WHERE CODIGO = ";
	private final String QueryApagaTurma = "DELETE FROM TURMA WHERE ID = ?;";
	private final String QueryApagaAluno = "DELETE FROM ALUNO WHERE CODIGO = ?;";
	private final String QueryCriarTurma = "INSERT INTO TURMA" + "(ID,USUARIO_ID, PERIODO, CADEIRA, FACULDADE, PESO_1, PESO_2,PESO_3) VALUES"+ "(NULL,?,?,?,?,?,?,?);";
	private final String QueryAdicionarAluno = "INSERT INTO ALUNO" + "(ID, NOME, SOBRENOME, CODIGO, TESTE_1, TESTE_2, TESTE_3, MEDIA) VALUES"+ "(?,?,?,?,?,?,?,?);";
	private final String QueryCriacaoUsuario = "INSERT INTO USUARIO VALUES ( NULL, ?, ?);";
	private final String QueryValidacaoUsuario = "SELECT USUARIO_ID FROM USUARIO WHERE NOME_USUARIO = ?;";
	private final String QueryAtualizarTurma = "UPDATE TURMA SET PERIODO = ?, CADEIRA = ?, FACULDADE = ?, PESO_1 = ?, PESO_2 = ?, PESO_3 = ? WHERE ID = ?;";
	private final String QueryAtualizarAluno = "UPDATE ALUNO SET NOME = ?, SOBRENOME = ?, CODIGO = ?, TESTE_1 = ?, TESTE_2 = ?, TESTE_3 = ?, MEDIA = ? WHERE CODIGO = ?;";
	private final String QueryApagaUsuario = "DELETE FROM USUARIO WHERE USUARIO_ID = ?;";
	private final String QueryAtualizarMedia = "UPDATE ALUNO SET MEDIA = (teste_1 * ?) + (teste_2 * ?) + (teste_3 * ?) WHERE ID = ?;";
	private final String QueryAtualizarCodigo = "UPDATE ALUNO SET CODIGO = ? WHERE NOME = ? AND SOBRENOME = ?;";
	
	//////////////////////////////////////////////////////////////////////////////
	public void AtualizarCodigo(int codigo,String nome, String sobrenome) {
		try(Connection conexao = DriverManager.getConnection(url,usuario,palavraPasse);
				
				PreparedStatement discurso = conexao.prepareStatement(QueryAtualizarCodigo);){
			    discurso.setInt(1,codigo);
			    discurso.setString(2,nome);
			    discurso.setString(3,sobrenome);
			   			   
			  discurso.executeUpdate();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
//////////////////////////////////////////////////////////////////////////////////
	public void AtualizarMedia(int idTurmaSelecionado,JComboBox peso1,JComboBox peso2,JComboBox peso3) {
		try(Connection conexao = DriverManager.getConnection(url,usuario,palavraPasse);
				PreparedStatement discurso = conexao.prepareStatement(QueryAtualizarMedia);){
			     discurso.setFloat(1, Float.parseFloat(peso1.getSelectedItem().toString())/100);
			     discurso.setFloat(2, Float.parseFloat(peso2.getSelectedItem().toString())/100);
			     discurso.setFloat(3, Float.parseFloat(peso3.getSelectedItem().toString())/100);
			     discurso.setInt(4, idTurmaSelecionado);
			     discurso.executeUpdate();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			
		}
		
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	public void ApagarUsuario(int id) {
		try(Connection conexao = DriverManager.getConnection(url,usuario,palavraPasse);
				PreparedStatement discurso = conexao.prepareStatement(QueryApagaUsuario);){
			     discurso.setInt(1, id);
			     discurso.executeUpdate();
			
		}catch(SQLException ex) {
			
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	public void AtualizarAluno(String nome, String sobrenome, int codigo, float teste_1, float teste_2, float teste_3,JComboBox peso1,JComboBox peso2,JComboBox peso3) {
		try(Connection conexao = DriverManager.getConnection(url,usuario,palavraPasse);
				
				PreparedStatement discurso = conexao.prepareStatement(QueryAtualizarAluno);){
			    discurso.setString(1,nome);
			    discurso.setString(2,sobrenome);
			    discurso.setFloat(3,codigo);
			    discurso.setFloat(4,teste_1);
			    discurso.setFloat(5,teste_2);
			    discurso.setFloat(6,teste_3);
			    discurso.setFloat(7,(teste_1 * (Float.parseFloat(peso1.getSelectedItem().toString())/100)) + (teste_2 * (Float.parseFloat(peso2.getSelectedItem().toString())/100)) + (teste_3 * (Float.parseFloat(peso3.getSelectedItem().toString())/100)));
			    discurso.setInt(8,codigo);
			  
			    
			 
			    discurso.executeUpdate();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
/////////////////////////////////////////////////////////////////
	public void AdicionarAluno(int idTurma,JTextField tfNome,JTextField tfSobrenome,JTextField tfCodigo, JTextField tfTeste1, JTextField tfTeste2, JTextField  tfTeste3,JPanel painelAluno,JComboBox peso1,JComboBox peso2,JComboBox peso3) {
		try(Connection conexao = DriverManager.getConnection(url,usuario,palavraPasse);
				PreparedStatement discurso = conexao.prepareStatement(QueryAdicionarAluno);){
			discurso.setInt(1,idTurma);
			discurso.setString(2,tfNome.getText());
			discurso.setString(3,tfSobrenome.getText());
			discurso.setInt(4, Integer.parseInt(tfCodigo.getText()));
			discurso.setFloat(5,Float.parseFloat(tfTeste1.getText()));
			discurso.setFloat(6,Float.parseFloat(tfTeste2.getText()));
			discurso.setFloat(7,Float.parseFloat(tfTeste3.getText()));
			float resultadoMedia = Float.parseFloat(tfTeste1.getText()) *( Float.parseFloat(peso1.getSelectedItem().toString())/100) + Float.parseFloat(tfTeste2.getText()) *( Float.parseFloat(peso2.getSelectedItem().toString())/100) + Float.parseFloat(tfTeste3.getText()) *( Float.parseFloat(peso3.getSelectedItem().toString())/100);
		
			discurso.setFloat(8,resultadoMedia);
			discurso.executeUpdate();
				
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(painelAluno,"Codigo de Aluno Indisponinel"); 
			
		}
		
	}
	
	
//////////////////////////////////////////////////////////////
	public void ApagarAluno(int codigoAlunoSelecionado) {
		try(Connection conexao = DriverManager.getConnection(url,usuario,palavraPasse);
				PreparedStatement discurso = conexao.prepareStatement(QueryApagaAluno);){
			discurso.setInt(1, codigoAlunoSelecionado);
			discurso.executeUpdate();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	//////////////////////////////////////////////////////////////////////
	public void LinhaSelecionadaTabelaAluno(String codigo ,JTextField tfNome,JTextField tfSobrenome,JTextField tfCodigo, JTextField tfTeste_1, JTextField tfTeste_2, JTextField  tfTeste_3) {
		try(Connection conexao = DriverManager.getConnection(url,usuario,palavraPasse);
				Statement discurso = conexao.createStatement()){
			
			ResultSet resultado = discurso.executeQuery(QueryLinhaSelecionadaTabelaAluno + codigo + ";");
			
			while(resultado.next()) {
				tfNome.setText(resultado.getString("Nome").toUpperCase());
				tfSobrenome.setText(resultado.getString("Sobrenome").toUpperCase());
				tfCodigo.setText(resultado.getString("Codigo").toUpperCase());
				tfTeste_1.setText(String.valueOf(resultado.getFloat("Teste_1")).toUpperCase());
				tfTeste_2.setText(String.valueOf(resultado.getFloat("Teste_2")).toUpperCase());
				tfTeste_3.setText(String.valueOf(resultado.getFloat("Teste_3")).toUpperCase());
				
			
				
			
				}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	 
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void BuscarAluno(DefaultTableModel modeloTabelaAluno, int ID) {
		try(Connection conexao = DriverManager.getConnection(url,usuario,palavraPasse);
				PreparedStatement discurso = conexao.prepareStatement(QueryAluno)){
			discurso.setInt(1,ID);
			
			ResultSet resultado = discurso.executeQuery();
			
		    while(resultado.next()) {
		    	int id = resultado.getInt("ID");
		    	String nome = resultado.getString("NOME").toUpperCase();
		    	String sobrenome = resultado.getString("SOBRENOME").toUpperCase();
		    	String codigo = resultado.getString("CODIGO").toUpperCase();
		    	float teste_1 = resultado.getFloat("TESTE_1");
		    	float teste_2 = resultado.getFloat("TESTE_2");
		    	float teste_3 = resultado.getFloat("TESTE_3");
		    	float media   = resultado.getFloat("MEDIA");
		    	Object [] dados = {id,nome,sobrenome,codigo,teste_1,teste_2,teste_3,media};
		    	modeloTabelaAluno.addRow(dados);
		    	
		    }
		}catch(SQLException ex) {
			ex.printStackTrace();
	}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	public void AtualizarTurma(int id, String periodo, String cadeira, String faculdade, int peso_1, int peso_2, int peso_3) {
		try(Connection conexao = DriverManager.getConnection(url,usuario,palavraPasse);
				
				PreparedStatement discurso = conexao.prepareStatement(QueryAtualizarTurma);){
			    discurso.setString(1,periodo);
			    discurso.setString(2,cadeira);
			    discurso.setString(3,faculdade);
			    discurso.setInt(4, peso_1);
			    discurso.setInt(5,peso_2);
			    discurso.setInt(6,peso_3);
			    discurso.setInt(7,id);
			    
			    
			    
			 
			    discurso.executeUpdate();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////

public String CriarUsuario(String nomeUsuario, String palavraPasse) {
	try(Connection conexao = DriverManager.getConnection(url,usuario,this.palavraPasse);
			PreparedStatement discurso = conexao.prepareStatement(QueryCriacaoUsuario);){
		discurso.setString(1, nomeUsuario);
		discurso.setString(2, palavraPasse);
		
		discurso.executeUpdate();
		
		
	}catch(SQLException ex) {
		ex.printStackTrace();

	
}
	return nomeUsuario + "-" + palavraPasse;
}
	
	
///////////////////////////////////////////////////////////////////////////////////
	public Integer ValidacaoUsuario(String nomeUsuario) {
		Integer usuario_id = 0;
		try(Connection conexao = DriverManager.getConnection(url,usuario,this.palavraPasse);
				PreparedStatement discurso = conexao.prepareStatement(QueryValidacaoUsuario);){
			
			discurso.setString(1, nomeUsuario);
			
			
			ResultSet resultado = discurso.executeQuery();
			
			while(resultado.next()) {
			 usuario_id = resultado.getInt("USUARIO_ID");
			 
				
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return usuario_id;
	}
	
	
	
	
	///////////////////////////////////////////////////////////////////
	public void CriarTurma(int usuario_id,JComboBox cbPeriodo,JTextField tfCadeira,JTextField tfFaculdade, JComboBox cbPeso1,JComboBox cbPeso2,JComboBox cbPeso3) {
		try(Connection conexao = DriverManager.getConnection(url,usuario,palavraPasse);
				PreparedStatement discurso = conexao.prepareStatement(QueryCriarTurma);){
			discurso.setInt(1,usuario_id);
			discurso.setString(2, cbPeriodo.getSelectedItem().toString());
			discurso.setString(3,tfCadeira.getText());
			discurso.setString(4, tfFaculdade.getText());
			discurso.setInt(5,Integer.parseInt(cbPeso1.getSelectedItem().toString()));
			discurso.setInt(6,Integer.parseInt(cbPeso2.getSelectedItem().toString()));
			discurso.setInt(7,Integer.parseInt(cbPeso3.getSelectedItem().toString()));
			
			discurso.executeUpdate();
				
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	//////////////////////////////////////////////
	public void LinhaSelecionada(String idTurmaSelecionado ,JTextField tfCadeira,JTextField tfFaculdade, JComboBox cbPeriodo,JComboBox cbPeso1,JComboBox cbPeso2,JComboBox cbPeso3) {
		try(Connection conexao = DriverManager.getConnection(url,usuario,palavraPasse);
				Statement discurso = conexao.createStatement()){
			
			ResultSet resultado = discurso.executeQuery(QueryLinhaSelecionada + idTurmaSelecionado + ";");
			
			while(resultado.next()) {
				tfCadeira.setText(resultado.getString("Cadeira").toUpperCase());
				tfFaculdade.setText(resultado.getString("Faculdade").toUpperCase());
				cbPeriodo.setSelectedItem(resultado.getString("Periodo"));
				cbPeso1.setSelectedItem(resultado.getString("Peso_1"));
				cbPeso2.setSelectedItem(resultado.getString("Peso_2"));
				cbPeso3.setSelectedItem(resultado.getString("Peso_3"));
				}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	//////////////////////////////////////////////////////
	public void ApagarTurma(int idTurmaSelecionada) {
		try(Connection conexao = DriverManager.getConnection(url,usuario,palavraPasse);
				PreparedStatement discurso = conexao.prepareStatement(QueryApagaTurma);){
			discurso.setInt(1, idTurmaSelecionada);
			discurso.executeUpdate();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////
	public int Entrar(String nomeUsuario, String palavraPasse) {
		int usuario_id = 0;
		try(Connection conexao = DriverManager.getConnection(url,usuario,this.palavraPasse);
				PreparedStatement discurso = conexao.prepareStatement(QueryEntrada);){
			
			discurso.setString(1, nomeUsuario);
			discurso.setString(2, palavraPasse);
			
			ResultSet resultado = discurso.executeQuery();
			
			while(resultado.next()) {
			 usuario_id = resultado.getInt("USUARIO_ID");
			 
				
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return usuario_id;
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	
	
	//////////////////////////////////////////////////////////
	
	public void BuscaTurma(DefaultTableModel modeloTabela, int usuario_id) {
		try(Connection conexao = DriverManager.getConnection(url,usuario,palavraPasse);
				PreparedStatement discurso = conexao.prepareStatement(QueryTurma)){
			discurso.setInt(1,usuario_id);
			
			ResultSet resultado = discurso.executeQuery();
			
		    while(resultado.next()) {
		    	int id = resultado.getInt("ID");
		    	String cadeira = resultado.getString("CADEIRA").toUpperCase();
		    	String periodo = resultado.getString("PERIODO").toUpperCase();
		    	String faculdade = resultado.getString("FACULDADE").toUpperCase();
		    	int peso_1 = resultado.getInt("PESO_1");
		    	int peso_2 = resultado.getInt("PESO_2");
		    	int peso_3 = resultado.getInt("PESO_3");
		    	
		    	Object [] dados = {id,cadeira,periodo,faculdade,peso_1,peso_2,peso_3};
		    	modeloTabela.addRow(dados);
		    	
		    }
		}catch(SQLException ex) {
			ex.printStackTrace();
	}
	}
	
	////////////////////////////////////////////////

		

}

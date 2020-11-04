package br.com.olist.store;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class NewSearch extends JFrame {

	
	private static final long serialVersionUID = 1L;

	    JLabel jlName = new JLabel ("Nome");
	    JTextField jtfName = new JTextField(35);
	    JLabel jlDescription = new JLabel ("Descrição");
	    JTextField jtfDescription = new JTextField(35);
	    JLabel jlValue = new JLabel ("Valor");
	    JTextField jtfValue = new JTextField(35);
	    JButton jbSearch = new JButton("Pesquisar");	    
	    DefaultTableModel tmAnswer = new DefaultTableModel (null, new String[]{"Produto", "Descrição", "Valor", "Categoria"});
	    JTable jtAnswer = new JTable(tmAnswer);
	    JScrollPane jspAnswer= new JScrollPane(jtAnswer);
	    JLabel jlCategory = new JLabel ("Categorias");  
		JComboBox<String> jcbCategory = new JComboBox<String>();
		
	
    
  
    
    //Construtor
    public NewSearch() {    
        
        //Define as porpriedades da janela
        setTitle("Pesquisa de Produtos");
        setSize(500,410);
        setLocation(100,100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setVisible(true);
        
        //Define o tamanho dos componentes
        jlName.setPreferredSize(new Dimension(75,20));
        jlDescription.setPreferredSize(new Dimension(75,20));
        jlValue.setPreferredSize(new Dimension(75,20));        
        jspAnswer.setPreferredSize(new Dimension(460,100));
        jlCategory.setPreferredSize(new Dimension(80,20));
        jcbCategory.setPreferredSize(new Dimension(325,20));
 
        
    
       
        
        //Adiciona os componentes à janela
       
        add(jlName);
        add(jtfName);
        add(jlDescription);
        add(jtfDescription);
        add(jlValue);
        add(jtfValue);
        add(jlCategory);
        add(jcbCategory);
        add(jbSearch);
        add(jtAnswer); 
        add(jspAnswer);
        
        String sqlTypeProduct = "SELECT * FROM category";
        try {
        	ConnectBD connectBD = new ConnectBD();
            Connection con = connectBD.getConnect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlTypeProduct);
            while (rs.next())
            jcbCategory.addItem(rs.getString("name_category"));
            connectBD.closeConnection(con);    
        }
        catch(SQLException sqle) {
            System.out.println("Não foi possível efetuar a operação sobre a BD!");
            sqle.printStackTrace();
        }
                  
       
   
        
        /*Registo do listener ActionListener e do listener MouseListener
        junto dos botões. Quando for gerado um evento por estes componentes, é
        criada uma instância da classe EventoJBGPesquisar ou EventoJTPesquisar,
        onde está o código que deve ser executado quando tal acontece*/
        jbSearch.addActionListener(new EventoJBSearch());
        
        
        //Limpa os dados que possam ter ficado desde a última utilização da janela
        //Os métodos invocados estão implementados em baixo
       
        clearTableRows();
    }
    
    
    
    public static void main(String[] args) {
        new NewSearch();
    }
    
    //Classe interna que contém o código que é executado quando se pressiona o botão jbPesquisar
    private class EventoJBSearch implements ActionListener {
        
        public void actionPerformed(ActionEvent ev) {
            
            
        	 clearTableRows();
        	  
            boolean findProduct = false;
            
            if (jtfName.getText().equals("") && jtfDescription.getText().equals("") && jtfValue.getText().equals("")) 
                JOptionPane.showMessageDialog(null, "Preencha um ou mais campos para realiza a pesquisa!");
            else {
            	  try {
                      PreparedStatement pstmt;
                      ResultSet rs;
                      String sqlSearch1 = "SELECT * FROM product WHERE name_product LIKE ? "
                      		+ "OR SELECT * FOR product WHERE description_product LIKE ? "
                      		+ "OR SELECT * FOR product WHERE value_product LIKE ?";                   
                      ConnectBD connectBD = new ConnectBD();
                      Connection con = connectBD.getConnect();
                      pstmt = con.prepareStatement(sqlSearch1);
                      pstmt.setString(1, '%' + jtfName.getText() + '%');
                      rs = pstmt.executeQuery();
                      int i=0;
                      String[] campos = new String[] {null, null, null, null};
                      while (rs.next()) {
                    	  findProduct = true;
                    	  tmAnswer.addRow(campos);
                    	  tmAnswer.setValueAt(rs.getString("name_product"), i, 0);
                    	  tmAnswer.setValueAt(rs.getString("description_product"), i, 1);
                    	  tmAnswer.setValueAt(rs.getInt("value_product"), i, 2);
                    	 
                    	  
                          i++;
                      }
                      if (findProduct  == false) {
                          JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum cliente com esse nome!");
                          cleanField(); 
                      }
                      connectBD.closeConnection(con);
                 
                }
                catch(SQLException sqle) {
                    System.out.println("Não foi possível efetuar a operação sobre a BD!");
                    sqle.printStackTrace();
                }
            }
        }
    }
    
 
    
   
        
        public void mousePressed(MouseEvent ev) {}
        
        public void mouseReleased(MouseEvent ev) {}
        
        public void mouseEntered(MouseEvent ev) {}
        
        public void mouseExited(MouseEvent ev) {}
    
    
    private void cleanField() {
        jtfName.setText("");
        jtfDescription.setText("");
        jtfValue.setText("");       
    }
    
    private void clearTableRows() {
        while (tmAnswer.getRowCount() > 0)
        	tmAnswer.removeRow(0);
    }
}




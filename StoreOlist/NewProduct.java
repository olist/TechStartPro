package br.com.olist.store;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;





public class NewProduct extends JFrame  {
	
	private static final long serialVersionUID = 1L;
	//Declara e cria os componentes
    JPanel jpPanel1 = new JPanel();
    JLabel jlName = new JLabel ("Nome");
    JTextField jtfName = new JTextField(40);
    JLabel jlDescription = new JLabel ("Descrição");
    JTextField jtfDescription = new JTextField(40);
    JLabel jlValue = new JLabel ("Valor");
    JTextField jtfValue = new JTextField(40);
    JLabel jlCategory = new JLabel ("Categorias");  
	JComboBox<String> jcbCategory = new JComboBox<String>();
    JButton jbAdd = new JButton("Adicionar");
    DefaultTableModel tmCategory = new DefaultTableModel (null, new String[]{"Código", "Categoria"});
    JTable jtCategory = new JTable(tmCategory);
    JScrollPane jspCategory = new JScrollPane(jtCategory);
	JButton jbSave = new JButton("Salvar");
    JButton jbClean= new JButton("Limpar");
    JLabel jlEmpty1 = new JLabel("");
    JLabel jlEmpty2 = new JLabel("");

 
    
    //Construtor

	public NewProduct() {    
        
        //Define as porpriedades da janela
        setTitle("Novo Produto");
        setSize(610,350);
        setLocation(100,100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setVisible(true);
        
        //Define o tamanho dos rótulos
        jlName.setPreferredSize(new Dimension(75,20));
        jlDescription.setPreferredSize(new Dimension(75,20));
        jlValue.setPreferredSize(new Dimension(75,20));
        jlCategory.setPreferredSize(new Dimension(80,20));
        jlEmpty1.setPreferredSize(new Dimension(600,20));
        jlEmpty2.setPreferredSize(new Dimension(600,10));
        
        jcbCategory.setPreferredSize(new Dimension(325,20));
        jbAdd.setPreferredSize(new Dimension(110,20));
        jspCategory.setPreferredSize(new Dimension(500,100));
 
        
     
        jpPanel1.setPreferredSize(new Dimension(600,340));
       
        
        
        jpPanel1.add(jlEmpty1);
        jpPanel1.add(jlName);
        jpPanel1.add(jtfName);
        jpPanel1.add(jlDescription);
        jpPanel1.add(jtfDescription);
        jpPanel1.add(jlValue);
        jpPanel1.add(jtfValue);
        jpPanel1.add(jlCategory);
        jpPanel1.add(jcbCategory);
        jpPanel1.add(jlCategory);
        jpPanel1.add(jbAdd);       
        jpPanel1.add(jspCategory);       
        jpPanel1.add(jlEmpty2);
        jpPanel1.add(jbSave);
        jpPanel1.add(jbClean);
        
        
        add(jpPanel1);       
   
    
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
	
        
  
        jbSave.addActionListener(new EventJBSave());
        jbAdd.addActionListener(new EventJBAdd());
        jbClean.addActionListener(new EventJBClear());
       
	}
        
    
    public static void main(String[] args) {
        new NewProduct();
    }
    
  //Classe interna que contém o código que é executado quando se pressiona o botão jbAdicionar
    private class EventJBAdd implements ActionListener {
        
        public void actionPerformed(ActionEvent ev) {

            String sqlPesquisaCategory = "SELECT id_category FROM category WHERE name_category LIKE ?";
            try {
                PreparedStatement pstmt;
                ConnectBD connectBD = new ConnectBD();
                Connection con = connectBD.getConnect();
         
                pstmt = con.prepareStatement(sqlPesquisaCategory);
                pstmt.setString(1, String.valueOf(jcbCategory.getSelectedItem()));
                ResultSet rs = pstmt.executeQuery();
                String[] campos = new String[] {null, null};
                if (rs.next()) {
                    tmCategory.addRow(campos);
                    tmCategory.setValueAt(jcbCategory.getSelectedItem(),tmCategory.getRowCount()-1,1);
                    tmCategory.setValueAt(rs.getInt("id_category"),tmCategory.getRowCount()-1,0);
                  
                }
                connectBD.closeConnection(con);
            }
            catch(SQLException sqle) {
                System.out.println("Não foi possível efetuar a operação sobre a BD!");
                sqle.printStackTrace();
            }
        }
    }
    
    
    //Classe interna que contém o código que é executado quando se pressiona o botão jbSave
    private class EventJBSave implements ActionListener {
    	
        public void actionPerformed(ActionEvent ev) {

            if (jtfName.getText().equals("") || jtfDescription.getText().equals("") || jtfValue.getText().equals("")) 
                JOptionPane.showMessageDialog(null,"Todos os campos são de preenchimento obrigatório!");
            else {
                try {
                	PreparedStatement pstmt1, pstmt2;             
                    ConnectBD connectBD = new ConnectBD();
                    Connection con = connectBD.getConnect();
                    String sqlNewProduct = "INSERT INTO product VALUES(null,?,?,?)";
                    pstmt1 = con.prepareStatement(sqlNewProduct);                   
                    pstmt1.setString(1, jtfName.getText());
                    pstmt1.setString(2, jtfDescription.getText());
                    pstmt1.setBigDecimal(3, new BigDecimal(jtfValue.getText()));
                    pstmt1.executeUpdate(); 
                    
                    String sqlID_product= "SELECT id_product AS codP FROM product WHERE id_product = (last_insert_id())";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sqlID_product);
                    int cod_prod = 0;
                    while (rs.next())
                        cod_prod = rs.getInt("codP");                      
                    
                    
                    String sqlSelctCategory = "INSERT INTO category_product VALUES(null,?,?)";
                    pstmt2 = con.prepareStatement(sqlSelctCategory);                 
                    
                    for (int i = 0; i < tmCategory.getRowCount(); i++) {
                    	pstmt2.setInt(1,Integer.parseInt(String.valueOf(tmCategory.getValueAt(i,0))));
                      	pstmt2.setInt(2, cod_prod);             
                        pstmt2.executeUpdate();
                    }
                
                    
                    connectBD.closeConnection(con); 
                    JOptionPane.showMessageDialog(null,"Os dados foram salvos com sucesso!");
                   
                    cleanField();
                    clearTableRows();
                }
                catch(SQLException sqle) {
                    System.out.println("Não foi possível efetuar a operação sobre a BD!");
                    sqle.printStackTrace();
                }
            }
        }
    }
    
    //Classe interna que contém o código que é executado quando se pressiona o botão jbLimpar
    private class EventJBClear implements ActionListener {
        
        public void actionPerformed(ActionEvent ev) {
        	cleanField();
        	clearTableRows() ;
        }
    }
   
    
    private void cleanField() {
        jtfName.setText("");
        jtfDescription.setText("");
        jtfValue.setText("");       
    }
    
    private void clearTableRows() {
        while (tmCategory.getRowCount() > 0)
            tmCategory.removeRow(0);
    }
}




    

package br.com.olist.store;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MainScreen extends JFrame {	

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		JMenuBar barMenu = new JMenuBar();
	    JMenu product = new JMenu("Inserir/Alterar");
	    JMenu search = new JMenu ("Pesquisar");
	    JMenuItem prod = new JMenuItem("Criar novo produto");
	    JMenuItem resarch= new JMenuItem("Ler dados do produto");
	    JMenuItem upgread = new JMenuItem("Atualizar dados do produto");
	    JMenuItem del = new JMenuItem("Excluir os dados do produto");
	    JMenu exit = new JMenu("Sair");
	   
        NewProduct newProduct;
//	    
	            
	    public MainScreen() {
	        setTitle("Store Products & Categories");
	        setSize(550,590);
	        setLocation(50,50);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setVisible(true);
	        setLayout(new FlowLayout());
	        setJMenuBar(barMenu);
	        product.add(prod);
	        
	        product.add(upgread);
	        product.add(del);
	        search.add(resarch);
	        barMenu.add(product);
	        barMenu.add(search);
	        
	    	barMenu.add(exit);
              
	        	      
	        
	        prod.addActionListener(new EventoJMenuItem());
	        resarch.addActionListener(new EventoJMenuItem());
	        upgread.addActionListener(new EventoJMenuItem());
	        del.addActionListener(new EventoJMenuItem());
	        
	        exit.addMouseListener(new EventoJMenuSair());
	    }
	    
	    public static void main(String[] args) {
	    	new MainScreen();
	    }
	    
	    private class EventoJMenuItem implements ActionListener {
	    
	        public void actionPerformed(ActionEvent ev) {
	            if (ev.getSource() == prod) {
                    newProduct = new NewProduct();
  	                newProduct.setVisible(true);
	            }
	            else if (ev.getSource() == resarch) {
//	                 novoCliente = new NovoCliente();
//	                 novoCliente.setVisible(true);
	            }
	            else if (ev.getSource() == upgread) {
//	                 novoAluguer = new NovoAluguer();
//	                 novoAluguer.setVisible(true);
	            }
	            else if (ev.getSource() == del) {
//	                 novoTipoCarro = new NovoTipoCarro();
//	                 novoTipoCarro.setVisible(true);
	            }
	          
	        }
	    }
	    
	    private class EventoJMenuSair implements MouseListener {
	    
	        public void mouseClicked(MouseEvent ev) {
	            System.exit(0);
	        }
	        
	        public void mouseEntered (MouseEvent ev) {}
	        
	        public void mouseExited(MouseEvent ev) {}
	        
	        public void mouseReleased(MouseEvent ev) {}
	        
	        public void mousePressed(MouseEvent ev) {}
	    }
	}



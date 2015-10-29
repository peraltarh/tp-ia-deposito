package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ControladorFabrica;
import modelo.Pedido;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ListarPedidos extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<Pedido> listModelPedidos;
	private JList<Pedido> listPedidos;
	private JPanel botones;
	private JButton btnActualizar;
	private JButton btnCompletar;
	private JScrollPane scrollPanePedidos;
	private JPanel listadoPedidos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarPedidos frame = new ListarPedidos();
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
	public ListarPedidos() {
		setTitle("Concretar Pedidos");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		listadoPedidos = new JPanel();
		GridBagConstraints gbc_listadoPedidos = new GridBagConstraints();
		gbc_listadoPedidos.insets = new Insets(0, 0, 5, 0);
		gbc_listadoPedidos.fill = GridBagConstraints.BOTH;
		gbc_listadoPedidos.gridx = 0;
		gbc_listadoPedidos.gridy = 0;
		contentPane.add(listadoPedidos, gbc_listadoPedidos);
		GridBagLayout gbl_listadoPedidos = new GridBagLayout();
		gbl_listadoPedidos.columnWidths = new int[]{202, 0};
		gbl_listadoPedidos.rowHeights = new int[]{2, 0};
		gbl_listadoPedidos.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_listadoPedidos.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		listadoPedidos.setLayout(gbl_listadoPedidos);
		
		scrollPanePedidos = new JScrollPane();
		scrollPanePedidos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPanePedidos = new GridBagConstraints();
		gbc_scrollPanePedidos.fill = GridBagConstraints.BOTH;
		gbc_scrollPanePedidos.gridx = 0;
		gbc_scrollPanePedidos.gridy = 0;
		listadoPedidos.add(scrollPanePedidos, gbc_scrollPanePedidos);
		
		listModelPedidos = new DefaultListModel<Pedido>();
		listPedidos = new JList<Pedido>(listModelPedidos);
		scrollPanePedidos.setViewportView(listPedidos);
		
		botones = new JPanel();
		GridBagConstraints gbc_botones = new GridBagConstraints();
		gbc_botones.fill = GridBagConstraints.VERTICAL;
		gbc_botones.gridx = 0;
		gbc_botones.gridy = 1;
		contentPane.add(botones, gbc_botones);
		GridBagLayout gbl_botones = new GridBagLayout();
		gbl_botones.columnWidths = new int[]{0, 0, 0};
		gbl_botones.rowHeights = new int[]{0, 0};
		gbl_botones.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_botones.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		botones.setLayout(gbl_botones);
		
		btnActualizar = new JButton("Actualizar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		botones.add(btnActualizar, gbc_btnNewButton);
		btnActualizar.addActionListener(this);
		
		btnCompletar = new JButton("Completar");
		GridBagConstraints gbc_btnCompletar = new GridBagConstraints();
		gbc_btnCompletar.gridx = 1;
		gbc_btnCompletar.gridy = 0;
		botones.add(btnCompletar, gbc_btnCompletar);
		btnCompletar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO
		if (e.getSource() == this.btnActualizar){
			this.listModelPedidos.clear();
			ControladorFabrica cf = new ControladorFabrica();
//			List<Pedido> pedidos = cf.buscarPedidos();
//			for (Pedido pedido : pedidos) {
//				this.listModelPedidos.addElement(pedido);
//			}
		}
		if (e.getSource() == this.btnCompletar){
			//TODO
		}
		
	}

}

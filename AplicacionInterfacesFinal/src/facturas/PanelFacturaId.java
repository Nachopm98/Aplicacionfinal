package facturas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 * 
 * @author usertar
 *
 */
public class PanelFacturaId extends JPanel {

	/**
	 * Create the panel.
	 */
	private String path;
	private Connection con;
	private JTextField textField;
	
	/**
	 * creador factura por id
	 */
	public PanelFacturaId() {
		setLayout(null);
		
		JButton btnGenerar = new JButton("GENERAR!");
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					generarInforme();
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnGenerar.setBounds(300, 147, 117, 25);
		add(btnGenerar);
		
		textField = new JTextField();
		textField.setBounds(120, 150, 137, 22);
		add(textField);
		textField.setColumns(10);
	
	}

	
	/**
	 * GENERA FACTURA
	 * @throws JRException
	 */
	protected void generarInforme() throws JRException {

		JFileChooser jchoChoser = new JFileChooser();
		if (jchoChoser.showSaveDialog(this) == jchoChoser.APPROVE_OPTION) {
			path = jchoChoser.getSelectedFile().getAbsolutePath();
			con = Conexion.getConnection();
			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("IDguay",textField.getText().toString());
		JasperPrint print = JasperFillManager.fillReport("jasper/informeObligatorio.jasper", parametro,con);
		JasperViewer.viewReport(print);
		JasperExportManager.exportReportToPdfFile(print,path);
		}
		
	}
}

package br.edu.psd.batalhanaval.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.psd.batalhanaval.model.socket.Cliente;

public class TableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	
	private List<Cliente> usuarios;
	
	public TableModel() {
		this.usuarios = new ArrayList<>();
	}
	
	public void addAll(List<Cliente> list)
	{
		if(list != null)
		{
			usuarios.clear();
			usuarios.addAll(list);
			fireTableDataChanged();
		}
	}
	
	public boolean addValor(Cliente usuarioPanel)
	{
		boolean b = false;
		if(usuarioPanel != null)
		{
			b = usuarios.add(usuarioPanel);
			fireTableDataChanged();
		}
		return b;
	}
	
	public List<Cliente> getUsuarios() {
		return usuarios;
	}

	public Cliente getValorAt(int linha)
	{
		return usuarios.get(linha);
	}
	
	@Override
	public String getColumnName(int column) {return "";}
	@Override
	public int getColumnCount() {return 1;}
	@Override
	public int getRowCount() {return usuarios.size();}

	@Override
	public Object getValueAt(int linha, int coluna) {

		Cliente usuario = usuarios.get(linha);
		
		return "<html>" + 
					"<h4>" + 
						usuario.getNome() + 
					"</h4>" + 
					"<div>" +
					usuario.getStatus() + 
					"</div>" +
//					"<div>" +
//						//Inserir aqui outra coisa
//					"</div>" +
			   "</html>";
	}

}

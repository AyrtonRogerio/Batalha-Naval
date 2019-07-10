package br.edu.psd.batalhanaval.model.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;



public class ClienteServer {
	private String nome;
	private Socket socket;

	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public ClienteServer(Socket socket) throws IOException, ClassNotFoundException  {
		this.socket = socket;
		InputStream is = this.socket.getInputStream();
		this.ois = new ObjectInputStream(is);
		OutputStream os = this.socket.getOutputStream();
		this.oos = new ObjectOutputStream(os);
	//	this.nome = nome;
	    //Object object = this.ois.readObject();
	}
	public ClienteServer(String nome) {
		super();
		this.nome = nome;
	}


	public String getNome() {
		return nome;
	}

	public Socket getSocket() {
		return socket;
	}
	
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}


	public ObjectOutputStream getOos() {
		return oos;
	}

	public void setOos(ObjectOutputStream oos) {
		this.oos = oos;
	}

	public ObjectInputStream getOis() {
		return ois;
	}

	public void setOis(ObjectInputStream ois) {
		this.ois = ois;
	}


}

package br.com.puc.tcc.util;

import java.security.MessageDigest;

public class Retorno {

	private Integer codigoRetorno;
	private String mensagem;
	
	public Retorno(Integer codigoRetorno, String mensagem) {
		super();
		this.codigoRetorno = codigoRetorno;
		this.mensagem = mensagem;
	}

	public Integer getCodigoRetorno() {
		return codigoRetorno;
	}

	public void setCodigoRetorno(Integer codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public static String getEspecialidade(Integer codigo) {
		switch (codigo) {
		case 1:
			return "Ortopedista";
		case 2:
			return "Cardiologista";
		case 3:
			return "Dermatologista";
		case 4:
			return "Oncologista";
		case 5:
			return "Cirurgia Plástica";
		case 6:
			return "Hematologista";
		default:
			break;
		}
		
		return null;
	}
	
	 public static String getSenhaBase64(String toEncode) {
	    	try {
	    		MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
	    		md.update(toEncode.getBytes("UTF-8"));
	    		byte[] passwordDigest = md.digest();
	    		String encodedHash = java.util.Base64.getEncoder().encodeToString(passwordDigest);
	    		return encodedHash;
	    	} catch (Exception e) {
	    		return null;
	    	}
	    }
}

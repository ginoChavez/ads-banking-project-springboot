package banking.transfers.infrastructure.email;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MensajeEmail {
	
	
	public String tituloDeMensaje;
	
	private String cuerpoDelMensaje;
	
	private String emailDeDestino;
	
	public static MensajeEmail mensajeDeTransferencia(
			String emailDeDestino, 
			String numeroCuentaOrigen, 
			String numeroCuentaDestino, 
			BigDecimal monto, 
			String nombrePersona, 
			Date fechaDeTransaccion) {
			
		    String fechaDeTransaccionFormateada = "";
		 	SimpleDateFormat mdyFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		 	fechaDeTransaccionFormateada = mdyFormat.format(fechaDeTransaccion);
		 	
		 	
			MensajeEmail mensaje = new MensajeEmail();
			mensaje.setTituloDeMensaje("ImaginaMedicaCredito: Constancia de Transferencia");
			mensaje.setEmailDeDestino(emailDeDestino);
			mensaje.setCuerpoDelMensaje(
					"<p>Hola "+nombrePersona+", </p>"+
							""+
							"<p>A continuación te enviamos el detalle de tu operación</p>"+
							""+
							"<hr />"+
							"<h1>Confirmación de Constancia de Transferencia</h1>"+
							""+
							"<div style=\"background:#eeeeee;border:1px solid #cccccc;padding:5px 10px;\">Fecha: "+ fechaDeTransaccionFormateada+"</div>"+
							""+
							"<div style=\"background:#eeeeee;border:1px solid #cccccc;padding:5px 10px;\">Cuenta a Cargo: "+numeroCuentaOrigen+"</div>"+
							""+
							"<div style=\"background:#eeeeee;border:1px solid #cccccc;padding:5px 10px;\">Cuenta Destino: "+numeroCuentaDestino+"</div>"+
							""+
							"<div style=\"background:#eeeeee;border:1px solid #cccccc;padding:5px 10px;\">Moneda y Monto:  S/. "+monto+"</div>");
								

			
			
		
		
		return mensaje;
		
	}

	
	public String getTituloDeMensaje() {
		return tituloDeMensaje;
	}
	
	public void setTituloDeMensaje(String tituloDeMensaje) {
		this.tituloDeMensaje = tituloDeMensaje;
	}
	
	public String getCuerpoDelMensaje() {
		return cuerpoDelMensaje;
	}

	public void setCuerpoDelMensaje(String cuerpoDelMensaje) {
		this.cuerpoDelMensaje = cuerpoDelMensaje;
	}

	public String getEmailDeDestino() {
		return emailDeDestino;
	}

	public void setEmailDeDestino(String emailDeDestino) {
		this.emailDeDestino = emailDeDestino;
	}
}

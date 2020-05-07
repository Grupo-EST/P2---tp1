package gestsaude.recurso;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;



/** Representa uma Consulta 
 */
public class Consulta {
	
	private Utente utente;
	private Senha senha;
	private Servico servico;
	
	private LocalDateTime dateTime;
	private boolean validada;

	public Consulta(LocalDateTime dateTime, Servico servico, Utente utente){
		setDateTime(dateTime);
		setServico(servico);
		setUtente(utente);
	}
	
	public void podeAdicionar() {
		servico.addConsultaMarcada(this);
		utente.addConsulta(this);
	}
	
	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Senha getSenha() {
		return senha;
	}

	public void setSenha(Senha senha) {
		this.senha = senha;
		servico.addSenha(senha);
		validada = true;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	
	public void setDateTime(LocalDateTime dataHora) {
		this.dateTime = dataHora;
	}

	public LocalDate getData() {
		return dateTime.toLocalDate();
	}
	
	public LocalTime getHora() {
		return dateTime.toLocalTime();
	}
	
	public boolean getValidada() {
		return validada;
	}
	
	@Override
	public String toString() {
		return "Consulta no dia " + getData().format( DateTimeFormatter.ofPattern("dd/MM/yyyy") ) + " às " + getHora() ;
	}

}

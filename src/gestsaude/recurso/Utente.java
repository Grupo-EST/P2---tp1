package gestsaude.recurso;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gestsaude.util.Consultas;
import gestsaude.util.RelogioSimulado;

/** Representa um Utente
 *
 */
public class Utente {
	
	private String numSns;
	private String nome;
	private ArrayList<Consulta> consultas = new ArrayList<Consulta>();
	
	public Utente(String numSns, String nome) {
		setNumSns(numSns);
		setNome(nome);
	}
	
	public String getNumSns() {
		return numSns;
	}

	public void setNumSns(String numSns) {
		this.numSns = numSns;
	}

	public String getNome() {
		return nome;
	}
 
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void addConsulta(Consulta cs) {
		Consultas.addConsultaOrdemData(consultas, cs);
	}
	
	public void removeConsulta(Consulta cs) {
		consultas.remove(cs);
	}
	
	public List<Consulta> getConsultas(){
		return Collections.unmodifiableList(consultas);		
	}
	
	// Verifica se tem consulta no dia
	public boolean consultaNoDia(List<Consulta> cs, LocalDate data) {
		if(Consultas.getConsultasDoDia(cs, data).isEmpty()) 
			return false;
		else
			return true;
	}
	
	// Depois de verificar que tem consulta no dia, 
	// verifica se tem consulta nas proximas 3h ou nas anteriores
	public boolean consultaNoMomento(List<Consulta> cs, LocalDateTime hConsulta) {
		if(Consultas.getConsultaEntreDatas(cs, hConsulta.minusHours(3), hConsulta.plusHours(3)).isEmpty()) 
			return false;
		else
			return true;
	}
	
	public Consulta getConsultaDoMomento(){	
		for(Consulta cs : consultas) {
			if(consultaNoDia(consultas, RelogioSimulado.getTempoAtual().toLocalDate())) 
				if(consultaNoMomento(consultas, cs.getDateTime())) 
					return cs;
		}
		return null;
	}

	// Utentes diferentes não podem ter o mesmo nº SNS
	@Override
	public String toString() {
		return nome + "(" + numSns + "): consultas=" + consultas + "...";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numSns == null) ? 0 : numSns.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		if (numSns == null) {
			if (other.numSns != null)
				return false;
		} else if (!numSns.equals(other.numSns))
			return false;
		return true;
	}
		
}

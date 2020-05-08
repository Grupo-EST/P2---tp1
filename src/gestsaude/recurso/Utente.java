package gestsaude.recurso;


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
		this.numSns = numSns;
		this.nome = nome;
	}
	
	public String getNumSns() {
		return numSns;
	}

	public String getNome() {
		return nome;
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
	public boolean consultaNoDia() {
		return !Consultas.getConsultasDoDia(consultas, RelogioSimulado.getTempoAtual().toLocalDate()).isEmpty();
	}
		
	// Verifica se tem consulta no momento (nas proximas 3h ou nas anteriores)
	public boolean consultaNoMomento() {
		return !Consultas.getConsultaEntreDatas(consultas, RelogioSimulado.getTempoAtual().minusHours(3), RelogioSimulado.getTempoAtual().plusHours(3)).isEmpty(); 
	}
	
	// Retorna consulta do momento se houver
	public Consulta getConsultaDoMomento(List<Consulta> cs, LocalDateTime data){	
		for(int i = 0;i<cs.size();i++) {
			if(cs.get(i).getData().isEqual(data.toLocalDate()))
				if(cs.get(i).getDateTime().isAfter(data.minusHours(3)) && cs.get(i).getDateTime().isBefore(data.plusHours(3)))
					return cs.get(i);	
		}
		return null;
	}
	
	@Override
	public String toString() {
		return nome + "(" + numSns + "): consultas=" + consultas + "...";
	}

}

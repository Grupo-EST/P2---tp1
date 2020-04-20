package gestsaude.recurso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import gestsaude.util.Consultas;

/** Representa um Serviço
 * TODO: A lista das consultasMarcadas deve ser cronológica.
 * 		 A lista das ordemSenhas deve ser ordenana pela ordem de atendimento.
 */
public class Servico {
	
	private String id;
	private String descricao;
	private ArrayList<Consulta> consultasMarcadas = new ArrayList<Consulta>();
	private ArrayList<Senha> ordemSenhas = new ArrayList<Senha>();
	// Para fazer marcações
	private Consulta consulta;
	private int nSenhas = ordemSenhas.size();

	public Servico(String id, String descricao) {
		setId(id);
		setDescricao(descricao);
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id=id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int getNSenhas() {
		return nSenhas;
	}
	
	public void setNSenhas() {
		this.nSenhas = ordemSenhas.size();
	}
	
	//TODO Getters e Setters de Consulta precisos?
	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	
	public void addConsultaMarcada(Consulta consulta) {
		Consultas.addConsultaOrdemData(consultasMarcadas, consulta);
	}
	
	public void removeConsultaMarcada(Consulta consulta) {
		consultasMarcadas.remove(consulta);
	}
	
	// Retorna lista de Utentes que têm consultas marcadas neste serviço
	public List<Consulta> getConsultasMarcadas(){
		return Collections.unmodifiableList(consultasMarcadas);
	}
	
	public void addSenha(Senha senha) {
		ordemSenhas.add(senha);
		setNSenhas();
	}
	
	public void removeSenha(Senha senha) {
		ordemSenhas.remove(senha);
		setNSenhas();
	}
	
	public List<Senha> getSenhasServico(){
		return Collections.unmodifiableList(ordemSenhas);
	}

	/** Retorna a próxima senha a ser atendida, ou null, caso não haja 
	 * @return a próxima senha a ser atendida, ou null, caso não haja
	 */
	public Senha getProximaSenha() {
		if(!ordemSenhas.isEmpty()) 
			return ordemSenhas.get(nSenhas - 1);
		
		return null;	
	}

	/** TODO:processo para rejeitar a próxima senha, caso o utente seja muito atrasado
	 */
	public void rejeitaProximaSenha() {
		
	}

	/** processo de terminar a consulta associada à senha */ 
	public void terminaConsulta( Senha s ) {
		s.terminaConsulta();
		removeSenha(s);
		// TODO: prox serviço?
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Servico other = (Servico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Servico: id(" + id + "), " + descricao + " Consultas Marcadas: " + getConsultasMarcadas();
	}
	
		
}


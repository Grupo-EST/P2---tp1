package gestsaude.recurso;

import java.util.ArrayList;
import java.util.Collections;
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
	private int nSenhas = 0;
	private boolean precisaConsulta;

	public Servico(String id, String descricao) {
		setId(id);
		setDescricao(descricao);
	}
	
	public void precisaConsulta() {
		this.precisaConsulta = true;
	}
	
	public boolean getPrecisaConsulta() {
		return precisaConsulta;
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
		if(chegouAHoras(senha)) 
			ordenaSenhaPorConsulta(ordemSenhas, senha);
		else 
			ordenaSenhaPorChegada(senha);
		
		nSenhas++;		
	}
	
	public void removeSenha(Senha senha) {
		ordemSenhas.remove(senha);
		nSenhas--;
	}
	
	public List<Senha> getSenhasServico(){
		return Collections.unmodifiableList(ordemSenhas);
	}

	/** Retorna a próxima senha a ser atendida, ou null, caso não haja 
	 * @return a próxima senha a ser atendida, ou null, caso não haja
	 */
	public Senha getProximaSenha() {
		if(!ordemSenhas.isEmpty()) {
			Senha s = ordemSenhas.get(0);
			setConsulta(s.getConsulta());
			return s;
		}
		
		return null;	
	}

	/** TODO:processo para rejeitar a próxima senha, caso o utente seja muito atrasado
	 */
	public void rejeitaProximaSenha() {
		Senha temp = ordemSenhas.get(0);
		if(ordemSenhas.size()<3) 
			ordemSenhas.add(ordemSenhas.size(), temp);
		else 
			ordemSenhas.add(3, temp);
		
		ordemSenhas.remove(0);
	}
	
	

	/** processo de terminar a consulta associada à senha */ 
	public void terminaConsulta( Senha s ) {
		s.terminaConsulta();
	}
	
	private boolean chegouAHoras(Senha s) {
		return s.getTempoEntrada().isBefore(s.getConsulta().getDateTime());
	}
	
	private void ordenaSenhaPorConsulta(List<Senha> senha, Senha s) {
		int i;
		for(i = 0; i<senha.size(); i++) {
			if(senha.get(i).getConsulta().getDateTime().isAfter(s.getConsulta().getDateTime()))
				break;
		}
		senha.add(i, s);
	}
	
	private void ordenaSenhaPorChegada(Senha s) {
		ordemSenhas.add(s);
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
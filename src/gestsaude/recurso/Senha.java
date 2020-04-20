package gestsaude.recurso;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/** Representa uma Senha
 */
public class Senha {
	
	private String id;
	private Consulta consulta;
	private LocalDateTime tempoEntrada;
	private List<Servico> listaServicos = new ArrayList<Servico>();
	private int nServicos = listaServicos.size();
	

	public Senha(String id, Consulta consulta, LocalDateTime tempoEntrada ) {
		this.id = id;
		setConsulta(consulta);
		setTempoEntrada(tempoEntrada);
	}
	
	public String getId() {
		return id;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
		consulta.setSenha(this);
		addListaServicos(consulta.getServico());
	}

	public LocalDateTime getTempoEntrada() {
		return tempoEntrada;
	}

	public void setTempoEntrada(LocalDateTime tempoEntrada) {
		this.tempoEntrada = tempoEntrada;
	}
	
	public int getNServicos() {
		return nServicos;
	}
	
	public void setNServicos() {
		this.nServicos = listaServicos.size();
	}

	public void addListaServicos(Servico servico) {
		listaServicos.add(servico);
		setNServicos();
		
	}
	
	public void removeListaServicos(Servico servico) {
		listaServicos.remove(servico);
		setNServicos();
	}
	
	public List<Servico> getListaServicos(){
		return Collections.unmodifiableList(listaServicos);
	}

	/** retorna o próximo serviço associado a esta senha 
	 * @return o próximo serviço associado a esta senha
	 */
	public Servico proxServico() {
		//serv.addSenha
		//serv.addConsultaMarcada(this.getConsulta)
		//consulta.setServico(servico);
		if(!listaServicos.isEmpty()) {
			Servico serv = listaServicos.get(nServicos - 1);
			consulta.setServico(serv);
			serv.addSenha(this);
			//serv.addConsultaMarcada(consulta);
			return serv;
		}
		return null;
	}

	/** faz o processamento do fim da consulta por um dado serviço
	 */
	public void terminaConsulta() {
		consulta.getServico().removeConsultaMarcada(consulta);
		//remover senha deste serviço
		//consulta.getServico().removeSenha(this);
	}

	@Override
	public String toString() {
		return getId() + " : (" + getConsulta().getSenha().getId() + ") para "
				+ getConsulta() + " no serviço: " + getConsulta().getServico().getId();
	}

	
}

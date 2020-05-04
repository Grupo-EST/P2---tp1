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
	private int tamLista = listaServicos.size();
	

	public Senha(String id, Consulta consulta, LocalDateTime tempoEntrada ) {
		this.id = id;
		setTempoEntrada(tempoEntrada);
		setConsulta(consulta);
	}
	
	
	public int getTamLista() {
		return tamLista;
	}
	
	public void setTamLista(int tamLista) {
		this.tamLista = tamLista;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
		consulta.setSenha(this);
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public LocalDateTime getTempoEntrada() {
		return tempoEntrada;
	}

	public void setTempoEntrada(LocalDateTime tempoEntrada) {
		this.tempoEntrada = tempoEntrada;
	}
	
	public void addListaServicos(Servico servico) {
		listaServicos.add(servico);
		setTamLista(listaServicos.size());
	}
	
	public void removeListaServicos(Servico servico) {
		 listaServicos.remove(servico);
		 setTamLista(listaServicos.size());
	}
	
	public List<Servico> getListaServicos(){
		return Collections.unmodifiableList(listaServicos);
	}

	/** retorna o próximo serviço associado a esta senha 
	 * @return o próximo serviço associado a esta senha
	 */
	public Servico proxServico() {
		if(existeProxServico()) {
			Servico serv = listaServicos.get(0);
			serv.addSenha(this);
			return listaServicos.get(0);
		} 
		
		return null;
	}
	
	public boolean existeProxServico() {
		/*
		 * if( tamLista > 0) { return true; } else {
		 * getConsulta().getUtente().setEstaEmConsulta(false); return false; }
		 */
		return (tamLista > 0) ? true : false;
	}
	
	
	/** faz o processamento do fim da consulta por um dado serviço
	 */
	public void terminaConsulta() {
		listaServicos.get(0).removeConsultaMarcada(consulta);
		listaServicos.get(0).removeSenha(this);
		removeListaServicos(consulta.getServico());	 
	}
	
	

	@Override
	public String toString() {
		return getId() + " : (" + getConsulta().getSenha().getId() + ") para "
				+ getConsulta() + " no serviço: " + getConsulta().getServico().getId();
	}

	
}

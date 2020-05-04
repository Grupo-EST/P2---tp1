package gestsaude.recurso;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import gestsaude.util.Consultas;
import gestsaude.util.RelogioSimulado;

/** Representa o sistema
 */
public class GEstSaude {

	// Constantes para os erros possíveis nos métodos de aceitar chamadas
	public static final int CONSULTA_ACEITE = 0;       // indica que a consulta é aceite
	public static final int UTENTE_TEM_CONSULTA = 1;   // indica que o utente já tem consulta
	public static final int SERVICO_TEM_CONSULTA = 2;  // indica que o serviço já tem consulta
	public static final int DATA_JA_PASSOU = 3;        // indica que a data já passou  
	public static final int FORA_DO_HORARIO = 4;	   // indica se está entre 8h10 e 19h50
	public static final int ALTERACAO_INVALIDA = 5;    // indica que a alteração é inválida
	
	
	// mapas com as listas de todas as informações
	private HashMap<String,Utente> utentes = new HashMap<String,Utente>();
	private HashMap<String,Servico> servicos = new HashMap<String,Servico>();
	private HashMap<String,Senha> senhas = new HashMap<String,Senha>();
	private ArrayList<Consulta> consultas = new ArrayList<Consulta>();	
	
	// definição da próxima senha
	private int proxSenha = 1;
	
	public GEstSaude() {
	}
	
	public void addUtentes(Utente u) {
		utentes.put(u.getNumSns(), u);
	}
	
	public void removeUtentes(String id) {
		utentes.remove(id);
	}
	
	public Utente getUtente(String id) {
		return utentes.get(id);
	}
	
	public void addServicos(Servico serv) {
		servicos.put(serv.getId(), serv);
	}
	
	public void removeServicos(String id) {
		servicos.remove(id);
	}
	
	public Servico getServico(String id) {
		return servicos.get(id);
	}
	
	public void addSenha(Senha senha) {
		senhas.put(senha.getId(), senha);
	}
	
	public void removeSenha(String id) {
		senhas.remove(id);
	}
	
	public Senha getSenha(String id) {
		return senhas.get(id);
	}
	
	public Senha emiteSenha( Consulta c, LocalDateTime t ) {
		// TODO testar se a consulta já está validada, se estiver retornar a senha já emitida
		// TODO senão criar e retornar a nova senha
		Senha senha = new Senha(getProximoIdSenha(), c, t);
		senha.addListaServicos(c.getServico());
		addSenha(senha);
		return senha;
	}
	
	public void resetSenhas() {
		proxSenha = 1;
	}
	
	private String getProximoIdSenha() {
		String id = "A" + proxSenha;
		proxSenha++;
		return id;
	}
	
	public int podeAceitarConsulta( Consulta c ) {
		// testar todos os motivos pelo qual isto pode falhar (ver constantes e enunciado)
		
		int numConsultasServicoSimultaneas= 0;
		int numConsultasUtenteCurtoTempo = 0;
		for (Consulta cs : c.getServico().getConsultasMarcadas()) {
			if (c.getDateTime().isEqual(cs.getDateTime()))
				numConsultasServicoSimultaneas++;
		}
		
		if (numConsultasServicoSimultaneas > 1)
			return SERVICO_TEM_CONSULTA;
		if (c.getDateTime().toLocalTime().isBefore(LocalTime.of(8, 10)) || c.getDateTime().toLocalTime().isAfter(LocalTime.of(19, 50)))
			return FORA_DO_HORARIO;
		if (c.getDateTime().isBefore(RelogioSimulado.getTempoAtual()))
			return DATA_JA_PASSOU;
		 
		
		
		return CONSULTA_ACEITE;
	}
	
	public int addConsulta( Consulta c ) {
		
		int validar = podeAceitarConsulta(c);
		
		if(validar == CONSULTA_ACEITE) {
			Consultas.addConsultaOrdemData(consultas, c);		
		} 
		// Caso nao seja validada devemos retirar? caso não aconteça continua a aparecer na lista de utentes e serviços porque foi criada.
		else {
			removeConsulta(c);
		}
		//return ALTERACAO_INVALIDA;
		return CONSULTA_ACEITE;			
	}
	
	public int podeAlterarConsulta( Consulta antiga, Consulta nova ) {
		// testar todos os motivos pelo qual isto pode falhar (ver constantes e enunciado)
		return CONSULTA_ACEITE;
	}
	
	public int alteraConsulta( Consulta antiga, Consulta nova ) {
		// testar todos os motivos pelo qual isto pode falhar (ver constantes e enunciado)
		return CONSULTA_ACEITE;
	}
	
	public Collection<Servico> getServicos() {
		return Collections.unmodifiableCollection(servicos.values());
	}
	
	public Collection<Utente> getUtentes() {
		return Collections.unmodifiableCollection(utentes.values());
	}
	
	public List<Consulta> getConsultas() {
		return Collections.unmodifiableList(consultas);
	}
	
	public Collection<Senha> getSenhas(){
		return Collections.unmodifiableCollection(senhas.values());
	}
	
	public void removeConsulta(Consulta c) {
		consultas.remove(c);
		c.getServico().removeConsultaMarcada(c);
		c.getUtente().removeConsulta(c);
	}

	
}

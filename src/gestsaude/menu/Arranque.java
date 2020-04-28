package gestsaude.menu;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.swing.Timer;

import gestsaude.recurso.Consulta;
import gestsaude.recurso.GEstSaude;
import gestsaude.recurso.Servico;
import gestsaude.recurso.Utente;
import gestsaude.util.RelogioSimulado;

/** Classe respons�vel pelo arranque do sistema.
 * Tem um m�todo para criar a configura��o de teste
 */
public class Arranque {

	/** m�todo qeu cria a configura��o inicial do sistema
	 * @return um GEstSaude j� completamente configurado
	 */
	/**
	 * @return
	 */
	public static GEstSaude getGEstSaude() {
		GEstSaude gest = new GEstSaude();
		
//		deve ter os seguintes utentes, a informa��o � n�mero, nome
//		120, D�lia Ribeiro Sanches
		Utente u1 = new Utente("120", "D�lia Ribeiro Sanches");
		gest.addUtentes(u1);
//		121, Raquel Marques Soares
		Utente u2 = new Utente("121", "Raquel Marques Soares");
		gest.addUtentes(u2);
//		122, Daniel Mendes Rodrigues
		Utente u3 = new Utente("122", "Daniel Mendes Rodrigues");
		gest.addUtentes(u3);
//		123, Zeferino Dias Torres
		Utente u4 = new Utente("123", "Zeferino Dias Torres");
		gest.addUtentes(u4);
//		124, Anabela Dias Santos
		Utente u5 = new Utente("124", "Anabela Dias Santos");
		gest.addUtentes(u5);
//		125, Felizbetrto Desgra�ado
		Utente u6 = new Utente("125", "Felizbetrto Desgra�ado");
		gest.addUtentes(u6);
//		126, Antonina Martins Pires
		Utente u7 = new Utente("126", "Antonina Martins Pires");
		gest.addUtentes(u7);
//		127, Camale�o das Neves Freitas
		Utente u8 = new Utente("127", "Camale�o das Neves Freitas");
		gest.addUtentes(u8);
//		128, Jo�o Pais Pwreira
		Utente u9 = new Utente("128", "Jo�o Pais Pwreira");
		gest.addUtentes(u9);
//		129, Carlos Freitas Lobo
		Utente u10 = new Utente("129", "Carlos Freitas Lobo");
		gest.addUtentes(u10);
//		130, Daniel Mendes Rodrigues
		Utente u11 = new Utente("130", "Daniel Mendes Rodrigues");
		gest.addUtentes(u1);


//
//		deve ter os seguintes servi�os que aceitam consultas (identifica��o, Nome do servi�o)
//		Ped1, Pediatria - Dr� P. Quena
		Servico ped1 = new Servico("Ped1", "Pediatria - Dr� P. Quena");
		gest.addServicos(ped1);
//		Ped2, Pediatria - Dr B. B. Zinho
		Servico ped2 = new Servico("Ped2", "Pediatria - Dr B. B. Zinho");
		gest.addServicos(ped2);
//		Orto1, Ortopedia - Dr Ossos
		Servico orto1 = new Servico("Orto1", "Ortopedia - Dr Ossos");	
		gest.addServicos(orto1);
//		Orto2, Ortopedia - Dr� Entorse
		Servico orto2 = new Servico("Orto2", "Ortopedia - Dr� Entorse");
		gest.addServicos(orto2);
//		Oto1, Otorrino - Dr Narize
		Servico oto1 = new Servico("Oto1", "Otorrino - Dr Narize");
		gest.addServicos(oto1);
//		Oto2, Otorrino - Dr� Odete Otite
		Servico oto2 = new Servico("Oto2", "Otorrino - Dr� Odete Otite");
		gest.addServicos(oto2);
//		Pneu1, Pneumologia - Dr� Paula M�o
		Servico pneu1 = new Servico("Pneu1", "Pneumologia - Dr� Paula M�o");
		gest.addServicos(pneu1);
//		Derm1, Dermatologia - Dr V. Ruga
		Servico derm1 = new Servico("Derm1", "Dermatologia - Dr V. Ruga");
		gest.addServicos(derm1);
//		Card1, Cardiologia - Dr Paul Sass�o
		Servico card1 = new Servico("Card1", "Cardiologia - Dr Paul Sass�o");
		gest.addServicos(card1);
//		Ofta1, Oftalmologia - Dr� �ris Tapada
		Servico ofta1 = new Servico("Ofta1", "Oftalmologia - Dr� �ris Tapada");
		gest.addServicos(ofta1);
//		Aler, Alergologia - Dr E. S. Pirro
		Servico aler = new Servico("Aler", "Alergologia - Dr E. S. Pirro");
		gest.addServicos(aler);
	
		

//	TODO:
//  deve ter os seguintes servi�os que N�O aceitam consultas (identifica��o, Nome do servi�o)
//	Rad, Radiologia
//	Audio, Audiometria
//	Scopia, Endo/Colonoscopia
//	Enf, Enfermaria
//	NeuLab, EEG + Dopler; 
		
// Deve adicionar as seguntes consultas (data e hora, ide do servi�o, id utente)	
	LocalDateTime arranque8h00 =  RelogioSimulado.getTempoAtual();
//	Hoje 8h10, Ped1, 120
	Consulta c1 = new Consulta(arranque8h00.plusMinutes(10), ped1, u1);
	gest.addConsulta(c1);
//	Hoje 8h10, Ped2, 121
	Consulta c2 = new Consulta(arranque8h00.plusMinutes(10), ped2, u2);
	gest.addConsulta(c2);
//	Hoje 8h10, Orto1, 122
	Consulta c3 = new Consulta(arranque8h00.plusMinutes(10), orto1, u3);
	gest.addConsulta(c3);
//	Hoje 8h20, Derm1, 125
	Consulta c4 = new Consulta(arranque8h00.plusMinutes(20), derm1, u5);
	gest.addConsulta(c4);
//	Hoje 8h30, Ped1, 126
	Consulta c5 = new Consulta(arranque8h00.plusMinutes(30), ped1, u7);
	gest.addConsulta(c5);
//	Hoje 8h40, Ped1, 127
	Consulta c6 = new Consulta(arranque8h00.plusMinutes(40), ped1, u8);
	gest.addConsulta(c6);
//	
//	Amanh� 8h10, Ped1, 127
	Consulta c7 = new Consulta(arranque8h00.plusDays(1).plusMinutes(10), ped1, u8);
	gest.addConsulta(c7);
//	Amanh� 8h10, Ped1, 129
	Consulta c8 = new Consulta(arranque8h00.plusDays(1).plusMinutes(10), ped1, u10);
	gest.addConsulta(c8);
//	Daqui a dois dias 8h40, Ped1, 123
	Consulta c9 = new Consulta(arranque8h00.plusDays(2).plusMinutes(40), ped1, u4);
	gest.addConsulta(c9);

	
	
	return gest;
	}

	/** arranque do sistema
	 */
	public static void main(String[] args) {
		// criar o GEstSaude
		GEstSaude gs = getGEstSaude();
		
		// Criar o rel�gio simulado e definir o tempo por segundo
		RelogioSimulado relogio = RelogioSimulado.getRelogioSimulado();
		relogio.setTicksPorSegundo( 60 ); 
		
		// criar as m�quina de entrada, neste caso ir� ter duas
		MaquinaEntrada me1 = new MaquinaEntrada( new Point(10, 10), "Entrada 1", gs );
		me1.setVisible( true );
		MaquinaEntrada me2 = new MaquinaEntrada( new Point(10, 140), "Entrada 2", gs );
		me2.setVisible( true );
		
		// criar o menu da secretaria, neste caso ir� ter apenas um
		MenuSecretaria lc = new MenuSecretaria( new Point( 230, 10 ), "Secretaria", gs);
		lc.setVisible( true );
		
		// criar todos os menus de servi�o
		Collection<Servico> servs = gs.getServicos(); 
		int serIdx = 0;
		MenuServico menus[] = new MenuServico[servs.size()];
		
		for (Servico s : servs) {
			menus[serIdx] = new MenuServico(new Point(10 + (serIdx % 5) * 200, 250 + (serIdx / 5) * 170), s, gs);
			menus[serIdx].setVisible(true);
			serIdx++;
		}
		 
		
		// criar um temporizador que vai atualizando as v�rias janelas
		// do menu de servi�os, de 10 em 10 segundos (10000 milisegundos)
		Timer t = new Timer( 10000, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        for( MenuServico ms : menus )
		            ms.atualizarInfo();		
		        	lc.atualizarRelogio();
		    }
		});
		t.start();
	}	
}

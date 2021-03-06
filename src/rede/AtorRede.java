package rede;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import fallenAngels.Arena;
import fallenAngels.AtorJogador;

public class AtorRede implements OuvidorProxy {
	private AtorJogador atorJogador;
	private Proxy proxy;
	private boolean ehMinhaVez = false;
	
	public AtorRede(AtorJogador atorJogador) {
		super();
		this.atorJogador = atorJogador;
		proxy = Proxy.getInstance();
		proxy.addOuvinte(this);
	}
	
	public boolean conectar(String nome, String servidor) {
		try {
			proxy.conectar(servidor, nome);
			return true;
		} catch (JahConectadoException | NaoPossivelConectarException | ArquivoMultiplayerException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void iniciarPartidaRede() {
		try {
			proxy.iniciarPartida(2);
		} catch (NaoConectadoException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarJogada(Arena arena) {
	    ehMinhaVez = false;
		Mensagem msg = new Mensagem(arena);
		try {
			proxy.enviaJogada(msg);
		} catch (NaoJogandoException e) {
			e.printStackTrace();
		}
	}

	public boolean enviarEstadoInicial(Arena arena){
	    Mensagem msg = new Mensagem(arena);
        try {
            proxy.enviaJogada(msg);
            return true;
        } catch (NaoJogandoException e) {
            e.printStackTrace();
            return false;
        }
    }

	@Override
	public void receberJogada(Jogada jogada) {
        Mensagem msg = (Mensagem) jogada;
        if(atorJogador.rodada == 0){
            atorJogador.receberEstadoInicial(msg.getMensagem());
        }else {
            ehMinhaVez = true;
            atorJogador.receberMensagemRede(msg.getMensagem());
        }
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
	    if(posicao == 1) {
            ehMinhaVez = true;
        }
	    else if(posicao == 2) {
            ehMinhaVez = false;
        }
		atorJogador.iniciarPartidaRede();
	}

	public void desconectar() {
		try {
			proxy.desconectar();
		} catch (NaoConectadoException e) {
			e.printStackTrace();
		}
	}

	public boolean ehMinhaVez(){
	    return ehMinhaVez;
    }

	@Override
	public void finalizarPartidaComErro(String message) {
		atorJogador.game.notificarPartidaComErro();
        desconectar();
	}

	@Override
	public void receberMensagem(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tratarConexaoPerdida() {
		atorJogador.game.notificarConexaoPerdida();
        desconectar();
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		// TODO Auto-generated method stub

	}

}

/**
	Esta classe representa um placar no jogo. A classe princial do jogo (Pong)
	instancia dois objeto deste tipo, cada um responsável por gerenciar a pontuação
	de um player, quando a execução é iniciada.
*/

public class Score {

	private String playerId;
	private int score;
	/**
		Construtor da classe Score.
		@param playerId uma string que identifica o player ao qual este placar está associado.
	*/

	public Score(String playerId){
		this.playerId = playerId;
	}
	/**
		Método de desenho do placar.
	*/

	public void draw(){

		String valor = String.valueOf(score);

		if(this.playerId.equals("Player 2")){
			GameLib.drawText(this.playerId, 70, GameLib.ALIGN_RIGHT);
			GameLib.drawText(valor, 90, GameLib.ALIGN_RIGHT);
		}
		else{
			GameLib.drawText(this.playerId, 70, GameLib.ALIGN_LEFT);			
			GameLib.drawText(valor, 90,  GameLib.ALIGN_LEFT);
		}

	}

	/**
		Método que incrementa em 1 unidade a contagem de pontos.
	*/

	public void inc(){
		this.score++;
	}

	/**
		Método que devolve a contagem de pontos mantida pelo placar.

		@return o valor inteiro referente ao total de pontos.
	*/

	public int getScore(){
		return this.score;
	}
}
import java.awt.Color;

/**
 * Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
 * instancia um objeto deste tipo quando a execução é iniciada.
 */

public class Ball {

	private final double INICIO_X;
	private final double INICIO_Y;
	private int direcaoX = 1;
	private int direcaoY = 1;
	private double cx;
	private double cy;
	private double width;
	private double height;
	private Color color;
	private double speed;

	/**
	 * Construtor da classe Ball. Observe que quem invoca o construtor desta classe
	 * define a velocidade da bola (em pixels por millisegundo), mas não define a
	 * direção deste movimento. A direção do movimento é determinada aleatóriamente
	 * pelo construtor.
	 * 
	 * @param cx     coordenada x da posição inicial da bola (centro do retangulo
	 *               que a representa).
	 * @param cy     coordenada y da posição inicial da bola (centro do retangulo
	 *               que a representa).
	 * @param width  largura do retangulo que representa a bola.
	 * @param height altura do retangulo que representa a bola.
	 * @param color  cor da bola.
	 * @param speed  velocidade da bola (em pixels por millisegundo).
	 */

	public Ball(double cx, double cy, double width, double height, Color color, double speed) {
		this.cx = cx;
		this.cy = cy;
		this.width = width;
		this.height = height;
		this.color = color;
		this.speed = speed;
		this.INICIO_X = cx;
		this.INICIO_Y = cy;
	}
	
	/**
	 * Método chamado sempre que a bola precisa ser (re)desenhada.
	 */
	
	public void draw() {
		GameLib.setColor(this.color);
		GameLib.fillRect(cx, cy, width, height);
	}
	
	/**
	 * Método chamado quando o estado (posição) da bola precisa ser atualizado.
	 * 
	 * @param delta quantidade de millisegundono inicio: %f | Posiçaõ de Y no inicio
	 *              %f", cx, cy); }s que se passou entre o ciclo anterior de
	 *              atualização do jogo e o atual.
	 */
	
	public void update(long delta) {
		double nextPosition = (delta / speed) / 10;
		this.cx += nextPosition * direcaoX; 
		this.cy += nextPosition * direcaoY;
	}

	/**
	 * Método chamado quando detecta-se uma colisão da bola com um jogador.
	 * 
	 * @param playerId uma string cujo conteúdo identifica um dos jogadores.
	 */

	public void onPlayerCollision(String playerId) {
		this.speed *= -1;
	}

	/**
	 * Método chamado quando detecta-se uma colisão da bola com uma parede.
	 * 
	 * @param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
	 */

	public void onWallCollision(String wallId) {

		switch(wallId){
			case "Right":
				this.direcaoX = -1;
				reset();
				break;
			case "Left":
				reset();
				this.direcaoX = 1; 
				break;
			case "Bottom":
				this.direcaoY = changeDirection();
				break;
			case "Top":
				this.direcaoY = changeDirection();
				break;
		}

	}

	/**
	 * Método que verifica se houve colisão da bola com uma parede.
	 * 
	 * @param wall referência para uma instância de Wall contra a qual será
	 *             verificada a ocorrência de colisão da bola.
	 * @return um valor booleano que indica a ocorrência (true) ou não (false) de
	 *         colisão.
	 */

	public boolean checkCollision(Wall wall) {
		switch(wall.getId()){
			case "Right":
				if((this.cx + this.width / 2) > (wall.getCx() - wall.getWidth() / 2)) {
					System.out.println(this.cx);
					return true;
				}
				break;
				case "Left":
				if((this.cx - this.width / 2) < ( wall.getCx() + wall.getWidth() / 2)){
					System.out.println(this.cx);
					return true;
				}
				break;
			case "Top":
				if((this.cy - this.height / 2) < ( wall.getCy() + wall.getHeight() / 2)){
					System.out.println(this.cx);
					return true;
				}
				break;
			case "Bottom":
				if((this.cy + this.height /2 ) > ( wall.getCy() - wall.getHeight() / 2)){
					return true;
				}
				break;
		}
		return false;
	}

	/**
	 * Método que verifica se houve colisão da bola com um jogador.
	 * BOLA: 124.60000000000973 Bola - Negocio114.60000000000973

	 * 
	 * @param player referência para uma instância de Player contra o qual será
	 *               verificada a ocorrência de colisão da bola.
	 * @return um valor booleano que indica a ocorrência (true) ou não (false) de
	 *         colisão.
	 */

	public boolean checkCollision(Player player) {

		switch(player.getId()){
			case "Player 2":
				System.out.println(player.getCx());
				if((this.cx + this.width / 2) > (player.getCx() - player.getWidth() / 2)
					&& (this.cy + this.height / 2) > (player.getCy() - player.getHeight() / 2)
					&& (this.cy - this.height /2) < (player.getCy() + player.getHeight() / 2)) {
						return true;
				}
				break;
			case "Player 1":{
				if( (this.cx - this.width / 2) < (player.getCx() + player.getWidth()/2)
					&& (this.cy + this.height / 2) > (player.getCy() - player.getHeight() / 2)
					&& (this.cy - this.height /2) < (player.getCy() + player.getHeight() / 2)
				){
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Método que devolve a coordenada x do centro do retângulo que representa a
	 * bola.
	 * 
	 * @return o valor double da coordenada x.
	 */

	public double getCx() {
		return this.cx;
	}

	/**
	 * Método que devolve a coordenada y do centro do retângulo que representa a
	 * bola.
	 * 
	 * @return o valor double da coordenada y.
	 */

	public double getCy() {
		return this.cy;
	}

	/**
	 * Método que devolve a velocidade da bola.
	 * 
	 * @return o valor double da velocidade.
	 * 
	 */

	public double getSpeed() {
		return this.speed;
	}

	private void reset(){
		this.cx = INICIO_X;
		this.cy = INICIO_Y;
	}

	private int changeDirection(){

		if(this.speed > 0) {
			if(this.cx >= 370){
				if(this.cy > 225){
					return -1;
				}else {
					return 1;
				}
			}else{
				if(this.cy > 225){
					return -1;
				}else {
					return 1;
				}
			}
		} else {
			if(this.cx >= 370){
				if(this.cy > 225){
					return 1;
				} else {
					return -1;
				}
			} else {
				if(this.cy > 225){
					return 1;
				} else {
					return -1;
				}
			}
		}

	}

}

/***
 * 225
 * 370
 * 
 * ^CLeft --- 10.0 Right --- 790.0 Top --- 400.0 Bottom --- 400.0
 * 
 */
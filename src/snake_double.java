import static java.lang.String.format;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

class snake_game extends JPanel {
	public static List<Point> snake_2 = new CopyOnWriteArrayList<Point>();
	public static List<Point> snake = new CopyOnWriteArrayList<Point>();
	public static List<Point> treat = new CopyOnWriteArrayList<Point>();
	public static List<Point> wall = new CopyOnWriteArrayList<Point>();

	public static int direction = 4;// p1����V�A�@�}�l����V����
	public static int direction_2 = 8;// p2����V�A�@�}�l����V����
	public static boolean gameover = false;// �P�_�O�_�C������
	public static int speed = 0;// p1���t��
	public static int speed_2 = 0;// p2���t��
	public static boolean startgame = false;// �P�_�O�_�}�l�C��
	public static int energy;// p1����q��
	public static int energy_2;// p2����q��
	public static int treat_num = 0;// p1�Y����q�y�ƶq
	public static int treat_num_2 = 0;// p2�Y����q�y�ƶq
	public static boolean win_1;// p1�O�_Ĺ
	public static boolean win_2;// p2�O�_Ĺ

	public snake_game() {// �غc��
		/* �����]�w */
		setPreferredSize(new Dimension(640, 440));// �����j�p
		setBackground(Color.white);// �����I���C��
		setFont(new Font("SansSerif", Font.BOLD, 48));// �r���j�p�C��]�w
		setFocusable(true);
		addKeyListener(new KeyAdapter() {// ��L��ť
			@Override
			public void keyPressed(KeyEvent e) {
				/****** p1 ******/
				if (e.getKeyCode() == KeyEvent.VK_UP) {// �Y���U��V��W
					if (direction != 2) {// ���U�ɤ��੹�W
						direction = 1;// �D���W
						speed = 200 - snake_game.treat_num * 10;// �H�ۦY�쪺��q�y�U�h�t�׷U��
						if (speed <= 0) {
							speed = 0;// �ִ̧N�O0
						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {// �Y���U��V��U
					if (direction != 1) {// ���W�ɤ��੹�U
						direction = 2;// �D���U
						speed = 200 - snake_game.treat_num * 10;// �H�ۦY�쪺��q�y�U�h�t�׷U��
						if (speed <= 0) {
							speed = 0;
						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {// �Y���U��V��k
					if (direction != 4) {// �����ɤ��੹�k
						direction = 3;// �D���k
						speed = 200 - snake_game.treat_num * 10;// �H�ۦY�쪺��q�y�U�h�t�׷U��
						if (speed <= 0) {
							speed = 0;
						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (direction != 3) {// �����ɤ��੹�k
						direction = 4;// �D����
						speed = 200 - snake_game.treat_num * 10;// �H�ۦY�쪺��q�y�U�h�t�׷U��
						if (speed <= 0) {
							speed = 0;
						}
					}
				}
				/****** p2 ******/
				if (e.getKeyCode() == KeyEvent.VK_W) {// �Y���U��V��W
					if (direction_2 != 6) {// ���U�ɤ��੹�W
						direction_2 = 5;// �D���W
						speed_2 = 200 - snake_game.treat_num_2 * 10;// �H�ۦY�쪺��q�y�U�h�t�׷U��
						if (speed_2 <= 0) {
							speed_2 = 0;
						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {// �Y���U��V��U
					if (direction_2 != 5) {// ���W�ɤ��੹�U
						direction_2 = 6;// �D���U
						speed_2 = 200 - snake_game.treat_num_2 * 10;// �H�ۦY�쪺��q�y�U�h�t�׷U��
						if (speed_2 <= 0) {
							speed_2 = 0;
						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_D) {// �Y���U��V��k
					if (direction_2 != 8) {// �����ɤ��੹�k
						direction_2 = 7;// �D���k
						speed_2 = 200 - snake_game.treat_num_2 * 10;// �H�ۦY�쪺��q�y�U�h�t�׷U��
						if (speed_2 <= 0) {
							speed_2 = 0;
						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_A) {
					if (direction_2 != 7) {// �����ɤ��੹�k
						direction_2 = 8;// �D����
						speed_2 = 200 - snake_game.treat_num_2 * 10;// �H�ۦY�쪺��q�y�U�h�t�׷U��
						if (speed_2 <= 0) {
							speed_2 = 0;
						}
					}
				}

			}

			public void keyReleased(KeyEvent e) {
				/*** �����i�[�t ***/
				/*** p1 ***/
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					speed = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					speed = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					speed = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					speed = 0;
				}
				/*** p2 ***/
				if (e.getKeyCode() == KeyEvent.VK_W) {
					speed_2 = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					speed_2 = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_D) {
					speed_2 = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_A) {
					speed_2 = 0;
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				/*** ����U�C���}�l ***/
				if (startgame == false) {
					startgame = true;// �C���}�l
					direction = 4;// p1��l��V
					direction_2 = 8;// p2��l��V
					speed = 0;// p1��l�t��
					speed_2 = 0;// p2��l�t��
					treat_num = 0;// p1��q�y��l�ƶq
					treat_num_2 = 0;// 2��q�y��l�ƶq
					wall.clear();// ��ɨ�s
					snake.clear();// p1��s
					snake_2.clear();// p2��s
					treat.clear();// ��q�y��s
					energy = 5000;// p1��l��q��
					energy_2 = 5000;// p2��l��q��
					win_1 = true;
					win_2 = true;
					/** �[�Jp1 **/
					control newgame = new control();
					newgame.game_init();
					/** �[�Jp2 **/
					control_2 newgame_2 = new control_2();
					newgame_2.game_init();
					/** �}�ҷs�C�� **/
					snake_double.newgame();
					/** �e����s **/
					repaint();
				}
				if (gameover == true) {// �����C���A���s�P�_��Ĺ�P�O�_�}�l
					startgame = false;
					gameover = false;
				}
			}
		});

	}

	/*** �e�X���� ***/
	public void paintComponent(Graphics gg) {
		super.paintComponent(gg);
		Graphics2D g = (Graphics2D) gg;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (startgame == true) {
			drawSnake(g);// �e�X�D
			drawGrid(g);// �e�X�I��
			drawTreats(g);// �e�X��q�y
			drawScore(g);// �e�X��q��
		}
		if (startgame == false) {
			drawStartScreen(g);// �e�X�}�l�e��
		}
		if (gameover == true) {
			drawOverScreen(g);// ���X�����e��
		}

	}

	/** �}�l�e�� **/
	void drawStartScreen(Graphics2D g) {
		g.setColor(Color.blue);// snake�r���C��
		g.setFont(getFont());// �N�C��[��r��
		g.drawString("Snake", 240, 190);// �]�wsnake�r���j�p
		g.setColor(Color.orange);// click to start�r���C��r���C��
		g.drawString("(click to start)", 150, 240);// �]�wclick to start�r���j�p
		repaint();
	}

	/** �����e�� **/
	void drawOverScreen(Graphics2D g) {
		g.setColor(Color.blue);// �r���C��
		g.setFont(getFont());// �N�C��[��r��
		if (snake_game.win_1 == false) {// �]�wp1������P1 Loss�M�r���j�p
			g.drawString("P1 Loss", 230, 190);
		} else if (snake_game.win_2 == false) {// �]�wp2������P2 Loss�M�r���j�p
			g.drawString("P2 Loss", 230, 190);
		}

		g.setColor(Color.orange);// click to restart�r���C��r���C��
		g.drawString("(click to restart)", 150, 240);// �]�wclick to restart�r���j�p
		repaint();
	}

	/** ��q�� **/
	void drawScore(Graphics2D g) {
		/** p1��q **/
		int h = getHeight();// ���o����
		g.setFont(getFont().deriveFont(Font.BOLD, 18));// �r�λP�j�p
		if (energy <= 0) {// ���q�Ȥp��0�r���C���ܬ���
			g.setColor(Color.red);
		} else {
			g.setColor(Color.BLUE);
		}
		g.drawString(format("P1:energy %d", energy), getWidth() - 550, h - 30);// �]�w���e�M�r
		/** p2��q **/
		g.setFont(getFont().deriveFont(Font.BOLD, 18));// �r�λP�j�p
		if (energy_2 <= 0) {// ���q�Ȥp��0�r���C���ܬ���
			g.setColor(Color.red);
		} else {
			g.setColor(Color.black);
		}
		g.drawString(format("P2:energy %d", energy_2), getWidth() - 200, h - 30);// �]�w���e�M�r
	}

	/** �D **/
	void drawSnake(Graphics2D g) {
		/** p1�D **/
		g.setColor(Color.blue);// �D�����C��
		for (Point p : snake) {
			g.fillRect(p.x * 10, p.y * 10, 10, 10);// ��setcolor�ҫ��w���C��,(x�y�СAy�y�СA���A�e)
			repaint();
		}
		if (energy <= 300) {// ���q�p��300�Y�|�ܬ���
			g.setColor(Color.red);
		} else {
			g.setColor(Color.orange);
		}

		Point head = snake.get(0);
		g.fillRect(head.x * 10, head.y * 10, 10, 10);// ��setcolor�ҫ��w���C��,(x�y�СAy�y�СA���A�e)
		/** p2�D **/
		g.setColor(Color.black);// �D�����C��
		for (Point p : snake_2) {
			g.fillRect(p.x * 10, p.y * 10, 10, 10);// ��setcolor�ҫ��w���C��,(x�y�СAy�y�СA���A�e)
			repaint();
		}
		if (energy_2 <= 300) {// ���q�p��300�Y�|�ܬ���
			g.setColor(Color.red);
		} else {
			g.setColor(Color.orange);
		}

		Point head_2 = snake_2.get(0);
		g.fillRect(head_2.x * 10, head_2.y * 10, 10, 10);
	}

	/** ��� **/
	void drawGrid(Graphics2D g) {
		g.setColor(Color.lightGray);// ����C��

		for (int i = 0; i < 216; i++) {
			g.fillRect(snake_game.wall.get(i).x * 10, snake_game.wall.get(i).y * 10, 10, 10);// ��ɽd��
		}
	}

	/** ��q�y **/
	void drawTreats(Graphics2D g) {
		g.setColor(Color.green);// ��q�y�C��
		for (Point p : treat)
			g.fillRect(p.x * 10, p.y * 10, 10, 10);// ��setcolor�ҫ��w���C��,(x�y�СAy�y�СA���A�e)
	}
}

/** ��q�y������ **/
class treat extends Thread {
	/** ��q�y�y�� **/
	int row;
	int col;

	public void addTreat() {
		row = (int) (Math.random() * 62 + 1);// ��1~62�ü�
		col = (int) (Math.random() * 42 + 1);// ��1~42�ü�
		if (snake_game.startgame == true) {
			if (snake_game.treat.size() < 2) {// �̦h�u��2�ӯ�q�y�b���W
				snake_game.treat.add(new Point(row, col));
				try {
					Thread.sleep(1000);// �Q�Y����ʤ@��~�|�]�X�ӤU�@�ӯ�q�y
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

	public void run() {
		while (snake_game.gameover != true) {
			addTreat();// �}�l�W�[��q�y
		}
	}
}

/** p1�D������ **/
class control extends Thread {
	/**�D���y��**/
	int row = 60;
	int col = 30;

	boolean start = false;
	/**������l�]�m**/
	void game_init() {

		for (int i = 0; i <= 6; i++) {
			snake_game.snake.add(new Point((row / 2) + i, col / 2));//�]�w�_�l�y��

		}
		border();//��ɪ�l�]�m
	}
	/**�C���@�ӯ�q�N�|���25**/
	public void energyUsed() {

		snake_game.energy -= 25;
		/*���q�p��0�N��F*/
		if (snake_game.energy <= 0) {
			snake_game.gameover = true;
			snake_game.win_1 = false;
		}
	}

	public void hitsnake() {
		// ����O�H
		if (snake_game.snake_2.contains(snake_game.snake.get(0))) {// ����p2
			snake_game.gameover = true;
			snake_game.win_1 = false;
		}

	}
	/**��ɪ��إ�**/
	public void border() {
		for (int i = 0; i <= 64; i++) {
			snake_game.wall.add(new Point(i, 0));
			snake_game.wall.add(new Point(i, 43));
		}

		for (int i = 1; i <= 43; i++) {
			snake_game.wall.add(new Point(0, i));
			snake_game.wall.add(new Point(63, i));
		}

	}
	/**�D������**/
	public void snake_move() {
		Point head = null;
		int total_speed;
		/**��D���t�פp��0�A��ȴN�|����0**/         
		if ((300 - snake_game.treat_num * 10) - (snake_game.speed) <= 0) {
			total_speed = 0;
		} else {
			total_speed = (300 - snake_game.treat_num * 10) - (snake_game.speed);
		}
		if (snake_game.startgame == true) {
			try {
				Thread.sleep(total_speed);// �D���t���H�ۤ��ƷU���N�U��
			} catch (InterruptedException e) {
				return;
			}
			/***��D���W��***/
			if (snake_game.direction == 1) { 
				snake_game.snake.add(0, new Point(snake_game.snake.get(0).x, snake_game.snake.get(0).y - 1));//�b�������W�@��B���y�Цs�i�}�C
				snake_game.snake.remove(snake_game.snake.size() - 1);//�R���}�C���̫�@�檺�y��
				head = snake_game.snake.get(0);//�����D���Y���y��
				
				for (Point p : snake_game.treat) {
					if (p.x == snake_game.snake.get(0).x && p.y == snake_game.snake.get(0).y) {/*�Y�Y���q�y*/
						snake_game.snake.add(0, new Point(snake_game.snake.get(0).x, snake_game.snake.get(0).y - 1));//�N�����D���ګ᭱�@�Ӫ��y�Х[��}�C��
						snake_game.treat.remove(p);//�R���Q�Y������q�y
						snake_game.treat_num++;//������q�y�ƶq
						snake_game.energy = 5000;//��_��q��
					}
				}
			}
			/***��D���U��***/
			if (snake_game.direction == 2) {
				snake_game.snake.add(0, new Point(snake_game.snake.get(0).x, snake_game.snake.get(0).y + 1));//�b�������W�@��B���y�Цs�i�}�C
				snake_game.snake.remove(snake_game.snake.size() - 1);//�R���}�C���̫�@�檺�y��
				head = snake_game.snake.get(0);//�����D���Y���y��
				for (Point p : snake_game.treat) {
					if (p.x == snake_game.snake.get(0).x && p.y == snake_game.snake.get(0).y) {/*�Y�Y���q�y*/
						snake_game.snake.add(0, new Point(snake_game.snake.get(0).x, snake_game.snake.get(0).y + 1));//�N�����D���ګ᭱�@�Ӫ��y�Х[��}�C��
						snake_game.treat.remove(p);//�R���Q�Y������q�y
						snake_game.treat_num++;//������q�y�ƶq
						snake_game.energy = 5000;//��_��q��
					}
				}
			}
			/***��D���k��***/
			if (snake_game.direction == 3) {
				snake_game.snake.add(0, new Point(snake_game.snake.get(0).x + 1, snake_game.snake.get(0).y));//�b�������W�@��B���y�Цs�i�}�C
				snake_game.snake.remove(snake_game.snake.size() - 1);//�R���}�C���̫�@�檺�y��
				head = snake_game.snake.get(0);//�����D���Y���y��
				for (Point p : snake_game.treat) {
					if (p.x == snake_game.snake.get(0).x && p.y == snake_game.snake.get(0).y) {/*�Y�Y���q�y*/
						snake_game.snake.add(0, new Point(snake_game.snake.get(0).x + 1, snake_game.snake.get(0).y));//�N�����D���ګ᭱�@�Ӫ��y�Х[��}�C��
						snake_game.treat.remove(p);//�R���Q�Y������q�y
						snake_game.treat_num++;//������q�y�ƶq
						snake_game.energy = 5000;//��_��q��
					}
				}
			}
			/***��D������***/
			if (snake_game.direction == 4) {
				snake_game.snake.add(0, new Point(snake_game.snake.get(0).x - 1, snake_game.snake.get(0).y));//�b�������W�@��B���y�Цs�i�}�C
				snake_game.snake.remove(snake_game.snake.size() - 1);//�R���}�C���̫�@�檺�y��
				head = snake_game.snake.get(0);//�����D���Y���y��
				for (Point p : snake_game.treat) {
					if (p.x == snake_game.snake.get(0).x && p.y == snake_game.snake.get(0).y) {/*�Y�Y���q�y*/
						snake_game.snake.add(0, new Point(snake_game.snake.get(0).x - 1, snake_game.snake.get(0).y));//�N�����D���ګ᭱�@�Ӫ��y�Х[��}�C��
						snake_game.treat.remove(p);//�R���Q�Y������q�y
						snake_game.treat_num++;//������q�y�ƶq
						snake_game.energy = 5000;//��_��q��
					}
				}
			}

			if (snake_game.wall.contains(snake_game.snake.get(0))) {// �Y��������N�����C��
				snake_game.gameover = true;
				snake_game.win_1 = false;
			}
			for (int i = 2; i < snake_game.snake.size(); i++) {// �Y����ۤv�N�����C��
				if (head.x == snake_game.snake.get(i).x && head.y == snake_game.snake.get(i).y) {
					snake_game.gameover = true;
					snake_game.win_1 = false;
				}
			}
		}
	}
	/**����p1�D�������**/
	public void run() {
		while (snake_game.gameover != true) {
			energyUsed();
			snake_move();
			hitsnake();
		}
	}
}
/** p2�D������ **/
class control_2 extends Thread {
	/**�D���y��**/
	int row = 30;
	int col = 30;

	boolean start = false;
	/**������l�]�m**/
	void game_init() {

		for (int i = 0; i <= 6; i++) {
			snake_game.snake_2.add(new Point((row / 2) + i, col / 2));//�]�w�_�l�y��
		}
	}
	/**�C���@�ӯ�q�N�|���25**/
	public void energyUsed() {
		/*���q�p��0�N��F*/
		snake_game.energy_2 -= 25;
		if (snake_game.energy_2 <= 0) {
			snake_game.gameover = true;
			snake_game.win_2 = false;
		}
	}

	public void hitsnake() {
		
		if (snake_game.snake.contains(snake_game.snake_2.get(0))) {// ����p1�N��F
			snake_game.gameover = true;
			snake_game.win_2 = false;
		}

	}
	/**�D������**/
	public void snake_move() {
		Point head = null;
		int total_speed;
		/**��D���t�פp��0�A��ȴN�|����0**/ 
		if (snake_game.startgame == true) {

			if ((300 - snake_game.treat_num_2 * 10) - (snake_game.speed_2) <= 0) {
				total_speed = 0;
			} else {
				total_speed = (300 - snake_game.treat_num_2 * 10) - (snake_game.speed_2);
			}
			try {
				Thread.sleep(total_speed);// �D���t���H�ۤ��ƷU���N�U��
			} catch (InterruptedException e) {
				return;
			}
			/***��D���W��***/
			if (snake_game.direction_2 == 5) {
				snake_game.snake_2.add(0, new Point(snake_game.snake_2.get(0).x, snake_game.snake_2.get(0).y - 1));//�b�������W�@��B���y�Цs�i�}�C
				snake_game.snake_2.remove(snake_game.snake_2.size() - 1);//�R���}�C���̫�@�檺�y��
				head = snake_game.snake_2.get(0);//�����D���Y���y��
				for (Point p : snake_game.treat) {
					if (p.x == snake_game.snake_2.get(0).x && p.y == snake_game.snake_2.get(0).y) {/*�Y�Y���q�y*/
						snake_game.snake_2.add(0,new Point(snake_game.snake_2.get(0).x, snake_game.snake_2.get(0).y - 1));//�N�����D���ګ᭱�@�Ӫ��y�Х[��}�C��
								
						snake_game.treat.remove(p);//�R���Q�Y������q�y
						snake_game.treat_num_2++;//������q�y�ƶq
						snake_game.energy_2 = 5000;//��_��q��
					}
				}
			}
			/***��D���U��***/
			if (snake_game.direction_2 == 6) {
				snake_game.snake_2.add(0, new Point(snake_game.snake_2.get(0).x, snake_game.snake_2.get(0).y + 1));//�b�������W�@��B���y�Цs�i�}�C
				snake_game.snake_2.remove(snake_game.snake_2.size() - 1);//�R���}�C���̫�@�檺�y��
				head = snake_game.snake_2.get(0);//�����D���Y���y��
				for (Point p : snake_game.treat) {
					if (p.x == snake_game.snake_2.get(0).x && p.y == snake_game.snake_2.get(0).y) {/*�Y�Y���q�y*/
						snake_game.snake_2.add(0,
								new Point(snake_game.snake_2.get(0).x, snake_game.snake_2.get(0).y + 1));//�N�����D���ګ᭱�@�Ӫ��y�Х[��}�C��
						snake_game.treat.remove(p);//�R���Q�Y������q�y
						snake_game.treat_num_2++;//������q�y�ƶq
						snake_game.energy_2 = 5000;//��_��q��
					}
				}
			}
			/***��D���k��***/
			if (snake_game.direction_2 == 7) {
				snake_game.snake_2.add(0, new Point(snake_game.snake_2.get(0).x + 1, snake_game.snake_2.get(0).y));//�b�������W�@��B���y�Цs�i�}�C
				snake_game.snake_2.remove(snake_game.snake_2.size() - 1);//�R���}�C���̫�@�檺�y��
				head = snake_game.snake_2.get(0);//�����D���Y���y��
				for (Point p : snake_game.treat) {
					if (p.x == snake_game.snake_2.get(0).x && p.y == snake_game.snake_2.get(0).y) {/*�Y�Y���q�y*/
						snake_game.snake_2.add(0,
								new Point(snake_game.snake_2.get(0).x + 1, snake_game.snake_2.get(0).y));//�N�����D���ګ᭱�@�Ӫ��y�Х[��}�C��
						snake_game.treat.remove(p);//�R���Q�Y������q�y
						snake_game.treat_num_2++;//������q�y�ƶq
						snake_game.energy_2 = 5000;//��_��q��
					}
				}
			}
			/***��D������***/
			if (snake_game.direction_2 == 8) {
				snake_game.snake_2.add(0, new Point(snake_game.snake_2.get(0).x - 1, snake_game.snake_2.get(0).y));//�b�������W�@��B���y�Цs�i�}�C
				snake_game.snake_2.remove(snake_game.snake_2.size() - 1);//�R���}�C���̫�@�檺�y��
				head = snake_game.snake_2.get(0);//�����D���Y���y��
				for (Point p : snake_game.treat) {
					if (p.x == snake_game.snake_2.get(0).x && p.y == snake_game.snake_2.get(0).y) {/*�Y�Y���q�y*/
						snake_game.snake_2.add(0,
								new Point(snake_game.snake_2.get(0).x - 1, snake_game.snake_2.get(0).y));//�N�����D���ګ᭱�@�Ӫ��y�Х[��}�C��
						snake_game.treat.remove(p);//�R���Q�Y������q�y
						snake_game.treat_num_2++;//������q�y�ƶq
						snake_game.energy_2 = 5000;//��_��q��
					}
				}
			}

			if (snake_game.wall.contains(snake_game.snake_2.get(0))) {// �Y��������N�����C��
				snake_game.gameover = true;
				snake_game.win_2 = false;

			}
			for (int i = 2; i < snake_game.snake_2.size(); i++) {// �Y����ۤv�N�����C��
				if (head.x == snake_game.snake_2.get(i).x && head.y == snake_game.snake_2.get(i).y) {
					snake_game.gameover = true;
					snake_game.win_2 = false;
				}
			}
		}
	}
	/**����p2�D�������**/
	public void run() {
		while (snake_game.gameover != true) {
			energyUsed();
			snake_move();
			hitsnake();
		}
	}
}

public class snake_double {

	public static void newgame() {
		/**���J3�Ӱ�����A���O�O�����q�y�Bp1�D�Bp2�D***/
		control a = new control();
		treat b = new treat();
		control_2 c = new control_2();
		a.start();
		b.start();
		c.start();
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();//�Ф@�ӷs������

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Snake");//�]�w���D
		f.setResizable(false);
		f.add(new snake_game(), BorderLayout.CENTER);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}

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

	public static int direction = 4;// p1的方向，一開始的方向為左
	public static int direction_2 = 8;// p2的方向，一開始的方向為左
	public static boolean gameover = false;// 判斷是否遊戲結束
	public static int speed = 0;// p1的速度
	public static int speed_2 = 0;// p2的速度
	public static boolean startgame = false;// 判斷是否開始遊戲
	public static int energy;// p1的能量值
	public static int energy_2;// p2的能量值
	public static int treat_num = 0;// p1吃的能量球數量
	public static int treat_num_2 = 0;// p2吃的能量球數量
	public static boolean win_1;// p1是否贏
	public static boolean win_2;// p2是否贏

	public snake_game() {// 建構元
		/* 介面設定 */
		setPreferredSize(new Dimension(640, 440));// 視窗大小
		setBackground(Color.white);// 視窗背景顏色
		setFont(new Font("SansSerif", Font.BOLD, 48));// 字型大小顏色設定
		setFocusable(true);
		addKeyListener(new KeyAdapter() {// 鍵盤監聽
			@Override
			public void keyPressed(KeyEvent e) {
				/****** p1 ******/
				if (e.getKeyCode() == KeyEvent.VK_UP) {// 若按下方向鍵上
					if (direction != 2) {// 當往下時不能往上
						direction = 1;// 蛇往上
						speed = 200 - snake_game.treat_num * 10;// 隨著吃到的能量球愈多速度愈快
						if (speed <= 0) {
							speed = 0;// 最快就是0
						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {// 若按下方向鍵下
					if (direction != 1) {// 當往上時不能往下
						direction = 2;// 蛇往下
						speed = 200 - snake_game.treat_num * 10;// 隨著吃到的能量球愈多速度愈快
						if (speed <= 0) {
							speed = 0;
						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {// 若按下方向鍵右
					if (direction != 4) {// 當往左時不能往右
						direction = 3;// 蛇往右
						speed = 200 - snake_game.treat_num * 10;// 隨著吃到的能量球愈多速度愈快
						if (speed <= 0) {
							speed = 0;
						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (direction != 3) {// 當往左時不能往右
						direction = 4;// 蛇往左
						speed = 200 - snake_game.treat_num * 10;// 隨著吃到的能量球愈多速度愈快
						if (speed <= 0) {
							speed = 0;
						}
					}
				}
				/****** p2 ******/
				if (e.getKeyCode() == KeyEvent.VK_W) {// 若按下方向鍵上
					if (direction_2 != 6) {// 當往下時不能往上
						direction_2 = 5;// 蛇往上
						speed_2 = 200 - snake_game.treat_num_2 * 10;// 隨著吃到的能量球愈多速度愈快
						if (speed_2 <= 0) {
							speed_2 = 0;
						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {// 若按下方向鍵下
					if (direction_2 != 5) {// 當往上時不能往下
						direction_2 = 6;// 蛇往下
						speed_2 = 200 - snake_game.treat_num_2 * 10;// 隨著吃到的能量球愈多速度愈快
						if (speed_2 <= 0) {
							speed_2 = 0;
						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_D) {// 若按下方向鍵右
					if (direction_2 != 8) {// 當往左時不能往右
						direction_2 = 7;// 蛇往右
						speed_2 = 200 - snake_game.treat_num_2 * 10;// 隨著吃到的能量球愈多速度愈快
						if (speed_2 <= 0) {
							speed_2 = 0;
						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_A) {
					if (direction_2 != 7) {// 當往左時不能往右
						direction_2 = 8;// 蛇往左
						speed_2 = 200 - snake_game.treat_num_2 * 10;// 隨著吃到的能量球愈多速度愈快
						if (speed_2 <= 0) {
							speed_2 = 0;
						}
					}
				}

			}

			public void keyReleased(KeyEvent e) {
				/*** 長按可加速 ***/
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
				/*** 當按下遊戲開始 ***/
				if (startgame == false) {
					startgame = true;// 遊戲開始
					direction = 4;// p1初始方向
					direction_2 = 8;// p2初始方向
					speed = 0;// p1初始速度
					speed_2 = 0;// p2初始速度
					treat_num = 0;// p1能量球初始數量
					treat_num_2 = 0;// 2能量球初始數量
					wall.clear();// 邊界刷新
					snake.clear();// p1刷新
					snake_2.clear();// p2刷新
					treat.clear();// 能量球刷新
					energy = 5000;// p1初始能量值
					energy_2 = 5000;// p2初始能量值
					win_1 = true;
					win_2 = true;
					/** 加入p1 **/
					control newgame = new control();
					newgame.game_init();
					/** 加入p2 **/
					control_2 newgame_2 = new control_2();
					newgame_2.game_init();
					/** 開啟新遊戲 **/
					snake_double.newgame();
					/** 畫面刷新 **/
					repaint();
				}
				if (gameover == true) {// 當結束遊戲，重新判斷輸贏與是否開始
					startgame = false;
					gameover = false;
				}
			}
		});

	}

	/*** 畫出介面 ***/
	public void paintComponent(Graphics gg) {
		super.paintComponent(gg);
		Graphics2D g = (Graphics2D) gg;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (startgame == true) {
			drawSnake(g);// 畫出蛇
			drawGrid(g);// 畫出背景
			drawTreats(g);// 畫出能量球
			drawScore(g);// 畫出能量值
		}
		if (startgame == false) {
			drawStartScreen(g);// 畫出開始畫面
		}
		if (gameover == true) {
			drawOverScreen(g);// 劃出結束畫面
		}

	}

	/** 開始畫面 **/
	void drawStartScreen(Graphics2D g) {
		g.setColor(Color.blue);// snake字形顏色
		g.setFont(getFont());// 將顏色加到字形
		g.drawString("Snake", 240, 190);// 設定snake字型大小
		g.setColor(Color.orange);// click to start字形顏色字形顏色
		g.drawString("(click to start)", 150, 240);// 設定click to start字型大小
		repaint();
	}

	/** 結束畫面 **/
	void drawOverScreen(Graphics2D g) {
		g.setColor(Color.blue);// 字形顏色
		g.setFont(getFont());// 將顏色加到字形
		if (snake_game.win_1 == false) {// 設定p1輸時顯示P1 Loss和字型大小
			g.drawString("P1 Loss", 230, 190);
		} else if (snake_game.win_2 == false) {// 設定p2輸時顯示P2 Loss和字型大小
			g.drawString("P2 Loss", 230, 190);
		}

		g.setColor(Color.orange);// click to restart字形顏色字形顏色
		g.drawString("(click to restart)", 150, 240);// 設定click to restart字型大小
		repaint();
	}

	/** 能量值 **/
	void drawScore(Graphics2D g) {
		/** p1能量 **/
		int h = getHeight();// 取得高度
		g.setFont(getFont().deriveFont(Font.BOLD, 18));// 字形與大小
		if (energy <= 0) {// 當能量值小於0字體顏色變紅色
			g.setColor(Color.red);
		} else {
			g.setColor(Color.BLUE);
		}
		g.drawString(format("P1:energy %d", energy), getWidth() - 550, h - 30);// 設定長寬和字
		/** p2能量 **/
		g.setFont(getFont().deriveFont(Font.BOLD, 18));// 字形與大小
		if (energy_2 <= 0) {// 當能量值小於0字體顏色變紅色
			g.setColor(Color.red);
		} else {
			g.setColor(Color.black);
		}
		g.drawString(format("P2:energy %d", energy_2), getWidth() - 200, h - 30);// 設定長寬和字
	}

	/** 蛇 **/
	void drawSnake(Graphics2D g) {
		/** p1蛇 **/
		g.setColor(Color.blue);// 蛇身體顏色
		for (Point p : snake) {
			g.fillRect(p.x * 10, p.y * 10, 10, 10);// 填滿setcolor所指定的顏色,(x座標，y座標，長，寬)
			repaint();
		}
		if (energy <= 300) {// 當能量小於300頭會變紅色
			g.setColor(Color.red);
		} else {
			g.setColor(Color.orange);
		}

		Point head = snake.get(0);
		g.fillRect(head.x * 10, head.y * 10, 10, 10);// 填滿setcolor所指定的顏色,(x座標，y座標，長，寬)
		/** p2蛇 **/
		g.setColor(Color.black);// 蛇身體顏色
		for (Point p : snake_2) {
			g.fillRect(p.x * 10, p.y * 10, 10, 10);// 填滿setcolor所指定的顏色,(x座標，y座標，長，寬)
			repaint();
		}
		if (energy_2 <= 300) {// 當能量小於300頭會變紅色
			g.setColor(Color.red);
		} else {
			g.setColor(Color.orange);
		}

		Point head_2 = snake_2.get(0);
		g.fillRect(head_2.x * 10, head_2.y * 10, 10, 10);
	}

	/** 邊界 **/
	void drawGrid(Graphics2D g) {
		g.setColor(Color.lightGray);// 邊界顏色

		for (int i = 0; i < 216; i++) {
			g.fillRect(snake_game.wall.get(i).x * 10, snake_game.wall.get(i).y * 10, 10, 10);// 邊界範圍
		}
	}

	/** 能量球 **/
	void drawTreats(Graphics2D g) {
		g.setColor(Color.green);// 能量球顏色
		for (Point p : treat)
			g.fillRect(p.x * 10, p.y * 10, 10, 10);// 填滿setcolor所指定的顏色,(x座標，y座標，長，寬)
	}
}

/** 能量球的控制 **/
class treat extends Thread {
	/** 能量球座標 **/
	int row;
	int col;

	public void addTreat() {
		row = (int) (Math.random() * 62 + 1);// 取1~62亂數
		col = (int) (Math.random() * 42 + 1);// 取1~42亂數
		if (snake_game.startgame == true) {
			if (snake_game.treat.size() < 2) {// 最多只有2個能量球在場上
				snake_game.treat.add(new Point(row, col));
				try {
					Thread.sleep(1000);// 被吃掉後廷一秒才會跑出來下一個能量球
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

	public void run() {
		while (snake_game.gameover != true) {
			addTreat();// 開始增加能量球
		}
	}
}

/** p1蛇的控制 **/
class control extends Thread {
	/**蛇的座標**/
	int row = 60;
	int col = 30;

	boolean start = false;
	/**介面初始設置**/
	void game_init() {

		for (int i = 0; i <= 6; i++) {
			snake_game.snake.add(new Point((row / 2) + i, col / 2));//設定起始座標

		}
		border();//邊界初始設置
	}
	/**每走一個能量就會減少25**/
	public void energyUsed() {

		snake_game.energy -= 25;
		/*當能量小於0就輸了*/
		if (snake_game.energy <= 0) {
			snake_game.gameover = true;
			snake_game.win_1 = false;
		}
	}

	public void hitsnake() {
		// 撞到別人
		if (snake_game.snake_2.contains(snake_game.snake.get(0))) {// 撞到p2
			snake_game.gameover = true;
			snake_game.win_1 = false;
		}

	}
	/**邊界的建立**/
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
	/**蛇的控制**/
	public void snake_move() {
		Point head = null;
		int total_speed;
		/**當蛇的速度小於0，其值就會維持0**/         
		if ((300 - snake_game.treat_num * 10) - (snake_game.speed) <= 0) {
			total_speed = 0;
		} else {
			total_speed = (300 - snake_game.treat_num * 10) - (snake_game.speed);
		}
		if (snake_game.startgame == true) {
			try {
				Thread.sleep(total_speed);// 蛇的速度隨著分數愈高就愈快
			} catch (InterruptedException e) {
				return;
			}
			/***當蛇往上走***/
			if (snake_game.direction == 1) { 
				snake_game.snake.add(0, new Point(snake_game.snake.get(0).x, snake_game.snake.get(0).y - 1));//在紀錄往上一格處的座標存進陣列
				snake_game.snake.remove(snake_game.snake.size() - 1);//刪除陣列中最後一格的座標
				head = snake_game.snake.get(0);//紀錄蛇的頭的座標
				
				for (Point p : snake_game.treat) {
					if (p.x == snake_game.snake.get(0).x && p.y == snake_game.snake.get(0).y) {/*若吃到能量球*/
						snake_game.snake.add(0, new Point(snake_game.snake.get(0).x, snake_game.snake.get(0).y - 1));//就紀錄蛇尾巴後面一個的座標加到陣列中
						snake_game.treat.remove(p);//刪除被吃掉的能量球
						snake_game.treat_num++;//紀錄能量球數量
						snake_game.energy = 5000;//恢復能量值
					}
				}
			}
			/***當蛇往下走***/
			if (snake_game.direction == 2) {
				snake_game.snake.add(0, new Point(snake_game.snake.get(0).x, snake_game.snake.get(0).y + 1));//在紀錄往上一格處的座標存進陣列
				snake_game.snake.remove(snake_game.snake.size() - 1);//刪除陣列中最後一格的座標
				head = snake_game.snake.get(0);//紀錄蛇的頭的座標
				for (Point p : snake_game.treat) {
					if (p.x == snake_game.snake.get(0).x && p.y == snake_game.snake.get(0).y) {/*若吃到能量球*/
						snake_game.snake.add(0, new Point(snake_game.snake.get(0).x, snake_game.snake.get(0).y + 1));//就紀錄蛇尾巴後面一個的座標加到陣列中
						snake_game.treat.remove(p);//刪除被吃掉的能量球
						snake_game.treat_num++;//紀錄能量球數量
						snake_game.energy = 5000;//恢復能量值
					}
				}
			}
			/***當蛇往右走***/
			if (snake_game.direction == 3) {
				snake_game.snake.add(0, new Point(snake_game.snake.get(0).x + 1, snake_game.snake.get(0).y));//在紀錄往上一格處的座標存進陣列
				snake_game.snake.remove(snake_game.snake.size() - 1);//刪除陣列中最後一格的座標
				head = snake_game.snake.get(0);//紀錄蛇的頭的座標
				for (Point p : snake_game.treat) {
					if (p.x == snake_game.snake.get(0).x && p.y == snake_game.snake.get(0).y) {/*若吃到能量球*/
						snake_game.snake.add(0, new Point(snake_game.snake.get(0).x + 1, snake_game.snake.get(0).y));//就紀錄蛇尾巴後面一個的座標加到陣列中
						snake_game.treat.remove(p);//刪除被吃掉的能量球
						snake_game.treat_num++;//紀錄能量球數量
						snake_game.energy = 5000;//恢復能量值
					}
				}
			}
			/***當蛇往左走***/
			if (snake_game.direction == 4) {
				snake_game.snake.add(0, new Point(snake_game.snake.get(0).x - 1, snake_game.snake.get(0).y));//在紀錄往上一格處的座標存進陣列
				snake_game.snake.remove(snake_game.snake.size() - 1);//刪除陣列中最後一格的座標
				head = snake_game.snake.get(0);//紀錄蛇的頭的座標
				for (Point p : snake_game.treat) {
					if (p.x == snake_game.snake.get(0).x && p.y == snake_game.snake.get(0).y) {/*若吃到能量球*/
						snake_game.snake.add(0, new Point(snake_game.snake.get(0).x - 1, snake_game.snake.get(0).y));//就紀錄蛇尾巴後面一個的座標加到陣列中
						snake_game.treat.remove(p);//刪除被吃掉的能量球
						snake_game.treat_num++;//紀錄能量球數量
						snake_game.energy = 5000;//恢復能量值
					}
				}
			}

			if (snake_game.wall.contains(snake_game.snake.get(0))) {// 若撞到牆壁就結束遊戲
				snake_game.gameover = true;
				snake_game.win_1 = false;
			}
			for (int i = 2; i < snake_game.snake.size(); i++) {// 若撞到自己就結束遊戲
				if (head.x == snake_game.snake.get(i).x && head.y == snake_game.snake.get(i).y) {
					snake_game.gameover = true;
					snake_game.win_1 = false;
				}
			}
		}
	}
	/**執行p1蛇的執行緒**/
	public void run() {
		while (snake_game.gameover != true) {
			energyUsed();
			snake_move();
			hitsnake();
		}
	}
}
/** p2蛇的控制 **/
class control_2 extends Thread {
	/**蛇的座標**/
	int row = 30;
	int col = 30;

	boolean start = false;
	/**介面初始設置**/
	void game_init() {

		for (int i = 0; i <= 6; i++) {
			snake_game.snake_2.add(new Point((row / 2) + i, col / 2));//設定起始座標
		}
	}
	/**每走一個能量就會減少25**/
	public void energyUsed() {
		/*當能量小於0就輸了*/
		snake_game.energy_2 -= 25;
		if (snake_game.energy_2 <= 0) {
			snake_game.gameover = true;
			snake_game.win_2 = false;
		}
	}

	public void hitsnake() {
		
		if (snake_game.snake.contains(snake_game.snake_2.get(0))) {// 撞到p1就輸了
			snake_game.gameover = true;
			snake_game.win_2 = false;
		}

	}
	/**蛇的控制**/
	public void snake_move() {
		Point head = null;
		int total_speed;
		/**當蛇的速度小於0，其值就會維持0**/ 
		if (snake_game.startgame == true) {

			if ((300 - snake_game.treat_num_2 * 10) - (snake_game.speed_2) <= 0) {
				total_speed = 0;
			} else {
				total_speed = (300 - snake_game.treat_num_2 * 10) - (snake_game.speed_2);
			}
			try {
				Thread.sleep(total_speed);// 蛇的速度隨著分數愈高就愈快
			} catch (InterruptedException e) {
				return;
			}
			/***當蛇往上走***/
			if (snake_game.direction_2 == 5) {
				snake_game.snake_2.add(0, new Point(snake_game.snake_2.get(0).x, snake_game.snake_2.get(0).y - 1));//在紀錄往上一格處的座標存進陣列
				snake_game.snake_2.remove(snake_game.snake_2.size() - 1);//刪除陣列中最後一格的座標
				head = snake_game.snake_2.get(0);//紀錄蛇的頭的座標
				for (Point p : snake_game.treat) {
					if (p.x == snake_game.snake_2.get(0).x && p.y == snake_game.snake_2.get(0).y) {/*若吃到能量球*/
						snake_game.snake_2.add(0,new Point(snake_game.snake_2.get(0).x, snake_game.snake_2.get(0).y - 1));//就紀錄蛇尾巴後面一個的座標加到陣列中
								
						snake_game.treat.remove(p);//刪除被吃掉的能量球
						snake_game.treat_num_2++;//紀錄能量球數量
						snake_game.energy_2 = 5000;//恢復能量值
					}
				}
			}
			/***當蛇往下走***/
			if (snake_game.direction_2 == 6) {
				snake_game.snake_2.add(0, new Point(snake_game.snake_2.get(0).x, snake_game.snake_2.get(0).y + 1));//在紀錄往上一格處的座標存進陣列
				snake_game.snake_2.remove(snake_game.snake_2.size() - 1);//刪除陣列中最後一格的座標
				head = snake_game.snake_2.get(0);//紀錄蛇的頭的座標
				for (Point p : snake_game.treat) {
					if (p.x == snake_game.snake_2.get(0).x && p.y == snake_game.snake_2.get(0).y) {/*若吃到能量球*/
						snake_game.snake_2.add(0,
								new Point(snake_game.snake_2.get(0).x, snake_game.snake_2.get(0).y + 1));//就紀錄蛇尾巴後面一個的座標加到陣列中
						snake_game.treat.remove(p);//刪除被吃掉的能量球
						snake_game.treat_num_2++;//紀錄能量球數量
						snake_game.energy_2 = 5000;//恢復能量值
					}
				}
			}
			/***當蛇往右走***/
			if (snake_game.direction_2 == 7) {
				snake_game.snake_2.add(0, new Point(snake_game.snake_2.get(0).x + 1, snake_game.snake_2.get(0).y));//在紀錄往上一格處的座標存進陣列
				snake_game.snake_2.remove(snake_game.snake_2.size() - 1);//刪除陣列中最後一格的座標
				head = snake_game.snake_2.get(0);//紀錄蛇的頭的座標
				for (Point p : snake_game.treat) {
					if (p.x == snake_game.snake_2.get(0).x && p.y == snake_game.snake_2.get(0).y) {/*若吃到能量球*/
						snake_game.snake_2.add(0,
								new Point(snake_game.snake_2.get(0).x + 1, snake_game.snake_2.get(0).y));//就紀錄蛇尾巴後面一個的座標加到陣列中
						snake_game.treat.remove(p);//刪除被吃掉的能量球
						snake_game.treat_num_2++;//紀錄能量球數量
						snake_game.energy_2 = 5000;//恢復能量值
					}
				}
			}
			/***當蛇往左走***/
			if (snake_game.direction_2 == 8) {
				snake_game.snake_2.add(0, new Point(snake_game.snake_2.get(0).x - 1, snake_game.snake_2.get(0).y));//在紀錄往上一格處的座標存進陣列
				snake_game.snake_2.remove(snake_game.snake_2.size() - 1);//刪除陣列中最後一格的座標
				head = snake_game.snake_2.get(0);//紀錄蛇的頭的座標
				for (Point p : snake_game.treat) {
					if (p.x == snake_game.snake_2.get(0).x && p.y == snake_game.snake_2.get(0).y) {/*若吃到能量球*/
						snake_game.snake_2.add(0,
								new Point(snake_game.snake_2.get(0).x - 1, snake_game.snake_2.get(0).y));//就紀錄蛇尾巴後面一個的座標加到陣列中
						snake_game.treat.remove(p);//刪除被吃掉的能量球
						snake_game.treat_num_2++;//紀錄能量球數量
						snake_game.energy_2 = 5000;//恢復能量值
					}
				}
			}

			if (snake_game.wall.contains(snake_game.snake_2.get(0))) {// 若撞到牆壁就結束遊戲
				snake_game.gameover = true;
				snake_game.win_2 = false;

			}
			for (int i = 2; i < snake_game.snake_2.size(); i++) {// 若撞到自己就結束遊戲
				if (head.x == snake_game.snake_2.get(i).x && head.y == snake_game.snake_2.get(i).y) {
					snake_game.gameover = true;
					snake_game.win_2 = false;
				}
			}
		}
	}
	/**執行p2蛇的執行緒**/
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
		/**嫁入3個執行緒，分別是控制能量球、p1蛇、p2蛇***/
		control a = new control();
		treat b = new treat();
		control_2 c = new control_2();
		a.start();
		b.start();
		c.start();
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();//創一個新的介面

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Snake");//設定標題
		f.setResizable(false);
		f.add(new snake_game(), BorderLayout.CENTER);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}

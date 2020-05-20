import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


import java.awt.Toolkit;
import java.awt.event.*;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;


class Minesweeper extends JFrame implements ActionListener, ContainerListener {

   
    
 
   
    
    
    
    
    
    
    JButton reset = new JButton("");
    
    
    
    
    
    Random ranr = new Random();
    Random ranc = new Random();
    
    
   
    boolean check = true, starttime = false;
     JButton[][] blocks;
    int[][] countmine;
    int[][] colour;
    ImageIcon[] ic = new ImageIcon[14];
    JPanel panelb = new JPanel();
    JPanel panelmt = new JPanel();
  JTextField num, time;
    int sing, x, y,v1,v2,xb,yb;
    int ayar1 = 1;
    int settings = 11;
    int num1 = 0;
    Point framelocation;
    Stopwatch sw;
    MouseHendeler mh;
    Point p;
    
   int[] r = {-1,   -1,   -1,  0, 1, 1, 1,  0};
    int[] c = {-1, 0, 1, 1, 1, 0, -1, -1};
    Minesweeper() 
    {
        super("Minesweeper");
        setLocation(500, 400);
        setIconImage(Toolkit.getDefaultToolkit().getImage("iconlar/1.png"));
        setic();
        setpanel(1, 0, 0, 0);
        setmanue();

        
        
        
        
        
        
        
        
        sw = new Stopwatch();

        reset.addActionListener(new ActionListener() {

            
            
            public void actionPerformed(ActionEvent ae) {
                try {
                    sw.stop();
                    setpanel(ayar1, xb, yb, settings);
                } catch (Exception ex) {
                    setpanel(ayar1, xb, yb, settings);
                }
                reset();

            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        show();
    }

    public void reset() {
        check = true;
        starttime = false;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                colour[i][j] = 'w';
            }
        }
    }

    
    
    public void setpanel(int level, int setr, int setc, int setm) {
        int xb1 = 0;
        int yb1 = 0;
        if (level == 1) {
            xb1 = 300;
            yb1 = 400;
            x = 10;
            y = 10;
            sing = 10;
        } else if (level == 2) {
            xb1 = 320;
            yb1 = 416;
            x = 16;
            y = 16;
            sing = 40;
        } else if (level == 3) {
            xb1 = 620;
            yb1 = 600;
            x = 16;
            y = 30;
            sing = 99;
        } else if (level == 4) {
            xb1 = (20 * setc);
            yb1 = (24 * setr);
            x = setr;
            y = setc;
            sing = setm;
        }

        xb = x;
        yb = y;
        settings = sing;

        setSize(xb1, yb1);
        setResizable(false);
        num1 = sing;
        p = this.getLocation();

        blocks = new JButton[x][y];
        countmine = new int[x][y];
        colour = new int[x][y];
        mh = new MouseHendeler();

        getContentPane().removeAll();
        panelb.removeAll();
        
        num = new JTextField("" + sing, 3);
        num.setEditable(false);
        num.setFont(new Font("DigtalFont.TTF", Font.BOLD, 25));
        num.setBackground(Color.BLACK);
        num.setForeground(Color.RED);
        num.setBorder(BorderFactory.createLoweredBevelBorder());
       
        time = new JTextField("000", 3);
        time.setEditable(true);
        time.setFont(new Font("DigtalFont.TTF", Font.BOLD, 25));
        time.setBackground(Color.BLACK);
        time.setForeground(Color.RED);
        time.setBorder(BorderFactory.createLoweredBevelBorder());

        reset.setIcon(ic[11]);
        reset.setBorder(BorderFactory.createLoweredBevelBorder());

        panelmt.removeAll();
        panelmt.setLayout(new BorderLayout());
        panelmt.add(num, BorderLayout.WEST);
        panelmt.add(reset, BorderLayout.CENTER);
        panelmt.add(time, BorderLayout.EAST);
        panelmt.setBorder(BorderFactory.createLoweredBevelBorder());

        panelb.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), BorderFactory.createLoweredBevelBorder()));
        panelb.setPreferredSize(new Dimension(xb1, yb1));
        panelb.setLayout(new GridLayout(0, y));
        panelb.addContainerListener(this);

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                blocks[i][j] = new JButton("");

                
                
                
                blocks[i][j].addMouseListener(mh);

                panelb.add(blocks[i][j]);

            }
        }
        reset();

        panelb.revalidate();
        panelb.repaint();
        //getcontentpane().setOpaque(true);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().addContainerListener(this);
        //getContentPane().revalidate();
        getContentPane().repaint();
        getContentPane().add(panelb, BorderLayout.CENTER);
        getContentPane().add(panelmt, BorderLayout.NORTH);
        setVisible(true);
    }

    public void setmanue() {
//menüler

        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);

        JMenu game = new JMenu("GAME");

        JMenuItem menuitem = new JMenuItem("New Game");
        game.add(menuitem);
        game.addSeparator();
        final JCheckBoxMenuItem beginner = new JCheckBoxMenuItem("Begineer");
        game.add(beginner);

        final JCheckBoxMenuItem intermediate = new JCheckBoxMenuItem("Intermediate");
        game.add(intermediate);
        final JCheckBoxMenuItem expart = new JCheckBoxMenuItem("Expart");
        game.add(expart);
        final JCheckBoxMenuItem custom = new JCheckBoxMenuItem("Custom");
        game.add(custom);
        
        game.addSeparator();
        final JMenuItem exit = new JMenuItem("Exit");
        game.add(exit);
        final JMenu help = new JMenu("Help");

        final JMenuItem helpitem = new JMenuItem("Yardım");
        help.add(helpitem);

        bar.add(game);
        bar.add(help);

        ButtonGroup status = new ButtonGroup();

        menuitem.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setpanel(1, 0, 0, 0);
                    }
                });
        beginner.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        panelb.removeAll();
                        reset();
                        setpanel(1, 0, 0, 0);
                        panelb.revalidate();
                        panelb.repaint();
                        beginner.setSelected(true);
                        ayar1 = 1;
                    }
                });
        intermediate.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        panelb.removeAll();
                        reset();
                        setpanel(2, 0, 0, 0);
                        panelb.revalidate();
                        panelb.repaint();
                        intermediate.setSelected(true);
                        ayar1 = 2;
                    }
                });
        expart.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        panelb.removeAll();
                        reset();
                        setpanel(3, 0, 0, 0);
                        panelb.revalidate();
                        panelb.repaint();
                        expart.setSelected(true);
                        ayar1 = 3;
                    }
                });

      custom.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                        Customizetion cus = new Customizetion();
                        reset();
                        panelb.revalidate();
                        panelb.repaint();

                        custom.setSelected(true);
                        ayar1 = 4;
            }
        });
  
        exit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        helpitem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, " Anlatmaya gerek yok");

            }
        });

        status.add(beginner);
        status.add(intermediate);
        status.add(expart);
        status.add(custom);

    }

    public void componentAdded(ContainerEvent ce) {
    }

    public void componentRemoved(ContainerEvent ce) {
    }

    public void actionPerformed(ActionEvent ae) {
    }

    class MouseHendeler extends MouseAdapter {

        public void mouseClicked(MouseEvent me) {
            if (check == true) {
                for (int i = 0; i < x; i++) {
                    for (int j = 0; j < y; j++) {
                        if (me.getSource() == blocks[i][j]) {
                            v1 = i;
                            v2 = j;
                            i = x;
                            break;
                        }
                    }
                }

                setmine();
                calculation();
                check = false;

            }

            showvalue(me);
            winner();

            if (starttime == false) {
                sw.Start();
                starttime = true;
            }

        }
    }

    
    public void showvalue(MouseEvent e) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {

                if (e.getSource() == blocks[i][j]) {
                    if (e.isMetaDown() == false) {
                        if (blocks[i][j].getIcon() == ic[10]) {
                            if (num1 < sing) {
                                num1++;
                            }
                            num.setText("" + num1);
                        }

                        if (countmine[i][j] == -1) {
                            for (int k = 0; k < x; k++) {
                                for (int l = 0; l < y; l++) {
                                    if (countmine[k][l] == -1) {

                                        blocks[k][l].setIcon(ic[9]);
                                        blocks[k][l].removeMouseListener(mh);
             
                                    }
                                    blocks[k][l].removeMouseListener(mh);
                                }
                            }
                            sw.stop();
                            reset.setIcon(ic[12]);
                        } else if (countmine[i][j] == 0) {
                            dfs(i, j);
                        } else {
                            blocks[i][j].setIcon(ic[countmine[i][j]]);
                            colour[i][j] = 'b';
                            break;
                        }
                    } else {
                        if (y != 0) {
                            if (blocks[i][j].getIcon() == null) {
                                num1--;
                                blocks[i][j].setIcon(ic[10]);
                            }
                            num.setText("" + num1);
                        }

                    }
                }

            }
        }

    }
public void winner() {
        int q = 0;
        for (int k = 0; k < x; k++) {
            for (int l = 0; l < y; l++) {
                if (colour[k][l] == 'w') {
                    q = 1;
                }
            }
        }

        if (q == 0) {
            for (int k = 0; k < x; k++) {
                for (int l = 0; l < y; l++) {
                    blocks[k][l].removeMouseListener(mh);
                }
            }

            sw.stop();
        }
    }

    public void calculation() {
        int row, column;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int value = 0;
                int R, C;
                row = i;
                column = j;
                if (countmine[row][column] != -1) {
                    for (int k = 0; k < 8; k++) {
                        R = row + r[k];
                        C = column + c[k];

                        if (R >= 0 && C >= 0 && R < x && C < y) {
                            if (countmine[R][C] == -1) {
                                value++;
                            }

                        }

                    }
                    countmine[row][column] = value;

                }
            }
        }
    }

    

    public void setmine() {
        int row = 0, col = 0;
        Boolean[][] flag = new Boolean[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                flag[i][j] = true;
                countmine[i][j] = 0;
            }
        }

        flag[v1][v2] = false;
        colour[v1][v2] = 'b';

        for (int i = 0; i < sing; i++) {
            row = ranr.nextInt(x);
            col = ranc.nextInt(y);

            if (flag[row][col] == true) {

                countmine[row][col] = -1;
                colour[row][col] = 'b';
                flag[row][col] = false;
            } else {
                i--;
            }
        }
    }
public void dfs(int row, int col) {
        int R, C;
        colour[row][col] = 'b';
        blocks[row][col].setBackground(Color.GRAY);
        blocks[row][col].setIcon(ic[countmine[row][col]]);
        for (int i = 0; i < 8; i++) {
            R = row + r[i];
            C = col + c[i];
            if (R >= 0 && R < x && C >= 0 && C < y && colour[R][C] == 'w') {
                if (countmine[R][C] == 0) {
                    dfs(R, C);
                } else {
                    blocks[R][C].setIcon(ic[countmine[R][C]]);
                    colour[R][C] = 'b';

                }
            }

        }
    }
//icon yerleri
    public void setic() {
        String name;

        for (int i = 0; i <= 8; i++) {
            name = i + ".gif";
            ic[i] = new ImageIcon(name);
        }
        ic[9] = new ImageIcon("iconlar/3.gif");
        ic[10] = new ImageIcon("iconlar/flag.gif");
        ic[11] = new ImageIcon("iconlar/4.gif");
        ic[12] = new ImageIcon("iconlar/2.gif");
    }

    public class Stopwatch extends JFrame implements Runnable {

        long startTime;
        Thread updater;
        boolean isRunning = false;
        long a = 0;
        Runnable displayUpdater = new Runnable() {

            public void run() {
                displayElapsedTime(a);
                a++;
            }
        };

        public void stop() {
            long elapsed = a;
            isRunning = false;
            try {
                updater.join();
            } catch (InterruptedException ie) {
            }
            displayElapsedTime(elapsed);
            a = 0;
        }
public void dfs(int row, int col) {
        int R, C;
        colour[row][col] = 'b';
        blocks[row][col].setBackground(Color.GRAY);
        blocks[row][col].setIcon(ic[countmine[row][col]]);
        for (int i = 0; i < 8; i++) {
            R = row + r[i];
            C = col + c[i];
            if (R >= 0 && R < x && C >= 0 && C < y && colour[R][C] == 'w') {
                if (countmine[R][C] == 0) {
                    dfs(R, C);
                } else {
                    blocks[R][C].setIcon(ic[countmine[R][C]]);
                    colour[R][C] = 'b';

                }
            }

        }
    }
 public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b1) {
                try {
                    cr = Integer.parseInt(t1.getText());
                    cc = Integer.parseInt(t2.getText());
                    cm = Integer.parseInt(t3.getText());

                    setpanel(4, row(), column(), mine());
                    dispose();
                } catch (Exception any) {
                    JOptionPane.showMessageDialog(this, "Wrong");
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                }
                
            }

            if (e.getSource() == b2) {
                dispose();
            }
        }

        private void displayElapsedTime(long elapsedTime) {

            if (elapsedTime >= 0 && elapsedTime < 9) {
                time.setText("00" + elapsedTime);
            } else if (elapsedTime > 9 && elapsedTime < 99) {
                time.setText("0" + elapsedTime);
            } else if (elapsedTime > 99 && elapsedTime < 999) {
                time.setText("" + elapsedTime);
            }
        }

        public void run() {
            try {
                while (isRunning) {
                    SwingUtilities.invokeAndWait(displayUpdater);
                    Thread.sleep(500);
                }
            } catch (java.lang.reflect.InvocationTargetException ite) {
                ite.printStackTrace(System.err);
            } catch (InterruptedException ie) {
            }
        }

        public void Start() {
            startTime = System.currentTimeMillis();
            isRunning = true;
            updater = new Thread(this);
            updater.start();
        }
    }

    class Customizetion extends JFrame implements ActionListener {

        JTextField t1, t2, t3;
        JLabel lb1, lb2, lb3;
        JButton b1, b2;
        int cr, cc, cm, actionc = 0;

        Customizetion() {
            super("CUSTOM");
            setSize(180, 250);
            setResizable(false);
            setLocation(p);
            setIconImage(Toolkit.getDefaultToolkit().getImage("icomlar/4.png"));

            t1 = new JTextField();
            t2 = new JTextField();
            t3 = new JTextField();

            b1 = new JButton("OK");
            b2 = new JButton("CANCEL");

            b1.addActionListener(this);
            b2.addActionListener(this);

            lb1 = new JLabel("Row",JLabel.RIGHT);
            lb2 = new JLabel("Column",JLabel.RIGHT);
            lb3 = new JLabel("Mine",JLabel.RIGHT);

            getContentPane().setLayout(new GridLayout(0, 2));

            getContentPane().add(lb1);
            getContentPane().add(t1);
            getContentPane().add(lb2);
            getContentPane().add(t2);
            getContentPane().add(lb3);
            getContentPane().add(t3);

            getContentPane().add(b1);
            getContentPane().add(b2);

            show();
        }

       
 public int mine() {
            if (cm > ((row() - 1) * (column() - 1))) {
                return ((row() - 1) * (column() - 1));
            } else if (cm < 10) {
                return 10;
            } else {
                return cm;
           
            }
        }
 
        public int row() {
            if (cr > 30) {
                return 30;
            } else if (cr < 10) {
                return 10;
            } else {
                return cr;
            }
        }
        public int column() {
            if (cc > 30) {
                return 30;
            } else if (cc < 10) {
                return 10;
            } else {
                return cc;
            }
        }

       
        
        
        
        
        
   
    
    
    
    
    
    
    }
}

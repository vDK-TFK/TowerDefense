
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PantallaJuego extends javax.swing.JFrame implements ActionListener {

    FondoPanel fondo = new FondoPanel();

    private final int PANEL_WIDTH = 600;
    private final int PANEL_HEIGHT = 600;
    private Image backGroundImage;

    private int xVelocity = 5;
    private int yVelocity = 1;
    private int x = 0;
    private int y = 0;
    private int numeroRonda = 1;
    private boolean juegoIniciado = false;
    private boolean CPU_WINNER = false;
    private boolean JUGADOR_WINNER = false;

    private Timer mTimer = new Timer();

    private int minutos = 0;
    private int segundos = 0;
    private int milisegundos = 0;

    TipoPersonaje arquero;
    TipoPersonaje caballero;
    TipoPersonaje mago;
    Tropa tTropa;
    Jugador jugador;
    Castillo castilloJugador;
    Castillo castilloCPU;
    NodoCo NodoPersonaje;
    CPU computadora;
    Timer t = new Timer();

    public PantallaJuego() {
        initComponents();
        setLocationRelativeTo(null);

        //Creacion tipos
        arquero = new TipoPersonaje(1, "Arquero", 2);
        caballero = new TipoPersonaje(2, "Caballero", 3);
        mago = new TipoPersonaje(3, "Mago", 1);

        // Creacion de los objetos de tropas
        castilloJugador = new Castillo(10);
        castilloCPU = new Castillo(10);

    }
    
    public void moverTropas() {
        MoverTropasCPU();
        MoverTropasJugador(true, true);
    }

    public void MoverTropasCPU() {
        int posicion_real = 0;
        if (computadora.getTropaSup().getLargo() > 0) {
            for (int i = 0; i < computadora.getTropaSup().getLargo(); i++) {
                Tropa uso = computadora.getTropaSup().encuentra(i);

                if (jugador.getTropasSup().getLargo() > 0) {
                    if (uso.getPoscionActual() == jugador.getTropasSup().ultimaPosicion().getPoscionActual()) {
                        if (uso.getTipo().getIdDebilidad() == jugador.getTropasSup().ultimaPosicion().getTipo().getIdTipo()) {
                            computadora.getTropaSup().tropaEliminar();
                        } else {
                            jugador.getTropasSup().tropaEliminar();
                        }
                    }
                }

                switch (uso.getPoscionActual()) {
                    case 4:
                        paint(caminoSuperior4, computadora.getTropaSup().encuentra(i).getCaracter().getImage());
                        posicion_real = 3;
                        break;
                    case 3:
                        paint(caminoSuperior3, computadora.getTropaSup().encuentra(i).getCaracter().getImage());
                        caminoSuperior4.setIcon(null);
                        repaint();
                        posicion_real = 2;
                        break;
                    case 2:
                        paint(caminoSuperior2, computadora.getTropaSup().encuentra(i).getCaracter().getImage());
                        caminoSuperior3.setIcon(null);
                        repaint();
                        posicion_real = 1;
                        break;
                    case 1:
                        paint(caminoSuperior1, computadora.getTropaSup().encuentra(i).getCaracter().getImage());
                        caminoSuperior2.setIcon(null);
                        repaint();
                        posicion_real = 0;
                        break;
                    default:
                        posicion_real = -1;
                        break;
                }

                uso.setPoscionActual(posicion_real);

                if (uso.getPoscionActual() == -1) {
                    jugador.getCasJugador().danoCastillo(uso.getDano());
                    int vidaN = (int) jugador.getCasJugador().getVida();
                    vidaJugadorPB.setValue(vidaN * 10);

                    if (jugador.getCasJugador().castilloDestruido() && !JUGADOR_WINNER) {
                        CPU_WINNER = true;
                        t.cancel();
                        mTimer.cancel();
                        JOptionPane.showMessageDialog(null, "Haz perdido la partida");
                    }
                }
            }
        }

        if (computadora.getTropaInf().getLargo() > 0) {
            for (int i = 0; i < computadora.getTropaInf().getLargo(); i++) {
                Tropa uso = computadora.getTropaInf().encuentra(i);

                if (jugador.getTropaInf().getLargo() > 0) {
                    if (uso.getPoscionActual() == jugador.getTropaInf().ultimaPosicion().getPoscionActual()) {
                        if (uso.getTipo().getIdDebilidad() == jugador.getTropaInf().ultimaPosicion().getTipo().getIdTipo()) {
                            computadora.getTropaInf().tropaEliminar();
                        } else {
                            jugador.getTropaInf().tropaEliminar();
                        }
                    }
                }

                switch (uso.getPoscionActual()) {
                    case 4:
                        paint(caminoInferior4, computadora.getTropaInf().encuentra(i).getCaracter().getImage());
                        posicion_real = 3;
                        break;
                    case 3:
                        paint(caminoInferior3, computadora.getTropaInf().encuentra(i).getCaracter().getImage());
                        caminoInferior4.setIcon(null);
                        repaint();
                        posicion_real = 2;
                        break;
                    case 2:
                        paint(caminoInferior2, computadora.getTropaInf().encuentra(i).getCaracter().getImage());
                        caminoInferior3.setIcon(null);
                        repaint();
                        posicion_real = 1;
                        break;
                    case 1:
                        paint(caminoInferior1, computadora.getTropaInf().encuentra(i).getCaracter().getImage());
                        caminoInferior2.setIcon(null);
                        repaint();
                        posicion_real = 0;
                        break;
                    default:
                        posicion_real = -1;
                        break;
                }

                uso.setPoscionActual(posicion_real);

                if (uso.getPoscionActual() == -1) {
                    jugador.getCasJugador().danoCastillo(uso.getDano());
                    int vidaN = (int) jugador.getCasJugador().getVida();
                    vidaJugadorPB.setValue(vidaN * 10);

                    if (jugador.getCasJugador().castilloDestruido() && !JUGADOR_WINNER) {
                        CPU_WINNER = true;
                        t.cancel();
                        mTimer.cancel();
                        JOptionPane.showMessageDialog(null, "Haz perdido la partida");
                    }
                }
            }
        }
    }

    public void MoverTropasJugador(boolean sup, boolean inf) {
        int posicion_real = 0;
        if (jugador.getTropasSup().getLargo() > 0 && sup) {
            for (int i = 0; i < jugador.getTropasSup().getLargo(); i++) {
                Tropa uso = jugador.getTropasSup().encuentra(i);

                switch (uso.getPoscionActual()) {
                    case 1:
                        paint(caminoSuperior1, jugador.getTropasSup().encuentra(i).getCaracter().getImage());
                        posicion_real = 2;
                        break;
                    case 2:
                        paint(caminoSuperior2, jugador.getTropasSup().encuentra(i).getCaracter().getImage());
                        caminoSuperior1.setIcon(null);
                        repaint();
                        posicion_real = 3;
                        break;
                    case 3:
                        paint(caminoSuperior3, jugador.getTropasSup().encuentra(i).getCaracter().getImage());
                        caminoSuperior2.setIcon(null);
                        repaint();
                        posicion_real = 4;
                        break;
                    case 4:
                        paint(caminoSuperior4, jugador.getTropasSup().encuentra(i).getCaracter().getImage());
                        caminoSuperior3.setIcon(null);
                        repaint();
                        posicion_real = 5;
                        break;

                    default:
                        posicion_real = 6;
                        break;
                }

                if (computadora.getTropaSup().getLargo() > 0) {
                    if (uso.getPoscionActual() == computadora.getTropaSup().ultimaPosicion().getPoscionActual()) {
                        if (uso.getTipo().getIdDebilidad() == computadora.getTropaSup().ultimaPosicion().getTipo().getIdTipo()) {
                            jugador.getTropasSup().tropaEliminar();
                        } else {
                            computadora.getTropaSup().tropaEliminar();
                        }
                    }
                }

                uso.setPoscionActual(posicion_real);

                if (uso.getPoscionActual() == 6) {
                    computadora.getCasCPU().danoCastillo(uso.getDano());
                    int vidaN = (int) computadora.getCasCPU().getVida();
                    vidaCPUPB.setValue(vidaN * 10);

                    if (computadora.getCasCPU().castilloDestruido() && !CPU_WINNER) {
                        JUGADOR_WINNER = true;
                        t.cancel();
                        mTimer.cancel();
                        JOptionPane.showMessageDialog(null, "Haz ganado la partidad felicitaciones");
                    }
                }
            }
        }

        if (jugador.getTropaInf().getLargo() > 0 && inf) {
            for (int i = 0; i < jugador.getTropaInf().getLargo(); i++) {
                Tropa uso = jugador.getTropaInf().encuentra(i);

                switch (uso.getPoscionActual()) {
                    case 1:
                        paint(caminoInferior1, jugador.getTropaInf().encuentra(i).getCaracter().getImage());
                        posicion_real = 2;
                        break;
                    case 2:
                        paint(caminoInferior2, jugador.getTropaInf().encuentra(i).getCaracter().getImage());
                        caminoInferior1.setIcon(null);
                        repaint();
                        posicion_real = 3;
                        break;
                    case 3:
                        paint(caminoInferior3, jugador.getTropaInf().encuentra(i).getCaracter().getImage());
                        caminoInferior2.setIcon(null);
                        repaint();
                        posicion_real = 4;
                        break;
                    case 4:
                        paint(caminoInferior4, jugador.getTropaInf().encuentra(i).getCaracter().getImage());
                        caminoInferior3.setIcon(null);
                        repaint();
                        posicion_real = 5;
                        break;
                    default:
                        posicion_real = 6;
                        break;
                }

                if (computadora.getTropaInf().getLargo() > 0) {
                    if (uso.getPoscionActual() == computadora.getTropaInf().ultimaPosicion().getPoscionActual()) {
                        if (uso.getTipo().getIdDebilidad() == computadora.getTropaInf().ultimaPosicion().getTipo().getIdTipo()) {
                            jugador.getTropaInf().tropaEliminar();
                        } else {
                            computadora.getTropaInf().tropaEliminar();
                        }
                    }
                }

                uso.setPoscionActual(posicion_real);

                if (uso.getPoscionActual() == 6) {
                    computadora.getCasCPU().danoCastillo(uso.getDano());
                    int vidaN = (int) computadora.getCasCPU().getVida();
                    vidaCPUPB.setValue(vidaN * 10);

                    if (computadora.getCasCPU().castilloDestruido() && !CPU_WINNER) {
                        JUGADOR_WINNER = true;
                        t.cancel();
                        mTimer.cancel();
                        JOptionPane.showMessageDialog(null, "Haz ganado la partida felicitaciones");
                    }
                }
            }
        }
    }

    public void moverUnidades() {
        int velmil = xVelocity * 1000;

        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                moverTropas();
            }
        };

        t.schedule(tt, 0, velmil);
    }
    
    public void iniciarCronometro(){
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                Cronometro();
            }
        };

        mTimer.schedule(tt, 0, 10);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        board = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        jCheckBox3 = new javax.swing.JCheckBox();
        gamePanel = new javax.swing.JPanel();
        vidaJugadorTxt = new javax.swing.JLabel();
        vidaJugadorPB = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        vidaCPUPB = new javax.swing.JProgressBar();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        unidadesCPUTxt = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        lblCronometro = new javax.swing.JLabel();
        Milisegundos = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tropaComboBox = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        caminoComboBox = new javax.swing.JComboBox<>();
        enviarTropaBT = new javax.swing.JButton();
        jPanel3 = new FondoPanel();
        caminoSuperior2 = new javax.swing.JLabel();
        caminoSuperior1 = new javax.swing.JLabel();
        caminoSuperior4 = new javax.swing.JLabel();
        caminoInferior1 = new javax.swing.JLabel();
        caminoInferior2 = new javax.swing.JLabel();
        caminoInferior3 = new javax.swing.JLabel();
        caminoSuperior3 = new javax.swing.JLabel();
        caminoInferior4 = new javax.swing.JLabel();
        lbl_ronda = new javax.swing.JLabel();
        BtnIniciar = new javax.swing.JButton();

        board.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Board(1).png"))); // NOI18N

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar3.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar3.add(jMenu6);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        gamePanel.setBackground(new java.awt.Color(204, 204, 204));

        vidaJugadorTxt.setBackground(new java.awt.Color(255, 51, 51));
        vidaJugadorTxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        vidaJugadorTxt.setText("Vida Jugador:  ");

        vidaJugadorPB.setBackground(new java.awt.Color(255, 51, 51));

        jLabel1.setBackground(new java.awt.Color(255, 51, 51));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Vida CPU:");

        vidaCPUPB.setBackground(new java.awt.Color(255, 51, 51));

        jLabel7.setText("Primer tres unidades CPU");

        unidadesCPUTxt.setColumns(20);
        unidadesCPUTxt.setRows(5);
        jScrollPane1.setViewportView(unidadesCPUTxt);

        lblCronometro.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        lblCronometro.setText("0m : 0s");

        Milisegundos.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        Milisegundos.setText(".00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCronometro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Milisegundos)
                .addGap(71, 71, 71))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCronometro)
                    .addComponent(Milisegundos))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel3.setText("Tropa");

        jLabel8.setText("Tropa");

        tropaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mago", "Arquero", "Caballero" }));
        tropaComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tropaComboBoxActionPerformed(evt);
            }
        });

        jLabel9.setText("Carril");

        caminoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Carril Superior", "Carril Inferior" }));

        enviarTropaBT.setBackground(new java.awt.Color(255, 153, 51));
        enviarTropaBT.setText("Enviar Tropa");
        enviarTropaBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarTropaBTActionPerformed(evt);
            }
        });

        caminoSuperior2.setMaximumSize(new java.awt.Dimension(100, 100));
        caminoSuperior2.setMinimumSize(new java.awt.Dimension(100, 100));

        caminoSuperior1.setMaximumSize(new java.awt.Dimension(100, 100));
        caminoSuperior1.setMinimumSize(new java.awt.Dimension(100, 100));

        caminoSuperior4.setMaximumSize(new java.awt.Dimension(100, 100));
        caminoSuperior4.setMinimumSize(new java.awt.Dimension(100, 100));

        caminoInferior1.setMaximumSize(new java.awt.Dimension(100, 100));
        caminoInferior1.setMinimumSize(new java.awt.Dimension(100, 100));

        caminoInferior2.setMaximumSize(new java.awt.Dimension(100, 100));
        caminoInferior2.setMinimumSize(new java.awt.Dimension(100, 100));

        caminoInferior3.setMaximumSize(new java.awt.Dimension(100, 100));
        caminoInferior3.setMinimumSize(new java.awt.Dimension(100, 100));

        caminoSuperior3.setMaximumSize(new java.awt.Dimension(100, 100));
        caminoSuperior3.setMinimumSize(new java.awt.Dimension(100, 100));

        caminoInferior4.setMaximumSize(new java.awt.Dimension(100, 100));
        caminoInferior4.setMinimumSize(new java.awt.Dimension(100, 100));

        lbl_ronda.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(caminoInferior1, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                    .addComponent(caminoSuperior1, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(caminoSuperior2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(caminoInferior2, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(caminoSuperior3, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                        .addGap(24, 24, 24)
                        .addComponent(caminoSuperior4, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                        .addGap(78, 78, 78))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(caminoInferior3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(caminoInferior4, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                        .addGap(91, 91, 91))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbl_ronda, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbl_ronda, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(caminoSuperior2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caminoSuperior1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caminoSuperior4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caminoSuperior3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(103, 103, 103)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(caminoInferior2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caminoInferior3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caminoInferior4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caminoInferior1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(165, Short.MAX_VALUE))
        );

        lbl_ronda.getAccessibleContext().setAccessibleName("lbl_ronda");

        BtnIniciar.setLabel("Iniciar juego");
        BtnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(vidaCPUPB, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vidaJugadorPB, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(gamePanelLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tropaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(111, 111, 111)
                                .addComponent(jLabel7))
                            .addComponent(BtnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vidaJugadorTxt)
                            .addGroup(gamePanelLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(enviarTropaBT))
                            .addGroup(gamePanelLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(caminoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(85, 85, 85)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tropaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(caminoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(enviarTropaBT)))
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addComponent(vidaJugadorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vidaJugadorPB, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vidaCPUPB, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gamePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tropaComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tropaComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tropaComboBoxActionPerformed

    private void enviarTropaBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarTropaBTActionPerformed
        switch (tropaComboBox.getSelectedItem().toString()) {
            case "Arquero": {
                tTropa = new Tropa(arquero, new ImageIcon("src/Imagenes/imagenArquero.jpeg"), 1);
                tTropa.setPoscionActual(1);
                NodoPersonaje = new NodoCo(tTropa);
            }
            break;

            case "Caballero": {
                tTropa = new Tropa(caballero, new ImageIcon("src/Imagenes/imagenCaballero.jpeg"), 2);
                tTropa.setPoscionActual(1);
                NodoPersonaje = new NodoCo(tTropa);
            }
            break;

            case "Mago": {
                tTropa = new Tropa(mago, new ImageIcon("src/Imagenes/imagenMago.jpeg"), 1.5);
                tTropa.setPoscionActual(1);
                NodoPersonaje = new NodoCo(tTropa);
            }
            break;
        }

        switch (caminoComboBox.getSelectedItem().toString()) {
            case "Carril Superior":
                if (jugador.getTropasSup().getLargo() == 0) {
                    jugador.getTropasSup().encola(NodoPersonaje);
                    MoverTropasJugador(true, false);
                }
                break;
            case "Carril Inferior":
                if (jugador.getTropaInf().getLargo() == 0) {
                    jugador.getTropaInf().encola(NodoPersonaje);
                    MoverTropasJugador(false, true);
                }
                break;
        }
    }//GEN-LAST:event_enviarTropaBTActionPerformed

    private void BtnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIniciarActionPerformed

    }//GEN-LAST:event_BtnIniciarActionPerformed
   
    private void Cronometro() {
        ActualizaTiempo();
        ActualizarCronometro();
    }

    private void ActualizarCronometro() {
        String cronometro = minutos + "m:" + segundos + "s";
        lblCronometro.setText(cronometro);
        Milisegundos.setText("." + milisegundos);
    }

    private void ActualizaTiempo() {
        milisegundos++;

        if (milisegundos == 100) {
            milisegundos = 0;
            segundos++;
        }

        if (segundos == 60) {
            segundos = 0;
            minutos++;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnIniciar;
    private javax.swing.JLabel Milisegundos;
    private javax.swing.JLabel board;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JComboBox<String> caminoComboBox;
    private javax.swing.JLabel caminoInferior1;
    private javax.swing.JLabel caminoInferior2;
    private javax.swing.JLabel caminoInferior3;
    private javax.swing.JLabel caminoInferior4;
    private javax.swing.JLabel caminoSuperior1;
    private javax.swing.JLabel caminoSuperior2;
    private javax.swing.JLabel caminoSuperior3;
    private javax.swing.JLabel caminoSuperior4;
    private javax.swing.JButton enviarTropaBT;
    private javax.swing.JPanel gamePanel;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCronometro;
    private javax.swing.JLabel lbl_ronda;
    private javax.swing.JComboBox<String> tropaComboBox;
    private javax.swing.JTextArea unidadesCPUTxt;
    private javax.swing.JProgressBar vidaCPUPB;
    private javax.swing.JProgressBar vidaJugadorPB;
    private javax.swing.JLabel vidaJugadorTxt;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/Imagenes/Board(1).png")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);

            super.paint(g);
        }

    }

}

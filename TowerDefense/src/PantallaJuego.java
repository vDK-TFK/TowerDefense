
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class PantallaJuego extends javax.swing.JFrame implements ActionListener {
    FondoPanel fondo = new FondoPanel();
    
    private final int PANEL_WIDTH = 600;
    private final int PANEL_HEIGHT = 600;
    private Image backGroundImage;
 
    private int xVelocity = 2;
    private int yVelocity = 1;
    private int x = 0;
    private int y = 0;
    
    private Cola colaUnidades = new Cola();
    private CPU CPU = new CPU();
    private ListaDobleCirc caminoSuperior = new ListaDobleCirc();
    private ListaDobleCirc caminoInferior = new ListaDobleCirc();
    private Castillo castilloJugador = new Castillo();
    private Castillo castilloCPU = new Castillo();
    private int numeroRonda = 1;
    private int numTropasJugador = numeroRonda + 4;
    private int numTropasCPU = 0;
    private int numTropasTablero = 0;
    private boolean juegoIniciado = false;
    
    private Timer mTimer;
    private int minutos = 0;
    private int segundos = 0;
    private int milisegundos = 0;
    
    Timer timer;
    
    
    
    
    
    /**
     * Creates new form PantallaJuego
     */
    public PantallaJuego() {
        initComponents();
        setLocationRelativeTo(null);
        mTimer = new Timer(10, (ActionEvent e) -> { Cronometro(); });
        
        // Creacion de los objetos de tropas
        Tropa arquero = new Tropa("arquero");
        Tropa mago = new Tropa("mago");
        Tropa caballero = new Tropa("caballero");
        
        // Creacion cola de unidades
        colaUnidades.encola(new Nodo(arquero));
        colaUnidades.encola(new Nodo(mago));
        colaUnidades.encola(new Nodo(caballero));
        
        
        
        // Creacion de los caminos y su inclusion en la lista doblemente enlazada
        // Camino superior 
        Camino caminoSup1 = new Camino();
        caminoSup1.setLabel(caminoSuperior1);
        caminoSuperior.inserta(caminoSup1);
        
        Camino caminoSup2 = new Camino();
        caminoSup2.setLabel(caminoSuperior2);
        caminoSuperior.inserta(caminoSup2);
        
        Camino caminoSup3 = new Camino();
        caminoSup3.setLabel(caminoSuperior3);
        caminoSuperior.inserta(caminoSup3);
        
        Camino caminoSup4 = new Camino();
        caminoSup4.setLabel(caminoSuperior4);
        caminoSuperior.inserta(caminoSup4);
        
        // Camino inferior
        Camino caminoInf1 = new Camino();
        caminoInf1.setLabel(caminoInferior1);
        caminoInferior.inserta(caminoInf1);
        
        Camino caminoInf2 = new Camino();
        caminoInf2.setLabel(caminoInferior2);
        caminoInferior.inserta(caminoInf2);
        
        Camino caminoInf3 = new Camino();
        caminoInf3.setLabel(caminoInferior3);
        caminoInferior.inserta(caminoInf3);
        
        Camino caminoInf4 = new Camino();
        caminoInf4.setLabel(caminoInferior4);
        caminoInferior.inserta(caminoInf4);
        
        
        
    }
    private void Cronometro() {
        ActualizaTiempo();
        ActualizarCronometro();
    }
    
    private void ActualizarCronometro(){
        String cronometro =  minutos + "m:" + segundos + "s";
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
     
    
    
    
     
    private Boolean inicializarJuego() {
        Boolean inicioJuego = true;
       
        Tropa tropaSeleccionada = new Tropa(tropaComboBox.getSelectedItem().toString());
        
        if(caminoComboBox.getSelectedItem().toString().equals("Carril Superior")){
            
            if(caminoSuperior.extrae(0).getTropa() != null){
                Enfrentamiento enfrentamiento = new Enfrentamiento();
                int casosEnfrentamiento = enfrentamiento.casosEnfrentamiento(tropaSeleccionada,caminoSuperior.extrae(0).getTropa());
                 
                if(casosEnfrentamiento == 1){
                    CPU.numTropasCaminoSup(); //reduce el num de tropas camino sup
                    caminoSuperior.extrae(0).setTropa(tropaSeleccionada);
                    caminoSuperior.extrae(0).ingresarTropa();
                    numTropasTablero++;
                    paint(caminoSuperior1,tropaSeleccionada.getCaracter().getImage());  
                }
            } else{
                caminoSuperior.extrae(0).setTropa(tropaSeleccionada);
                caminoSuperior.extrae(0).ingresarTropa();
                numTropasTablero++;
                paint(caminoSuperior1,tropaSeleccionada.getCaracter().getImage());   
            }
            moverUnidades();
           
        } else if(caminoComboBox.getSelectedItem().toString().equals("Carril Inferior")){
            if(caminoInferior.extrae(0).getTropa() != null){
                Enfrentamiento enfrentamiento = new Enfrentamiento();
                int casosEnfrentamiento = enfrentamiento.casosEnfrentamiento(tropaSeleccionada,caminoInferior.extrae(0).getTropa());
                 
                if(casosEnfrentamiento == 1){
                    CPU.numTropasCaminoInf(); //reduce el num de tropas camino sup
                    caminoInferior.extrae(0).setTropa(tropaSeleccionada);
                    caminoInferior.extrae(0).ingresarTropa();
                    numTropasTablero++;
                    paint(caminoInferior1,tropaSeleccionada.getCaracter().getImage());
                   
                }
            } else{
                caminoInferior.extrae(0).setTropa(tropaSeleccionada);
                caminoInferior.extrae(0).ingresarTropa();
                paint(caminoInferior1,tropaSeleccionada.getCaracter().getImage());
                numTropasTablero++;
            }
            moverUnidades();
            numRondas();
        } else{
            JOptionPane.showMessageDialog(null, "Error, camino no seleccionado");
            return false;
        }
        
        return inicioJuego;
    }
    
    // Permite pintar una imagen en un jlabel
    public void paint(JLabel Label, Image image){
        Icon icono = new ImageIcon(image.getScaledInstance
        (Label.getWidth(),Label.getHeight(),Image.SCALE_DEFAULT));
        Label.setIcon(icono);
        Label.repaint();  
    }
    
    // Metodo para mover las tropas
    public void moverTropas(){
        mover moverJugador = new mover(caminoSuperior, caminoInferior, castilloCPU, this, CPU);
        MoverCpu moverCPU = new MoverCpu(caminoSuperior, caminoInferior, castilloJugador, this, CPU);
        moverJugador.start();
        moverCPU.start();
    }
    
    
    // Timer
    public void moverUnidades(){
        Timer timer;
        TimerTask tarea;
        int velmil = xVelocity*1000;
        
        tarea = new TimerTask(){
            
          @Override
          public void run(){
              moverTropas();
          }
            
        };
    }
    
    // Actualizar numero de rondas
    
    public int numRondas(){
        if(numTropasTablero == 0){
            numeroRonda += 1;
        }
        return numeroRonda;
    }
    
    
    private void mostrarUnidadesCPU(){
        String primeraUnidad = CPU.imprimirPrimeraUnidad();
        String segundaUnidad = CPU.imprimirSegundaUnidad();
        String terceraUnidad = CPU.imprimirTerceraUnidad();
        
        unidadesCPUTxt.setText(primeraUnidad + "\n"
                + segundaUnidad + "\n"
                + terceraUnidad);
    }
    
    

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tropasDisponiblesTxt = new javax.swing.JTextField();
        vidaJugadorPB = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        vidaCPUPB = new javax.swing.JProgressBar();
        numRonda = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        unidadesCPUTxt = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        lblCronometro = new javax.swing.JLabel();
        Milisegundos = new javax.swing.JLabel();
        Iniciar = new javax.swing.JButton();
        Detener = new javax.swing.JButton();
        Reiniciar = new javax.swing.JButton();
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

        jLabel2.setText("Temporizador");

        jLabel4.setText("Ronda:");

        jLabel5.setText("Cantidad de tropas disponibles:");

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

        Iniciar.setBackground(new java.awt.Color(0, 153, 51));
        Iniciar.setText("Iniciar");
        Iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciarActionPerformed(evt);
            }
        });

        Detener.setBackground(new java.awt.Color(204, 0, 51));
        Detener.setText("Detener");
        Detener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DetenerActionPerformed(evt);
            }
        });

        Reiniciar.setBackground(new java.awt.Color(153, 153, 0));
        Reiniciar.setText("Reiniciar");
        Reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReiniciarActionPerformed(evt);
            }
        });

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(Iniciar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Detener)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(Reiniciar)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCronometro)
                    .addComponent(Milisegundos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Iniciar)
                    .addComponent(Detener)
                    .addComponent(Reiniciar)))
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
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(158, 158, 158)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vidaJugadorTxt)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vidaCPUPB, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vidaJugadorPB, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(gamePanelLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(numRonda, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(gamePanelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(198, 198, 198)
                                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)))
                            .addGroup(gamePanelLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tropasDisponiblesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(gamePanelLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tropaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(gamePanelLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(caminoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(gamePanelLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(enviarTropaBT)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(numRonda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tropasDisponiblesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tropaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(caminoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(enviarTropaBT))
                .addGap(31, 31, 31)
                .addComponent(vidaJugadorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(vidaJugadorPB, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vidaCPUPB, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

    private void IniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarActionPerformed
        mTimer.start();
        Iniciar.setEnabled(false);
        numRonda.setText(String.valueOf(numeroRonda));
        tropasDisponiblesTxt.setText(Integer.toString(numTropasJugador));
        mostrarUnidadesCPU();
    }//GEN-LAST:event_IniciarActionPerformed

    private void DetenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetenerActionPerformed
        mTimer.stop();
        Iniciar.setEnabled(true);
    }//GEN-LAST:event_DetenerActionPerformed

    private void ReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReiniciarActionPerformed
        if(mTimer.isRunning()) 
        {
            mTimer.stop();
            Iniciar.setEnabled(true);
        }
       
 
        minutos = 0; 
        segundos = 0; 
        milisegundos = 0;
        
        ActualizarCronometro();
    }//GEN-LAST:event_ReiniciarActionPerformed

    private void tropaComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tropaComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tropaComboBoxActionPerformed

    private void enviarTropaBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarTropaBTActionPerformed
        //        numTropasJugador = numeroRonda + 4;
//        tropasDisponiblesTxt.setText(Integer.toString(numTropasJugador));
//        Tropa tropaSeleccionada = new Tropa(tropaComboBox.getSelectedItem().toString());
//        
//        if (numTropasJugador != 0) {
//            enviarTropaBT.setVisible(true);
//            
//            if (caminoSuperior.extrae(0).getTropa() != null) {
//                Enfrentamiento enfrentamiento = new Enfrentamiento();
//                int battleResult = enfrentamiento.casosEnfrentamiento(tropaSeleccionada, caminoSuperior.extrae(0).getTropa());
//                
//                switch(battleResult){
//                    case 1:
//                        
//                        
//                        
//                }
//                
//                
//                
//                
//                
//            } else{
//                Camino entradaCamino = caminoInferior.extrae(1);
//                entradaCamino.setTropa(tropaSeleccionada);
//                entradaCamino.ingresarTropa(tropaSeleccionada);
//                numTropasJugador--;
//            }
//        } else{
//            enviarTropaBT.setVisible(false);
//        }

            inicializarJuego();
    }//GEN-LAST:event_enviarTropaBTActionPerformed
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaJuego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Detener;
    private javax.swing.JButton Iniciar;
    private javax.swing.JLabel Milisegundos;
    private javax.swing.JButton Reiniciar;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JTextField numRonda;
    private javax.swing.JComboBox<String> tropaComboBox;
    private javax.swing.JTextField tropasDisponiblesTxt;
    private javax.swing.JTextArea unidadesCPUTxt;
    private javax.swing.JProgressBar vidaCPUPB;
    private javax.swing.JProgressBar vidaJugadorPB;
    private javax.swing.JLabel vidaJugadorTxt;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) { 
        
        timer = new Timer(0, null);
        timer.setRepeats(juegoIniciado);
        
    }
    
    class FondoPanel extends JPanel{
        private Image imagen;
        
        @Override
        public void paint(Graphics g){
            imagen = new ImageIcon(getClass().getResource("/Imagenes/Board(1).png")).getImage();
            g.drawImage(imagen,0,0,getWidth(),getHeight(),this);
            
            setOpaque(false);
            
            super.paint(g);
        }
        
        
    }
    
}

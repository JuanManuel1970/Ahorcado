import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AhorcadoFrame extends JFrame {
    private final JLabel palabraLabel;
    private final JTextField letraInput;
    private final JButton probarButton;
    private final JLabel erroresLabel;
    private final JLabel imagenLabel;
    private final AhorcadoLogica logica;

    public AhorcadoFrame() {
        logica = new AhorcadoLogica();

        palabraLabel = new JLabel(logica.getPalabraVisible());
        palabraLabel.setFont(new Font("Monospaced", Font.BOLD, 28));
        palabraLabel.setHorizontalAlignment(SwingConstants.CENTER);

        erroresLabel = new JLabel("Errores: 0 / 6");
        erroresLabel.setHorizontalAlignment(SwingConstants.CENTER);
        erroresLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        imagenLabel = new JLabel();
        actualizarImagen();

        letraInput = new JTextField(1);
        probarButton = new JButton("Probar");

        probarButton.addActionListener(e -> {
            String texto = letraInput.getText().toLowerCase();
            if (texto.length() == 1 && Character.isLetter(texto.charAt(0))) {
                logica.intentarLetra(texto.charAt(0));
                palabraLabel.setText(logica.getPalabraVisible());
                erroresLabel.setText("Errores: " + logica.getErrores() + " / 6");
                actualizarImagen();
                letraInput.setText("");

                if (logica.juegoGanado()) {
                    JOptionPane.showMessageDialog(this, "¡Ganaste! La palabra era: " + logica.getPalabra());
                    reiniciarJuego();
                } else if (logica.juegoPerdido()) {
                    JOptionPane.showMessageDialog(this, "¡Perdiste! La palabra era: " + logica.getPalabra());
                    reiniciarJuego();
                }
            }
        });

        setLayout(new BorderLayout());
        add(palabraLabel, BorderLayout.NORTH);
        add(imagenLabel, BorderLayout.WEST);
        add(erroresLabel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Letra:"));
        inputPanel.add(letraInput);
        inputPanel.add(probarButton);
        add(inputPanel, BorderLayout.SOUTH);

        setTitle("Juego del Ahorcado");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void actualizarImagen() {
        int errores = logica.getErrores();
        String path = "img/" + errores + ".png";

        File archivo = new File(path);
        if (!archivo.exists()) {
            System.out.println("⚠ No se encontró la imagen: " + archivo.getAbsolutePath());
            return;
        }

        ImageIcon icon = new ImageIcon(path);
        Image scaled = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        imagenLabel.setIcon(new ImageIcon(scaled));

        System.out.println("🖼 Mostrando: " + archivo.getAbsolutePath());
    }

    private void reiniciarJuego() {
        dispose();
        new AhorcadoFrame();
    }
}

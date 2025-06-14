import java.util.Random;

public class AhorcadoLogica {
    private final String palabra;
    private final boolean[] descubiertas;
    private int errores;

    private static final String[] PALABRAS = {
            "computadora", "java", "python", "swing", "programacion",
            "teclado", "monitor", "archivo", "clase", "objeto"
    };

    public AhorcadoLogica() {
        this.palabra = PALABRAS[new Random().nextInt(PALABRAS.length)].toLowerCase();
        this.descubiertas = new boolean[palabra.length()];
        this.errores = 0;
    }

    public void intentarLetra(char letra) {
        boolean acierto = false;
        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == letra) {
                descubiertas[i] = true;
                acierto = true;
            }
        }
        if (!acierto) {
            errores++;
        }
    }

    public String getPalabraVisible() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < palabra.length(); i++) {
            if (descubiertas[i]) {
                sb.append(palabra.charAt(i)).append(" ");
            } else {
                sb.append("_ ");
            }
        }
        return sb.toString().trim();
    }

    public boolean juegoGanado() {
        for (boolean b : descubiertas) {
            if (!b) return false;
        }
        return true;
    }

    public boolean juegoPerdido() {
        return errores >= 6;
    }

    public int getErrores() {
        return errores;
    }

    public String getPalabra() {
        return palabra;
    }
}

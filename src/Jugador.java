import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.JPanel;

public class Jugador {
    private static final int TOTAL_CARTAS = 10;
    private static final int MARGEN = 10;
    private static final int DISTANCIA = 40;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    public void repartir() {
        for (int i = 0; i < cartas.length; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        int posicionHorizontal = MARGEN + cartas.length * DISTANCIA;
        for (Carta c : cartas) {
            c.mostrar(pnl, posicionHorizontal, MARGEN);
            posicionHorizontal -= DISTANCIA;
        }
        pnl.repaint();
    }

    public String analizarCartas() {
        Map<NombreCarta, Integer> conteo = new HashMap<>();
        for (Carta c : cartas) {
            conteo.put(c.getNombre(), conteo.getOrDefault(c.getNombre(), 0) + 1);
        }
        
        ArrayList<String> combinaciones = new ArrayList<>();
        for (Map.Entry<NombreCarta, Integer> entrada : conteo.entrySet()) {
            if (entrada.getValue() >= 2) {
                combinaciones.add(entrada.getKey() + " x" + entrada.getValue());
            }
        }
        
        return combinaciones.isEmpty() ? "No se encontraron combinaciones" : String.join(", ", combinaciones);
    }
}
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Zerbitzaria {
    private ServerSocket socket;
    private int puerto;
    private List<Bezeroa> bezeroak;

    public Zerbitzaria(int puerto) {
        this.puerto = puerto;
        this.bezeroak = new ArrayList<>();
    }

    public void hasi() throws IOException {
        this.socket = new ServerSocket(this.puerto);
        System.out.println("Servidor iniciado en el puerto " + this.puerto);
    }

    public void itxi() throws IOException {
        socket.close();
    }

    public boolean konektatuta() {
        return !socket.isClosed();
    }

    public Socket onartuKonexioa() throws IOException {
        return socket.accept();
    }

    public synchronized void geituBezeroa(Bezeroa bezero) {
        bezeroak.add(bezero);
        System.out.println("Cliente conectado. Total: " + bezeroak.size());
    }

    public synchronized void kenduBezeroa(Bezeroa bezero) {
        bezeroak.remove(bezero);
        System.out.println("Cliente desconectado. Total: " + bezeroak.size());
    }

    public synchronized void bidaliMezuaDenei(String mezua, Bezeroa bidaltzailea) {
        System.out.println("Enviando mensaje a todos los clientes: " + mezua);
        Iterator<Bezeroa> iterator = bezeroak.iterator();

        while (iterator.hasNext()) {
            Bezeroa bezeroa = iterator.next();
            if (bezeroa != bidaltzailea && bezeroa.konektatutaDago()) {
                bezeroa.out.println(mezua);
            }
        }
    }
}

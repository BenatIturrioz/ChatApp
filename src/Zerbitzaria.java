import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Zerbitzaria {
    private ServerSocket socket;
    private int puerto;
    private String host;
    private ArrayList<Bezeroa> bezeroak;

    public Zerbitzaria(int puerto, String host) {
        this.puerto = puerto;
        this.host = host;
        this.bezeroak = new ArrayList<>();
    }

    public void hasi() throws IOException {
        this.socket = new ServerSocket(this.puerto);
    }

    public void itxi() throws IOException{
        this.socket.close();
    }

    public boolean konektatuta(){return !this.socket.isClosed();}

    public Socket onartuKonexioa() throws IOException{
        return this.socket.accept();
    }

    public void geituBezeroa(Bezeroa bezero){this.bezeroak.add(bezero);}

    public void bidaliMezuaDenei(String mezua, Bezeroa bidaltzailea) {
        for (Bezeroa bezeroa : this.bezeroak) {
            // No enviar el mensaje al remitente
            if (bezeroa != bidaltzailea) {
                bezeroa.out.println(mezua);
            }
        }
    }
}

import java.io.IOException;
import java.net.SocketException;

public class BezeroenKonexioa extends Thread {
    private Bezeroa bezero;
    private Zerbitzaria zerbitzaria;

    public BezeroenKonexioa(Bezeroa bezero, Zerbitzaria zerbitzaria) {
        this.bezero = bezero;
        this.zerbitzaria = zerbitzaria;
    }

    public void run() {
        bezero.out.println("Kaixo, ongi etorri gure mezularitza zerbitzura!");

        try {
            while (bezero.konektatutaDago()) {
                String mezua = bezero.in.readLine();
                if (mezua == null) break; // Cliente se desconectó

                System.out.println("Mensaje recibido: " + mezua);
                zerbitzaria.bidaliMezuaDenei(mezua, bezero);
            }
        } catch (SocketException e) {
            System.out.println("Cliente desconectado abruptamente.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            zerbitzaria.kenduBezeroa(bezero);
            bezero.cerrarConexion();
        }
    }
}

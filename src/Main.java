import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        Zerbitzaria zerbitzaria = new Zerbitzaria(5555);

        try {
            zerbitzaria.hasi();

            while (zerbitzaria.konektatuta()) {
                Socket bezeroarenSocketa = zerbitzaria.onartuKonexioa();
                Bezeroa bezero = new Bezeroa(bezeroarenSocketa);
                zerbitzaria.geituBezeroa(bezero);
                BezeroenKonexioa konexioa = new BezeroenKonexioa(bezero, zerbitzaria);
                konexioa.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.io.IOException;

public class BezeroenKonexioa extends Thread{

    private Bezeroa bezero;
    private Zerbitzaria zerbitzaria;

    public BezeroenKonexioa(Bezeroa bezero, Zerbitzaria zerbitzaria) {
        this.bezero = bezero;
        this.zerbitzaria = zerbitzaria;
    }

    public void run() {
        this.bezero.out.println("Kaixo ongi etorri gure mezularitza zerbitzura!");

        try {
            while (this.bezero.konektatutaDago()) {
                String mezua = this.bezero.in.readLine();
                // Pasa el cliente actual como remitente
                this.zerbitzaria.bidaliMezuaDenei(mezua, this.bezero);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void gitproba(){

    }
}

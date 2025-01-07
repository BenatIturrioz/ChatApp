import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Bezeroa {
    private Socket socket;
    BufferedReader in;
    PrintWriter out;

    public Bezeroa(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public boolean konektatutaDago(){return !this.socket.isClosed();}
}

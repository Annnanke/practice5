package TCP;

import Packets.Packet;
import Sup.Decryptor;
import Sup.Encryptor;

import java.io.*;
import java.net.*;
import java.util.Arrays;

public class ClientTCP{
    private final Socket socket;
    private final byte clientID;
    private OutputStream out;
    private InputStream in;

    public ClientTCP(byte clientID) throws IOException {
        this.clientID = clientID;
        this.socket = new Socket("127.0.0.1", ServerTCP.PORT);
        out = socket.getOutputStream();
        in = socket.getInputStream();
    }

    public void send(Packet packet) throws IOException {
        byte[] bytes = Encryptor.encodePackage(packet);
        out.write(bytes);
    }

    public Packet receive() throws IOException {
        byte[] bytes = new byte[1000];
        int length = in.read(bytes);
        return Decryptor.decodePackage(Arrays.copyOfRange(bytes, 0, length));
    }
}
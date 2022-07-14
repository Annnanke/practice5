
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class MyServer {
    private static final byte[] API_KEY_SECRET_BYTES = "key".getBytes(StandardCharsets.UTF_8)

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(6060), 0);
        DataBase db = new DataBase();
        db.initDataBase("warehouse");
        db.insertProduct(new Product("bread", 10, 500));
        db.insertProduct(new Product("buckwheat", 10, 100));

        server.start();
    }
}
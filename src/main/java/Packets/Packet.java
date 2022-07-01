package Packets;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Packet {
    private static final byte MAGIC_BYTE = 0x13;
    private final byte client;
    private final long packetID;
    Message message;

    public Packet(byte client, long packetID, Message message) {
        this.client = client;
        this.packetID = packetID;
        this.message = message;
    }

    public static byte getMagicByte() {
        return MAGIC_BYTE;
    }

    public byte getClient() {
        return client;
    }

    public long getPacketID() {
        return packetID;
    }

    public Message getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Packet{" +
                "client=" + client +
                ", packet=" + packetID +
                ", message=" + message.toString() +
                '}';
    }
}
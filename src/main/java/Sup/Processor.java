package Sup;

import Packets.Message;
import Packets.Packet;


import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;

public class Processor implements Runnable{
    private BlockingQueue<Packet> decryptQueue;
    private BlockingQueue<Packet> answerQueue;


    public Processor(BlockingQueue<Packet> decryptQueue, BlockingQueue<Packet> answerQueue) {
        this.decryptQueue = decryptQueue;
        this.answerQueue = answerQueue;
    }
    @Override
    public void run() {
        try{
            while(true) {
                if(!Server.alive){
                    System.out.println("CLOSE Sup.Processor");
                    return;
                }
                Packet packet = decryptQueue.take();
                Message message = packet.getMessage();
                if (packet.getMessage().getCType() == MessageGenerator.getDeadCommand()) {
                    Server.alive = false;
                    System.out.println("CLOSE Sup.Processor");
                    return;
                }

                Message answerMessage = new Message(message.getCType(), message.getBUserID(), "OK".getBytes(StandardCharsets.UTF_8));
                Packet answerPacket = new Packet(packet.getClient(), packet.getPacketID(), answerMessage);
                answerQueue.put(answerPacket);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
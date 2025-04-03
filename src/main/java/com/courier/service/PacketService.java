package com.courier.service;

import com.courier.model.Packet;
import java.time.LocalDate;
import java.util.List;

public interface PacketService {
    Packet createPacket(Packet packet);
    Packet updatePacket(Integer packetId, Packet packet);
    void deletePacket(Integer packetId);
    Packet getPacketById(Integer packetId);
    List<Packet> getAllPackets();
    List<Packet> getPacketsBySender(Integer senderId);
    List<Packet> getPacketsByReceiver(Integer receiverId);
    List<Packet> getPacketsByDateRange(LocalDate startDate, LocalDate endDate);
    List<Packet> getPacketsByStatus(Integer statusId);
    List<Packet> getFragilePackets();
    List<Packet> getPacketsByDeliveryType(String deliveryType);
} 
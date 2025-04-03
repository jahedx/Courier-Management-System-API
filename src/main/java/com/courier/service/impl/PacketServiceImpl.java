package com.courier.service.impl;

import com.courier.model.Packet;
import com.courier.service.PacketService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@Service
public class PacketServiceImpl implements PacketService {

    private final JdbcTemplate jdbcTemplate;

    public PacketServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Packet createPacket(Packet packet) {
        String sql = "INSERT INTO Packets (Weight, Size, PacketType, SendDate, StatusID, Price, " +
                    "Fragile, DeliveryType, RecieverID, SenderID, RegistererID, PostmanID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, packet.getWeight());
            ps.setString(2, packet.getSize());
            ps.setString(3, packet.getPacketType());
            ps.setDate(4, java.sql.Date.valueOf(packet.getSendDate()));
            ps.setInt(5, packet.getStatusId());
            ps.setBigDecimal(6, packet.getPrice());
            ps.setBoolean(7, packet.getFragile());
            ps.setString(8, packet.getDeliveryType());
            ps.setInt(9, packet.getRecieverId());
            ps.setInt(10, packet.getSenderId());
            ps.setInt(11, packet.getRegistererId());
            ps.setInt(12, packet.getPostmanId());
            return ps;
        }, keyHolder);

        packet.setPacketId(keyHolder.getKey().intValue());
        return packet;
    }

    @Override
    @Transactional
    public Packet updatePacket(Integer packetId, Packet packet) {
        String sql = "UPDATE Packets SET Weight = ?, Size = ?, PacketType = ?, SendDate = ?, " +
                    "StatusID = ?, Price = ?, Fragile = ?, DeliveryType = ?, RecieverID = ?, " +
                    "SenderID = ?, RegistererID = ?, PostmanID = ? WHERE PacketID = ?";
        
        jdbcTemplate.update(sql,
                packet.getWeight(),
                packet.getSize(),
                packet.getPacketType(),
                java.sql.Date.valueOf(packet.getSendDate()),
                packet.getStatusId(),
                packet.getPrice(),
                packet.getFragile(),
                packet.getDeliveryType(),
                packet.getRecieverId(),
                packet.getSenderId(),
                packet.getRegistererId(),
                packet.getPostmanId(),
                packetId);

        return getPacketById(packetId);
    }

    @Override
    @Transactional
    public void deletePacket(Integer packetId) {
        String sql = "DELETE FROM Packets WHERE PacketID = ?";
        jdbcTemplate.update(sql, packetId);
    }

    @Override
    public Packet getPacketById(Integer packetId) {
        String sql = "SELECT * FROM Packets WHERE PacketID = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Packet.class), packetId);
    }

    @Override
    public List<Packet> getAllPackets() {
        String sql = "SELECT * FROM Packets";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Packet.class));
    }

    @Override
    public List<Packet> getPacketsBySender(Integer senderId) {
        String sql = "SELECT * FROM Packets WHERE SenderID = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Packet.class), senderId);
    }

    @Override
    public List<Packet> getPacketsByReceiver(Integer receiverId) {
        String sql = "SELECT * FROM Packets WHERE RecieverID = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Packet.class), receiverId);
    }

    @Override
    public List<Packet> getPacketsByDateRange(LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT * FROM Packets WHERE SendDate BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, 
            new BeanPropertyRowMapper<>(Packet.class), 
            java.sql.Date.valueOf(startDate),
            java.sql.Date.valueOf(endDate));
    }

    @Override
    public List<Packet> getPacketsByStatus(Integer statusId) {
        String sql = "SELECT * FROM Packets WHERE StatusID = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Packet.class), statusId);
    }

    @Override
    public List<Packet> getFragilePackets() {
        String sql = "SELECT * FROM Packets WHERE Fragile = 1";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Packet.class));
    }

    @Override
    public List<Packet> getPacketsByDeliveryType(String deliveryType) {
        String sql = "SELECT * FROM Packets WHERE DeliveryType = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Packet.class), deliveryType);
    }
} 
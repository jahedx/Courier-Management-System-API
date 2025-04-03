package com.courier.controller;

import com.courier.model.Packet;
import com.courier.service.PacketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/packets")
@Tag(name = "Packet Management", description = "APIs for managing courier packets")
public class PacketController {

    private final PacketService packetService;

    public PacketController(PacketService packetService) {
        this.packetService = packetService;
    }

    @PostMapping
    @Operation(summary = "Create a new packet", description = "Creates a new courier packet with the provided information")
    public ResponseEntity<Packet> createPacket(@Valid @RequestBody Packet packet) {
        return ResponseEntity.ok(packetService.createPacket(packet));
    }

    @PutMapping("/{packetId}")
    @Operation(summary = "Update packet", description = "Updates an existing packet's information")
    public ResponseEntity<Packet> updatePacket(
            @Parameter(description = "Packet ID") @PathVariable Integer packetId,
            @Valid @RequestBody Packet packet) {
        return ResponseEntity.ok(packetService.updatePacket(packetId, packet));
    }

    @DeleteMapping("/{packetId}")
    @Operation(summary = "Delete packet", description = "Deletes a packet by ID")
    public ResponseEntity<Void> deletePacket(
            @Parameter(description = "Packet ID") @PathVariable Integer packetId) {
        packetService.deletePacket(packetId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{packetId}")
    @Operation(summary = "Get packet by ID", description = "Retrieves a packet by its ID")
    public ResponseEntity<Packet> getPacketById(
            @Parameter(description = "Packet ID") @PathVariable Integer packetId) {
        return ResponseEntity.ok(packetService.getPacketById(packetId));
    }

    @GetMapping
    @Operation(summary = "Get all packets", description = "Retrieves a list of all packets")
    public ResponseEntity<List<Packet>> getAllPackets() {
        return ResponseEntity.ok(packetService.getAllPackets());
    }

    @GetMapping("/sender/{senderId}")
    @Operation(summary = "Get packets by sender", description = "Retrieves all packets sent by a specific customer")
    public ResponseEntity<List<Packet>> getPacketsBySender(
            @Parameter(description = "Sender ID") @PathVariable Integer senderId) {
        return ResponseEntity.ok(packetService.getPacketsBySender(senderId));
    }

    @GetMapping("/receiver/{receiverId}")
    @Operation(summary = "Get packets by receiver", description = "Retrieves all packets received by a specific customer")
    public ResponseEntity<List<Packet>> getPacketsByReceiver(
            @Parameter(description = "Receiver ID") @PathVariable Integer receiverId) {
        return ResponseEntity.ok(packetService.getPacketsByReceiver(receiverId));
    }

    @GetMapping("/date-range")
    @Operation(summary = "Get packets by date range", description = "Retrieves packets within a specific date range")
    public ResponseEntity<List<Packet>> getPacketsByDateRange(
            @Parameter(description = "Start date") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @Parameter(description = "End date") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(packetService.getPacketsByDateRange(startDate, endDate));
    }

    @GetMapping("/status/{statusId}")
    @Operation(summary = "Get packets by status", description = "Retrieves packets with a specific status")
    public ResponseEntity<List<Packet>> getPacketsByStatus(
            @Parameter(description = "Status ID") @PathVariable Integer statusId) {
        return ResponseEntity.ok(packetService.getPacketsByStatus(statusId));
    }

    @GetMapping("/fragile")
    @Operation(summary = "Get fragile packets", description = "Retrieves all packets marked as fragile")
    public ResponseEntity<List<Packet>> getFragilePackets() {
        return ResponseEntity.ok(packetService.getFragilePackets());
    }

    @GetMapping("/delivery-type/{deliveryType}")
    @Operation(summary = "Get packets by delivery type", description = "Retrieves packets with a specific delivery type")
    public ResponseEntity<List<Packet>> getPacketsByDeliveryType(
            @Parameter(description = "Delivery type") @PathVariable String deliveryType) {
        return ResponseEntity.ok(packetService.getPacketsByDeliveryType(deliveryType));
    }
} 
package com.cibus.repository;

import java.sql.Connection;
import java.util.ArrayList;

import com.cibus.common.dtos.HotelDto;
import com.cibus.common.models.HotelModel;
import com.cibus.interfaces.repository.IHotelRepository;

public class HotelRepository implements IHotelRepository {
  public HotelRepository(Connection connection) {
    this.connection = connection;
  }

  private Connection connection;

  @Override
  public void addHotel(HotelDto hotel) throws Exception {
    final var query = "INSERT INTO hotels (name, city, owner_id) VALUES (?, ?, ?)";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setString(1, hotel.getName());
      stmt.setString(2, hotel.getCity());
      stmt.setLong(3, hotel.getOwnerId());
      stmt.executeUpdate();
    }
  }

  @Override
  public ArrayList<HotelModel> getHotelsByOwnerId(long ownerId) throws Exception {
    final var query = "SELECT * FROM hotels where owner_id = " + ownerId;
    try (var stmt = connection.createStatement()) {
      var hotels = new ArrayList<HotelModel>();
      var rs = stmt.executeQuery(query);

      while(rs.next()) {
        var hotelId = rs.getLong(1);
        var hotel = new HotelModel(hotelId);
        hotel.setName(rs.getString(2));
        hotel.setCity(rs.getString(3));
        hotels.add(hotel);
      }

      return hotels;
    }
  }

  @Override
  public ArrayList<HotelModel> getHotels() throws Exception {
    final var query = "SELECT * FROM hotels";
    try (var stmt = connection.createStatement()) {
      var hotels = new ArrayList<HotelModel>();
      var rs = stmt.executeQuery(query);

      while(rs.next()) {
        var hotelId = rs.getLong(1);
        var hotel = new HotelModel(hotelId);
        hotel.setName(rs.getString(2));
        hotel.setCity(rs.getString(3));
        hotels.add(hotel);
      }

      return hotels;
    }
  }

  @Override
  public void updateHotel(HotelModel hotel) throws Exception {
    final var query = "UPDATE hotels SET name = ?, city = ? WHERE id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setString(1, hotel.getName());
      stmt.setString(2, hotel.getCity());
      stmt.setLong(3, hotel.getId());
      stmt.executeUpdate();
    }
  }

  @Override
  public void deleteHotel(HotelModel hotel) throws Exception {
    final var query = "DELETE FROM hotels WHERE id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, hotel.getId());
      stmt.executeUpdate();
    }
  }
}

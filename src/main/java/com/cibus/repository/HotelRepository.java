package com.cibus.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cibus.dtos.HotelDto;
import com.cibus.exceptions.RowNotFoundException;
import com.cibus.interfaces.repository.IHotelRepository;
import com.cibus.models.HotelModel;

public class HotelRepository implements IHotelRepository {
  public HotelRepository(Connection connection) {
    this.connection = connection;
  }

  private Connection connection;

  @Override
  public HotelModel addHotel(HotelDto hotel) throws Exception {
    final var query = "INSERT INTO hotels (name, city, owner_id) VALUES (?, ?, ?)";
    try (var stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
      stmt.setString(1, hotel.getName());
      stmt.setString(2, hotel.getCity());
      stmt.setLong(3, hotel.getOwnerId());
      stmt.executeUpdate();

      HotelModel model = null;

      try (var generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          model = new HotelModel(generatedKeys.getLong(1));
        } else {
          throw new SQLException("Can't get Primary Key");
        }
      }

      model.setOwnerId(hotel.getOwnerId());
      model.setName(hotel.getName());
      model.setCity(hotel.getCity());
      return model;
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
        hotel.setOwnerId(rs.getLong(4));
        hotels.add(hotel);
      }

      return hotels;
    }
  }

  @Override
  public ArrayList<HotelModel> getAllHotels() throws Exception {
    final var query = "SELECT * FROM hotels";
    try (var stmt = connection.createStatement()) {
      var hotels = new ArrayList<HotelModel>();
      var rs = stmt.executeQuery(query);

      while(rs.next()) {
        var hotelId = rs.getLong(1);
        var hotel = new HotelModel(hotelId);
        hotel.setName(rs.getString(2));
        hotel.setCity(rs.getString(3));
        hotel.setOwnerId(rs.getLong(4));
        hotels.add(hotel);
      }

      return hotels;
    }
  }

  @Override
  public void updateHotel(HotelModel hotel) throws Exception {
    final var query = "UPDATE hotels SET name = ?, city = ?, owner_id = ? WHERE id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setString(1, hotel.getName());
      stmt.setString(2, hotel.getCity());
      stmt.setLong(3, hotel.getOwnerId());
      stmt.setLong(4, hotel.getId());
      stmt.executeUpdate();
    }
  }

  @Override
  public void deleteHotel(HotelModel hotel) throws Exception {
    this.deleteHotel(hotel.getId());
  }

  @Override
  public void deleteHotel(long id) throws Exception {
    final var query = "DELETE FROM hotels WHERE id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, id);
      stmt.executeUpdate();
    }
  }

  @Override
  public HotelModel getHotel(long id) throws Exception {
    final var query = "SELECT * FROM hotels where id = " + id;
    try (var stmt = connection.createStatement()) {
      var rs = stmt.executeQuery(query);

      if(!rs.next()) {
        throw new RowNotFoundException(query);
      }

      var hotelId = rs.getLong(1);
      var hotel = new HotelModel(hotelId);
      hotel.setName(rs.getString(2));
      hotel.setCity(rs.getString(3));
      hotel.setOwnerId(rs.getLong(4));
      return hotel;
    }
  }
}

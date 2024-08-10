package com.cibus.repository;

import java.sql.Connection;
import java.util.ArrayList;

import com.cibus.common.dtos.HotelDto;
import com.cibus.common.models.HotelModel;
import com.cibus.exceptions.DatabaseException;
import com.cibus.interfaces.IHotelRepository;

public class HotelRepository implements IHotelRepository {
  public HotelRepository(Connection connection) {
    this.connection = connection;
  }

  private Connection connection;

  @Override
  public void addHotel(HotelDto hotel) {
    final var query = "INSERT INTO hotels (name, city, owner_id) VALUES (?, ?, ?)";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setString(1, hotel.getName());
      stmt.setString(2, hotel.getCity());
      stmt.setLong(3, hotel.getOwnerId());
      stmt.executeUpdate();
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public ArrayList<HotelModel> getHotelsByOwnerId(long ownerId) {
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
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public ArrayList<HotelModel> getHotels() {
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
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public void updateHotel(HotelModel hotel) {
    final var query = "UPDATE hotels SET name = ?, city = ? WHERE id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setString(1, hotel.getName());
      stmt.setString(2, hotel.getCity());
      stmt.setLong(3, hotel.getId());
      stmt.executeUpdate();
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public void deleteHotel(HotelModel hotel) {
    final var query = "DELETE FROM hotels WHERE id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, hotel.getId());
      stmt.executeUpdate();
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }
}

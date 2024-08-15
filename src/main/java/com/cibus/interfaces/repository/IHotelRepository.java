package com.cibus.interfaces.repository;

import java.util.ArrayList;

import com.cibus.dtos.HotelDto;
import com.cibus.models.HotelModel;

public interface IHotelRepository {
  ArrayList<HotelModel> getHotelsByOwnerId(long ownerId) throws Exception;
  ArrayList<HotelModel> getAllHotels() throws Exception;

  HotelModel addHotel(HotelDto hotel) throws Exception;
  void updateHotel(HotelModel hotel) throws Exception;
  void deleteHotel(HotelModel hotel) throws Exception;
  void deleteHotel(long id) throws Exception;
}

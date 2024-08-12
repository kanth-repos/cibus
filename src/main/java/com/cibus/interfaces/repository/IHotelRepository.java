package com.cibus.interfaces.repository;

import java.util.ArrayList;

import com.cibus.common.dtos.HotelDto;
import com.cibus.common.models.HotelModel;

public interface IHotelRepository {
  ArrayList<HotelModel> getHotelsByOwnerId(long ownerId) throws Exception;
  ArrayList<HotelModel> getHotels() throws Exception;

  void addHotel(HotelDto hotel) throws Exception;
  void updateHotel(HotelModel hotel) throws Exception;
  void deleteHotel(HotelModel hotel) throws Exception;
}

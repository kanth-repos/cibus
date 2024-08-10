package com.cibus.interfaces;

import java.util.ArrayList;

import com.cibus.common.dtos.HotelDto;
import com.cibus.common.models.HotelModel;

public interface IHotelRepository {
  ArrayList<HotelModel> getHotelsByOwnerId(long ownerId);
  ArrayList<HotelModel> getHotels();

  void addHotel(HotelDto hotel);
  void updateHotel(HotelModel hotel);
  void deleteHotel(HotelModel hotel);
}

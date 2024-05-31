package com.Dasanayaka.Backend.service;

import com.Dasanayaka.Backend.dto.RestorentsDto;
import com.Dasanayaka.Backend.model.Resturent;
import com.Dasanayaka.Backend.model.User;
import com.Dasanayaka.Backend.request.CreateRestorentRequest;

import java.util.List;

public interface RestorentService {

    public Resturent createRestorent(CreateRestorentRequest req, User user);

    public  Resturent updateRestorent(Long  restorentId, CreateRestorentRequest updatedRestorent) throws  Exception;

    public  void  deleteRestorent(Long restorentId) throws Exception;

    public List<Resturent> getAllResthorents();

    public  List<Resturent> serchRestorent(String keyword);

    public  Resturent findRestorentById( Long id )  throws  Exception;

    public  Resturent getRestorentByUserId(Long userId) throws  Exception;

    public RestorentsDto addToFavorites(Long restorentId, User user) throws  Exception;

    public  Resturent updateRestorentStatus( Long id)  throws  Exception;
}

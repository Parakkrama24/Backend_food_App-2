package com.Dasanayaka.Backend.service;

import com.Dasanayaka.Backend.model.Oder;
import com.Dasanayaka.Backend.model.User;
import com.Dasanayaka.Backend.request.OderRequest;

import java.util.List;


public interface OderService {

    public Oder createOder(OderRequest order , User user );

    public Oder updateOder(Long oderId, String oderStatus) throws  Exception;

    public  void cancelOder( Long  oderId ) throws  Exception;

    public List<Oder> getUserOrder (Long oderid) throws  Exception;
}

package com.Dasanayaka.Backend.service;

import com.Dasanayaka.Backend.dto.RestorentsDto;
import com.Dasanayaka.Backend.model.Address;
import com.Dasanayaka.Backend.model.Resturent;
import com.Dasanayaka.Backend.model.User;
import com.Dasanayaka.Backend.repository.AddressRepository;
import com.Dasanayaka.Backend.repository.RestorentRepository;
import com.Dasanayaka.Backend.request.CreateRestorentRequest;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurentSeviceImp implements  RestorentService{

    @Autowired
    private RestorentRepository restorentRepository;

    @Autowired
    private  AddressRepository addressRepository;

    @Autowired
    private Userservice userservice;

    //private User

    @Override
    public Resturent createRestorent(CreateRestorentRequest req, User user) {

        Address address = addressRepository.save(req.getAddress());
        Resturent resturent= new Resturent();
        resturent.setAddress(address);
        resturent.setContactImformation(req.getContactInformation());
        resturent.setImages(req.getImages());
        resturent.setCuisineType(req.getCuisineType());
        resturent.setDescription(req.getDiscription());
        resturent.setName(req.getName());
        resturent.setOpeningHours(req.getOpeningHours());
        resturent.setRegistrationDate(LocalDateTime.now());
        resturent.setOwner(user);
        return restorentRepository.save(resturent);
    }

    @Override
    public Resturent updateRestorent(Long restorentId, CreateRestorentRequest updatedRestorent) throws Exception {

        Resturent resturent= findRestorentById(restorentId);
        if(resturent.getCuisineType()!= null){
            resturent.setCuisineType(updatedRestorent.getCuisineType());
        }

        if(resturent.getDescription() != null ){
            resturent.setDescription(updatedRestorent.getDiscription());
        }
        if(resturent.getName() != null){
            resturent.setName(updatedRestorent.getName());
        }
        return restorentRepository.save(resturent);
    }

    @Override
    public void deleteRestorent(Long restorentId) throws Exception {

        Resturent resturent = findRestorentById(restorentId);
        restorentRepository.delete(resturent);

    }

    @Override
    public List<Resturent> getAllResthorents() {
        return  restorentRepository.findAll();
    }

    @Override
    public List<Resturent> serchRestorent(String keyword) {
        return restorentRepository.findBySearchQuary(keyword);
    }

    @Override
    public Resturent findRestorentById(Long id) throws Exception {
        Optional<Resturent> opt= restorentRepository.findById(id);
        if(opt.isEmpty()){
            throw  new Exception("restaurant not find with id " + id );
        }
        return opt.get();
    }

    @Override
    public Resturent getRestorentByUserId(Long userId) throws Exception {
        Resturent resturent= restorentRepository.findByOwnerId(userId);
        if(resturent==null){
            throw  new Exception("restaurant not found with this owner id "+ userId);
        }
        return resturent;
    }

    @Override
    public RestorentsDto addToFavorites(Long restorentId, User user) throws Exception {
        return null;
    }

    @Override
    public Resturent updateRestorentStatus(Long id) throws Exception {
        return null;
    }
}

package com.Dasanayaka.Backend.service;

import com.Dasanayaka.Backend.model.User;

public interface Userservice {

   public  User findUsserByJwtToken(String jwt) throws  Exception;

   public  User findUserByEmai(String  email) throws  Exception;
}

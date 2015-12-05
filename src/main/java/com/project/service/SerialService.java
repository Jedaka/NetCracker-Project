package com.project.service;

import com.project.database.dao.SerialDAO;
import com.project.model.Serial;
import com.project.model.Token;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Юрий on 05.12.2015.
 */
public class SerialService {


    @Autowired
    private SerialDAO serialDAO;
    public Serial getSerialByToken(Token token){
        return serialDAO.getSerialByToken(token);
    }
}

package com.project.service;

import com.project.database.dao.TokenDAO;
import com.project.model.Serial;
import com.project.model.Studio;
import com.project.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jedaka on 12.11.2015.
 */
@Service
@Transactional
public class TokenService {

    @Autowired
    private TokenDAO tokenDAO;

    public int save(Serial serial, Studio studio) {
        return tokenDAO.create(new Token(serial, studio));
    }

    public List getAll() {
        return tokenDAO.getAll();
    }

    @Transactional(readOnly = true)
    public Token get(String token) {
        return tokenDAO.read(token);
    }

    public void update(Token token){
        tokenDAO.update(token);
    }

    public void delete(Token token){
        tokenDAO.delete(token);
    }

    public void setTokenDAO(TokenDAO tokenDAO) {
        this.tokenDAO = tokenDAO;
    }
}

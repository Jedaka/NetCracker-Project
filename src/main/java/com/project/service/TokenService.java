package com.project.service;

import com.project.database.dao.TokenDAO;
import com.project.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by jedaka on 12.11.2015.
 */
@Service
@Transactional
public class TokenService {

    @Autowired
    private TokenDAO tokenDAO;

    public int save(Token token) {
        return tokenDAO.create(token);
    }

    public void update(Token token){
        tokenDAO.update(token);
    }

    public void delete(Token token){
        tokenDAO.delete(token);
    }

    @Transactional(readOnly = true)
    public Set<Token> getAll() {
        return tokenDAO.getAll();
    }

    @Transactional(readOnly = true)
    public Token get(int id) {
        return tokenDAO.read(id);
    }

    @Transactional(readOnly = true)
    public Token findByToken(String token){
        return tokenDAO.findByToken(token);
    }


    public void setTokenDAO(TokenDAO tokenDAO) {
        this.tokenDAO = tokenDAO;
    }
}

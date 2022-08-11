package com.andikscript.simpleusermongodb.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "verify")
public class Verify {

    @Id
    private String id;

    private String IdUser;

    private String verifyCode;

    public Verify() {
    }

    public Verify(String idUser, String verifyCode) {
        IdUser = idUser;
        this.verifyCode = verifyCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}

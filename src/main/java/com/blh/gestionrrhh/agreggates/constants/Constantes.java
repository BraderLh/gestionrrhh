package com.blh.gestionrrhh.agreggates.constants;

public class Constantes {
    private Constantes() {
    }
    //Codes
    public static final Integer CODE_SUCCESS=200;
    public static final Integer CODE_ERROR=400;

    //MESSAGES
    public static final String MESS_SUCCESS="Execution successful";
    public static final String MESS_ERROR="Execution error";
    public static final String MESS_ERROR_LOGIN="Error: Failed authentication, sign up or invalid credentials";
    public static final String MESS_ERROR_DATA_NOT_VALID="Error: Invalid data, ID or field not valid";
    public static final String MESS_NOT_FOUND_ID="Error: Id not exists or is not correct";
    public static final String MESS_ZERO_ROWS="Error: No rows or zero rows for this request";
    public static final String MESS_ERROR_NOT_DELETE = "Error: Fail deleting, something wrong happened";
    public static final String MESS_ERROR_NOT_FIND = "Error: Fail searching, something wrong happened";
    public static final String MESS_ERROR_NOT_UPDATE ="Error: Fail updating, something wrong happened";
    public static final String MESS_ERROR_NON_RENIEC_DATA ="Error: Non exists data in RENIEC";

    //data
    public static final Integer LENGTH_DNI=8;
    public static final Integer LENGTH_RUC=11;
    public static final Integer LENGTH_PHONE_NUMBER = 9;

    //Status
    public static final Integer STATUS_ACTIVE=1;
    public static final Integer STATUS_INACTIVE=0;

    //AUDIT
    public static final String AUDIT_ADMIN="ADMIN";
    public static final String AUDIT_USER= "USER";
}

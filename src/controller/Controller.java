package controller;

import integration.SgDAO;
import integration.SgDBException;
import model.Instrument;
import model.InstrumentException;

import java.util.ArrayList;

/**
 * Author: DENNIS HADZIALIC
 * Date: 2022-01-06
 * Description:
 **/
public class Controller {
    private final SgDAO sgDAO;
    public Controller() throws SgDBException {
        sgDAO = new SgDAO();
    }

    public ArrayList<? extends Instrument> listInstrumentRental(String type) throws InstrumentException {
        if(type == null) throw new InstrumentException("Query for listInstrumentRental with type " + type);
        try{
            return sgDAO.getInstruments(type);
        }catch(SgDBException e){
            throw new InstrumentException("Query for listInstrumentRental with type " + type);
        }
    }
}

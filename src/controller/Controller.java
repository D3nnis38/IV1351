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
        final String baseError = "Query for listInstrumentRental with type " + type + " is unsuccessful";
        if (type == null)
            throw new InstrumentException(baseError);
        try {
            return sgDAO.getInstruments(type);
        } catch (SgDBException e) {
            throw new InstrumentException(baseError);
        }
    }

    public void rentInstrument(String instrumentRentalId, String studentId) throws Exception {
        final String baseError = "Could not rent instrument,";
        if (instrumentRentalId == null) throw new InstrumentException(baseError + " instrumentId was 'null'");
        if (studentId == null) throw new InstrumentException(baseError + " studentId was 'null'");
    }
}

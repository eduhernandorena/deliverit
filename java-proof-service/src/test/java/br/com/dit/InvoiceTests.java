package br.com.dit;

import br.com.dit.entities.Invoice;
import junit.framework.TestCase;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InvoiceTests extends TestCase {
    public void testCalculatePenaltyDateAfter() throws ParseException {
        Invoice i = new Invoice();
        i.setDueDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/12/2019"));
        i.setPaymentDate(new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2019"));
        i.setValue(1000.0);
        i.prePersist();
        assertEquals(0.0, i.getPenalty());
    }

    public void testCalculatePenaltyDateEqual() throws ParseException {
        Invoice i = new Invoice();
        i.setDueDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/12/2019"));
        i.setPaymentDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/12/2019"));
        i.setValue(1000.0);
        i.prePersist();
        assertEquals(0.0, i.getPenalty());
    }

    public void testCalculatePenaltyDateBefore() throws ParseException {
        Invoice i = new Invoice();
        i.setDueDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/12/2019"));
        i.setPaymentDate(new SimpleDateFormat("dd/MM/yyyy").parse("20/12/2019"));
        i.setValue(1000.0);
        i.prePersist();
        assertEquals(50.0, i.getPenalty());
    }

    public void testCalculateInterestDateBefore() throws ParseException {
        Invoice i = new Invoice();
        i.setDueDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/12/2019"));
        i.setPaymentDate(new SimpleDateFormat("dd/MM/yyyy").parse("20/12/2019"));
        i.setValue(1000.0);
        i.prePersist();
        assertEquals(30.0, i.getInterest());
    }

    public void testCalculateInterestDateAfter() throws ParseException {
        Invoice i = new Invoice();
        i.setDueDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/12/2019"));
        i.setPaymentDate(new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2019"));
        i.setValue(1000.0);
        i.prePersist();
        assertEquals(0.0, i.getInterest());
    }

    public void testCalculateInterestDateEqual() throws ParseException {
        Invoice i = new Invoice();
        i.setDueDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/12/2019"));
        i.setPaymentDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/12/2019"));
        i.setValue(1000.0);
        i.prePersist();
        assertEquals(0.0, i.getInterest());
    }

    public void testCalculateCorrectedValueDateBefore() throws ParseException {
        Invoice i = new Invoice();
        i.setDueDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/12/2019"));
        i.setPaymentDate(new SimpleDateFormat("dd/MM/yyyy").parse("20/12/2019"));
        i.setValue(1000.0);
        i.prePersist();
        assertEquals(1080.0, i.getCorrectedValue());
    }

    public void testCalculateCorrectedValueDateAfter() throws ParseException {
        Invoice i = new Invoice();
        i.setDueDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/12/2019"));
        i.setPaymentDate(new SimpleDateFormat("dd/MM/yyyy").parse("09/12/2019"));
        i.setValue(1000.0);
        i.prePersist();
        assertEquals(1000.0, i.getCorrectedValue());
    }

    public void testCalculateCorrectedValueDateEqual() throws ParseException {
        Invoice i = new Invoice();
        i.setDueDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/12/2019"));
        i.setPaymentDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/12/2019"));
        i.setValue(1000.0);
        i.prePersist();
        assertEquals(1000.0, i.getCorrectedValue());
    }
}

package br.com.dit.controllers;

import br.com.dit.entities.Invoice;
import br.com.dit.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("invoices")
@CrossOrigin("*")
public class InvoiceController {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @GetMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return new ResponseEntity<>(invoiceRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"/{id}", "{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Invoice> getAllInvoices(@PathVariable Long id) {
        Optional<Invoice> optInvoice = invoiceRepository.findById(id);
        return optInvoice.map(invoice -> new ResponseEntity<>(invoice, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = {"", "/"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Invoice> create(@RequestBody @Valid Invoice invoice) {
        return new ResponseEntity<>(invoiceRepository.save(invoice), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/{id}", "/{id}/"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Invoice> delete(@PathVariable Long id) {
        invoiceRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

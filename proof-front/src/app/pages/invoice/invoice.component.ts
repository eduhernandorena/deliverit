import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {environment} from '../../../environments/environment';
import {ConnectService} from '../../services/connect-service.service';

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {
  public dataSource = new MatTableDataSource();
  public displayedColumns: string[] = ['id', 'name', 'value', 'dueDate', 'paymentDate', 'correctedValue', 'penalty', 'interest'];

  constructor(private connectService: ConnectService) {
  }

  ngOnInit() {
    this.getInvoices();
  }

  getInvoices() {
    this.connectService.getGenericAll(environment.paths.invoices).then(res => {
      this.dataSource = res;
    }).catch(error => {
      console.error(error);
    });
  }

}

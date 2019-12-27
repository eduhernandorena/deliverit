import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import * as moment from 'moment';
import {Moment} from 'moment';
import {environment} from '../../../../environments/environment';
import {ConnectService} from '../../../services/connect-service.service';


@Component({
  selector: 'app-invoice-create',
  templateUrl: './invoice-create.component.html',
  styleUrls: ['./invoice-create.component.css']
})
export class InvoiceCreateComponent implements OnInit {

  public invoiceForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private connectService: ConnectService,
  ) {
  }

  ngOnInit() {
    this.invoiceForm = this.formBuilder.group({
      name: ['', Validators.required],
      dueDate: ['', Validators.required],
      paymentDate: ['', Validators.required],
      value: [0.00, Validators.required],
      correctedValue: [0.00],
      penalty: [0],
      interest: [0],
      daysLate: [0],
    });
  }

  correctValue() {
    if (this.invoiceForm.valid) {
      const dayInMilli = 86400000;
      const controls = this.invoiceForm.controls;
      const dueDate: Moment = moment(controls.dueDate.value);
      const paymentDate: Moment = moment(controls.paymentDate.value);
      if (dueDate.isBefore(paymentDate)) {
        const diff = (dueDate.diff(paymentDate) / dayInMilli) * (-1);
        controls.daysLate.setValue(diff);
        let penalty: number;
        let interest: number;
        if (diff > 0 && diff <= 3) {
          penalty = 2.0;
          interest = diff * 0.1;
        } else if (diff > 3 && diff <= 5) {
          penalty = 3.0;
          interest = diff * 0.2;
        } else if (diff > 5) {
          penalty = 5.0;
          interest = diff * 0.3;
        }
        if (penalty > 0 && interest > 0) {
          controls.penalty.setValue(Number(controls.value.value) * (penalty / 100));
          controls.interest.setValue(Number(controls.value.value) * (interest / 100));

          const correctValue: number = Number(controls.value.value) +
            Number(controls.penalty.value) +
            Number(controls.interest.value);
          controls.correctedValue.setValue(correctValue);
        }
      } else {
        controls.correctedValue.setValue(controls.value.value);
        controls.penalty.setValue(0);
        controls.interest.setValue(0);
        controls.daysLate.setValue(0);
      }
    }
  }

  public save() {
    this.connectService.postGeneric(environment.paths.invoices, this.invoiceForm.value).then(res => {
      console.log(res);
      window.location.reload();
    }).catch(error => {
      console.error(error);
    });
  }
}

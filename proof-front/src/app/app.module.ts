import {BrowserModule} from '@angular/platform-browser';
import {forwardRef, NgModule, LOCALE_ID} from '@angular/core';

import {AppComponent} from './app.component';
import {InvoiceComponent} from './pages/invoice/invoice.component';
import {InvoiceCreateComponent} from './pages/invoice/invoice-create/invoice-create.component';
import {ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  MatButtonModule, MatDatepickerModule,
  MatFormFieldModule, MatInputModule, MatNativeDateModule,
  MatPaginatorIntl,
  MatPaginatorModule,
  MatTableModule
} from '@angular/material';
import {MatPaginatorIntlPortuguese} from './paginator/mat-paginator-intl-portuguese';

import {registerLocaleData} from '@angular/common';
import ptBr from '@angular/common/locales/pt';
import {HttpClient, HttpClientModule} from '@angular/common/http';

registerLocaleData(ptBr)

@NgModule({
  declarations: [
    AppComponent,
    InvoiceComponent,
    InvoiceCreateComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatPaginatorModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    HttpClientModule
  ],
  providers: [{provide: LOCALE_ID, useValue: 'pt-BR'}, {provide: MatPaginatorIntl, useClass: forwardRef(() => MatPaginatorIntlPortuguese)}],
  bootstrap: [AppComponent]
})
export class AppModule {
}

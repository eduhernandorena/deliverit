import { Injectable } from '@angular/core';
import { MatPaginatorIntl } from '@angular/material';

@Injectable()
export class MatPaginatorIntlPortuguese extends MatPaginatorIntl {
  itemsPerPageLabel = 'Itens por página: ';
  nextPageLabel = 'Próximo';
  previousPageLabel = 'Anterior';
  firstPageLabel = 'Primeira página';
  lastPageLabel = 'Última página';

  getRangeLabel = (page: number, pageSize: number, length: number) => {
    const currentPage = (page * pageSize) + 1;
    const currentPageSize = ((page * pageSize) + pageSize);
    return currentPage + ' - ' + (currentPageSize <= length ? currentPageSize : length) + ' de ' + length;
  }
}

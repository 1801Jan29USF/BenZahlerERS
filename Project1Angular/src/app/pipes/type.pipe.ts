import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'typePipe'
})
export class TypePipe implements PipeTransform {

  transform(value: number): String {
    if (value === 0) {
      return 'Lodging';
    } else if (value === 1) {
      return 'Travel';
    } else if (value === 2) {
      return 'Food';
    } else if (value === 3) {
      return 'Other';
    } else {
      return 'Undefined';
    }
  }
}

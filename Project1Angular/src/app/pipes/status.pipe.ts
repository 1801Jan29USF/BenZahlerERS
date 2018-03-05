import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'statusPipe'
})
export class StatusPipe implements PipeTransform {

  transform(value: number): String {
    if (value === 0) {
      return 'Pending';
    } else if (value === 1) {
      return 'Approved';
    } else if (value === 2) {
      return 'Denied';
    } else {
      return 'Undefined';
    }
  }

}

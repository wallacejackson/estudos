import { Component, OnInit } from '@angular/core';
import { Summary } from 'src/app/model/summary.model';
import { TicketService } from 'src/app/services/ticket.service';
import { ResponseApi } from 'src/app/model/response-api.model';

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.css']
})
export class SummaryComponent implements OnInit {

  summary: Summary = new Summary();
  message: {};
  classCss: {};

  constructor(
    private ticketService: TicketService
  ) { }

  ngOnInit() {
    this.ticketService.summary().subscribe((responseAPI: ResponseApi) => {
      this.summary = responseAPI.data;
    }, err => {
      this.showMessage({
        type: 'error',
        text: err['error']['erros'][0]
      });
    });
  }

  private showMessage(message: { type: string, text: string }): void {
    this.message = message;
    this.buildClasses(message.type);
    setTimeout(() => {
      this.message = undefined
    }, 3000);
  }

  private buildClasses(type: string): void {
    this.classCss = {
      'alert': true
    }
    this.classCss['alert-' + type] = true;
  }

}

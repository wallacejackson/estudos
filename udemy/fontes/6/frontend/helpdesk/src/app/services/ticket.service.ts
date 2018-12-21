import { HELP_DESK_API } from './helpdesk.api';
import { Ticket } from './../model/ticket.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tick } from '@angular/core/src/render3';
import { ThrowStmt } from '@angular/compiler';

@Injectable()
export class TicketService {

  constructor(private http: HttpClient) { }

  createOrUpdate(ticket: Ticket) {
    if (ticket.id != null && ticket.id != '') {
      return this.http.put(`${HELP_DESK_API}/api/ticket`, ticket);
    } else {
      ticket.status = 'NEW';
      return this.http.post(`${HELP_DESK_API}/api/ticket`, ticket);
    }
  }

  findAll(page: number, count: number) {
    return this.http.get(`${HELP_DESK_API}/api/ticket/${page}/${count}`);
  }

  findById(id: string) {
    return this.http.get(`${HELP_DESK_API}/api/ticket/${id}`);
  }

  deleteById(id: string) {
    return this.http.delete(`${HELP_DESK_API}/api/ticket/${id}`);
  }

  findByParams(page: number, count: number, assignedToMe: boolean, ticket: Ticket) {
    ticket.number = ticket.number == null ? 0 : ticket.number;
    ticket.title = ticket.title == '' ? 'uninformed' : ticket.title;
    ticket.status = ticket.status == '' ? 'uninformed' : ticket.status;
    ticket.priority = ticket.priority == '' ? 'uninformed' : ticket.priority;

    return this.http.get(`${HELP_DESK_API}/api/ticket/${page}/${count}/${ticket.number}/${ticket.title}/${ticket.status}/${ticket.priority}/${assignedToMe}`);
  }

  changeStaus(status: string, ticket: Ticket) {
    return this.http.put(`${HELP_DESK_API}/api/ticket/${ticket.id}/${status}`, ticket);
  }

  summary() {
    return this.http.get(`${HELP_DESK_API}/api/ticket/summary`);
  }
}

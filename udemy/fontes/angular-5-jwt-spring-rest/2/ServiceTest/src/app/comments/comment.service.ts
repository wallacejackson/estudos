import { Comment } from './comment.modelo';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';


@Injectable()
export class CommentService {
  constructor(private http: Http) { }

  getComments(): Observable<Comment[]> {
    return this.http.get('https://jsonplaceholder.typicode.com/comments').pipe(map(res => res.json()));
  }

}
import { CommentService } from './comment.service';
import { Comment } from './comment.modelo';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  comments: Comment[];

  constructor(private CommentService: CommentService) { }

  ngOnInit() {
    this.CommentService.getComments().subscribe(comments => this.comments = comments);
  }

}

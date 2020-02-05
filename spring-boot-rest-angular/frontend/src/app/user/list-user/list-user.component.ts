import {Component, OnInit} from '@angular/core';
import {User} from '../../model/user.model';
import {Router} from '@angular/router';
import {ApiService} from '../../service/api.service';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {
  users: User[];

  constructor(private router: Router, private apiService: ApiService) {
  }

  ngOnInit() {
    this.apiService.getUsers().subscribe(data => {
      const result = data.result;
      this.users = result.content;
    });
  }

  addUser(): void {
    this.router.navigate(['add-user']);
  }

  deleteUser(user: User): void {
    this.apiService.deleteUser(user.id).subscribe(data => {
      this.users = this.users.filter(u => u !== user);
    });
  }

  editUser(user: User): void {
    this.router.navigate(['edit-user', user.id]);
  }
}

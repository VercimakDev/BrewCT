import { Component } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-prompt-input',
  templateUrl: './prompt-input.component.html',
  styleUrls: ['./prompt-input.component.css']
})
export class PromptInputComponent {
  userInput: string = '';
  responseContent: string = '';
  loading: boolean = false;

  constructor(private http: HttpClient) { }

  sendPrompt() {
    this.loading = true;
    // Create URL parameters for the GET request
    const params = new HttpParams().set('prompt', this.userInput);

    // Send the GET request to your backend
    this.http.get<any>('http://localhost:8080/api/v1/chat', { params }).subscribe(response => {
      // Handle the response from the backend as needed
      this.responseContent = response.choices[0].message.content;
      this.loading = false;
    });
  }
}

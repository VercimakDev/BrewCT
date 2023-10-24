import { Component } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import {SharedService} from "../shared.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-prompt-input',
  templateUrl: './prompt-input.component.html',
  styleUrls: ['./prompt-input.component.css']
})
export class PromptInputComponent {
  prompts: any[] = [];
  currentPromptIndex: number = 0;
  userInput: string = '';
  responseContent: string = '';
  loading: boolean = false;
  executed: boolean = false;
  responseRating: number = 5;
  responseFeedback: string = '';

  constructor(private http: HttpClient,
              private sharedService: SharedService,
              private router: Router) { }
  ngOnInit() {
    // Retrieve prompts from the shared service
    this.prompts = this.sharedService.getPrompts();

    // Pre-fill userInput with the text from the first prompt
    this.updateUserInput();
  }

  sendPrompt() {
    this.loading = true;
    // Create URL parameters for the GET request
    const params = new HttpParams().set('prompt', this.userInput);

    // Send the GET request to your backend
    this.http.get<any>('http://localhost:8080/api/v1/chat', { params }).subscribe(response => {
      // Handle the response from the backend as needed
      this.responseContent = response.choices[0].message.content;
      this.loading = false;
      this.executed = true;
    });
  }

  // send a POST Request to the feeback endpoint, the body containing the current prompt, responserating, and responsefeedback
  sendFeedback() {
    const body = {prompt: this.prompts[this.currentPromptIndex], responseText: this.responseContent, rating: this.responseRating, feedback: this.responseFeedback};
    this.http.post<any>('http://localhost:8080/api/v1/feedback', body).subscribe(response => {
    });
  }
  nextPrompt() {
    this.sendFeedback()
    // Move to the next prompt
    if (this.currentPromptIndex < this.prompts.length - 1) {
      this.currentPromptIndex++;
      this.updateUserInput();
    }

  }

  updateUserInput() {
    // Set userInput with the text of the current prompt
    this.userInput = this.prompts[this.currentPromptIndex].text;
    this.responseContent = '';
    this.executed = false;
    this.responseRating = 5;
    this.responseFeedback = '';
    }

  navigateToUpload() {
    this.sendFeedback();
    this.router.navigate(['/uploader']);
  }
}

import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class SharedService {
  prompts: any[] = [];

  setPrompts(prompts: any[]) {
    this.prompts = prompts;
  }

  getPrompts() {
    return this.prompts;
  }
}

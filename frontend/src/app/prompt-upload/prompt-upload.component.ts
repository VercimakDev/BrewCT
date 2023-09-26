import { Component } from '@angular/core';
import {SharedService} from "../shared.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-prompt-upload',
  templateUrl: './prompt-upload.component.html',
  styleUrls: ['./prompt-upload.component.css']
})
export class PromptUploadComponent {
  prompts: Prompt[] = [];
  constructor(private sharedService: SharedService,
              private router: Router) {}
  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    const reader: FileReader = new FileReader();

    reader.onload = (e) => {
      const xmlText: string | ArrayBuffer = e.target?.result as string;
      this.parseXml(xmlText);
    };

    reader.readAsText(file);
  }

  private parseXml(xmlText: string) {
    const parser = new DOMParser();
    const xmlDoc = parser.parseFromString(xmlText, 'text/xml');
    const promptElements = xmlDoc.getElementsByTagName('prompt');

    for (let i = 0; i < promptElements.length; i++) {
      const promptElement = promptElements[i];
      const id = promptElement.getAttribute('id');
      const text = promptElement.textContent || '';
      if (id) {
        this.prompts.push({id, text});
      }
    }
    this.sharedService.setPrompts(this.prompts);
  }

  navigateToInput() {
    this.router.navigate(['/input']);
  }
}


import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PromptUploadComponent } from './prompt-upload/prompt-upload.component';
import { PromptInputComponent } from './prompt-input/prompt-input.component';

const routes: Routes = [
  { path: '', redirectTo: '/uploader', pathMatch: 'full' },
  { path: 'uploader', component: PromptUploadComponent },
  { path: 'input', component: PromptInputComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

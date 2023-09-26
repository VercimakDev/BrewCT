import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PromptUploadComponent } from './prompt-upload.component';

describe('PromptUploadComponent', () => {
  let component: PromptUploadComponent;
  let fixture: ComponentFixture<PromptUploadComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PromptUploadComponent]
    });
    fixture = TestBed.createComponent(PromptUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

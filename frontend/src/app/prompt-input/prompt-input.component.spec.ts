import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PromptInputComponent } from './prompt-input.component';

describe('PromptInputComponent', () => {
  let component: PromptInputComponent;
  let fixture: ComponentFixture<PromptInputComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PromptInputComponent]
    });
    fixture = TestBed.createComponent(PromptInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

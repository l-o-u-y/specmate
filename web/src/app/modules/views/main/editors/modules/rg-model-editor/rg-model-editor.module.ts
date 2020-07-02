import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { GraphicalEditorModule } from '../graphical-editor/graphical-editor.module';
import { RGModelDetails } from './components/rg-model-details.component';

@NgModule({
  imports: [
    // MODULE IMPORTS
    GraphicalEditorModule,
    BrowserModule
  ],
  declarations: [
    // COMPONENTS IN THIS MODULE
      RGModelDetails
  ],
  exports: [
    // THE COMPONENTS VISIBLE TO THE OUTSIDE
      RGModelDetails
  ],
  providers: [
    // SERVICES
  ],
  bootstrap: [
    // COMPONENTS THAT ARE BOOTSTRAPPED HERE
  ]
})

export class RGModelEditorModule { }

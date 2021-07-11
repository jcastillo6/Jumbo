import { AbstractControl, ValidationErrors } from "@angular/forms";
import { Store } from "../model/store";

export function jsonValidator(control: AbstractControl): ValidationErrors | null {
    try {
      let object:Store[]=JSON.parse(control.value);
    } catch (e) {
      return { jsonInvalid: true };
    }
  
    return null;
  };
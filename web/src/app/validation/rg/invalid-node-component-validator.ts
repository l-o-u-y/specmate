import { RGNode } from '../../model/RGNode';
import { IContainer } from '../../model/IContainer';
import { ElementValidatorBase } from '../element-validator-base';
import { ValidationMessage } from '../validation-message';
import { ValidationResult } from '../validation-result';
import { Validator } from '../validator-decorator';
import { ValidationUtil } from '../validation-util';
import { ValidationErrorSeverity } from '../validation-error-severity';

/**
 * Detects nodes in a model that have a character in the variable name that is not allowed for
 * element names. As from the variable name Specmate contstruct Testparameters with the same name, this
 * would cause problems.
 */

@Validator(RGNode)
export class InvalidNodeComponentValidator extends ElementValidatorBase<RGNode> {

    public validate(element: RGNode, contents: IContainer[]): ValidationResult {

        if (!this.isValidNode(element)) {
            let message = ValidationMessage.ERROR_INVALID_VARIABLE;
            return new ValidationResult(message, false, [element], ValidationErrorSeverity.SAVE_DISABLED);
        }
        return ValidationResult.VALID;

    }

    /* Checks if a RG node has a valid variable name */
    private isValidNode(element: RGNode): boolean {
        if (element === undefined || element.component === undefined) {
            return true;
        }
        if (!ValidationUtil.isValidName(element.component)) {
            return false;
        }
        return true;
    }
}

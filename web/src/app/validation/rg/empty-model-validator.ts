import { RGModel } from '../../model/RGModel';
import { RGNode } from '../../model/RGNode';
import { IContainer } from '../../model/IContainer';
import { Type } from '../../util/type';
import { ElementValidatorBase } from '../element-validator-base';
import { ValidationMessage } from '../validation-message';
import { ValidationResult } from '../validation-result';
import { Validator } from '../validator-decorator';

@Validator(RGModel)
export class EmptyModelValidator extends ElementValidatorBase<RGModel> {
    public validate(element: RGModel, contents: IContainer[]): ValidationResult {
        const valid: boolean = contents.some((element: IContainer) => Type.is(element, RGNode));
        if (valid) {
            return ValidationResult.VALID;
        }
        return new ValidationResult(ValidationMessage.ERROR_EMPTY_MODEL, false, [element]);
    }
}

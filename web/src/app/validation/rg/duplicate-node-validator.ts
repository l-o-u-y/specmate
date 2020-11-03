import { RGModel } from '../../model/RGModel';
import { RGNode } from '../../model/RGNode';
import { IContainer } from '../../model/IContainer';
import { Type } from '../../util/type';
import { ElementValidatorBase } from '../element-validator-base';
import { ValidationMessage } from '../validation-message';
import { ValidationResult } from '../validation-result';
import { Validator } from '../validator-decorator';
import { ValidationUtil } from '../validation-util';

@Validator(RGModel)
export class DuplicateNodeValidator extends ElementValidatorBase<RGModel> {
    public validate(element: RGModel, contents: IContainer[]): ValidationResult {
        let nodes: RGNode[] =
            contents.filter((element: IContainer) => Type.is(element, RGNode)).map((element: IContainer) => element as RGNode);
        let duplicates: Set<RGNode> = new Set();
        for (let i = 0; i < nodes.length; i++) {
            let currentNode: RGNode = nodes[i];
            let currentDuplicates: RGNode[] =
                nodes.filter((otherNode: RGNode) =>
                    currentNode.type !== 'ACTION' &&
                    ValidationUtil.compareStrTrimmed(otherNode.component, currentNode.component) &&
                    otherNode !== currentNode &&
                    !duplicates.has(otherNode));
            currentDuplicates.forEach( node => duplicates.add(node));
        }
        let dupList = Array.from(duplicates.keys());
        if (dupList.length > 0) {
            return new ValidationResult(ValidationMessage.ERROR_DUPLICATE_NODE, false, dupList);
        }
        return ValidationResult.VALID;
    }
}

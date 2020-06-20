import { RGModel } from '../../model/RGModel';
import { RGNode } from '../../model/RGNode';
import { IContainer } from '../../model/IContainer';
import { Type } from '../../util/type';
import { ElementValidatorBase } from '../element-validator-base';
import { ValidationMessage } from '../validation-message';
import { ValidationResult } from '../validation-result';
import { Validator } from '../validator-decorator';

@Validator(RGModel)
export class SingleNodesValidator extends ElementValidatorBase<RGModel> {

    public validate(element: RGModel, contents: IContainer[]): ValidationResult {
        let invalidNodes: IContainer[] = contents.filter((element: IContainer) => {
            if (!Type.is(element, RGNode)) {
                return false;
            }
            let node: RGNode = element as RGNode;
            let hasIncomingConnections: boolean = node.incomingConnections && node.incomingConnections.length > 0;
            let hasOutgoingConnections: boolean = node.outgoingConnections && node.outgoingConnections.length > 0;
            return !hasIncomingConnections && !hasOutgoingConnections;
        });

        if (invalidNodes.length === 0) {
            return ValidationResult.VALID;
        }
        return new ValidationResult(ValidationMessage.ERROR_UNCONNECTED_NODE, false, invalidNodes);
    }
}

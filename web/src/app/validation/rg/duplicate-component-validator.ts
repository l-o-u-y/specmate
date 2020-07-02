import { RGModel } from '../../model/RGModel';
import { RGNode } from '../../model/RGNode';
import { IContainer } from '../../model/IContainer';
import { Type } from '../../util/type';
import { ElementValidatorBase } from '../element-validator-base';
import { ValidationMessage } from '../validation-message';
import { ValidationResult } from '../validation-result';
import { Validator } from '../validator-decorator';

type IO = ('input' | 'output');

@Validator(RGModel)
export class DuplicateComponentValidator extends ElementValidatorBase<RGModel> {
    public validate(element: RGModel, contents: IContainer[]): ValidationResult {

        const nodeMap: { [component: string]: IContainer[] } = {};
        const typeMap: { [component: string]: IO[] } = {};
        let invalidNodes: IContainer[] = [];

        for (let content of contents) {
            if (!Type.is(content, RGNode)) {
                continue;
            }

            const node: RGNode = content as RGNode;

            let type: IO;
            if (!node.incomingConnections || node.incomingConnections.length <= 0) {
                type = 'input';
            } else if (!node.outgoingConnections || node.outgoingConnections.length <= 0) {
                type = 'output';
            }

            if (typeMap[node.component.trim().toLowerCase()] === undefined) {
                typeMap[node.component.trim().toLowerCase()] = [];
            }
            if (typeMap[node.component.trim().toLowerCase()].indexOf(type) < 0) {
                typeMap[node.component.trim().toLowerCase()].push(type);
            }

            if (nodeMap[node.component.trim().toLowerCase()] === undefined) {
                nodeMap[node.component.trim().toLowerCase()] = [];
            }
            nodeMap[node.component.trim().toLowerCase()].push(node);
        }

        for (const component in typeMap) {
            if (typeMap[component].length > 1) {
                invalidNodes = invalidNodes.concat(nodeMap[component]);
            }
        }

        if (invalidNodes.length > 0) {
            return new ValidationResult(ValidationMessage.ERROR_DUPLICATE_IO_COMPONENT, false, invalidNodes);
        }
        return ValidationResult.VALID;
    }
}

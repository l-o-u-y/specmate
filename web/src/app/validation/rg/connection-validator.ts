import { RGModel } from '../../model/RGModel';
import { RGNode } from '../../model/RGNode';
import { IContainer } from '../../model/IContainer';
import { Type } from '../../util/type';
import { ElementValidatorBase } from '../element-validator-base';
import { ValidationMessage } from '../validation-message';
import { ValidationResult } from '../validation-result';
import { Validator } from '../validator-decorator';
import {RGConnection} from '../../model/RGConnection';

@Validator(RGModel)
export class ConnectionValidator extends ElementValidatorBase<RGModel> {

    public validate(element: RGModel, contents: IContainer[]): ValidationResult {
        // TODO MA this validation is kinda wrong because its alllowed to have multiple Condition connections...
        return ValidationResult.VALID;
        let invalidNodes: IContainer[] = contents.filter((element: IContainer) => {
            if (!Type.is(element, RGNode)) {
                return false;
            }

            let node: RGNode = element as RGNode;
                let incomingConnections = node.incomingConnections.filter(e => {
                    let con = contents.find(c => c.url == e.url) as RGConnection;
                    if (con && con.type == 'Condition') {
                        return true;
                    }
                    return false;
                })
                let outgoingConnections = node.outgoingConnections.filter(e => {
                    let con = contents.find(c => c.url == e.url) as RGConnection;
                    if (con && con.type == 'Condition') {
                        return true;
                    }
                    return false;
                })

                console.log(incomingConnections)
                console.log(outgoingConnections)
            if (incomingConnections.length <= 1 &&
                outgoingConnections.length <= 1 ) {
                return false;
            } else {
                    return true;
            }
        });

        if (invalidNodes.length === 0) {
            return ValidationResult.VALID;
        }
        return new ValidationResult(ValidationMessage.ERROR_NODE_MULTIPLE_CONDITION_CONNECTIONS, false, invalidNodes);
    }
}

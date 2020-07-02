import { RGModel } from '../../../../../../../../model/RGModel';
import { DeleteToolBase } from '../delete-tool-base';

export class RGDeleteTool extends DeleteToolBase {
    protected modelType: { className: string; } = RGModel;
}

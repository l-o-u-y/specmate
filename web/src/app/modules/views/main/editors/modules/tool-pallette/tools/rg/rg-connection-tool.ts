import { RGConnectionFactory } from '../../../../../../../../factory/rg-connection-factory';
import { ElementFactoryBase } from '../../../../../../../../factory/element-factory-base';
import { RGConnection } from '../../../../../../../../model/RGConnection';
import { RGModel } from '../../../../../../../../model/RGModel';
import { IModelConnection } from '../../../../../../../../model/IModelConnection';
import { IModelNode } from '../../../../../../../../model/IModelNode';
import { ConnectionToolBase } from '../connection-tool-base';

export class RGConnectionTool extends ConnectionToolBase<RGConnection> {

    protected modelType: { className: string; } = RGModel;

    public negated = false;

    public name = 'tools.addRgConnection';
    public icon = 'sitemap';
    public style = '';

    protected getFactory(e1: IModelNode, e2: IModelNode): ElementFactoryBase<IModelConnection> {
        return new RGConnectionFactory(e1, e2, this.dataService, this.negated);
    }
}

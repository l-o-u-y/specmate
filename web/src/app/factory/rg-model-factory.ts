import { Config } from '../config/config';
import { RGModel } from '../model/RGModel';
import { IContainer } from '../model/IContainer';
import { ElementFactoryBase } from './element-factory-base';
import { ModelFactoryBase } from './model-factory-base';

export class RGModelFactory extends ModelFactoryBase {
    protected get simpleModel(): IContainer {
        return new RGModel();
    }

    protected get name(): string {
        return Config.RG_NEW_MODEL_NAME + ' ' + ElementFactoryBase.getDateStr();
    }

    protected get description(): string {
        return Config.RG_NEW_MODEL_DESCRIPTION;
    }
}

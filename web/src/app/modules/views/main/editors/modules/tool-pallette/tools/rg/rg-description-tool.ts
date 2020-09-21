import {CreateNodeToolBase} from '../create-node-tool-base';
import {RGNode} from '../../../../../../../../model/RGNode';
import {RGModel} from '../../../../../../../../model/RGModel';
import {ShapeProvider} from '../../../graphical-editor/providers/properties/shape-provider';
import {ElementFactoryBase} from '../../../../../../../../factory/element-factory-base';
import {RGNodeFactory} from '../../../../../../../../factory/rg-node-factory';
import {ToolBase} from '../tool-base';
import {SpecmateDataService} from '../../../../../../../data/modules/data-service/services/specmate-data.service';
import {SelectedElementService} from '../../../../../../side/modules/selected-element/services/selected-element.service';
import {IContainer} from '../../../../../../../../model/IContainer';
import {ConfirmationModal} from '../../../../../../../notification/modules/modals/services/confirmation-modal.service';
import {TranslateService} from '@ngx-translate/core';

type Literal = IContainer & {content: string[]};

export class RgDescriptionTool extends ToolBase {

    protected modelType: { className: string; } = RGModel;

    public icon = 'arrow-alt-circle-up';
    public name = 'tools.generateDescription';
    public isVertexTool: boolean = undefined;
    public color = 'primary';
    public isHidden = false;
    public style = new ShapeProvider(RGModel).getStyle(RGNode);


    constructor(dataService: SpecmateDataService, selectedElementService: SelectedElementService,
                parent: IContainer, private modalService: ConfirmationModal, private translate: TranslateService) {
        super(dataService, selectedElementService, parent);
        this.parent = parent as RGModel;
        this.dataService = dataService;
        this.modalService = modalService;
    }

    public perform(): Promise<any> {
        // TODO MA
        return this.dataService.readElement(this.parent.url + '/text', false)
            .then((value: Literal) => {
                console.log(value.content[0]);
                this.modalService.openOk('', value.content[0]);
            });
    }
}

import { IContainer } from '../../../../../../../../model/IContainer';
import { SpecmateDataService } from '../../../../../../../data/modules/data-service/services/specmate-data.service';
import { SelectedElementService } from '../../../../../../side/modules/selected-element/services/selected-element.service';
import { CEGLayoutTool } from '../../../tool-pallette/tools/ceg/ceg-layout-tool';
import { CEGNodeTool } from '../../../tool-pallette/tools/ceg/ceg-node-tool';
import { RGLayoutTool } from '../../../tool-pallette/tools/rg/rg-layout-tool';
import { RGNodeTool } from '../../../tool-pallette/tools/rg/rg-node-tool';
import { DecisionTool } from '../../../tool-pallette/tools/process/decision-tool';
import { EndTool } from '../../../tool-pallette/tools/process/end-tool';
import { ProcessConnectionTool } from '../../../tool-pallette/tools/process/process-connection-tool';
import { ProcessDeleteTool } from '../../../tool-pallette/tools/process/process-delete-tool';
import { StartTool } from '../../../tool-pallette/tools/process/start-tool';
import { StepTool } from '../../../tool-pallette/tools/process/step-tool';
import { ToolBase } from '../../../tool-pallette/tools/tool-base';
import { ProviderBase } from './provider-base';
import { mxgraph } from 'mxgraph';
import { CEGConnectionTool } from '../../../tool-pallette/tools/ceg/ceg-connection-tool';
import { CEGDeleteTool } from '../../../tool-pallette/tools/ceg/ceg-delete-tool';
import { RGConnectionTool } from '../../../tool-pallette/tools/rg/rg-connection-tool';
import { RGDeleteTool } from '../../../tool-pallette/tools/rg/rg-delete-tool';
import { ConfirmationModal } from '../../../../../../../../../app/modules/notification/modules/modals/services/confirmation-modal.service';
import { TranslateService } from '@ngx-translate/core';
import { ProcessLayoutTool } from '../../../tool-pallette/tools/process/process-layout-tool';
import {RgDescriptionTool} from '../../../tool-pallette/tools/rg/rg-description-tool';
import {RgUpdateTool} from '../../../tool-pallette/tools/rg/rg-update-tool';

const mx: typeof mxgraph = require('mxgraph')({
    mxBasePath: 'mxgraph'
});

export class ToolProvider extends ProviderBase {

    private _tools: ToolBase[];

    constructor(
        private model: IContainer,
        private dataService: SpecmateDataService,
        private selectedElementService: SelectedElementService,
        private modal: ConfirmationModal,
        private translate: TranslateService) {
        super(model);
    }

    public get tools(): ToolBase[] {
        if (this._tools) {
            return this._tools;
        }
        if (this.isCEGModel) {
            this.createToolsForCEGModel();
        } else if (this.isRGModel) {
            this.createToolsForRGModel();
        } else if (this.isProcessModel) {
            this.createToolsForProcess();
        } else {
            this.createEmptyTools();
        }

        return this._tools;
    }

    private createEmptyTools(): void {
        this._tools = [];
    }

    private createToolsForCEGModel(): void {
        this._tools = [
            new CEGNodeTool(this.dataService, this.selectedElementService, this.model),
            new CEGLayoutTool(this.dataService, this.selectedElementService, this.model, this.modal, this.translate),
            new CEGConnectionTool(this.dataService, this.selectedElementService, this.model),
            new CEGDeleteTool(this.model, this.dataService, this.selectedElementService)
        ];
    }

    private createToolsForRGModel(): void {
        this._tools = [
            new RGNodeTool(this.dataService, this.selectedElementService, this.model),
            new RGLayoutTool(this.dataService, this.selectedElementService, this.model, this.modal, this.translate),
            new RgDescriptionTool(this.dataService, this.selectedElementService, this.model, this.modal, this.translate),
            new RgUpdateTool(this.dataService, this.selectedElementService, this.model, this.modal, this.translate),
            new RGConnectionTool(this.dataService, this.selectedElementService, this.model),
            new RGDeleteTool(this.model, this.dataService, this.selectedElementService)
        ];
    }

    private createToolsForProcess(): void {
        this._tools = [
            new StepTool(this.dataService, this.selectedElementService, this.model),
            new DecisionTool(this.model, this.dataService, this.selectedElementService),
            new StartTool(this.model, this.dataService, this.selectedElementService),
            new EndTool(this.model, this.dataService, this.selectedElementService),
            new ProcessConnectionTool(this.dataService, this.selectedElementService, this.model),
            new ProcessDeleteTool(this.model, this.dataService, this.selectedElementService),
            new ProcessLayoutTool(this.dataService, this.selectedElementService, this.model)
        ];
    }

    public getDefaultTool(contents: IContainer[]): ToolBase {
        return contents && contents.length > 0 ? this.tools[0] : this.tools[1];
    }
}

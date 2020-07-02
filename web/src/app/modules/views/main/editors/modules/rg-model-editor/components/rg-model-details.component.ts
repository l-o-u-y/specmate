import { Component, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { RGModel } from '../../../../../../../model/RGModel';
import { IContainer } from '../../../../../../../model/IContainer';
import { SpecmateDataService } from '../../../../../../data/modules/data-service/services/specmate-data.service';
import { NavigatorService } from '../../../../../../navigation/modules/navigator/services/navigator.service';
import { ConfirmationModal } from '../../../../../../notification/modules/modals/services/confirmation-modal.service';
import { SpecmateViewBase } from '../../../base/specmate-view-base';
import { GraphicalEditor } from '../../graphical-editor/components/graphical-editor.component';

@Component({
    moduleId: module.id.toString(),
    selector: 'rg-model-details-editor',
    templateUrl: 'rg-model-details.component.html',
    styleUrls: ['rg-model-details.component.css']
})

export class RGModelDetails extends SpecmateViewBase {

    public model: RGModel;
    private contents: IContainer[];

    @ViewChild(GraphicalEditor, { static: false })
    private editor: GraphicalEditor;

    constructor(
        dataService: SpecmateDataService,
        navigator: NavigatorService,
        route: ActivatedRoute,
        modal: ConfirmationModal,
        translate: TranslateService) {

        super(dataService, navigator, route, modal, translate);
    }

    protected onElementResolved(element: IContainer): void {
        this.model = <RGModel>element;
        this.dataService.readContents(this.model.url).then((contents: IContainer[]) => this.contents = contents);
    }

    protected get isValid(): boolean {
        if (!this.editor) {
            return true;
        }
        return this.editor.isValid;
    }
}

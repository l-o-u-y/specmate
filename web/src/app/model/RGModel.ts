	import './support/gentypes';
	import { Proxy } from './support/proxy';


	export class RGModel  {

		___nsuri: string = "http://specmate.com/20200611/model/requirements";
		public url: string;
		public className: string = "RGModel";
		public static className: string = "RGModel";
		// Attributes
		public id: EString;
		public name: EString;
		public description: EString;
		public recycled: EBoolean;
		public hasRecycledChildren: EBoolean;
		public modelRequirements: EString;

		// References
		
		public tracesTo: Proxy[];
		public tracesFrom: Proxy[];
		
		
		public prevRGModel: Proxy;
		public nextRGModel: Proxy;

		// Containment


	}


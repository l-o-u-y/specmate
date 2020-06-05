	import './support/gentypes';
	import { Proxy } from './support/proxy';


	export class IModelConnection  {

		___nsuri: string = "http://specmate.com/20200602/model/base";
		public url: string;
		public className: string = "IModelConnection";
		public static className: string = "IModelConnection";
		// Attributes
		public id: EString;
		public name: EString;
		public description: EString;
		public recycled: EBoolean;
		public hasRecycledChildren: EBoolean;

		// References
		
		public tracesTo: Proxy[];
		public tracesFrom: Proxy[];
		public source: Proxy;
		public target: Proxy;

		// Containment


	}


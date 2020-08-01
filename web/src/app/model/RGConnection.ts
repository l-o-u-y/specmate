	import './support/gentypes';
	import { Proxy } from './support/proxy';


	export class RGConnection  {

		___nsuri: string = "http://specmate.com/20200611/model/requirements";
		public url: string;
		public className: string = "RGConnection";
		public static className: string = "RGConnection";
		// Attributes
		public id: EString;
		public name: EString;
		public description: EString;
		public recycled: EBoolean;
		public hasRecycledChildren: EBoolean;
		public negate: EBoolean;
		public type: RGConnectionType;
		public label: EString;

		// References
		
		public tracesTo: Proxy[];
		public tracesFrom: Proxy[];
		public source: Proxy;
		public target: Proxy;

		// Containment


	}


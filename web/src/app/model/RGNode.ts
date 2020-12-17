	import './support/gentypes';
	import { Proxy } from './support/proxy';


	export class RGNode  {

		___nsuri: string = "http://specmate.com/20201010/model/requirements";
		public url: string;
		public className: string = "RGNode";
		public static className: string = "RGNode";
		// Attributes
		public id: EString;
		public name: EString;
		public description: EString;
		public recycled: EBoolean;
		public hasRecycledChildren: EBoolean;
		public x: EDouble;
		public y: EDouble;
		public width: EDouble;
		public height: EDouble;
		public type: NodeType;
		public component: EString;
		public temporary: EBoolean;

		// References
		
		public tracesTo: Proxy[];
		public tracesFrom: Proxy[];
		public outgoingConnections: Proxy[];
		public incomingConnections: Proxy[];
		public objects: Proxy[];

		// Containment


	}


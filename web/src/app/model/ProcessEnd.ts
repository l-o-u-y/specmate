	import './support/gentypes';
	import { Proxy } from './support/proxy';


	export class ProcessEnd  {

		___nsuri: string = "http://specmate.com/20200602/model/processes";
		public url: string;
		public className: string = "ProcessEnd";
		public static className: string = "ProcessEnd";
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

		// References
		
		public tracesTo: Proxy[];
		public tracesFrom: Proxy[];
		public outgoingConnections: Proxy[];
		public incomingConnections: Proxy[];

		// Containment


	}


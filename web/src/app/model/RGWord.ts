	import './support/gentypes';
	import { Proxy } from './support/proxy';


	export class RGWord  {

		___nsuri: string = "http://specmate.com/20201010/model/requirements";
		public url: string;
		public className: string = "RGWord";
		public static className: string = "RGWord";
		// Attributes
		public id: EString;
		public name: EString;
		public description: EString;
		public recycled: EBoolean;
		public hasRecycledChildren: EBoolean;
		public position: EInt;
		public originalText: EString;
		public processedText: EString;
		public posTag: EString;
		public removed: EBoolean;

		// References
		public node: Proxy;
		public incoming: Proxy[];
		public outgoing: Proxy[];

		// Containment


	}


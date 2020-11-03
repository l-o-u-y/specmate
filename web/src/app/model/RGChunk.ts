	import './support/gentypes';
	import { Proxy } from './support/proxy';


	export class RGChunk  {

		___nsuri: string = "http://specmate.com/20201010/model/requirements";
		public url: string;
		public className: string = "RGChunk";
		public static className: string = "RGChunk";
		// Attributes
		public id: EString;
		public name: EString;
		public description: EString;
		public recycled: EBoolean;
		public hasRecycledChildren: EBoolean;
		public text: EString;
		public removed: EBoolean;

		// References
		public node: Proxy;
		public incomingChunks: Proxy[];
		public outgoingChunks: Proxy[];
		public objects: Proxy[];

		// Containment


	}


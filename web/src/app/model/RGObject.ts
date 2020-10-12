	import './support/gentypes';
	import { Proxy } from './support/proxy';


	export class RGObject  {

		___nsuri: string = "http://specmate.com/20201010/model/requirements";
		public url: string;
		public className: string = "RGObject";
		public static className: string = "RGObject";
		// Attributes
		public id: EString;
		public description: EString;
		public recycled: EBoolean;
		public hasRecycledChildren: EBoolean;
		public originalText: EString;
		public processedText: EString;

		// References
		public chunk: Proxy;

		// Containment


	}

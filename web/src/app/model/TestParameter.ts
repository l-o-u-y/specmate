	import './support/gentypes';
	import { Proxy } from './support/proxy';


	export class TestParameter  {

		___nsuri: string = "http://specmate.com/20200309/model/testspecification";
		public url: string;
		public className: string = "TestParameter";
		public static className: string = "TestParameter";
		// Attributes
		public id: EString;
		public name: EString;
		public description: EString;
		public recycled: EBoolean;
		public hasRecycledChildren: EBoolean;
		public type: ParameterType;

		// References
		public assignments: Proxy[];

		// Containment


	}


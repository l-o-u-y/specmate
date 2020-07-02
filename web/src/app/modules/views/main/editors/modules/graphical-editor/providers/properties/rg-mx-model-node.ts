export class RGmxModelNode {
    public static COMPONENT_KEY = 'component';
    public static MODIFIER_KEY = 'modifier';
    public static TYPE_KEY = 'type';
    constructor(public component: string, public modifier: string, public type: string) {}
}

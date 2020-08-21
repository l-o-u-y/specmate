import EN.DEP.STANFORD.*
import EN.POS.PTB.*

def subtrees Source, Action, Target, Parent, Child, New, Old, TMP, TMP2

/*
TODO MA just leave this commented out for now
def rule Update_1 {
	[New] - cc -> IN:'of' - advmod -> RB:'instead'
	[New] - conj -> [Old]
}

def rule Update_2 {
	'(add)|(adds)|(adding)|(include)|(includes)|(including)|(implement)|(implements)|(implementing)':[TMP] - dobj -> [New]
}

def rule Update_2_2 {
	'(add)|(adds)|(adding)|(include)|(includes)|(including)|(implement)|(implements)|(implementing)':[TMP] - dobj -> [New] - prep -> IN:'(to)|(in)' - pobj -> [Parent]
}

def rule Update_3 {
	'(change)|(changes)|(changing)':[TMP] - prep -> IN:'to' - pobj -> [New]
	[TMP] - dobj -> * - prep -> IN:'from' - pobj -> [Old]
}

def rule Update_4 {
	'(remove)|(removes)|(removing)' - dobj -> [Old] - prep -> IN:'from' - pobj -> [Parent]
}

def rule Update_5 {
	'(replace)|(replaces)|(replacing)':[TMP] - dobj -> [Old]
	[TMP] - prep -> IN:'with' - pobj -> [New]
}
def rule Update_5_2 {
	'(replace)|(replaces)|(replacing)':[TMP] - conj -> [Old] - prep -> IN:'with' - pobj -> [New]
}

def rule Update_6 {
	[TMP] - dobj -> [New]
	[TMP] - prep -> IN:'of':[TMP2] - advmod -> RB:'instead'
	[TMP2] - pobj -> [Old]
}
*/

def rule Composition_1_1 {
	[Child] - prep -> (IN:'on'|IN:'of'|IN:'inside'|IN:'in') - pobj -> [Parent]
}

def rule Composition_2 {
	[Parent] - prep -> IN:'with' - pobj -> [Child]
}

def rule Composition_3 {
	[Parent] - acl -> 'showing' - pobj -> [Child]
}

def rule Composition_4 {
	'has':[TMP] - nsubj -> [Parent]
	[TMP] - dobj -> [Child]
}

def rule Inheritance_1 {
	'is':[TMP] - nsubj -> [Child]
	[TMP] - attr -> [Parent]
}

def rule Inheritance_1_2 {
	[Child] - relcl -> 'is':[TMP] - attr -> [Parent]
}

def rule Inheritance_2 { // a component -> called -> qbtn // TODO MA label
	[Parent] - acl -> [Action] - oprd -> [Child]
}

def rule Action_Explicit_1 {
	[Action] - nsubj -> [Source]
	[Action] - dobj -> [Target]
}

def rule Action_1 {
	[Action] - dobj -> [Target]
}

def rule Action_Explicit_Prep_1 {
	[Action] - prep -> TO:'to' - dobj -> [Target]
	[Action] - nsubj -> [Source]
}

def rule Action_Prep_1 {
	[Action] - prep -> TO:'to' - dobj -> [Target]
}

def subtrees  PartA, PartB, Head, Head_tmp
def subtrees  PartA_SubA, PartB_SubA

def rule Conjunction_NOR_1 {
	[PartA] - preconj -> CC:'neither'
	[PartA] - cc -> CC: 'nor'
	[PartA] - conj -> [PartB]
}

def rule Conjunction_NOR_2 {
	[PartA] - preconj -> CC:'neither'
	[PartA] - cc -> CC: 'nor'
	[PartA] - conj -> [PartB]
}

def rule Conjunction_XOR {
	[PartA] - advmod -> RB:'either'
	[PartA] - cc -> CC: 'or'
	[PartA] - conj -> [PartB]
}

def rule Conjunction_OR {
	[PartA] - cc -> CC:'or'
	[PartA] - conj -> [PartB]
}

def rule Conjunction_OR_2 {
	[PartA] - ccomp -> [PartB]  - nsubj -> [PartA_SubA] - conj -> [PartB_SubA]
	 [PartA_SubA] - cc -> CC:'or'
}

def rule Conjunction_AND_1 {
	[PartA] - cc -> CC:'and'
	[PartA] - conj -> [PartB]
	[PartA] - preconj -> DT:'both'
}

def rule Conjunction_AND_2 {
	[PartA] - cc -> CC:'and'
	[PartA] - conj -> [PartB]
}

def rule Conjunction_AND_3 {
	[PartA] - ccomp -> [PartB]  - nsubj -> [PartA_SubA] - conj -> [PartB_SubA]
	[PartA_SubA] - cc -> CC:'and'
}

def rule Conjunction_AND_4 {
	[PartA] - dobj -> [PartA_SubA]  - conj -> [PartB]
	[PartA_SubA] - cc -> CC:'and'
}

def rule Negation {
	[Head] - neg -> RB:*
}

def rule Negation_2 {
	[Head] - dobj -> [Head_tmp] - neg -> DT:*
}

def rule Negation_3 {
	[Head] - dobj-> [Head_tmp] - det -> DT:'no'
}
// TODO MA file handling for CEG/RG
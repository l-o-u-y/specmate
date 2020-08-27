import EN.DEP.STANFORD.*
// import EN.DEP.SPACY.*
import EN.POS.PTB.*
import EN.POS.LENA.*

def subtrees Source, Action, Target, Parent, Child, Label, New, Old, TMP, TMP2

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
	'(add)|(adds)|(adding)|(include)|(includes)|(including)|(implement)|(implements)|(implementing)':[TMP] - dobj -> [New] - prep -> IN:'(to)|(in)' - pobj -> noun:[Parent]
}

def rule Update_3 {
	'(change)|(changes)|(changing)':[TMP] - prep -> IN:'to' - pobj -> [New]
	[TMP] - dobj -> * - prep -> IN:'from' - pobj -> [Old]
}

def rule Update_4 {
	'(remove)|(removes)|(removing)' - dobj -> [Old] - prep -> IN:'from' - pobj -> noun:[Parent]
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
	noun:[Child] - prep -> IN:'(on)|(of)|(inside)|(in)':[Label] - pobj -> noun:[Parent]
}

def rule Composition_2 {
	noun:[Parent] - prep -> IN:'with':[Label] - pobj -> noun:[Child]
}

def rule Composition_3 {
	noun:[Parent] - acl -> 'showing':[Label] - pobj -> noun:[Child]
}

def rule Composition_4 {
	'has':[Label] - nsubj -> noun:[Parent]
	[Label] - dobj -> noun:[Child]
}

def rule Inheritance_1 {
	'is':[Label] - nsubj -> noun:[Child]
	[Label] - attr -> noun:[Parent]
}

def rule Inheritance_1_2 {
	noun:[Child] - relcl -> 'is':[Label] - attr -> noun:[Parent]
}

def rule Inheritance_2 { // a component -> called -> qbtn // TODO MA label
	noun:[Parent] - acl -> [Label] - oprd -> noun:[Child]
}

def rule Inheritance_Colon { // it comes in two shapes: this and that
	noun:[Parent] - appos -> noun:[Child]
}

def rule Action_Explicit_1 {
	[Action] - nsubj -> [Source]
	[Action] - dobj -> [Target]
}

def rule Action_1 {
	[Action] - dobj -> [Target]
}

def rule Action_Explicit_Prep_1 {
	[Action] - prep -> [Label] - pobj -> [Target]
	[Action] - nsubj -> [Source]
}

def rule Action_Prep_1 {
	[Action] - prep -> [Label] - pobj -> [Target]
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
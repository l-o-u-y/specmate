import EN.DEP.STANFORD.*
import EN.POS.PTB.*

def subtrees Source, Action, Target, Parent, Child, New, Old, TMP

def rule Action_1 {
	[Action] - dobj -> [Target]
}

def rule Action_Explicit_1 {
	[Action] - dobj -> [Target]
	[Action] - nsubj -> [Source]
}

def rule Action_Explicit_Prep_1 {
	[Action] - prep -> TO:'to' - dobj -> [Target]
	[Action] - nsubj -> [Source]
}

// VB conj VB dobj NN conj NN <-- TODO MA
def rule Action_1 {
	[Action] - dobj -> [Target]
}

def rule Action_Prep_1 {
	[Action] - prep -> TO:'to' - dobj -> [Target]
}

// NOTE: (IN:'on'|IN:'of'|IN:'inside'|IN:'in') doesn't do what u think it does
def rule Composition_1_1 {
	[Child] - prep -> IN:'on' - pobj -> [Parent]
}
def rule Composition_1_2 {
	[Child] - prep -> IN:'of' - pobj -> [Parent]
}
def rule Composition_1_3 {
	[Child] - prep -> IN:'inside' - pobj -> [Parent]
}
def rule Composition_1_4 {
	[Child] - prep -> IN:'in' - pobj -> [Parent]
}

def rule Composition_2 {
	[Parent] - prep -> IN:'with' - pobj -> [Child]
}

// NNS:[Child] - cc -> 'and'; [Child] - conj -> NNS <-- TODO MA
def rule Composition_3 {
	[Parent] - acl -> 'showing' - pobj -> [Child]
}

/*
def rule Update_1 {
	[New] - cc -> IN:'of' - advmod -> RB:'instead'
	[New] - conj -> [Old]
}

def rule Update_2 {
	'(add*)|(includ*)|(implement*)':[TMP] - dobj -> [New]
}

def rule Update_2_2 {
	'(add*)|(includ*)|(implement*)':[TMP] - dobj -> [New] - prep -> IN:'(to)|(in)' - pobj -> [Parent]
}

def rule Update_3 {
	'(chang*)':[TMP] - dobj -> NN - prep -> IN:'from' - pobj -> [Old]
	[TMP] - prep -> IN:'to' - pobj -> [New]
}


def rule Update_4 {
	'(remov*)' - dobj -> [Old] - prep -> IN:'from' - pobj -> [Parent]
}
*/

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
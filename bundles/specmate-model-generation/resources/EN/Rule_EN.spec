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
// TODO MA file handling for CEG/RG
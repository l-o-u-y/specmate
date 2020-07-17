import EN.DEP.STANFORD.*
import EN.POS.PTB.*

def subtrees Limit, Conditional, Source, Action, Target, Parent, Child, New, Old, TMP

def rule Action_Explicit_1 {
	VB:[Action] - dobj -> NN:[Target]
	[Action] - nsubj -> *:[Source]
}

def rule Action_Explicit_Prep_1 {
	VB:[Action] - prep -> TO:'to' - dobj -> NN:[Target]
	[Action] - nsubj -> *:[Source]
}

// VB conj VB dobj NN conj NN <-- TODO MA
def rule Action_1 {
	VB:[Action] - dobj -> NN:[Target]
}

def rule Action_Prep_1 {
	VB:[Action] - prep -> TO:'to' - dobj -> NN:[Target]
}

/*
def rule Composition_1 {
	(NN:*:[Child]|NNS:*:[Child]) - prep -> IN:('on'|'of') - pobj -> NN:[Parent]
}
def rule Composition_2 {
	NN:[Parent] - prep -> IN:'with' - pobj -> (NNS|NN):[Child]
}

// NNS:[Child] - cc -> 'and'; [Child] - conj -> NNS <-- TODO MA
def rule Composition_3 {
	NN:[Parent] - acl -> 'showing' - pobj -> (NNS|NN):[Child]
}


def rule Composition_4 {
	(NNS|NN):[Child] - prep -> IN:'inside' - pobj -> NN:[Parent]
}


def rule Update_1 {
	NN:[New] - cc -> IN:'of' - advmod -> RB:'instead'
	[New] - conj -> NN:[Old]
}

def rule Update_2 {
	(VB|VBZ):('add*'|'includ*'|'implement*'):[TMP] - dobj -> (NN|NNS):[New]
}

def rule Update_2.2 {
	(VB|VBZ):('add*'|'includ*'|'implement*'):[TMP] - dobj -> (NN|NNS):[New] - prep -> IN:('to'|'in') - pobj -> (NN|NNP)
}

def rule Update_3 {
	'chang*':[TMP] - dobj -> NN - prep -> 'from' - pobj -> (NN|NNP):[Old]
	[TMP] - prep -> IN:'to' - pobj -> (NN|NNP):[New]
}

def rule Update_4 {
	(VB|VBZ):'remov*' - dobj -> NN:[Old] - from -> NN:[Parent]
}*/




/* def rule Action_Conj_1.1 {
	vb:[Action] - conj -> vb - dobj -> nn:[Target]
}

def rule Action_Conj_1.2 {
	vb:[Action] - conj -> vb - dobj -> nn - conj -> nn:[Target]
}

def rule Action_Conj_1.3 {
	vb:[Action] - dobj -> nn - conj -> nn:[Target]
}

def rule Action_Explicit_1 {
	vb:[Action] - dobj -> nn:[Target]
	[Action] - nsubj -> [Source]
}

def rule Action_Explicit_Conj_1.1 {
	vb:[Action] - conj -> vb - dobj -> nn:[Target]
	[Action] - nsubj -> [Source]
}

def rule Action_Explicit_Conj_1.2 {
	vb:[Action] - conj -> vb - dobj -> nn - conj -> nn:[Target]
	[Action] - nsubj -> [Source]
}

def rule Action_Explicit_Conj_1.3 {
	vb:[Action] - dobj -> nn - conj -> nn:[Target]
	[Action] - nsubj -> [Source]
} */



/* def rule Inheritance_1 {
	'is':[TMP] - nsubj -> [Parent]
	[TMP] - attr -> [Child]
}

def rule Composition_1 {
	'has':[TMP] - nsubj -> [Parent]
	[TMP] - attr -> [Child]
}

def rule Composition_2 {
	'has':[TMP] - nsubj -> [Parent]
	[TMP] - attr -> 'component' - acl -> 'called' - oprd -> [Child]
}

def rule Composition_3 {
	'is':[TMP] - nsubj -> [Child]
	[TMP] - dobj -> 'component' - prep -> 'of' - pobj -> [Parent]
}

def rule Composition_4 {
	'has':[TMP] - nsubj -> [Child]
	[TMP] - dobj -> 'parent' - acl -> 'called' - oprd -> [Parent]
}

def rule Composition_5 {
	'is':[TMP] - nsubj -> [Parent]
	[TMP] - dobj -> 'parent' - prep -> 'of' - pobj -> [Child]
} */

def rule LimitedCondition_1 {
	[Limit] - nsubjpass -> [Conditional] - prep -> IN:'until'
}

def rule LimitedCondition_2 {
	[Limit] - csubjpass -> [Conditional] - prep -> IN:'until'
}


def subtrees Cause, Effect, Effect_SubA, Cause_SubA, Cause_SubB, Cause_SubC, Cause_SubD

//  If the tool detects an error then the tool beeps.
def rule Condition1_1 {
	[Effect] - advcl -> [Cause]	- mark -> IN:'if'
	[Effect] - advmod -> RB: 'then'
}

def rule Condition1_2 {
	[Effect] - dep -> [Cause] - mark -> IN:'if'
	[Effect] - advmod -> RB: 'then'
}

def rule Condition1_3 {
	[Effect] - dobj -> [Cause] - mark -> IN:'if'
	[Effect] - advmod -> RB: 'then'
}

//  If/Because/Although the tool detects an error, the tool beeps.
//  The tool beeps if/because/although the tool detects an error.
def rule Condition1_4 {
	[Effect] - advcl -> [Cause] - mark -> IN:'(if)|(because)|(although)'
}

def rule Condition1_5 {
	[Effect] - dep -> [Cause] - mark -> IN:'(if)|(because)|(although)'
}

// When the tool detects an error then the tool beeps.
def rule Condition2_1 {
	[Cause] - ccomp -> [Effect]
	[Cause] - advmod -> WRB:'when'
	[Cause] - advmod -> RB: 'then'
}


def rule Condition2_2 {
	[Cause] - dobj -> [Effect]
	[Cause] - advmod -> WRB:'when'
	[Cause] - advmod -> RB: 'then'
}


def rule Condition2_3 {
	[Cause] - advcl -> [Effect]
	[Cause] - advmod -> RB: 'then'
	[Cause] - advmod -> WRB:'when'
}


def rule Condition2_4 {
	[Cause] - ccomp -> [Effect]
	[Cause] - mark -> IN:'If'
}


def rule Condition2_5 {
	[Cause] - mark -> IN:'If'
	[Cause] - advmod -> RB:'then'
	[Cause] - dep -> [Effect]
}

def rule Condition2_6 {
	[Cause] - mark -> IN:'If'
	[Cause] - dobj -> [Cause_SubA] - prep -> [Cause_SubB] - pobj -> [Cause_SubC] - advmod -> RB:'then'
	[Cause_SubC] - dep -> [Effect]
}

def rule Condition2_7 {
	[Cause] - mark -> IN:'If'
	[Cause] - dobj -> [Cause_SubA] - dep -> [Cause_SubB] - dep -> [Cause_SubC] - prep -> [Cause_SubD]  - advmod -> RB:'then'
	[Cause_SubD] - dep -> [Effect]
}

// When the tool detects an error, the tool beeps.
def rule Condition2_8 {
	[Effect] - dep -> [Cause] - advmod -> WRB:'when'
}

// The tool beeps when the tool detects an error.
def rule Condition2_9 {
	[Effect] - advcl -> [Cause] - advmod -> WRB:'when'
}

def rule Condition2_10 {
	[Cause] - advmod -> WRB: 'when'
	[Cause] - parataxis -> [Effect]
}



// The tool detects an error and for this reason the tool beeps .
def rule Condition3_1 {
	[Cause] - conj -> [Effect]
	[Cause] - cc -> 'and'
	[Effect] - prep -> 'for' - pobj -> 'reason' - det -> 'this'
}

// The tool detects an error and as a result the tool beeps.
def rule Condition4_1 {
	[Cause] - advcl -> [Effect] - mark -> IN:'as'
	[Effect] - nsubj -> NN:'result' - det -> DT:'a'
}
def rule Condition4_2 {
	[Cause] - ccomp -> [Effect]
	[Cause] - prep -> IN:'as' - pobj -> NN:'result' -det -> DT:'a'
}

// Specmate shows the error window as a result of invalid login data.
def rule Condition4_3 {
	[Effect] - dobj -> [Effect_SubA] - prep -> IN:'as' - pobj -> NN:'result':[TMP] - prep -> IN:'of' - pobj -> [Cause]
}

// Specmate shows the error window as a result of invalid login data.
def rule Condition4_4 {
	[Effect] - prep -> IN:'as' - pobj -> NN:'result':[TMP] - prep -> IN:'of' - pobj -> [Cause]
}
def rule Condition4_5 {
	[Cause] - prep -> IN:'as' - pobj -> NN:'result' -rcmod -> [Effect]
}

// The tool beeps due to the tool detecting an error.
def rule Condition5_1 {
	[Cause] - prep -> 'due' - pcomp -> TO:'to' - pobj -> [Effect]
}
// Due to the tool detecting an error, the tool beeps.
def rule Condition5_2 {
	JJ:'due':[TMP] - prep -> TO:'to' - pobj -> [Cause]
	[TMP] - dep -> [Effect]
}

// The tool beeps owning to the tool detecting an error.
def rule Condition6_1 {
	VBG:'owning':[TMP] - prep -> TO:'to' - pobj -> [Cause]
	[TMP] - nsubj -> [Effect]
}

// Owning to the tool detecting an error, the tool beeps.
def rule Condition6_2 {
	[Effect] - dep -> VBG:'owning':[TMP] - prep -> TO:'to' - pobj -> [Cause]
}

// The tool beeps provided/supposing that the tool detected an error.
def rule Condition7_1 {
	[Effect] - partmod -> (VBN:'provided'|VBG:'supposing') -ccomp -> [Cause] - complm -> IN:'that' 
}
	
// Specmate saves the model provided that the model is correct.
def rule Condition7_2 {
	[Effect] -ccomp -> (VBD:'provided'|VBG:'supposing') -ccomp -> [Cause] - complm -> IN:'that' 
	[Effect] -ccomp -> (VBD:'provided'|VBG:'supposing') -nsubj -> [Effect_SubA]
}
// Supposing that the tool detected an error, the tool beeps .
def rule Condition7_3 {
	[Effect] -dep-> VBG:'supposing' -ccomp-> [Cause] -complm-> IN:'that'
}

// Provided that the tool detected an error, the tool beeps.
def rule Condition7_4 {
	VBN:'provided'  - ccomp -> [Cause] - dobj -> [Cause_SubA] -appos -> [Effect]
	[Cause] - complm -> IN:'that'
}

// Provided that the tool detected an error, the tool beeps.
// Provided that the tool crashed, the tool beeps.
def rule Condition7_5 {
	VBN:'provided'  - ccomp -> [Cause] - dobj -> [Effect]
	[Cause] - complm -> IN:'that'
}

// The tool beeping has something/(a lot) to do with it detecting an error.
def rule Condition8_1 {
	VBZ:*:[TMP] - nsubj -> [Effect]
	[TMP] - dobj -> NN:"(something)|lot" - infmod -> VB:"do" - prep -> IN:'with' - pobj -> [Cause]
}

// The tool detects an error so that it can report it.
def rule Condition9_1 {
	[Cause] - advcl -> [Effect] - dep -> IN:'that'
	[Effect] - advmod -> RB:'so'
}

// The tool detects an error so that it can report it.
def rule Condition9_2 {
	[Cause] - dobj -> [Effect] - dep -> IN:'that'
	[Effect] - advmod -> RB:'so'
}

// The tool detects an error so to report it.
def rule Condition9_3 {
	[Cause] - advmod -> RB:'so'
	[Cause] - xcomp -> [Effect] - aux -> TO:'to'
}


// The tool detects an error in order that it can report it. 
def rule Condition10_1 {
	[Cause] - prep -> IN:'in' - pobj -> NN:'order' - ccomp ->  [Effect] - complm -> IN:'that'
}
// In order that the tool can report an error, the tool detects errors. 
def rule Condition10_2 {
	[Cause] - prep -> IN:'in' - pobj -> NN:'order' 
	[Cause] - advcl ->  [Effect] - complm -> IN:'that'
}
// The tool detects an error in order to report it.
def rule Condition10_3 {
	[Cause] - advcl -> [Effect]
	[Effect] - mark -> IN:'in'
	[Effect] - dep -> NN:'order'
	[Effect] - aux -> TO:'to'
}

// The tool beeps even though the tool detected an error .
def rule Condition11_1 {
	[Cause] - nsubj -> [Effect]
	[Cause] - advmod -> RB:'even'
	[Cause] - dep -> RB:'though'
}
// Even though the tool detected an error, the tool beeps.
// Even though the tool crashed, the tool beeps.
def rule Condition11_2 {
	[Effect] - advcl -> [Cause] - advmod -> RB:'even'
	[Cause] - dep -> IN:'though'
}

// The tool beeps in case that the tool detected an error.
def rule Condition12_1 {
	[Effect] - ccomp -> [Cause] - complm -> IN:'that'
	[Effect] - prep -> IN:'in' - pobj -> NN:'case'
}

// In case that the tool detected an error, the tool beeps.
// In case that the tool crashes, the tool beeps.
def rule Condition12_2 {
	IN:'in':[TMP] - pobj -> NN:'case'
	[TMP] - dep -> IN:'that' - pobj -> [Cause] - appos -> [Effect]
}

// The tool beeps on the condition that the tool detected an error .
def rule Condition13_1 {
	[Effect] - ccomp -> [Cause] - complm -> IN:'that'
	[Effect] - prep -> IN:'on' - pobj -> NN:'condition'
}

// On the condition that the tool detected an error, the tool beeps.
// On the condition that the tool crashes, the tool beeps.
def rule Condition13_2 {
	IN:'on' - pobj -> NN:'condition' - prep -> IN:'that' - pobj -> [Cause] - appos -> [Effect]
}
// TODO "Any child in this tree may have a subtree X"

// The tool detects an error. For this reason the tool beeps.
//def rule DanglingConditional1 {
//	IN:'for' - pobj -> [Effect] - nsubj -> NN:'reason'
//}

// The tool detects an error. As a result the tool beeps.
//def rule DanglingConditional2 {
//	[Effect] - mark -> IN:'as'
//	[Effect] - nsubj -> NN:'result'
//}

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

def subtrees Variable, Condition, Variable_Sub

def rule CondVar_1 {
	[Condition] - nsubj -> [Variable]
}

def rule CondVar_2 {
	[Condition] - nsubjpass -> [Variable]
}

def subtrees Verb, Object

def rule VerbObject {
	[Verb] - dobj -> [Object]
}
	
def subtrees Preposition

def rule VerbPreposition {
	[Verb] - prep -> [Preposition]
}